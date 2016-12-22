<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<html>
<head>
<title>Reservacion de Salas</title>
<script type="text/javascript"  src="<html:rewrite page='/overlib.js'/>"></script>
<script language="JavaScript" src="<html:rewrite page='/calendar1.js'/>" type="text/javascript"></script>
<script type="text/javascript">
function set(target) {
	document.forms[0].elements[0].value=target;
}
function goAway() {
	if (confirm('Estas seguro de que quieres guardar?')){
		set('ReservPermanente');
		document.forms[0].submit();
	}else {
		return false;
	}
	
	
}
</script>
<link type="text/css" rel="stylesheet" rev="stylesheet" href="/room/css/style.css">
</head>
<body background="/room/img/fondoa.jpg">
<table align="center" class="tableLine" width="600" cellpadding="0" cellspacing="0" border="0">
  <tr> 
    <td width="600" height="125" valign="top">&nbsp; </td>
  </tr>
  <html:form action="Permanentes"> <html:hidden property="operacion" value=""/> 
  <tr> 
    <td align="right" height="100" width="100%"> <table align="center" width="100%" cellspacing="0" cellpadding="3">
        <tr> 
          <td colspan="5" align="right"><html:link action="/regresar">Regresar</html:link> 
          </td>
        </tr>
        <tr> 
          <td align="center" colspan="5" class="Titulo2">Llena el formulario, 
            elije la duracion de la reservacion y presiona el boton de GUARDAR.</td>
        </tr>
        <tr> 
          <td align="center" colspan="5" class="error"><html:errors/></td>
        </tr>
        <tr> 
          <td align="right" width="25%" class="Titulo1">Mail:</td>
          <td class="Titulo1" width="25%"><html:text property="mail" styleClass="estilo"/></td>
          <td align="center" class="Titulo1">&nbsp; &nbsp;</td>
          <td align="right" class="Titulo1" width="25%">Departamento:</td>
          <td class="Titulo1" width="25%"><html:text property="departamento" styleClass="estilo"/></td>
        </tr>
        <tr> 
          <td align="right" class="Titulo1">Comentario:</td>
          <td class="Titulo1"><html:text property="comentario" styleClass="estilo"/></td>
          <td class="Titulo1">&nbsp; &nbsp;</td>
          <td align="right" class="Titulo1" width="20%">Salas: </td>
          <td class="Titulo1"> <html:select property="salaSelect" onchange="set('mostrar');submit();" styleClass="estiloSel" > 
            <html:optionsCollection name="ResPermFormBean"  property="salas"  /> 
            </html:select></td>
      </table>
      <table align="center" cellpadding="2" cellspacing="2" border="0" width="100%">
        <bean:define id="next" value="next"/> <bean:define id="back" value="back"/> 
        <tr> 
          <td align="left" class="Titulo1"> Fecha Inicio : &nbsp; &nbsp;<html:text property="fecha" readonly="true" size="10" styleClass="estilo"/> 
            &nbsp; &nbsp;<a href="javascript:cal1.popup();"><img src='<html:rewrite page="/img/calendario/cal.gif"/>' border="0" alt="imagen" height="16" width="20" align="absmiddle"></a> 
          </td>
          <td align="right" class="Titulo1"> Hora de Inicio : </td>
		  <td class="Titulo1">
		    <html:select property="hora" styleClass="estiloSel">
            <html:option value="0">-- Selecciona --</html:option> 
			<html:options property="horas"/> 
            </html:select> </td>
        </tr>
        <tr> 
          <td align="left" class="Titulo1"> Fecha Inicio : &nbsp; &nbsp;<html:text property="fecha_fin" readonly="true" size="10" styleClass="estilo"/> 
            &nbsp; &nbsp;<a href="javascript:cal2.popup();"><img src='<html:rewrite page="/img/calendario/cal.gif"/>' border="0" alt="imagen" height="16" width="20" align="absmiddle"></a> 
          </td>
          <td align="right" class="Titulo1"> Dia :</td>
			<td class="Titulo1">
			<html:select property="dia" styleClass="estiloSel"> 
            <html:option value="0">-- Selecciona --</html:option> <html:option value="1">Domingo</html:option>	
            <html:option value="2">Lunes</html:option> <html:option value="3">Martes</html:option>	
            <html:option value="4">Miercoles</html:option> <html:option value="5">Jueves</html:option>	
            <html:option value="6">Viernes</html:option> <html:option value="7">Sabado</html:option>	
            </html:select> </td>
        </tr>
        <tr> 
          <td colspan="5" align="center" class="Titulo3"> &nbsp;&nbsp;Duraci&oacute;n 
            (horas): </td></tr>
        <tr> 
          <td align="center" colspan="3" class="Titulo1"> <html:radio property="total" value="1">½&nbsp;&nbsp;&nbsp;&nbsp;</html:radio> 
            <html:radio property="total" value="2">1&nbsp;&nbsp;&nbsp;&nbsp;</html:radio> 
            <html:radio property="total" value="3">1 ½&nbsp;&nbsp;&nbsp;&nbsp;</html:radio> 
            <html:radio property="total" value="4">2&nbsp;&nbsp;&nbsp;&nbsp;</html:radio> 
            <html:radio property="total" value="5">2 ½&nbsp;&nbsp;&nbsp;&nbsp;</html:radio> 
            <html:radio property="total" value="6">3&nbsp;&nbsp;&nbsp;&nbsp;</html:radio> 
            <html:radio property="total" value="7">3 ½&nbsp;&nbsp;&nbsp;&nbsp;</html:radio> 
            <html:radio property="total" value="8" >4&nbsp;&nbsp;&nbsp;&nbsp;</html:radio> 
          </td>
        </tr>
        <tr> 
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr> 
          <td colspan="3" align="center" class="datos"> <html:reset value="limpia" styleClass="estilo"></html:reset> 
            <html:button property="" value="Guardar" onclick="goAway()" styleClass="estilo"/> 
          </td>
        </tr>
      </table></td>
  </tr>
  </html:form> 
  <tr> 
    <td  height="20" colspan="3" bgcolor="#DADDE2" align="center" class="pie"> 
      &nbsp; Notmusa 2007 | Derechos Reservados &reg; </td>
  </tr>
</table>
<script type="text/javascript">
var cal1 = new calendar1(document.forms[0].elements['fecha']);
  cal1.year_scroll = true;
  cal1.time_comp = false;
  
  var cal2 = new calendar1(document.forms[0].elements['fecha_fin']);
  cal2.year_scroll = true;
  cal2.time_comp = false;
</script>
</body>
</html>