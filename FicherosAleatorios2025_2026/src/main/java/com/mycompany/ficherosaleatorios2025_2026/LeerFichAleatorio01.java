/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ficherosaleatorios2025_2026;

/**
 *
 * @author Wilfredo2025
 * Lee un fichero binario sencillo en forma secuencial, aunque no es la idea principal de usar RandomAccessFile, ayudará acomprender el uso de los metodos de RamdomAccess().
 * 
Estructura del Archivo:
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

public class LeerFichAleatorio01 {
    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("ejemplo_fichero.bin", "r")) {
            // Leer y mostrar todos los registros
            mostrarRegistros(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para leer y mostrar los registros del archivo
    public static void mostrarRegistros(RandomAccessFile file) throws IOException {
        //file.seek(0);  // Posicionarse al inicio del archivo ( no es necesario en este caso)
        // se recorre de forma secuencial
        while (file.getFilePointer() < file.length()) { //EOF()
            int id = file.readInt();         // Leer ID (4 bytes)
            short edad = file.readShort();   // Leer Edad (2 bytes)
            float salario = file.readFloat();// Leer Salario (4 bytes)

            // Mostrar el registro
            System.out.println("ID: " + id + ", Edad: " + edad + ", Salario: " + salario);
        }
    }
}
