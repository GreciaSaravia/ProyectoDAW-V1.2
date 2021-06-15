
$(document).on("click","#btnEliminar",function(){
	var cod,codExp;
	cod=$(this).parents("tr").find("td")[0].innerHTML;
	codExp=$(this).parents("tr").find("td")[6].innerHTML;
	$("#idEliminar").val(cod);
	$("#idExpe").val(codExp);
	
})

$(document).on("click","#btnLimpiar",function(){
	$("#idRegistrar").trigger('reset');
	
	try{
	$("#idRegistrar").data('bootstrapValidator').resetForm();
	$("#idRegistrar").data('bootstrapValidator').destroy();
	}catch(error){};
	var foto='https://png.pngtree.com/png-vector/20191026/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1870181.jpg';
	$("#idimg").attr('src', foto);
	$("#btnRegistrar").prop('disabled', true);
	$("#btnLimpiar").prop('disabled', true);
	$("#btnBuscar").prop('disabled', false);
	$(".dcoAnti").empty();
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
	
	$("#idobjetivos").prop('disabled', false);
	$("#idanalisis").prop('disabled', false);
	$("#idliquida").prop('disabled', false);
	
})

$(document).on("click","#btnEditar",function(){
	$("#idRegistrar").trigger('reset');
	
	try{
	$("#idRegistrar").data('bootstrapValidator').resetForm();
	$("#idRegistrar").data('bootstrapValidator').destroy();
	}catch(error){};
	var codITL;
	codITL=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletITLxCodigoJSON",{codigoITLxCodigo:codITL},function(response){
		$.each(response,function(index,item){
			$("#mosCodigo").text(codITL);
			$("#idcodigoex").val(item.codExp);
			$("#idcodigo").val(item.codigoSoli);
			$("#idnombres").val(item.nombre);
			$("#idapellidos").val(item.apePaterno +' '+ item.apeMaterno);
			$("#iddni").val(item.dni);
			$("#iddireccion").val(item.direccion);
			$("#idimg").attr('src', 'data:image/jpg;Base64,'+item.foto);
			$("#idobjetivos").val(item.objetivo);
			$("#idanalisis").val(item.analisis);
			$("#doc1").text("Archivo antiguo: "+item.liquidacion);
			
			$("#codReg").val(0);
			$("#codModi").val(codITL);
			$("#btnRegistrar").prop('disabled', false);
			$("#btnLimpiar").prop('disabled', false);
			$("#btnBuscar").prop('disabled', true);
			$("#idobjetivos").prop('disabled', false);
			$("#idanalisis").prop('disabled', false);
			$("#idliquida").prop('disabled', false);
			
			$("#idliquida1").val(item.liquidacion);
			
		})
		
	})
	
})

$(document).on("click","#btnVer",function(){
	var codITL;
	codITL=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletITLxCodigoJSON",{codigoITLxCodigo:codITL},function(response){
		$.each(response,function(index,item){
			$("#detalle11").text(codITL);
			$("#detalle6").text(item.objetivo);
			$("#detalle7").text(item.analisis);
			$("#detalle8").text(item.liquidacion);
			
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


$(document).on("click","#btnRegistrar",function(){
	allFilled();
	var attr = $("#idRegistrar").attr('novalidate');
		if (typeof attr !== typeof undefined && attr !== false) {
		
			$("#btnRegistrar").click();
	}
		
})

function allFilled() {
	 $('#idanalisis, #idliquida1, #idobjetivos').each(function() {
     if($(this).val() != ''){
		$('#idRegistrar').bootstrapValidator({   
    	 fields:{
				objetivos:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Objetivos del Informe es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Objetivos del Informe de 20 a 500 letras o números'
     			 		}
    		 		}
    		 	},
    		 	analisis:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Análisis del Informe es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Análisis del Informe de 20 a 500 letras o números'
     			 		}
    		 		}
    		 	},
    		 	liquida:{
    		 		validators:{
						regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato válido'
        		 			}
    		 		}
    		 	},
    	 }
    });   
		}	
		
		 if($(this).val() == ''){
		   $('#idRegistrar').bootstrapValidator({   
    		 fields:{
				objetivos:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Objetivos del Informe es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Objetivos del Informe de 20 a 500 letras o números'
     			 		}
    		 		}
    		 	},
    		 	analisis:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Análisis del Informe es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Análisis del Informe de 20 a 500 letras o números'
     			 		}
    		 		}
    		 	},
    		 	liquida:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Adjunte una Liquidación'
    		 			},
						regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato válido'
        		 			}
    		 		}
    		 	},
    	 }
    });   
	} });
}
