package Veterinaria.servicios;

public class CatalogoServicio {
	 private TipoServicio tipoServicio;
	    private String descripcion;
	    private String detalleTarifa;
	    private int precio;
	    
	    public CatalogoServicio(
	    		 TipoServicio tipoServicio,
	             String descripcion,
	             String detalleTarifa,
	             int precio) {

	         this.tipoServicio = tipoServicio;
	         this.descripcion = descripcion;
	         this.detalleTarifa = detalleTarifa;
	         this.precio = precio;
	    }

	    public TipoServicio getTipoServicio() {
	        return tipoServicio;
	    }

	    public String getDescripcion() {
	        return descripcion;
	    }
	    public String getDetalleTarifa() {
	        return detalleTarifa;
	    }

	    public int getPrecio() {
	        return precio;
	    }
		
}
