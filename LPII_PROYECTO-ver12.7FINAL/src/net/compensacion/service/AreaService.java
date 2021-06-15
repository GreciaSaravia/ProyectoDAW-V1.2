package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.Area;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.AreaDAO;

public class AreaService {
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	
	//Definir uno o mas interfaces interfaz 
	AreaDAO daoArea=fabrica.getAreaDAO();
	
	public List<Area> listAll() {
		return daoArea.listAll();
	}

}
