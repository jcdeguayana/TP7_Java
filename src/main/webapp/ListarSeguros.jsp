<%@page import="entidades.Seguro"%>
<%@page import="entidades.TipoSeguro"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/estilos.css" rel="stylesheet">
<title>Inicio</title>
</head>
<body>

	<div class="nav-bg">
		<nav class="navegacion-principal contenedor">
		<a href = "Inicio.jsp">Inicio</a>
		<a href = "ServletSeguros?accion=agregar">Agregar Seguros</a>
		<a href = "ServletSeguros?accion=listar">Listar Seguros</a>	
	</nav>
	</div>
	
	<%
		List<TipoSeguro> tipos = null;
		if(request.getAttribute("tiposSeguros")!=null){
		tipos = (List<TipoSeguro>) request.getAttribute("tiposSeguros");
		}
	%>
	
	<h1><b>Tipos de seguro en la base de datos</b></h1><br>
	
<% 
	ArrayList<Seguro> listaSeguros = null;
	if(request.getAttribute("ListaSeguros")!=null)
	{
		listaSeguros = (ArrayList<Seguro>) request.getAttribute("ListaSeguros");
	}

 %>
 
 <h2>Búsqueda por tipo de seguro</h2>
<form action="ServletSeguros" method="get" style="margin-bottom: 20px;">
    <input type="hidden" name="accion" value="filtrar">
    <label for="tipoSeguro">Tipo de seguro:</label>
    <select name="tipoSeguro" id="tipoSeguro">
      <% if (tipos != null) {
           			for (TipoSeguro tipo : tipos) { %>
               			<option value="<%= tipo.getIdTipo() %>"><%= tipo.getDescripcion() %></option>
   												 <%     }
       				} else { %>
         			  <option value="">(Sin tipos disponibles)</option>
    					<% } %>
    </select>
    <button type="submit">Filtrar</button>
</form>
 
 

<table border="1">
 <tr>
 <th>ID Seguro</th>
 <th>Descripción Seguro</th>
 <th>Descripción Tipo Seguro</th>
 <th>Costo Contratación</th>
 <th>Costo Máximo Asegurado</th>
 </tr>
    <tbody>
       <%  if(listaSeguros!=null)
		for(Seguro seguro : listaSeguros) 
		{
	%>
		<tr>  
	<td><%=seguro.getIdSeguro() %></td> 
	<td><%=seguro.getDescripcion() %></td>   
	<td><%=seguro.getTipo().getDescripcion() %></td>  
	<td><%=seguro.getCostoContratacion() %></td>
	<td><%=seguro.getCostoAsegurado() %></td>
</tr>
		
	<%  } %>
    </tbody>
 
</table>

</body>
</html>