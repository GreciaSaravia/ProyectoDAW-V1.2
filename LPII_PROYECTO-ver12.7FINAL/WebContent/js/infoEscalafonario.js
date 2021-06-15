$(document).ready(function() {
	cargarEscMag();
	cargarGrupoOc();
	cargarJornLabor();
} );
	

$("[type='number']").keypress(function (evt) {
    evt.preventDefault();
});

$("#idtiempoultimocargo").change(function() {
  $("#idtiempototal").attr('min',$("#idtiempoultimocargo").val());
	$("#idtiempototal").val($("#idtiempoultimocargo").val());
});

$(document).on("click","#btnEliminar",function(){
	var cod;
	cod=$(this).parents("tr").find("td")[0].innerHTML;
	codExp=$(this).parents("tr").find("td")[6].innerHTML;
	$("#idEliminar").val(cod);
	$("#idExpe").val(codExp);
	
})

$(document).on("click","#btnLimpiar",function(){
	$("#idRegistrar").trigger('reset');
	$("#idRegistrar").data('bootstrapValidator').resetForm();
	var foto='https://png.pngtree.com/png-vector/20191026/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1870181.jpg';
	$("#idimg").attr('src', foto);
	$("#btnRegistrar").prop('disabled', true);
	$("#btnLimpiar").prop('disabled', true);
	$("#btnBuscar").prop('disabled', false);
	
	var name = $("#mosCodigo").attr("class");
	$("#mosCodigo").text(name);
	
})



$(document).on("click","#btnAgregar",function(){
	var codSoli,nom,ape,dni,dir,foto,codExp;
	codSoli=$(this).parents("tr").find("td")[0].innerHTML;
	nom=$(this).parents("tr").find("td")[1].innerHTML;
	ape=$(this).parents("tr").find("td")[2].innerHTML;
	dni=$(this).parents("tr").find("td")[3].innerHTML;
	dir=$(this).parents("tr").find("td")[4].innerHTML;
	foto = $(this).parents("tr").children("td")[5].querySelector('img').getAttribute('src');
   	codExp=$(this).parents("tr").find("td")[6].innerHTML;

	$("#idcodigo").val(codSoli);
	$("#idnombres").val(nom);
	$("#idapellidos").val(ape);
	$("#iddni").val(dni);
	$("#iddireccion").val(dir);
	$("#idimg").attr('src', foto);
	$("#idcodigoex").val(codExp);
	
	$("#btnRegistrar").prop('disabled', false);
	$("#btnLimpiar").prop('disabled', false);
	
	$("#idtituloprofesional").prop('disabled', false);
	$("#idcargoactual").prop('disabled', false);
	$("#idescmagisterial").prop('disabled', false);
	$("#idocupacional").prop('disabled', false);
	$("#idjornada").prop('disabled', false);
	$("#idtiempoultimocargo").prop('disabled', false);
	$("#idtiempototal").prop('disabled', false);
	$("#idantecedentes").prop('disabled', false);
	$("#idreslicsingocerem").prop('disabled', false);
	$("#idresdemerito").prop('disabled', false);
	
})

$(document).on("click","#btnEditar",function(){
	$("#idRegistrar").trigger('reset');
	$("#idRegistrar").data('bootstrapValidator').resetForm();
	var codInfo;
	codInfo=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletIExpCodigoJSON",{codigoIExCodigo:codInfo},function(response){
		$.each(response,function(index,item){
			$("#mosCodigo").text(codInfo);
			$("#idcodigoex").val(item.codExp);
			$("#idcodigo").val(item.codigoSoli);
			$("#idnombres").val(item.nombre);
			$("#idapellidos").val(item.apePaterno +' '+ item.apeMaterno);
			$("#iddni").val(item.dni);
			$("#iddireccion").val(item.direccion);
			$("#idimg").attr('src', 'data:image/jpg;Base64,'+item.foto);
			$("#idtituloprofesional").val(item.tp);
			$("#idcargoactual").val(item.ca);
			$("#idescmagisterial").val(item.cod_es_mag );
			$("#idocupacional").val(item.cod_grup_ocp);
			$("#idjornada").val(item.cod_jor_labo);
			$("#idtiempoultimocargo").val(item.ts_uc);
			$("#idtiempototal").val(item.tst);
			$("#idantecedentes").val(item.an);
			$("#idreslicsingocerem").val(item.rlsgr);
			$("#idresdemerito").val(item.rd);
			
			$("#codReg").val(0);
			$("#codModi").val(codInfo);
			$("#btnRegistrar").prop('disabled', false);
			$("#btnLimpiar").prop('disabled', false);
			$("#btnBuscar").prop('disabled', true);
			$("#idtituloprofesional").prop('disabled', false);
			$("#idcargoactual").prop('disabled', false);
			$("#idescmagisterial").prop('disabled', false);
			$("#idocupacional").prop('disabled', false);
			$("#idjornada").prop('disabled', false);
			$("#idtiempoultimocargo").prop('disabled', false);
			$("#idtiempototal").prop('disabled', false);
			$("#idantecedentes").prop('disabled', false);
			$("#idreslicsingocerem").prop('disabled', false);
			$("#idresdemerito").prop('disabled', false);
			
		})
		
	})
	
})

