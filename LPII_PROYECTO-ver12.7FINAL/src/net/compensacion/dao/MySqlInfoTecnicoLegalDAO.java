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

import net.compensacion.entidad.InformeEscalafonario;
import net.compensacion.entidad.InformeTecnicoLegal;
import net.compensacion.interfaces.InformTecnicoLegalDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlInfoTecnicoLegalDAO implements InformTecnicoLegalDAO {

	@Override
	public int saveITL(InformeTecnicoLegal infTL) {
		int salida=-1;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="insert into TBInformeTecnicoLegal values(?,?,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1,infTL.getCodigo());
			pstm.setString(2, infTL.getFecha());
			pstm.setString(3, infTL.getHora());
			pstm.setString(4, infTL.getObjetivo());
			pstm.setString(5,infTL.getAnalisis());
			pstm.setString(6,infTL.getLiquidacion());
			pstm.setString(7,infTL.getId_usuario());
			pstm.setInt(8,infTL.getCodExp());
	
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
	public int updateITL(InformeTecnicoLegal infTL) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="update TBInformeTecnicoLegal set fecha_itl=?,hora_itl=?,obj_itl=?,an_itl=?,liq_itl=?, "+
					"id_usuario=?,cod_exp=? where cod_itl=?";
			pstm=cn.prepareStatement(sql);
			
			pstm.setString(1, infTL.getFecha());
			pstm.setString(2, infTL.getHora());
			pstm.setString(3, infTL.getObjetivo());
			pstm.setString(4,infTL.getAnalisis());
			pstm.setString(5,infTL.getLiquidacion());
			pstm.setString(6,infTL.getId_usuario());
			pstm.setInt(7,infTL.getCodExp());
			pstm.setInt(8,infTL.getCodigo());
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
	public int deleteITL(int codigo, int codExp) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null,pstm1=null,pstm2=null,pstm3=null;
		try {
			cn=MySqlBDConexion.getConexion();
			cn.setAutoCommit(false);
			String sql="delete from TBInformeTecnicoLegal where cod_itl=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
			salida=pstm.executeUpdate();
			String sql1="delete from TBInformeDispoPresup where cod_exp=?";
			pstm1=cn.prepareStatement(sql1);
			pstm1.setInt(1, codExp);
			salida+=pstm1.executeUpdate();
			String sql2="delete from TBInformeLegal where cod_exp=?";
			pstm2=cn.prepareStatement(sql2);
			pstm2.setInt(1, codExp);
			salida+=pstm2.executeUpdate();
			String sql3="delete from TBResolucion where cod_exp=?";
			pstm3=cn.prepareStatement(sql3);
			pstm3.setInt(1, codExp);
			salida+=pstm3.executeUpdate();
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
				if(pstm3!=null) pstm3.close();
				if(pstm2!=null) pstm2.close();
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
			String sql="select max(cod_itl)from TBInformeTecnicoLegal";
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
	public List<InformeTecnicoLegal> listCompleTL() {
		List<InformeTecnicoLegal> lista=new ArrayList<InformeTecnicoLegal>();
		InformeTecnicoLegal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select i.cod_exp, s.cod_sol, s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,s.dir_sol, i.cod_itl, " + 
					"			 DATE_FORMAT(i.fecha_itl, '%d/%m/%Y'),i.hora_itl,i.id_usuario from TBSolicitante s " + 
					"			join TBSolicitud so on s.cod_sol=so.cod_sol join TBExpediente e on so.cod_solic=e.cod_exp join " + 
					"					TBInformeTecnicoLegal  i on e.cod_exp=i.cod_exp ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				bean=new InformeTecnicoLegal();
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
	public List<InformeTecnicoLegal> listCompleITLxcod(String cod) {
		List<InformeTecnicoLegal> lista=new ArrayList<InformeTecnicoLegal>();
		InformeTecnicoLegal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select i.cod_exp, s.cod_sol, s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,s.dir_sol,s.foto_usuario, " + 
					" DATE_FORMAT(i.fecha_itl, '%d/%m/%Y'),i.hora_itl,i.obj_itl,i.an_itl,i.liq_itl,i.id_usuario, " + 
					"concat( u.nom_usuario,' ' , u.apePat_usuario,' ' , u.apeMat_usuario)  from TBSolicitante s " + 
					"join TBSolicitud so on s.cod_sol=so.cod_sol join TBExpediente ex on so.cod_solic=ex.cod_exp join   " + 
					"	TBinformeTecnicoLegal i on  ex.cod_exp= i.cod_exp  " + 
					"	join TBusuario u on i.id_usuario=u.id_usuario  " + 
					"	where i.cod_itl=? ";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,cod);
			rs=pstm.executeQuery();
			while(rs.next()){
				bean=new InformeTecnicoLegal();
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
				bean.setObjetivo(rs.getString(11));
				bean.setAnalisis(rs.getString(12));
				bean.setLiquidacion(rs.getString(13));
				bean.setId_usuario(rs.getString(14));
				bean.setNom_usuario(rs.getString(15));
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
	public List<InformeTecnicoLegal> listSoliSinITL() {
		List<InformeTecnicoLegal> lista = new ArrayList<InformeTecnicoLegal>();
		InformeTecnicoLegal bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			cn=MySqlBDConexion.getConexion();
			String sql=" select s.cod_sol, s.nom_sol, s.apePat_Sol, s.apeMat_sol, s.dni_sol, s.dir_sol, s.foto_usuario,so.cod_solic  " + 
					"from tbSolicitante s  inner join tbSolicitud so on  s.cod_sol=so.cod_sol " + 
					" where so.cod_solic in (select cod_exp from tbInformeEscalafonario) and so.cod_solic not in (select cod_exp from tbinformetecnicolegal) ";
				
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new InformeTecnicoLegal();
	
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
	public List<InformeTecnicoLegal> listaItlReporte() {
		List<InformeTecnicoLegal> lista=new ArrayList<InformeTecnicoLegal>();
		InformeTecnicoLegal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select i.cod_itl,s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol, DATE_FORMAT(i.fecha_itl, '%d/%m/%Y'), "
					+ "DATE_FORMAT(i.hora_itl ,'%T') from TBSolicitante s join TBSolicitud so on s.cod_sol=so.cod_sol join "
					+ " TBExpediente e on so.cod_solic=e.cod_exp join "+
					"TBInformeTecnicoLegal i on e.cod_exp=i.cod_exp ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				bean=new InformeTecnicoLegal();
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
