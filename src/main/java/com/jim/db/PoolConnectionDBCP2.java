package com.jim.db;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by jim on 2017/7/13. This class is ...
 */
public class PoolConnectionDBCP2 {

	private static BasicDataSource basicDataSource;

	private PoolConnectionDBCP2() {

	}

	public static BasicDataSource getInstance() {
		if (basicDataSource == null) {
			basicDataSource = new BasicDataSource();
			final ResourceBundle config = ResourceBundle.getBundle("database", new Locale("zh_CN"));
			basicDataSource.setDriverClassName(config.getString("driverClass"));
			basicDataSource.setUrl(config.getString("url"));
			basicDataSource.setUsername(config.getString("username"));
			basicDataSource.setPassword(config.getString("password"));
			basicDataSource.setMinIdle(5);
			basicDataSource.setMaxIdle(20);
			basicDataSource.setMaxOpenPreparedStatements(180);
		}
		return basicDataSource;
	}

	public static Connection getConnection() throws SQLException {
		return basicDataSource.getConnection();
	}
}
