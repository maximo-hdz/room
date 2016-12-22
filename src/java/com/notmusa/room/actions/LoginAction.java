package com.notmusa.room.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.notmusa.room.db.Adms;
import com.notmusa.room.db.AdmsDB;
import com.notmusa.room.forms.LoginFormBean;

public class LoginAction extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		LoginFormBean bean=(LoginFormBean)form;
		
		AdmsDB admsDB=AdmsDB.getInstance();
		Adms adms=admsDB.selectByUserPassword(bean.getUser(),bean.getPassword());
		if(adms==null){
			ActionErrors errores = new ActionErrors();
			errores.add("email", new ActionMessage("errors.usuarioMissing"));
			addErrors(request,errores);
			return mapping.findForward("faild");
		}
		request.getSession().setMaxInactiveInterval(60*60);
		HttpSession session=request.getSession(true);
		
		session.setAttribute("userlogin",adms);
		session.removeAttribute("AdminFormBean");
		session.removeAttribute("SalasFormBean");
		return mapping.findForward("success");
	}
	

}
