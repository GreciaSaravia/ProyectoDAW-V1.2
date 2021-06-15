package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.InformeDisponibilidadPresupuestal;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.InformDispoPresuDAO;

public class InfoDisponPresuService {
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	InformDispoPresuDAO daoIDP=fabrica.getInformDispoPresuDAO();
	
	public int registrarIDP (InformeDisponibilidadPresupuestal infDP) {
		return daoIDP.saveIDP(infDP);
	};
	
	public int actualizarIDP (InformeDisponibilidadPresupuestal infDP){
		return daoIDP.updateIDP(infDP);
	};
	
	public int eliminarIDP (int codigo,int codExp){
		return daoIDP.deleteIDP(codigo, codExp);
	};
	
	public int cargarCodigo(){
		return daoIDP.findCod();
	};
	
	public List<InformeDisponibilidadPresupuestal> listarCompleIDP (){
		return daoIDP.listCompleIDP();
	};
	
	public List<InformeDisponibilidadPresupuestal> listarCompleIDPxcod (String cod){
		return daoIDP.listCompleIDPxcod(cod);
	};
	
	public List<InformeDisponibilidadPresupuestal> listarSolisinIDP (){
		return daoIDP.listSolisinIDP();
	};
	
	public List<InformeDisponibilidadPresupuestal> listarMontoCompen (String cod){
		return daoIDP.listMontoCompen(cod);
	};
	
	public List<InformeDisponibilidadPresupuestal> listaIDPReporte(){
		return daoIDP.listaIDPReporte();
	}
	
}
