package com.notmusa.room.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.notmusa.room.db.Reservacion;
import com.notmusa.room.db.ReservacionDB;
import com.notmusa.room.db.Sala;
import com.notmusa.room.db.SalaDB;
import com.notmusa.room.forms.AdminFormBean;
import com.notmusa.room.forms.EliminarReservFormBean;
import com.notmusa.room.utils.Dia;
import com.notmusa.room.utils.MatrizHorasDias;
/**
 * 
 * @author avillagran
 *
 */
public class AdminAction extends DispatchAction {
	
	public ActionForward salas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session.getAttribute("userlogin")==null){
			return mapping.findForward("prohibido");
		}else{
			
			return mapping.findForward("rooms");
		}
		
		
	}
	public ActionForward mostrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session.getAttribute("userlogin")==null){
			return mapping.findForward("prohibido");
		}else{
			AdminFormBean formBean=(AdminFormBean)form;
			
			formBean.setHorasDias(new MatrizHorasDias(formBean.getDias(),formBean.getSalaSelect()));		
			return mapping.findForward("refresh");
		}
	}
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session.getAttribute("userlogin")==null){
			return mapping.findForward("prohibido");
		}else{
			String idRes=request.getParameter("param1");
			
			try {
				int idReservacion=Integer.parseInt(idRes);
				ReservacionDB reservacionDB=ReservacionDB.getInstance();
				Reservacion reservacion=reservacionDB.selectByPrimaryKey(idReservacion);
				
				if(reservacion!=null){
					SalaDB salaDB=SalaDB.getInstance();
					Sala sala=salaDB.selectByPrimaryKey(reservacion.getId_sala());
					EliminarReservFormBean eliminarFormBean=new EliminarReservFormBean();
					eliminarFormBean.setReservacion(reservacion);
					eliminarFormBean.setSala(sala);
					
					request.getSession(false).setAttribute("EliminarReservFormBean",eliminarFormBean);
				}else{
					return mapping.findForward("refresh");
				}
				
				
				
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return mapping.findForward("refresh");
			}
			
			return mapping.findForward("eliminar");
		}
	}
	public ActionForward next(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminFormBean bean=(AdminFormBean)form;
		
		Dia lunes=(Dia)bean.getDias().get(0);
		Calendar calendar=Calendar.getInstance(new Locale("en","US"));
		calendar.setTime(new Date(lunes.getFechaDate().getTime()+(7*24*60*60*1000)));
		bean.inicializaHeaderDias(calendar);
		bean.setHorasDias(new MatrizHorasDias(bean.getDias(),bean.getSalaSelect()));
		return mapping.findForward("refresh");
	}
	public ActionForward back(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AdminFormBean bean=(AdminFormBean)form;
		Dia lunes=(Dia)bean.getDias().get(0);
		Calendar calendar=Calendar.getInstance(new Locale("en","US"));		
		
		calendar.setTime(new Date(lunes.getFechaDate().getTime()-(7*24*60*60*1000)));
		bean.inicializaHeaderDias(calendar);
		bean.setHorasDias(new MatrizHorasDias(bean.getDias(),bean.getSalaSelect()));
		return mapping.findForward("refresh");
	}
	public ActionForward updateByFecha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		AdminFormBean bean=(AdminFormBean)form;
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
	
	
}
