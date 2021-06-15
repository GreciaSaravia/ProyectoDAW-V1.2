package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.Expediente;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.ExpAreaDAO;

public class ExpAreaService {
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	ExpAreaDAO daoArea=fabrica.getExpAreaDAO();
	
	public String identificarArea (String codExp) {
		return daoArea.idenArea(codExp);
	};
	
	public List<Expediente> listarCompleExpXCod (String cod) {
		return daoArea.listCompleExpXCod(cod);
	};
		
	public List<Expediente> listExpTerminadosReporte(){
		return daoArea.listExpTerminadosReporte();
	}
		
}
