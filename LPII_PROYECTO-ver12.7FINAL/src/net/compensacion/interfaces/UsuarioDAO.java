package net.compensacion.interfaces;


import java.util.List;

import net.compensacion.entidad.Usuario;


public interface UsuarioDAO {
	public int addUsuario(Usuario bean);
	public int returnCodigo();
	public int ReturnCodigoCargo(String cargo);
	public int ReturnCodigoPerfil(String perfil);
	public int ReturnCodigoDistrito(int distrito, int provincia);
	public List<Usuario> ListAllUsuarios();
	public Usuario firstUsuarios();
	public Usuario UsuariosByCodigo(String codigo);
	public int returnCantidadUsuario();
	public int updateUsuario(Usuario bean);
	public int deleteUsuario(String codigo);
	public List<Usuario> ListUsuariosByApellido(String apellido, String codUsuario); 
	public String validarDni(String dni);
	public String validarDniUpdate(String dni, String codigo);
}
