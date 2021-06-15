package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import net.compensacion.entidad.InformeDisponibilidadPresupuestal;
import net.compensacion.interfaces.InformDispoPresuDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlInfoDispoPresuDAO implements InformDispoPresuDAO {

	@Override
	public int saveIDP(InformeDisponibilidadPresupuestal infDP) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="insert into TBInformeDispoPresup values(?,?,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, infDP.getCodigo());
			pstm.setString(2, infDP.getFecha());
			pstm.setString(3, infDP.getHora());
			pstm.setDouble(4, infDP.getMonto());
			pstm.setString(5, infDP.getObjetivo());
			pstm.setString(6,infDP.getAlcance());
			pstm.setString(7,infDP.getId_usuario());
			pstm.setInt(8,infDP.getCodExp());
	
			salida=pstm.executeUpdate();
		
			
		} catch (SQLException  e) {
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
	public int updateIDP(InformeDisponibilidadPresupuestal infDP) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="update TBInformeDispoPresup set fecha_idp=?,hora_idp=?,monto=?,obj_idp=?,al_idp=?, "+
					"id_usuario=? where cod_idp=?";
			pstm=cn.prepareStatement(sql);
			
			pstm.setString(1, infDP.getFecha());
			pstm.setString(2, infDP.getHora());
			pstm.setDouble(3, infDP.getMonto());
			pstm.setString(4, infDP.getObjetivo());
			pstm.setString(5,infDP.getAlcance());
			pstm.setString(6,infDP.getId_usuario());
			pstm.setInt(7,infDP.getCodigo());
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
	public int deleteIDP(int codigo, int codExp) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null,pstm1=null,pstm2=null;
		try {
			cn=MySqlBDConexion.getConexion();
			cn.setAutoCommit(false);
			String sql="delete from TBInformeDispoPresup where cod_idp=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
			salida=pstm.executeUpdate();
			String sql1="delete from TBInformeLegal where cod_exp=?";
			pstm1=cn.prepareStatement(sql1);
			pstm1.setInt(1, codExp);
			salida+=pstm1.executeUpdate();
			String sql2="delete from TBResolucion where cod_exp=?";
			pstm2=cn.prepareStatement(sql2);
			pstm2.setInt(1, codExp);
			salida+=pstm2.executeUpdate();
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
			String sql="select max(cod_idp) from TBInformeDispoPresup";
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
	public List<InformeDisponibilidadPresupuestal> listCompleIDP() {
		List<InformeDisponibilidadPresupuestal> lista=new ArrayList<InformeDisponibilidadPresupuestal>();
		InformeDisponibilidadPresupuestal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql=" select i.cod_exp, s.cod_sol, s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,s.dir_sol, i.cod_idp," + 
					"			 DATE_FORMAT(i.fecha_idp, '%d/%m/%Y'),i.hora_idp,i.id_usuario from TBSolicitante s " + 
					"			join TBSolicitud so on s.cod_sol=so.cod_sol join TBExpediente e on so.cod_solic=e.cod_exp join " + 
					"					TBInformeDispoPresup  i on e.cod_exp=i.cod_exp ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				bean=new InformeDisponibilidadPresupuestal();
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
	public List<InformeDisponibilidadPresupuestal> listCompleIDPxcod(String cod) {
		List<InformeDisponibilidadPresupuestal> lista=new ArrayList<InformeDisponibilidadPresupuestal>();
		InformeDisponibilidadPresupuestal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql=" select i.cod_exp, s.cod_sol, s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,s.dir_sol,s.foto_usuario, " + 
					" DATE_FORMAT(i.fecha_idp, '%d/%m/%Y'),i.hora_idp,i.monto,i.obj_idp,i.al_idp,i.id_usuario, " + 
					" concat( u.nom_usuario,' ' , u.apePat_usuario,' ' , u.apeMat_usuario)  from TBSolicitante s " + 
					" join TBSolicitud so on s.cod_sol=so.cod_sol join TBExpediente ex on so.cod_solic=ex.cod_exp join " + 
					" TBInformeDispoPresup  i on ex.cod_exp=i.cod_exp " + 
					"	join TBusuario u on i.id_usuario=u.id_usuario " + 
					"	where i.cod_idp=? ";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,cod);
			rs=pstm.executeQuery();
			while(rs.next()){
				bean=new InformeDisponibilidadPresupuestal();
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
				bean.setMonto(rs.getDouble(11));
				bean.setObjetivo(rs.getString(12));
				bean.setAlcance(rs.getString(13));
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
	public List<InformeDisponibilidadPresupuestal> listSolisinIDP() {
		List<InformeDisponibilidadPresupuestal> lista=new ArrayList<InformeDisponibilidadPresupuestal>();
		InformeDisponibilidadPresupuestal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="  select s.cod_sol, s.nom_sol, s.apePat_Sol, s.apeMat_sol, s.dni_sol, s.dir_sol, s.foto_usuario,so.cod_solic  " + 
					"from tbSolicitante s  inner join tbSolicitud so on  s.cod_sol=so.cod_sol  " + 
					" where so.cod_solic in (select cod_exp from tbinformetecnicolegal) and so.cod_solic not "+ 
					"in (select cod_exp from TBInformeDispoPresup) " ;
				
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new InformeDisponibilidadPresupuestal();
	
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
	public List<InformeDisponibilidadPresupuestal> listMontoCompen(String cod) {
		List<InformeDisponibilidadPresupuestal> lista=new ArrayList<InformeDisponibilidadPresupuestal>();
		InformeDisponibilidadPresupuestal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			cn=MySqlBDConexion.getConexion();
			String sql=" select i.cod_es_mag, e.escala, i.cod_jor_labo,j.jornada,i.tst from tbInformeEscalafonario i " + 
					" join tbescalamagisterial e on i.cod_es_mag=e.cod_es_mag  join tbgrupoocupacional g " + 
					" on i.cod_grup_ocp=g.cod_grup_ocp join tbjornadalaboral j on i.cod_jor_labo=j.cod_jor_labo " + 
					" join TBExpediente ex on i.cod_exp=ex.cod_exp " + 
					"	where i.cod_exp=?" ;
				
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,cod);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new InformeDisponibilidadPresupuestal();
				bean.setCod_es_mag(rs.getInt(1));
				bean.setNom_es_mag(rs.getString(2));
				bean.setCod_jor_labo(rs.getInt(3));
				bean.setNom_jor_labo(rs.getString(4));
				bean.setTst(rs.getInt(5));
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
	public List<InformeDisponibilidadPresupuestal> listaIDPReporte() {
		List<InformeDisponibilidadPresupuestal> lista=new ArrayList<InformeDisponibilidadPresupuestal>();
		InformeDisponibilidadPresupuestal bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select i.cod_idp,s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,i.monto,DATE_FORMAT(i.fecha_idp, '%d/%m/%Y'),DATE_FORMAT(i.hora_idp ,'%T') "
					+ "from TBSolicitante s join TBSolicitud so on s.cod_sol=so.cod_sol join "
					+ " TBExpediente e on so.cod_solic=e.cod_exp join "+
					"TBInformeDispoPresup i on e.cod_exp=i.cod_exp ";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				bean=new InformeDisponibilidadPresupuestal();
				bean.setCodigo(rs.getInt(1));
				bean.setNombre(rs.getString(2));
				bean.setApePaterno(rs.getString(3));
				bean.setApeMaterno(rs.getString(4));
				bean.setDni(rs.getString(5));
				bean.setMonto(rs.getDouble(6));
				bean.setFecha(rs.getString(7));
				bean.setHora(rs.getString(8));
				
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
