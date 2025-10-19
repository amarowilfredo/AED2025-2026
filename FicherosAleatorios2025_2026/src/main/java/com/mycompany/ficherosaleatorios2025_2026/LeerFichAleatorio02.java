/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ficherosaleatorios2025_2026;

/**
 *
 * @author Wilfredo2025
 */
/**
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
 * @author Wilfredo Amaro 2024-2025
 * Recorre un registro de forma aleatoria usando Seek()
 * 
- Método saltarYLeerRegistro():
    Este método permite leer un registro específico usando el número de registro (numeroRegistro).
    -   Calcula la posición del registro en bytes usando long posicion = TAMANO_REGISTRO * (numeroRegistro - 1);.
    -   Usa file.seek(posicion); para mover el puntero del archivo a esa posición.
    -   Luego, lee el registro normalmente y lo muestra en la consola.

- Método saltarYLeerRegistro():
    - En main(), después de leer todos los registros secuencialmente, se llama a saltarYLeerRegistro(file, 2); para saltar directamente al segundo registro usando acceso aleatorio.
    - Esto demuestra cómo se puede utilizar seek() para leer registros de manera no secuencial.

 */
import java.io.RandomAccessFile;
import java.io.IOException;

public class LeerFichAleatorio02 {
    private static final int TAMANO_REGISTRO = 10; // Tamaño de cada registro en bytes (4 bytes ID + 2 bytes Edad + 4 bytes Salario)

    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("ejemplo_fichero.bin", "r")) {
            // Leer y mostrar todos los registros secuencialmente
            System.out.println("Leyendo todos los registros secuencialmente:");
            mostrarRegistros(file);

            // Leer el segundo registro usando acceso aleatorio
            System.out.println("\nLeyendo el segundo registro usando acceso aleatorio:");
            saltarYLeerRegistro(file, 2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer y mostrar los registros del archivo secuencialmente
    public static void mostrarRegistros(RandomAccessFile file) throws IOException {
        file.seek(0);  // Posicionarse al inicio del archivo

        while (file.getFilePointer() < file.length()) {
            int id = file.readInt();         // Leer ID (4 bytes)
            short edad = file.readShort();   // Leer Edad (2 bytes)
            float salario = file.readFloat();// Leer Salario (4 bytes)

            // Mostrar el registro
            System.out.println("ID: " + id + ", Edad: " + edad + ", Salario: " + salario);
        }
    }

    // Método para saltar a un registro específico y leerlo usando acceso aleatorio
    public static void saltarYLeerRegistro(RandomAccessFile file, int numeroRegistro) throws IOException {
        // Calcular la posición del registro en bytes y mover el puntero a esa posición
        long posicion = TAMANO_REGISTRO * (numeroRegistro - 1);  // Ejemplo: segundo registro -> 10 * (2 - 1) = 10 bytes
        file.seek(posicion);  // Mover el puntero a la posición del registro deseado

        // Leer y mostrar el registro actual en la posición especificada
        int id = file.readInt();          // Leer ID (4 bytes)
        short edad = file.readShort();    // Leer Edad (2 bytes)
        float salario = file.readFloat(); // Leer Salario (4 bytes)

        // Mostrar el registro leído
        System.out.println("ID: " + id + ", Edad: " + edad + ", Salario: " + salario);
    }
}