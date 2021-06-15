package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.compensacion.entidad.Distrito;
import net.compensacion.interfaces.DistritoDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlDistritoDAO implements DistritoDAO {

	@Override
	public List<Distrito> listAll(int codProvincia) {
		List<Distrito> lista=new ArrayList<Distrito>();
		Distrito bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select * from tbdistrito where cod_prov=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codProvincia);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Distrito();
				
				bean.setCodDistrito(rs.getInt(1));
				bean.setNomDistrito(rs.getString(2));
				bean.setCodProvincia(rs.getInt(3));
				
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
