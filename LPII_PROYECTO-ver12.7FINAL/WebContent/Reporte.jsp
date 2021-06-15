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
		<div class="card ">
			<h5 class="card-header  font-weight-bold">Reportes</h5>
			<div class="card-body">
			<form id="idReporte" data-toggle="validator" role="form" method="" action="">
				<br>
					<div class="row col-sm-8 d-flex align-items-center ">
					
					<div class="form-group col-sm-2">
						<label for="idReporte">Reporte</label> 	
					</div>
					<div class="form-group col-sm-6">
						<select class="form-control" name="reporte" id="idComboReporte">
							<option value="">[Seleccione]</option>
							<option value="1">Solicitudes</option>
							<option value="2">Informes Escalafonarios</option>
							<option value="3">Informes Técnicos Legales</option>
							<option value="4">Informes de Disponibilidad Presupuestal</option>
							<option value="5">Informes Legales</option>
							<option value="6">Resoluciones</option>
							<option value="7">Expedientes</option>
						</select>	
					</div>
					<div class="form-group col-sm-2">
						<button type="button" class="btn btn-primary1 float-right boton1" id="btnBuscar" >
						<i class="fas fa-search"></i><span class="boton">Buscar</span></button>	
					</div>
					
				</div>
				<br>
				<div class="embed-responsive embed-responsive-16by9">
  					<iframe class="embed-responsive-item" id="panelPDF" src="" allowfullscreen></iframe>
				</div>
			</form>
			
			</div>
		</div>
	</div>
</div>



<br><jsp:include page="PrincipalFinal.jsp" />

	<script>
	
		$("#btnBuscar").click(function(){
			
			var doc,
			
			doc=$("#idComboReporte").val();
			
			$("#panelPDF").attr('src', 'http://localhost:8080/LPII_PROYECTO-ver12.1/ServletReporte?reporte='+ doc);
			
		})
	
	</script>

</body>
</html>