
$(document).on("click","#btnEliminar",function(){
	var cod,codExp;
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
	$("#btnMonto").prop('disabled', false);
	
	$("#idobjetivos").prop('disabled', false);
	$("#idalcance").prop('disabled', false);
	
	CargarMonto(codExp);
	
	
	
})

$(document).on("click","#btnEditar",function(){
	$("#idRegistrar").trigger('reset');
	$("#idRegistrar").data('bootstrapValidator').resetForm();
	var codIDP;
	codIDP=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletIDPxCodigoJSON",{codigoIDPxCodigo:codIDP},function(response){
		$.each(response,function(index,item){
			$("#mosCodigo").text(codIDP);
			$("#idcodigoex").val(item.codExp);
			$("#idcodigo").val(item.codigoSoli);
			$("#idnombres").val(item.nombre);
			$("#idapellidos").val(item.apePaterno +' '+ item.apeMaterno);
			$("#iddni").val(item.dni);
			$("#iddireccion").val(item.direccion);
			$("#idimg").attr('src', 'data:image/jpg;Base64,'+item.foto);
			$("#idobjetivos").val(item.objetivo);
			$("#idalcance").val(item.alcance);
			
			var codExp=item.codExp;
			CargarMonto(codExp);
			
			
			$("#codReg").val(0);
			$("#codModi").val(codIDP);
			$("#mosCodigo1").text(codIDP);
			$("#btnRegistrar").prop('disabled', false);
			$("#btnLimpiar").prop('disabled', false);
			$("#btnMonto").prop('disabled', false);
			$("#btnBuscar").prop('disabled', true);
			$("#idobjetivos").prop('disabled', false);
			$("#idalcance").prop('disabled', false);
			
			
		})
		
	})
	
})

