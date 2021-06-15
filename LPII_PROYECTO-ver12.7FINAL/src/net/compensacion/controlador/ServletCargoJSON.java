package net.compensacion.controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.compensacion.entidad.Cargo;

import net.compensacion.service.CargoService;

/**
 * Servlet implementation class ServletCargo
 */
@WebServlet("/ServletCargoJSON")
public class ServletCargoJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CargoService servicioCargo;

    public ServletCargoJSON() {
        super();
        servicioCargo=new CargoService();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cod=request.getParameter("codArea");
		// invocaar el metodo listAllCategoriaService
		Cargo objCargo = servicioCargo.returnCargo(Integer.parseInt(cod));
		// Crear objeto de la clase Gson
		Gson gson = new Gson();
		// Convertir en JSON el valor del arreglo lista
		String json = gson.toJson(objCargo);
		// indicar el tipo de valor a mostrar en el navegador
		response.setContentType("application/json;charset=utf-8");
		// proceso de inprimir en el navegador
		PrintWriter salida = response.getWriter();
		salida.println(json);
		
	}

}
