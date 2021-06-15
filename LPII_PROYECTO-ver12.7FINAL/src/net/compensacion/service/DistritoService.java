package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.Distrito;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.DistritoDAO;


public class DistritoService {

	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	//Definir uno o mas interfaces interfaz 
	DistritoDAO daoDistrito=fabrica.getDistritoDAO();
	
	public List<Distrito> listAll(int codProvincia) {
		return daoDistrito.listAll(codProvincia);
	}
	
}
