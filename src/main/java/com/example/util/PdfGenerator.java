package com.example.util;

import java.io.FileNotFoundException;

import com.example.Usuario;
import com.example.Curso;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class PdfGenerator {

    // =========================
    // PDF USUARIO
    // =========================
    public static void generarPdfUsuario(Usuario usuario) {

        try {

            String ruta =
                    "usuario_" + usuario.getIdUsuario() + ".pdf";

            PdfWriter writer = new PdfWriter(ruta);

            PdfDocument pdf =
                    new PdfDocument(writer);

            Document document =
                    new Document(pdf);

            document.add(
                    new Paragraph("===== USUARIO ====="));

            document.add(
                    new Paragraph("Nombre: "
                            + usuario.getNombre()));

            document.add(
                    new Paragraph("Email: "
                            + usuario.getEmail()));

            document.add(
                    new Paragraph("Rol: "
                            + usuario.getRol().getNombre()));

            document.add(
                    new Paragraph("Activo: "
                            + usuario.isActivo()));

            document.close();

            System.out.println(
                    "PDF usuario generado.");

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }

    // =========================
    // PDF CURSO
    // =========================
    public static void generarPdfCurso(Curso curso) {

        try {

            String ruta =
                    "curso_" + curso.getIdCurso() + ".pdf";

            PdfWriter writer =
                    new PdfWriter(ruta);

            PdfDocument pdf =
                    new PdfDocument(writer);

            Document document =
                    new Document(pdf);

            document.add(
                    new Paragraph("===== CURSO ====="));

            document.add(
                    new Paragraph("Titulo: "
                            + curso.getTitulo()));

            document.add(
                    new Paragraph("Descripcion: "
                            + curso.getDescripcion()));

            document.add(
                    new Paragraph("Instructor: "
                            + curso.getInstructor().getNombre()));

            document.add(
                    new Paragraph("Categoria: "
                            + curso.getCategoria().getNombre()));

            document.add(
                    new Paragraph("Valor: "
                            + curso.getValor()));

            document.close();

            System.out.println(
                    "PDF curso generado.");

        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
    }
}