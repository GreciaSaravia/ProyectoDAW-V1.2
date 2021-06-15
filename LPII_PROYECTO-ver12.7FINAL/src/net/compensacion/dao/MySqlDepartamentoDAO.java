package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.compensacion.entidad.Departamento;
import net.compensacion.interfaces.DepartamentoDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlDepartamentoDAO implements DepartamentoDAO {

	@Override
	public List<Departamento> listAll() {
		List<Departamento> lista=new ArrayList<Departamento>();
		Departamento bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			
			cn=MySqlBDConexion.getConexion();
			String sql="SELECT * FROM tbdepartamento";
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new Departamento();
				
				bean.setCodigo(rs.getInt(1));
				bean.setNomDepartamento(rs.getString(2));
				
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
