package net.compensacion.fabrica;

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

public abstract class DAOFactory {
	// los posibles orígenes de datos
    public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int DB2 = 3;
    public static final int SQLSERVER = 4;
    public static final int XML = 5;
 

    public abstract SesionDAO getSesionDAO();
    public abstract SolicitanteDAO getSolicitanteDAO();
    public abstract SolicitudDAO getSolicitudDAO();
    public abstract InformEscalafonarioDAO getInformEscalafonarioDAO();
    public abstract InfEscCboDAO getInfEscCboDAO();
    public abstract InformTecnicoLegalDAO getInformTecnicoLegalDAO();
    public abstract InformDispoPresuDAO getInformDispoPresuDAO();
    public abstract InformLegalDAO getInformLegalDAO();
    public abstract ResolucionDAO getResolucionDAO();
    public abstract ExpAreaDAO getExpAreaDAO();

    public abstract UsuarioDAO getUsuarioDAO();
    public abstract DepartamentoDAO getDepartamentoDAO();
    public abstract ProvinciaDAO getProvinciaDAO();
    public abstract DistritoDAO getDistritoDAO();
    public abstract AreaDAO getAreaDAO();
    public abstract CargoDAO getCargoDao();
    public abstract PerfilDAO getPerfilDAO();
   
    public static DAOFactory getDAOFactory(int whichFactory){
        switch(whichFactory){
       	case MYSQL:
        	   return new MySqlDAOFactory();
        	case XML:
        	    //return new XmlDAOFactory();
        	case ORACLE:
        	    return null;
        	/*case DB2:
        	    return new Db2DAOFactory();*/
        	case SQLSERVER:
        	    return null;
        	/*case XML:
        	    return new XmlDAOFactory();*/
        	default:
        	    return null;
        }
     }
}
