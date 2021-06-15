package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.Menu;
import net.compensacion.entidad.Opciones;
import net.compensacion.entidad.Sesion;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.SesionDAO;

public class SesionService {
	
	//Definir gestor de base de datos
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	
	//Definir uno o mas interfaces interfaz 
	SesionDAO daoSesion=fabrica.getSesionDAO();
	
	public Sesion Sesion(String cuenta, String contraseña) {
		return daoSesion.iniciarSesion(cuenta, contraseña);
	}
	
	public List<Menu> traerMenus(int codPerfil){
		return daoSesion.menuPerfil(codPerfil);
	}
	
	public List<Opciones> traerOpciones(int codigoPerfil, int codMenu){
		return daoSesion.opcionesPerfil(codigoPerfil, codMenu);
	}
	
}
