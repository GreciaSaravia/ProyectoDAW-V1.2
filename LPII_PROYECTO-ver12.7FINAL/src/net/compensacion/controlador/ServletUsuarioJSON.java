package net.compensacion.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import net.compensacion.entidad.Usuario;
import net.compensacion.service.UsuarioService;

@WebServlet("/ServletUsuarioJSON")
public class ServletUsuarioJSON extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private UsuarioService servicioUsuario;
	
    public ServletUsuarioJSON() {
        super();
        servicioUsuario=new UsuarioService();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cod=request.getParameter("codUsuario");
		Usuario usuario=servicioUsuario.UsuariosByCodigo(cod);
		Gson gson=new Gson();
		String json=gson.toJson(usuario);
		response.setContentType("application/json;charset=utf-8");
		PrintWriter salida=response.getWriter();
		salida.println(json);
	}

}
