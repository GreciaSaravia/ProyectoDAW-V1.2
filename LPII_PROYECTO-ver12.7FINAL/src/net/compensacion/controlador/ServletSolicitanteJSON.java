package net.compensacion.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.compensacion.entidad.Solicitante;
import net.compensacion.service.SolicitanteService;

/**
 * Servlet implementation class ServletSolicitanteJSON
 */
@WebServlet("/ServletSolicitanteJSON")
public class ServletSolicitanteJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	SolicitanteService servicioSolicitante;
	
    public ServletSolicitanteJSON() {
        super();
        servicioSolicitante = new SolicitanteService();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cod=request.getParameter("codSolicitante");
		Solicitante solicitante=servicioSolicitante.SolicitanteByCodigo(cod);
		Gson gson=new Gson();
		String json=gson.toJson(solicitante);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter salida=response.getWriter();
		salida.println(json);
		
	}

}
