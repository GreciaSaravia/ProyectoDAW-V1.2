package net.compensacion.interfaces;

import java.util.List;

import net.compensacion.entidad.Solicitante;

public interface SolicitanteDAO {

	public List<Solicitante> ListSolicitanteSinSolicitud();
	public int addSolicitante(Solicitante bean);
	public int returnCodigo();
	public String validarDni(String dni);
	public String validarDniUpdate(String dni, String codigo);
	public int ReturnCodigoDistrito(int distrito, int provincia);
	public List<Solicitante> ListAllUsuarios();
	public int returnCantidadSolicitante();
	public Solicitante firstSolicitante();
	public Solicitante SolicitanteByCodigo(String codigo);
	public int updateSolicitante(Solicitante bean);
	public int deleteSolicitante(String codigo);
	public List<Solicitante> ListSolicitanteByApellido(String apellido, int codSoli);
	public List<Solicitante> ListSolicitanteByApellidoSinSolicitud(String apellido);
	public String findExp(int codSoli);
}
