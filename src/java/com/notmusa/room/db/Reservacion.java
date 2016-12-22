package com.notmusa.room.db;

import java.util.Date;

public class Reservacion {
	 
	private int id_reservas=0;
	private int id_sala=0;
	private Date tiempo = null;
	private String mail=null;
	private String comentario = null;
	private String departamento = null;
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public Date getTiempo() {
		return tiempo;
	}
	public void setTiempo(Date fecha) {
		this.tiempo = fecha;
	}
	public int getId_reservas() {
		return id_reservas;
	}
	public void setId_reservas(int id_reservas) {
		this.id_reservas = id_reservas;
	}
	public int getId_sala() {
		return id_sala;
	}
	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
		
		
	
}
