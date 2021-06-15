package net.compensacion.interfaces;

import java.util.List;

import net.compensacion.entidad.Expediente;


public interface ExpAreaDAO {
	public String idenArea (String codExp);
	public List<Expediente> listCompleExpXCod (String cod);
	public List<Expediente> listExpTerminadosReporte();
}
