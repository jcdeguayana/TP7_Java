package daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import dao.DaoTipoSeguro;
import entidades.Seguro;
import entidades.TipoSeguro;

public class DaoTipoSeguroImpl implements DaoTipoSeguro {

	public static String obtenerLista = "SELECT idTipo, descripcion FROM tiposeguros";
	public static String proximoId = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_NAME = 'seguros' AND TABLE_SCHEMA = 'segurosgroup'";
	
	@Override
	public List<TipoSeguro> listarTipoSeguros() {

		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		List<TipoSeguro> listaTipos = new ArrayList<>();
	    try {
	    	statement = conexion.prepareStatement(obtenerLista);
			ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            TipoSeguro tipo = new TipoSeguro();
	            tipo.setIdTipo(rs.getInt("idTipo"));
	            tipo.setDescripcion(rs.getString("descripcion"));
	            listaTipos.add(tipo);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

		return listaTipos;
	}

	@Override
	public int siguienteId() {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		int id=0;
	    try {
	    	statement = conexion.prepareStatement(proximoId);
			ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	        	id = rs.getInt("AUTO_INCREMENT");
	        }
	        }catch (Exception e) {
		        e.printStackTrace();
		    }
		return id;
	}

}
	
