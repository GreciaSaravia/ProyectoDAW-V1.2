package net.compensacion.controlador;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import net.compensacion.entidad.Solicitante;
import net.compensacion.entidad.Solicitud;
import net.compensacion.entidad.Usuario;
import net.compensacion.service.SolicitanteService;
import net.compensacion.service.UsuarioService;


@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuarioService usuarioService;
	
    public ServletUsuario() {
        super();
        usuarioService = new UsuarioService();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Recuperar la accion "parametro" que viene de la pagina "docente.jsp"
		
		// TODO Auto-generated method stub
		String tipo=request.getParameter("accion");
    	if(tipo.equals("LISTAR"))
    		listar(request,response);
    	else if(tipo.equals("GUARDAR"))
    		guardar(request,response);
    	else if(tipo.equals("ELIMINAR"))
    		eliminar(request,response);
		
	}
	
	private void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int codigo=usuarioService.returnCodigo();
		
		String codUsuario;

		String cod,codUsuarioAct,nom, apePat, apeMat, dni, area, user, pass, perfil, dir, tel, correo, dis, prov, foto, cargo;
		
		if(codigo==-1)
			codUsuario="U0001";
		else {
			DecimalFormat objDF = new DecimalFormat ("0000");
			String  result = objDF.format (codigo);
			codUsuario="U"+result;
		}
		
		cod=request.getParameter("codiUsuario");
		nom=request.getParameter("nombres");
		apePat=request.getParameter("apePat");
		apeMat=request.getParameter("apeMat");
		dni=request.getParameter("dni");
		area=request.getParameter("area");
		user=request.getParameter("usuario");
		pass=request.getParameter("contraseña");
		perfil=request.getParameter("perfil");
		dir=request.getParameter("direccion");
		dis=request.getParameter("distrito");
		prov=request.getParameter("provincia");
		tel=request.getParameter("telefono");
		correo=request.getParameter("correo");
		cargo=request.getParameter("cargo");	
		foto=request.getParameter("dataurl");
		codUsuarioAct=request.getParameter("codiUsuarioModi");	
		
		String head = "data:image/jpeg;base64,";
		String base64 = foto.substring(head.length()-1);
		byte[] fotoUsuario=DatatypeConverter.parseBase64Binary(base64);
		
		DateFormat fecha = new SimpleDateFormat("yy-MM-dd");
		DateFormat hora = new SimpleDateFormat("kk:mm:ss ");
		Date dateobj = new Date();
		
		String dniDup=usuarioService.validarDni(dni);
		String dniDupAct=usuarioService.validarDniUpdate(dni, codUsuarioAct);
		
		
		if(cod.equals(codUsuario)){//REGISTRAR	
			
			if(dniDup.equals("")) {
				
				Usuario bean=new Usuario();
				
				bean.setId(cod);
				bean.setNom(nom);
				bean.setApePat(apePat);
				bean.setApeMat(apeMat);
				bean.setDni(dni);
				bean.setCodCargo(usuarioService.ReturnCodigoCargo(cargo));
				bean.setCuenta(user);
				bean.setContraseña(pass);
				bean.setIdPerfil(Integer.parseInt(perfil));//pruebas
				bean.setDir(dir);
				bean.setCodDist(usuarioService.ReturnCodigoDistrito(Integer.parseInt(dis), Integer.parseInt(prov)));//pruebas
				bean.setTel(tel);
				bean.setCorreo(correo);
				bean.setFecha(fecha.format(dateobj));
				bean.setHora(hora.format(dateobj));
				bean.setFoto(fotoUsuario);
				
				int salida;
				salida=usuarioService.addUsuario(bean);
				
				if(salida>0)
					request.setAttribute("MENSAJE", "Se registró el Usuario correctamente");
				else 
					request.setAttribute("MENSAJE", "Error en el registro del usuario");
		
				listar(request, response);
			}else {
				request.setAttribute("MENSAJE", "Ya se encuentra registrado el DNI ingresado");
				listar(request, response);
			}
			

		}else {
			
			if(dniDupAct.equals("")) {
				Usuario bean=new Usuario();
				
				bean.setId(codUsuarioAct);
				bean.setNom(nom);
				bean.setApePat(apePat);
				bean.setApeMat(apeMat);
				bean.setDni(dni);
				bean.setCodCargo(usuarioService.ReturnCodigoCargo(cargo));
				bean.setCuenta(user);
				bean.setContraseña(pass);
				bean.setIdPerfil(Integer.parseInt(perfil));//pruebas
				bean.setDir(dir);
				bean.setCodDist(usuarioService.ReturnCodigoDistrito(Integer.parseInt(dis), Integer.parseInt(prov)));//pruebas
				bean.setTel(tel);
				bean.setCorreo(correo);
				bean.setFecha(fecha.format(dateobj));
				bean.setHora(hora.format(dateobj));
				bean.setFoto(fotoUsuario);
				
				int salida;
				salida=usuarioService.updateUsuario(bean);
				
				if(salida>0)
					request.setAttribute("MENSAJE", "Se actualizó el Usuario correctamente");
				else 
					request.setAttribute("MENSAJE", "Error en la actualización del usuario");
		
				listar(request, response);
			}else {
				
				request.setAttribute("MENSAJE", "Ya se encuentra registrado el DNI ingresado");
				listar(request, response);
				
			}
	
		}
			
	}

	private void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cod=request.getParameter("codigoEliminar");
		int salida=usuarioService.deleteUsuario(cod);
		if(salida> 0)
			request.setAttribute("MENSAJE", "Se eliminó Usuario correctamente");
		else
			request.setAttribute("MENSAJE", "Error en la eliminación del Usuario");
		listar(request, response);		
			
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Objeto para almacenar el valor de retorno del metodo listAll
		
		List<Usuario> lista=usuarioService.ListAllUsuarios();
		
		
		int codigo=usuarioService.returnCodigo();
		request.setAttribute("codigo", codigo);
		
		request.setAttribute("listaUsuarios",lista );
				
		request.getRequestDispatcher("/Usuario.jsp").forward(request, response);
		
	}
	
	public void retornaCodigo(HttpServletRequest request, HttpServletResponse response) {
		int codigo=usuarioService.returnCodigo();

		if(codigo==-1)
			request.setAttribute("codigo", "U0001");
		else {
			DecimalFormat objDF = new DecimalFormat ("0000");
			String  result = objDF.format (codigo);
			request.setAttribute("codigo", "U"+result);
		}
	}

}
