package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.Departamento;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.DepartamentoDAO;

public class DepartamentoService {
	
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	
	//Definir uno o mas interfaces interfaz 
	DepartamentoDAO daoDepartamento=fabrica.getDepartamentoDAO();
	
	public List<Departamento> listAll() {
		return daoDepartamento.listAll();
	}
}
