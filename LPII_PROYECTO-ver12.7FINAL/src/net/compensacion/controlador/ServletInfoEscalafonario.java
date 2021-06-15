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

import net.compensacion.entidad.InformeEscalafonario;
import net.compensacion.service.InfoEscalafonarioService;


/**
 * Servlet implementation class ServletInfoEscalafonario
 */
@WebServlet("/ServletInfoEscalafonario")
public class ServletInfoEscalafonario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    private InfoEscalafonarioService infoEscalafonarioService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInfoEscalafonario() {
        super();
        infoEscalafonarioService= new InfoEscalafonarioService();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tipo=request.getParameter("accion");
    	if(tipo.equals("LISTAR"))
    		listar(request,response);
    	else if(tipo.equals("GUARDAR"))
    		guardar(request,response);
    	else if(tipo.equals("ELIMINAR"))
    		eliminar(request,response);
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<InformeEscalafonario> data=infoEscalafonarioService.ListarTodoIE();
		request.setAttribute("infescala", data);

		List<InformeEscalafonario> data2=infoEscalafonarioService.listarIESinIE();
		request.setAttribute("solicitantes", data2);
		
		int cod=infoEscalafonarioService.cargarCodigo();
		request.setAttribute("codigo", cod);	
		request.getRequestDispatcher("/InformeEscalafonario.jsp").forward(request,response);
		
	}

	private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codi=infoEscalafonarioService.cargarCodigo();
		String tp,ca,an,rlsgr,rd,id_usuario;
		String cod,codExp,cod_es_mag,cod_grup_ocp,cod_jor_labo,ts_uc,tst,codIEAct;
		
		cod=request.getParameter("codiIE");
		codExp=request.getParameter("codiExp");
		tp=request.getParameter("tituloprofesional");
		ca=request.getParameter("cargoactual");
		cod_es_mag=request.getParameter("escalamagisterial");
		cod_grup_ocp=request.getParameter("ocupacional");
		cod_jor_labo=request.getParameter("jornada");
		ts_uc=request.getParameter("tieds");
		tst=request.getParameter("tiest");
		an=request.getParameter("ant");
		rlsgr=request.getParameter("reslicsingocerem");
		rd=request.getParameter("resdemerito");
		id_usuario=request.getParameter("codiUsuario");
		codIEAct=request.getParameter("codiIEModi");
		
		DateFormat fecha = new SimpleDateFormat("yy-MM-dd");
		DateFormat hora = new SimpleDateFormat("kk:mm:ss ");
		Date dateobj = new Date();
		
		if(codi==Integer.parseInt(cod)){//REGISTRAR		
			InformeEscalafonario bean=new InformeEscalafonario();
			bean.setCodigo(Integer.parseInt(cod));
			bean.setCodExp(Integer.parseInt(codExp));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setTp(tp);
			bean.setCa(ca);
			bean.setAn(an);
			bean.setRd(rd);
			bean.setRlsgr(rlsgr);
			bean.setTs_uc(Integer.parseInt(ts_uc));
			bean.setTst(Integer.parseInt(tst));
			bean.setCod_es_mag(Integer.parseInt(cod_es_mag));
			bean.setCod_grup_ocp(Integer.parseInt(cod_grup_ocp));
			bean.setCod_jor_labo(Integer.parseInt(cod_jor_labo));
			bean.setId_usuario(id_usuario);
		
			int salida;
			salida=infoEscalafonarioService.registrarIE(bean);
		
			if(salida>0) {
				request.setAttribute("MENSAJE", "Se registró Informe Escalafonario correctamente");
				
			}
			else
				request.setAttribute("MENSAJE", "Error en el registro de Informe Escalafonario");	
			
			listar(request, response);
		}
		
		else  {//ACTUALIZAR
		
			InformeEscalafonario bean=new InformeEscalafonario();
			bean.setCodigo(Integer.parseInt(codIEAct));
			bean.setCodExp(Integer.parseInt(codExp));
			bean.setFecha(fecha.format(dateobj));
			bean.setHora(hora.format(dateobj));
			bean.setTp(tp);
			bean.setCa(ca);
			bean.setAn(an);
			bean.setRd(rd);
			bean.setRlsgr(rlsgr);
			bean.setTs_uc(Integer.parseInt(ts_uc));
			bean.setTst(Integer.parseInt(tst));
			bean.setCod_es_mag(Integer.parseInt(cod_es_mag));
			bean.setCod_grup_ocp(Integer.parseInt(cod_grup_ocp));
			bean.setCod_jor_labo(Integer.parseInt(cod_jor_labo));
			bean.setId_usuario(id_usuario);
		
			int salida;
			salida=infoEscalafonarioService.actualizarIE(bean);
	
			if(salida>0)
				request.setAttribute("MENSAJE", "Se actualizó Informe Escalafonario correctamente");
			else
				request.setAttribute("MENSAJE", "Error en la actualización de Informe Escalafonario");	
	
			listar(request, response);		
		}
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cod=request.getParameter("codigoEliminar");
		String codExp=request.getParameter("codigoExpe");
		int salida=infoEscalafonarioService.eliminarIE(Integer.parseInt(cod), Integer.parseInt(codExp));
		if(salida!=-1)
			request.setAttribute("MENSAJE", "Se eliminó Informe Escalafonario correctamente");
		else
			request.setAttribute("MENSAJE", "Error en la eliminación de Informe Escalafonario");
		listar(request, response);	
	}

}
