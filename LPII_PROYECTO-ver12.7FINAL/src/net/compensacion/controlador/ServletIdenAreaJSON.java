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

import net.compensacion.entidad.Expediente;
import net.compensacion.service.ExpAreaService;

/**
 * Servlet implementation class ServletAreaJSON
 */
@WebServlet("/ServletIdenAreaJSON")
public class ServletIdenAreaJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private ExpAreaService areaservice;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletIdenAreaJSON() {
        super();
        areaservice= new ExpAreaService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codExp=request.getParameter("idenAreaxCodigo");
    	String codigo=areaservice.identificarArea(codExp);
    	
    	Gson gson=new Gson();
    	
    	String json=gson.toJson(codigo);
    	
    	response.setContentType("application/json;charset=UTF-8");
    
    	PrintWriter salida=response.getWriter();
    	salida.println(json);
	}

}
