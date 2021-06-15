package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import net.compensacion.entidad.InformeLegal;
import net.compensacion.interfaces.InformLegalDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlInfoLegalDAO implements InformLegalDAO {

	@Override
	public int saveIL(InformeLegal infLegal) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="insert into TBInformeLegal values(?,?,?,?,?,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1,infLegal.getCodigo());
			pstm.setString(2, infLegal.getFecha());
			pstm.setString(3, infLegal.getHora());
			pstm.setString(4,infLegal.getAntecedente());
			pstm.setString(5,infLegal.getPedido());
			pstm.setString(6,infLegal.getBaseLegal());
			pstm.setString(7,infLegal.getExposicion());
			pstm.setString(8,infLegal.getConclusiones());
			pstm.setString(9,infLegal.getSugerencias());
			pstm.setString(10,infLegal.getId_usuario());
			pstm.setInt(11,infLegal.getCodExp());
	
			salida=pstm.executeUpdate();
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int updateIL(InformeLegal infLegal) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="update TBInformeLegal set fecha_inf_leg=?,hora_inf_leg=?,ant_inf_leg=?,ped_inf_leg=?,bl_itl=?, "+
					"e_ah_itl=?,con_itl=?,su_itl=?,id_usuario=?,cod_exp=? where cod_inf_leg=?";
			pstm=cn.prepareStatement(sql);
			
			pstm.setString(1, infLegal.getFecha());
			pstm.setString(2, infLegal.getHora());
			pstm.setString(3,infLegal.getAntecedente());
			pstm.setString(4,infLegal.getPedido());
			pstm.setString(5,infLegal.getBaseLegal());
			pstm.setString(6,infLegal.getExposicion());
			pstm.setString(7,infLegal.getConclusiones());
			pstm.setString(8,infLegal.getSugerencias());
			pstm.setString(9,infLegal.getId_usuario());
			pstm.setInt(10,infLegal.getCodExp());
			pstm.setInt(11,infLegal.getCodigo());
			salida=pstm.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int deleteIL(int codigo, int codExp) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null,pstm1=null;
		try {
			cn=MySqlBDConexion.getConexion();
			cn.setAutoCommit(false);
			String sql="delete from TBInformeLegal where cod_exp=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codExp);
			salida=pstm.executeUpdate();
			//elimnar documentos posteriores
			String sql1="delete from TBResolucion where cod_exp=?";
			pstm1=cn.prepareStatement(sql1);
			pstm1.setInt(1, codExp);
			salida+=pstm1.executeUpdate();
			//
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
				salida=-1;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			try {
				if(pstm1!=null) pstm1.close();
				if(pstm!=null)pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return salida;
	}

	@Override
	public int findCod() {
		int codigo=0;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select max(cod_inf_leg)from TBInformeLegal";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			if(rs.next()){
				codigo=rs.getInt(1)+1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return codigo;
	}

	@Override
	public List<InformeLegal> listCompleIL() {
		List<InformeLegal> lista=new ArrayList<InformeLegal>();
		InformeLegal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql=" select i.cod_exp, s.cod_sol, s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,s.dir_sol, i.cod_inf_leg, " + 
					"	 DATE_FORMAT(i.fecha_inf_leg, '%d/%m/%Y'),i.hora_inf_leg,i.id_usuario from TBSolicitante s " + 
					"				join TBSolicitud so on s.cod_sol=so.cod_sol join TBExpediente e on so.cod_solic=e.cod_exp join " + 
					"		TBInformeLegal  i on e.cod_exp=i.cod_exp ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				bean=new InformeLegal();
				bean.setCodExp(rs.getInt(1));
				bean.setCodigoSoli(rs.getInt(2));
				bean.setNombre(rs.getString(3));
				bean.setApePaterno(rs.getString(4));
				bean.setApeMaterno(rs.getString(5));
				bean.setDni(rs.getString(6));
				bean.setDireccion(rs.getString(7));
				
				bean.setCodigo(rs.getInt(8));
				bean.setFecha(rs.getString(9));
				bean.setHora(rs.getString(10));
				bean.setId_usuario(rs.getString(11));
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public List<InformeLegal> listCompleILxcod(String cod) {
		List<InformeLegal> lista=new ArrayList<InformeLegal>();
		InformeLegal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select i.cod_exp, s.cod_sol, s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,s.dir_sol,s.foto_usuario, " + 
					"  DATE_FORMAT(i.fecha_inf_leg, '%d/%m/%Y'),i.hora_inf_leg,i.ant_inf_leg,i.ped_inf_leg,i.bl_itl, " + 
					" i.e_ah_itl,i.con_itl,i.su_itl,i.id_usuario, concat( u.nom_usuario,' ' , u.apePat_usuario,' ' , u.apeMat_usuario) " + 
					" from TBSolicitante s join TBSolicitud so on s.cod_sol=so.cod_sol join TBExpediente ex on so.cod_solic=ex.cod_exp join  " + 
					" TBInformeLegal  i on ex.cod_exp=i.cod_exp  join TBusuario u on i.id_usuario=u.id_usuario where i.cod_inf_leg=? ";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,cod);
			rs=pstm.executeQuery();
			while(rs.next()){
				bean=new InformeLegal();
				bean.setCodExp(rs.getInt(1));
				bean.setCodigoSoli(rs.getInt(2));
				bean.setNombre(rs.getString(3));
				bean.setApePaterno(rs.getString(4));
				bean.setApeMaterno(rs.getString(5));
				bean.setDni(rs.getString(6));
				bean.setDireccion(rs.getString(7));
				byte [] imagen=rs.getBytes(8);
				String fo=Base64.getEncoder().encodeToString(imagen);
				bean.setFoto(fo);
				
				bean.setFecha(rs.getString(9));
				bean.setHora(rs.getString(10));
				bean.setAntecedente(rs.getString(11));
				bean.setPedido(rs.getString(12));
				bean.setBaseLegal(rs.getString(13));
				bean.setExposicion(rs.getString(14));
				bean.setConclusiones(rs.getString(15));
				bean.setSugerencias(rs.getString(16));
				bean.setId_usuario(rs.getString(17));
				bean.setNom_usuario(rs.getString(18));
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

	@Override
	public List<InformeLegal> listSoliSinIL() {
		List<InformeLegal> lista=new ArrayList<InformeLegal>();
		InformeLegal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			cn=MySqlBDConexion.getConexion();
			String sql=" select s.cod_sol, s.nom_sol, s.apePat_Sol, s.apeMat_sol, s.dni_sol, s.dir_sol, s.foto_usuario,so.cod_solic " + 
					" from tbSolicitante s  inner join tbSolicitud so on  s.cod_sol=so.cod_sol " + 
					" where so.cod_solic in (select cod_exp from TBInformeDispoPresup) and so.cod_solic not in (select cod_exp from TBInformeLegal)";
				
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				bean=new InformeLegal();
				bean.setCodigoSoli(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setApePaterno(rs.getString(3));
				bean.setApeMaterno(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setDireccion(rs.getString(6));	
				byte [] imagen=rs.getBytes(7);
				String fo=Base64.getEncoder().encodeToString(imagen);
				bean.setFoto(fo);
				bean.setCodExp(rs.getInt(8));
				lista.add(bean);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null)pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		
		return lista;
	}

	@Override
	public List<InformeLegal> listILReporte() {
		List<InformeLegal> lista=new ArrayList<InformeLegal>();
		InformeLegal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select i.cod_inf_leg, s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,DATE_FORMAT(i.fecha_inf_leg, '%d/%m/%Y'),DATE_FORMAT(i.hora_inf_leg,'%T') from TBSolicitante s "+
					"join TBSolicitud so on s.cod_sol=so.cod_sol join TBExpediente e on so.cod_solic=e.cod_exp join "+
					"TBInformeLegal i on e.cod_exp=i.cod_exp ";
					
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				bean=new InformeLegal();
				bean.setCodigo(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setApePaterno(rs.getString(3));
				bean.setApeMaterno(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setFecha(rs.getString(6));
				bean.setHora(rs.getString(7));
				
				lista.add(bean);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null) rs.close();
				if(pstm!=null) pstm.close();
				if(cn!=null) cn.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
		return lista;
	}

}
