package Veterinaria.paciente;

public class Aviar extends Mascota {

    public Aviar(
            String nombre,
            SexoAnimal sexoAnimal,
            int edad,
            double peso,
            String plumaje,
            DiaSemana diaSemana,
            String dueno) {

        super(
            TipoAnimal.AVIAR,
            nombre,
            sexoAnimal,
            edad,
            peso,
            plumaje,
            diaSemana,
            dueno
        );
    }

    @Override
    public double aplicarAjuste(double precio) {
    	 return precio * 0.90; // Descuento del 10 %
    }
}
