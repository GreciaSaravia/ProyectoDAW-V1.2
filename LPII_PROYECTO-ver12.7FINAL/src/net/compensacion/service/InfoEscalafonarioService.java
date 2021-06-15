package net.compensacion.service;

import java.util.List;

import net.compensacion.entidad.EscalaMagis;
import net.compensacion.entidad.GrupoOcupa;
import net.compensacion.entidad.InformeEscalafonario;
import net.compensacion.entidad.JornadaLaboral;
import net.compensacion.fabrica.DAOFactory;
import net.compensacion.interfaces.InfEscCboDAO;
import net.compensacion.interfaces.InformEscalafonarioDAO;

public class InfoEscalafonarioService {
	DAOFactory fabrica=DAOFactory.getDAOFactory(1);
	InformEscalafonarioDAO daoIE=fabrica.getInformEscalafonarioDAO();
	InfEscCboDAO daoIEcbo=fabrica.getInfEscCboDAO();
	
	public int cargarCodigo() {
		return daoIE.findCod();
	};
	public int registrarIE(InformeEscalafonario infEsc) {
		return daoIE.saveIE (infEsc);
	};
	public List<InformeEscalafonario> ListarTodoIE(){
		return daoIE.listCompleIE ();
	};
	public int actualizarIE(InformeEscalafonario infEsc) {
		return daoIE.updateIE (infEsc);
	};
	public int eliminarIE(int codigo,int  codExp) {
		return daoIE.deleteIE(codigo,codExp);
	};
	
	public List<InformeEscalafonario> listarIExcod (String cod){
		return daoIE.listCompleIExcod(cod);
	};
	public List<InformeEscalafonario> listarIESinIE (){
		return daoIE.listIESinIE();
	};
	public List<EscalaMagis> listarEscalaMagis(){
		return daoIEcbo.listAllEscalaMagis();
	};
	public List<GrupoOcupa> listarGrupoOcupa(){
		return daoIEcbo.listAllGrupoOcupa();
	};
	public List<JornadaLaboral> listarJornadaLaboral(){
		return daoIEcbo.listAllJornadaLaboral();
	};
	
	public List<InformeEscalafonario> listIEReporte(){
		return daoIE.listIEReporte();
	};
}
