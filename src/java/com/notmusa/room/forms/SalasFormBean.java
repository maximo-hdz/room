package com.notmusa.room.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

import com.notmusa.room.db.Sala;
import com.notmusa.room.db.SalaDB;

public class SalasFormBean extends ActionForm {
	private Vector salas;
	public SalasFormBean() {
		salas=getSalasDB();
	}
	public Vector getSalas() {
		return salas;
	}

	public void setSalas(Vector salas) {
		this.salas = salas;
	}
	private Vector getSalasDB(){
		
		ArrayList salasAll=null;
		try {
			SalaDB salaDB=SalaDB.getInstance();
			salasAll=salaDB.selectAll();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Vector salas=new Vector();
		if(salasAll!=null){
			Iterator iterator=salasAll.iterator();
			while (iterator.hasNext()) {
				Sala sala = (Sala) iterator.next();
				salas.add(new LabelValueBean(sala.getNombre_sala(),String.valueOf(sala.getIdSala())));			
			}
			
		}
		return salas;		
	}

}
