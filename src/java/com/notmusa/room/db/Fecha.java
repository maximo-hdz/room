package com.notmusa.room.db;




public class Fecha {
	
	private String nombre = null;
	private String fecha = null;
	private String hora = null;
	private boolean fechaReservada;
	private boolean First30;;
	
	

	

	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isFechaReservada() {
		return fechaReservada;
	}
	public void setFechaReservada(boolean fechaReservada) {
		this.fechaReservada = fechaReservada;
	}
	public boolean isFirst30() {
		return First30;
	}
	public void setFirst30(boolean first30) {
		First30 = first30;
	}
	

}
