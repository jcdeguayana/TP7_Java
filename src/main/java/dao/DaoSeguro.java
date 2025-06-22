package dao;

import java.util.List;

import entidades.Seguro;

public interface DaoSeguro {
	public List<Seguro> listarSeguros();
	public boolean insertarSeguro(Seguro seguro);
}
