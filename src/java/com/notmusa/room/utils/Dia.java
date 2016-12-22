package com.notmusa.room.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa un dia de la semana con sus respectivas horas reservadas
 * @author avillagran
 *
 */
public class Dia {
	
	private Date fechaDate;
	private String nameDia;
	
	private SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
	
	public Dia(String nameDia,Date date) {
		
		fechaDate=date;
		this.nameDia=nameDia;
	}
	
	public Date getFechaDate() {
		return fechaDate;
	}
	public void setFechaDate(Date fecha) {
		this.fechaDate = fecha;
	}
	public String getFecha(){
		return dateFormat.format(fechaDate);
	}

	public String getNameDia() {
		return nameDia;
	}

	public void setNameDia(String nameDia) {
		this.nameDia = nameDia;
	}
}
