

<!-- Agregar Libreria Core  de JSTL -->
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <c:if test="${sesion == null}">
		<c:redirect url="Sesion.jsp"/>
	</c:if>


<jsp:include page="PrincipalInicio.jsp"/>

	<!--Main layout-->
	<main>
	
	<c:if test="${requestScope.MENSAJE!=null}">
			<div class="alert alert-light alert-dismissible fade show" role="alert">
			  <strong>MENSAJE </strong><i class="fas fa-exclamation-circle alerta"></i>
			  <h6>${requestScope.MENSAJE}</h6>
			 
			</div>
		</c:if>
		
		<div class="row">
			<div class="col-sm-12">
				<div class="card ">
					<h5 class="card-header  font-weight-bold">Solicitud de Beca N° <span id="mosCodigo" class="${requestScope.codigo}">${requestScope.codigo}</span></h5>
					<div class="card-body">
					<form id="idRegistrar" data-toggle="validator" role="form" method="post" action="ServletSolicitud?accion=GUARDAR">
						<input name="codiSolicitud" style="display: none;" value="${requestScope.codigo}" id="codReg" ></input>
						<input name="codiSolicitudModi" style="display: none;"  id="codModi" ></input>
						<input name="codiUsuario" style="display: none;"  id="idUsuario"  value="${sesion.codUsuario}" ></input>
						<div class="row">
						  <div class="col-sm-7">
						    <div class="card">
						      <div class="card-body">
						        <h5 class="card-title col-sm-12">Datos del Solicitante</h5>
						        <hr>
						        <div class="row">
						        	<div class="col-sm-5 d-flex align-items-center justify-content-center" >
						        		<img alt="" class="img-fluid rounded" src="https://png.pngtree.com/png-vector/20191026/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1870181.jpg " id="idimgsoli">
						        	</div>
						        	<div class="col-sm-7">   															        			
						        		<div class="form-group col-sm-12">
											<label for ="idcodigo">Código</label> 
											<div class="input-group mb-3">
											  <input type="text" class="form-control" id="idcodigo"  name="codSolicitante"  readonly>
											  <div class="input-group-append">
											    <button class="btn btn-outline-secondary "  type="button"  data-toggle="modal" data-target="#modalBuscar" id="btnBuscar"><span class="fas fa-search"></span></button>
											  </div>
											</div>									
										</div>
										<div class="form-group col-sm-12">
											<label for="idnombres">Nombres</label> <input type="text"
												class="form-control" id="idnombres" readonly >
										</div>
										<div class="form-group col-sm-12">
											<label for="idapellidos">Apellidos</label> <input type="text"
												class="form-control" id="idapellidos" readonly>
										</div>
										<div class="form-group col-sm-12">
											<label for="iddni">DNI</label> <input type="text"
												class="form-control" id="iddni"  readonly>
										</div>
										<div class="form-group col-sm-12">
											<label for="iddireccion">Dirección</label> <input type="text"
												class="form-control" id="iddireccion" readonly>
										</div>
						        	</div>
						        </div>								
							</div>
						    </div>
						  </div>
						  <div class="col-sm-5">
						    <div class="card">
						      <div class="card-body">
						        <h5 class="card-title col-sm-12">Requisitos</h5>
						        <hr>
						        <div class="form-group col-sm-12  req">
								    <label for="iddecjurada">Declaración Jurada</label>  
								    <label class="dcoAnti" id="doc1">  </label><br>
								    <input  type="file" id="iddecjurada" name="docdj" disabled>
								    <input  type="text"  id="iddecjurada1"  name="docdj1"  class="no"  >
								 </div>
								 <div class="form-group col-sm-12 req">
								    <label  for="idrescontrato">Resolución de Contrato</label> 
									 <label class="dcoAnti" id="doc2"></label><br>
								   	<input  type="file" id="idrescontrato" name="docrc" disabled>
								   	  <input  type="text"  class="no"  id="idrescontrato1"  name="docrc1" >
								 
								 </div>
								 <div class="form-group col-sm-12 req">
								    <label  for="idresolquinquenio">Resolución de Quinquenio</label> 
								     <label class="dcoAnti" id="doc3"> </label><br>
								    <input  type="file" id="idresolquinquenio" name="doceq" disabled>
								      <input  type="text"   class="no" id="idresolquinquenio1"  name="doceq1">
								  
								 </div>
								 <div class="form-group col-sm-12 req">
								    <label  for="idconshab">Constancia de Pagos de Haberes</label> 
								    <label class="dcoAnti" id="doc4"> </label><br>
								    <input  type="file" id="idconshab" name="docph" disabled>
								     <input  type="text"  id="idconshab1"  name="docph1"  class="no">
								  
								 </div>
								 <div class="form-group col-sm-12 req">
								    <label  for="idcopiadni">DNI</label>
								    <label class="dcoAnti" id="doc5"></label><br>
								    <input  type="file" id="idcopiadni" name="docdni" disabled>
								     <input  type="text"  id="idcopiadni1"  name="docdni1"  class="no" >
								 </div>	
								 <br>
						      </div>
						    </div>
						  </div>
						</div>
					<div class="row">
						<div class="col-sm-12">
							
							<button type="button" class="btn btn-primary1 float-right boton1" id="btnLimpiar" disabled>
							<i class="fas fa-broom"></i><span class="boton">Limpiar</span></button>
							<button type="submit" class="btn btn-primary float-right boton1" id="btnRegistrar" disabled>
							<i class="fas fa-save"></i><span class="boton">Registrar</span></button>
							
						</div>
					</div>
					</form>
				</div>
			</div>	
		</div>
	</div>
	<br>
	<div class="row">
		<div class="col-sm-12">
			<div class="card ">
			<h5 class="card-header  font-weight-bold">Lista de Solicitudes</h5>
			<div class="card-body">
				<div class="row">
					<div class="table-responsive">
						<table class="table table-bordered" id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>Código</th>
									<th>Nombres</th>
									<th>Apellidos</th>
									<th>DNI</th>
									<th>Fecha</th>
									<th>Hora</th>
									
									<th class="ult-col"></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${requestScope.solicitudes}" var="bean">
					            <tr>
					                <td>${bean.codSolicitud}</td>
					                <td>${bean.nombres}</td>
					                <td>${bean.apePat} ${bean.apeMat}</td>
					                <td>${bean.dni}</td>
					                <td>${bean.fecha}</td>
					                <td>${bean.hora}</td>
					                <td class="d-flex align-items-center justify-content-center">
										<button type="button" class="btn btn-success btn-tabla" data-toggle="modal" data-target="#modalVer" id="btnVer">
										<i class="fas fa-eye"></i></button>
										<button type="button" class="btn btn-info btn-tabla" data-toggle="modal" id="btnEditar" >
										<i class="fas fa-edit"></i></button>
										<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#modalEliminar" id="btnEliminar">
										<i class="fas fa-trash-alt"></i></button>
	                				</td>
					            </tr>
				            </c:forEach>
				           
							</tbody>
						</table>
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal Buscar-->
	<div class="modal fade" id="modalBuscar" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog  modal-dialog-centered modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Buscar Solicitante</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="padding-top: 0px;">			
					<div class="row">
								<div class="card-body">
									<div class="row">
									<div class="table-responsive">
										<table class="table table-bordered" id="dataTable2" width="100%"
											cellspacing="0">
											<thead>
												<tr>
													<th>Código</th>
													<th>Nombre</th>
													<th>Apellido</th>
													<th>DNI</th>
													<th style="display:none;"></th>
													<th style="display:none;"></th>
													<th></th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${requestScope.solicitantes}" var="soli">
									            <tr>
									                <td >${soli.id}</td>
									                <td>${soli.nom}</td>
									                <td>${soli.apePat} ${soli.apeMat}</td>
									                <td>${soli.dni}</td>
									                <td style="display:none;" >${soli.dir}</td>
									                <td style="display:none;"><img src="data:image/jpg;Base64,${soli.fotoString}" class="img-fluid rounded"  ></td>
									                <td class="d-flex align-items-center justify-content-center">
													<button type="button" class="btn btn-success " data-toggle="modal" id="btnAgregar"
													data-dismiss="modal" aria-label="Close">
													<i class="fas fa-file-medical"></i></button></td>
									            </tr>
								            </c:forEach>
											</tbody>
										</table>
									</div>
									</div>
								</div>
					</div>		
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal Ver detalle-->
	<div class="modal fade" id="modalVer" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog  modal-dialog-centered modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Detalle de Solicitud N° <span id="detalle11" ></span></h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
									
				<div class="modal-body" style="padding-top: 0px;">			
					<div class="row">
								<div class="card-body">
									<div class="row card1">
									<div class="col-sm-8">  
									<dl class="row">
									<dt class="col-sm-12">Datos del Solicitante:</dt>
									  <dt class="col-sm-3">
										<img alt="" class="img-fluid rounded" src="https://png.pngtree.com/png-vector/20191026/
										ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1870181.jpg " id="detalle12">
						        	
										</dt>
									  <dd class="col-sm-9">
										  <dl class="row">
										  <dt class="col-sm-3">Código:</dt>
										  <dd class="col-sm-9" id="detalle1"></dd>
										  
										  <dt class="col-sm-3">Nombres:</dt>
										  <dd class="col-sm-9" id="detalle2"></dd>
										  
										  <dt class="col-sm-3">Apellidos:</dt>
										  <dd class="col-sm-9" id="detalle3"></dd>
										  
										  <dt class="col-sm-3">DNI:</dt>
										  <dd class="col-sm-9" id="detalle4"></dd>
										  
										  <dt class="col-sm-3">Dirección:</dt>
										  <dd class="col-sm-9" id="detalle5"></dd>
									  
										  </dl> 
									 </dd>
									  
									  <br><br>
									  <dt class="col-sm-12">Requisitos:</dt>
									  <dt class="col-sm-1"></dt>
									  <dd class="col-sm-11">
										  <dl class="row">
										  <dt class="col-sm-12">Declaración Jurada:</dt>
										  <dd class="col-sm-12" id="detalle6"></dd>
										  
										  <dt class="col-sm-12">Resolución de Contrato:</dt>
										  <dd class="col-sm-12" id="detalle7"></dd>
										  
										  <dt class="col-sm-12">Resolución de Quinquenio:</dt>
										  <dd class="col-sm-12" id="detalle8"></dd>
										  
										  <dt class="col-sm-12">Constancia de Pagos de Haberes:</dt>
										  <dd class="col-sm-12" id="detalle9"></dd>
										  
										  <dt class="col-sm-12">DNI:</dt>
										  <dd class="col-sm-12" id="detalle10"></dd>
										  </dl> 
									 </dd>
									 </dl> 		
									</div>
									<div class="col-sm-3">  
										<div class="row ">
										<dd class="col-sm-12">Actualmente en:</dd>
										<button type="button" class="btn btn-danger " id="area" readonly>
										</button>
										</div>
										<br><br>
										<div class="row">
										Registrado por el usuario:
										<br>
										<button type="button" class="btn btn-danger" readonly id="usuario">
										</button>
										</div>
									</div>
									
						        	</div>
									</div>
								</div>
					</div>		
				</div>
			</div>
		</div>
	
	
	<!-- Modal Eliminar-->
	<div class="modal fade" id="modalEliminar" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog  modal-dialog-centered modal-lg">
			<div class="modal-content">
							
				<div class="modal-body" style="padding-top: 7%;">			
				<form action="ServletSolicitud?accion=ELIMINAR" method="post">
	        	<input type="hidden" id="idEliminar" name="codigoEliminar">
	        	<h4>¿Seguro que deseas eliminar Solicitud de Compensación?</h4>
	        	 <div class="modal-footer">
	        	 	<button type="submit" class="btn btn-primary">Sí</button>
			        <button type="button" class="btn btn-primary1" data-dismiss="modal">No</button>
	      		 </div>
	        </form>
					</div>		
				</div>
			</div>
		</div>
</main>
	
<br>
	<script type="text/javascript" src="js/webcam.js"></script>
	<jsp:include page="PrincipalFinal.jsp"/>
	<script type="text/javascript" src="js/solicitud.js" charset="UTF-8"></script>

</body>
</html>