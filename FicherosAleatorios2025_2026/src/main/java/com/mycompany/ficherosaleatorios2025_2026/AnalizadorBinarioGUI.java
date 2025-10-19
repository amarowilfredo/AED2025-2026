/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ficherosaleatorios2025_2026;

/**
 *
 * @author Wilfredo2025
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class AnalizadorBinarioGUI extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JLabel infoArchivo;

    public AnalizadorBinarioGUI() {
        setTitle("Analizador Binario — Estructura de ficheros");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JButton botonAbrir = new JButton("📂 Abrir fichero binario");
        botonAbrir.addActionListener(e -> seleccionarArchivo());

        infoArchivo = new JLabel("Seleccione un archivo para analizar", SwingConstants.CENTER);

        modelo = new DefaultTableModel(new Object[]{"Offset", "Bytes (hex)", "Int", "Short", "Float", "Texto"}, 0);
        tabla = new JTable(modelo);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scroll = new JScrollPane(tabla);

        add(botonAbrir, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(infoArchivo, BorderLayout.SOUTH);
    }

    private void seleccionarArchivo() {
        JFileChooser fc = new JFileChooser();
        int sel = fc.showOpenDialog(this);
        if (sel == JFileChooser.APPROVE_OPTION) {
            File archivo = fc.getSelectedFile();
            analizarArchivo(archivo);
        }
    }

    private void analizarArchivo(File archivo) {
        try (FileInputStream fis = new FileInputStream(archivo)) {
            byte[] datos = fis.readAllBytes();
            modelo.setRowCount(0);
            infoArchivo.setText(String.format("Archivo: %s — Tamaño: %d bytes", archivo.getName(), datos.length));

            for (int i = 0; i < datos.length; i += 10) {
                int fin = Math.min(i + 10, datos.length);
                byte[] bloque = Arrays.copyOfRange(datos, i, fin);

                int intVal = leerInt(bloque, 0);
                short shortVal = leerShort(bloque, 4);
                float floatVal = leerFloat(bloque, 6);
                String texto = convertirTexto(bloque);

                modelo.addRow(new Object[]{i, bytesHex(bloque), intVal, shortVal, floatVal, texto});
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static int leerInt(byte[] b, int off) {
        if (b.length < off + 4) return 0;
        return ((b[off] & 0xFF) << 24) | ((b[off + 1] & 0xFF) << 16)
                | ((b[off + 2] & 0xFF) << 8) | (b[off + 3] & 0xFF);
    }

    private static short leerShort(byte[] b, int off) {
        if (b.length < off + 2) return 0;
        return (short) (((b[off] & 0xFF) << 8) | (b[off + 1] & 0xFF));
    }

    private static float leerFloat(byte[] b, int off) {
        if (b.length < off + 4) return 0;
        int bits = ((b[off] & 0xFF) << 24) | ((b[off + 1] & 0xFF) << 16)
                | ((b[off + 2] & 0xFF) << 8) | (b[off + 3] & 0xFF);
        return Float.intBitsToFloat(bits);
    }

    private static String convertirTexto(byte[] b) {
        String s = new String(b, StandardCharsets.ISO_8859_1).trim();
        return s.replaceAll("[^\\p{Print}]", ".");
    }

    private static String bytesHex(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (byte value : b) sb.append(String.format("%02X ", value));
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnalizadorBinarioGUI().setVisible(true));
    }
}
