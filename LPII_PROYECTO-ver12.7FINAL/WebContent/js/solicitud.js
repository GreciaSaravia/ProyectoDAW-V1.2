
$(document).on("click","#btnEliminar",function(){
	var cod;
	cod=$(this).parents("tr").find("td")[0].innerHTML;
	$("#idEliminar").val(cod);
	
})

$(document).on("click","#btnLimpiar",function(){
	$("#idRegistrar").trigger('reset');
	
	try{
	$("#idRegistrar").data('bootstrapValidator').resetForm();
	$("#idRegistrar").data('bootstrapValidator').destroy();
	}catch(error){};

		
	var foto='https://png.pngtree.com/png-vector/20191026/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1870181.jpg';
	$("#idimgsoli").attr('src', foto);
	$("#btnRegistrar").prop('disabled', true);
	$("#btnLimpiar").prop('disabled', true);
	$("#btnBuscar").prop('disabled', false);
	$("#iddecjurada").prop('disabled', true);
	$("#idrescontrato").prop('disabled', true);
	$("#idresolquinquenio").prop('disabled', true);
	$("#idconshab").prop('disabled', true);
	$("#idcopiadni").prop('disabled', true);
	$(".dcoAnti").empty();
	
	var name = $("#mosCodigo").attr("class");
	$("#mosCodigo").text(name);
	
})



$(document).on("click","#btnAgregar",function(){
	var codSoli,nom,ape,dni,dir,foto;
	codSoli=$(this).parents("tr").find("td")[0].innerHTML;
	nom=$(this).parents("tr").find("td")[1].innerHTML;
	ape=$(this).parents("tr").find("td")[2].innerHTML;
	dni=$(this).parents("tr").find("td")[3].innerHTML;
	dir=$(this).parents("tr").find("td")[4].innerHTML;
	foto = $(this).parents("tr").children("td")[5].querySelector('img').getAttribute('src');
   
	$("#idcodigo").val(codSoli);
	$("#idnombres").val(nom);
	$("#idapellidos").val(ape);
	$("#iddni").val(dni);
	$("#iddireccion").val(dir);
	$("#idimgsoli").attr('src', foto);
	
	$("#btnRegistrar").prop('disabled', false);
	$("#btnLimpiar").prop('disabled', false);
	$("#iddecjurada").prop('disabled', false);
	$("#idrescontrato").prop('disabled', false);
	$("#idresolquinquenio").prop('disabled', false);
	$("#idconshab").prop('disabled', false);
	$("#idcopiadni").prop('disabled', false);
	
})

$(document).on("click","#btnEditar",function(){
	$("#idRegistrar").trigger('reset');
	
	try{
	$("#idRegistrar").data('bootstrapValidator').resetForm();
	$("#idRegistrar").data('bootstrapValidator').destroy();
	}catch(error){};
	var codSoli;
	codSoli=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletSolicitudxCodigoJSON",{codigoSolixCodigo:codSoli},function(response){
		$.each(response,function(index,item){
			
			$("#mosCodigo").text(codSoli);
			$("#doc1").text("Archivo antiguo: "+item.decJurada);
			$("#doc2").text("Archivo antiguo: "+item.resCont);
			$("#doc3").text("Archivo antiguo: "+item.resQuinq);
			$("#doc4").text("Archivo antiguo: "+item.constPago);
			$("#doc5").text("Archivo antiguo: "+item.docdni);
			
			$("#idcodigo").val(item.codSolicitante);
			$("#idnombres").val(item.nombres);
			$("#idapellidos").val(item.apePat + ' ' +item.apeMat);
			$("#iddni").val(item.dni);
			$("#iddireccion").val(item.dir);
			$("#idimgsoli").attr('src', 'data:image/jpg;Base64,'+item.foto);
			
			$("#codReg").val(0);
			$("#codModi").val(codSoli);
			$("#btnRegistrar").prop('disabled', false);
			$("#btnLimpiar").prop('disabled', false);
			$("#iddecjurada").prop('disabled', false);
			$("#idrescontrato").prop('disabled', false);
			$("#idresolquinquenio").prop('disabled', false);
			$("#idconshab").prop('disabled', false);
			$("#idcopiadni").prop('disabled', false);
			$("#btnBuscar").prop('disabled', true);
			
			$("#iddecjurada1").val(item.decJurada);
			$("#idrescontrato1").val(item.resCont);
			$("#idresolquinquenio1").val(item.resQuinq);
			$("#idconshab1").val(item.constPago);
			$("#idcopiadni1").val(item.docdni);
			
			
		})
		
	})
})


