package problema2;

import java.util.ArrayList;
import java.util.Scanner;

public class Libro {

    private String titulo;
    private String autor;
    private boolean disponible;

    public Libro(String titulo, String autor) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("El autor no puede ser nulo o vacío.");
        }
        this.titulo = titulo;
        this.autor = autor;
        this.disponible = true;
    }

    public void prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("El libro '" + titulo + "' ha sido prestado.");
        } else {
            throw new IllegalStateException("El libro '" + titulo + "' no está disponible para préstamo.");
        }
    }

    public void devolver() {
        disponible = true;
        System.out.println("El libro '" + titulo + "' ha sido devuelto.");
    }

    public void consultar_disponibilidad() {
        if (disponible) {
            System.out.println("El libro '" + titulo + "' está disponible para préstamo.");
        } else {
            System.out.println("El libro '" + titulo + "' no está disponible para préstamo.");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("El título no puede ser nulo o vacío.");
        }
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("El autor no puede ser nulo o vacío.");
        }
        this.autor = autor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", disponible=" + disponible +
                '}';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Libro> biblioteca = new ArrayList<>();

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Consultar disponibilidad de un libro");
            System.out.println("2. Prestar un libro");
            System.out.println("3. Devolver un libro");
            System.out.println("4. Agregar un nuevo libro");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro para consultar disponibilidad: ");
                    String tituloConsulta = scanner.nextLine();
                    boolean encontrado = false;
                    for (Libro libro : biblioteca) {
                        if (libro.getTitulo().equalsIgnoreCase(tituloConsulta)) {
                            libro.consultar_disponibilidad();
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Libro no encontrado.");
                    }
                    break;
                case 2:
                    System.out.print("Ingrese el título del libro para prestar: ");
                    String tituloPrestar = scanner.nextLine();
                    encontrado = false;
                    for (Libro libro : biblioteca) {
                        if (libro.getTitulo().equalsIgnoreCase(tituloPrestar)) {
                            try {
                                libro.prestar();
                            } catch (IllegalStateException e) {
                                System.out.println(e.getMessage());
                            }
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Libro no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Ingrese el título del libro para devolver: ");
                    String tituloDevolver = scanner.nextLine();
                    encontrado = false;
                    for (Libro libro : biblioteca) {
                        if (libro.getTitulo().equalsIgnoreCase(tituloDevolver)) {
                            libro.devolver();
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        System.out.println("Libro no encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Ingrese el título del nuevo libro: ");
                    String nuevoTitulo = scanner.nextLine();
                    System.out.print("Ingrese el autor del nuevo libro: ");
                    String nuevoAutor = scanner.nextLine();
                    biblioteca.add(new Libro(nuevoTitulo, nuevoAutor));
                    System.out.println("Libro agregado exitosamente.");
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }
}