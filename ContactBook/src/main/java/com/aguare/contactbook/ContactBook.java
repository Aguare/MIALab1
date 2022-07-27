package com.aguare.contactbook;

import java.util.Scanner;

/**
 *
 * @author marco
 */
public class ContactBook {

    private static Archive save = new Archive();

    public static void main(String[] args) {
        try {
            menu();
        } catch (Exception e) {
            System.out.println("Por favor ingrese valores válidos");
        }

    }

    public static void menu() throws Exception {
        Scanner in = new Scanner(System.in);
        String op = "";
        do {
            System.out.println("--------MENÚ--------");
            System.out.println("1. Crear Registro");
            System.out.println("2. Ver Registros");
            System.out.println("0. Salir");
            System.out.println("--------------------");
            System.out.print("Ingrese su opción: ");
            op = in.nextLine();
            switch (op) {
                case "1" -> {
                    createContact();
                }
                case "2" -> {
                    System.out.println("------------------------------------------");
                    System.out.println("Nombre \t Tel \t Email \t Facebook");
                    for (Contact o : save.readFile()) {
                        System.out.println(o.toString());
                    }
                    System.out.println("------------------------------------------");
                }
                default ->
                    System.out.println("Opción inválida");
            }
        } while (!op.equals("0"));
    }

    public static void createContact() {
        Contact cont = new Contact(readInput(" el Nombre"), readInput(" el Teléfono"),
                readInput(" el Correo"), readInput(" el Facebook"));
        if (save.saveList(cont)) {
            System.out.println("Registro realizado!");
        }
    }

    public static String readInput(String option) {
        Scanner in = new Scanner(System.in);
        String input = "";
        do {
            System.out.print("Ingrese " + option + ": ");
            input = in.nextLine();
            if (input.length() == 0) {
                System.out.println("Por favor llene el campo");
            }
        } while (input.length() == 0);
        return input;
    }
}
