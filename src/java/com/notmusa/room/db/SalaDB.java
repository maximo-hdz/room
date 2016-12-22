package com.notmusa.room.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.notmusa.room.db.dbutils.PoolDB;
/**
 *
 * @author avillagran
 *
 */
public class SalaDB extends PoolDB {
	protected static SalaDB instance;
	private Connection connection = null;

	private SalaDB() throws SQLException {
		super();

	}
	public static SalaDB getInstance() throws ClassNotFoundException,
	SQLException {
		instance = new SalaDB();
		return instance;
	}

	public ArrayList selectAll() throws java.sql.SQLException {
		PreparedStatement stmt = null;
		ResultSet res = null;
		Sala sala = null;
		ArrayList salas=new ArrayList();
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(
			"select * from salas order by id_sala");


			res = stmt.executeQuery();

			while (res.next()) {
				sala = new Sala();
				sala.setIdSala(res.getInt("id_sala"));
				sala.setNombre_sala(res.getString("nombre_sala"));
				salas.add(sala);
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
		return salas;
	}

	public void delete(Object obj) throws SQLException {
		PreparedStatement stmt = null;
	    Sala menuClass = null;


	      menuClass = (Sala) obj;


	    try {
	      connection = getConnection();
	      stmt = connection.prepareStatement(
	          "delete from salas where id_sala = ?");

	      stmt.setInt(1, menuClass.getIdSala());

	      if (stmt.executeUpdate() == 0) {
	        throw new SQLException("Error al borrar de la tabla salas id " +
	                               menuClass.getIdSala());
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



	public void update(Object obj) throws SQLException {
		 PreparedStatement stmt = null;
		    Sala sala = (Sala) obj;



		    try {
		      connection = getConnection();
		      stmt = connection.prepareStatement(
		          "update salas set  nombre_sala = ? where id_sala = ?");

		      stmt.setString(1, sala.getNombre_sala());
		      stmt.setInt(2, sala.getIdSala());


		      if (stmt.executeUpdate() == 0) {
		        throw new SQLException(
		            "Error al actualizar los datos en la tabla sala" +
		            sala.getIdSala());
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
	public void insert(Object obj) throws SQLException {
		PreparedStatement stmt = null;
		ResultSet res = null;
		Sala sala=(Sala)obj;

		try {
			connection = getConnection();
			stmt = connection.prepareStatement(
			"insert into salas (nombre_sala) values(?)");
			stmt.setString(1, sala.getNombre_sala());


			if (stmt.executeUpdate() == 0) {
				throw new SQLException("Error al insertar los datos en tabla adm_user");
			}
			res = stmt.getGeneratedKeys();

			if (res.next()) {
				sala.setIdSala(res.getInt(1));
			}
			else {
				throw new SQLException(
				"Error al generar llave primaria para op_plazas");
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
 public Sala selectByPrimaryKey(int key) throws java.sql.
    SQLException {
  PreparedStatement stmt = null;
  ResultSet res = null;
  Sala opEstados = null;

  try {
    connection = getConnection();
    stmt = connection.prepareStatement(
        "select * from salas where id_sala = ? ");
    stmt.setInt(1, key);

    res = stmt.executeQuery();

    if (res.next()) {
      opEstados = new Sala();
      opEstados.setIdSala(res.getInt("id_sala"));
      opEstados.setNombre_sala(res.getString("nombre_sala"));
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
  return opEstados;
}

public ArrayList selectAllByES(int idSala) throws java.sql.SQLException {
        PreparedStatement stmt = null;
        ResultSet res = null;
        Sala sala = null;
        ArrayList salas=new ArrayList();
        try {
                connection = getConnection();
                stmt = connection.prepareStatement(
                "select * from salas where id_sala = ?"+
                " order by id_sala");
                      stmt.setInt(1,idSala);

                res = stmt.executeQuery();

                while (res.next()) {
                        sala = new Sala();
                        sala.setIdSala(res.getInt("id_sala"));
                        sala.setNombre_sala(res.getString("nombre_sala"));
                        salas.add(sala);
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
        return salas;
}


}
