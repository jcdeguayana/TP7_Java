package dao;

import java.util.List;

import entidades.Seguro;
import entidades.TipoSeguro;

public interface DaoTipoSeguro {

	public List<TipoSeguro> listarTipoSeguros();
	public int siguienteId();
}
