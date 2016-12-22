package com.notmusa.room.actions;

import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

import com.notmusa.room.db.Reservacion;
import com.notmusa.room.db.ReservacionDB;
import com.notmusa.room.db.Sala;
import com.notmusa.room.db.SalaDB;
import com.notmusa.room.forms.AdminFormBean;
import com.notmusa.room.forms.EliminarReservFormBean;
import com.notmusa.room.forms.LoginFormBean;
import com.notmusa.room.forms.ReservacionFormBean;
import com.notmusa.room.utils.Dia;
import com.notmusa.room.utils.MatrizHorasDias;




public class ReservacionAction extends DispatchAction {
	
	public ActionForward salvar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("salvar");
	}


	public ActionForward salir(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		    ReservacionFormBean resFormBean=(ReservacionFormBean)request.getSession(false).getAttribute("ReservacionFormBean");	   
		    resFormBean.setHorasDias(new MatrizHorasDias(resFormBean.getDias(),resFormBean.getSalaSelect()));
		    resFormBean.inicializaSalasDB();
		    resFormBean.inicializa();
		return mapping.findForward("salir");
	}

	
	public ActionForward mostrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReservacionFormBean formBean=(ReservacionFormBean)form;
		
		
		//formBean.inicializaHeaderDias(Calendar.getInstance());
		formBean.setHorasDias(new MatrizHorasDias(formBean.getDias(),formBean.getSalaSelect()));		
		return mapping.findForward("refresh");
	}

	public ActionForward next(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReservacionFormBean bean=(ReservacionFormBean)form;
//		System.out.println("Sala"+bean.getSalaSelect());
		Dia lunes=(Dia)bean.getDias().get(0);
		Calendar calendar=Calendar.getInstance(new Locale("en","US"));
		calendar.setTime(new Date(lunes.getFechaDate().getTime()+(7*24*60*60*1000)));
		bean.inicializaHeaderDias(calendar);
		bean.setHorasDias(new MatrizHorasDias(bean.getDias(),bean.getSalaSelect()));
		bean.inicializa();
		return mapping.findForward("refresh");
	}
	
	public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ReservacionFormBean bean=(ReservacionFormBean)form;
		Dia lunes=(Dia)bean.getDias().get(0);
		Calendar calendar=Calendar.getInstance(new Locale("en","US"));
		
//		System.out.println("Sala"+bean.getSalaSelect());
		calendar.setTime(new Date(lunes.getFechaDate().getTime()-(7*24*60*60*1000)));
		bean.inicializaHeaderDias(calendar);
		bean.setHorasDias(new MatrizHorasDias(bean.getDias(),bean.getSalaSelect()));
		bean.inicializa();
		return mapping.findForward("refresh");
	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		
		request.getSession(false).removeAttribute("ReservacionFormBean");
		
		return mapping.findForward("refresh");
		
	}
	

public ActionForward updateByFecha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ReservacionFormBean bean=(ReservacionFormBean)form;
		if(!bean.getFecha().equals("")){
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
			Calendar calendar=Calendar.getInstance(new Locale("en","US"));
			calendar.setTime(dateFormat.parse(bean.getFecha()));
			ArrayList dias=new ArrayList();
			
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
			bean.setDias(dias);
			bean.setHorasDias(new MatrizHorasDias(dias,bean.getSalaSelect()));
			
		}
		
		 
		return mapping.findForward("refresh");
	}
	
public ActionForward guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


	
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	java.util.Date fecha; 
	ReservacionFormBean reservacionFormBean=(ReservacionFormBean)request.getSession(false).getAttribute("ReservacionFormBean");
    Long cons = new Long(1800000);
    int totalHora;
    int hora=8;
    ArrayList reservas=null;
    totalHora = Integer.parseInt(reservacionFormBean.getTotal());
    ReservacionDB reservacionDB=ReservacionDB.getInstance();
    Long fI = new Long(sdf.parse(reservacionFormBean.getOption()).getTime());
    Long bus = new Long(sdf.parse(reservacionFormBean.getOption()).getTime()+(1800000*(totalHora-1)));


	reservas = reservacionDB.findResFecha(reservacionFormBean.getOption(), sdf.format(new Date(bus.longValue())), reservacionFormBean.getSalaSelect());
	
	if(reservas ==null || reservas.size()!=0){
		ActionErrors errores = new ActionErrors();
		errores.add("reservas", new ActionMessage("errors.reservas"));
		addErrors(request,errores);
//		reservacionFormBean.inicializa();
		return mapping.findForward("refresh");		
		
	}else{
    
	for(int i =0; i < totalHora; i++){
		hora = new Date(fI.longValue()).getHours();
	try {
	
    if(hora > 7 && hora != 0 && hora <= 23){
	Reservacion reservacionBean = new Reservacion();
	reservacionBean.setComentario(reservacionFormBean.getComentario());
	reservacionBean.setDepartamento(reservacionFormBean.getDepartamento());
	reservacionBean.setId_sala(reservacionFormBean.getSalaSelect());
	reservacionBean.setMail(reservacionFormBean.getMail());
	reservacionBean.setTiempo(new Date(fI.longValue()));
//	System.out.println("FcehaaaBean:" + reservacionBean.getTiempo().getHours());
	
	reservacionDB.insertReservacion(reservacionBean);		
	fI = new Long(fI.longValue()+ cons.longValue());
    }
	
} catch (SQLException e) {
	e.printStackTrace();
}
}
	}
    ReservacionFormBean resFormBean=(ReservacionFormBean)request.getSession(false).getAttribute("ReservacionFormBean");	   
    resFormBean.setHorasDias(new MatrizHorasDias(resFormBean.getDias(),resFormBean.getSalaSelect()));
    resFormBean.inicializaSalasDB();
    resFormBean.inicializa();
	return mapping.findForward("success");
}
public ActionForward No(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
	
	return mapping.findForward("success");
}
	
}
