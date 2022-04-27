<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!--Para mejorar el uso de jsp debemos importar estas dos librerias de  -->
<!--jstl a nuestro proyecto, y aunque nuestro servidor Tomcat no-->
<!--reconoce estas librerias nos va a mostrar el proyecto sin problema,  -->
<!--pero es buena practica al menos poner a disponibilidad del servidor -->
<!--Tomcat estas librerias configurandolo a traves de Eclipse en el menu -->
<!--deploiment assembly agregandole las librerias desde el java build path -->
<!--que es donde le cargamos las librerias a nuestro proyecto -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Productos</title>
	<link rel="stylesheet" href="styles/listado_productos.css">
</head>

<body>
	<div id="contPrincipal">
<%--	#{ }  --%>
		<h1>Listado de Productos</h1>
		<ul>

<!--Este foreach solo recorre objetos JavaBeans, ya que asi lo hace JSP al igual que 
Hibernate al seguir el estandar JPA, solo leen objetos del tipo Javabeans, 
en donde los atributos de dichos objetos son las propertties de los Java Beans, que
a su vez son los métodos getters y setters de las clases de dichos objetos (el nombre
de la propertie es el nombre del metodo sin el get o set y todo el nombre en 
minúsculas). -->

			<c:forEach items="${prods}" var="prod">
			<li>${prod.idProducto} ${prod.nombreProd}, ${prod.precio} - ${prod.fabricanteProd.nombreFab}</li> 
			</c:forEach>
		</ul>
		<a href="index.html"><button>Volver</button></a>
	</div>

</body>

</html>