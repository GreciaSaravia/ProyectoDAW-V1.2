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
	
	$("#idintroduccion").prop('disabled', false);
	$("#idconsideraciones").prop('disabled', false);
	$("#idresolucion").prop('disabled', false);
	
})

$(document).on("click","#btnEditar",function(){
	$("#idRegistrar").trigger('reset');
	$("#idRegistrar").data('bootstrapValidator').resetForm();
	var codRes;
	codRes=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletResxCodigoJSON",{codigoResxCodigo:codRes},function(response){
		$.each(response,function(index,item){
			$("#mosCodigo").text(codRes);
			$("#idcodigoex").val(item.codExp);
			$("#idcodigo").val(item.codigoSoli);
			$("#idnombres").val(item.nombre);
			$("#idapellidos").val(item.apePaterno +' '+ item.apeMaterno);
			$("#iddni").val(item.dni);
			$("#iddireccion").val(item.direccion);
			$("#idimg").attr('src', 'data:image/jpg;Base64,'+item.foto);
			$("#idintroduccion").val(item.introduccion);
			$("#idconsideraciones").val(item.consideraciones);
			$("#idresolucion").val(item.resolucion);
			
		
			$("#codReg").val(0);
			$("#codModi").val(codRes);
			$("#btnRegistrar").prop('disabled', false);
			$("#btnLimpiar").prop('disabled', false);
			$("#btnBuscar").prop('disabled', true);
			$("#idintroduccion").prop('disabled', false);
			$("#idconsideraciones").prop('disabled', false);
			$("#idresolucion").prop('disabled', false);
			
		})
		
	})
	
})

$(document).on("click","#btnVer",function(){
	var codRes;
	codRes=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletResxCodigoJSON",{codigoResxCodigo:codRes},function(response){
		$.each(response,function(index,item){
			$("#detalle11").text(codRes);
			$("#detalle6").text(item.introduccion);
			$("#detalle7").text(item.consideraciones);
			$("#detalle8").text(item.resolucion);
			
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
				introduccion:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Introducción es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Introducción de 20 a 500 letras o números'
     			 		}}
    		 	},
    		 	consideraciones:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Consideraciones es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Consideraciones de 20 a 500 letras o números'
     			 		}
    		 		}
    		 	},
    		 	resolucion:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Se resuelve es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Se resuelve de 20 a 500 letras o números'
     			 		}
    		 		}
    		 	},
    		
    }});   
});    
