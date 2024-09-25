package clases;

import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class Almacen {

    protected String nombre;
    protected static int codigoAuto;
    protected int codigo;
    protected Double precio;
    @Setter
    protected int cantidad;
    protected Double precioFinal;

    public Almacen(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.codigo = ++codigoAuto;
    }

    public void calcularPrecio() {
        precioFinal = precio * cantidad;
    }

    @Override
    public String toString() {
        return "Información del producto en el almacén:\n" +
                "Nombre del producto: " + nombre + "\n" +
                "Código del producto: " + codigo + "\n" +
                "Precio unitario: $" + String.format("%.2f", precio) + "\n";
    }

    public String detallesConsola() {
        return "=== Detalles completos del producto ===\n" +
                "Nombre del producto: " + nombre + "\n" +
                "Código del producto: " + codigo + "\n" +
                "Precio unitario: $" + String.format("%.2f", precio) + "\n" +
                "Cantidad en inventario: " + cantidad + " unidades\n" +
                "Precio total (cantidad x precio unitario): $" + String.format("%.2f", precioFinal) + "\n" +
                "=======================================";
    }

}
