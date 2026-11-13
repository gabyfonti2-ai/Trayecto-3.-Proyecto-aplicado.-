package Veterinaria.facturacion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Veterinaria.paciente.Mascota;
import Veterinaria.servicios.CatalogoServicio;

public class Factura implements Facturable {

    // Atributos
    private String numeroFactura;
    private LocalDate fecha;
    private Mascota mascota;
    private List<CatalogoServicio> servicios;

    // Constructor
    public Factura(String numeroFactura, Mascota mascota) {
        this.numeroFactura = numeroFactura;
        this.fecha = LocalDate.now();
        this.mascota = mascota;
        this.servicios = new ArrayList<>();
    }

    // Agregar servicios a la factura
    public void agregarServicio(CatalogoServicio servicio) {
        servicios.add(servicio);
    }

    // Calcular subtotal
    @Override
    public int calcularSubtotal() {
        int subtotal = 0;

        for (CatalogoServicio servicio : servicios) {
            subtotal += servicio.getPrecio();
        }
        return subtotal;
    }

    // Calcular descuento por contratar más de un servicio
    @Override
    public int calcularDescuento() {

        // El descuento se aplica únicamente con más de un servicio
        if (servicios.size() > 1) {
            int subtotal = calcularSubtotal();
            // Polimorfismo: Cada tipo de mascota aplica su porcentaje
            double subtotalConDescuento = mascota.aplicarAjuste(subtotal);
            return (int) Math.round(subtotal - subtotalConDescuento);
        }
        return 0;
    }

    @Override
    public int calcularTotal() {

        int subtotal = calcularSubtotal();
        int descuento = calcularDescuento();

        return Math.max(0, subtotal - descuento);
    }
    

    // Mostrar factura
    public void mostrarFactura() {

        System.out.println("\n========== MASCOTAS SANAS ==========");
        System.out.println("FACTURA No: " + numeroFactura);
        System.out.println("FECHA: " + fecha);
        System.out.println("PACIENTE: " + mascota.getNombre());
        System.out.println("TIPO: " + mascota.getTipoAnimal());
        System.out.println("DUEÑO: " + mascota.getDueno());

        System.out.println("\nSERVICIOS CONTRATADOS:");

        for (CatalogoServicio servicio : servicios) {
            System.out.println(
                servicio.getDescripcion()
                + " - $"
                + servicio.getDetalleTarifa()
            );
        }

        System.out.println("------------------------------------");
        System.out.println("SUBTOTAL: $" + calcularSubtotal());
        System.out.println("DESCUENTO: $" + calcularDescuento());
        System.out.println("TOTAL A PAGAR: $" + calcularTotal());
        System.out.println("====================================");

        System.out.println("COMPRA REGISTRADA CON EXITO");
        System.out.println("GRACIAS POR ELEGIRNOS");
        System.out.println(
            "\nNOTA: Puede continuar en el sistema o seleccionar 11 para salir"
        );
    }

    // Getters
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public List<CatalogoServicio> getServicios() {
        return new ArrayList<>(servicios);
    }
}