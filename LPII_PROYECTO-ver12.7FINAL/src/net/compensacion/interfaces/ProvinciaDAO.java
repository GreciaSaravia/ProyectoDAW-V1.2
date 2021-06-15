package net.compensacion.interfaces;

import java.util.List;

import net.compensacion.entidad.Provincia;


public interface ProvinciaDAO {
	
	public List<Provincia> listAll(int codDep);

}
