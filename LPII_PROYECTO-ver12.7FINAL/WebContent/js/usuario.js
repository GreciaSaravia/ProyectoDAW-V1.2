
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
	
		
		//Cargar Areas
		$.getJSON("ServletAreaJSON",{},function(response){
			//bucle para realizar recorrido sobre response
			$("#idarea").html("<option id='selectArea' value='seleccioneArea'>[Seleccione]</option>");
			$.each(response,function(index, item){
				$("#idarea").append("<option value='"+item.codArea+"'>"+item.nomArea+"</option>")
			});
		});
		$(function(){
	        //evento change parececido al onchange de javascript que cambia al seleccionar las Areas
	    $("#idarea").change(function(){
	        //Observese que la respuesta viene del servlet ServletDepartamentoJSON
	        	if($("#selectArea").val()!=$(this).val()){
	        		$.getJSON("ServletCargoJSON",{codArea: $(this).val()},function(response){	
	        			
		        		$("#idcargo").val(response.nomCargo);
		        			 
		        	});
	        		
	        	}else{
	        		$("#idcargo").val("");
		        }
	    	});
		});
		
		//Cargar Perfil
		$.getJSON("ServletPerfilJSON",{},function(response){
			//bucle para realizar recorrido sobre response
			$("#idperfil").html("<option >[Seleccione]</option>");
			$.each(response,function(index, item){
				$("#idperfil").append("<option value='"+item.codPerfil+"'>"+item.nomPerfil+"</option>")
			});
		});
		
	}
	
	$(document).ready(function(){     
	     $('#idRegistrarUsuario').bootstrapValidator({ 
	    	 fields:{
	    		
	    		 
	    		 	nombres:{
	
	    		 		validators:{
	    		 			notEmpty:{
									message:'Campo Nombre de 3 a 30 letras es obligatorio',
	    		 			},
							regexp:{
	     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\s]{3,30})$/,
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
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\s]{3,30})$/,
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
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\s]{3,30})$/,
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
	     			 			message:'Teléfono es obligatorio '	
	     			 		},
							regexp:{
	       					 	regexp:/^(\d{9})$/,
	       					 	message:'Teléfono debe tener 9 digitos'
	       				 }
	    		 		}
	    		 	},
	    		 	correo:{
	    		 		validators:{
	    		 			notEmpty:{
								
	    		 				message:'Correo es obligatorio'
	    		 			},
	    		 			emailAddress:{
	        		 				
	        		 				message:'El correo no tiene un formato válido'
	        		 			}
	    		 		}
	    		 	},
	    		 	direccion:{
	    		 		validators:{
	    		 			notEmpty:{
	    		 				message:'Dirección es obligatorio'
	    		 			},
							regexp:{
								regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{5,100})$/,
	     			 			message:'Dirección de 5 a 100 letras o números'
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
	    		 	area:{
	    		 		validators:{
	    		 			digits:{
		    		 			
	    		 				message:'Seleccione un Área'
	    		 			}
	    		 		}
	    		 	},

	    		 	usuario:{
	    		 		validators:{
	    		 			notEmpty:{
								
	    		 				message:'Usuario es obligatorio'
	    		 			},
						regexp:{
	     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\s]{5,10})$/,
	     			 			message:'Campo Usuario de 5 a 10 letras'
	        		 			}
	    		 		}
	    		 	},  		 	
	    		 	contraseña:{
	    		 		validators:{
	    		 			notEmpty:{
								
	    		 				message:'Contraseña es obligatoria'
	    		 			},
						regexp:{
									regexp:/^([A-Za-z0-9°._:-\s@]{5,20})$/,
									message:'Contraseña de 5 a 20 letras o números'
	        		 			}
	    		 		}
	    		 	},
	    		 	perfil:{
	    		 		validators:{
	    		 			digits:{
		    		 			
	    		 				message:'Seleccione un Perfil'
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
		$("#idRegistrarUsuario").trigger('reset');
		$("#idRegistrarUsuario").data('bootstrapValidator').resetForm();
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
	

			var codUse;
			codUse=$(this).parents("tr").find("td")[0].innerHTML;
			
			$.getJSON("ServletUsuarioJSON",{codUsuario:codUse},function(response){
				
			
			$("#mosCodigo").text(codUse);
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
	            
	            $("#idDistrito").val(response.cod_dist);
				
        	});
	
			$("#idarea").val(response.cod_area);
			
			$.getJSON("ServletCargoJSON",{codArea: response.cod_area},function(cargo){	
    			
        		$("#idcargo").val(cargo.nomCargo);
        			 
        	});

			$("#idusuario").val(response.cuenta);
			$("#idcontrasena").val(response.contraseña);		
			$("#idperfil").val(response.id_perfil);
			
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
            $("#codModi").val(codUse);
            
	})
})


$(document).on("click","#btnVer",function(){
	
	var codUse;
	codUse=$(this).parents("tr").find("td")[0].innerHTML;
	var cargo;
	cargo=$(this).parents("tr").find("td")[4].innerHTML;
	
	$.getJSON("ServletUsuarioJSON",{codUsuario:codUse},function(response){

			$("#det1").text(codUse);
			$("#det2").text(response.nom);
			$("#det3").text(response.apePat+" "+response.apeMat);
			$("#det4").text(response.dni);
			$("#det5").text(response.tel);
			$("#det6").text(response.correo);
			$("#det7").text(response.dir);
			$("#det8").text(response.nomDepartamento +" / "+response.nomProvincia+" / "+response.nomDistrito);
			$("#det9").text(response.nomArea);
			$("#det10").text(cargo);
			$("#det11").text(response.cuenta);
			$("#det12").text(response.contraseña);
			$("#det13").text(response.nomPerfil);
			$("#fotoDetalle").attr('src', 'data:image/jpg;Base64,'+response.fotoString);

		
	})
	
})