package com.example.util;

import java.io.FileNotFoundException;
import java.util.List;

import com.example.Usuario;
import com.example.UsuarioRol;
import com.example.Categoria;
import com.example.Curso;
import com.example.Inscripcion;
import com.example.Leccion;
import com.example.Modulo;
import com.example.Pago;
import com.example.ProgresoLeccion;
import com.example.Rol;
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

    public static void generarPdfGeneral(
        List<Usuario> usuarios,
        List<Curso> cursos,
        List<Categoria> categorias,
        List<Inscripcion> inscripciones,
        List<Leccion> lecciones,
        List<Modulo> modulos,
        List<Pago> pagos,
        List<Rol> roles,
        List<UsuarioRol> usuariosRoles,
        List<ProgresoLeccion> progresos
) {

    try {

        String ruta = "ReporteGeneral.pdf";

        PdfWriter writer = new PdfWriter(ruta);

        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf);

        // TITULO
        document.add(
                new Paragraph("===== REPORTE GENERAL ====="));

        document.add(new Paragraph(" "));

        // =========================
        // USUARIOS
        // =========================

        document.add(
                new Paragraph("===== USUARIOS ====="));

        for (Usuario u : usuarios) {

            document.add(
                    new Paragraph(
                            "Nombre: " + u.getNombre()
                            + " | Email: " + u.getEmail()
                    ));
        }

        document.add(new Paragraph(" "));

        // =========================
        // CURSOS
        // =========================

        document.add(
                new Paragraph("===== CURSOS ====="));

        for (Curso c : cursos) {

            document.add(
                    new Paragraph(
                            "Titulo: " + c.getTitulo()
                            + " | Valor: " + c.getValor()
                    ));
        }

        document.add(new Paragraph(" "));

        // =========================
        // CATEGORIAS
        // =========================

        document.add(
                new Paragraph("===== CATEGORIAS ====="));

        for (Categoria cat : categorias) {

            document.add(
                    new Paragraph(
                            "Nombre: " + cat.getNombre()
                    ));
        }

        document.add(new Paragraph(" "));

        // =========================
        // PAGOS
        // =========================

        document.add(
                new Paragraph("===== PAGOS ====="));

        for (Pago p : pagos) {

            document.add(
                    new Paragraph(
                            "Usuario: "
                            + p.getUsuario().getNombre()
                            + " | Valor: "
                            + p.getValor()
                    ));
        }

        document.close();

        System.out.println(
                "PDF GENERAL GENERADO.");

    } catch (Exception e) {

        e.printStackTrace();
    }
}
}