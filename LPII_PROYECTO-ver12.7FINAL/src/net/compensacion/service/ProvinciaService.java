package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.Provincia;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.ProvinciaDAO;

public class ProvinciaService {
	
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	//Definir uno o mas interfaces interfaz 
	ProvinciaDAO daoProvincia=fabrica.getProvinciaDAO();
	
	public List<Provincia> listAll(int codDep) {
		return daoProvincia.listAll(codDep);
	}
	
}
