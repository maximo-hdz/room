package com.notmusa.room.utils;

import com.notmusa.room.db.Reservacion;


/**
 * 
 * @author avillagran
 *
 */
public class HoraDia {
	private boolean busyFirst30;
	private boolean busyLast30;
	private int idFirst30;
	private int idLast30;
	private boolean dateOldFirst30;
	private boolean dateOldLast30;
    private String fechaCompletaFirst30;
    private String fechaCompletaLast30;
	private Reservacion reservacionFirst30;
	private Reservacion reservacionLast30;
	private String colorFirst30;
	private String colorLast30;
	
	public boolean isBusyFirst30() {
		return busyFirst30;
	}
	public void setBusyFirst30(boolean busyFirst30) {
		this.busyFirst30 = busyFirst30;
	}
	public boolean isBusyLast30() {
		return busyLast30;
	}
	public void setBusyLast30(boolean busyLast30) {
		this.busyLast30 = busyLast30;
	}
	public int getIdLast30() {
		return idLast30;
	}
	public void setIdLast30(int idLast30) {
		this.idLast30 = idLast30;
	}
	public int getIdFirst30() {
		return idFirst30;
	}
	public void setIdFirst30(int idFirst30) {
		this.idFirst30 = idFirst30;
	}
	public Reservacion getReservacionLast30() {
		return reservacionLast30;
	}
	public void setReservacionLast30(Reservacion reservacionLast30) {
		this.reservacionLast30 = reservacionLast30;
	}
	public Reservacion getReservacionFirst30() {
		return reservacionFirst30;
	}
	public void setReservacionFirst30(Reservacion reservacionFirst30) {
		this.reservacionFirst30 = reservacionFirst30;
	}
	
	public boolean isDateOldFirst30() {
		return dateOldFirst30;
	}
	public void setDateOldFirst30(boolean isDateOld) {
		this.dateOldFirst30 = isDateOld;
	}
	public boolean getDateOldLast30() {
		return dateOldLast30;
	}
	public void setDateOldLast30(boolean isDateOldLast30) {
		this.dateOldLast30 = isDateOldLast30;
	}
	public String getFechaCompletaFirst30() {
		return fechaCompletaFirst30;
	}
	public void setFechaCompletaFirst30(String fechaCompleta) {
		this.fechaCompletaFirst30 = fechaCompleta;
	}
	public String getFechaCompletaLast30() {
		return fechaCompletaLast30;
	}
	public void setFechaCompletaLast30(String fechaCompletaLast30) {
		this.fechaCompletaLast30 = fechaCompletaLast30;
	}
	public String getColorFirst30() {
		return colorFirst30;
	}
	public void setColorFirst30(String color) {
		this.colorFirst30 = color;
	}
	public boolean equalsFirst30(HoraDia horaDia){
		if(this.reservacionFirst30.getMail().trim().equals(horaDia.getReservacionFirst30().getMail().trim())
				){
			return true;
		}else{
			return false;
		}
	}
	public boolean equalsFirst30Last30(HoraDia horaDia){
		if(this.reservacionFirst30.getMail().trim().equals(horaDia.getReservacionLast30().getMail().trim())
				){
			return true;
		}else{
			return false;
		}
	}
	public boolean equalsLast30(HoraDia horaDia){
		if(this.reservacionLast30.getMail().trim().equals(horaDia.getReservacionLast30().getMail().trim())
				){
			return true;
		}else{
			return false;
		}
	}
	public boolean equalsLast30First30(HoraDia horaDia){
		if(this.reservacionLast30.getMail().trim().equals(horaDia.getReservacionFirst30().getMail().trim())
				){
			return true;
		}else{
			return false;
		}
	}
	public String getColorLast30() {
		return colorLast30;
	}
	public void setColorLast30(String colorLast30) {
		this.colorLast30 = colorLast30;
	}

	 
	}
