package Veterinaria.gestion;
import java.util.ArrayList;
import java.util.List;

import Veterinaria.servicios.CatalogoServicio;
import Veterinaria.servicios.TipoServicio;

public class GestorCatalogo {
	public static List<CatalogoServicio> obtenerCatalogoCompleto() {
        List<CatalogoServicio> lista = new ArrayList<>();
        lista.add(new CatalogoServicio(
        TipoServicio.CITA_GENERAL,
        "Consultas generales para perros y gatos. " +
        "Junto con atención para aves de compañía.",
        "(Lunes a sábado / domingos y festivos)"+
        "\nPerros y gatos:\n" +
        "-Programada: $94.000 / $110.000\n" +
        "-No programada: $116.000 / $135.000\n" +
        "-Urgencia: $142.000 / $160.000\n" +
        "Aves:\n" +
        "-Programada: $110.000 / $130.000\n" +
        "-No programada: $132.000 / $140.000\n" +
        "-Urgencia: $145.000 / $165.000\n", 0
            ));
        lista.add(new CatalogoServicio(
        TipoServicio.ESPECIALISTAS,
        "Cirugía, dermatología, ortopedia, cardiología, " +
        "fisioterapia, oftalmología, endocrinología, " +
        "odontología, nutrición, oncología, gastroenterología, " +
        "rayos X y laboratorios.",
        "$267.000. Aplica para todas las especies.", 0
            ));

        lista.add(new CatalogoServicio(
        TipoServicio.BANO_PELUQUERIA_UNAS_COLMILLOS,
        "Baño, peluquería, uñas y colmillos.",
        "Perros pequeños (<10 kg): $85.000\n" +
        "Perros medianos (10 a 25 kg): $95.000\n" +
        "Perros grandes (>25 kg): $105.000\n" +
        "Gatos: $115.000\n" +
        "Equipo de baño para Aves->premium: $96.000\n" +
        "Equipo de baño para Aves->estándar: $77.000", 0
            ));

        lista.add(new CatalogoServicio(
        TipoServicio.ESTERILIZACION,
        "Procedimiento quirúrgico de esterilización.",
        "Gato macho: $180.000\n" +
        "Gato hembra: $220.000\n" +
        "Perro pequeño (<10 kg): $185.000\n" +
        "Perro mediano (10 a 25 kg): $210.000\n" +
        "Perro grande (>25 kg): $235.000\n" +
        "No disponible para aves.", 0
            ));

        lista.add(new CatalogoServicio(
        TipoServicio.VACUNAS,
        "Aplicación de vacunas según especie.",
        "Antirrábica: $35.000\n" +
        "Polivalente canina: $60.000\n" +
        "Múltiple felina: $130.000\n" +
        "No hay vacunas disponibles para aves de compañía.", 0
            ));

        lista.add(new CatalogoServicio(
        TipoServicio.MANTENIMIENTO_PICO_UNAS,
        "Mantenimiento de pico y uñas. Servicio exclusivo para aves.",
        "Sin sedación: $40.000\n" +
        "Con sedación: $75.000", 0
            ));

        lista.add(new CatalogoServicio(
        TipoServicio.DESPARACITACION,
        "Tratamiento de desparasitación.",
        "Gatos: $80.000\n" +
        "Perros pequeños (<10 kg): $80.000\n" +
        "Perros medianos (10 a 25 kg): $100.000\n" +
        "Perros grandes (>25 kg): $120.000\n" +
        "Aves: $37.000", 0
            ));

            return lista;
        }

        public static void mostrarCatalogo() {

            List<CatalogoServicio> lista = obtenerCatalogoCompleto();

            System.out.println("\n===== CATÁLOGO DE SERVICIOS =====");

            for (CatalogoServicio servicio : lista) {

                System.out.println("\n--------------------------------");
                System.out.println("SERVICIO: "
                        + servicio.getTipoServicio());

                System.out.println("\nDescripción:");
                System.out.println(servicio.getDescripcion());

                System.out.println("\nTarifas:");
                System.out.println(servicio.getDetalleTarifa());
            }

            System.out.println("\n--------------------------------");
            System.out.println("DESCUENTO: 10% AVES, 15% FELINOS Y 20% CANINOS");
            System.out.println("Aplica al seleccionar más de un servicio.");
            System.out.println("================================");
        }
}
