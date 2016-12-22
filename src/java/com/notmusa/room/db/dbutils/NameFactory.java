package com.notmusa.room.db.dbutils;

/**
 * <p>Title: Anuncios</p>
 * <p>Description: Interface desde la cual se puede obtener la referencia a
 * variables en todas las clases.</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Diario Deportivo Record</p>
 * @author Lizeth Cosio
 * @version 1.0
 */

public interface NameFactory {
  public static final String AnunciosJDBC = "java:comp/env/jdbc/roomDB";
}
