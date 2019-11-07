package katsai.nikolai.internetshop.dao.impl.jdbc;

import java.sql.Connection;

public class AbstractDaoClass<T> {
    protected static final String DB_NAME = "internet_shop";
    protected final Connection connection;

    public AbstractDaoClass(Connection connection) {
        this.connection = connection;
    }
}
