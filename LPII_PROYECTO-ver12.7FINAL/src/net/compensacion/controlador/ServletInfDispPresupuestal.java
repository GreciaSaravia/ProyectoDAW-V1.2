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

import net.compensacion.entidad.InformeDisponibilidadPresupuestal;
import net.compensacion.service.InfoDisponPresuService;

/**
 * Servlet implementation class ServletInfDispPresupuestal
 */
@WebServlet("/ServletInfDispPresupuestal")
public class ServletInfDispPresupuestal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoDisponPresuService infoDisponPresuService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInfDispPresupuestal() {
        super();
        infoDisponPresuService= new InfoDisponPresuService();
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
		List<InformeDisponibilidadPresupuestal> data=infoDisponPresuService.listarCompleIDP();
		request.setAttribute("infdispresu", data);

		List<InformeDisponibilidadPresupuestal> data2=infoDisponPresuService.listarSolisinIDP();
		request.setAttribute("solicitantes", data2);
		
		int cod=infoDisponPresuService.cargarCodigo();
		request.setAttribute("codigo", cod);	
		request.getRequestDispatcher("/InformeDisponibilidadPresupuestal.jsp").forward(request,response);
		
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codi=infoDisponPresuService.cargarCodigo();
		String mon,alc,obj,id_usuario;
		String cod,codExp,codiIDPModi;
		
		cod=request.getParameter("codiIDP");
		codExp=request.getParameter("codiExp");
		mon=request.getParameter("monto");
		obj=request.getParameter("objetivos");
		alc=request.getParameter("alcance");
		id_usuario=request.getParameter("codiUsuario");
		codiIDPModi=request.getParameter("codiIDPModi");
		
		DateFormat fecha = new SimpleDateFormat("yy-MM-dd");
		DateFormat hora = new SimpleDateFormat("kk:mm:ss ");
		Date dateobj = new Date();
		
		if(codi==Integer.parseInt(cod)){//REGISTRAR		
			InformeDisponibilidadPresupuestal bean=new InformeDisponibilidadPresupuestal();
			bean.setCodigo(Integer.parseInt(cod));
			bean.setCodExp(Integer.parseInt(codExp));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setMonto(Double.parseDouble(mon));
			bean.setObjetivo(obj);
			bean.setAlcance(alc);
			bean.setId_usuario(id_usuario);
			
			int salida;
			salida=infoDisponPresuService.registrarIDP(bean);
		
			if(salida>0) {
				request.setAttribute("MENSAJE", "Se registró Informe de Disponibilidad Presupuestal correctamente");
				
			}
			else
				request.setAttribute("MENSAJE", "Error en el registro de Informe de Disponibilidad Presupuestal");	
			
			listar(request, response);
		}
		
		else  {//ACTUALIZAR
			InformeDisponibilidadPresupuestal bean=new InformeDisponibilidadPresupuestal();
			bean.setCodigo(Integer.parseInt(codiIDPModi));
			bean.setCodExp(Integer.parseInt(codExp));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setMonto(Double.parseDouble(mon));
			bean.setObjetivo(obj);
			bean.setAlcance(alc);
			bean.setId_usuario(id_usuario);
		
			int salida;
			salida=infoDisponPresuService.actualizarIDP(bean);
	
			if(salida>0)
				request.setAttribute("MENSAJE", "Se actualizó Informe de Disponibilidad Presupuestal correctamente");
			else
				request.setAttribute("MENSAJE", "Error en la actualización de Informe de Disponibilidad Presupuestal");	
	
			listar(request, response);		
		}
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod=request.getParameter("codigoEliminar");
		String codExp=request.getParameter("codigoExpe");
		int salida=infoDisponPresuService.eliminarIDP(Integer.parseInt(cod), Integer.parseInt(codExp));
		if(salida!=-1)
			request.setAttribute("MENSAJE", "Se eliminó Informe de Disponibilidad Presupuestal correctamente");
		else
			request.setAttribute("MENSAJE", "Error en la eliminación de Informe de Disponibilidad Presupuestal");
		listar(request, response);	
	}

}