function CargarMonto(codExp){
	$.getJSON("ServletMontoCompenJSON",{MontoCompenxCodExp:codExp},function(response){
		$.each(response,function(index,item){
			var sueldoEsc1Jorn1=2300.10;
			var sueldoEsc2Jorn1=2530.11;
			var sueldoEsc3Jorn1=2760.12;
			var sueldoEsc4Jorn1=2990.13;
			var sueldoEsc5Jorn1=3450.15;
			var sueldoEsc6Jorn1=4025.18;
			var sueldoEsc7Jorn1=4370.19;
			var sueldoEsc8Jorn1=4830.21;
			
			var sueldoEsc1Jorn2=3066.80;
			var sueldoEsc2Jorn2=3373.48;
			var sueldoEsc3Jorn2=3680.16;
			var sueldoEsc4Jorn2=3986.84;
			var sueldoEsc5Jorn2=4600.20;
			var sueldoEsc6Jorn2=5366.90;
			var sueldoEsc7Jorn2=5826.92;
			var sueldoEsc8Jorn2=6440.28;
			
			var esc,jor,tie;
			$("#esc").text(item.nom_es_mag);
			$("#jor").text(item.nom_jor_labo);
			$("#tie").text(item.tst+" años");
			
			if(item.cod_es_mag===1 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc1Jorn1*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc1Jorn1*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc1Jorn1);}
				else{
					$("#idmonto").val((sueldoEsc1Jorn1/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc1Jorn1/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc1Jorn1);}
			}
			else if(item.cod_es_mag===1 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc1Jorn2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc1Jorn2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc1Jorn2);}
				else{
					$("#idmonto").val((sueldoEsc1Jorn2/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc1Jorn2/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc1Jorn2);}
			}
			else if(item.cod_es_mag===2 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc2Jorn1*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc2Jorn1*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc2Jorn1);	}
				else{
					$("#idmonto").val((sueldoEsc2Jorn1/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc2Jorn1/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc2Jorn1);	}
			}
			else if(item.cod_es_mag===2 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc2Jorn2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc2Jorn2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc2Jorn2);}
				else{
					$("#idmonto").val((sueldoEsc2Jorn2/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc2Jorn2/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc2Jorn2);}
			}
			else if(item.cod_es_mag===3 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc3Jorn1*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc3Jorn1*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc3Jorn1);}
				else{
					$("#idmonto").val(sueldoEsc3Jorn1/2*item.tst);
					$("#monto2").text("S/. "+sueldoEsc3Jorn1/2*item.tst);
					$("#sue").text("S/. "+sueldoEsc3Jorn1);}
			}
			else if(item.cod_es_mag===3 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc3Jorn2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc3Jorn2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc3Jorn2);}
				else{
					$("#idmonto").val((sueldoEsc3Jorn2/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc3Jorn2/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc3Jorn2);}
			}
			else if(item.cod_es_mag===4 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc4Jorn1*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc4Jorn1*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc4Jorn1);}
				else{
					$("#idmonto").val((sueldoEsc4Jorn1/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc4Jorn1/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc4Jorn1);}
			}
			else if(item.cod_es_mag===4 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc4Jorn2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc4Jorn2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc4Jorn2);}
				else{
					$("#idmonto").val((sueldoEsc4Jorn2/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "(sueldoEsc4Jorn2/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc4Jorn2);}
			}
			else if(item.cod_es_mag===5 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc5Jorn1*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc5Jorn1*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc5Jorn1);}
				else{
					$("#idmonto").val((sueldoEsc5Jorn1/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc5Jorn1/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc5Jorn1);}
			}
			else if(item.cod_es_mag===5 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc5Jorn2*item.tst).toFixed(2));
					$("#monto2").val("S/. "+(sueldoEsc5Jorn2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc5Jorn2);}
				else{
					$("#idmonto").val((sueldoEsc5Jorn2/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc5Jorn2/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc5Jorn2);}
			}
			else if(item.cod_es_mag===6 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc6Jorn1*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc6Jorn1*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc6Jorn1);}
				else{
					$("#idmonto").val((sueldoEsc6Jorn1/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc6Jorn1/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc6Jorn1);}
			}
			else if(item.cod_es_mag===6 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc6Jorn2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc6Jorn2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc6Jorn2);}
				else{
					$("#idmonto").val((sueldoEsc6Jorn2/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc6Jorn2/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc6Jorn2);}
			}
			else if(item.cod_es_mag===7 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc7Jorn1*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc7Jorn1*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc7Jorn1);}
				else{
					$("#idmonto").val((sueldoEsc7Jorn1/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc7Jorn1/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc7Jorn1);}
			}
			else if(item.cod_es_mag===7 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc7Jorn2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc7Jorn2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc7Jorn2);}
				else{
					$("#idmonto").val((sueldoEsc7Jorn2/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc7Jorn2/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc7Jorn2);}
			}
			else if(item.cod_es_mag===8 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc8Jorn1*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc8Jorn1*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc8Jorn1);}
				else{
					$("#idmonto").val((sueldoEsc8Jorn1/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc8Jorn1/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc8Jorn1);}
			}
			else {
				if(item.tst>=20){
					$("#idmonto").val((sueldoEsc8Jorn2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc8Jorn2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc8Jorn2);}
				else{
					$("#idmonto").val((sueldoEsc8Jorn2/2*item.tst).toFixed(2));
					$("#monto2").text("S/. "+(sueldoEsc8Jorn2/2*item.tst).toFixed(2));
					$("#sue").text("S/. "+sueldoEsc8Jorn2);}
					}
		})
	})
	
}

