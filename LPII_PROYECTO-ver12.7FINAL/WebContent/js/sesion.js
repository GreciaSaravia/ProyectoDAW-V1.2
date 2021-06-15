
$(document).ready(function(){     
     $('#iniciarSesion').bootstrapValidator({ 
    	 fields:{
				login:{
    		 		validators:{
    		 			notEmpty:{
								message:'Ingrese usuario',
    		 			},
					
    		 		}
    		 	},
    		 	clave:{
    		 		validators:{
    		 			notEmpty:{
    		 				message:'Ingrese contrase\u00f1a'
    		 			},
					
    		 		}
    		 	}
    	 }
    });  
});    
