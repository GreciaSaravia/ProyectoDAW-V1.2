package net.compensacion.interfaces;

import java.util.ArrayList;
import java.util.List;

import net.compensacion.entidad.Expediente;
import net.compensacion.entidad.Requisitos;
import net.compensacion.entidad.Solicitud;

public interface SolicitudDAO {
	public int returnCodigo();
	public int addSolicitud(Solicitud bean, Requisitos req, Expediente exp);
	public List<Solicitud> ListAllSolicitud();
	public int updateSolicitud(Solicitud bean, Requisitos req);
	public int deleteSolicitud(String codigo);
	public List<Solicitud> ListSolicitudxCodi(String cod);
	public List<Solicitud> ListAllSolicitudReporte();
}
