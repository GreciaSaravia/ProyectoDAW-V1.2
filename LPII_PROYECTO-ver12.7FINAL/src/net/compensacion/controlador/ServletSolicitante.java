package net.compensacion.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import net.compensacion.entidad.Solicitante;
import net.compensacion.interfaces.SolicitanteDAO;
import net.compensacion.service.SolicitanteService;


@WebServlet("/ServletSolicitante")
public class ServletSolicitante extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	SolicitanteService solicitanteService;
	
    public ServletSolicitante() {
        super();
        solicitanteService = new SolicitanteService();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String tipo=request.getParameter("accion");
    	if(tipo.equals("LISTAR"))
    		listar(request,response);
    	else if(tipo.equals("GUARDAR"))
    		guardar(request,response);
    	else if(tipo.equals("ELIMINAR"))
    		eliminar(request,response);
		
	}
	
	private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int codigo=solicitanteService.returnCodigo();
		
		
		String cod,codSolicitanteAct,nom, apePat, apeMat, dni, dir, tel, correo, dis, prov, foto, codUser;
	
		
		cod=request.getParameter("codiSolicitud");
		nom=request.getParameter("nombres");
		apePat=request.getParameter("apePat");
		apeMat=request.getParameter("apeMat");
		dni=request.getParameter("dni");
		dir=request.getParameter("direccion");
		dis=request.getParameter("distrito");
		prov=request.getParameter("provincia");
		tel=request.getParameter("telefono");
		correo=request.getParameter("correo");	
		foto=request.getParameter("dataurl");
		codUser=request.getParameter("idUsuario");
		
		codSolicitanteAct=request.getParameter("codiSolicitudModi");	
		
		String head = "data:image/jpeg;base64,";
		String base64 = foto.substring(head.length()-1);
		byte[] fotoSolicitante=DatatypeConverter.parseBase64Binary(base64);
		
		DateFormat fecha = new SimpleDateFormat("yy-MM-dd");
		DateFormat hora = new SimpleDateFormat("kk:mm:ss ");
		Date dateobj = new Date();
		
		String dniDup=solicitanteService.validarDni(dni);
		String dniDupAct=solicitanteService.validarDniUpdate(dni, codSolicitanteAct);
		
		
		if(codigo==Integer.parseInt(cod)){//REGISTRAR	
			
			if(dniDup.equals("")) {
				
				Solicitante bean=new Solicitante();
				
				bean.setId(cod);
				bean.setNom(nom);
				bean.setApePat(apePat);
				bean.setApeMat(apeMat);
				bean.setDni(dni);
				bean.setDir(dir);
				bean.setCodDist(solicitanteService.ReturnCodigoDistrito(Integer.parseInt(dis), Integer.parseInt(prov)));//pruebas
				bean.setTel(tel);
				bean.setCorreo(correo);
				bean.setFecha(fecha.format(dateobj));
				bean.setHora(hora.format(dateobj));
				bean.setFoto(fotoSolicitante);
				bean.setIdUsuario(codUser);
				
				int salida;
				salida=solicitanteService.addSolicitante(bean);
				
				if(salida>0)
					request.setAttribute("MENSAJE", "Se registró el Solicitante correctamente");
				else 
					request.setAttribute("MENSAJE", "Error en el registro del Solicitante");
		
				listar(request, response);
			}else {
				request.setAttribute("MENSAJE", "Ya se encuentra registrado el DNI ingresado");
				listar(request, response);
			}
			

		}else {
			
			if(dniDupAct.equals("")) {
				Solicitante bean=new Solicitante();
				
				bean.setId(codSolicitanteAct);
				bean.setNom(nom);
				bean.setApePat(apePat);
				bean.setApeMat(apeMat);
				bean.setDni(dni);
				bean.setDir(dir);
				bean.setCodDist(solicitanteService.ReturnCodigoDistrito(Integer.parseInt(dis), Integer.parseInt(prov)));//pruebas
				bean.setTel(tel);
				bean.setCorreo(correo);
				bean.setFecha(fecha.format(dateobj));
				bean.setHora(hora.format(dateobj));
				bean.setFoto(fotoSolicitante);
				bean.setIdUsuario(codUser);
				
				int salida;
				salida=solicitanteService.updateSolicitante(bean);

				if(salida>0)
					request.setAttribute("MENSAJE", "Se actualizó el Solicitante correctamente");
				else 
					request.setAttribute("MENSAJE", "Error en la actualización del Solicitante");
		
				listar(request, response);
			}else {
				
				request.setAttribute("MENSAJE", "Ya se encuentra registrado el DNI ingresado");
				listar(request, response);
				
			}
	
		}

	}
	
	

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cod=request.getParameter("codigoEliminar");
		int salida=solicitanteService.deleteSolicitante(cod);
		if(salida> 0)
			request.setAttribute("MENSAJE", "Se eliminó Solicitante correctamente");
		else
			request.setAttribute("MENSAJE", "Error en la eliminación del Solicitante");
		listar(request, response);		
			
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Objeto para almacenar el valor de retorno del metodo listAll
		List<Solicitante> lista=solicitanteService.ListAllUsuarios();
		
		int codigo=solicitanteService.returnCodigo();
		request.setAttribute("codigo", codigo);
		
		//Crear un atributo para almacenar el valor del arreglo "lista"
		request.setAttribute("listaSolicitantes",lista );
		
		//Direccionar a la pagina "docente.jsp"
		request.getRequestDispatcher("/Solicitante.jsp").forward(request, response);
		
	}
	
}
