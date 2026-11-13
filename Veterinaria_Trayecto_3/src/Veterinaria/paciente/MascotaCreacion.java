package Veterinaria.paciente;

public class MascotaCreacion {
	 public static Mascota crearMascota(
	            TipoAnimal tipoAnimal,
	            String nombre,
	            SexoAnimal sexoAnimal,
	            int edad,
	            double peso,
	            String raza,
	            DiaSemana diaSemana,
	            String dueno) {

	        switch (tipoAnimal) {

	            case CANINO:
	                return new Canino(
	                    nombre, sexoAnimal, edad, peso,
	                    raza, diaSemana, dueno
	                );

	            case FELINO:
	                return new Felino(
	                    nombre, sexoAnimal, edad, peso,
	                    raza, diaSemana, dueno
	                );

	            case AVIAR:
	                return new Aviar(
	                    nombre, sexoAnimal, edad, peso,
	                    raza, diaSemana, dueno
	                );

	            default:
	                throw new IllegalArgumentException(
	                    "Tipo de animal no válido."
	                );
	        }
	    }
}
