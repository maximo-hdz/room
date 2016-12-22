package com.notmusa.room.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.notmusa.room.db.Sala;
import com.notmusa.room.db.SalaDB;
import com.notmusa.room.forms.AdminFormBean;
import com.notmusa.room.forms.EditarFormBean;
import com.notmusa.room.forms.SalasFormBean;
/**
 * 
 * @author avillagran
 *
 */
public class EditarAction extends DispatchAction {
	public ActionForward Guardar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session.getAttribute("userlogin")==null){
			return mapping.findForward("prohibido");
		}else{
			SalaDB salaDB=SalaDB.getInstance();
			Sala sala=new Sala();
			EditarFormBean bean=(EditarFormBean)form;
			sala.setNombre_sala(bean.getSala());
			sala.setIdSala(bean.getIdSala());
			
			salaDB.update(sala);
			SalasFormBean salasFormBean=new SalasFormBean();
			request.getSession(false).setAttribute("SalasFormBean",salasFormBean);
			AdminFormBean adminFormBean=(AdminFormBean)request.getSession(false).getAttribute("AdminFormBean");
			adminFormBean.inicializaSalasDB();
			
			return mapping.findForward("success");
		}
	}
	public ActionForward Regresar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session.getAttribute("userlogin")==null){
			return mapping.findForward("prohibido");
		}else{
			String idSala=request.getParameter("id");
			if(idSala!=null){
				SalaDB salaDB=SalaDB.getInstance();
				Sala sala=salaDB.selectByPrimaryKey(Integer.parseInt(idSala));
				if(sala!=null){
					EditarFormBean editarFormBean=new EditarFormBean();
					editarFormBean.setIdSala(Integer.parseInt(idSala));
					editarFormBean.setSala(sala.getNombre_sala());
					request.getSession(false).setAttribute("EditarFormBean",editarFormBean);
					return mapping.findForward("edit");
				}else{
					return mapping.findForward("prohibido");
				}
			}else{
				return mapping.findForward("prohibido");
			}
		}
	}
	
}
