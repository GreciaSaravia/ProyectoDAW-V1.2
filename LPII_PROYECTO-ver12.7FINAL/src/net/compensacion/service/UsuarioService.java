package net.compensacion.service;
import java.util.ArrayList;
import java.util.List;

import net.compensacion.entidad.Usuario;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.UsuarioDAO;

public class UsuarioService {
	//Definir gestor de base de datos
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
		
	//Definir uno o mas interfaces interfaz 
	UsuarioDAO daoSesion=fabrica.getUsuarioDAO();
	
	public int addUsuario(Usuario bean) {
		return daoSesion.addUsuario(bean);
	}
	
	public int returnCodigo() {
		return daoSesion.returnCodigo();
	}
	
	public int ReturnCodigoCargo(String cargo) {
		return daoSesion.ReturnCodigoCargo(cargo);
	}
	
	public int ReturnCodigoPerfil(String perfil) {
		return daoSesion.ReturnCodigoPerfil(perfil);
	}
	
	public int ReturnCodigoDistrito(int distrito, int departamento) {
		return daoSesion.ReturnCodigoDistrito(distrito, departamento);
	}
	
	public List<Usuario> ListAllUsuarios(){
		return daoSesion.ListAllUsuarios();
	}
	
	public Usuario firstUsuarios() {
		return daoSesion.firstUsuarios();
	}
	
	public Usuario UsuariosByCodigo(String codigo) {
		return daoSesion.UsuariosByCodigo(codigo);
	}
	
	public int returnCantidadUsuario() {
		return daoSesion.returnCantidadUsuario();
	}
	
	public int updateUsuario(Usuario bean) {
		return daoSesion.updateUsuario(bean);
	}
	
	public int deleteUsuario(String codigo) {
		return daoSesion.deleteUsuario(codigo);
	}
	
	public List<Usuario> ListUsuariosByApellido(String apellido, String codUsuario){
		return daoSesion.ListUsuariosByApellido(apellido, codUsuario);
	}
	
	public String validarDni(String dni) {
		return daoSesion.validarDni(dni);
	}
	
	public String validarDniUpdate(String dni, String codigo) {
		return daoSesion.validarDniUpdate(dni, codigo);
	}
		
}
























