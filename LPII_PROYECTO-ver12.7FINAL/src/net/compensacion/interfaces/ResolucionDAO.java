package net.compensacion.interfaces;

import java.util.ArrayList;
import java.util.List;

import net.compensacion.entidad.Expediente;
import net.compensacion.entidad.Resolucion;


public interface ResolucionDAO {
	public int saveRes (Resolucion res);
	public int updateRes (Resolucion res);
	public int deleteRes (int codigo,int codExp);
	public int findCod();
	public List<Resolucion> listCompleRes ();
	public List<Resolucion> listCompleResxcod (String cod);
	public List<Resolucion> listSoliSinRes ();
	public List<Resolucion> listaResReporte();
	
}
