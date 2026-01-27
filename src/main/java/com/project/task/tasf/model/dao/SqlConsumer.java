package com.project.task.tasf.model.dao;

import java.sql.SQLException;

public interface SqlConsumer<T> {
    void accept(T t) throws SQLException;
}
