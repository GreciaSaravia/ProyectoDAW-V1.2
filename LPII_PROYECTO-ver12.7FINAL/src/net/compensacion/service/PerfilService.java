package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.Perfil;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.PerfilDAO;

public class PerfilService {
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	
	//Definir uno o mas interfaces interfaz 
	PerfilDAO daoPerfil=fabrica.getPerfilDAO();
	
	public List<Perfil> listAll() {
		return daoPerfil.listAll();
	}
}
