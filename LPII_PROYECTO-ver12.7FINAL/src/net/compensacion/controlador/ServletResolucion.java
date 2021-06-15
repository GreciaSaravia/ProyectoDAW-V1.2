package net.compensacion.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.compensacion.entidad.Resolucion;
import net.compensacion.service.ResolucionService;

/**
 * Servlet implementation class ServletResolucion
 */
@WebServlet("/ServletResolucion")
public class ServletResolucion extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ResolucionService resolucionService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletResolucion() {
        super();
        resolucionService = new ResolucionService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo=request.getParameter("accion");
    	if(tipo.equals("LISTAR"))
    		listar(request,response);
    	else if(tipo.equals("GUARDAR"))
    		guardar(request,response);
    	else if(tipo.equals("ELIMINAR"))
    		eliminar(request,response);
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Resolucion> data=resolucionService.listarCompleRes();
		request.setAttribute("reso", data);

		List<Resolucion> data2=resolucionService.listarSoliSinRes();
		request.setAttribute("solicitantes", data2);
		
		int cod=resolucionService.cargarCodigo();
		request.setAttribute("codigo", cod);	
		request.getRequestDispatcher("/Resolucion.jsp").forward(request,response);
		
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codi=resolucionService.cargarCodigo();
		String cod,codExp,in,con,res,codModi,id_usuario;
		
		cod=request.getParameter("codiRes");
		codExp=request.getParameter("codiExp");
		in=request.getParameter("introduccion");
		con=request.getParameter("consideraciones");
		res=request.getParameter("resolucion");
		id_usuario=request.getParameter("codiUsuario");
		codModi=request.getParameter("codiResModi");
		
		DateFormat fecha = new SimpleDateFormat("yy-MM-dd");
		DateFormat hora = new SimpleDateFormat("kk:mm:ss ");
		Date dateobj = new Date();
		
		if(codi==Integer.parseInt(cod)){//REGISTRAR		
			Resolucion bean=new Resolucion();
			bean.setCodigo(Integer.parseInt(cod));
			bean.setCodExp(Integer.parseInt(codExp));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setIntroduccion(in);
			bean.setConsideraciones(con);
			bean.setResolucion(res);
			bean.setId_usuario(id_usuario);
		
			int salida;
			salida=resolucionService.registrarRes(bean);
		
			if(salida>0) {
				request.setAttribute("MENSAJE", "Se registró Resolución correctamente");
				
			}
			else
				request.setAttribute("MENSAJE", "Error en el registro de Resolución");	
			
			listar(request, response);
		}
		
		else  {//ACTUALIZAR
			Resolucion bean=new Resolucion();
			bean.setCodigo(Integer.parseInt(codModi));
			bean.setCodExp(Integer.parseInt(codExp));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setIntroduccion(in);
			bean.setConsideraciones(con);
			bean.setResolucion(res);
			bean.setId_usuario(id_usuario);
		
			int salida;
			salida=resolucionService.actualizarRes(bean);
	
			if(salida>0)
				request.setAttribute("MENSAJE", "Se actualizó Resolución correctamente");
			else
				request.setAttribute("MENSAJE", "Error en la actualización de Resolución");	
	
			listar(request, response);		
		}
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod=request.getParameter("codigoEliminar");
		String codExp=request.getParameter("codigoExpe");
		int salida=resolucionService.eliminarRes(Integer.parseInt(cod), Integer.parseInt(codExp));
		if(salida!=-1)
			request.setAttribute("MENSAJE", "Se eliminó Resolución correctamente");
		else
			request.setAttribute("MENSAJE", "Error en la eliminación de Resolución");
		listar(request, response);	
	}
}
