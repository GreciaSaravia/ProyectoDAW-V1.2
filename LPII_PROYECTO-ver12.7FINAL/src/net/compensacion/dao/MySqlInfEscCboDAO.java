package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.compensacion.entidad.EscalaMagis;
import net.compensacion.entidad.GrupoOcupa;
import net.compensacion.entidad.JornadaLaboral;
import net.compensacion.interfaces.InfEscCboDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlInfEscCboDAO implements InfEscCboDAO {

	@Override
	public List<EscalaMagis> listAllEscalaMagis() {
		List<EscalaMagis> lista=new ArrayList<EscalaMagis>();
		EscalaMagis bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select * from tbescalamagisterial";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()) {
				bean=new EscalaMagis();
				bean.setCodigo(rs.getInt(1));
				bean.setNombre(rs.getString(2));
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
	public List<GrupoOcupa> listAllGrupoOcupa() {
		List<GrupoOcupa> lista=new ArrayList<GrupoOcupa>();
		GrupoOcupa bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select * from tbgrupoocupacional";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()) {
				bean=new GrupoOcupa();
				bean.setCodigo(rs.getInt(1));
				bean.setNombre(rs.getString(2));
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
	public List<JornadaLaboral> listAllJornadaLaboral() {
		List<JornadaLaboral> lista=new ArrayList<JornadaLaboral>();
		JornadaLaboral bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select * from tbjornadalaboral";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			while(rs.next()) {
				bean=new JornadaLaboral();
				bean.setCodigo(rs.getInt(1));
				bean.setNombre(rs.getString(2));
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
