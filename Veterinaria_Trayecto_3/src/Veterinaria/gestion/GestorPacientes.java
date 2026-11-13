package Veterinaria.gestion;

import java.util.List;
import java.util.Scanner;

import Veterinaria.paciente.Mascota;
import Veterinaria.paciente.TipoAnimal;
import Veterinaria.paciente.SexoAnimal;
import Veterinaria.paciente.DiaSemana;
import Veterinaria.util.FilesUtils;
import Veterinaria.paciente.MascotaCreacion;

public class GestorPacientes {

    // SELECCIONAR PACIENTE
    public static Mascota seleccionarPaciente(Scanner scanner) {

    	 List<Mascota> pacientes = FilesUtils.leerMascotas();

    	    if (pacientes.isEmpty()) {
    	        System.out.println("No hay pacientes registrados.");
    	        return null;
    	    }

    	    System.out.println("\n===== SELECCIONAR PACIENTE =====");

    	    for (int i = 0; i < pacientes.size(); i++) {

    	        Mascota mascota = pacientes.get(i);

    	        System.out.println(
    	            (i + 1) + ". " +
    	            mascota.getNombre() +
    	            " | " + mascota.getTipoAnimal() +
    	            " | Dueño: " + mascota.getDueno()
    	        );
    	    }

    	    System.out.print("\nSeleccione el número del paciente: ");

    	    int opcion;

    	    try {
    	        opcion = Integer.parseInt(scanner.nextLine().trim());
    	    } catch (NumberFormatException e) {
    	        System.out.println("Debe ingresar un número válido.");
    	        return null;
    	    }

    	    Mascota seleccionada = pacientes.get(opcion - 1);

    	    System.out.println("\n===== PACIENTE SELECCIONADO =====");
    	    System.out.println("Nombre: " + seleccionada.getNombre());
    	    System.out.println("Tipo: " + seleccionada.getTipoAnimal());
    	    System.out.println("Sexo: " + seleccionada.getSexoAnimal());
    	    System.out.println("Edad: " + seleccionada.getEdad());
    	    System.out.println("Peso: " + seleccionada.getPeso() + " kg");
    	    System.out.println("Raza: " + seleccionada.getRaza());
    	    System.out.println("Día: " + seleccionada.getDiaSemana());
    	    System.out.println("Dueño: " + seleccionada.getDueno());

    	    return seleccionada;
    }
    
 // REGISTRAR PACIENTE
    public static void agregarPaciente(Scanner scanner) {
    	System.out.println("\n===== REGISTRO DE PACIENTE =====");

        // Seleccionar tipo de animal
        System.out.println("Tipo de animal:");
        System.out.println("1. Canino");
        System.out.println("2. Felino");
        System.out.println("3. Aviar");
        System.out.print("Seleccione una opción: ");
        int tipo = Integer.parseInt(scanner.nextLine());

        TipoAnimal tipoAnimal;

        switch (tipo) {
            case 1:
                tipoAnimal = TipoAnimal.CANINO;
                break;

            case 2:
                tipoAnimal = TipoAnimal.FELINO;
                break;

            case 3:
                tipoAnimal = TipoAnimal.AVIAR;
                break;

            default:
                System.out.println("Tipo de animal no válido.");
                return;
        }

        System.out.print("Nombre de la mascota(Mayuscula): ");
        String nombre = scanner.nextLine().trim();

        // Seleccionar sexo
        System.out.println("\nSexo:");
        System.out.println("1. Hembra");
        System.out.println("2. Macho");
        System.out.print("Seleccione una opción: ");

        int sexo = Integer.parseInt(scanner.nextLine());

        SexoAnimal sexoAnimal;

        if (sexo == 1) {
            sexoAnimal = SexoAnimal.HEMBRA;
        } else if (sexo == 2) {
            sexoAnimal = SexoAnimal.MACHO;
        } else {
            System.out.println("Sexo no válido.");
            return;
        }

        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine());

        System.out.print("Peso en kg: ");
        double peso = Double.parseDouble(
            scanner.nextLine().replace(",", ".")
        );

        System.out.print("Raza(Mayuscula): ");
        String raza = scanner.nextLine().trim();

        // Seleccionar día
        System.out.println("\nDía de atención:");

        DiaSemana[] dias = DiaSemana.values();

        for (int i = 0; i < dias.length; i++) {
            System.out.println((i + 1) + ". " + dias[i]);
        }

        System.out.print("Seleccione una opción: ");

        int dia = Integer.parseInt(scanner.nextLine());

        if (dia < 1 || dia > dias.length) {
            System.out.println("Día no válido.");
            return;
        }

        DiaSemana diaSemana = dias[dia - 1];

        System.out.print("Nombre del dueño(Mayuscula): ");
        String dueno = scanner.nextLine().trim();

        // Crear objeto mascota
        Mascota nuevaMascota = MascotaCreacion.crearMascota(
        	    tipoAnimal,
        	    nombre,
        	    sexoAnimal,
        	    edad,
        	    peso,
        	    raza,
        	    diaSemana,
        	    dueno
        );

        // Guardar en archivo
        FilesUtils.escribirMascota(nuevaMascota);
    }

    // ELIMINAR PACIENTE
    public static void eliminarPaciente(Scanner scanner) {

    	    List<Mascota> pacientes = FilesUtils.leerMascotas();

    	    if (pacientes.isEmpty()) {
    	        System.out.println("No hay pacientes registrados.");
    	        return;
    	    }

    	    System.out.println("\n===== ELIMINAR PACIENTE =====");

    	    for (int i = 0; i < pacientes.size(); i++) {

    	        Mascota mascota = pacientes.get(i);

    	        System.out.println(
    	            (i + 1) + ". " +
    	            mascota.getNombre() +
    	            " | " + mascota.getTipoAnimal() +
    	            " | Dueño: " + mascota.getDueno()
    	        );
    	    }

    	    System.out.print("\nSeleccione el paciente que desea eliminar: ");

    	    int opcion = Integer.parseInt(scanner.nextLine());

    	    if (opcion < 1 || opcion > pacientes.size()) {

    	        System.out.println("Opción no válida.");
    	        return;
    	    }

    	    Mascota seleccionada = pacientes.get(opcion - 1);

    	    System.out.println(
    	        "\n¿Está seguro de eliminar a " +
    	        seleccionada.getNombre() + "?"
    	    );

    	    System.out.println("1. Sí");
    	    System.out.println("2. No");
    	    System.out.print("Seleccione una opción: ");
    	    
    	    int confirmar = Integer.parseInt(scanner.nextLine());

    	    if (confirmar == 1) {

    	        boolean resultado = FilesUtils.eliminarPaciente(seleccionada);

    	        if (resultado) {
    	            System.out.println("Paciente eliminado correctamente.");
    	        }

    	    } else {
    	        System.out.println("Operación cancelada.");
    	    }
    	}
}