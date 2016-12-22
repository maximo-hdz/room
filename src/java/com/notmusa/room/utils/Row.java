package com.notmusa.room.utils;

import java.util.ArrayList;
/**
 * 
 * @author avillagran
 *
 */
public class Row{
	private String hora;
	private ArrayList renglon;
	
	public Row() {
		renglon=new ArrayList();
		hora="";
	}
	void add(Object object) {
		renglon.add(object);
	}
	public ArrayList getRenglon() {
		return renglon;
	}
	public void setRenglon(ArrayList renglon) {
		this.renglon = renglon;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public HoraDia get(int i){
		return (HoraDia)renglon.get(i);
	}
}
