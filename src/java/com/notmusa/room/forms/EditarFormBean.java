package com.notmusa.room.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
/**
 * 
 * @author avillagran
 *
 */
public class EditarFormBean extends ActionForm {
	private String sala;
	private int idSala;
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

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}
	
	
}
