package com.notmusa.room.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.notmusa.room.db.dbutils.PoolDB;

public class ReservacionDB extends PoolDB {

	protected static ReservacionDB instance;
	private Connection connection = null;
	
	private ReservacionDB() throws SQLException {
		super();
		
	}
	public static ReservacionDB getInstance() throws 
	SQLException {		
		instance = new ReservacionDB(); 
		return instance;
	}
	public void delete(Object obj) throws SQLException {
		PreparedStatement stmt = null;
	    Reservacion reservacion = null;

	   
	      reservacion = (Reservacion) obj;
	   

	    try {
	      connection = getConnection(); 
	      stmt = connection.prepareStatement(
	          "delete from reservas where id_reserva = ?");

	      stmt.setInt(1, reservacion.getId_reservas());

	      if (stmt.executeUpdate() == 0) {
	        throw new SQLException("Error al borrar de la tabla reservas id " +
	                               reservacion.getId_reservas());
	      }
	    }
	    finally {
	      try {
	        stmt.close();
	        connection.close(); 
	      }
	      catch (SQLException sqle) {
	        throw new SQLException(sqle.getMessage());
	      }
	    }
	}	
	
	
	public void update(Object obj) throws SQLException {}
	public void insert(Object obj) throws SQLException {}
	
	
	public ArrayList selectAll() throws java.sql.SQLException {
		PreparedStatement stmt = null;
		ResultSet res = null;
		Reservacion reservacion = null;
		ArrayList reservaciones=new ArrayList();
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(
			"select id_reserva, id_sala, tiempo, mail, departamento, comentario from reservas order by id_reservas");
			
			
			res = stmt.executeQuery();
			
			while (res.next()) {
				reservacion = new Reservacion();
				reservacion.setId_reservas(res.getInt("id_reserva"));
				reservacion.setId_sala(res.getInt("id__sala"));
				reservacion.setTiempo(res.getTimestamp("tiempo"));
				reservacion.setMail(res.getString("mail"));
				reservacion.setDepartamento(res.getString("departamento"));
				reservacion.setComentario(res.getString("departamento"));
				reservaciones.add(reservacion);
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
		return reservaciones;
	}
/**
 * Busca una reservacion en especial en base a la sala,la hora y fecha
 * @param fecha
 * @param hora
 * @return
 * @throws SQLException
 */
	public Reservacion findReservacion(int id_sala,String fecha,String hora) throws
	SQLException {
		PreparedStatement stmt = null;
		ResultSet res = null;
		Reservacion reservacion = null;
		String fechaHora=fecha+" "+hora;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(
			"select * from reservas where tiempo=? and id_sala=? ");
			stmt.setString(1,fechaHora);
			stmt.setInt(2,id_sala);
			
		
			res = stmt.executeQuery();
			
			if(res.next()) {			
						
				reservacion = new Reservacion();
				reservacion.setId_reservas(res.getInt("id_reserva"));
				reservacion.setId_sala(res.getInt("id_sala"));
				reservacion.setTiempo(res.getTimestamp("tiempo"));
				reservacion.setMail(res.getString("mail"));
				reservacion.setDepartamento(res.getString("departamento"));
				reservacion.setComentario(res.getString("comentario"));
				
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
		return reservacion;
	}
	
	
	/**
	 * Inserta nueva reservacion
	 * @param fecha
	 * @param hora
	 * @return
	 * @throws SQLException
	 */
		public void insertReservacion(Reservacion reserBean) throws
		SQLException {
			PreparedStatement stmt = null;
			ResultSet res = null;
			Reservacion reservacion = null;
//			String fechaHora=fecha+" "+hora;
			try {
				connection = getConnection();
				stmt = connection.prepareStatement(
				"insert  into reservas (id_sala,tiempo,mail,departamento,comentario) values(?,?,?,?,?)");
				stmt.setInt(1,reserBean.getId_sala());
				if (reserBean.getTiempo() != null) {
//					System.out.println("time"+(reserBean.getTiempo()).getTime());
//					System.out.println("day"+(reserBean.getTiempo()).getDay());
//					System.out.println("hour"+(reserBean.getTiempo()).getHours());
//					System.out.println("min"+(reserBean.getTiempo()).getMinutes());
					stmt.setTimestamp(2,
							new Timestamp((reserBean.getTiempo()).getTime()));
				}
				else {
					stmt.setDate(2, null);
				}				
			
				stmt.setString(3,reserBean.getMail());
				stmt.setString(4, reserBean.getDepartamento());
				stmt.setString(5, reserBean.getComentario());
			
				if (stmt.executeUpdate() == 0) {
				throw new SQLException("Error al insertar los datos en tabla ");
			}
			
			}
			finally {
				try {
					connection.close();
				}
				catch (SQLException sqle) {
					throw new SQLException(sqle.getMessage());
				}
			}
			
		}
		
	public Reservacion selectByPrimaryKey(int key) throws java.sql.
    SQLException {
  PreparedStatement stmt = null;
  ResultSet res = null;
  Reservacion reservacion = null;

  try {
    connection = getConnection();
    stmt = connection.prepareStatement(
        "select * from reservas where id_reserva = ? ");
    stmt.setInt(1, key);

    res = stmt.executeQuery();

    if (res.next()) {
      reservacion = new Reservacion();
      reservacion.setId_reservas(res.getInt("id_reserva"));
      reservacion.setId_sala(res.getInt("id_sala"));
      reservacion.setTiempo(res.getTimestamp("tiempo"));
      reservacion.setMail(res.getString("mail"));
      reservacion.setDepartamento(res.getString("departamento"));
      reservacion.setComentario(res.getString("comentario"));
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
  return reservacion;
}
	/**
	 * Elimina todas las reservaciones que correspondientes al id de la sala indicada
	 * 
	 * @author Alex Villagran
	 * @param obj
	 * @throws SQLException
	 */
	public void deleteByIdSala(int idSala) throws SQLException {
		PreparedStatement stmt = null;
	    

	   
	    
	   

	    try {
	      connection = getConnection(); 
	      stmt = connection.prepareStatement(
	          "delete from reservas where id_sala = ?");

	      stmt.setInt(1, idSala);
         
          
	      if (stmt.executeUpdate() == 0) {
	       /* throw new SQLException("Error al borrar de las reservaciones de la sala " +
	                               idSala);*/
	      }
	    }
	    finally {
	      try {
	        stmt.close();
	        connection.close(); 
	      }
	      catch (SQLException sqle) {
	        throw new SQLException(sqle.getMessage());
	      }
	    }
	}
	
	
	public ArrayList findResFecha(String fechaIni, String fechaFin, int sala) throws java.sql.SQLException {
		PreparedStatement stmt = null;
		ResultSet res = null;
		Reservacion reservacion = null;
		ArrayList reservaciones=new ArrayList();
//		System.out.println("fechaIniDB:" + fechaIni);
//		System.out.println("fechaFinDB:" + fechaFin);
		
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(
			"select * from reservas where tiempo between ? and ? and id_sala=? ");
			
			stmt.setString(1,fechaIni);
			stmt.setString(2,fechaFin);
			stmt.setInt(3, sala);
			res = stmt.executeQuery();
			
			while (res.next()) {
				reservacion = new Reservacion();
				reservacion.setId_reservas(res.getInt("id_reserva"));
				reservacion.setId_sala(res.getInt("id_sala"));
				reservacion.setTiempo(res.getTimestamp("tiempo"));
				reservacion.setMail(res.getString("mail"));
				reservacion.setDepartamento(res.getString("departamento"));
				reservacion.setComentario(res.getString("comentario"));
				reservaciones.add(reservacion);
			}
//			System.out.println("tamanioDB:" + reservaciones.size());
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
		return reservaciones;
	}
	
	
	/**
	 * Busca reservaciones hechas por semana
	 * @param fecha
	 * @param hora
	 * @return
	 * @throws SQLException
	 */
		public ArrayList WeekReservation(int id_sala,String fechaIni,String horaIni,String fechaFin,String horaFin) throws
		SQLException {
			PreparedStatement stmt = null;
			ResultSet res = null;
			Reservacion reservacion = null;
			String fechaHora1=fechaIni+" "+horaIni;
			String fechaHora2=fechaFin+" "+horaFin;
			

			
			ArrayList datos = new ArrayList();
			try {
				connection = getConnection();
				stmt = connection.prepareStatement(
				"select * from reservas where tiempo >= ? and tiempo <= ? and id_sala=? " +
				"order by tiempo ");
				stmt.setString(1,fechaHora1);
				stmt.setString(2,fechaHora2);
				stmt.setInt(3,id_sala);
				
			
				res = stmt.executeQuery();
				
				while(res.next()) {			
							
					reservacion = new Reservacion();
					reservacion.setId_reservas(res.getInt("id_reserva"));
					reservacion.setId_sala(res.getInt("id_sala"));
					reservacion.setTiempo(res.getTimestamp("tiempo"));
					reservacion.setMail(res.getString("mail"));
					reservacion.setDepartamento(res.getString("departamento"));
					reservacion.setComentario(res.getString("comentario"));
					datos.add(reservacion);
					
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

			return datos;
		}
		
}


