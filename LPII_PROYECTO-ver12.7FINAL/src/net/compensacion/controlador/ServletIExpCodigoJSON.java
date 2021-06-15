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
import net.compensacion.service.InfoEscalafonarioService;

/**
 * Servlet implementation class ServletIExcCodigo
 */
@WebServlet("/ServletIExpCodigoJSON")
public class ServletIExpCodigoJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoEscalafonarioService infoEscalafonarioService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletIExpCodigoJSON() {
        super();
        infoEscalafonarioService= new InfoEscalafonarioService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	String codIE=request.getParameter("codigoIExCodigo");
    	List<InformeEscalafonario> lista=infoEscalafonarioService.listarIExcod(codIE);
    	
    	Gson gson=new Gson();
    	
    	String json=gson.toJson(lista);
    	
    	response.setContentType("application/json;charset=UTF-8");
    
    	PrintWriter salida=response.getWriter();
    	salida.println(json);
	}

}
