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

import net.compensacion.entidad.InformeLegal;
import net.compensacion.service.InfoLegalService;

/**
 * Servlet implementation class ServletInfLegal
 */
@WebServlet("/ServletInfLegal")
public class ServletInfLegal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private InfoLegalService infoLegalService;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInfLegal() {
        super();
        infoLegalService= new InfoLegalService();
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
		List<InformeLegal> data=infoLegalService.listarCompleIL();
		request.setAttribute("inflegal", data);

		List<InformeLegal> data2=infoLegalService.listarSoliSinIL();
		request.setAttribute("solicitantes", data2);
		
		int cod=infoLegalService.cargarCodigo();
		request.setAttribute("codigo", cod);	
		request.getRequestDispatcher("/InformeLegal.jsp").forward(request,response);
		
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codi=infoLegalService.cargarCodigo();
		String cod,codExp,ant,ped,con,bl,exp,sug,codModi,id_usuario;
		
		cod=request.getParameter("codiIL");
		codExp=request.getParameter("codiExp");
		ant=request.getParameter("antecedentes");
		ped=request.getParameter("pedido");
		bl=request.getParameter("baselegal");
		exp=request.getParameter("exposicion");
		con=request.getParameter("conclusiones");
		sug=request.getParameter("sugerencias");
		id_usuario=request.getParameter("codiUsuario");
		codModi=request.getParameter("codiILModi");
		
		DateFormat fecha = new SimpleDateFormat("yy-MM-dd");
		DateFormat hora = new SimpleDateFormat("kk:mm:ss ");
		Date dateobj = new Date();
		
		if(codi==Integer.parseInt(cod)){//REGISTRAR		
			InformeLegal bean=new InformeLegal();
			bean.setCodigo(Integer.parseInt(cod));
			bean.setCodExp(Integer.parseInt(codExp));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setAntecedente(ant);
			bean.setPedido(ped);
			bean.setBaseLegal(bl);
			bean.setExposicion(exp);
			bean.setConclusiones(con);
			bean.setSugerencias(sug);
			bean.setId_usuario(id_usuario);
		
			int salida;
			salida=infoLegalService.registrarIL(bean);
		
			if(salida>0) {
				request.setAttribute("MENSAJE", "Se registró Informe Legal correctamente");
				
			}
			else
				request.setAttribute("MENSAJE", "Error en el registro de Informe Legal");	
			
			listar(request, response);
		}
		
		else  {//ACTUALIZAR
			InformeLegal bean=new InformeLegal();
			bean.setCodigo(Integer.parseInt(codModi));
			bean.setCodExp(Integer.parseInt(codExp));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setAntecedente(ant);
			bean.setPedido(ped);
			bean.setBaseLegal(bl);
			bean.setExposicion(exp);
			bean.setConclusiones(con);
			bean.setSugerencias(sug);
			bean.setId_usuario(id_usuario);
		
			int salida;
			salida=infoLegalService.actualizarIL(bean);
	
			if(salida>0)
				request.setAttribute("MENSAJE", "Se actualizó Informe Legal correctamente");
			else
				request.setAttribute("MENSAJE", "Error en la actualización de Informe Legal");	
	
			listar(request, response);		
		}
		
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod=request.getParameter("codigoEliminar");
		String codExp=request.getParameter("codigoExpe");
		int salida=infoLegalService.eliminarIL(Integer.parseInt(cod), Integer.parseInt(codExp));
		if(salida!=-1)
			request.setAttribute("MENSAJE", "Se eliminó Informe Legal correctamente");
		else
			request.setAttribute("MENSAJE", "Error en la eliminación de Informe Legal");
		listar(request, response);	
	}

}
