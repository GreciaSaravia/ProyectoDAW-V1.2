package net.compensacion.interfaces;

import java.util.List;

import net.compensacion.entidad.InformeTecnicoLegal;


public interface InformTecnicoLegalDAO {
	public int saveITL (InformeTecnicoLegal infTL);
	public int updateITL (InformeTecnicoLegal infTL);
	public int deleteITL (int codigo,int codExp);
	public int findCod();
	public List<InformeTecnicoLegal> listCompleTL ();
	public List<InformeTecnicoLegal> listCompleITLxcod (String cod);
	public List<InformeTecnicoLegal> listSoliSinITL ();
	public List<InformeTecnicoLegal> listaItlReporte();
}
