package com.notmusa.room.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.notmusa.room.forms.AdminFormBean;
import com.sun.corba.se.internal.core.Request;

public class SalasAction extends DispatchAction {
	public ActionForward Nueva(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("newroom");
	}
	public ActionForward Regresar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.getSession(false).setAttribute("AdminFormBean",new AdminFormBean());
		return mapping.findForward("back");
	}
	protected ActionForward unspecified(ActionMapping mapping, ActionForm arg1, HttpServletRequest request, HttpServletResponse arg3) throws Exception {
		
		
		return mapping.findForward("prohibido");
	}
	

}
