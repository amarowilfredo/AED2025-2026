/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ficherosaleatorios2025_2026;

/**
 *
 * @author Wilfredo2025
 */
import java.io.IOException;
import java.io.RandomAccessFile;

public class CrearFichAleatorio03 {

    private static final int TAMANO_REGISTRO = 10; // 4 + 2 + 4 bytes

    public static void main(String[] args) {
        try (RandomAccessFile file = new RandomAccessFile("ejemplo_fichero.bin", "rw")) {

            // Limpiar el archivo anterior si ya existía
            file.setLength(0);

            // Datos de ejemplo: ID, Edad, Salario
            int[] ids = {1, 2, 3, 4, 5,6,7,8,9,10};
            short[] edades = {25, 30, 35, 40, 45,33,45,67,88,25};
            float[] salarios = {1500.0f, 2500.0f, 3500.0f, 4500.0f, 5500.0f,5500.0f,5400.0f,5600.0f,2200.0f,2500.0f};

            for (int i = 0; i < ids.length; i++) {
                escribirRegistro(file, i, ids[i], edades[i], salarios[i]);
            }

            System.out.println("Archivo 'ejemplo_fichero.bin' generado correctamente.");
            System.out.println("Registros creados: " + ids.length);
            System.out.println("Tamaño total: " + file.length() + " bytes");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para escribir un registro en una posición determinada
    private static void escribirRegistro(RandomAccessFile file, int n, int id, short edad, float salario) throws IOException {
        long posicion = (long) n * TAMANO_REGISTRO;
        file.seek(posicion);
        file.writeInt(id);      // 4 bytes
        file.writeShort(edad);  // 2 bytes
        file.writeFloat(salario); // 4 bytes
    }
}

