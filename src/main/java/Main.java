import clases.Almacen;
import clases.NoPerecederos;
import clases.Perecederos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Almacen> almacen = new ArrayList<>();
        ArrayList<Almacen> carrito = new ArrayList<>();
        String continuar;

        do {
            try {
                System.out.println("\n=== Menú Principal ===");
                System.out.println("1: Crear un nuevo producto");
                System.out.println("2: Comprar productos");
                System.out.print("Seleccione una opción: ");
                int num = scanner.nextInt();
                scanner.nextLine();

                switch (num) {
                    case 1 -> crearProducto(scanner, almacen);
                    case 2 -> comprarProductos(scanner, almacen, carrito);
                    default -> System.out.println("Opción inválida, por favor intente de nuevo.");
                }

                System.out.print("\n¿Desea continuar en el menú principal? (s/n): ");
                continuar = scanner.nextLine();

            } catch (InputMismatchException ime) {
                System.out.println("Error: Entrada inválida. Por favor, introduzca un número.");
                scanner.nextLine();
                continuar = "s";
            } catch (Exception e) {
                System.out.println("Se ha producido un error inesperado: " + e.getMessage());
                continuar = "n";
            }

        } while (continuar.equalsIgnoreCase("s"));

        System.out.println("¡Gracias por usar el sistema! Hasta luego.");
    }

    private static void crearProducto(Scanner scanner, ArrayList<Almacen> almacen) {
        try {
            System.out.print("\nIngrese el nombre del producto: ");
            String nombre = scanner.nextLine();

            System.out.print("Ingrese el precio del producto: ");
            Double precio = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("Seleccione el tipo de producto:");
            System.out.println("1: Producto NO PERECEDERO");
            System.out.println("2: Producto PERECEDERO");
            System.out.print("Opción: ");
            int tipo = scanner.nextInt();
            scanner.nextLine();

            if (tipo == 1) {
                Almacen noPerecedero = new NoPerecederos(nombre, precio);
                almacen.add(noPerecedero);
                System.out.println("Producto no perecedero creado con éxito.");
            } else if (tipo == 2) {
                System.out.print("Indique los días a caducar del producto: ");
                int diasCaducidad = scanner.nextInt();
                scanner.nextLine();
                Almacen perecedero = new Perecederos(nombre, precio, diasCaducidad);
                almacen.add(perecedero);
                System.out.println("Producto perecedero creado con éxito.");
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (InputMismatchException ime) {
            System.out.println("Error: Entrada inválida. Por favor, introduzca un número válido.");
            scanner.nextLine();
        }
    }

    private static void comprarProductos(Scanner scanner, ArrayList<Almacen> almacen, ArrayList<Almacen> carrito) {
        Double precioCompra = 0d;
        String continuarCompra;

        System.out.println("\n=== Productos Disponibles ===");
        for (Almacen producto : almacen) {
            System.out.println(producto.toString());
        }

        do {
            try {
                System.out.print("\nIngrese el código del producto que desea comprar: ");
                int codigoProducto = scanner.nextInt();
                scanner.nextLine();

                System.out.print("¿Cuántos desea comprar? ");
                int cantidad = scanner.nextInt();
                scanner.nextLine();

                boolean productoExistente = false;

                for (Almacen prod : carrito) {
                    if (prod.getCodigo() == codigoProducto) {
                        prod.setCantidad(prod.getCantidad() + cantidad);
                        productoExistente = true;
                        break;
                    }
                }

                if (!productoExistente) {
                    for (Almacen producto : almacen) {
                        if (producto.getCodigo() == codigoProducto) {
                            if (cantidad > 0) {
                                producto.setCantidad(cantidad);
                                carrito.add(producto);
                                System.out.println("Producto agregado al carrito.");
                            }
                            break;
                        }
                    }
                }

                System.out.print("¿Desea continuar comprando? (s/n): ");
                continuarCompra = scanner.nextLine();

            } catch (InputMismatchException ime) {
                System.out.println("Error: Entrada inválida. Intente nuevamente.");
                scanner.nextLine();
                continuarCompra = "s";
            }
        } while (continuarCompra.equalsIgnoreCase("s"));

        System.out.println("\n=== Su Carrito ===");
        for (Almacen producto : carrito) {
            producto.calcularPrecio();
            System.out.println(producto.detallesConsola());
            precioCompra += producto.getPrecioFinal();
        }

        System.out.printf("El total a pagar por su compra es de: $%.2f\n", precioCompra);
    }
}
