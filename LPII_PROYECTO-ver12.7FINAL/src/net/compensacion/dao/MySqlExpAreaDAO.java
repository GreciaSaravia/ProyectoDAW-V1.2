package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.compensacion.entidad.Expediente;
import net.compensacion.interfaces.ExpAreaDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlExpAreaDAO implements ExpAreaDAO {

	@Override
	public String idenArea(String codExp) {
		String codigo="";
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="  select " + 
					" ( " + 
					"   (select count(*) from tbsolicitud where cod_solic=?) " + 
					" + (select count(*) from tbinformeescalafonario where cod_exp = ?) " + 
					" + (select count(*) from tbinformetecnicolegal where cod_exp = ?) " + 
					" + (select count(*) from tbinformedispopresup where cod_exp = ?) " + 
					" + (select count(*) from tbinformelegal where cod_exp = ?) " + 
					" + (select count(*) from tbresolucion where cod_exp = ?) " + 
					" ) contador from tbsolicitud so where cod_solic=? ";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,codExp);
			pstm.setString(2,codExp);
			pstm.setString(3,codExp);
			pstm.setString(4,codExp);
			pstm.setString(5,codExp);
			pstm.setString(6,codExp);
			pstm.setString(7,codExp);
			rs=pstm.executeQuery();
			if(rs.next()){
				codigo=rs.getString(1);
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
	public List<Expediente> listCompleExpXCod(String cod) {
		List<Expediente> lista=new ArrayList<Expediente>();
		Expediente bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select * from tbDocEnArea where cod_doc_area=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,cod);
			rs=pstm.executeQuery();
			while(rs.next()){
				bean=new Expediente();
				bean.setCodigoArea(rs.getInt(1));
				bean.setAreaExp(rs.getString(2));
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
	public List<Expediente> listExpTerminadosReporte() {
		List<Expediente> lista=new ArrayList<Expediente>();
		Expediente bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select e.cod_exp, sl.nom_sol, sl.apePat_sol, sl.apeMat_sol, sl.dni_sol,DATE_FORMAT(r.fecha_reso, '%d/%m/%Y') ,DATE_FORMAT(r.hora_reso,'%T') from tbexpediente e inner join tbinformeescalafonario i "+
					"on e.cod_exp=i.cod_exp inner join tbinformetecnicolegal tl "+
					"on e.cod_exp=tl.cod_exp inner join tbinformedispopresup dp "+
					"on e.cod_exp=dp.cod_exp inner join tbinformelegal il "+
					"on e.cod_exp=il.cod_exp inner join tbresolucion r "+
					"on e.cod_exp=r.cod_exp inner join tbsolicitud s "+
					"on e.cod_exp=s.cod_sol inner join tbsolicitante sl "+
					"on s.cod_sol=sl.cod_sol "+
					"where (i.cod_exp is not null) and (tl.cod_exp is not null) and (dp.cod_exp is not null) and (il.cod_exp is not null) and (r.cod_exp is not null) "; 
					
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				
				bean=new Expediente();
				
				bean.setCodigoExp(rs.getInt(1));
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
