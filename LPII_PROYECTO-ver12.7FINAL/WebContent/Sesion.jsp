<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html lang="es">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>UNEG - Login</title>
	<link rel="icon" href="img/LOGO_UNEG.jpg">
	  <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">
 	<link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="css/estilo.css" rel="stylesheet">

</head>

<body class="inicio-sesion " >

    <div class="container" >

		<c:if test="${requestScope.MENSAJE!=null}">
			<div class="alert alert-light alert-dismissible fade show" role="alert">
			  <strong>MENSAJE </strong><i class="fas fa-exclamation-circle alerta"></i>
			  <h6>${requestScope.MENSAJE}</h6>
			 
			</div>
		</c:if>

        <!-- Outer Row -->
        
        <div class="row justify-content-center ">
			
            <div class="col-xl-10 col-lg-12 col-md-9 ">

                <div class="card o-hidden border-0 shadow-lg caja">
                    <div class="card-body p-0">
                        <div class="row">
                            <div class="col-lg-6 d-flex align-items-center justify-content-center fondo-logo">
                            <img alt="" class="img-fluid rounded " src="img/LOGO_UNEG.jpg">
							</div>
                            <div class="col-lg-6 ">
                            
                                <div class="p-5">
                                    <div class="text-center">
                                        <br><h1 class="h4 text-gray-900 mb-4 font-weight-bold">BIENVENIDO</h1><br>
                                    </div>
                                     <form class="user" data-toggle="validator" role="form" method="post" id="iniciarSesion" action="ServletSesion?accion=INICIAR"  >
                                        <div class="form-group ">
                                         <div class="row">
                                          <div class="col-lg-1 d-flex align-items-center justify-content-center">
                                          	<i class="fas fa-user  icono-sesion"></i>
                                          </div>
                                           <div class="col-lg-11 ">
                                            <input class="form-control form-control-user d-block"
                                                placeholder="Ingrese usuario..." name="login">
                                          </div>
                                           </div>
                                        </div>
                                        <div class="form-group ">
                                        
                                           <div class="row">
                                          <div class="col-lg-1 d-flex align-items-center justify-content-center">
                                          	<i class="fas fa-key  icono-sesion"></i>
                                          </div>
                                           <div class="col-lg-11 ">
                                            <input type="password" class="form-control form-control-user d-block"
                                               placeholder="Ingrese contraseña..." name="clave">
                                          </div>
                                        </div>
                                        
                                        </div>
                                         <!-- <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">Recordarme</label>
                                            </div>
                                        </div>
                                        <a href="#" class="btn btn-primary2 btn-user btn-block" onclick="document.getElementById('iniciarSesion').submit();">
                                           <i class="fas fa-sign-in-alt fa-sm fa-fw mr-2"></i>
                                            INGRESAR
                                        </a>-->
                                        
                                        <button type="submit" class="btn btn-primary2 btn-user btn-block" id="btnIniciar">
										 <i class="fas fa-sign-in-alt fa-sm fa-fw mr-2"></i><span class="boton">INGRESAR</span></button>
                                    </form>
                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

        </div>
		
    </div>

    <!-- Bootstrap core JavaScript-->
   <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
 
	<script src="https://cdn.bootcdn.net/ajax/libs/bootstrap-validator/0.4.0/js/bootstrapValidator.js"></script>
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <script src="js/sb-admin-2.min.js"></script>

    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <script src="js/demo/datatables-demo.js"></script>

    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>
	<script type="text/javascript" src="js/sesion.js"></script>
	
</body>

</html>