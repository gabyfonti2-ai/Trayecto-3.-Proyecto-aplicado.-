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

    	    int opcion = -1;
            while (true) {
                System.out.print("\nSeleccione el número del paciente: ");
                String entrada = scanner.nextLine().trim();
                
                try {
                    opcion = Integer.parseInt(entrada);
                    if (opcion >= 1 && opcion <= pacientes.size()) {
                        break;
                    }
                    System.out.println("Número de paciente fuera de rango. Intente de nuevo.");
                } catch (NumberFormatException e) {
                    System.out.println("Debe ingresar un número válido.");
                }
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
    
 // TEXTOS VACIOS: Validación para textos que no pueden estar vacíos
    private static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        String texto = "";
        while (true) {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            
            if (!texto.isEmpty()) {
                break;
            }
            System.out.println("Este campo no puede estar vacío. Intente de nuevo.");
        }
        return texto;
    }

    // VALORES NO VALIDOS: Validación para enteros mayores o iguales a 0
    private static int leerEnteroPositivo(Scanner scanner, String mensaje) {
        int valor = -1;
        while (true) {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= 0) {
                    break;
                }
                System.out.println("No se permiten números negativos. Intente de nuevo.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero válido.");
            }
        }
        return valor;
    }
    
    // VALORES CON RANGO: Validación para opciones de menú numéricas cerradas (Ej: 1 al 3)
    private static int leerOpcionMenu(Scanner scanner, String mensaje, int min, int max) {
        int valor = -1;
        while (true) {
            System.out.print(mensaje);
            try {
                valor = Integer.parseInt(scanner.nextLine().trim());
                if (valor >= min && valor <= max) {
                    break;
                }
                System.out.println("Opción fuera de rango. Debe ser entre " + min + " y " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
            }
        }
        return valor;
    }

    // VALIDACION DECIMALES: Validación para decimales mayores o iguales a 0 (Peso)
    private static double leerDoublePositivo(Scanner scanner, String mensaje) {
        double valor = -1.0;
        while (true) {
            System.out.print(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
                if (valor >= 0) {
                    break;
                }
                System.out.println("El peso no puede ser negativo. Intente de nuevo.");
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un valor numérico válido.");
            }
        }
        return valor;
    }
 // REGISTRAR PACIENTE
    public static void agregarPaciente(Scanner scanner) {
    	System.out.println("\n===== REGISTRO DE PACIENTE =====");

        // Seleccionar tipo de animal
        System.out.println("Tipo de animal:");
        System.out.println("1. Canino");
        System.out.println("2. Felino");
        System.out.println("3. Aviar");
        int tipo = leerOpcionMenu(scanner, "Seleccione una opción: ", 1, 3);

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

        String nombre = leerTextoNoVacio(scanner, "Nombre de la mascota (Mayúscula): ");
        

        // Seleccionar sexo
        System.out.println("\nSexo:");
        System.out.println("1. Hembra");
        System.out.println("2. Macho");
        int sexo = leerOpcionMenu(scanner, "Seleccione una opción: ", 1, 2);
        SexoAnimal sexoAnimal = (sexo == 1) ? SexoAnimal.HEMBRA : SexoAnimal.MACHO;
        
        //Diligenciar info escrita
        int edad = leerEnteroPositivo(scanner, "Edad: ");
        double peso = leerDoublePositivo(scanner, "Peso en kg: ");

        String raza = leerTextoNoVacio(scanner, "Raza (Mayúscula): ");

        // Seleccionar día
        System.out.println("\nDía de atención:");

        DiaSemana[] dias = DiaSemana.values();

        for (int i = 0; i < dias.length; i++) {
            System.out.println((i + 1) + ". " + dias[i]);
        }

        int diaOpcion = leerOpcionMenu(scanner, "Seleccione una opción: ", 1, dias.length);
        DiaSemana diaSemana = dias[diaOpcion - 1];

        String dueno = leerTextoNoVacio(scanner, "Nombre del dueño (Mayúscula): ");

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

    	    int opcion = leerOpcionMenu(scanner, "\nSeleccione el paciente que desea eliminar: ", 1, pacientes.size());
            Mascota seleccionada = pacientes.get(opcion - 1);

            System.out.println(
                "\n¿Está seguro de eliminar a " +
                seleccionada.getNombre() + "?"
            );
            System.out.println("1. Sí");
            System.out.println("2. No");
            
            int confirmar = leerOpcionMenu(scanner, "Seleccione una opción: ", 1, 2);

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
