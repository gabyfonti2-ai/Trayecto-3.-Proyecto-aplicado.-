
package Veterinaria.util;

	import java.io.IOException;
	import java.nio.charset.StandardCharsets;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.StandardOpenOption;
	import java.util.ArrayList;
	import java.util.List;

	import Veterinaria.paciente.Mascota;
	import Veterinaria.paciente.TipoAnimal;
	import Veterinaria.paciente.SexoAnimal;
	import Veterinaria.paciente.DiaSemana;
	import Veterinaria.paciente.MascotaCreacion;

	public class FilesUtils {
		

	    private static final String SEPARADOR = "|";

	    // Archivo ubicado en la raíz del proyecto
	    private static final Path RUTA_ARCHIVO =
	            Path.of("Pacientes.txt");
	    
	    // LEER MASCOTAS
	    public static List<Mascota> leerMascotas() {

	        List<Mascota> mascotas = new ArrayList<>();

	        try {
	            if (!Files.exists(RUTA_ARCHIVO)) {
	                System.out.println("No se encontró el archivo: "
	                        + RUTA_ARCHIVO.toAbsolutePath());
	                return mascotas;
	            }

	            List<String> lineas = Files.readAllLines(
	                    RUTA_ARCHIVO, StandardCharsets.UTF_8);

	            for (String linea : lineas) {

	                if (linea.trim().isEmpty()) {
	                    continue;
	                }

	                // Elimina espacios y descarta separadores vacíos finales
	                String[] datos = linea.trim().split("\\|");

	                if (datos.length != 8) {
	                    System.out.println("Registro incompleto ("
	                            + datos.length + " campos): " + linea);
	                    continue;
	                }

	                try {
	                	Mascota mascota = MascotaCreacion.crearMascota(
	                		    TipoAnimal.valueOf(datos[0].trim()),
	                		    datos[1].trim(),
	                		    SexoAnimal.valueOf(datos[2].trim()),
	                		    Integer.parseInt(datos[3].trim()),
	                		    Double.parseDouble(
	                		        datos[4].trim().replace(",", ".")
	                		    ),
	                		    datos[5].trim(),
	                		    DiaSemana.valueOf(datos[6].trim()),
	                		    datos[7].trim()
	                    );

	                    mascotas.add(mascota);

	                } catch (IllegalArgumentException e) {
	                    System.out.println("Error en los datos del registro: "
	                            + linea);
	                }
	            }

	        } catch (IOException e) {
	            System.out.println("Error al leer mascotas: " + e.getMessage());
	        }

	        return mascotas;
	    }

	    // ESCRIBIR MASCOTA
	    public static void escribirMascota(Mascota mascota) {

	        String linea = mascota.getTipoAnimal() + SEPARADOR
	                + mascota.getNombre() + SEPARADOR
	                + mascota.getSexoAnimal() + SEPARADOR
	                + mascota.getEdad() + SEPARADOR
	                + mascota.getPeso() + SEPARADOR
	                + mascota.getRaza() + SEPARADOR
	                + mascota.getDiaSemana() + SEPARADOR
	                + mascota.getDueno();

	        try {
	            // Verifica si el archivo ya tiene contenido
	            if (Files.exists(RUTA_ARCHIVO)
	                    && Files.size(RUTA_ARCHIVO) > 0) {

	                String contenido = Files.readString(
	                        RUTA_ARCHIVO, StandardCharsets.UTF_8);

	                if (!contenido.endsWith("\n")
	                        && !contenido.endsWith("\r")) {

	                    linea = System.lineSeparator() + linea;
	                }
	            }

	            Files.writeString(
	                    RUTA_ARCHIVO,
	                    linea + System.lineSeparator(),
	                    StandardCharsets.UTF_8,
	                    StandardOpenOption.CREATE,
	                    StandardOpenOption.APPEND
	            );

	            System.out.println("Mascota guardada correctamente.");

	        } catch (IOException e) {
	            System.out.println("Error al guardar mascota: "
	                    + e.getMessage());}
	        
	    //ELIMINAR MASCOTA
	        }
	    public static boolean eliminarPaciente(Mascota seleccionada) {
	    	List<Mascota> mascotas = leerMascotas();

	        // Filtramos y removemos la mascota que coincida en nombre y dueño
	        boolean removido = mascotas.removeIf(m -> 
	            m.getNombre().equalsIgnoreCase(seleccionada.getNombre()) && 
	            m.getDueno().equalsIgnoreCase(seleccionada.getDueno())
	        );

	        if (!removido) {
	            System.out.println("No se encontró el paciente en el registro.");
	            return false;
	        }

	        try {
	            // 1. Sobrescribimos el archivo dejándolo vacío
	            Files.writeString(
	                    RUTA_ARCHIVO, 
	                    "", 
	                    StandardCharsets.UTF_8, 
	                    StandardOpenOption.CREATE, 
	                    StandardOpenOption.TRUNCATE_EXISTING
	            );
	            // 2. Volvemos a escribir todos los pacientes restantes uno por uno
	            //(Se garantiza la eliminación)
	            for (Mascota m : mascotas) {
	                escribirMascota(m);
	            }
	            return true;
	        } catch (IOException e) {
	            System.out.println("Error al actualizar el archivo de pacientes: " + e.getMessage());
	            return false;
	        }
		}
	    }
    