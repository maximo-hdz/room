package com.notmusa.room.db.dbutils;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

/**
 * <p>Title: Anuncios</p>
 * <p>Description: Clase base encargada de realizar la conexion a la base de
 * datos a traves de un pool de conexiones. Además define tres metodos
 * abstractos.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Diario Deportivo Record</p>
 * @author Lizeth Cosio
 * @version 1.0
 */

public abstract class PoolDB {

  /** Datasource para obtener las conexiones a la base de datos */
  private DataSource ds;

  /**
   * Constructor encargado de obtener la referencia al data source
   * para habilitar el poleo de conexiones
   * @throws SQLException
   */
  public PoolDB() throws SQLException {

    Context ctx = null;

    try {
      ctx = new InitialContext();
    }
    catch (NamingException ne) {
      throw new SQLException("Sin contexto inicial " + ne.getExplanation());
    }

    try {
      ds = (DataSource) ctx.lookup(NameFactory.AnunciosJDBC);
    }
    catch (NamingException ne) {
      throw new SQLException("Error al obtener el data source " +
                             NameFactory.AnunciosJDBC + " " +
                             ne.getExplanation());
    }
  }

  /**
   * Obtiene una conexion del pool
   * @return la conexion asignada
   * @throws SQLException
   */
  public Connection getConnection() throws SQLException {
    if (ds == null) {
      throw new NullPointerException("El data source es nulo");
    }
    Connection con = ds.getConnection();

    return con;
  }

  /**
   * Borra un objeto de la base de datos
   * @param obj Objeto
   * @throws java.sql.SQLException
   */
  public abstract void delete(Object obj) throws java.sql.SQLException;

  /**
   * Inserta un objeto en la base de datos
   * @param obj Objeto
   * @throws java.sql.SQLException
   */
  public abstract void insert(Object obj) throws java.sql.SQLException;

  /**
   * Actualiza un registro
   * @param obj Objeto
   * @throws java.sql.SQLException
   */
  public abstract void update(Object obj) throws java.sql.SQLException;
}