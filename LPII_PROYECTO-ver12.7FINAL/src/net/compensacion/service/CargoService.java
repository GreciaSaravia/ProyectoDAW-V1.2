package net.compensacion.service;

import net.compensacion.entidad.Cargo;
import net.compensacion.fabrica.DAOFactory;

import net.compensacion.interfaces.CargoDAO;

public class CargoService {
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	
	//Definir uno o mas interfaces interfaz 
	CargoDAO daoCargo=fabrica.getCargoDao();
	
	public Cargo returnCargo(int codArea) {
		return daoCargo.returnCargo(codArea);
	}
}
