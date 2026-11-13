package Veterinaria.paciente;

public class Felino extends Mascota{
	public Felino(
            String nombre,
            SexoAnimal sexoAnimal,
            int edad,
            double peso,
            String raza,
            DiaSemana diaSemana,
            String dueno) {

        super(
            TipoAnimal.FELINO,
            nombre,
            sexoAnimal,
            edad,
            peso,
            raza,
            diaSemana,
            dueno
        );
    }

	 @Override
	    public double aplicarAjuste(double precio) {
	        return precio * 0.85; // Descuento del 15 %

}
    }
