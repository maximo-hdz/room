package com.notmusa.room.db;

public class Sala {
	private int idSala;
	private String nombre_sala;
	public int getIdSala() {
		return idSala;
	}
	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	public String getNombre_sala() {
		return nombre_sala;
	}
	public void setNombre_sala(String nombre_sala) {
		this.nombre_sala = nombre_sala;
	}
	
	public String toString() {		
		return nombre_sala;
	}

}
