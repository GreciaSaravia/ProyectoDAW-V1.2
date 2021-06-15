package net.compensacion.interfaces;


import java.util.List;

import net.compensacion.entidad.Menu;
import net.compensacion.entidad.Opciones;
import net.compensacion.entidad.Sesion;


public interface SesionDAO {
	public Sesion iniciarSesion(String cuenta, String contraseña);
	public List<Menu> menuPerfil(int codigoPerfil);
	public List<Opciones> opcionesPerfil(int codigoPerfil, int codMenu);
}
