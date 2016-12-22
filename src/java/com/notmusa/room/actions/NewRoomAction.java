package com.notmusa.room.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.notmusa.room.db.Sala;
import com.notmusa.room.db.SalaDB;
import com.notmusa.room.forms.NewRoomFormBean;
import com.notmusa.room.forms.SalasFormBean;
/**
 * 
 * @author avillagran
 *
 */
public class NewRoomAction extends DispatchAction {

	public ActionForward Guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SalaDB salaDB=SalaDB.getInstance();
		Sala sala=new Sala();
		NewRoomFormBean bean=(NewRoomFormBean)form;
		sala.setNombre_sala(bean.getSala());
		salaDB.insert(sala);
		SalasFormBean salasFormBean=new SalasFormBean(); 
		request.getSession(false).setAttribute("SalasFormBean",salasFormBean);		
		
		return mapping.findForward("success");
	}
	public ActionForward Regresar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}

}
