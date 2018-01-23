package com.epoint.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.util.DruidDataSourceUtils;

public class DataSourceUtil {
	private static DruidDataSource ds ;//数据源对象
	private DataSourceUtil() {
		
	}
	public static  DruidDataSource getDruidDataSouce() {
		if(ds==null) {
			ds =new DruidDataSource();
			ds.setDriverClassName("com.mysql.jdbc.Driver");
			ds.setUrl(ConfigUtil.getJDBCConfigValue("url"));
			ds.setUsername(ConfigUtil.getJDBCConfigValue("username"));
			ds.setPassword(ConfigUtil.getJDBCConfigValue("password"));
		}
		return ds;
	}
}
