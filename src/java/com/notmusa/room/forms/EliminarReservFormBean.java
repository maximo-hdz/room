package com.notmusa.room.forms;

import org.apache.struts.action.ActionForm;

import com.notmusa.room.db.Reservacion;
import com.notmusa.room.db.Sala;
/**
 * 
 * @author avillagran
 *
 */
public class EliminarReservFormBean extends ActionForm {
	private Sala sala;
	private Reservacion reservacion;
	
	
	public Reservacion getReservacion() {
		return reservacion;
	}
	public void setReservacion(Reservacion reservacion) {
	
		this.reservacion = reservacion;
	}
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
	
	
}
