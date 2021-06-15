package net.compensacion.controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.compensacion.entidad.Menu;
import net.compensacion.entidad.MenuOpciones;
import net.compensacion.entidad.Opciones;
import net.compensacion.entidad.Sesion;
import net.compensacion.service.SesionService;

/**
 * Servlet implementation class ServletSesion
 */
@WebServlet("/ServletSesion")
public class ServletSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SesionService servicioSesion;
	
    public ServletSesion() {
        super();
        servicioSesion=new SesionService();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String accion= request.getParameter("accion");
		
		if(accion.equals("INICIAR"))
			iniciarSesion(request,response);
		else if(accion.equals("CERRAR"))
			cerrarSesion(request,response);
		
	}


	private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Obtener sesion actual
		HttpSession session=request.getSession();
		
		//invalidar sesion actual
		session.invalidate();
		request.getRequestDispatcher("Sesion.jsp").forward(request, response);
		
	}


	private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login, clave;
		login=request.getParameter("login");
		clave=request.getParameter("clave");
		
		//Invocar al metodo sesion
		
		Sesion bean=servicioSesion.Sesion(login, clave);
		
		
		//Validar objeto bean
		
		if(bean==null) {
			request.setAttribute("MENSAJE", "Usuario y/o clave incorrectos..");
			request.getRequestDispatcher("/Sesion.jsp").forward(request, response);
		}
		else {
			
			//Obtener el codigo del usuario actual
			int codPerfil;
			codPerfil = bean.getCodPerfil();
			
			//Arreglo para MenuOpciones;
			List<MenuOpciones> aMenuOpciones=new ArrayList<MenuOpciones>();
			
			//Invocar al metodo traermenu
			List<Menu> lista=servicioSesion.traerMenus(codPerfil);
			
			for (Menu menu : lista) {
				
				MenuOpciones mopc=new MenuOpciones();
				mopc.setCodMenu(menu.getCodigo());
				mopc.setNomMenu(menu.getNombre());	
				mopc.setClaseIcono(menu.getClaseIcono());
				List<Opciones> lista2=servicioSesion.traerOpciones(codPerfil, menu.getCodigo());
				mopc.setaOpciones(lista2);
				
				aMenuOpciones.add(mopc);							
			}
			
			//Crear una sesion
			
			HttpSession session=request.getSession();
			
			//Crear atributos desntro del objeto sesion
			
			session.setAttribute("MenuOpciones", aMenuOpciones);
			session.setAttribute("sesion", bean);
			
			request.getRequestDispatcher("/MenuPrincipal.jsp").forward(request, response);
		}
		
	}

}