$(document).on("click","#btnVer",function(){
	var codInfo;
	codInfo=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletIExpCodigoJSON",{codigoIExCodigo:codInfo},function(response){
		$.each(response,function(index,item){
			$("#detalle11").text(codInfo);
			$("#detalle6").text(item.tp);
			$("#detalle7").text(item.ca);
			$("#detalle8").text(item.nom_es_mag);
			$("#detalle9").text(item.nom_grup_ocp);
			$("#detalle10").text(item.nom_jor_labo);
			$("#detalle13").text(item.ts_uc+ ' años');
			$("#detalle14").text(item.tst+ ' años');
			$("#detalle15").text(item.an);
			$("#detalle16").text(item.rlsgr);
			$("#detalle17").text(item.rd);
			
			$("#detalle1").text(item.codigoSoli);
			$("#detalle2").text(item.nombre);
			$("#detalle3").text(item.apePaterno +' '+ item.apeMaterno);
			$("#detalle4").text(item.dni);
			$("#detalle5").text(item.direccion);
			$("#detalle12").attr('src', 'data:image/jpg;Base64,'+item.foto);
			$("#usuario").text(item.nom_usuario);
			
			var codExp=item.codExp;
		$.getJSON("ServletIdenAreaJSON",{idenAreaxCodigo:codExp},function(response){
			var area= response;	
				$.getJSON("ServletExpAreaJSON",{codigoAreaxCodigo:area},function(response){
				$.each(response,function(index,item){
					 $("#area").text(item.areaExp);
				})
			})	
		})
		
			
		})
	})
	
	$.getJSON("ServletIdenAreaJSON",{idenAreaxCodigo:codExp},function(response){
			var area= response;	
				$.getJSON("ServletExpAreaJSON",{codigoAreaxCodigo:area},function(response){
				$.each(response,function(index,item){
					 $("#area").text(item.areaExp);
				})
			})	
		})
	
})

function cargarEscMag(){
		$.getJSON("ServletIEcboEscMagJSON",{},function(response){
			$.each(response,function(index,item){
				$("#idescmagisterial").append("<option value='"+item.codigo+"'>"+item.nombre+"</option>");
			})
		})
	}
	
function cargarGrupoOc(){
		$.getJSON("ServletIEcboGruOcupJSON",{},function(response){
			$.each(response,function(index,item){
				$("#idocupacional").append("<option value='"+item.codigo+"'>"+item.nombre+"</option>");
			})
		})
	}
	
function cargarJornLabor(){
		$.getJSON("ServletIEcboJornLaboJSON",{},function(response){
			$.each(response,function(index,item){
				$("#idjornada").append("<option value='"+item.codigo+"'>"+item.nombre+"</option>");
			})
		})
	}

$(document).ready(function(){     
    $('#idRegistrar').bootstrapValidator({   
    	 fields:{
				tituloprofesional:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Título Profesional es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\s]{7,150})$/,
     			 			message:'Campo Título Profesional de 7 a 150 letras'
     			 		}
    		 		}
    		 	},
    		 	cargoactual:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Cargo Actual es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\s]{4,50})$/,
     			 			message:'Campo Cargo Actual de 4 a 50 letras'
     			 		}
    		 		}
    		 	},
    		 	escalamagisterial:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Seleccione una Escala Magisterial'
    		 			}
    		 		}
    		 	},
    		 	ocupacional:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Seleccione un Grupo Ocupacional'
    		 			}
    		 		}
    		 	},
				jornada:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Seleccione una Jornada Laboral'
    		 			}
    		 		}
    		 	},
    		 	tieds:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Tiempo de Serv. en el último cargo es obligatorio'
    		 			},
						/*regexp:{
     			 			regexp:/^([0-9]{1,2})$/,
     			 			message:'Campo Tiempo de Serv. en el último cargo solo números'
     			 		},*/
    		 		}
    		 	},
				tiest:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Tiempo de Servicio Total es obligatorio'
    		 			},
    		 		}
    		 	},
				ant:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Antecedentes es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{4,150})$/,
     			 			message:'Campo Antecedentes de 4 a 150 letras o números'
     			 		}
    		 		}
    		 	},
				reslicsingocerem:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Res. de Lic. sin Goce de Remuneración es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{4,150})$/,
     			 			message:'Campo Res. de Lic. sin Goce de Remuneración de 4 a 150 letras o números'
     			 		}
    		 		}
    		 	},
				resdemerito:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Resolución de Demérito es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{4,150})$/,
     			 			message:'Campo Resolución de Demérito de 4 a 150 letras o números'
     			 		}
    		 		}
    		 	},
    		 	
    	 }
    });   
});    
