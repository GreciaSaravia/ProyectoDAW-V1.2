package net.compensacion.fabrica;

import net.compensacion.dao.MySqlExpAreaDAO;
import net.compensacion.dao.MySqlAreaDAO;
import net.compensacion.dao.MySqlCargoDAO;
import net.compensacion.dao.MySqlDepartamentoDAO;
import net.compensacion.dao.MySqlDistritoDAO;
import net.compensacion.dao.MySqlInfEscCboDAO;
import net.compensacion.dao.MySqlInfoDispoPresuDAO;
import net.compensacion.dao.MySqlInfoEscalafonarioDAO;
import net.compensacion.dao.MySqlInfoLegalDAO;
import net.compensacion.dao.MySqlInfoTecnicoLegalDAO;
import net.compensacion.dao.MySqlPerfilDAO;
import net.compensacion.dao.MySqlProvinciaDAO;
import net.compensacion.dao.MySqlResolucionDAO;
import net.compensacion.dao.MySqlSesionDAO;
import net.compensacion.dao.MySqlSolicitanteDAO;
import net.compensacion.dao.MySqlSolicitudDAO;
import net.compensacion.dao.MySqlUsuarioDAO;
import net.compensacion.interfaces.ExpAreaDAO;
import net.compensacion.interfaces.AreaDAO;
import net.compensacion.interfaces.CargoDAO;
import net.compensacion.interfaces.DepartamentoDAO;
import net.compensacion.interfaces.DistritoDAO;
import net.compensacion.interfaces.InfEscCboDAO;
import net.compensacion.interfaces.InformDispoPresuDAO;
import net.compensacion.interfaces.InformEscalafonarioDAO;
import net.compensacion.interfaces.InformLegalDAO;
import net.compensacion.interfaces.InformTecnicoLegalDAO;
import net.compensacion.interfaces.PerfilDAO;
import net.compensacion.interfaces.ProvinciaDAO;
import net.compensacion.interfaces.ResolucionDAO;
import net.compensacion.interfaces.SesionDAO;
import net.compensacion.interfaces.SolicitanteDAO;
import net.compensacion.interfaces.SolicitudDAO;
import net.compensacion.interfaces.UsuarioDAO;

public class MySqlDAOFactory extends DAOFactory {

	@Override
	public SolicitanteDAO getSolicitanteDAO() {
		// TODO Auto-generated method stub
		return new MySqlSolicitanteDAO();
	}

	@Override
	public SolicitudDAO getSolicitudDAO() {
		// TODO Auto-generated method stub
		return new MySqlSolicitudDAO();
	}

	@Override
	public InformEscalafonarioDAO getInformEscalafonarioDAO() {
		// TODO Auto-generated method stub
		return new MySqlInfoEscalafonarioDAO();
	}

	@Override
	public InfEscCboDAO getInfEscCboDAO() {
		// TODO Auto-generated method stub
		return new MySqlInfEscCboDAO();
	}
	@Override
	public SesionDAO getSesionDAO() {
		// TODO Auto-generated method stub
		return new MySqlSesionDAO();
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		// TODO Auto-generated method stub
		return new MySqlUsuarioDAO();
	}

	@Override
	public DepartamentoDAO getDepartamentoDAO() {
		// TODO Auto-generated method stub
		return new MySqlDepartamentoDAO();
	}

	@Override
	public ProvinciaDAO getProvinciaDAO() {
		// TODO Auto-generated method stub
		return new MySqlProvinciaDAO();
	}

	@Override
	public DistritoDAO getDistritoDAO() {
		// TODO Auto-generated method stub
		return new MySqlDistritoDAO();
	}

	@Override
	public AreaDAO getAreaDAO() {
		// TODO Auto-generated method stub
		return new MySqlAreaDAO();
	}

	@Override
	public CargoDAO getCargoDao() {
		// TODO Auto-generated method stub
		return new MySqlCargoDAO();
	}

	@Override
	public PerfilDAO getPerfilDAO() {
		// TODO Auto-generated method stub
		return new MySqlPerfilDAO();
	}
	
	@Override
	public InformTecnicoLegalDAO getInformTecnicoLegalDAO() {
		// TODO Auto-generated method stub
		return new MySqlInfoTecnicoLegalDAO();
	}

	@Override
	public InformDispoPresuDAO getInformDispoPresuDAO() {
		// TODO Auto-generated method stub
		return new MySqlInfoDispoPresuDAO();
	}

	@Override
	public InformLegalDAO getInformLegalDAO() {
		// TODO Auto-generated method stub
		return new MySqlInfoLegalDAO();
	}

	@Override
	public ResolucionDAO getResolucionDAO() {
		// TODO Auto-generated method stub
		return new MySqlResolucionDAO();
	}

	@Override
	public ExpAreaDAO getExpAreaDAO() {
		// TODO Auto-generated method stub
		return new MySqlExpAreaDAO();
	}

}
