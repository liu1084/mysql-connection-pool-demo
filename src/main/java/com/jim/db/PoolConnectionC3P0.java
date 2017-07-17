package com.jim.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by jim on 2017/7/12.
 * This class is ...
 */
public class PoolConnectionC3P0 {
	private static DataSource DATASOURCE;
	private static String DRIVER;
	private static String URL;
	private static String USERNAME;
	private static String PASSWORD;
	private static int MINPOOLSIZE;
	private static int ACQUIREINCREMENT;
	private static int MAXPOOLSIZE;

	static {
		final ResourceBundle config = ResourceBundle.getBundle("database", new Locale("zh_CN"));
		DRIVER = config.getString("driverClass");
		URL = config.getString("url");
		USERNAME = config.getString("username");
		PASSWORD = config.getString("password");
		MINPOOLSIZE = Integer.parseInt(config.getString("minPoolSize"));
		ACQUIREINCREMENT = Integer.parseInt(config.getString("maxPoolSize"));
		MAXPOOLSIZE = Integer.parseInt(config.getString("acquireIncrement"));
		DATASOURCE = setupDataSource();
	}

	private static DataSource setupDataSource() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		try {
			comboPooledDataSource.setDriverClass(DRIVER);
			comboPooledDataSource.setJdbcUrl(URL);
			comboPooledDataSource.setUser(USERNAME);
			comboPooledDataSource.setPassword(PASSWORD);
			comboPooledDataSource.setMinPoolSize(MINPOOLSIZE);
			comboPooledDataSource.setMaxPoolSize(MAXPOOLSIZE);
			comboPooledDataSource.setAcquireIncrement(ACQUIREINCREMENT);
			comboPooledDataSource.setUnreturnedConnectionTimeout(5000);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		return comboPooledDataSource;
	}

	public static Connection getConnection() {
		DataSource dataSource = setupDataSource();
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}


}
