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
	
	$("#idantecedentes").prop('disabled', false);
	$("#idpedido").prop('disabled', false);
	$("#idbaselegal").prop('disabled', false);
	$("#idexposicion").prop('disabled', false);
	$("#idconclusiones").prop('disabled', false);
	$("#idsugerencias").prop('disabled', false);
	
})

$(document).on("click","#btnEditar",function(){
	$("#idRegistrar").trigger('reset');
	$("#idRegistrar").data('bootstrapValidator').resetForm();
	var codInfo;
	codInfo=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletILxCodigoJSON",{codigoILxCodigo:codInfo},function(response){
		$.each(response,function(index,item){
			$("#mosCodigo").text(codInfo);
			$("#idcodigoex").val(item.codExp);
			$("#idcodigo").val(item.codigoSoli);
			$("#idnombres").val(item.nombre);
			$("#idapellidos").val(item.apePaterno +' '+ item.apeMaterno);
			$("#iddni").val(item.dni);
			$("#iddireccion").val(item.direccion);
			$("#idimg").attr('src', 'data:image/jpg;Base64,'+item.foto);
			$("#idantecedentes").val(item.antecedente);
			$("#idpedido").val(item.pedido);
			$("#idbaselegal").val(item.baseLegal);
			$("#idexposicion").val(item.exposicion);
			$("#idconclusiones").val(item.conclusiones);
			$("#idsugerencias").val(item.sugerencias);
		
			$("#codReg").val(0);
			$("#codModi").val(codInfo);
			$("#btnRegistrar").prop('disabled', false);
			$("#btnLimpiar").prop('disabled', false);
			$("#btnBuscar").prop('disabled', true);
			$("#idantecedentes").prop('disabled', false);
			$("#idpedido").prop('disabled', false);
			$("#idbaselegal").prop('disabled', false);
			$("#idexposicion").prop('disabled', false);
			$("#idconclusiones").prop('disabled', false);
			$("#idsugerencias").prop('disabled', false);
			
		})
		
	})
	
})

$(document).on("click","#btnVer",function(){
	var codInfo;
	codInfo=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletILxCodigoJSON",{codigoILxCodigo:codInfo},function(response){
		$.each(response,function(index,item){
			$("#detalle11").text(codInfo);
			$("#detalle6").text(item.antecedente);
			$("#detalle7").text(item.pedido);
			$("#detalle8").text(item.baseLegal);
			$("#detalle9").text(item.exposicion);
			$("#detalle10").text(item.conclusiones);
			$("#detalle13").text(item.sugerencias);
			
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
	
})

$(document).ready(function(){     
    $('#idRegistrar').bootstrapValidator({   
    	 fields:{
				antecedentes:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Antecedentes es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Antecedentes de 20 a 500 letras o números'
     			 		}}
    		 	},
    		 	pedido:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Pedido es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Pedido de 20 a 500 letras o números'
     			 		}
    		 		}
    		 	},
    		 	baselegal:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Base Legal es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Base Legal de 20 a 500 letras o números'
     			 		}
    		 		}
    		 	},
    		 	exposicion:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Exposición y Análisis de los Hecho es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Exposición y Análisis de los Hecho de 20 a 500 letras o números'
     			 		}
    		 		}
    		 	},
				conclusiones:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Conclusiones es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Conclusiones de 20 a 500 letras o números'
     			 		}
    		 		}
    		 	},
    		 	sugerencias:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Sugerencias es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{8,500})$/,
     			 			message:'Campo Sugerencias de 20 a 500 letras o números'
     			 		}
					}},
    }});   
});    
