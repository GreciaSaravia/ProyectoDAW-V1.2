package net.compensacion.interfaces;

import java.util.List;

import net.compensacion.entidad.InformeEscalafonario;

public interface InformEscalafonarioDAO {
	public int saveIE (InformeEscalafonario infEsc);
	public int updateIE (InformeEscalafonario infEsc);
	public int deleteIE (int codigo,int codExp);
	public int findCod();
	public List<InformeEscalafonario> listCompleIE ();
	public List<InformeEscalafonario> listCompleIExcod (String cod);
	public List<InformeEscalafonario> listIESinIE ();
	public List<InformeEscalafonario> listIEReporte();
}
