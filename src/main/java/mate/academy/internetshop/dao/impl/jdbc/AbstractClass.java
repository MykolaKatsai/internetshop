package mate.academy.internetshop.dao.impl.jdbc;

import java.sql.Connection;

public class AbstractClass<T> {
    protected final Connection connection;

    public AbstractClass(Connection connection) {
        this.connection = connection;
    }
}
