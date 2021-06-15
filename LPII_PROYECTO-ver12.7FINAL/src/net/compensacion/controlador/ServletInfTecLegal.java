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

import net.compensacion.entidad.InformeTecnicoLegal;
import net.compensacion.service.InfoTecnicoLegalService;


/**
 * Servlet implementation class ServletInfTecLegal
 */
@WebServlet("/ServletInfTecLegal")
public class ServletInfTecLegal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private InfoTecnicoLegalService infoTecnicoLegalService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInfTecLegal() {
        super();
        infoTecnicoLegalService= new InfoTecnicoLegalService();
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
		List<InformeTecnicoLegal> data=infoTecnicoLegalService.listarCompleTL();
		request.setAttribute("inftecnlegal", data);

		List<InformeTecnicoLegal> data2=infoTecnicoLegalService.listarSoliSinITL();
		request.setAttribute("solicitantes", data2);
		
		int cod=infoTecnicoLegalService.cargarCodigo();
		request.setAttribute("codigo", cod);	
		request.getRequestDispatcher("/InformeTecnicoLegal.jsp").forward(request,response);
		
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codi=infoTecnicoLegalService.cargarCodigo();
		String obj,an,liq,liq1,id_usuario;
		String cod,codExp,codiITLModi;
		
		cod=request.getParameter("codiITL");
		codExp=request.getParameter("codiExp");
		obj=request.getParameter("objetivos");
		an=request.getParameter("analisis");
		liq=request.getParameter("liquida");
		id_usuario=request.getParameter("codiUsuario");
		codiITLModi=request.getParameter("codiITLModi");
		
		DateFormat fecha = new SimpleDateFormat("yy-MM-dd");
		DateFormat hora = new SimpleDateFormat("kk:mm:ss ");
		Date dateobj = new Date();
		
		if(codi==Integer.parseInt(cod)){//REGISTRAR		
			InformeTecnicoLegal bean=new InformeTecnicoLegal();
			bean.setCodigo(Integer.parseInt(cod));
			bean.setCodExp(Integer.parseInt(codExp));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setObjetivo(obj);
			bean.setAnalisis(an);
			bean.setLiquidacion(liq);
			bean.setId_usuario(id_usuario);
			
			int salida;
			salida=infoTecnicoLegalService.registrarITL(bean);
		
			if(salida>0) {
				request.setAttribute("MENSAJE", "Se registró Informe Técnico Legal correctamente");
				
			}
			else
				request.setAttribute("MENSAJE", "Error en el registro de Informe Técnico Legal");	
			
			listar(request, response);
		}
		
		else  {//ACTUALIZAR
			liq1=request.getParameter("liquida1");
			InformeTecnicoLegal bean=new InformeTecnicoLegal();
			bean.setCodigo(Integer.parseInt(codiITLModi));
			bean.setCodExp(Integer.parseInt(codExp));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setObjetivo(obj);
			bean.setAnalisis(an);
			
			if(!liq.isEmpty()) {
				bean.setLiquidacion(liq);
			}
			if (liq.isEmpty()) {
				bean.setLiquidacion(liq1);	
			}
			
			bean.setId_usuario(id_usuario);
			
			int salida;
			salida=infoTecnicoLegalService.actualizarITL(bean);
	
			if(salida>0)
				request.setAttribute("MENSAJE", "Se actualizó Informe Técnico Legal correctamente");
			else
				request.setAttribute("MENSAJE", "Error en la actualización de Informe Técnico Legal");	
	
			listar(request, response);		
		}
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod=request.getParameter("codigoEliminar");
		String codExp=request.getParameter("codigoExpe");
		int salida=infoTecnicoLegalService.eliminarITL(Integer.parseInt(cod), Integer.parseInt(codExp));
		if(salida!=-1)
			request.setAttribute("MENSAJE", "Se eliminó Informe Técnico Legal correctamente");
		else
			request.setAttribute("MENSAJE", "Error en la eliminación de Informe Técnico Legal");
		listar(request, response);	
	}

}
