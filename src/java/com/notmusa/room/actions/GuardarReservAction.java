package com.notmusa.room.actions;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.notmusa.room.db.Reservacion;
import com.notmusa.room.db.ReservacionDB;
import com.notmusa.room.db.SalaDB;
import com.notmusa.room.forms.AdminFormBean;
import com.notmusa.room.forms.EliminarReservFormBean;
import com.notmusa.room.forms.ReservacionFormBean;
import com.notmusa.room.utils.MatrizHorasDias;

public class GuardarReservAction extends DispatchAction {
	public ActionForward Guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {


		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date fecha; 
		ReservacionFormBean reservacionFormBean=(ReservacionFormBean)request.getSession(false).getAttribute("ReservacionFormBean");
        Long cons = new Long(1800000);
        int totalHora;
        int hora=8;
        Long fI = new Long(sdf.parse(reservacionFormBean.getOption()).getTime());
		
		totalHora = Integer.parseInt(reservacionFormBean.getTotal());
        
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
//		System.out.println("FcehaaaBean:" + reservacionBean.getTiempo().getHours());
		ReservacionDB reservacionDB=ReservacionDB.getInstance();
		reservacionDB.insertReservacion(reservacionBean);		
		fI = new Long(fI.longValue()+ cons.longValue());
	    }
		
	} catch (SQLException e) {
		e.printStackTrace();
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
