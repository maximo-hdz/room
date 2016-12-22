package com.notmusa.room.forms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

import com.notmusa.room.db.Sala;
import com.notmusa.room.db.SalaDB;
import com.notmusa.room.utils.Dia;
import com.notmusa.room.utils.MatrizHorasDias;
/**
 * 
 * @author avillagran
 *
 */
public class AdminFormBean extends ActionForm {
	private String operacion;
	private Vector salas;
	private int salaSelect;
	
	private ArrayList dias;
	private ArrayList horasDias;
	private String fecha;
	
	 
	//private boolean l
	public AdminFormBean() throws SQLException {
		inicializaSalasDB();
		if(!salas.isEmpty()){
			salaSelect=Integer.parseInt(((LabelValueBean)salas.get(0)).getValue());
		}else{
			salaSelect=0;
		}
		inicializaHeaderDias(Calendar.getInstance(new Locale("en","US")));
		horasDias=new MatrizHorasDias(dias,salaSelect);
		
	}
	
	public String getOperacion() {
		return operacion;
	}
	
	public Vector getSalas() {
		return salas;
	}
	
	public void setSalas(Vector salas) {
		this.salas = salas;
	}
	
	public void inicializaSalasDB(){
		
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
		this.salas=salas;		
	}
	
	public int getSalaSelect() {
		return salaSelect;
	}
	
	public void setSalaSelect(int salaSelect) {
		this.salaSelect = salaSelect;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}	
	public void inicializaHeaderDias(Calendar calendar){
		dias=new ArrayList();
		
		Dia dia;
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);		
		dia=new Dia("Domingo",calendar.getTime());
		dias.add(dia);
		
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		dia=new Dia("Lunes",calendar.getTime());
		dias.add(dia);
		
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
		dia=new Dia("Martes",calendar.getTime());
		dias.add(dia);
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
		dia=new Dia("Miercoles",calendar.getTime());
		dias.add(dia);
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
		dia=new Dia("Jueves",calendar.getTime());
		dias.add(dia);
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
		dia=new Dia("Viernes",calendar.getTime());
		dias.add(dia);

		calendar.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
		dia=new Dia("Sabado",calendar.getTime());		
		dias.add(dia);
		
	}
    
	public ArrayList getDias() {
		return dias;
	}

	public void setDias(ArrayList dias) {
		this.dias = dias;
	}

	public ArrayList getHorasDias() {
		return horasDias;
	}

	public void setHorasDias(ArrayList horasDias) {
		this.horasDias = horasDias;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
}
