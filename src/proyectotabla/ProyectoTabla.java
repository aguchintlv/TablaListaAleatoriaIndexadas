/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectotabla;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Aguchintlv
 */
public class ProyectoTabla {

    private static EIOPD eiopd;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ejecutar();
    }

    public static void ejecutar() {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {
            System.out.println("----------------------------------------------------------------");
            System.out.println("1. Ingresar Registros a la Estructura de Indices Ordenados, Primarios, Distersos");
            System.out.println("2. Cargar y Generar la Estructura de Indices Ordenados, Primarios, Distersos");
            System.out.println("3. Guardar la Estructura de Indices Ordenados, Primarios, Dispersos");
            System.out.println("4. Colsultar registros a partir de un indice");
            System.out.println("5. Eliminar registros a partir de un indice");
            System.out.println("6. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        ingresarRegistro(sn);
                        break;
                    case 2:
                        cargarRegistros();
                        break;
                    case 3:
                        guardarRegistros();
                        break;
                    case 4:
                        buscarIndice(sn);
                        break;
                    case 5:
                        eliminarRegistro(sn);
                        break;
                    case 6:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número.");
                sn.next();
            }
        }
    }
    
    public static void ingresarRegistro(Scanner sn) {
        if (eiopd == null) {
            System.out.print("Ingrese la cantidad de columnas de la Estructura Secuencial Indexada:");
            int max = sn.nextInt();
            eiopd = new EIOPD(max);
        }
        try {
            Object[] o = new Object[eiopd.getESIndexada().getColumnCount()-1];
            for (int i = 0; i < o.length; i++) {
                System.out.println("Ingrese columna "+i+":");
                if (i == 0) {
                    o[i] = sn.nextInt();
                } else {
                    o[i] = sn.next();
                }
            }
            eiopd.insert(o);
            eiopd.generarEnlaces();
            System.out.println(eiopd);
            System.out.println("Se ingreso el registro en las estructuras");            
        } catch(NumberFormatException ex) {
            System.out.println("La primera columna debe ser un valor numerico");
        }
    }

    public static void cargarRegistros() {
        File registros = new File("registros.txt");
        if (registros.exists()) {
            try {
                BufferedReader br;
                String linea;
                br = new BufferedReader(new FileReader(registros));
                int i = 0;
                while ((linea = br.readLine()) != null) {
                    if (i == 0) {
                        eiopd = new EIOPD(linea.split(",").length);
                    }
                    Object[] reg = linea.split(",");
                    eiopd.insert(reg);
                    i++;
                }
                br.close();
                eiopd.generarEnlaces();
                System.out.println("Se Generaron las Estructuras.");
                System.out.println(eiopd);
            } catch (IOException ex) {
                System.out.println("No se pudo cargar los registros del archivo.");
            }
        } else {
            try {
                registros.createNewFile();
                cargarRegistros();
            } catch (IOException ex) {
                System.out.println("No se pudo crear el archivo de registros.");
            }
        }
    }

    public static void guardarRegistros() {
        File registros = new File("registros.txt");
        if (registros.exists()) {
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(registros));
                for (int i = 0; i < eiopd.getESIndexada().getRowCount(); i++) {
                    String linea = "";
                    for (int j = 0; j < eiopd.getESIndexada().getColumnCount() - 1; j++) {
                        if (j == 0) {
                            linea += "" + eiopd.getESIndexada().getValueAt(i, j);
                        } else {
                            linea += "," + eiopd.getESIndexada().getValueAt(i, j);                        
                        }
                    }
                    pw.println(linea);
                }
                pw.close();
                System.out.println(eiopd);
                System.out.println("Se guardaron los registros en el archivo");
            } catch (IOException ex) {
                System.out.println("No se pudo guardar los registros en el archivo");
            }            
        } else {
            try {
                registros.createNewFile();
                guardarRegistros();
            } catch (IOException ex) {
                System.out.println("No se pudo crear el archivo de registros.");
            }
        }
    }

    public static void buscarIndice(Scanner sn) {
        if (!eiopd.isEmpty()) {
            System.out.print("Ingrese un indice:");
            Tabla temp = eiopd.buscar(sn.nextInt());
            if (!temp.isEmpty()) {
                System.out.println(temp);
            } else {
                System.out.println("No se encontraron coincidencias.");
            }
        } else {
            System.out.println("La estructura de Secuencial Indexada esta vacia.");
        }
    }

    public static void eliminarRegistro(Scanner sn) {
        if (!eiopd.isEmpty()) {
            System.out.print("Ingrese un indice:");
            boolean exito = eiopd.remove(sn.nextInt());
            eiopd.generarEnlaces();
            if (exito) {
                System.out.println(eiopd);
                System.out.println("Los egistros fueron eliminados.");
            } else {
                System.out.println("No se encontraron coincidencias.");
            }
        } else {
            System.out.println("La estructura de Secuencial Indexada esta vacia.");
        }
    }
}
