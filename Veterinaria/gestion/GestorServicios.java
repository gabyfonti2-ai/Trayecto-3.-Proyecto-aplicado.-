package Veterinaria.gestion;

import java.util.Scanner;

import Veterinaria.paciente.Mascota;
import Veterinaria.paciente.TipoAnimal;
import Veterinaria.facturacion.Factura;
import Veterinaria.servicios.*;
import Veterinaria.excepciones.ServicioNoDisponibleException;

public class GestorServicios {
	public static Factura seleccionarServicios(
            Scanner scanner,
            Mascota mascota) {
		 String numeroFactura = "FAC-" + System.currentTimeMillis();

		    Factura factura = new Factura(numeroFactura, mascota);

		    int opcion;

		    do {

		        System.out.println("\n===== SELECCIÓN DE SERVICIOS =====");
		        System.out.println("Paciente: " + mascota.getNombre());
		        System.out.println("Tipo: " + mascota.getTipoAnimal());

		        System.out.println("\n1. Cita general");
		        System.out.println("2. Especialistas");
		        System.out.println("3. Baño, peluquería, uñas y colmillos");
		        System.out.println("4. Esterilización");
		        System.out.println("5. Vacunas");
		        System.out.println("6. Mantenimiento de pico y uñas");
		        System.out.println("7. Desparasitación");
		        System.out.println("0. Finalizar selección");

		        System.out.print("\nSeleccione un servicio: ");

		        opcion = Integer.parseInt(scanner.nextLine());

		        int precio = 0;
		        String descripcion = "";

		        try {

		            switch (opcion) {

		                // 1. CITA GENERAL
		                case 1:

		                    System.out.println("\nModalidad de cita:");
		                    System.out.println("1. Programada");
		                    System.out.println("2. No programada");
		                    System.out.println("3. Urgencia");
		                    System.out.print("Seleccione una opción: ");
		                    int modalidad = Integer.parseInt(
		                        scanner.nextLine()
		                    );

		                    ModalidadCita tipoCita;

		                    switch (modalidad) {
		                        case 1:
		                            tipoCita = ModalidadCita.PROGRAMADA;
		                            break;

		                        case 2:
		                            tipoCita = ModalidadCita.NO_PROGRAMADA;
		                            break;

		                        case 3:
		                            tipoCita = ModalidadCita.URGENCIA;
		                            break;

		                        default:
		                            System.out.println("Modalidad no válida.");
		                            continue;
		                    }

		                    precio = TarifaServicio.calcularCitaGeneral(
		                        mascota.getTipoAnimal(),
		                        tipoCita,
		                        mascota.getDiaSemana()
		                    );

		                    descripcion = "Cita general - " + tipoCita;

		                    break;


		                // 2. ESPECIALISTAS
		                case 2:

		                    precio = TarifaServicio.calcularEspecialista();

		                    descripcion = "Consulta con especialista";

		                    break;


		                // 3. BAÑO
		                case 3:

		                    boolean premium = false;

		                    if (mascota.getTipoAnimal() == TipoAnimal.AVIAR) {

		                        System.out.println("\nTipo de equipo de baño:");
		                        System.out.println("1. Premium");
		                        System.out.println("2. Estándar");
		                        System.out.print("Seleccione una opción: ");
		                        int tipoBano = Integer.parseInt(
		                            scanner.nextLine()
		                        );

		                        if (tipoBano != 1 && tipoBano != 2) {
		                            System.out.println("Opción no válida.");
		                            continue;
		                        }

		                        premium = tipoBano == 1;
		                    }

		                    precio = TarifaServicio.calcularBano(
		                        mascota.getTipoAnimal(),
		                        mascota.getPeso(),
		                        premium
		                    );

		                    descripcion = "Baño, peluquería, uñas y colmillos";

		                    break;


		                // 4. ESTERILIZACIÓN
		                case 4:

		                    precio = TarifaServicio.calcularEsterilizacion(
		                        mascota.getTipoAnimal(),
		                        mascota.getSexoAnimal(),
		                        mascota.getPeso()
		                    );

		                    descripcion = "Esterilización";

		                    break;


		                // 5. VACUNAS
		                case 5:

		                    System.out.println("\nVacunas disponibles:");
		                    System.out.println("1. Antirrábica");
		                    System.out.println("2. Polivalente canina");
		                    System.out.println("3. Múltiple felina");

		                    int vacuna = Integer.parseInt(
		                        scanner.nextLine()
		                    );

		                    TipoVacuna tipoVacuna;

		                    switch (vacuna) {

		                        case 1:
		                            tipoVacuna = TipoVacuna.ANTIRRABICA;
		                            break;

		                        case 2:
		                            tipoVacuna = TipoVacuna.POLIVALENTE_CANINA;
		                            break;

		                        case 3:
		                            tipoVacuna = TipoVacuna.MULTIPLE_FELINA;
		                            break;

		                        default:
		                            System.out.println("Vacuna no válida.");
		                            continue;
		                    }

		                    precio = TarifaServicio.calcularVacuna(
		                        mascota.getTipoAnimal(),
		                        tipoVacuna
		                    );

		                    descripcion = "Vacuna " + tipoVacuna;

		                    break;


		                // 6. MANTENIMIENTO DE AVES
		                case 6:

		                    System.out.println("\nTipo de mantenimiento:");
		                    System.out.println("1. Sin sedación");
		                    System.out.println("2. Con sedación");
		                    System.out.print("Seleccione una opción: ");
		                    int sedacion = Integer.parseInt(
		                        scanner.nextLine()
		                    );

		                    if (sedacion != 1 && sedacion != 2) {
		                        System.out.println("Opción no válida.");
		                        continue;
		                    }

		                    precio = TarifaServicio.calcularMantenimientoAves(
		                        mascota.getTipoAnimal(),
		                        sedacion == 2
		                    );

		                    descripcion = "Mantenimiento de pico y uñas";

		                    break;


		                // 7. DESPARASITACIÓN
		                case 7:

		                    precio = TarifaServicio.calcularDesparasitacion(
		                        mascota.getTipoAnimal(),
		                        mascota.getPeso()
		                    );

		                    descripcion = "Desparasitación";

		                    break;


		                case 0:

		                    System.out.println("Finalizando selección...");

		                    continue;


		                default:

		                    System.out.println("Servicio no válido.");

		                    continue;
		            }


		            TipoServicio tipoServicio = null;

		            switch (opcion) {
		                case 1:
		                    tipoServicio = TipoServicio.CITA_GENERAL;
		                    break;
		                case 2:
		                    tipoServicio = TipoServicio.ESPECIALISTAS;
		                    break;
		                case 3:
		                    tipoServicio = TipoServicio.BANO_PELUQUERIA_UNAS_COLMILLOS;
		                    break;
		                case 4:
		                    tipoServicio = TipoServicio.ESTERILIZACION;
		                    break;
		                case 5:
		                    tipoServicio = TipoServicio.VACUNAS;
		                    break;
		                case 6:
		                    tipoServicio = TipoServicio.MANTENIMIENTO_PICO_UNAS;
		                    break;
		                case 7:
		                    tipoServicio = TipoServicio.DESPARACITACION;
		                    break;
		            }

		            CatalogoServicio servicio = new CatalogoServicio(
		            	    tipoServicio,
		            	    descripcion,
		            	    String.valueOf(precio),
		            	    precio
		            );

		            factura.agregarServicio(servicio);

		            System.out.println("\nServicio agregado correctamente.");
		            System.out.println("Servicio: " + descripcion);
		            System.out.println("Precio: $" + precio);


		        } catch (ServicioNoDisponibleException e) {

		            System.out.println("\nServicio no disponible.");
		            System.out.println(e.getMessage());

		        }

		    } while (opcion != 0);


		    if (factura.getServicios().isEmpty()) {

		        System.out.println("No se seleccionaron servicios.");

		        return null;
		    }

		    System.out.println("\nServicios seleccionados correctamente.");

		    System.out.println(
		        "Subtotal: $" + factura.calcularSubtotal()
		    );

		    System.out.println(
		        "Descuento: $" + factura.calcularDescuento()
		    );

		    System.out.println(
		        "Total: $" + factura.calcularTotal()
		    );
		   
		    return factura;
		}
	}
