package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DaoSeguro;
import entidades.Seguro;
import entidades.TipoSeguro;

public class DaoSeguroImpl implements DaoSeguro{

	public static String obtenerLista = "SELECT s.idSeguro, s.descripcion, s.costoContratacion, s.costoAsegurado, "
										+ "ts.idTipo, ts.descripcion AS tipoDescripcion "
										+ "FROM seguros s "
										+ "JOIN tipoSeguros ts ON s.idTipo = ts.idTipo;";
	public static String obtenerUnSeguro = "SELECT s.idSeguro, s.descripcion, s.costoContratacion, s.costoAsegurado, "
			+ "ts.idTipo, ts.descripcion AS tipoDescripcion "
			+ "FROM seguros s "
			+ "JOIN tipoSeguros ts ON s.idTipo = ts.idTipo WHERE ts.idTipo = ?;";
	
	public static String insertar = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?, ?, ?, ?)";
	
    
	public List<Seguro> listarSegurosPorTipo(String tipoDescripcion) {
	    PreparedStatement statement;
	    Connection conexion = Conexion.getConexion().getSQLConexion();
	    List<Seguro> listaSeguros = new ArrayList<>();
	    
	    try {
	        statement = conexion.prepareStatement(obtenerUnSeguro);
	        statement.setInt(1, Integer.parseInt(tipoDescripcion)); 

	        ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            Seguro seguro = new Seguro();
	            seguro.setIdSeguro(rs.getInt("idSeguro"));
	            seguro.setDescripcion(rs.getString("descripcion"));

	            TipoSeguro tipo = new TipoSeguro();
	            tipo.setDescripcion(rs.getString("tipoDescripcion"));
	            seguro.setTipo(tipo);

	            seguro.setCostoContratacion(rs.getDouble("costoContratacion"));
	            seguro.setCostoAsegurado(rs.getDouble("costoAsegurado"));

	            listaSeguros.add(seguro);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return listaSeguros;
	}

	@Override
	public List<Seguro> listarSeguros() {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		List<Seguro> listaSeguros = new ArrayList<>();
	    try {
	    	statement = conexion.prepareStatement(obtenerLista);
			ResultSet rs = statement.executeQuery();
	        while (rs.next()) {
	            Seguro seguro = new Seguro();
	            seguro.setIdSeguro(rs.getInt("idSeguro"));
	            seguro.setDescripcion(rs.getString("descripcion"));
	            
	            /////TipoSeguro//////
	            TipoSeguro tipoSeguro = new TipoSeguro();
	            /*tipoSeguro.setIdTipo(rs.getInt("idTipo"));*/
	            tipoSeguro.setDescripcion(rs.getString("tipoDescripcion"));
	            /////TipoSeguro//////
	            seguro.setTipo(tipoSeguro);
	            
	            seguro.setCostoContratacion(rs.getDouble("costoContratacion"));
	            seguro.setCostoAsegurado(rs.getDouble("CostoAsegurado"));
	            
	            listaSeguros.add(seguro);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

		return listaSeguros;
	}

	@Override
	public boolean insertarSeguro(Seguro seguro) {
		
		boolean insertado = false;
	    PreparedStatement statement;
	    Connection conexion = Conexion.getConexion().getSQLConexion();

	    try {
	        statement = conexion.prepareStatement(insertar);
	        statement.setString(1, seguro.getDescripcion());
	        statement.setInt(2, seguro.getTipo().getIdTipo());
	        statement.setDouble(3, seguro.getCostoContratacion());
	        statement.setDouble(4, seguro.getCostoAsegurado());

	        if (statement.executeUpdate() > 0) {
	            conexion.commit();
	            insertado = true;
	        }

	    } catch (Exception e) {
	        try {
	            conexion.rollback();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        e.printStackTrace();
	    }

	    return insertado;
	}

}
