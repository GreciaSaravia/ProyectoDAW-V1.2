package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.compensacion.entidad.Area;
import net.compensacion.interfaces.AreaDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlAreaDAO implements AreaDAO {

	@Override
	public List<Area> listAll() {
		List<Area> lista=new ArrayList<Area>();
		Area bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select * from tbarea";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Area();
				
				bean.setCodArea(rs.getInt(1));
				bean.setNomArea(rs.getString(2));
				
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
