package net.compensacion.interfaces;

import java.util.List;

import net.compensacion.entidad.InformeLegal;


public interface InformLegalDAO {
	public int saveIL (InformeLegal infLegal);
	public int updateIL (InformeLegal infLegal);
	public int deleteIL (int codigo,int codExp);
	public int findCod();
	public List<InformeLegal> listCompleIL ();
	public List<InformeLegal> listCompleILxcod (String cod);
	public List<InformeLegal> listSoliSinIL ();
	public List<InformeLegal> listILReporte();
}
