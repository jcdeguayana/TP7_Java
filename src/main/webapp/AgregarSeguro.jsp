<%@page import="entidades.TipoSeguro"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/estilos.css" rel="stylesheet">
</head>
<body>

	<div class="nav-bg">
		<nav class="navegacion-principal contenedor">
		<a href = "Inicio.jsp">Inicio</a>
		<a href = "ServletSeguros?accion=agregar">Agregar Seguros</a>
		<a href = "ServletSeguros?accion=listar">Listar Seguros</a>	
	</nav>
	</div>
	
	
	
	<form method="post" action="ServletSeguros">
	<%
		List<TipoSeguro> tipos = null;
		if(request.getAttribute("tiposSeguros")!=null){
		tipos = (List<TipoSeguro>) request.getAttribute("tiposSeguros");
		}
	%>
	<%
		int id =0;
		if(request.getAttribute("siguienteId")!=null){
		id = (int)request.getAttribute("siguienteId");
		}
	%>
	
	<h1><b>Agregar seguro</b></h1><br>

		Id seguro:<input type="text" name= "txtId" value="<%= id %>"readonly><br>       
		Descripcion: <input type="text" name="txtDescripcion"><br>
		
		Tipo de seguro:<select name="tipo" id="tipo">
    			<% if (tipos != null) {
           			for (TipoSeguro tipo : tipos) { %>
               			<option value="<%= tipo.getIdTipo() %>"><%= tipo.getDescripcion() %></option>
   												 <%     }
       				} else { %>
         			  <option value="">(Sin tipos disponibles)</option>
    					<% } %>
					 </select>
	
		Costo contratacion:<input type="text" name="txtCosto"><br>
		Costo maximo agregado:<input type="text" name="txtMaximoAgregado"><br>
	
	<br>
	
	<input type="submit" name = "btnAgregar" value="Aceptar">
	
	
	
	
	
	
	
	</form>

</body>
</html>