package clases;

import lombok.Getter;

@Getter
public class Perecederos extends Almacen {

    private int diasACaducar;
    private Double porcentaje;

    public Perecederos(String nombre, Double precio, int diasACaducar) {
        super(nombre, precio);
        this.diasACaducar = diasACaducar;
    }

    @Override
    public void calcularPrecio() {
        porcentaje = switch (diasACaducar){
            case 1 -> 0.6d;
            case 2 ->0.4d;
            case 3 ->0.2d;
            default -> 0d;
        };
        precioFinal = cantidad * (precio -(precio * porcentaje));
    }
}
