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
	<c:if test="${requestScope.MENSAJE!=null}">
			<div class="alert alert-light alert-dismissible fade show" role="alert">
			  <strong>MENSAJE </strong><i class="fas fa-exclamation-circle alerta"></i>
			  <h6>${requestScope.MENSAJE}</h6>
			 
			</div>
	</c:if>
<div class="row">
	<div class="col-sm-12">
		<div class="card ">
			<h5 class="card-header  font-weight-bold">Informe Legal N° <span id="mosCodigo"
			 class="${requestScope.codigo}" >${requestScope.codigo}</span></h5>
			<div class="card-body">
				<form id="idRegistrar" data-toggle="validator" role="form" method="post" action="ServletInfLegal?accion=GUARDAR">
				<input name="codiIL" style="display: none;" value="${requestScope.codigo}" id="codReg" ></input>
					<input name="codiILModi" style="display: none;"  id="codModi" ></input>
					<input name="codiUsuario" id="idUsuario" style="display: none;" value="${sesion.codUsuario}" ></input>
					<div class="row">
						<div class="form-group col-sm-2">
							<label for="idcodigo">Código de Expediente</label>
						</div>
						<div class="form-group col-sm-5">
							<div class="input-group mb-3">
								<input type="text" class="form-control " id="idcodigoex" name="codiExp"
									placeholder=""  readonly>
								<div class="input-group-append">
									<button class="btn btn-outline-secondary " type="button"
										data-toggle="modal" data-target="#modalBuscar"
										id="btnBuscar">
										<span class="fas fa-search"></span>
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title col-sm-12">Datos del Solicitante</h5>
									<hr>
									<div class="row">
										<div class="col-sm-2 d-flex align-items-center justify-content-center" >
						        		<img alt="" class="img-fluid rounded"
						        		 src="https://png.pngtree.com/png-vector/20191026/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1870181.jpg " 
						        		 id="idimg"></div>
										<div class="col-sm-10">
											<div class="row">
												<div class="col-sm-6">
													<div class="form-group col-sm-12">
														<label for="idcodigo">Código</label> <input type="text"
															class="form-control" id="idcodigo" readonly>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-6">
													<div class="form-group col-sm-12">
														<label for="idnombres">Nombres</label> <input type="text"
															class="form-control" id="idnombres" readonly>
													</div>
												</div>
												<div class="col-sm-6">
													<div class="form-group col-sm-12">
														<label for="idapellidos">Apellidos</label> <input
															type="text" class="form-control" id="idapellidos" readonly>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-6">
													<div class="form-group col-sm-12">
														<label for="iddni">DNI</label> <input type="text"
															class="form-control" id="iddni" readonly>
													</div>
												</div>
												<div class="col-sm-6">
													<div class="form-group col-sm-12">
														<label for="iddireccion">Dirección</label> <input
															type="text" class="form-control" id="iddireccion" readonly>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

					<br>
					<div class="row">
						<div class="col-sm-12">
							<div class="card">
								<div class="card-body">
									<h5 class="card-title col-sm-12">Descripción del Informe Legal</h5>
									<hr>
									<div class="row">
										<div class="col-sm-6">
											<div class="row">
												<div class="col-sm-12">
													<label for="idantecedentes">Antecedentes</label>
												</div>
												<div class="col-sm-12">
													<textarea class="form-control" id="idantecedentes" rows="3" name="antecedentes" disabled></textarea>
												</div>
											</div><br>
											<div class="row">
												<div class="col-sm-12">
													<label for="idpedido">Pedido</label>
												</div>
												<div class="col-sm-12">
													<textarea class="form-control" id="idpedido" rows="3" name="pedido" disabled></textarea>
												</div>
											</div><br>
											<div class="row">
												<div class="col-sm-12">
													<label for="idbaselegal">Base Legal</label>
												</div>
												<div class="col-sm-12">
													<textarea class="form-control" id="idbaselegal" rows="3" name="baselegal" disabled></textarea>
												</div>
											</div>
										</div>

										<div class="col-sm-6">
											<div class="row">
												<div class="col-sm-12">
													<label for="idexposicion">Exposición y Análisis de los Hechos</label>
												</div>
												<div class="col-sm-12">
													<textarea class="form-control" id="idexposicion" rows="3" name="exposicion" disabled></textarea>
												</div>
											</div><br>
											<div class="row">
												<div class="col-sm-12">
													<label for="idconclusiones">Conclusiones</label>
												</div>
												<div class="col-sm-12">
													<textarea class="form-control" id="idconclusiones" rows="3" name="conclusiones" disabled></textarea>
												</div>
											</div><br>
											<div class="row">
												<div class="col-sm-12">
													<label for="idsugerencias">Sugerencias</label>
												</div>
												<div class="col-sm-12">
													<textarea class="form-control" id="idsugerencias" rows="3" name="sugerencias" disabled>No tiene</textarea>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<br>
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
			<h5 class="card-header  font-weight-bold">Lista de Informes Legales</h5>
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
									<th style="display:none;"></th>
									<th class="ult-col"></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${requestScope.inflegal}" var="bean">
								<tr>
									 <td id="codEditar">${bean.codigo}</td>
					                <td>${bean.nombre}</td>
					                <td>${bean.apePaterno} ${bean.apeMaterno}</td>
					                <td>${bean.dni}</td>
					                <td>${bean.fecha}</td>
					                <td>${bean.hora}</td>
					                <td style="display:none;" >${bean.codExp}</td>
									<td class="d-flex align-items-center justify-content-center">
										<button type="button" class="btn btn-success btn-tabla" data-toggle="modal" data-target="#modalVer" id="btnVer">
										<i class="fas fa-eye"></i></button>
										<button type="button" class="btn btn-info btn-tabla" data-toggle="modal" id="btnEditar">
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
				<h5 class="modal-title" id="exampleModalLabel">Buscar Expediente</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
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
											<th style="display:none;"></th>
											<th></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${requestScope.solicitantes}" var="bean">
								            <tr>
								                <td >${bean.codigoSoli}</td>
								                <td>${bean.nombre}</td>
								                <td>${bean.apePaterno} ${bean.apeMaterno}</td>
								                <td>${bean.dni}</td>
								                <td style="display:none;" >${bean.direccion}</td>
								                <td style="display:none;"><img src="data:image/jpg;Base64,${bean.foto}" class="img-fluid rounded"  ></td>
								                <td style="display:none;" >${bean.codExp}</td>
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
					<h5 class="modal-title" id="exampleModalLabel">Detalle de Informe Legal N° <span id="detalle11" ></span></h5>
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
									  <dt class="col-sm-3">	<img alt="" class="img-fluid rounded" src="https://png.pngtree.com/png-vector/20191026/
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
									  <dt class="col-sm-12">Descripción del Informe Legal:</dt>
									  <dt class="col-sm-2"></dt>
									  <dd class="col-sm-10">
										  <dl class="row">
										  <dt class="col-sm-10">Antecedentes:</dt>
										  <dd class="col-sm-12" id="detalle6"></dd>
										  
										  <dt class="col-sm-10">Pedido:</dt>
										 <dd class="col-sm-12" id="detalle7"></dd>
										  
										  <dt class="col-sm-10">Base Legal:</dt>
										  <dd class="col-sm-12" id="detalle8"></dd>
										  
										  <dt class="col-sm-10">Exposición y Análisis de los Hechos:</dt>
										 <dd class="col-sm-12" id="detalle9"></dd>
										  
										  <dt class="col-sm-10">Conclusiones:</dt>
										  <dd class="col-sm-12" id="detalle10"></dd>
										  
										   <dt class="col-sm-10">Sugerencias:</dt>
										  <dd class="col-sm-12" id="detalle13"></dd>
										  </dl> 
									 </dd>
									 </dl> 		
									</div>
									<div class="col-sm-4">  
										<div class="row">
										<dd class="col-sm-12">Actualmente en:</dd>
										<br>
										<button type="button" class="btn btn-danger" id="area" readonly>
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
				<form action="ServletInfLegal?accion=ELIMINAR" method="post">
	        	<input type="hidden" id="idEliminar" name="codigoEliminar">
	        	<input type="hidden" id="idExpe" name="codigoExpe">
	        	<h4>¿Seguro que deseas eliminar Informe Legal?</h4>
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

<br><jsp:include page="PrincipalFinal.jsp" />
<script type="text/javascript" src="js/infoLegal.js" charset="UTF-8"></script>
</body>
</html>