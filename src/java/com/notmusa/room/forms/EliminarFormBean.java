package com.notmusa.room.forms;

import org.apache.struts.action.ActionForm;

public class EliminarFormBean extends ActionForm {
	private int idSala;
	private String sala;

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public String getSala() {
		return sala;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

}
