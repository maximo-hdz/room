package com.notmusa.room.actions;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

import com.notmusa.room.db.Reservacion;
import com.notmusa.room.db.ReservacionDB;
import com.notmusa.room.forms.ResPermFormBean;
import com.notmusa.room.forms.ReservacionFormBean;
import com.notmusa.room.utils.Dia;
import com.notmusa.room.utils.MatrizHorasDias;
import com.sun.rsasign.c;

public class ResPermanentesAction extends DispatchAction {
	


	
	  public ActionForward ReservPermanente(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws SQLException, ParseException{

		  
			ResPermFormBean resPermFormBean=(ResPermFormBean)request.getSession(false).getAttribute("ResPermFormBean");
		  
		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		  SimpleDateFormat sdf2=new SimpleDateFormat("yyyy-MM-dd");
		  SimpleDateFormat sdf3=new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date fecha; 		
		    long cons = 1800000l;
		    int totalHora;
		    int hora=8;
		    ArrayList reservas=null;
		    ReservacionDB reservacionDB=ReservacionDB.getInstance();
			java.util.Date inicio;
			java.util.Date fin;			
			
			int metodo = 1;
			int sala = 30;
			long fechaBase;
		    long fechaCalculada;
		    long dia = 86400000l;
		    Date ini = sdf3.parse(resPermFormBean.getFecha());
		    Date fin_f =sdf3.parse(resPermFormBean.getFecha_fin());

		    int cont = 1;		 
		    
		    
			inicio = sdf.parse(sdf2.format(ini) + " " +resPermFormBean.getHora());
			fin = sdf.parse(sdf2.format(fin_f) +  " 23:59.9");
			fechaBase = inicio.getTime();
			fechaCalculada = fechaBase;

		  
		    Calendar calendar=Calendar.getInstance(new Locale("en","US"));
		    calendar.setTime(new Date(inicio.getTime()));
		  
		    for (int j=0;;j++){
		    	
		    	
		    	if(calendar.getTimeInMillis()<=fin.getTime()){
		    	
		    	 
		   
//				System.out.println("Fin:" + calendar.getTimeInMillis());
				int d = calendar.get(Calendar.DAY_OF_WEEK);
//				System.out.println("Dia:" + d);	
			if(d == resPermFormBean.getDia()){
			for(int i =0; i < Integer.parseInt(resPermFormBean.getTotal()); i++){

			try {
			
		    if(hora > 7 && hora != 0 && hora <= 23){
			Reservacion reservacionBean = new Reservacion();
			reservacionBean.setComentario(resPermFormBean.getComentario());
			reservacionBean.setDepartamento(resPermFormBean.getDepartamento());
			reservacionBean.setId_sala(resPermFormBean.getSalaSelect());
			reservacionBean.setMail(resPermFormBean.getMail());
			reservacionBean.setTiempo(new Date(calendar.getTimeInMillis()));
//			System.out.println("FcehaaaBean:" + reservacionBean.getTiempo().getHours());
			
			reservacionDB.insertReservacion(reservacionBean);		
//		    fechaCalculada = fechaCalculada + cons;
			calendar.add(Calendar.MINUTE,30);
		
		    }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
			calendar.add(Calendar.MINUTE,-30*Integer.parseInt(resPermFormBean.getTotal()));
		}
						 
			calendar.add(Calendar.DATE,1);
	  	  
		
	  }else{
		  break;
	  }
	  }
		    resPermFormBean.inicializa();
			return mapping.findForward("refresh");
	}	    
	  


	

		
		public ActionForward mostrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			ResPermFormBean formBean=(ResPermFormBean)form;
			
			
			//formBean.inicializaHeaderDias(Calendar.getInstance());
			formBean.setHorasDias(new MatrizHorasDias(formBean.getDias(),formBean.getSalaSelect()));		
			return mapping.findForward("refresh");
		}


		
		protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse arg3) throws Exception {
			
			request.getSession(false).removeAttribute("ReservacionFormBean");
			
			return mapping.findForward("refresh");
			
		}

}
