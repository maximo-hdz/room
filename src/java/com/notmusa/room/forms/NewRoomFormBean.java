package com.notmusa.room.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class NewRoomFormBean extends ActionForm {
	private String sala;
	
	public String getSala() {
		return sala;
	}
	
	public void setSala(String sala) {
		this.sala = sala;
	}
	
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		
		String operacion=request.getParameter("operacion");
		ActionErrors actionErrors=new ActionErrors();
		if(operacion!=null && !operacion.equals("Regresar")){
			
			if(sala==null || sala.trim().equals("")){
				actionErrors.add("empty",new ActionMessage("errors.salaEmpty"));
			}
		}
		return actionErrors;
	}
	
	
	
}
