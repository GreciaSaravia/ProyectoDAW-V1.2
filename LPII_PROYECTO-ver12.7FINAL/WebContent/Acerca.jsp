<!-- Agregar Libreria Core  de JSTL -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	
	<c:if test="${sesion == null}">
		<c:redirect url="Sesion.jsp"/>
	</c:if>

<jsp:include page="PrincipalInicio.jsp" />

<!--Main layout-->
<main>

<div class="row">
	<div class="col-sm-12">
		<div class="card text-center">
			<h5 class="card-header  font-weight-bold">Universidad Nacional Experimental de Guayana</h5>
			<div class="card-body">
				<h5 class="card-title">Luis Torres</h5>
				<h5 class="card-title">Grecia Saravia</h5>
				<h5 class="card-title">Daniela Campos</h5>
				<h5 class="card-title">Juan Carlos Vivanco</h5>
			</div>
		</div>
	</div>
</div>


<br><jsp:include page="PrincipalFinal.jsp" />

</body>
</html>