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

import net.compensacion.entidad.Provincia;
import net.compensacion.service.ProvinciaService;


@WebServlet("/ServletProvinciaJSON")
public class ServletProvinciaJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ProvinciaService servicioProvincia;
	
    public ServletProvinciaJSON() {
        super();
        servicioProvincia=new ProvinciaService();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String codDep=request.getParameter("codDepartamento");
			// invocaar el metodo listAllCategoriaService
			List<Provincia> lista = servicioProvincia.listAll(Integer.parseInt(codDep));
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
