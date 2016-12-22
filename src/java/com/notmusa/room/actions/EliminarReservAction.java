package com.notmusa.room.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.notmusa.room.db.Reservacion;
import com.notmusa.room.db.ReservacionDB;
import com.notmusa.room.forms.AdminFormBean;
import com.notmusa.room.forms.EliminarReservFormBean;
import com.notmusa.room.utils.MatrizHorasDias;
/**
 * 
 * @author avillagran
 *
 */
public class EliminarReservAction extends DispatchAction {
	public ActionForward Si(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		EliminarReservFormBean bean=(EliminarReservFormBean)form;
		
		Reservacion reservacion=new Reservacion();
		reservacion.setId_reservas(bean.getReservacion().getId_reservas());
		ReservacionDB reservacionDB=ReservacionDB.getInstance();
		reservacionDB.delete(reservacion);
		AdminFormBean adminFormBean=(AdminFormBean)request.getSession(false).getAttribute("AdminFormBean");
		adminFormBean.setHorasDias(new MatrizHorasDias(adminFormBean.getDias(),adminFormBean.getSalaSelect()));
		return mapping.findForward("success");
	}
	public ActionForward No(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
}
