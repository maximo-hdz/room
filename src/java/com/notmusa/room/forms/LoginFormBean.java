package com.notmusa.room.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginFormBean extends ActionForm {
	private String user;
	private String password;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public ActionErrors validate(ActionMapping arg0, HttpServletRequest arg1) {
		ActionErrors errores = new ActionErrors();
		
		
		if(password==null || user==null || password.trim().equals("") || user.trim().equals("")){
			errores.add("email", new ActionMessage("errors.usuario"));
		}
		
		return errores;
	}
	

}
