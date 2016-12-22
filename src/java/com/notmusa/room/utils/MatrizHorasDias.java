package com.notmusa.room.utils;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import com.notmusa.room.db.Reservacion;
import com.notmusa.room.db.ReservacionDB;

/**
 * Representa la marix de horas y dias de las reservaciones
 * @author avillagran
 *
 */
public class MatrizHorasDias extends ArrayList{	
	ArrayList dias;
	Row renglon; 
	String horas []={"08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
	String colores []={"#153e7e","RED", "BLACK","#f87431","#993300","#FF33FF", "#CC0099", "#9966CC", "#330033", "#330033","#660033", "#FF0000"};
	int indiceColor=0;
	SimpleDateFormat dateFormatDB=new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat dateFormatDB2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss" );
	Row rowAnterior=null;
	HoraDia horaAnterior;
	int cont = 0;
	ArrayList datos = null;
	public MatrizHorasDias(ArrayList dias,int id_sala) throws SQLException {
		ReservacionDB reservacionDB;
		reservacionDB = ReservacionDB.getInstance();
		Dia dia01= (Dia) dias.get(0);
		Dia dia02= (Dia) dias.get(6);
		int contador=1;
		datos = reservacionDB.WeekReservation(id_sala,dateFormatDB.format(dia01.getFechaDate()),horas[0]+":"+"00:"+"00", dateFormatDB.format(dia02.getFechaDate()),horas[15]+":"+"30:"+"00");

		for (int i = 0; i < horas.length; i++) {
			
			renglon=new Row();
			renglon.setHora(horas[i]+":"+"00");
			
			Iterator iterator=dias.iterator();
			int numDia=0;
			while (iterator.hasNext()) {
				
				Iterator iterator2 = datos.iterator();
				Reservacion reservacion = null;
				Dia dia = (Dia) iterator.next();
				HoraDia horaDia=new HoraDia();
				
				while(iterator2.hasNext())
				{
				    Reservacion reservacion2 = null;

				reservacion2=(Reservacion) iterator2.next();

				if(reservacion2.getTiempo().toString().equals(dateFormatDB.format(dia.getFechaDate()).toString()+" "+horas[i]+":"+"00:"+"00.0") && (id_sala==reservacion2.getId_sala())){
					reservacion=reservacion2;
					break;
				}
				
				}

				if(reservacion!=null){						
					horaDia.setBusyFirst30(true);
					horaDia.setIdFirst30(reservacion.getId_reservas());
					horaDia.setReservacionFirst30(reservacion);										
				}else{
					horaDia.setFechaCompletaFirst30(dateFormatDB.format(dia.getFechaDate())+" "+horas[i]+":"+"00");
					
				}
				
				

				Calendar calendar=Calendar.getInstance();
				calendar.setTime(dia.getFechaDate());//fijamos la fecha
				calendar.set(Calendar.HOUR_OF_DAY,Integer.parseInt(horas[i]));
				calendar.set(Calendar.MINUTE,0);
				if(calendar.before(Calendar.getInstance())){
					horaDia.setDateOldFirst30(true);
				}
				
				
				

				reservacion=null;
				iterator2 = datos.iterator();
				while(iterator2.hasNext())
				{
				    Reservacion reservacion2 = null;
				reservacion2=(Reservacion) iterator2.next();
				 if(reservacion2.getTiempo().toString().equals(dateFormatDB.format(dia.getFechaDate()).toString()+" "+horas[i]+":"+"30:"+"00.0")&& (id_sala==reservacion2.getId_sala())){
					reservacion=reservacion2;
					break;
				}
				}
				
				
				
				
				if(reservacion!=null){						
					horaDia.setBusyLast30(true);
					horaDia.setIdLast30(reservacion.getId_reservas());
					horaDia.setReservacionLast30(reservacion);					
				}else{
					horaDia.setFechaCompletaLast30((dateFormatDB.format(dia.getFechaDate())+" "+horas[i]+":"+"30"));
				}
				
				/**************************/
				
				
				
				
				//Asignamos el color correspndiente a la primera media hora
				
				
				if(horaDia.getReservacionFirst30()!=null) {
					
					if(this.size()>=1){//Verificamos que no se trate del primer renglon			
						
						rowAnterior=(Row)(this.get(this.size()-1));
						horaAnterior=(HoraDia)rowAnterior.get(numDia);
						//Comapramos los primeros 30 minutos con los ultimos 30 minutos de la hora anterior							
						if(horaAnterior.getReservacionLast30()!=null){
							if(horaDia.equalsFirst30Last30(horaAnterior)){
								horaDia.setColorFirst30(horaAnterior.getColorLast30());
								
							}else{
								horaDia.setColorFirst30(colores[(indiceColor++)%colores.length]);
								
							}
						}else{//si no hay reservacion de los ultimos 30 minutos de la hora anterior								
							horaDia.setColorFirst30(colores[(indiceColor++)%colores.length]);
//								System.out.println("Nuevo color>>>"+horaDia.getReservacionFirst30().getMail());
						}
					}else if(this.size()==0){//Solo para el primer renglon
						 
//							if(cont != 0){
//							   horaDia.setColorFirst30(colores[(indiceColor)%colores.length]);
//							   cont =0;
//							}else{
							horaDia.setColorFirst30(colores[(indiceColor++)%colores.length]);
//								cont ++;
//							}

						//comparamos los ultimios 30 minutos con los primeros 30
						if(horaDia.getReservacionFirst30()!=null && horaDia.getReservacionLast30()!=null) {
							if(horaDia.equalsLast30First30(horaDia)){
								horaDia.setColorLast30(horaDia.getColorFirst30());
								
							}else{
								horaDia.setColorLast30(colores[(indiceColor)%colores.length]);
							}
						}else if (horaDia.getReservacionFirst30()==null && horaDia.getReservacionLast30()!=null){
							horaDia.setColorLast30(colores[(indiceColor++)%colores.length]);
						}
					}

				}
				
				
				//comparamos los ultimios 30 minutos con los primeros 30
				
				if(horaDia.getReservacionFirst30()!=null && horaDia.getReservacionLast30()!=null) {
					if(horaDia.equalsLast30First30(horaDia)){
						horaDia.setColorLast30(horaDia.getColorFirst30());
						
					}else{
						horaDia.setColorLast30(colores[(indiceColor++)%colores.length]);
					}
				}else if (horaDia.getReservacionFirst30()==null && horaDia.getReservacionLast30()!=null){
					horaDia.setColorLast30(colores[(indiceColor++)%colores.length]);
				}
				
				
				
				
				
				calendar.set(Calendar.MINUTE,30);
				if(calendar.before(Calendar.getInstance())){
					horaDia.setDateOldLast30(true);
				}
				renglon.add(horaDia);
				numDia++;
				
//			}//while for iterartor2
				
			}
			add(renglon);
			
		}
	}
	public Row getRenglon() {
		return renglon;
	}
	public void setRenglon(Row renglon) {
		this.renglon = renglon;
	}
	
}
