package problema4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Empleado {
    protected String nombre;
    protected int id;

    public Empleado(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    public abstract double calcularSalario();

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("ID: " + id);
    }

    public void actualizarInformacion(String nombre) {
        this.nombre = nombre;
    }
}

class EmpleadoAsalariado extends Empleado {
    private double salario_base;
    private double comisiones;
    private double bonos;

    public EmpleadoAsalariado(String nombre, int id, double salario_base, double comisiones, double bonos) {
        super(nombre, id);
        this.salario_base = salario_base;
        this.comisiones = comisiones;
        this.bonos = bonos;
    }

    @Override
    public double calcularSalario() {
        return salario_base + comisiones + bonos;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Salario Base: " + salario_base);
        System.out.println("Comisiones: " + comisiones);
        System.out.println("Bonos: " + bonos);
    }

    public void actualizarInformacion(String nombre, double salario_base, double comisiones, double bonos) {
        super.actualizarInformacion(nombre);
        this.salario_base = salario_base;
        this.comisiones = comisiones;
        this.bonos = bonos;
    }
}

class EmpleadoPorHoras extends Empleado {
    private int horasTrabajadas;
    private double tarifaPorHora;

    public EmpleadoPorHoras(String nombre, int id, int horasTrabajadas, double tarifaPorHora) {
        super(nombre, id);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }

    @Override
    public double calcularSalario() {
        return horasTrabajadas * tarifaPorHora;
    }

    @Override
    public void mostrarInformacion() {
        super.mostrarInformacion();
        System.out.println("Horas Trabajadas: " + horasTrabajadas);
        System.out.println("Tarifa por Hora: " + tarifaPorHora);
    }

    public void actualizarInformacion(String nombre, int horasTrabajadas, double tarifaPorHora) {
        super.actualizarInformacion(nombre);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }
}

public class problema4 {
    private static List<Empleado> empleados = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Agregar empleado");
            System.out.println("2. Eliminar empleado");
            System.out.println("3. Mostrar información de empleados");
            System.out.println("4. Actualizar información de empleado");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    agregarEmpleado(scanner);
                    break;
                case 2:
                    eliminarEmpleado(scanner);
                    break;
                case 3:
                    mostrarInformacionEmpleados();
                    break;
                case 4:
                    actualizarEmpleado(scanner);
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

    private static void agregarEmpleado(Scanner scanner) {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el ID del empleado: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 

        
        if (buscarEmpleadoPorId(id) != null) {
            System.out.println("Error: El ID del empleado ya existe. Intente con un ID diferente.");
            return;
        }

        System.out.println("Tipo de empleado:");
        System.out.println("1. Empleado Asalariado");
        System.out.println("2. Empleado por Horas");
        System.out.print("Seleccione el tipo de empleado: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); 

        if (tipo == 1) {
            System.out.print("Ingrese el salario base del empleado: ");
            double salario_base = scanner.nextDouble();
            System.out.print("Ingrese las comisiones del empleado: ");
            double comisiones = scanner.nextDouble();
            System.out.print("Ingrese los bonos del empleado: ");
            double bonos = scanner.nextDouble();
            scanner.nextLine(); 
            empleados.add(new EmpleadoAsalariado(nombre, id, salario_base, comisiones, bonos));
        } else if (tipo == 2) {
            System.out.print("Ingrese las horas trabajadas: ");
            int horasTrabajadas = scanner.nextInt();
            System.out.print("Ingrese la tarifa por hora: ");
            double tarifaPorHora = scanner.nextDouble();
            scanner.nextLine(); 
            empleados.add(new EmpleadoPorHoras(nombre, id, horasTrabajadas, tarifaPorHora));
        } else {
            System.out.println("Tipo de empleado no válido.");
        }
    }

    private static void eliminarEmpleado(Scanner scanner) {
        System.out.print("Ingrese el ID del empleado a eliminar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        Empleado empleadoAEliminar = buscarEmpleadoPorId(id);

        if (empleadoAEliminar != null) {
            empleados.remove(empleadoAEliminar);
            System.out.println("Empleado eliminado exitosamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static void mostrarInformacionEmpleados() {
        for (Empleado empleado : empleados) {
            empleado.mostrarInformacion();
            System.out.println("Salario Calculado: " + empleado.calcularSalario());
            System.out.println();
        }
    }

    private static void actualizarEmpleado(Scanner scanner) {
        System.out.print("Ingrese el ID del empleado a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        Empleado empleado = buscarEmpleadoPorId(id);

        if (empleado != null) {
            System.out.print("Ingrese el nuevo nombre del empleado: ");
            String nombre = scanner.nextLine();

            if (empleado instanceof EmpleadoPorHoras) {
                System.out.print("Ingrese las nuevas horas trabajadas: ");
                int horasTrabajadas = scanner.nextInt();
                System.out.print("Ingrese la nueva tarifa por hora: ");
                double tarifaPorHora = scanner.nextDouble();
                scanner.nextLine(); 
                ((EmpleadoPorHoras) empleado).actualizarInformacion(nombre, horasTrabajadas, tarifaPorHora);
            } else if (empleado instanceof EmpleadoAsalariado) {
                System.out.print("Ingrese el nuevo salario base del empleado: ");
                double salario_base = scanner.nextDouble();
                System.out.print("Ingrese las nuevas comisiones del empleado: ");
                double comisiones = scanner.nextDouble();
                System.out.print("Ingrese los nuevos bonos del empleado: ");
                double bonos = scanner.nextDouble();
                scanner.nextLine(); 
                ((EmpleadoAsalariado) empleado).actualizarInformacion(nombre, salario_base, comisiones, bonos);
            }

            System.out.println("Información del empleado actualizada exitosamente.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    private static Empleado buscarEmpleadoPorId(int id) {
        for (Empleado empleado : empleados) {
            if (empleado.id == id) {
                return empleado;
            }
        }
        return null;
    }
}