package net.compensacion.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.compensacion.entidad.Expediente;
import net.compensacion.entidad.Requisitos;
import net.compensacion.entidad.Solicitante;
import net.compensacion.entidad.Solicitud;
import net.compensacion.service.SolicitanteService;
import net.compensacion.service.SolicitudService;

@WebServlet("/ServletSolicitud")
public class ServletSolicitud extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SolicitudService solicitudService;
	private SolicitanteService solicitanteService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSolicitud() {
        super();
        // TODO Auto-generated constructor stub
        solicitudService=new SolicitudService();
        solicitanteService=new SolicitanteService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipo=request.getParameter("accion");
    	if(tipo.equals("LISTAR"))
    		listar(request,response);
    	else if(tipo.equals("GUARDAR"))
    		guardar(request,response);
    	else if(tipo.equals("ELIMINAR"))
    		eliminar(request,response);
	}



	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		List<Solicitud> data=solicitudService.ListarTodoSolicitud();
		request.setAttribute("solicitudes", data);

		List<Solicitante> data2=solicitanteService.ListarSolicitanteSinSolicitud();
		request.setAttribute("solicitantes", data2);
		
		int cod=solicitudService.cargarCodigo();
		request.setAttribute("codigo", cod);	
		
		
		request.getRequestDispatcher("/Solicitud.jsp").forward(request,response);
		
	}
	
	private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int cod=solicitudService.cargarCodigo();
		String codSoli,codSolicitan, decJurada, resCont, resQuinq, consPago, dni,codSoliAct,id_usuario;
		String decJurada1, resCont1, resQuinq1, consPago1, dni1;
		int codExp;
		codSoli=request.getParameter("codiSolicitud");
		codSolicitan=request.getParameter("codSolicitante");
		decJurada=request.getParameter("docdj");
		resCont=request.getParameter("docrc");
		resQuinq=request.getParameter("doceq");
		consPago=request.getParameter("docph");
		dni=request.getParameter("docdni");
		id_usuario=request.getParameter("codiUsuario");
		codSoliAct=request.getParameter("codiSolicitudModi");
		
		DateFormat fecha = new SimpleDateFormat("yy-MM-dd");
		DateFormat hora = new SimpleDateFormat("kk:mm:ss ");
		Date dateobj = new Date();
		
	
		
		if(cod==Integer.parseInt(codSoli)){//REGISTRAR		
			Solicitud bean=new Solicitud();
			bean.setCodSolicitud(Integer.parseInt(codSoli));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setCodSolicitante(Integer.parseInt(codSolicitan));
			bean.setCodUsuario(id_usuario);
			
			Requisitos req=new Requisitos();
			
			req.setCodRequisitos(Integer.parseInt(codSoli));
			req.setDecJurada(decJurada);
			req.setResCont(resCont);
			req.setResQuinq(resQuinq);
			req.setConstPago(consPago);
			req.setDni(dni);
			req.setCodSolicitud(Integer.parseInt(codSoli));
			
			Expediente exp=new Expediente();
			
			exp.setCodigoExp(Integer.parseInt(codSoli));
		
			int salida;
			salida=solicitudService.registrarSolicitud(bean, req, exp);
		
			if(salida>2) {
				request.setAttribute("MENSAJE", "Se registró Solicitud de Compensación correctamente");
				
			}
			else
				request.setAttribute("MENSAJE", "Error en el registro de Solicitud de Compensación");	
			
			listar(request, response);
		}
		
		else  {//ACTUALIZAR
			decJurada1=request.getParameter("docdj1");
			resCont1=request.getParameter("docrc1");
			resQuinq1=request.getParameter("doceq1");
			consPago1=request.getParameter("docph1");
			dni1=request.getParameter("docdni1");
			
			Solicitud bean=new Solicitud();
			bean.setCodSolicitud(Integer.parseInt(codSoliAct));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setCodSolicitante(Integer.parseInt(codSolicitan));
			bean.setCodUsuario(id_usuario);
			
			Requisitos req=new Requisitos();
			req.setCodRequisitos(Integer.parseInt(codSoliAct));
			
			if(!decJurada.isEmpty()) {
				req.setDecJurada(decJurada);
			}
			if (decJurada.isEmpty()) {
				req.setDecJurada(decJurada1);	
			}
			
			if(!resCont.isEmpty()) {
				req.setResCont(resCont);
			}
			if(resCont.isEmpty()) {
				req.setResCont(resCont1);	
			}
			
			if(!resQuinq.isEmpty()) {
				req.setResQuinq(resQuinq);
			}
			if(resQuinq.isEmpty()) {
				req.setResQuinq(resQuinq1);	
			}
			
			if(!consPago.isEmpty()) {
				req.setConstPago(consPago);
			}
			if(consPago.isEmpty())  {
				req.setConstPago(consPago1);
			}
			if(!dni.isEmpty()) {
				req.setDni(dni);
			}
			if(dni.isEmpty()) {
				req.setDni(dni1);	
			}
			
			
			req.setCodSolicitud(Integer.parseInt(codSoliAct));
			int salida;
			salida=solicitudService.actualizarSolicitud(bean, req);
	
			if(salida>1)
				request.setAttribute("MENSAJE", "Se actualizó Solicitud de Compensación correctamente");
			else
				request.setAttribute("MENSAJE", "Error en la actualización de Solicitud de Compensación");	
	
			listar(request, response);			
		}
		
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod=request.getParameter("codigoEliminar");
		int salida=solicitudService.eliminarSolicitud(cod);
		if(salida> 2)
			request.setAttribute("MENSAJE", "Se eliminó Solicitud de Compensación correctamente");
		else
			request.setAttribute("MENSAJE", "Error en la eliminación de Solicitud de Compensación");
		listar(request, response);			
	}

	

}
