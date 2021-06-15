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

import net.compensacion.entidad.Distrito;
import net.compensacion.service.DistritoService;

/**
 * Servlet implementation class ServletDistrito
 */
@WebServlet("/ServletDistritoJSON")
public class ServletDistritoJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DistritoService servicioDistrito;
	
    public ServletDistritoJSON() {
        super();
        servicioDistrito=new DistritoService();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String codProv=request.getParameter("codProvincia");
		// invocaar el metodo listAllCategoriaService
		List<Distrito> lista = servicioDistrito.listAll(Integer.parseInt(codProv));
		// Crear objeto de la clase Gson
		Gson gson = new Gson();
		// Convertir en JSON el valor del arreglo lista
		String json = gson.toJson(lista);
		// indicar el tipo de valor a mostrar en el navegador
		response.setContentType("application/json;charset=utf-8");
		// proceso de inprimir en el navegador
		PrintWriter salida = response.getWriter();
		salida.println(json);
		
	}

}
