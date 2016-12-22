<%@ taglib uri="/tags/struts-html" prefix="html" %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<logic:notPresent name="userlogin" scope="session"> <logic:forward name="prohibido"/></logic:notPresent>
<html>
<head>
<title>Administrador</title>
<script type="text/javascript"  src="<html:rewrite page='/overlib.js'/>" ></script>
<script language="JavaScript" src="<html:rewrite page='/calendar1.js'/>" type="text/javascript"></script>
<script type="text/javascript">function set(target) {document.forms[0].elements[0].value=target;}</script>
<link type="text/css" rel="stylesheet" rev="stylesheet" href="/room/css/style.css">
</head>
<body background="/room/img/fondoa.jpg">
<table align="center" class="tableLine" width="600" cellpadding="0" cellspacing="0" border="0">
  <tr> 
    <td width="600" height="125" valign="top">&nbsp; </td>
  </tr>
  <tr> 
    <td align="right" width="100%"><html:form action="Admin"> 
      <table align="center" width="100%">
        <tr>
          <td colspan="3"><html:hidden property="operacion" value=""/> <table align="center" cellpadding="2" cellspacing="0" width="100%">
              <tr> 
                <td width="54%" class="Titulo2">Administrador</td>
                <td width="46%" align="right" class="Titulo2"><html:link action="Salir"><font color="#FFFFFF">Salir</font></html:link></td>
              </tr>
              <tr> 
                <td align="right" class="Titulo1">Salas: </td>
                <td align="right" class="Titulo1"><html:link action="/altas">Proceso Batch</html:link></td>
              </tr>
              <tr>
                <td align="center" colspan="2"> <html:select property="salaSelect" onchange="set('mostrar');submit();" styleClass="estiloSel"> 
                  <html:optionsCollection name="AdminFormBean"  property="salas"   /> 
                  </html:select>&nbsp;&nbsp;&nbsp;<html:submit value="Editar Salas" property="operacion" onclick="set('salas')" styleClass="estilo"/> 
                </td>
              </tr>
            </table></td>
        </tr>
        <bean:define id="next" value="next"/><bean:define id="back" value="back"/>
        <tr> 
          <td width="25%" align="right" class="Titulo1"> Ver semana: </td>
          <td width="180" align="center" class="Titulo1"> <html:link action="/Admin" paramId="operacion" paramName="back"><img src="/room/img/botAnte.gif" alt="anterior" width="80" height="20" border="0"></html:link> 
            &nbsp;&nbsp; <html:link action="/Admin" paramId="operacion" paramName="next"><img src="/room/img/botSig.gif" alt="siguiente" width="80" height="20" border="0"></html:link>	
          </td>
          <td align="right" class="Titulo1"> ir al : &nbsp; &nbsp;<html:text property="fecha" readonly="true" size="10" styleClass="estilo"/> 
            &nbsp; &nbsp;<a href="javascript:cal1.popup();"><img src='<html:rewrite page="/img/calendario/cal.gif"/>' border="0" alt="imagen" height="16" width="20" align="absmiddle"></a> 
            &nbsp; &nbsp;<html:button property="" value="Mostrar" onclick=" set('updateByFecha');submit();" styleClass="estilo"/>	
          </td>
        </tr>
        <tr>
          <td colspan="3"><table cellpadding="2" cellspacing="2" border="0" width="100%">
              <tr align="center">
                <td class="Titulo3">Hora</td>
                <logic:iterate name="AdminFormBean" id="dia" property="dias">
                <td colspan="2" class="Titulo3"> <bean:write name="dia" property="nameDia" /> 
                  <br>
                  <bean:write name="dia" property="fecha" /> </td>
                </logic:iterate><logic:iterate name="AdminFormBean" id="row" property="horasDias">
              <tr> 
                <td bgcolor="#ffffff" align="center"><font color="#D8A201" size="2"><bean:write name="row" property="hora"/></font></td>
                <logic:iterate name="row" id="dia" property="renglon"> <logic:equal name="dia" property="dateOldFirst30" value="true">	
                <logic:equal name="dia"   property='busyFirst30' value="true">	
                <td onMouseOver='return overlib("<html>Mail: <bean:write name="dia" property="reservacionFirst30.mail"/><br/>	Depto: <bean:write name="dia" property="reservacionFirst30.departamento"/>  <br/>	Comentario: <bean:write name="dia" property="reservacionFirst30.comentario"/> </html>",CAPTION,"Reservado")' onMouseOut="nd();"  width="35" bgcolor="#E7E7E7" align="center">	
                  <img src="/room/img/fin.jpg" alt="Expiro" width="30" height="15" border ="0"  hspace="0" vspace="0"></td>
                </logic:equal> <logic:notEqual name="dia"   property="busyFirst30" value="true">	
                <td width="35" bgcolor="#E7E7E7" align="center"><font color="#CFCFCF">-</font></td>
                </logic:notEqual> </logic:equal> <logic:equal name="dia" property="dateOldLast30" value="true">	
                <logic:equal name="dia"   property='busyLast30' value="true">	
                <td onMouseOver='return overlib("<html>Mail: <bean:write name="dia" property="reservacionLast30.mail"/><br/> Depto: <bean:write name="dia" property="reservacionLast30.departamento"/>  <br/>	Comentario: <bean:write name="dia" property="reservacionLast30.comentario"/> </html>",CAPTION,"Reservado")' onMouseOut="nd();"  width="35" bgcolor="#ffffff" align="center" >	
                  <img src="/room/img/fin.jpg" alt="Expiro" width="30" height="15" border ="0"  hspace="0" vspace="0"></td>
                </logic:equal> <logic:notEqual name="dia"   property="busyLast30" value="true">	
                <td width="35" bgcolor="#ffffff" align="center"><font color="#CFCFCF">-</font></td>
                </logic:notEqual> </logic:equal> <logic:notEqual name="dia" property="dateOldFirst30" value="true">	
                <logic:equal name="dia"   property='busyFirst30' value="true">	
                <td height="35" onMouseOver='return overlib("<html>Mail: <bean:write name="dia" property="reservacionFirst30.mail"/><br/>	Depto: <bean:write name="dia" property="reservacionFirst30.departamento"/>  <br/>	Comentario: <bean:write name="dia" property="reservacionFirst30.comentario"/> </html>",CAPTION,"Reservado")' onMouseOut="nd();" width="35" bgcolor="#e7e7e7" align="center">	
                  <img src="/room/img/reser.jpg" alt="Reser" width="30" height="15" border ="0">	
                  <html:link action="/Admin" paramId="param1" paramName="dia" paramProperty="idFirst30">	
                  <br>
                  <img src="/room/img/cancel.jpg" alt="Cancel" width="30" height="15" border ="0"></html:link></td>
                </logic:equal> <logic:notEqual  name="dia" property="busyFirst30" value="true">	
                <td width="35" align="center" bgcolor="#e7e7e7"><font color="#CFCFCF">-</font></td>
                </logic:notEqual> </logic:notEqual> <logic:notEqual name="dia" property="dateOldLast30" value="true">	
                <logic:equal name="dia"   property="busyLast30" value="true">	
                <td height="35" onMouseOver='return overlib("<html>Mail: <bean:write name="dia" property="reservacionLast30.mail"/><br/> Depto: <bean:write name="dia" property="reservacionLast30.departamento"/>  <br/>	Comentario: <bean:write name="dia" property="reservacionLast30.comentario"/> </html>",CAPTION,"Reservado")' onMouseOut="nd();" width="35"  bgcolor="#ffffff" align="center">	
                  <img src="/room/img/reser.jpg" alt="Reser" width="30" height="15" border ="0">	
                  <html:link action="/Admin" paramId="param1" paramName="dia" paramProperty="idLast30">	
                  <br>
                  <img src="/room/img/cancel.jpg" alt="Cancel" width="30" height="15" border ="0"></html:link></td>
                </logic:equal> <logic:notEqual  name="dia" property="busyLast30" value="true">	
                <td width="35" align="center" bgcolor="#ffffff"><font color="#CFCFCF">-</font></td>
                </logic:notEqual> </logic:notEqual> </logic:iterate> </tr></logic:iterate>
            </table>
      </table>
      </html:form> </td>
  </tr>
  <tr> 
    <td  height="20" colspan="3" bgcolor="#DADDE2" align="center" class="pie"> 
      &nbsp; Notmusa 2007 | Derechos Reservados &reg; </td>
  </tr>
</table>
<script type="text/javascript">var cal1 = new calendar1(document.forms[0].elements['fecha']);  cal1.year_scroll = true;  cal1.time_comp = false;</script>
</body>
</html>