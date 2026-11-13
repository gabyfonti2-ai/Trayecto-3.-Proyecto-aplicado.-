package Veterinaria.paciente;

public class Canino extends Mascota{
	 public Canino(
			 String nombre, 
			 SexoAnimal sexoAnimal, 
			 int edad,
             double peso, 
             String raza, 
             DiaSemana diaSemana, 
             String dueno) {

   super(
		   TipoAnimal.CANINO, 
		   nombre, 
		   sexoAnimal, 
		   edad,
		   peso, 
		   raza, 
		   diaSemana, 
		   dueno);
}
	 @Override
	 public double aplicarAjuste(double precio) {
   return precio * 0.80; // Descuento del 20 %
}
}
