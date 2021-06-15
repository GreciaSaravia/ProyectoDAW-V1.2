package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.Resolucion;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.ResolucionDAO;

public class ResolucionService {
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	ResolucionDAO daoRes=fabrica.getResolucionDAO();
	
	public int registrarRes (Resolucion res) {
		return daoRes.saveRes(res);
	};
	
	public int actualizarRes (Resolucion res){
		return daoRes.updateRes(res);
	};
	
	public int eliminarRes (int codigo,int codExp){
		return daoRes.deleteRes(codigo, codExp);
	};
	
	public int cargarCodigo(){
		return daoRes.findCod();
	};
	
	public List<Resolucion> listarCompleRes (){
		return daoRes.listCompleRes();
	};
	
	public List<Resolucion> listarCompleResxcod (String cod){
		return daoRes.listCompleResxcod(cod);
	};
	
	public List<Resolucion> listarSoliSinRes (){
		return daoRes.listSoliSinRes();
	};
	public List<Resolucion> listaResReporte(){
		return daoRes.listaResReporte();
	}
	
}
