package Veterinaria.gestion;

import Veterinaria.facturacion.Factura;

public class GestorFacturacion {

    public static void generarFactura(Factura factura) {

        if (factura == null) {

            System.out.println(
                "No hay una factura disponible."
            );

            return;
        }

        factura.mostrarFactura();
    }
}