<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<html>
<head>
<title>Reservacion de Salas</title>
<script type="text/javascript"  src="/room/overlib.js"></script>
<script language="JavaScript" src="/room/calendar1.js" type="text/javascript"></script>

<script type="text/javascript">
function set(target) {	document.forms[0].elements[0].value=target;}

function goAway() {	
if (confirm('Estas seguro de que quieres guardar?'))
{		set('guardar');		
document.forms[0].submit();	
}else {		return false;	}		}

function goNextWeek(value){
set(value);		
document.forms[0].submit();	
}
</script>
<script type="text/javascript">function abrirWin(){infSala = document.forms[0].elements['salaSelect'].value;
window.open('/room/html/sala'+infSala+'.html','DetallaWin','width=600,height=460')}</script>
<link type="text/css" rel="stylesheet" rev="stylesheet" href="/room/css/style.css">
</head>
<body background="/room/img/fondoa.jpg">
<table align="center" class="tableLine" width="600" cellpadding="0" cellspacing="0" border="0">
  <tr> 
    <td width="600" height="120" valign="top">&nbsp;</td>
  </tr>
  <tr> 
    <td align="right">&nbsp;<a href="/room/adm/login.jsp">Admin</a></td>
  </tr>
  <html:form action="Reservacion2"><html:hidden property="operacion" value=""/> 
  <tr> <td align="right" height="100" width="100%">
    <table align="center" width="100%" cellspacing="0" cellpadding="5">
      <tr> 
        <td align="center" colspan="15" class="Titulo2">Llena el formulario, elige 
          la duraci&oacute;n de la reservaci&oacute;n y presiona el bot&oacute;n 
          de GUARDAR.</td>
      </tr>
      <tr> 
        <td align="center" colspan="15" class="error"><html:errors/></td>
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
        <td class="Titulo1">&nbsp;</td>
        <td align="right" class="Titulo1" width="20%">Salas: </td>
        <td class="Titulo1"> <html:select property="salaSelect" onchange="set('mostrar');submit();" styleClass="estiloSel" ><html:optionsCollection name="ReservacionFormBean2"  property="salas"  /></html:select></td>
        <br>
        <td width="180" align="center" class="Titulo1"> <!-- a href="#" target="_blank" onClick="abrirWin();return false;" onMouseOver="abrirWin();return true;"> 
          M&aacute;s informaci&oacute;n.</a --> </td>
      </tr>
    </table>
    <table align="center" cellpadding="2" cellspacing="2" border="0" width="100%">
      <bean:define id="next" value="next"/><bean:define id="back" value="back"/> 
      <tr> 
        <td width="25%" align="right" class="Titulo1"> Ver semana: </td>
        <td width="180" align="center" class="Titulo1"> 

<!-- html:link action="Reservacion2" paramId="operacion" paramName="back" --><!-- img src="/room/img/botAnte.gif" alt="anterior" width="80" height="20" border="0" --><!-- /html:link --> 
<a href="#" onClick="goNextWeek('back');return false;" ><img src="/room/img/botAnte.gif" alt="anterior" width="80" height="20" border="0"></a>
          &nbsp;&nbsp; <!-- html:link action="/Reservacion2" paramId="operacion" paramName="next" --><!-- img src="/room/img/botSig.gif" alt="siguiente" width="80" height="20" border="0" --><!-- /html:link -->
