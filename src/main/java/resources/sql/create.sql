CREATE TABLE users (id SERIAL PRIMARY KEY,
                    name VARCHAR(255) NOT NULL,
                    password_hash VARCHAR(255) NOT NULL,
                    role VARCHAR(20) NOT NULL
);

CREATE TABLE orders (id SERIAL PRIMARY KEY,
                    client_id INTEGER NOT NULL,
                    price NUMERIC(10, 2),
                    order_type VARCHAR(20) NOT NULL,

                    CONSTRAINT fk_orders_client
                    FOREIGN KEY (client_id) REFERENCES users(id)
);

CREATE TABLE reviews (id SERIAL PRIMARY KEY,
                    client_id INTEGER NOT NULL,
                    order_id INTEGER NOT NULL,
                    text TEXT,
                    rating INTEGER NOT NULL,

                    CONSTRAINT fk_reviews_client
                    FOREIGN KEY (client_id) REFERENCES users(id),

                    CONSTRAINT fk_reviews_order
                    FOREIGN KEY (order_id) REFERENCES orders(id)
);

CREATE TABLE trainings (id SERIAL PRIMARY KEY,
                        trainer_id INTEGER NOT NULL,
                        order_id INTEGER NOT NULL,
                        exercise VARCHAR(255),
                        count_approaches INTEGER NOT NULL,
                        tonnage NUMERIC(10, 2),

                        CONSTRAINT fk_trainings_trainer
                            FOREIGN KEY (trainer_id) REFERENCES users(id),

                        CONSTRAINT fk_trainings_order
                            FOREIGN KEY (order_id) REFERENCES orders(id)
)


