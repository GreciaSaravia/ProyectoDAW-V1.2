package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.InformeTecnicoLegal;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.InformTecnicoLegalDAO;

public class InfoTecnicoLegalService {
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	InformTecnicoLegalDAO daoITL=fabrica.getInformTecnicoLegalDAO();
	
	
	public int registrarITL (InformeTecnicoLegal infTL) {
		return daoITL.saveITL (infTL);
	};
	
	public int actualizarITL (InformeTecnicoLegal infTL) {
		return daoITL.updateITL(infTL);
	};
	
	public int eliminarITL (int codigo,int codExp) {
		return daoITL.deleteITL(codigo, codExp);
	};
	
	public int cargarCodigo() {
		return daoITL.findCod();
	};
	
	public List<InformeTecnicoLegal> listarCompleTL (){
		return daoITL.listCompleTL ();
	};
	
	public List<InformeTecnicoLegal> listarCompleITLxcod (String cod){
		return daoITL.listCompleITLxcod (cod);
	};
	
	public List<InformeTecnicoLegal> listarSoliSinITL (){
		return daoITL.listSoliSinITL ();
	};
	
	public List<InformeTecnicoLegal> listaItlReporte(){
		return daoITL.listaItlReporte();
	}
}