$(document).on("click","#btnVer",function(){
	var codIDP;
	codIDP=$(this).parents("tr").find("td")[0].innerHTML;
	$.getJSON("ServletIDPxCodigoJSON",{codigoIDPxCodigo:codIDP},function(response){
		$.each(response,function(index,item){
			$("#detalle11").text(codIDP);
			$("#detalle8").text(item.objetivo);
			$("#detalle7").text(item.alcance);
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
		
		$.getJSON("ServletMontoCompenJSON",{MontoCompenxCodExp:codExp},function(response){
		$.each(response,function(index,item){
			var sueldoEsc1Jorn1=2300.10;
			var sueldoEsc2Jorn1=2530.11;
			var sueldoEsc3Jorn1=2760.12;
			var sueldoEsc4Jorn1=2990.13;
			var sueldoEsc5Jorn1=3450.15;
			var sueldoEsc6Jorn1=4025.18;
			var sueldoEsc7Jorn1=4370.19;
			var sueldoEsc8Jorn1=4830.21;
			
			var sueldoEsc1Jorn2=3066.80;
			var sueldoEsc2Jorn2=3373.48;
			var sueldoEsc3Jorn2=3680.16;
			var sueldoEsc4Jorn2=3986.84;
			var sueldoEsc5Jorn2=4600.20;
			var sueldoEsc6Jorn2=5366.90;
			var sueldoEsc7Jorn2=5826.92;
			var sueldoEsc8Jorn2=6440.28;
			
			var esc,jor,tie;
			$("#detalle9").text(item.nom_es_mag);
			$("#detalle10").text(item.nom_jor_labo);
			$("#detalle14").text(item.tst+" años");
			
			if(item.cod_es_mag===1 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc1Jorn1*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc1Jorn1);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc1Jorn1/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc1Jorn1);}
			}
			else if(item.cod_es_mag===1 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc1Jorn2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc1Jorn2);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc1Jorn2/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc1Jorn2);}
			}
			else if(item.cod_es_mag===2 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc2Jorn1*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc2Jorn1);	}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc2Jorn1/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc2Jorn1);	}
			}
			else if(item.cod_es_mag===2 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc2Jorn2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc2Jorn2);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc2Jorn2/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc2Jorn2);}
			}
			else if(item.cod_es_mag===3 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc3Jorn1*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc3Jorn1);}
				else{
					$("#detalle6").text("S/. "+sueldoEsc3Jorn1/2*item.tst);
					$("#detalle13").text("S/. "+sueldoEsc3Jorn1);}
			}
			else if(item.cod_es_mag===3 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc3Jorn2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc3Jorn2);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc3Jorn2/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc3Jorn2);}
			}
			else if(item.cod_es_mag===4 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc4Jorn1*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc4Jorn1);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc4Jorn1/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc4Jorn1);}
			}
			else if(item.cod_es_mag===4 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc4Jorn2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc4Jorn2);}
				else{
					$("#detalle6").text("S/. "(sueldoEsc4Jorn2/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc4Jorn2);}
			}
			else if(item.cod_es_mag===5 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc5Jorn1*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc5Jorn1);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc5Jorn1/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc5Jorn1);}
			}
			else if(item.cod_es_mag===5 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#monto2").val("S/. "+(sueldoEsc5Jorn2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc5Jorn2);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc5Jorn2/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc5Jorn2);}
			}
			else if(item.cod_es_mag===6 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc6Jorn1*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc6Jorn1);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc6Jorn1/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc6Jorn1);}
			}
			else if(item.cod_es_mag===6 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc6Jorn2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc6Jorn2);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc6Jorn2/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc6Jorn2);}
			}
			else if(item.cod_es_mag===7 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc7Jorn1*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc7Jorn1);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc7Jorn1/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc7Jorn1);}
			}
			else if(item.cod_es_mag===7 && item.cod_jor_labo===2){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc7Jorn2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc7Jorn2);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc7Jorn2/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc7Jorn2);}
			}
			else if(item.cod_es_mag===8 && item.cod_jor_labo===1){
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc8Jorn1*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc8Jorn1);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc8Jorn1/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc8Jorn1);}
			}
			else {
				if(item.tst>=20){
					$("#detalle6").text("S/. "+(sueldoEsc8Jorn2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc8Jorn2);}
				else{
					$("#detalle6").text("S/. "+(sueldoEsc8Jorn2/2*item.tst).toFixed(2));
					$("#detalle13").text("S/. "+sueldoEsc8Jorn2);}
			}	
		})
	})
		})
		
	})
	
})


$(document).ready(function(){     
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
    		 	alcance:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Campo Alcance del Informe es obligatorio'
    		 			},
						regexp:{
     			 			regexp:/^([a-zA-Z\\ñáéíóúÁÉÍÓÚÑ\0-9°._:-\s@]{20,500})$/,
     			 			message:'Campo Alcance del Informe de 20 a 500 letras o números'
     			 		}
    		 		}
    		 	},
    		 	
    	 }
    });   
});    
