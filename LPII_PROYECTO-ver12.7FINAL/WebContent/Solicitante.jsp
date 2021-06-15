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
					<h5 class="card-header  font-weight-bold">Solicitante : <span id="mosCodigo" class="${requestScope.codigo}">${requestScope.codigo}</span></h5>
					<div class="card-body">
					<form id="idRegistrarSolicitud" data-toggle="validator" role="form" method="post" action="ServletSolicitante?accion=GUARDAR">
					<input name="codiSolicitud" style="display: none;" value="${requestScope.codigo}" id="codReg" ></input>
					<input name="codiSolicitudModi" style="display: none;"  id="codModi" ></input>
					<input type="hidden" name="idUsuario" value="${sesion.codUsuario}">
						<div class="row">
						<div class="col-sm-8"> 	
							<div class="row">
								<div class="col-sm-12">
									<!--<div class="form-group col-sm-4">
										<label for="idcodigo">CÛdigo</label>
	    								<input type="text" class="form-control" id="idcodigo" placeholder="">
									</div>-->
								</div>
							</div>
							<div class="row">						
								<div class="col-sm-6">
									<div class="form-group col-sm-12">
										<label for="idnombres">Nombres</label>
		    							<input type="text" class="form-control" id="idnombres" placeholder="" name="nombres">
									</div>	
									<div class="form-group col-sm-12">
										<label for="	idapellidos">Apellido Paterno</label>
		    							<input type="text" class="form-control" id="idapepaterno" placeholder="" name="apePat">
									</div>
									<div class="form-group col-sm-12">
										<label for="	idapellidos">Apellido Materno</label>
		    							<input type="text" class="form-control" id="idapematerno" placeholder="" name="apeMat">
									</div>	
									<div class="form-group col-sm-12">
										<label for="iddni">DNI</label>
		    							<input type="text" class="form-control" id="iddni" placeholder="" name="dni">
									</div>
									<div class="form-group col-sm-12">
										<label for="idtelefono">TelÈfono</label>
		    							<input type="text" class="form-control" id="idtelefono" placeholder="" name="telefono">
									</div>
								</div>
								
								<div class="col-sm-6">
									<div class="form-group col-sm-12">
										<label for="idcorreo">Correo</label>
		    							<input type="text" class="form-control" id="idcorreo" placeholder="" name="correo">
									</div>
									<div class="form-group col-sm-12">
										<label for="iddni">DirecciÛn</label>
		    							<input type="text" class="form-control" id="iddireccion" placeholder="" name="direccion">
									</div>
									<div class="form-group col-sm-12	">
						   	 			<label for="exampleInputEmail1" >Departamento</label>
						    			<select class="form-control" name="departamento" id="iddepartamento" name="departamento">
										<option value=" ">[Seleccione]</option>
										</select>
						  			</div>
						  			<div class="form-group col-sm-12	">
						   	 			<label for="exampleInputEmail1" >Provincia</label>
						    			<select class="form-control" name="provincia" id="idprovincia" name="provincia">
										<option value=" ">[Seleccione]</option>
										</select>
						  			</div>
						  			<div class="form-group col-sm-12">
						   	 			<label for="exampleInputEmail1" >Distrito</label>
						    			<select class="form-control" name="distrito" id="idDistrito" name="distrito">
										<option value=" ">[Seleccione]</option>
										</select>
						  			</div>
						  					  			
								</div>
									
							</div>
						</div>
						<div class="col-sm-4 d-flex align-items-center justify-content-center">
						<div class="row">	
							<div class="col-sm-12 d-flex align-items-center justify-content-center">	
						 		<div id="div_alpha" class="img-fluid p-4"></div>						 	
                        	</div>	
                        	<div  class="col-sm-12 d-flex align-items-center justify-content-center">	
							 	<a href="#" class="btn btn-danger1 p-3  d-flex align-items-center justify-content-center" 
							 	id="alpha" data-toggle="modal"  data-target="#modalFoto" 
	                            onclick="takeSnapshot(this)"><i class="fas fa-camera" ></i><span class="boton">Tomar Foto</span>
	                            </a>
	                                                       
                        	</div>
                        	<div  class="col-sm-12 d-flex align-items-center justify-content-center">
                        		<div class="form-group ">
						   	 		<input type="text" style="padding-top: 0px;width: 0px;height: 0px;padding-left: 0px;
						   	 		 padding-right: 0px;padding-bottom: 0px;border-right-width: 0px;border-bottom-width: 0px;
						   	 		 border-left-width: 0px;border-top-width: 0px;" class="form-control"  id="dataurl" placeholder="" name="dataurl" value="" readonly >		
						  		</div>	
						  	</div>
                       </div>
						</div>		
					</div>
					<div class="row">
						<div class="col-sm-12">
							<button type="button" class="btn btn-primary1 float-right boton1" id="btnLimpiar" >
							<i class="fas fa-broom"></i><span class="boton">Limpiar</span></button>
							<button type="submit" class="btn btn-primary float-right boton1" id="btnRegistrar" >
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
					<h5 class="card-header  font-weight-bold">Lista de Solicitantes</h5>
			<div class="card-body">
					<div class="row">
					<div class="table-responsive">
						<table class="table table-bordered " id="dataTable" width="100%"
							cellspacing="0">
							<thead>
								<tr>
									<th>CÛdigo</th>
									<th>Nombres</th>
									<th>Apellidos</th>
									<th>Dni</th>
									<th>DirecciÛn</th>
									<th>TelÈfono</th>
									<th>Correo</th>
									<th>Fecha</th>
									<th class="ult-col"></th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${requestScope.listaSolicitantes}" var="bean">
								<tr>
									<td>${bean.id}</td>
									<td>${bean.nom}</td>
									<td>${bean.apePat} ${bean.apeMat}</td>
									<td>${bean.dni}</td>
									<td>${bean.dir}</td>
									<td>${bean.tel}</td>
									<td>${bean.correo}</td>
									<td>${bean.fecha}</td>
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
	
	<!-- Modal Ver detalle-->
	<div class="modal fade" id="modalVer" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog  modal-dialog-centered  modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Detalle de Usuario</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="padding-top: 0px;">			
					<div class="row">
								<div class="card-body">
									<div class="row card1">
									<div class="col-sm-6 ">  
									<dl class="row"  >
									  <dt class="col-sm-3">CÛdigo:</dt>
									  <dd class="col-sm-9" id="det1"></dd>
									  
									  <dt class="col-sm-3">Nombres:</dt>
									  <dd class="col-sm-9" id="det2"></dd>
									  
									  <dt class="col-sm-3">Apellidos:</dt>
									  <dd class="col-sm-9" id="det3"></dd>
									  
									  <dt class="col-sm-3">DNI:</dt>
									  <dd class="col-sm-9" id="det4"></dd>
									  
									  <dt class="col-sm-3">DirecciÛn:</dt>
									  <dd class="col-sm-9" id="det5"></dd>
									  
									  <dt class="col-sm-3"></dt>
									  <dd class="col-sm-9" id="det6">"Departamento"/"Provincia"/"Distrito"</dd>
									  
									  <dt class="col-sm-3">TelÈfono:</dt>
									  <dd class="col-sm-9" id="det7"></dd>
									  
									  <dt class="col-sm-3">Correo:</dt>
									  <dd class="col-sm-9" id="det8"></dd>
									  
									  <dt class="col-sm-3">Fecha:</dt>
									  <dd class="col-sm-9" id="det9"></dd>
						
									 </dl> 		
									</div>
									<div class="img-fluid d-flex align-items-center justify-content-center col-sm-6 ">
										<img id="fotoDetalle" class="rounded" alt="" src=""   width="300px" height="250px" />
									</div>
						        	</div>
									</div>
								</div>
					</div>		
				</div>
			</div>
		</div>
		
	<!-- Modal Foto-->
	<div class="modal fade" id="modalFoto" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog  modal-dialog-centered modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Capturar Foto</h5>
					
				</div>
				<div class="modal-body" style="padding-top: 0px;">					
			            <div id="camera" style="height:auto;text-align:center;margin:0 auto;"></div>
			
			            <p class="d-flex align-items-center justify-content-center" >
			                <input type="button" value="CAPTURAR"  class="btn-success btn-tabla rounded p-2"  id="btAddPicture" 
			                    onclick="addCamPicture()"   data-dismiss="modal" aria-label="Close" />
			                <input type="button" value="CANCELAR"  class="btn-info btn-tabla rounded p-2" 
			                    onclick="closeCam()"  data-dismiss="modal" aria-label="Close" />
			            </p>
			            <input type="hidden" id="rowid" />            
			
			    </div>
					</div>		
				</div>
			</div>
			
	<div class="modal fade" id="modalEliminar" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog  modal-dialog-centered modal-lg">
			<div class="modal-content">
							
				<div class="modal-body" style="padding-top: 7%;">			
				<form action="ServletSolicitante?accion=ELIMINAR" method="post">
	        	<input type="hidden" id="idEliminar" name="codigoEliminar">
	        	<h4>øSeguro que deseas eliminar Solicitante?</h4>
	        	 <div class="modal-footer">
	        	 	<button type="submit" class="btn btn-primary">SÌ</button>
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
	<script type="text/javascript">
	
	$(document).ready(function() {
		cargarDepartamento();
		
	} );
	
	function cargarDepartamento(){
		/*La clase getJSON de jquery permite leer un JSON tiene de parametros:
			-nombre del Servlet a invocar
			-son parametros que necesitan que el Servelt para que funcione
			-variable que almacena lo que retorna el Servlet*/
		$.getJSON("ServletDepartamentoJSON",{},function(response){
			//bucle para realizar recorrido sobre response
			$("#iddepartamento").html("<option id='select' value='seleccione'>[Seleccione]</option>");
			$("#idprovincia").html("<option>[Seleccione]</option>");
			$("#idDistrito").html("<option>[Seleccione]</option>");
			$.each(response,function(index, item){
				$("#iddepartamento").append("<option value='"+item.codigo+"'>"+item.nomDepartamento+"</option>")
			});
		});
		

	        //evento change parececido al onchange de javascript que cambia al seleccionar los Departamentos
	    $("#iddepartamento").change(function(){
	        //Observese que la respuesta viene del servlet ServletDepartamentoJSON
	        	if($("#select").val()!=$(this).val()){
	        		$.getJSON("ServletProvinciaJSON",{codDepartamento: $(this).val()},function(response){	
	        			
		        		$("#idprovincia").html("<option id='selectProvincia' value='seleccioneProvincia'>[Seleccione]</option>");
		        		$("#idDistrito").html("<option>[Seleccione]</option>");
			            $.each(response, function(index,item){	
			                $("#idprovincia").append("<option value='"+item.codProv+"'>"+item.nomProv+"</option>");
			            });		 
		        	});
	        		
	        	}else{
	  	        		$("#idprovincia").html("<option>[Seleccione]</option>");
	  	        		$("#idDistrito").html("<option>[Seleccione]</option>");
		        }
	    	});
	
		

	        //evento change parececido al onchange de javascript que cambia al seleccionar los Provincia
	    $("#idprovincia").change(function(){
	        //Observese que la respuesta viene del servlet ServletProvinciaJSON
	        	if($("#selectProvincia").val()!=$(this).val()){
	        		$.getJSON("ServletDistritoJSON",{codProvincia: $(this).val()},function(response){	
	        			
		        		$("#idDistrito").html("<option>[Seleccione]</option>");
			            $.each(response, function(index,item){	
			                $("#idDistrito").append("<option value='"+item.codDistrito+"'>"+item.nomDistrito+"</option>");
			            });		 
		        	});
	        		
	        	}else{
	  	        		$("#idDistrito").html("<option>[Seleccione]</option>");
		        }
	    	});
	
	}
	
	$(document).ready(function(){     
	     $('#idRegistrarSolicitud').bootstrapValidator({ 
	    	 fields:{
	    		
	    		 
	    		 	nombres:{
	
	    		 		validators:{
	    		 			notEmpty:{
									message:'Campo Nombre de 3 a 30 letras es obligatorio',
	    		 			},
							regexp:{
	     			 			regexp:/^([a-zA-Z\\Ò·ÈÌÛ˙¡…Õ”⁄—\s]{3,30})$/,
	     			 			message:'Campo Nombre de 3 a 30 letras'
	        		 		}
	    		 		}
	    		 	},
	    		 	apePat:{
	    		 		validators:{
	    		 			notEmpty:{
								message:'Apellido Paterno de 3 a 30 letras es obligatorio',
   		 			},
						regexp:{
    			 			regexp:/^([a-zA-Z\\Ò·ÈÌÛ˙¡…Õ”⁄—\s]{3,30})$/,
    			 			message:'Apellido Paterno de 3 a 30 letras'
       		 		}
	    		 		}
	    		 	},
	    		 	apeMat:{
	    		 		validators:{
	    		 			notEmpty:{
								message:'Apellido Materno de 3 a 30 letras es obligatorio',
   		 			},
						regexp:{
    			 			regexp:/^([a-zA-Z\\Ò·ÈÌÛ˙¡…Õ”⁄—\s]{3,30})$/,
    			 			message:'Apellido Materno de 3 a 30 letras'
       		 		}
	    		 		}
	    		 	},
	    		 	dni:{
	    		 		validators:{
	     			 		notEmpty:{
	     			 			message:'DNI es obligatorio '	
	     			 		},
							regexp:{
	       					 	regexp:/^(\d{8})$/,
	       					 	message:'DNI debe tener 8 digitos'
	       				 }
	    		 		}
	    		 	},
	    		 	telefono:{
	    		 		validators:{
	     			 		notEmpty:{
	     			 			message:'Telefono es obligatorio '	
	     			 		},
							regexp:{
	       					 	regexp:/^(\d{9})$/,
	       					 	message:'Telefono debe tener 9 digitos'
	       				 }
	    		 		}
	    		 	},
	    		 	correo:{
	    		 		validators:{
	    		 			notEmpty:{
								
	    		 				message:'Correo es obligatorio'
	    		 			},
	    		 			emailAddress:{
	        		 				
	        		 				message:'El correo no tiene un formato v·lido'
	        		 			}
	    		 		}
	    		 	},
	    		 	direccion:{
	    		 		validators:{
	    		 			notEmpty:{
	    		 				message:'DirecciÛn es obligatorio'
	    		 			},
							regexp:{
								regexp:/^([a-zA-Z\\Ò·ÈÌÛ˙¡…Õ”⁄—\0-9∞._:-\s@]{5,100})$/,
	     			 			message:'DirecciÛn de 5 a 100 letras o n˙meros'
	     			 		}
	    		 		}
	    		 	},

	    		 	departamento:{
	    		 		validators:{
	    		 			digits:{
	    		 			
	    		 				message:'Seleccione un Departamento'
	    		 			}
	    		 		}
	    		 	},
	    		 	
	    		 	provincia:{
	    		 		validators:{
	    		 			digits:{
		    		 			
	    		 				message:'Seleccione una Provincia'
	    		 			}
	    		 		}
	    		 	},
	    		 	
	    		 	distrito:{
	    		 		validators:{
	    		 			digits:{
		    		 			
	    		 				message:'Seleccione un Distrito'
	    		 			}
	    		 		}
	    		 	},
	    		 	dataurl:{
	    		 		validators:{   
	    		 			notEmpty:{
								
	    		 				message:' La foto es obligatoria'
	    		 			},
	    		 		}
	    		 	},
    		 	
	    	 }
	    });  
	});
	
	//Eliminar Usuario
	$(document).on("click","#btnEliminar",function(){
	var cod;
	cod=$(this).parents("tr").find("td")[0].innerHTML;
	$("#idEliminar").val(cod);
	
	})
	
			//Limpiar formulario
	$(document).on("click","#btnLimpiar",function(){
		$("#idRegistrarSolicitud").trigger('reset');
		$("#idRegistrarSolicitud").data('bootstrapValidator').resetForm();
		$("#foto").remove();
		$('#idprovincia option').remove();
		$('#idDistrito option').remove();
		$("#idprovincia").html("<option id='selectProvincia' value='seleccioneProvincia'>[Seleccione]</option>");
		$("#idDistrito").html("<option>[Seleccione]</option>");
		
		var name = $("#mosCodigo").attr("class");
		$("#mosCodigo").text(name);
		
	})
	
		$(document).on("click","#btnEditar",function(){
	
			$('#idprovincia option').remove();
			$('#idDistrito option').remove();
	

			var codSolic;
			codSolic=$(this).parents("tr").find("td")[0].innerHTML;
			
			$.getJSON("ServletSolicitanteJSON",{codSolicitante:codSolic},function(response){
				
			
			$("#mosCodigo").text(codSolic);
			$("#idnombres").val(response.nom);
			$("#idapepaterno").val(response.apePat);
			$("#idapematerno").val(response.apeMat);
			$("#iddni").val(response.dni);
			$("#idtelefono").val(response.tel);
			$("#idcorreo").val(response.correo);
			$("#iddireccion").val(response.dir);
			$("#iddepartamento").val(response.cod_dep);
			
			$.getJSON("ServletProvinciaJSON",{codDepartamento:response.cod_dep},function(listaProvincias){	
				$("#idprovincia").html("<option id='selectProvincia' value='seleccioneProvincia'>[Seleccione]</option>");
	            $.each(listaProvincias, function(index,item){		          
	           		$("#idprovincia").append("<option value='"+item.codProv+"'>"+item.nomProv+"</option>");
	            });	  
	            
	            $("#idprovincia").val(response.cod_prov);
	            
        	});	
			
			$.getJSON("ServletDistritoJSON",{codProvincia: response.cod_prov},function(listaDistritos){	
				$("#idDistrito").html("<option>[Seleccione]</option>");
	            $.each(listaDistritos, function(index,item){	
	                $("#idDistrito").append("<option value='"+item.codDistrito+"'>"+item.nomDistrito+"</option>");
	            });
	            
	            $("#idDistrito").val(response.codDist);
				
        	});
			
			document.getElementById('div_alpha').innerHTML = '<img class="rounded" id="foto" src=""  width="300px" height="200px" />';
	        $("#foto").attr('src', 'data:image/jpg;Base64,'+response.fotoString);
	
	        var base64image = document.getElementById("foto").src;
            document.getElementById('dataurl').value = base64image;
	        
	      //Genera evento de teclado en el input "dataurl" para que el boostrap validator lo tome como casilla llena.
            var e = document.createEvent("KeyboardEvent"); 
            if (e.initKeyboardEvent) { // Chrome, IE 
                e.initKeyboardEvent("keyup", true, true, document.defaultView, "Enter", 0, "", false, ""); 
            } else { // FireFox 
                e.initKeyEvent("keyup", true, true, document.defaultView, false, false, false, false, 13, 0); 
            } 
            document.getElementById("dataurl").dispatchEvent(e);
			
            $("#codReg").val(0);
            $("#codModi").val(codSolic);
            
		})
	})
	
	$(document).on("click","#btnVer",function(){
		
		var codSolic;
		codSolic=$(this).parents("tr").find("td")[0].innerHTML;
		
		$.getJSON("ServletSolicitanteJSON",{codSolicitante:codSolic},function(response){
	
				$("#det1").text(codSolic);
				$("#det2").text(response.nom);
				$("#det3").text(response.apePat+" "+response.apeMat);
				$("#det4").text(response.dni);
				$("#det5").text(response.dir);
				$("#det6").text(response.nomDepartamento +" / "+response.nomProvincia+" / "+response.nomDistrito);
				$("#det7").text(response.tel);
				$("#det8").text(response.correo);
				$("#det9").text(response.fecha);
				$("#fotoDetalle").attr('src', 'data:image/jpg;Base64,'+response.fotoString);
				
		})
		
	})
	
	
	</script>
	
	
</body>
</html>

