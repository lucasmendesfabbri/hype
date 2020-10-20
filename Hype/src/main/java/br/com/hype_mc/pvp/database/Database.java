package br.com.hype_mc.pvp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class Database {

	public Connection connection;

	private String host, database, username, password;
	private int port = 3306;

	public Database() {
		
		try {
			
			if(connection!=null)this.connection.close();
			
			this.host = "sql10.freemysqlhosting.net";this.database="sql10371765";this.username="sql10371765";this.password="R6k7z8jwse";
			if(this.connection!=null)this.connection.close();
			Class.forName("com.mysql.jdbc.Driver");
			String LINK = "jdbc:mysql://"+this.host+":"+this.port+"/"+this.database;
			this.connection = DriverManager.getConnection(LINK, this.username, this.password);
			Bukkit.getLogger().info("CONNECTED MYSQL");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void closeConnection() {
		try {
			this.connection.close();
			System.out.println("CONNECTION CLOSE MYSQL");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getHost() {
		return host;
	}

	public String getDatabase() {
		return database;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}


}
