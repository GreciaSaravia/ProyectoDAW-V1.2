package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import net.compensacion.entidad.Provincia;
import net.compensacion.interfaces.ProvinciaDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlProvinciaDAO implements ProvinciaDAO {

	@Override
	public List<Provincia> listAll(int codDep) {
		List<Provincia> lista=new ArrayList<Provincia>();
		Provincia bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select * from tbprovincia where cod_dep=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codDep);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Provincia();
				
				bean.setCodProv(rs.getInt(1));
				bean.setNomProv(rs.getString(2));
				bean.setCodDep(rs.getInt(3));
				
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
