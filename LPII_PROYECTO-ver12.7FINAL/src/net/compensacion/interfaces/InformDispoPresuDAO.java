package net.compensacion.interfaces;

import java.util.List;

import net.compensacion.entidad.InformeDisponibilidadPresupuestal;


public interface InformDispoPresuDAO {
	public int saveIDP (InformeDisponibilidadPresupuestal infDP);
	public int updateIDP (InformeDisponibilidadPresupuestal infDP);
	public int deleteIDP (int codigo,int codExp);
	public int findCod();
	public List<InformeDisponibilidadPresupuestal> listCompleIDP ();
	public List<InformeDisponibilidadPresupuestal> listCompleIDPxcod (String cod);
	public List<InformeDisponibilidadPresupuestal> listSolisinIDP ();
	public List<InformeDisponibilidadPresupuestal> listMontoCompen (String cod);
	public List<InformeDisponibilidadPresupuestal> listaIDPReporte();
}
