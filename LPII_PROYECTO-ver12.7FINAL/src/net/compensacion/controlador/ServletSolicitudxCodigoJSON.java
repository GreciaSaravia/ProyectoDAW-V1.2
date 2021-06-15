package net.compensacion.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.compensacion.entidad.InformeEscalafonario;
import net.compensacion.entidad.Solicitud;
import net.compensacion.interfaces.SolicitudDAO;
import net.compensacion.service.SolicitudService;

/**
 * Servlet implementation class ServletSolicitudxCodigoJSON
 */
@WebServlet("/ServletSolicitudxCodigoJSON")
public class ServletSolicitudxCodigoJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SolicitudService solicitudService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSolicitudxCodigoJSON() {
        super();
        solicitudService=new SolicitudService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod=request.getParameter("codigoSolixCodigo");
		List<Solicitud> lista=solicitudService.ListarSolicitudxCodi(cod);
    	
    	Gson gson=new Gson();
    	
    	String json=gson.toJson(lista);
    	
    	response.setContentType("application/json;charset=UTF-8");
    
    	PrintWriter salida=response.getWriter();
    	salida.println(json);
	}
}
