package day0520_core;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Statement;



/**
 * 封装可公用的方法
 * 
 * @author 兰
 *
 */

public class JDBCUtils {
	private JDBCUtils() {
	};

	static Properties prop = null;

	static {
		try {
			prop = new Properties();
			File file = new File("jdbc.propersies");
			prop.load(new FileInputStream(file));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getconnection() {
		try {
			Class.forName(prop.getProperty("DriverClass"));
			Connection conn = DriverManager.getConnection(prop.getProperty("JdbcUrl"), 
					prop.getProperty("name"),
					prop.getProperty("password"));
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static void close(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				st = null;
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}

	}

}
