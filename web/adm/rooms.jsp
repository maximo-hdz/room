<%@ taglib uri="/tags/struts-html" prefix="html" %><%@ taglib uri="/tags/struts-bean"   prefix="bean" %><%@ taglib uri="/tags/struts-logic"   prefix="logic" %><html><head><title>Salas</title><link type="text/css" rel="stylesheet" rev="stylesheet" href="/room/css/style.css"></head><body background="/room/img/fondoa.jpg"><table align="center" class="tableLine">	<tr>		<td width="600" height="125" valign="top">&nbsp;				</td>	</tr>	 <tr> 		<td align="right" height="100">			<html:form action="Salas"><table align="center" cellpadding="5" cellspacing="0"><tr><td colspan="3" align="center" class="datos"><html:submit value="Nueva" property="operacion" styleClass="estilo"/></td> </tr><logic:notEmpty name="SalasFormBean"  property="salas"><logic:iterate id="sala" property="salas" name="SalasFormBean"><tr><td class="datos"><bean:write name="sala" property="label" /> </td><td class="datos"> <html:link action="/Editar" paramId="id" paramName="sala" paramProperty="value" > <img border="0" align="middle" height="20" src="<html:rewrite page='/img/editar.gif'/>" /></html:link> </td> <td> <html:link action="/Eliminar" paramId="id" paramName="sala" paramProperty="value" > <img border="0" align="middle" height="20" src="<html:rewrite page='/img/apagar.gif'/>" /></html:link> </td> </tr></logic:iterate></logic:notEmpty><tr><td colspan="3" align="center" ><html:submit value="Regresar" property="operacion" styleClass="estilo"/></td> </tr></table></html:form>		</td>  	</tr>	<tr>		<td  height="20" colspan="3" bgcolor="#DADDE2" align="center" class="pie">      &nbsp; Notmusa 2007 | Derechos Reservados &reg;     </td>	</tr></table></body></html>