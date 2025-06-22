package entidades;

public class Seguro {
    private int idSeguro;
    private String descripcion;
    private TipoSeguro tipo;
    private double costoContratacion;
    private double costoAsegurado;


    public Seguro() {}


	public int getIdSeguro() {
		return idSeguro;
	}


	public void setIdSeguro(int idSeguro) {
		this.idSeguro = idSeguro;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public TipoSeguro getTipo() {
		return tipo;
	}


	public void setTipo(TipoSeguro tipo) {
		this.tipo = tipo;
	}


	public double getCostoContratacion() {
		return costoContratacion;
	}


	public void setCostoContratacion(double costoContratacion) {
		this.costoContratacion = costoContratacion;
	}


	public double getCostoAsegurado() {
		return costoAsegurado;
	}


	public void setCostoAsegurado(double costoAsegurado) {
		this.costoAsegurado = costoAsegurado;
	}
 
    
}