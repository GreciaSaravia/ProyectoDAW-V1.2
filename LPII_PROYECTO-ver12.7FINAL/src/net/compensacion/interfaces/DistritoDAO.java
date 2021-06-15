package net.compensacion.interfaces;

import java.util.List;

import net.compensacion.entidad.Distrito;

public interface DistritoDAO {
	public List<Distrito> listAll(int codProvincia);
}
