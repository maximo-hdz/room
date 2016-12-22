package com.notmusa.room.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.notmusa.room.db.dbutils.PoolDB;

public class AdmsDB extends PoolDB {
	protected static AdmsDB instance;
	private Connection connection = null;
	
	private AdmsDB() throws SQLException {
		super();
		
	}
	public static AdmsDB getInstance() throws ClassNotFoundException,
	SQLException {		
		instance = new AdmsDB(); 
		return instance;
	}
	public Adms selectByUserPassword(String user,String password) throws java.sql.SQLException {
		PreparedStatement stmt = null;
		ResultSet res = null;
		Adms empleado = null;		    
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(
			"select * from adms where user = ? and password=? ");
			stmt.setString(1,user);
			stmt.setString(2, password);
			
			res = stmt.executeQuery();
			
			if (res.next()) {
				empleado = new Adms();
				empleado.setUser(res.getString("user"));
				empleado.setDescripcion(res.getString("password"));
				empleado.setPassword(res.getString("descripcion"));
				
			}
		}
		finally {
			try {
				stmt.close();
				res.close();
				connection.close();
			}
			catch (SQLException sqle) {
				throw new SQLException(sqle.getMessage());
			}
		}
		return empleado;
	}
	public void delete(Object obj) throws SQLException {
	}
	
	public void insert(Object obj) throws SQLException {
	}
	
	public void update(Object obj) throws SQLException {
	}
	
}