$(document).on("click","#btnVer",function(){
	var codSoli;
	codSoli=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletSolicitudxCodigoJSON",{codigoSolixCodigo:codSoli},function(response){
		$.each(response,function(index,item){
			
			
			$("#detalle11").text(codSoli);
			$("#detalle6").text(item.decJurada);
			$("#detalle7").text(item.resCont);
			$("#detalle8").text(item.resQuinq);
			$("#detalle9").text(item.constPago);
			$("#detalle10").text(item.docdni);
			
			$("#detalle1").text(item.codSolicitante);
			$("#detalle2").text(item.nombres);
			$("#detalle3").text(item.apePat + ' ' +item.apeMat);
			$("#detalle4").text(item.dni);
			$("#detalle5").text(item.dir);
			$("#detalle12").attr('src', 'data:image/jpg;Base64,'+item.foto);
			$("#usuario").text(item.nom_usuario);
	})
	$.getJSON("ServletIdenAreaJSON",{idenAreaxCodigo:codSoli},function(response){
			var area= response;	
				$.getJSON("ServletExpAreaJSON",{codigoAreaxCodigo:area},function(response){
				$.each(response,function(index,item){
					 $("#area").text(item.areaExp);
				})
			})	
		})
})})


$(document).on("click","#btnRegistrar",function(){
		allFilled();
		var attr = $("#idRegistrar").attr('novalidate');
		if (typeof attr !== typeof undefined && attr !== false) {
			
			$("#btnRegistrar").click();
		}
		 
})

function allFilled() {
	
    $('#iddecjurada1, #idrescontrato1, #idresolquinquenio1, #idconshab1, #idcopiadni1').each(function() {
        if($(this).val() != ''){
			 
		  $('#idRegistrar').bootstrapValidator({ 
    		 fields:{
				docdj:{
    		 		validators:{
						regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato v&#225lido'
        		 			}
    		 		}
    		 	},
    		 	docrc:{
    		 		validators:{
					regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato v&#225lido'
        		 			}
    		 		}
    		 	},
    		 	doceq:{
    		 		validators:{
					regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato v&#225lido'
        		 			}
    		 		}
    		 	},
    		 	docph:{
    		 		validators:{
					regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato v&#225lido'
        		 			}
    		 		}
    		 	},
    		 	docdni:{
    		 		validators:{
					regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato v&#225lido'
        		 			}
    		 		}
    		 	},
    		 	
    	 }
    });   
		}	
		
		 if($(this).val() == ''){
		 $('#idRegistrar').bootstrapValidator({ 
    	 fields:{
				docdj:{
    		 		validators:{
						notEmpty:{
    		 				  message:'Adjunte una Declaraci&#243n Jurada'
    		 			},
						regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato v&#225lido'
        		 			}
    		 		}
    		 	},
    		 	docrc:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Adjunte una Resoluci&#243n de Contrato'
    		 			},
					regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato v&#225lido'
        		 			}
    		 		}
    		 	},
    		 	doceq:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Adjunte una Resoluci&#243n de Quinquenio'
    		 			},
					regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato v&#225lido'
        		 			}
    		 		}
    		 	},
    		 	docph:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Adjunte una Constancia de Pagos de Haberes'
    		 			},
					regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato v&#225lido'
        		 			}
    		 		}
    		 	},
    		 	docdni:{
    		 		validators:{
    		 			notEmpty:{
							
    		 				message:'Adjunte un DNI'
    		 			},
					regexp:{
        		 				regexp:/^.*\.(jpg|JPG|doc|DOC|docx|DOCX|pdf|PDF)$/,
        		 				message:'El archivo adjuntado no tiene un formato v&#225lido'
        		 			}
    		 		}
    		 	},
    		 	
    	 }
    }); 
	}
 
    });

}
  