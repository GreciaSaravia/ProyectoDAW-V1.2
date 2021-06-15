package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.InformeLegal;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.InformLegalDAO;

public class InfoLegalService {
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	InformLegalDAO daoIL=fabrica.getInformLegalDAO();
	
	public int registrarIL (InformeLegal infLegal) {
		return daoIL.saveIL(infLegal);
	};
	
	public int actualizarIL (InformeLegal infLegal) {
		return daoIL.updateIL(infLegal);
	};
	
	public int eliminarIL (int codigo,int codExp) {
		return daoIL.deleteIL(codigo, codExp);
	};
	
	public int cargarCodigo(){
		return daoIL.findCod();
	};
	
	public List<InformeLegal> listarCompleIL (){
		return daoIL.listCompleIL();
	};
	
	public List<InformeLegal> listarCompleILxcod (String cod){
		return daoIL.listCompleILxcod(cod);
	};
	
	public List<InformeLegal> listarSoliSinIL (){
		return daoIL.listSoliSinIL();
	};
	
	public List<InformeLegal> listILReporte(){
		return daoIL.listILReporte();
	}
	
}
