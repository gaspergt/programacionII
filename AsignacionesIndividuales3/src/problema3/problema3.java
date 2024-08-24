package problema3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Producto {
    protected String nombre;
    protected double precio;
    protected int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public abstract void agregar_cantidad(int cantidad);

    public abstract void vender(int cantidad);

    public void consultar_inventario() {
        System.out.println("Cantidad disponible de " + nombre + ": " + cantidad);
    }
}

class Electronico extends Producto {

    public Electronico(String nombre, double precio, int cantidad) {
        super(nombre, precio, cantidad);
    }

    @Override
    public void agregar_cantidad(int cantidad) {
        if (cantidad > 0) {
            this.cantidad += cantidad;
            System.out.println("Se han agregado " + cantidad + " unidades. Nueva cantidad: " + this.cantidad);
        } else {
            System.out.println("Cantidad a agregar debe ser mayor que cero.");
        }
    }

    @Override
    public void vender(int cantidad) {
        if (cantidad > 0 && cantidad <= this.cantidad) {
            this.cantidad -= cantidad;
            System.out.println("Se han vendido " + cantidad + " unidades. Nueva cantidad: " + this.cantidad);
        } else if (cantidad > this.cantidad) {
            System.out.println("No se puede realizar la operación. Cantidad insuficiente en inventario.");
        } else {
            System.out.println("Cantidad a vender debe ser mayor que cero.");
        }
    }
}

public class problema3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Producto> inventario = new ArrayList<>();

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Consultar inventario");
            System.out.println("2. Agregar cantidad");
            System.out.println("3. Vender cantidad");
            System.out.println("4. Registrar nuevo producto");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    for (Producto producto : inventario) {
                        producto.consultar_inventario();
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombreAgregar = scanner.next();
                    Producto productoAgregar = buscarProducto(inventario, nombreAgregar);
                    if (productoAgregar != null) {
                        System.out.print("Ingrese la cantidad a agregar: ");
                        int cantidadAgregar = scanner.nextInt();
                        productoAgregar.agregar_cantidad(cantidadAgregar);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombreVender = scanner.next();
                    Producto productoVender = buscarProducto(inventario, nombreVender);
                    if (productoVender != null) {
                        System.out.print("Ingrese la cantidad a vender: ");
                        int cantidadVender = scanner.nextInt();
                        productoVender.vender(cantidadVender);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el nombre del nuevo producto: ");
                    String nombreNuevo = scanner.next();
                    System.out.print("Ingrese el precio del nuevo producto: ");
                    double precioNuevo = scanner.nextDouble();
                    System.out.print("Ingrese la cantidad inicial del nuevo producto: ");
                    int cantidadNueva = scanner.nextInt();
                    inventario.add(new Electronico(nombreNuevo, precioNuevo, cantidadNueva));
                    System.out.println("Producto registrado exitosamente.");
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
    }

    private static Producto buscarProducto(List<Producto> inventario, String nombre) {
        for (Producto producto : inventario) {
            if (producto.nombre.equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }
}