package com.project.task.tasf.model.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;

public enum ConnectionPool {
    INSTANCE;

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private LinkedBlockingQueue<Connection> free;
    private LinkedBlockingQueue<Connection> used;
    private String url;
    private String user;
    private String password;
    private String driver;
    private int poolSize;

    ConnectionPool() {
        loadProperties();
        initPool();
    }

    private void loadProperties() {
        Properties properties = new Properties();
        try (InputStream inputStream = ConnectionPool.class.getClassLoader()
                .getResourceAsStream("db.properties")) {
            if (inputStream == null) {
                throw new IOException("db.properties not found");
            }
            properties.load(inputStream);
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
            driver = properties.getProperty("db.driver");
            poolSize = Integer.parseInt(properties.getProperty("db.pool.size"));

        } catch (IOException e) {
            logger.fatal("Error loading db properties", e);
            throw new ExceptionInInitializerError("DB properties not found");
        }
    }

    private void initPool() {
        try {
            Class.forName(driver);
            free = new LinkedBlockingQueue<>(poolSize);
            used = new LinkedBlockingQueue<>(poolSize);
            for (int i = 0; i < poolSize; i++) {
                Connection connection = createProxyConnection();
                free.add(connection);
            }

            logger.info("Pool has been initialized");

        } catch (ClassNotFoundException | SQLException e) {
            logger.fatal("Error initializing connection pool", e);
            throw new ExceptionInInitializerError("Error initializing connection pool");
        }
    }

    private Connection createProxyConnection() throws SQLException {
        Connection realConnection = DriverManager.getConnection(url, user, password);

        return (Connection) java.lang.reflect.Proxy.newProxyInstance(
                ConnectionPool.class.getClassLoader(),
                new Class[]{Connection.class},
                (proxy, method, args) -> {
                    if ("close".equals(method.getName())) {
                        releaseConnection((Connection) proxy);
                        return null;
                    }
                    return method.invoke(realConnection, args);
                }
        );
    }

    public Connection getConnection() {
        try {
            Connection connection = free.take();
            used.put(connection);
            if (!connection.isValid(1)) {
                logger.warn("Connection is not valid, recreating...");
                Connection newConn = createProxyConnection();
                used.remove(connection);
                used.put(newConn);
                return newConn;
            }

            return connection;

        } catch (InterruptedException e) {
            logger.error("Error getting connection", e);
            Thread.currentThread().interrupt();
            throw new ExceptionInInitializerError("Thread interrupted while getting connection");
        } catch (SQLException e) {
            throw new ExceptionInInitializerError();
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                used.remove(connection);
                free.put(connection);
            } catch (InterruptedException e) {
                logger.error("Error releasing connection", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public void destroyPool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                Connection freeConnection = free.poll();
                if (freeConnection != null) freeConnection.unwrap(Connection.class).close();

                Connection usedConnection = used.poll();
                if (usedConnection != null) usedConnection.unwrap(Connection.class).close();

            } catch (SQLException e) {
                logger.error("Error closing connection", e);
            }
        }

        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
                logger.info("Driver deregistered successfully");
            } catch (SQLException e) {
                logger.error("Error deregistering driver {}", driver, e);
            }
        }
    }
}