<a href="#" onClick="goNextWeek('next');return false;" ><img src="/room/img/botSig.gif" alt="siguiente" width="80" height="20" border="0"></a>
	
        </td>
        <td align="right" class="Titulo1"> ir al : &nbsp; &nbsp;<html:text property="fecha" readonly="true" size="10" styleClass="estilo"/> 
          &nbsp; &nbsp;<a href="javascript:cal1.popup();"><img src="/room/img/calendario/cal.gif" border="0" alt="imagen" height="16" width="20" align="absmiddle"></a> 
          &nbsp; &nbsp;<html:button property="" value="Mostrar" onclick=" set('updateByFecha');submit();" styleClass="estilo"/>	
        </td>
      </tr><tr><td colspan="3">
      <table align="center" cellpadding="2" cellspacing="2" border="0" width="100%">
        <tr align="center"> 
          <td class="Titulo3">&nbsp; </td>
          <logic:iterate name="ReservacionFormBean2" id="dia" property="dias">	
          <td colspan="2" class="Titulo3"> <bean:write name="dia" property="nameDia" />	
            <bean:write name="dia" property="fecha" /> </td>
          </logic:iterate> 
        <tr align="center" bgcolor="#ffffff" > 
          <td class="Titulo3"> Hora </td>
          <td class="Titulo3">0 min.</td>
          <td class="Titulo3">30 min.</td>
          <td class="Titulo3">0 min.</td>
          <td class="Titulo3">30 min.</td>
          <td class="Titulo3">0 min.</td>
          <td class="Titulo3">30 min.</td>
          <td class="Titulo3">0 min.</td>
          <td class="Titulo3">30 min.</td>
          <td class="Titulo3">0 min.</td>
          <td class="Titulo3">30 min.</td>
          <td class="Titulo3">0 min.</td>
          <td class="Titulo3">30 min.</td>
          <td class="Titulo3">0 min.</td>
          <td class="Titulo3">30 min.</td>
        </tr>
        <logic:iterate name="ReservacionFormBean2" id="row" property="horasDias">	
        <tr> 
          <td bgcolor="#ffffff" align="center"> <font color="#D8A201" size="2"><bean:write name="row" property="hora"/></font>	
          </td><logic:iterate name="row" id="dia" property="renglon">
          <logic:equal name="dia" property="dateOldFirst30" value="true"> <logic:equal name="dia"   property='busyFirst30' value="true">	
          <td width="35" bgcolor="#E7E7E7" align="center" > <img src="/room/img/fin.jpg" alt="Cancel" width="30" height="15" border ="0" align="middle" hspace="0" vspace="0">	
          </td>
          </logic:equal> <logic:notEqual name="dia"   property="busyFirst30" value="true">	
          <td width="35" bgcolor="#E7E7E7" align="center"> <font color="#CFCFCF">-</font>	
          </td>
          </logic:notEqual> </logic:equal> <logic:equal name="dia" property="dateOldLast30" value="true">	
          <logic:equal name="dia"   property='busyLast30' value="true"> 
          <td width="35" bgcolor="#ffffff" align="center" > <img src="/room/img/fin.jpg" alt="Cancel" width="30" height="15" border ="0" align="middle" hspace="0" vspace="0">	
          </td>
          </logic:equal> <logic:notEqual name="dia"   property="busyLast30" value="true">	
          <td width="35" bgcolor="#ffffff" align="center"> <font color="#CFCFCF">-</font>	
          </td>
          </logic:notEqual> </logic:equal> <logic:notEqual name="dia" property="dateOldFirst30" value="true"><logic:equal name="dia"   property='busyFirst30' value="true">
          <td   onMouseOver='return overlib("<html>Mail: <bean:write name="dia" property="reservacionFirst30.mail"/><br/>	Depto: <bean:write name="dia" property="reservacionFirst30.departamento"/>  <br/>	Comentario: <bean:write name="dia" property="reservacionFirst30.comentario"/> </html>",CAPTION,"Reservado")' onMouseOut="nd();" width="35" bgcolor=<bean:write name="dia" property="colorFirst30"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;align="center" 
          > <font size="2" class="styfo"><img src="/room/img/reser.jpg" alt="Cancel" width="30" height="15" border ="0" align="middle" hspace="0" vspace="0"></font>	</td>
        <!---<html:link action="/Admin" paramId="param1" paramName="dia" paramProperty="idFirst30">			 <br/><font size="2">C</font></html:link></td>--></logic:equal>
        <logic:notEqual  name="dia" property="busyFirst30" value="true"> 
        <td width="35"  bgcolor="#E7E7E7" align="center"> <html:radio  idName="dia" property="option" name="ReservacionFormBean2" value="fechaCompletaFirst30" disabled="false"/>	
        </td>
        </logic:notEqual> </logic:notEqual> <logic:notEqual name="dia" property="dateOldLast30" value="true">	
        <logic:equal name="dia"   property="busyLast30" value="true"> 
        <td  onMouseOver='return overlib("<html>Mail: <bean:write name="dia" property="reservacionLast30.mail"/><br/> Depto: <bean:write name="dia" property="reservacionLast30.departamento"/>  <br/>	Comentario: <bean:write name="dia" property="reservacionLast30.comentario"/> </html>",CAPTION,"Reservado")' onMouseOut="nd();" width="35"  bgcolor=<bean:write name="dia" property="colorLast30"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;align="center" 
        > <font size="2" class="styfo"><img src="/room/img/reser.jpg" alt="Cancel" width="30" height="15" border ="0" align="middle" hspace="0" vspace="0"></font>	</td>
        <!--<html:link action="/Admin" paramId="param1" paramName="dia" paramProperty="idLast30">			<br/><font size="2">C</font></html:link></td>-->
        </logic:equal> <logic:notEqual  name="dia" property="busyLast30" value="true">	
        <td width="35"  bgcolor="#ffffff" align="center"> <html:radio  idName="dia" property="option" name="ReservacionFormBean2" value="fechaCompletaLast30" disabled="false"/>	
        </td>
        </logic:notEqual> </logic:notEqual> </logic:iterate> </tr></logic:iterate>	
      </table>
      <tr> 
        <td colspan="5" align="center" class="Titulo3"> &nbsp;&nbsp;Duraci&oacute;n 
          (horas): </td>
      <tr>  
        <td align="center" colspan="3" class="Titulo1"> 
                                                        <html:radio property="total" value="2">1&nbsp;&nbsp;&nbsp;&nbsp;</html:radio>
                                                        
                                                                <html:radio property="total" value="4">2&nbsp;&nbsp;&nbsp;&nbsp;</html:radio>
                                                                
                                                                <html:radio property="total" value="6">3&nbsp;&nbsp;&nbsp;&nbsp;</html:radio>
                                                                
                                                                <html:radio property="total" value="8" >4&nbsp;&nbsp;&nbsp;&nbsp;</html:radio>	

                                                                <html:radio property="total" value="10" >5&nbsp;&nbsp;&nbsp;&nbsp;</html:radio>	
                                                                <html:radio property="total" value="12" >6&nbsp;&nbsp;&nbsp;&nbsp;</html:radio>	
                                                                <html:radio property="total" value="14" >7&nbsp;&nbsp;&nbsp;&nbsp;</html:radio>	
                                                                <html:radio property="total" value="16" >8&nbsp;&nbsp;&nbsp;&nbsp;</html:radio>	
                                                                <html:radio property="total" value="18" >9&nbsp;&nbsp;&nbsp;&nbsp;</html:radio>	
                                                                <html:radio property="total" value="20" >10&nbsp;&nbsp;&nbsp;&nbsp;</html:radio>	
        </td>
      </tr>
      <tr> 
        <td colspan="3">&nbsp;</td>
      </tr>
      <tr> 
        <td colspan="3" align="center" class="datos"> <html:reset value="limpia" styleClass="estilo"></html:reset><html:button property="" value="guardar" onclick="goAway()" styleClass="estilo"/>	
        </td>
      </tr>
      <tr> 
        <td colspan="3" align="center" class="datos">&nbsp; </td>
      </tr>
    </table></td>
  </tr>
  </html:form> 
  <tr> 
    <td  height="20" colspan="3" bgcolor="#DADDE2" align="center" class="pie"> 
      &nbsp; Notmusa 2007 | Derechos Reservados &reg; </td>
  </tr>
</table>
<script type="text/javascript">var cal1 = new calendar1(document.forms[0].elements['fecha']);  cal1.year_scroll = true;  cal1.time_comp = false;</script>

</body>
</html>
