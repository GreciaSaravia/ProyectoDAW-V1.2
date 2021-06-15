package net.compensacion.utils;
import java.sql.Connection;
import java.sql.DriverManager;
public class MySqlBDConexion {
	public static Connection getConexion(){
		Connection cn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost/sis_compensacion_2020_2?useSSL=false","root","mysql");
			//cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?useSSL=false","root","mysql");
			} catch (Exception e) {
			e.printStackTrace();
		}
		return cn;	
	}
}


