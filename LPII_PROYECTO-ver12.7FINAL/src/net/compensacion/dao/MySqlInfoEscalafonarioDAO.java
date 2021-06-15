package net.compensacion.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import net.compensacion.entidad.InformeEscalafonario;
import net.compensacion.entidad.Solicitante;
import net.compensacion.interfaces.InformEscalafonarioDAO;
import net.compensacion.utils.MySqlBDConexion;

public class MySqlInfoEscalafonarioDAO implements InformEscalafonarioDAO {

	@Override
	public int saveIE(InformeEscalafonario infEsc) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="insert into tbInformeEscalafonario values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1,infEsc.getCodigo());
			pstm.setString(2, infEsc.getFecha());
			pstm.setString(3, infEsc.getHora());
			pstm.setString(4,infEsc.getTp());
			pstm.setString(5,infEsc.getCa());
			pstm.setInt(6,infEsc.getCod_es_mag());
			pstm.setInt(7,infEsc.getCod_grup_ocp());
			pstm.setInt(8,infEsc.getCod_jor_labo());
			pstm.setInt(9,infEsc.getTs_uc());
			pstm.setInt(10,infEsc.getTst());
			pstm.setString(11,infEsc.getAn());
			pstm.setString(12,infEsc.getRlsgr());
			pstm.setString(13,infEsc.getRd());
			pstm.setString(14,infEsc.getId_usuario());
			pstm.setInt(15,infEsc.getCodExp());
	
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
	public int updateIE(InformeEscalafonario infEsc) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="update TBInformeEscalafonario set fecha_inf_esc=?,hora_inf_esc=?,tp=?,ca=?,cod_es_mag=?, "+
					"cod_grup_ocp=?,cod_jor_labo=?,ts_uc=?,tst=?,an=?,rlsgr=?,rd=?,id_usuario=?,cod_exp=? where cod_inf_esc=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1, infEsc.getFecha());
			pstm.setString(2, infEsc.getHora());
			pstm.setString(3,infEsc.getTp());
			pstm.setString(4,infEsc.getCa());
			pstm.setInt(5,infEsc.getCod_es_mag());
			pstm.setInt(6,infEsc.getCod_grup_ocp());
			pstm.setInt(7,infEsc.getCod_jor_labo());
			pstm.setInt(8,infEsc.getTs_uc());
			pstm.setInt(9,infEsc.getTst());
			pstm.setString(10,infEsc.getAn());
			pstm.setString(11,infEsc.getRlsgr());
			pstm.setString(12,infEsc.getRd());
			pstm.setString(13,infEsc.getId_usuario());
			pstm.setInt(14,infEsc.getCodExp());
			pstm.setInt(15,infEsc.getCodigo());
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
	public int deleteIE(int codigo, int codExp) {
		int salida=-1;
		Connection cn=null;
		PreparedStatement pstm=null,pstm1=null,pstm2=null,pstm3=null,pstm4=null;
		try {
			cn=MySqlBDConexion.getConexion();
			cn.setAutoCommit(false);
			String sql="delete from tbInformeEscalafonario where cod_inf_esc=?";
			pstm=cn.prepareStatement(sql);
			pstm.setInt(1, codigo);
			salida=pstm.executeUpdate();
			//elimnar documentos posteriores
			String sql1="delete from tbInformeTecnicoLegal where cod_exp=?";
			pstm1=cn.prepareStatement(sql1);
			pstm1.setInt(1, codExp);
			salida+=pstm1.executeUpdate();
			String sql2="delete from tbInformeDispoPresup where cod_exp=?";
			pstm2=cn.prepareStatement(sql2);
			pstm2.setInt(1, codExp);
			salida+=pstm2.executeUpdate();
			String sql3="delete from tbInformeLegal where cod_exp=?";
			pstm3=cn.prepareStatement(sql3);
			pstm3.setInt(1, codExp);
			salida+=pstm3.executeUpdate();
			String sql4="delete from tbResolucion where cod_exp=?";
			pstm4=cn.prepareStatement(sql4);
			pstm4.setInt(1, codExp);
			salida+=pstm4.executeUpdate();
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
				if(pstm4!=null) pstm4.close();
				if(pstm3!=null) pstm3.close();
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
			String sql="select max(cod_inf_esc) from TBInformeEscalafonario";
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
	public List<InformeEscalafonario> listCompleIE() {
		List<InformeEscalafonario> lista=new ArrayList<InformeEscalafonario>();
		InformeEscalafonario bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select i.cod_exp, s.cod_sol, s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,s.dir_sol, i.cod_inf_esc, "
					+ "DATE_FORMAT(i.fecha_inf_esc, '%d/%m/%Y'),i.hora_inf_esc,i.id_usuario from TBSolicitante s "+
					"join TBSolicitud so on s.cod_sol=so.cod_sol join TBExpediente e on so.cod_solic=e.cod_exp join "+
					"TBInformeEscalafonario i on e.cod_exp=i.cod_exp  ";
			
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				bean=new InformeEscalafonario();
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
	public List<InformeEscalafonario> listCompleIExcod(String cod) {
		List<InformeEscalafonario> lista=new ArrayList<InformeEscalafonario>();
		InformeEscalafonario bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="select i.cod_exp, s.cod_sol, s.nom_sol,s.apePat_sol,s.apeMat_sol,s.dni_sol,s.dir_sol,s.foto_usuario," + 
					"DATE_FORMAT(i.fecha_inf_esc, '%d/%m/%Y'),i.hora_inf_esc,i.tp,i.ca,i.cod_es_mag,e.escala, i.cod_grup_ocp, g.grupo," + 
					"i.cod_jor_labo,j.jornada,i.ts_uc,i.tst,i.an,i.rlsgr,i.rd,i.id_usuario,"+
					 " concat( u.nom_usuario,' ' , u.apePat_usuario,' ' , u.apeMat_usuario)  from TBSolicitante s" + 
					"	join TBSolicitud so on s.cod_sol=so.cod_sol join TBExpediente ex on so.cod_solic=ex.cod_exp join  " + 
					"			tbInformeEscalafonario i on  ex.cod_exp= i.cod_exp join tbescalamagisterial e on i.cod_es_mag=e.cod_es_mag " + 
					"				 join tbgrupoocupacional g on i.cod_grup_ocp=g.cod_grup_ocp " + 
					"					join tbjornadalaboral j on i.cod_jor_labo=j.cod_jor_labo " + 
					   " join tbusuario u on i.id_usuario=u.id_usuario " +
					"					where i.cod_inf_esc=?";
			pstm=cn.prepareStatement(sql);
			pstm.setString(1,cod);
			rs=pstm.executeQuery();
			while(rs.next()){
				bean=new InformeEscalafonario();
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
				bean.setTp(rs.getString(11));
				bean.setCa(rs.getString(12));
				bean.setCod_es_mag(rs.getInt(13));
				bean.setNom_es_mag(rs.getString(14));
				bean.setCod_grup_ocp(rs.getInt(15));
				bean.setNom_grup_ocp(rs.getString(16));
				bean.setCod_jor_labo(rs.getInt(17));
				bean.setNom_jor_labo(rs.getString(18));
				bean.setTs_uc(rs.getInt(19));
				bean.setTst(rs.getInt(20));
				bean.setAn(rs.getString(21));
				bean.setRlsgr(rs.getString(22));
				bean.setRd(rs.getString(23));
				bean.setId_usuario(rs.getString(24));
				bean.setNom_usuario(rs.getString(25));
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
	public List<InformeEscalafonario> listIESinIE() {
		List<InformeEscalafonario> lista = new ArrayList<InformeEscalafonario>();
		InformeEscalafonario bean;
		
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="  select s.cod_sol, s.nom_sol, s.apePat_Sol, s.apeMat_sol, s.dni_sol, s.dir_sol, s.foto_usuario,so.cod_solic " + 
					"	from tbSolicitante s  inner join tbsolicitud so on  s.cod_sol=so.cod_sol " + 
					"	 where s.cod_sol in (select s.cod_sol from tbSolicitud ) and so.cod_solic not in (select cod_exp from tbInformeEscalafonario)";
				
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()) {
				
				bean=new InformeEscalafonario();
	
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
	public List<InformeEscalafonario> listIEReporte() {
		
		List<InformeEscalafonario> lista=new ArrayList<InformeEscalafonario>();
		InformeEscalafonario bean;
		Connection cn=null;
		PreparedStatement pstm=null;
		ResultSet rs=null;
		
		try {
			cn=MySqlBDConexion.getConexion();
			String sql="SELECT "+
				     "DATE_FORMAT(tbinformeescalafonario.`fecha_inf_esc`, '%d/%m/%Y') AS fecha, "+
				     "DATE_FORMAT(tbinformeescalafonario.`hora_inf_esc` ,'%T')  AS hora, "+
				     "tbinformeescalafonario.`cod_inf_esc` AS codigo, "+
				     "tbsolicitante.`nom_sol` AS nombre, "+
				     "tbsolicitante.`apePat_sol` AS apePaterno, "+
				     "tbsolicitante.`apeMat_sol` AS apeMaterno, "+
				     "tbsolicitante.`dni_sol` AS dni, " +
				     "tbinformeescalafonario.`tp` AS tp, "+
				     "tbinformeescalafonario.`ca` AS ca, "+
				     "tbinformeescalafonario.`tst` AS tst, "+
				     "tbescalamagisterial.`escala` AS escala, "+
				     "tbgrupoocupacional.`grupo` AS grupo, "+
				     "tbjornadalaboral.`jornada` AS jornada "+

				"FROM "+
				     "`tbescalamagisterial` tbescalamagisterial INNER JOIN `tbinformeescalafonario` tbinformeescalafonario ON tbescalamagisterial.`cod_es_mag` = tbinformeescalafonario.`cod_es_mag` "+
				     "INNER JOIN `tbgrupoocupacional` tbgrupoocupacional ON tbinformeescalafonario.`cod_grup_ocp` = tbgrupoocupacional.`cod_grup_ocp` "+
				     "INNER JOIN `tbjornadalaboral` tbjornadalaboral ON tbinformeescalafonario.`cod_jor_labo` = tbjornadalaboral.`cod_jor_labo` "+
				     "INNER JOIN `tbexpediente` tbexpediente ON tbinformeescalafonario.`cod_exp` = tbexpediente.`cod_exp` "+
				     "INNER JOIN `tbsolicitud` tbsolicitud ON tbexpediente.`cod_exp` = tbsolicitud.`cod_solic` "+
				     "INNER JOIN `tbsolicitante` tbsolicitante ON tbsolicitud.`cod_sol` = tbsolicitante.`cod_sol`  order by codigo asc";   
			pstm=cn.prepareStatement(sql);
			rs=pstm.executeQuery();
			
			while(rs.next()){
				
				bean=new InformeEscalafonario();
				
				bean.setFecha(rs.getString(1));
				bean.setHora(rs.getString(2));
				bean.setCodigo(rs.getInt(3));
				bean.setNombre(rs.getString(4));
				bean.setApePaterno(rs.getString(5));
				bean.setApeMaterno(rs.getString(6));
				bean.setDni(rs.getString(7));
				bean.setTp(rs.getString(8));
				bean.setCa(rs.getString(9));
				bean.setTst(rs.getInt(10));
				bean.setEscala(rs.getString(11));
				bean.setGrupo(rs.getString(12));
				bean.setJornada(rs.getString(13));


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