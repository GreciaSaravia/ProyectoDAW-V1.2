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

import net.compensacion.entidad.InformeTecnicoLegal;
import net.compensacion.service.InfoTecnicoLegalService;

/**
 * Servlet implementation class ServletITLxCodigo
 */
@WebServlet("/ServletITLxCodigoJSON")
public class ServletITLxCodigoJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoTecnicoLegalService infoTecnicoLegalService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletITLxCodigoJSON() {
        super();
        infoTecnicoLegalService= new InfoTecnicoLegalService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String codITL=request.getParameter("codigoITLxCodigo");
    	List<InformeTecnicoLegal> lista=infoTecnicoLegalService.listarCompleITLxcod(codITL);
    	
    	Gson gson=new Gson();
    	
    	String json=gson.toJson(lista);
    	
    	response.setContentType("application/json;charset=UTF-8");
    
    	PrintWriter salida=response.getWriter();
    	salida.println(json);
	}

}
