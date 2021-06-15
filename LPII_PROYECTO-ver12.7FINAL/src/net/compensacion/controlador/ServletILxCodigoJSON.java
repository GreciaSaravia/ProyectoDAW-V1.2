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

import net.compensacion.entidad.InformeLegal;
import net.compensacion.service.InfoLegalService;

/**
 * Servlet implementation class ServletILxCodigoJSON
 */
@WebServlet("/ServletILxCodigoJSON")
public class ServletILxCodigoJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoLegalService infoLegalService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletILxCodigoJSON() {
        super();
        infoLegalService= new InfoLegalService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod=request.getParameter("codigoILxCodigo");
    	List<InformeLegal> lista=infoLegalService.listarCompleILxcod(cod);
    	
    	Gson gson=new Gson();
    	
    	String json=gson.toJson(lista);
    	
    	response.setContentType("application/json;charset=UTF-8");
    
    	PrintWriter salida=response.getWriter();
    	salida.println(json);
	}

}
