/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ficherosaleatorios2025_2026;

/**
 *
 * @author Wilfredo2025
 * 
 * Estructura del Archivo:
Cada registro tiene los siguientes tres campos:

ID (int): 4 bytes
Edad (short): 2 bytes
Salario (float): 4 bytes
Tamaño total por registro: 4 + 2 + 4 = 10 bytes

Datos en el Archivo:
Registro 1: ID = 1, Edad = 25, Salario = 1500.0
Registro 2: ID = 2, Edad = 30, Salario = 2500.0
Registro 3: ID = 3, Edad = 35, Salario = 3500.0
 */
import java.io.RandomAccessFile;
import java.io.IOException;

public class LeerFichAleatorio03 {
    private static final int TAMANO_REGISTRO = 10; // Tamaño de cada registro en bytes (4 bytes ID + 2 bytes Edad + 4 bytes Salario)

    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("ejemplo_fichero.bin", "r")) {
            // Mostrar el registro específico (por ejemplo, el tercer registro)
            int numeroRegistro = 10; // Número del registro que se quiere leer (por ejemplo, el tercer registro)
            mostrarRegistroEspecifico(file, numeroRegistro);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar un registro específico usando su número de registro
    public static void mostrarRegistroEspecifico(RandomAccessFile file, int numeroRegistro) throws IOException {
        // Calcular la posición del registro específico en bytes
        long posicion = TAMANO_REGISTRO * (numeroRegistro - 1);  // Ejemplo: tercer registro -> 10 * (3 - 1) = 20 bytes

        // Verificar si la posición calculada está dentro del tamaño del archivo
        if (posicion >= file.length() || posicion < 0) {
            System.out.println("El registro " + numeroRegistro + " no existe en el archivo.");
            return;
        }

        // Mover el puntero a la posición del registro deseado
        file.seek(posicion);

        // Leer los campos del registro en la posición especificada
        int id = file.readInt();          // Leer ID (4 bytes)
        short edad = file.readShort();    // Leer Edad (2 bytes)
        float salario = file.readFloat(); // Leer Salario (4 bytes)

        // Mostrar el registro leído
        System.out.println("Registro " + numeroRegistro + ": ID: " + id + ", Edad: " + edad + ", Salario: " + salario);
    }
}