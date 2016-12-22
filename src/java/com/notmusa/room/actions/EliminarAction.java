package com.notmusa.room.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.notmusa.room.db.ReservacionDB;
import com.notmusa.room.db.Sala;
import com.notmusa.room.db.SalaDB;
import com.notmusa.room.forms.AdminFormBean;
import com.notmusa.room.forms.EliminarFormBean;
import com.notmusa.room.forms.SalasFormBean;
/**
 * Elimina la una sala de la base datos asi como sus resctivas reservaciones
 * @author avillagran
 *
 */
public class EliminarAction extends DispatchAction {
	public ActionForward Si(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		EliminarFormBean bean=(EliminarFormBean)form;
		
		
		
		SalaDB salaDB=SalaDB.getInstance();			
		try {
			int idSala=bean.getIdSala();
			Sala sala=new Sala();
			sala.setIdSala(idSala);
			salaDB.delete(sala);
			ReservacionDB reservacionDB=ReservacionDB.getInstance();
			reservacionDB.deleteByIdSala(idSala);
			
			AdminFormBean adminFormBean=new AdminFormBean();
			SalasFormBean salasFormBean=new SalasFormBean(); 
			request.getSession(false).setAttribute("SalasFormBean",salasFormBean);
			request.getSession(false).setAttribute("AdminFormBean",adminFormBean);
			return mapping.findForward("success");
		} catch (Exception e) {				
			e.printStackTrace();
			return mapping.findForward("error");
		}
		
		
		
	}
	public ActionForward No(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String idSala=request.getParameter("id");
		if(idSala!=null){
			SalaDB salaDB=SalaDB.getInstance();
			Sala sala=salaDB.selectByPrimaryKey(Integer.parseInt(idSala));
			if(sala!=null){
				EliminarFormBean editarFormBean=new EliminarFormBean();
				editarFormBean.setIdSala(Integer.parseInt(idSala));
				editarFormBean.setSala(sala.getNombre_sala());
				request.getSession(false).setAttribute("EliminarFormBean",editarFormBean);
				return mapping.findForward("drop");
			}else{
				return mapping.findForward("error");
			}
		}else{
			return mapping.findForward("error");
		}
		
	}
}
