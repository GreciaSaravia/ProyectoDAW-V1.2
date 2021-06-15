package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.compensacion.entidad.Cargo;
import net.compensacion.interfaces.CargoDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlCargoDAO implements CargoDAO {

	@Override
	public Cargo returnCargo(int codArea) {
		
		Cargo bean = null;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select * from tbcargo where cod_area=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codArea);
			rs=pstm.executeQuery();
			
			if(rs.next()) {
				
				bean=new Cargo();
				
				bean.setCodCargo(rs.getInt(1));
				bean.setNomCargo(rs.getString(2));
				bean.setCodarea(rs.getInt(3));
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
		
		
		return bean;
	}

}
