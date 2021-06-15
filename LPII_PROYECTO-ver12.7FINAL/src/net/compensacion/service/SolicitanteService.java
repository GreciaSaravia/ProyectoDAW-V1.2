package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.Solicitante;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.SolicitanteDAO;

public class SolicitanteService {
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	SolicitanteDAO daoSolicitante=fabrica.getSolicitanteDAO();
	
	public List<Solicitante> ListarSolicitanteSinSolicitud(){
		return daoSolicitante.ListSolicitanteSinSolicitud();
	};
	public int addSolicitante(Solicitante bean) {
		return daoSolicitante.addSolicitante(bean);
	}
	public int returnCodigo() {
		return daoSolicitante.returnCodigo();
	}
	public String validarDni(String dni) {
		return daoSolicitante.validarDni(dni);
	}
	public String validarDniUpdate(String dni, String codigo) {
		return daoSolicitante.validarDniUpdate(dni, codigo);
	}
	public int ReturnCodigoDistrito(int distrito, int provincia) {
		return daoSolicitante.ReturnCodigoDistrito(distrito, provincia);
	}
	public List<Solicitante> ListAllUsuarios(){
		return daoSolicitante.ListAllUsuarios();
	}
	public int returnCantidadSolicitante() {
		return daoSolicitante.returnCantidadSolicitante();
	}
	public Solicitante firstSolicitante() {
		return daoSolicitante.firstSolicitante();
	}
	public Solicitante SolicitanteByCodigo(String codigo) {
		return daoSolicitante.SolicitanteByCodigo(codigo);
	}
	public int updateSolicitante(Solicitante bean) {
		return daoSolicitante.updateSolicitante(bean);
	}
	public int deleteSolicitante(String codigo) {
		return daoSolicitante.deleteSolicitante(codigo);
	}
	public List<Solicitante> ListSolicitanteByApellido(String apellido, int codSoli){
		return daoSolicitante.ListSolicitanteByApellido(apellido, codSoli);
	}
	public List<Solicitante> ListSolicitanteByApellidoSinSolicitud(String apellido){
		return daoSolicitante.ListSolicitanteByApellidoSinSolicitud(apellido);
	}
	public String findExp(int codSoli) {
		return daoSolicitante.findExp(codSoli);
	}
}
