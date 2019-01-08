package com.motosport.football.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import static com.motosport.football.config.SoccerConstants.DB_RECONNECT_INTERVAL_IN_SECONDS;

public final class DatabaseUtil {

    private static final Logger log = LoggerFactory.getLogger(DatabaseUtil.class);

    private DatabaseUtil() {
    }

    /**
     * Проверка доступности DataSource
     *
     * @param dataSource {@link DataSource}
     */
    public static void checkDataSource(DataSource dataSource) {
        try (Statement statement = dataSource.getConnection().createStatement()) {
            statement.executeQuery("select 1");
            log.info("Connection to the database is established");
        } catch (SQLException e) {
            log.warn("No database connection");
            try {
                TimeUnit.SECONDS.sleep(DB_RECONNECT_INTERVAL_IN_SECONDS);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            log.warn("Attempt to re-establish the connection");
            checkDataSource(dataSource);
        }
    }

}
