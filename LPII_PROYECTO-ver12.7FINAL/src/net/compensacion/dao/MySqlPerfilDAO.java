package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.compensacion.entidad.Perfil;
import net.compensacion.interfaces.PerfilDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlPerfilDAO implements PerfilDAO {

	@Override
	public List<Perfil> listAll() {
		List<Perfil> lista=new ArrayList<Perfil>();
		Perfil bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="select * from tbperfil";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Perfil();
				
				bean.setCodPerfil(rs.getInt(1));
				bean.setNomPerfil(rs.getString(2));
				
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
