package com.belhard.bookstore.connection;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;

import java.io.Closeable;
import java.sql.Connection;

@Log4j2
public class DataSourceImpl implements DataSource, Closeable {
    @Value("${my.app.db.${my.app.profile}.url}")
    private String url;

    @Value("${my.app.db.${my.app.profile}.user}")
    private String user;

    @Value("${my.app.db.${my.app.profile}.password}")
    private String password;

    @Value("${my.app.db.${my.app.profile}.drv}")
    private String drv;

    @Value("${my.app.db.${my.app.profile}.poolSize}")
    private String poolSizeString;
    private int poolSize;

    private ConnectionPool connectionPool;

    public void init() {
        poolSize = Integer.parseInt(poolSizeString);
        connectionPool = new ConnectionPool(url, user, password, drv, poolSize);
    }

    public Connection getConnection() {
        try {
            log.info("Connection to the database is being established...");
            Class.forName("org.postgresql.Driver");
            if (connectionPool == null) {
                connectionPool = new ConnectionPool(url, user, password, drv, poolSize);
            }
            return connectionPool.getConnection();
        } catch (ClassNotFoundException e) {
            log.error("Error with connection");
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if (connectionPool != null) {
            connectionPool.destroyPool();
            connectionPool = null;
        }
    }
}
