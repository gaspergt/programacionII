package problema1;

import java.util.Scanner;

public class problema1 {
    public static void main(String args []) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre de la primera persona: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Ingrese el año de nacimiento de la primera persona: ");
        int anioNacimiento1 = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Ingrese el nombre de la segunda persona: ");
        String nombre2 = scanner.nextLine();
        System.out.print("Ingrese el año de nacimiento de la segunda persona: ");
        int anioNacimiento2 = scanner.nextInt();
        
        int diferenciaEdad = Math.abs(anioNacimiento1 - anioNacimiento2);
        
        if (anioNacimiento1 < anioNacimiento2) {
            System.out.println(nombre1 + " es mayor que " + nombre2 + " por " + diferenciaEdad + " años.");
        } else if (anioNacimiento1 > anioNacimiento2) {
            System.out.println(nombre2 + " es mayor que " + nombre1 + " por " + diferenciaEdad + " años.");
        } else {
            System.out.println(nombre1 + " y " + nombre2 + " tienen la misma edad.");
        }

        scanner.close();
    }
}