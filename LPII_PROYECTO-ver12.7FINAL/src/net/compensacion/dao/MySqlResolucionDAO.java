package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import net.compensacion.entidad.Resolucion;
import net.compensacion.interfaces.ResolucionDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlResolucionDAO implements ResolucionDAO {

	@Override
	public int saveRes(Resolucion res) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="insert into TBResolucion values(?,?,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, res.getCodigo());
			pstm.setString(2, res.getFecha());
			pstm.setString(3, res.getHora());
			pstm.setString(4, res.getIntroduccion());
			pstm.setString(5, res.getConsideraciones());
			pstm.setString(6,res.getResolucion());
			pstm.setString(7,res.getId_usuario());
			pstm.setInt(8,res.getCodExp());
	
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
	public int updateRes(Resolucion res) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="update TBResolucion set fecha_reso=?,hora_reso=?,int_reso=?,con_reso=?,reso=?, "+
					"id_usuario=?,cod_exp=? where cod_reso=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, res.getFecha());
			pstm.setString(2, res.getHora());
			pstm.setString(3, res.getIntroduccion());
			pstm.setString(4, res.getConsideraciones());
			pstm.setString(5,res.getResolucion());
			pstm.setString(6,res.getId_usuario());
			pstm.setInt(7,res.getCodExp());
			pstm.setInt(8,res.getCodigo());
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
	public int deleteRes(int codigo, int codExp) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="delete from TBResolucion where cod_reso=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
			salida=pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
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
			String sql="select max(cod_reso)from TBResolucion";
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
	public List<Resolucion> listCompleRes() {
		List<Resolucion> lista=new ArrayList<Resolucion>();
		Resolucion bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql=" select r.cod_exp, s.cod_sol, s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,s.dir_sol, r.cod_reso, " + 
					"	 DATE_FORMAT(r.fecha_reso, '%d/%m/%Y'),r.hora_reso,r.id_usuario from TBSolicitante s " + 
					"	join TBSolicitud so on s.cod_sol=so.cod_sol join TBExpediente e on so.cod_solic=e.cod_exp join " + 
					"		TBResolucion  r on e.cod_exp=r.cod_exp";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				bean=new Resolucion();
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
	public List<Resolucion> listCompleResxcod(String cod) {
		List<Resolucion> lista=new ArrayList<Resolucion>();
		Resolucion bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select r.cod_exp, s.cod_sol, s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,s.dir_sol,s.foto_usuario, " + 
					"	 DATE_FORMAT(r.fecha_reso, '%d/%m/%Y'),r.hora_reso,r.int_reso,r.con_reso,r.reso, " + 
					"     r.id_usuario, concat( u.nom_usuario,' ' , u.apePat_usuario,' ' , u.apeMat_usuario) " + 
					" from TBSolicitante s join TBSolicitud so on s.cod_sol=so.cod_sol join TBExpediente ex on so.cod_solic=ex.cod_exp join " + 
					"TBResolucion  r on ex.cod_exp=r.cod_exp  join TBusuario u on r.id_usuario=u.id_usuario where r.cod_reso=? ";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,cod);
			rs=pstm.executeQuery();
			while(rs.next()){
				bean=new Resolucion();
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
				bean.setIntroduccion(rs.getString(11));
				bean.setConsideraciones(rs.getString(12));
				bean.setResolucion(rs.getString(13));
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
	public List<Resolucion> listSoliSinRes() {
		List<Resolucion> lista=new ArrayList<Resolucion>();
		Resolucion bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select s.cod_sol, s.nom_sol, s.apePat_Sol, s.apeMat_sol, s.dni_sol, s.dir_sol, s.foto_usuario,so.cod_solic " + 
					"from tbSolicitante s  inner join tbSolicitud so on  s.cod_sol=so.cod_sol  " + 
					" where so.cod_solic in (select cod_exp from TBInformeLegal) and so.cod_solic not in (select cod_exp from TBResolucion) ";
				
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				bean=new Resolucion();
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
	public List<Resolucion> listaResReporte() {
		List<Resolucion> lista=new ArrayList<Resolucion>();
		Resolucion bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select i.cod_reso,s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,DATE_FORMAT(i.fecha_reso, '%d/%m/%Y'),DATE_FORMAT(i.hora_reso,'%T') "
					+ "from TBSolicitante s join TBSolicitud so on s.cod_sol=so.cod_sol join "
					+ " TBExpediente e on so.cod_solic=e.cod_exp join "+
					"TBResolucion i on e.cod_exp=i.cod_exp ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				bean=new Resolucion();
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
