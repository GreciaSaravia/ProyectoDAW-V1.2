package net.compensacion.interfaces;

import java.util.List;

import net.compensacion.entidad.EscalaMagis;
import net.compensacion.entidad.GrupoOcupa;
import net.compensacion.entidad.JornadaLaboral;

public interface InfEscCboDAO {
	public List<EscalaMagis> listAllEscalaMagis();
	public List<GrupoOcupa> listAllGrupoOcupa();
	public List<JornadaLaboral> listAllJornadaLaboral();
}
