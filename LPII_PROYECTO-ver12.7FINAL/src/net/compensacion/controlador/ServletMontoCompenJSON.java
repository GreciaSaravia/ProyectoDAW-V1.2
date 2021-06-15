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

import net.compensacion.entidad.InformeDisponibilidadPresupuestal;
import net.compensacion.service.InfoDisponPresuService;

/**
 * Servlet implementation class ServletMontoCompenJSON
 */
@WebServlet("/ServletMontoCompenJSON")
public class ServletMontoCompenJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoDisponPresuService infoDisponPresuService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletMontoCompenJSON() {
        super();
        infoDisponPresuService= new InfoDisponPresuService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codExp=request.getParameter("MontoCompenxCodExp");
    	List<InformeDisponibilidadPresupuestal> lista=infoDisponPresuService.listarMontoCompen(codExp);
    	
    	Gson gson=new Gson();
    	
    	String json=gson.toJson(lista);
    	
    	response.setContentType("application/json;charset=UTF-8");
    
    	PrintWriter salida=response.getWriter();
    	salida.println(json);
	}

}
