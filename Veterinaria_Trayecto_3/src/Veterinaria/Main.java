package Veterinaria;

import java.util.List;
import java.util.Scanner;

import Veterinaria.gestion.GestorCatalogo;
import Veterinaria.gestion.GestorFacturacion;
import Veterinaria.gestion.GestorPacientes;
import Veterinaria.gestion.GestorServicios;
import Veterinaria.facturacion.Factura;
import Veterinaria.paciente.Mascota;
import Veterinaria.paciente.TipoAnimal;
import Veterinaria.util.FilesUtils;



public class Main {
	 public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	Mascota pacienteSeleccionado = null;
	Factura facturaActual = null;

	int opcion = 0;

    do {
    	System.out.println("\n===== MASCOTAS SANAS🐕🐈🐦 =====");
        System.out.println("1. Consultar todos los pacientes");
        System.out.println("2. Consultar solo Caninos");
        System.out.println("3. Consultar solo Felinos");
        System.out.println("4. Consultar solo Aves");
        System.out.println("5. Catalogo de servicios");
        System.out.println("6. Añadir paciente");
        System.out.println("7. Eliminar paciente");
        System.out.println("8. Seleccionar paciente");
        System.out.println("9. Selección de servicio");
        System.out.println("10. Generar factura");
        System.out.println("11. Salir");
        System.out.print("\nSeleccione una opción: ");

        String entrada = scanner.nextLine().trim();

        if (entrada.isEmpty()) {
            System.out.println("Debe ingresar una opción.");
            continue;
        }

        try {
            opcion = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            System.out.println("Ingrese un número válido.");
            continue;
        }

        switch (opcion) {
        	case 1:
        		 List<Mascota> pacientes = FilesUtils.leerMascotas();

        	        System.out.println("===== PACIENTES REGISTRADOS =====");

        	        for (Mascota mascota : pacientes) {

        	            System.out.println("Nombre: " + mascota.getNombre());
        	            System.out.println("Tipo: " + mascota.getTipoAnimal());
        	            System.out.println("Sexo: " + mascota.getSexoAnimal());
        	            System.out.println("Edad: " + mascota.getEdad());
        	            System.out.println("Peso(kg): " + mascota.getPeso());
        	            System.out.println("Raza (Dato unico): " + mascota.getRaza());
        	            System.out.println("Día: " + mascota.getDiaSemana());
        	            System.out.println("Dueño: " + mascota.getDueno());

        	            System.out.println("------------------------------");
        	        }

        	        System.out.println("Total de pacientes: " + pacientes.size());
        		break;
        	case 2:
        		 List<Mascota> CANINO = FilesUtils.leerMascotas();

     	        System.out.println("===== PACIENTES CANINOS =====");

     	        for (Mascota mascota : CANINO) {
     	        	if (mascota.getTipoAnimal() == TipoAnimal.CANINO) {
     	            System.out.println("Nombre: " + mascota.getNombre());
     	            System.out.println("Tipo: " + mascota.getTipoAnimal());
     	            System.out.println("Sexo: " + mascota.getSexoAnimal());
     	            System.out.println("Edad: " + mascota.getEdad());
     	            System.out.println("Peso(kg): " + mascota.getPeso());
     	            System.out.println("Raza (Dato unico): " + mascota.getRaza());
     	            System.out.println("Día: " + mascota.getDiaSemana());
     	            System.out.println("Dueño: " + mascota.getDueno());

     	            System.out.println("------------------------------");
     	        }}
     	       
        		break;
        	case 3:
        		 List<Mascota> FELINO = FilesUtils.leerMascotas();

      	       System.out.println("===== PACIENTES FELINOS =====");

      	        for (Mascota mascota : FELINO) {
      	        	if (mascota.getTipoAnimal() == TipoAnimal.FELINO) {
      	            System.out.println("Nombre: " + mascota.getNombre());
      	            System.out.println("Tipo: " + mascota.getTipoAnimal());
      	            System.out.println("Sexo: " + mascota.getSexoAnimal());
      	            System.out.println("Edad: " + mascota.getEdad());
      	            System.out.println("Peso(kg): " + mascota.getPeso());
      	            System.out.println("Raza (Dato unico): " + mascota.getRaza());
      	            System.out.println("Día: " + mascota.getDiaSemana());
      	            System.out.println("Dueño: " + mascota.getDueno());

      	            System.out.println("------------------------------");
      	        }}
      	       
        		break;
        	case 4:
        		List<Mascota> AVIAR = FilesUtils.leerMascotas();

       	       System.out.println("===== PACIENTES AVIARES =====");

       	        for (Mascota mascota : AVIAR) {
       	        	if (mascota.getTipoAnimal() == TipoAnimal.AVIAR) {
       	            System.out.println("Nombre: " + mascota.getNombre());
       	            System.out.println("Tipo: " + mascota.getTipoAnimal());
       	            System.out.println("Sexo: " + mascota.getSexoAnimal());
       	            System.out.println("Edad: " + mascota.getEdad());
       	            System.out.println("Peso(kg): " + mascota.getPeso());
       	            System.out.println("Raza (Dato unico): " + mascota.getRaza());
       	            System.out.println("Día: " + mascota.getDiaSemana());
       	            System.out.println("Dueño: " + mascota.getDueno());

       	            System.out.println("------------------------------");
       	        }}
       	       
        		break;
        	case 5:
        		GestorCatalogo.mostrarCatalogo();
        		break;
        	case 6:
        		GestorPacientes.agregarPaciente(scanner);
        		break;
        	case 7:
        		 GestorPacientes.eliminarPaciente(scanner);

        	        pacienteSeleccionado = null;
        	        facturaActual = null;
        		break;
        	case 8:
        		 pacienteSeleccionado =
                 GestorPacientes.seleccionarPaciente(scanner);

             facturaActual = null;
        		break;
        	case 9:
        		if (pacienteSeleccionado == null) {
        	        System.out.println(
        	            "Primero debe seleccionar un paciente."
        	        );
        	    } else {
        	    	facturaActual =
        	                GestorServicios.seleccionarServicios(
        	                    scanner,
        	                    pacienteSeleccionado
        	                );
        	    }
        		break;
        	case 10:
        		  GestorFacturacion.generarFactura(facturaActual);
        		break;
        	case 11:
        		System.out.println("Finalización del proceso"+ "\nUsted salió del sistema");
        	default:
                System.out.println("Opción no válida.");
        		break;}
        }
    		while (opcion != 11);
	 }   
}

