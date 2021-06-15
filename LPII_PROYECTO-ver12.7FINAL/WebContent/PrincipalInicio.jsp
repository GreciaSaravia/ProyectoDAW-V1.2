<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<c:if test="${sesion == null}">
		<c:redirect url="Sesion.jsp"/>
	</c:if>
	
	
<!DOCTYPE html>

<html lang="es">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>UNEG</title>
    <link rel="icon" href="img/LOGO_UNEG.jpg">
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <link href="css/estilo.css" rel="stylesheet">
    <link href="vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/webcamjs/1.0.25/webcam.js"></script>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav sidebar sidebar-dark accordion menu-principal" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="d-flex align-items-center justify-content-center" href="MenuPrincipal.jsp">
                <img alt="" class="logo hvr-bounce-in" src="img/LOGO_UNEG.jpg">
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider">


            <!-- Nav Item - Utilities Collapse Menu -->
            
           <c:forEach items="${sessionScope.MenuOpciones}" var="row">
            <li class="nav-item">
                <a class="nav-link menu collapsed" href="#" data-toggle="collapse" data-target="#${row.nomMenu}"
                    aria-expanded="true" aria-controls="${row.nomMenu}">
                    <i class="${row.claseIcono}"></i>
                    <span>${row.nomMenu}</span>
                </a>
                <div id="${row.nomMenu}" class="collapse" aria-labelledby="headingTwo"  data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                    	<c:forEach items="${row.aOpciones}" var="opc">                	                      
                        	<a class="collapse-item" href="${opc.url}">${opc.nombre}</a>                      	
                        </c:forEach>                   
                    </div>
                </div>
            </li>
            </c:forEach>
             <!--
            <li class="nav-item">
            
                <a class="nav-link menu collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
                    aria-expanded="true" aria-controls="collapseUtilities">
                   <i class="fas fa-file-alt"></i>
                   <span>TRÁMITES</span>
                </a>
                <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
                    data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="ServletSolicitud?accion=LISTAR">Solicitud</a>
                        <a class="collapse-item" href="ServletInfoEscalafonario?accion=LISTAR">Inf. Escalafonario</a>
                        <a class="collapse-item" href="InformeTecnicoLegal.jsp">Inf. Tec. Legal</a>
                        <a class="collapse-item" href="InformeDisponibilidadPresupuestal.jsp">Inf. Disp. Presupuestal</a>
                        <a class="collapse-item" href="InformeLegal.jsp">Inf. Legal</a>
                        <a class="collapse-item" href="Resolucion.jsp">Resolución</a>
                    </div>
                </div>
            </li>
           
            <!-- Nav Item - Utilities Collapse Menu 
            <li class="nav-item">
                <a class="nav-link menu" href="Reporte.jsp" >
                   <i class="fas fa-scroll"></i>
                    <span>REPORTES</span>
                </a>
            </li>
			
			  Nav Item - Utilities Collapse Menu 
            <li class="nav-item">
                <a class="nav-link menu" href="Acerca.jsp" >
                   <i class="fas fa-info-circle"></i>
                    <span>ACERCA</span>
                </a>
            </li>
			--->
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        

        </ul>
        <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle usuario" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline small ">${sesion.nomUsuario} ${sesion.apePaterno}</span>
                                <img class="img-profile rounded-circle"
                                    src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">                             
                                <a class="dropdown-item salida d-flex align-items-center justify-content-center" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 salida1"></i>
                                    Cerrar
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">


