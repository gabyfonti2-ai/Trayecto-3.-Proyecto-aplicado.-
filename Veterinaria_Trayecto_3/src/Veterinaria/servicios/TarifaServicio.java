package Veterinaria.servicios;

import Veterinaria.paciente.TipoAnimal;
import Veterinaria.paciente.SexoAnimal;
import Veterinaria.paciente.DiaSemana;
import Veterinaria.excepciones.ServicioNoDisponibleException;

public class TarifaServicio {

    // Tarifa de especialistas//
    public static final int ESPECIALISTAS = 267000;


    // 1. CITAS GENERALES
    public static int calcularCitaGeneral(
            TipoAnimal animal,
            ModalidadCita modalidad,
            DiaSemana dia) {

        boolean domingoOFestivo =
                dia == DiaSemana.DOMINGO ||
                dia == DiaSemana.FESTIVO;

        if (animal == TipoAnimal.CANINO ||
            animal == TipoAnimal.FELINO) {

            if (modalidad == ModalidadCita.PROGRAMADA) {
                return domingoOFestivo ? 110000 : 94000;
            }

            if (modalidad == ModalidadCita.NO_PROGRAMADA) {
                return domingoOFestivo ? 135000 : 116000;
            }

            if (modalidad == ModalidadCita.URGENCIA) {
                return domingoOFestivo ? 160000 : 142000;
            }
        }

        if (animal == TipoAnimal.AVIAR) {

            if (modalidad == ModalidadCita.PROGRAMADA) {
                return domingoOFestivo ? 130000 : 110000;
            }

            if (modalidad == ModalidadCita.NO_PROGRAMADA) {
                return domingoOFestivo ? 140000 : 132000;
            }

            if (modalidad == ModalidadCita.URGENCIA) {
                return domingoOFestivo ? 165000 : 145000;
            }
        }

        throw new ServicioNoDisponibleException(
                "No existe una tarifa para esta mascota");
    }

    // 2. BAÑO, PELUQUERÍA, UÑAS Y COLMILLOS
    public static int calcularBano(
            TipoAnimal animal, double peso, boolean premium) {

        if (animal == TipoAnimal.CANINO) {

            if (peso < 10) return 85000;
            if (peso >= 10 && peso <= 25) return 95000;
            if (peso > 25) return 105000;
        }

        if (animal == TipoAnimal.FELINO) {
            return 115000;
        }

        if (animal == TipoAnimal.AVIAR) {
            return premium ? 96000 : 77000;
        }

        throw new ServicioNoDisponibleException(
                "No hay tarifa de baño para esta mascota");
    }

    // 3. ESTERILIZACIÓN
    public static int calcularEsterilizacion(
            TipoAnimal animal, SexoAnimal sexo, double peso) {

        if (animal == TipoAnimal.FELINO) {
            return sexo == SexoAnimal.MACHO ? 180000 : 220000;
        }

        if (animal == TipoAnimal.CANINO) {

            if (peso < 10) return 185000;
            if (peso >= 10 && peso <= 25) return 210000;
            if (peso > 25) return 235000;
        }

        throw new ServicioNoDisponibleException(
                "La esterilización no está disponible para esta mascota");
    }

    // 4. MANTENIMIENTO DE PICO Y UÑAS
    public static int calcularMantenimientoAves(
            TipoAnimal animal, boolean sedacion) {

        if (animal != TipoAnimal.AVIAR) {
            throw new ServicioNoDisponibleException(
                    "Este servicio es exclusivo para aves");
        }

        return sedacion ? 75000 : 40000;
    }

    // 5. DESPARASITACIÓN
    public static int calcularDesparasitacion(
            TipoAnimal animal, double peso) {

        if (animal == TipoAnimal.FELINO) {
            return 80000;
        }

        if (animal == TipoAnimal.CANINO) {

            if (peso < 10) return 80000;
            if (peso >= 10 && peso <= 25) return 100000;
            if (peso > 25) return 120000;
        }

        if (animal == TipoAnimal.AVIAR) {
            return 37000;
        }

        throw new ServicioNoDisponibleException(
                "No existe tarifa de desparasitación");
    }

    // 6. VACUNACIÓN
    public static int calcularVacuna(
            TipoAnimal animal, TipoVacuna vacuna) {

        if (animal == TipoAnimal.AVIAR) {
            throw new ServicioNoDisponibleException(
                    "No hay vacunas para aves de compañía");
        }

        switch (vacuna) {

            case ANTIRRABICA:
                return 35000;

            case POLIVALENTE_CANINA:
                if (animal == TipoAnimal.CANINO) {
                    return 60000;
                }
                break;

            case MULTIPLE_FELINA:
                if (animal == TipoAnimal.FELINO) {
                    return 130000;
                }
                break;
        }

        throw new ServicioNoDisponibleException(
                "La vacuna seleccionada no corresponde a la mascota");
    }

    // 7. ESPECIALISTAS
    public static int calcularEspecialista() {
        return ESPECIALISTAS;
    }
}
