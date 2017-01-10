package com.zjh.db;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

public class DBCP {

	private static DataSource ds = null;
	// 在静态代码块中创建数据库连接池
	static {
		try {
			// 加载dbcp.properties配置文件
			InputStream in = Druid.class.getClassLoader().getResourceAsStream(
					"dbcp.properties");
			Properties prop = new Properties();
			prop.load(in);
			// 创建数据源
			ds = BasicDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 从数据源中获取数据库连接
	public static Connection getConnection() {
		Connection conn = null;
		// 从数据源中获取数据库连接
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 释放连接
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				// 将Connection连接对象还给数据库连接池
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}  
