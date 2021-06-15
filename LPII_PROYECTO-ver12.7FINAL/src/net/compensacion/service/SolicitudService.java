package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.Expediente;
import net.compensacion.entidad.Requisitos;
import net.compensacion.entidad.Solicitud;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.SolicitudDAO;

public class SolicitudService {
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	SolicitudDAO daoSolicitud=fabrica.getSolicitudDAO();
	
	public int cargarCodigo() {
		return daoSolicitud.returnCodigo();
	};
	public int registrarSolicitud(Solicitud bean, Requisitos req, Expediente exp) {
		return daoSolicitud.addSolicitud(bean, req, exp);
	};
	public List<Solicitud> ListarTodoSolicitud(){
		return daoSolicitud.ListAllSolicitud();
	};
	public int actualizarSolicitud(Solicitud bean, Requisitos req) {
		return daoSolicitud.updateSolicitud(bean, req);
	};
	public int eliminarSolicitud(String codigo) {
		return daoSolicitud.deleteSolicitud(codigo);
	};
	
	public List<Solicitud> ListarSolicitudxCodi(String cod){
		return daoSolicitud.ListSolicitudxCodi( cod);
	};
	
	public List<Solicitud> ListAllSolicitudReporte(){
		return daoSolicitud.ListAllSolicitudReporte();
	}

}
