package com.example;

import com.example.repository.CategoriaRepository;
import com.example.repository.CursoRepository;
import com.example.repository.InscripcionRepository;
import com.example.repository.LeccionRepository;
import com.example.repository.ModuloRepository;
import com.example.repository.PagoRepository;
import com.example.repository.ProgresoLeccionRepository;
import com.example.repository.RolRepository;
import com.example.repository.UsuarioRepository;
import com.example.repository.UsuarioRolRepository;

import com.example.util.JpaUtil;
import jakarta.persistence.EntityManager;
import com.example.util.PdfGenerator;

import java.util.Scanner;
import java.util.Optional;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static EntityManager em;
    private static CategoriaRepository categoriaRepo;
    private static CursoRepository cursoRepo;
    private static InscripcionRepository inscripcionRepo;
    private static LeccionRepository leccionRepo;
    private static ModuloRepository moduloRepo;
    private static PagoRepository pagoRepo;
    private static ProgresoLeccionRepository progresoLeccionRepo;
    private static RolRepository rolRepo;
    private static UsuarioRepository usuarioRepo;
    private static UsuarioRolRepository usuarioRolRepo;

    public static void main(String[] args) {
        em = JpaUtil.getEntityManager();
        categoriaRepo = new CategoriaRepository(em);
        cursoRepo = new CursoRepository(em);
        inscripcionRepo = new InscripcionRepository(em);
        leccionRepo = new LeccionRepository(em);
        moduloRepo = new ModuloRepository(em);
        pagoRepo = new PagoRepository(em);
        progresoLeccionRepo = new ProgresoLeccionRepository(em);
        rolRepo = new RolRepository(em);
        usuarioRepo = new UsuarioRepository(em);
        usuarioRolRepo = new UsuarioRolRepository(em);

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Gestionar Usuarios");
            System.out.println("2. Gestionar Cursos");
            System.out.println("3. Gestionar Inscripcion");
            System.out.println("4. Gestionar Categoria");
            System.out.println("5. Gestionar Leccion");
            System.out.println("6. Gestionar Modulo");
            System.out.println("7. Gestionar Pago");
            System.out.println("8. Gestionar UsuarioRol");
            System.out.println("9. Gestionar ProgresoLeccion");
            System.out.println("10. Gestionar Rol");

            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1 -> menuUsuario();
                case 2 -> menuCurso();
                case 3 -> menuInscripcion();
                case 4 -> menuCategoria();
                case 5 -> menuLeccion();
                case 6 -> menuModulo();
                case 7 -> menuPago();
                case 8 -> menuUsuarioRol();
                case 9 -> menuProgresoLeccion();
                case 10 -> menuRol();

                case 0 -> exit = true;
                default -> System.out.println("Opción no válida.");
            }
        }
        JpaUtil.close();
        System.out.println("Saliendo...");
    }

    private static void menuUsuario() {
        System.out.println("\n--- Usuarios ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");

        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();

                System.out.print("Email: ");
                String email = scanner.nextLine();

                System.out.print("Contrasena: ");
                String contrasena = scanner.nextLine();

                System.out.println("\nSeleccione un rol:");

                rolRepo.findAll().forEach(rol -> System.out.println(rol.getIdRol() + ". " + rol.getNombre()));

                System.out.print("ID del rol: ");
                Long idRol = scanner.nextLong();
                scanner.nextLine();

                Optional<Rol> rolOptional = rolRepo.findById(idRol);

                if (rolOptional.isPresent()) {

                    Rol rol = rolOptional.get();

                    Usuario usuario = new Usuario(
                            nombre,
                            email,
                            contrasena,
                            rol);

                    usuarioRepo.save(usuario);
                    PdfGenerator.generarPdfUsuario(usuario); // se genera el pdf

                    System.out.println("Usuario guardado correctamente.");

                } else {
                    System.out.println("Rol no encontrado.");
                }
            }

            case 2 -> usuarioRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID del usuario a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();

                Optional<Usuario> usuario = usuarioRepo.findById(id);

                usuario.ifPresentOrElse(u -> {

                    System.out.print("Nuevo nombre: ");
                    u.setNombre(scanner.nextLine());

                    System.out.print("Nuevo email: ");
                    u.setEmail(scanner.nextLine());

                    System.out.print("Nueva password: ");
                    u.setContrasena(scanner.nextLine());

                    // MOSTRAR ROLES
                    System.out.println("\nRoles disponibles:");

                    rolRepo.findAll().forEach(rol -> System.out.println(rol.getIdRol() + ". " + rol.getNombre()));

                    System.out.print("Nuevo ID Rol: ");
                    Long idRol = scanner.nextLong();
                    scanner.nextLine();

                    Optional<Rol> rolOptional = rolRepo.findById(idRol);

                    rolOptional.ifPresent(u::setRol);

                    usuarioRepo.update(u);

                    System.out.println("Usuario actualizado.");

                }, () -> System.out.println("Usuario no encontrado."));
            }

            case 4 -> {

                System.out.print("ID del usuario a eliminar: ");
                long id = scanner.nextLong();

                usuarioRepo.findById(id).ifPresentOrElse(u -> {

                    usuarioRepo.delete(u);

                    System.out.println("Usuario eliminado.");

                }, () -> System.out.println("Usuario no encontrado."));
            }
        }
    }

    private static void menuCurso() {

        System.out.println("\n--- Cursos ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");

        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {

            case 1 -> {

                System.out.print("Titulo: ");
                String titulo = scanner.nextLine();

                System.out.print("Descripcion: ");
                String descripcion = scanner.nextLine();

                System.out.print("URL: ");
                String url = scanner.nextLine();

                System.out.print("Valor: ");
                double valor = scanner.nextDouble();

                System.out.print("¿Publicado? (true/false): ");
                boolean publicado = scanner.nextBoolean();

                System.out.println("\n--- Instructores ---");

                usuarioRepo.findByRol("instructor")
                        .forEach(System.out::println);

                System.out.print("ID Instructor: ");
                long idInstructor = scanner.nextLong();

                System.out.println("\n--- Categorias ---");
                categoriaRepo.findAll().forEach(System.out::println);

                System.out.print("ID Categoria: ");
                long idCategoria = scanner.nextLong();

                Optional<Usuario> instructor = usuarioRepo.findById(idInstructor);

                Optional<Categoria> categoria = categoriaRepo.findById(idCategoria);

                if (instructor.isPresent()
                        && categoria.isPresent()
                        && instructor.get().getRol().getNombre()
                                .equalsIgnoreCase("instructor")) {

                    Curso curso = new Curso(
                            titulo,
                            descripcion,
                            url,
                            valor,
                            publicado,
                            instructor.get(),
                            new java.sql.Date(System.currentTimeMillis()),
                            new java.sql.Date(System.currentTimeMillis()),
                            categoria.get());

                    cursoRepo.save(curso);
                    PdfGenerator.generarPdfCurso(curso); // se llama al pdf

                    System.out.println("Curso guardado.");

                } else {

                    System.out.println("Instructor o categoria no encontrada.");
                }
            }

            case 2 -> cursoRepo.findAll().forEach(System.out::println);

            case 3 -> {

                System.out.print("ID del curso a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();

                Optional<Curso> curso = cursoRepo.findById(id);

                curso.ifPresentOrElse(c -> {

                    System.out.print("Nuevo titulo: ");
                    c.setTitulo(scanner.nextLine());

                    System.out.print("Nueva descripcion: ");
                    c.setDescripcion(scanner.nextLine());

                    System.out.print("Nueva URL: ");
                    c.setUrl(scanner.nextLine());

                    System.out.print("Nuevo valor: ");
                    c.setValor(scanner.nextDouble());

                    System.out.print("¿Publicado? (true/false): ");
                    c.setEstaPublicado(scanner.nextBoolean());

                    c.setFechaActualizacion(
                            new java.sql.Date(System.currentTimeMillis()));

                    cursoRepo.update(c);

                    System.out.println("Curso actualizado.");

                }, () -> System.out.println("Curso no encontrado."));
            }

            case 4 -> {

                System.out.print("ID del curso a eliminar: ");
                long id = scanner.nextLong();

                cursoRepo.findById(id).ifPresentOrElse(c -> {

                    cursoRepo.delete(c);

                    System.out.println("Curso eliminado.");

                }, () -> System.out.println("Curso no encontrado."));
            }
        }
    }

    private static void menuInscripcion() {

        System.out.println("\n--- Inscripciones ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar progreso");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");

        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {

            case 1 -> {

                System.out.println("\n--- Usuarios ---");
                usuarioRepo.findByRol("instructor")
                    .forEach(System.out::println);

                System.out.print("ID Usuario: ");
                long idUsuario = scanner.nextLong();

                System.out.println("\n--- Cursos ---");
                cursoRepo.findAll().forEach(System.out::println);

                System.out.print("ID Curso: ");
                long idCurso = scanner.nextLong();

                Optional<Usuario> usuario = usuarioRepo.findById(idUsuario);

                Optional<Curso> curso = cursoRepo.findById(idCurso);

                if (usuario.isPresent() && curso.isPresent()) {

                    Inscripcion inscripcion = new Inscripcion(
                            usuario.get(),
                            curso.get(),
                            new java.sql.Date(System.currentTimeMillis()),
                            Inscripcion.EstadoInscripcion.ACTIVA,
                            0,
                            Inscripcion.Nivel.BASICO,
                            curso.get().getValor());

                    inscripcionRepo.save(inscripcion);

                    System.out.println("Inscripcion creada.");

                } else {

                    System.out.println("Usuario o Curso no encontrado.");
                }
            }

            case 2 ->
                inscripcionRepo.findAll().forEach(System.out::println);

            case 3 -> {

                System.out.print("ID Inscripcion: ");
                long id = scanner.nextLong();

                Optional<Inscripcion> inscripcion = inscripcionRepo.findById(id);

                inscripcion.ifPresentOrElse(i -> {

                    System.out.print("Nuevo progreso: ");
                    double progreso = scanner.nextDouble();

                    i.setPorcentajeProgreso(progreso);

                    if (progreso >= 100) {

                        i.setEstado(
                                Inscripcion.EstadoInscripcion.COMPLETADA);
                    }

                    inscripcionRepo.update(i);

                    System.out.println("Progreso actualizado.");

                }, () -> System.out.println("Inscripcion no encontrada."));
            }

            case 4 -> {

                System.out.print("ID Inscripcion a eliminar: ");
                long id = scanner.nextLong();

                inscripcionRepo.findById(id).ifPresentOrElse(i -> {

                    inscripcionRepo.delete(i);

                    System.out.println("Inscripcion eliminada.");

                }, () -> System.out.println("Inscripcion no encontrada."));
            }
        }
    }

    private static void menuCategoria() {
        System.out.println("\n--- Categorias ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");
        int opt = scanner.nextInt();
        scanner.nextLine();
        switch (opt) {
            case 1 -> {
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Descripcion: ");
                String descripcion = scanner.nextLine();
                categoriaRepo.save(new Categoria(nombre, descripcion));
                System.out.println("Categoria guardada.");
            }
            case 2 -> categoriaRepo.findAll().forEach(System.out::println);
            case 3 -> {
                System.out.print("ID a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();
                Optional<Categoria> p = categoriaRepo.findById(id);
                p.ifPresentOrElse(prod -> {
                    System.out.print("Nuevo nombre: ");
                    prod.setNombre(scanner.nextLine());
                    System.out.print("Nuevo precio: ");
                    prod.setDescripcion(scanner.nextLine());
                    System.out.print("Nuevo stock: ");
                    categoriaRepo.update(prod);
                    System.out.println("categoria actualizada.");
                }, () -> System.out.println("No encontrado."));
            }
            case 4 -> {
                System.out.print("ID a eliminar: ");
                long id = scanner.nextLong();
                categoriaRepo.findById(id).ifPresentOrElse(prod -> {
                    categoriaRepo.delete(prod);
                    System.out.println("Producto eliminado.");
                }, () -> System.out.println("No encontrado."));
            }
        }
    }

    private static void menuLeccion() {

        System.out.println("\n--- Lecciones ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");

        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {

            case 1 -> {

                System.out.print("Titulo: ");
                String titulo = scanner.nextLine();

                System.out.print("Contenido: ");
                String contenido = scanner.nextLine();

                System.out.print("URL: ");
                String url = scanner.nextLine();

                System.out.print("Duracion: ");
                int duracion = scanner.nextInt();

                /*
                 * System.out.print("Orden: ");
                 * int orden = scanner.nextInt();
                 */
                System.out.println("\n--- Modulos ---");
                moduloRepo.findAll().forEach(System.out::println);

                System.out.print("ID Modulo: ");
                long idModulo = scanner.nextLong();

                Optional<Modulo> modulo = moduloRepo.findById(idModulo);

                if (modulo.isPresent()) {

                    Leccion leccion = new Leccion(
                            titulo,
                            contenido,
                            url,
                            duracion,
                            modulo.get());

                    modulo.get().agregarLeccion(leccion);

                    leccionRepo.save(leccion);

                    System.out.println("Leccion guardada.");

                } else {

                    System.out.println("Modulo no encontrado.");
                }
            }

            case 2 -> leccionRepo.findAll().forEach(System.out::println);

            case 3 -> {

                System.out.print("ID de la leccion a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();

                Optional<Leccion> leccion = leccionRepo.findById(id);

                leccion.ifPresentOrElse(l -> {

                    System.out.print("Nuevo titulo: ");
                    l.setTitulo(scanner.nextLine());

                    System.out.print("Nuevo contenido: ");
                    l.setContenido(scanner.nextLine());

                    leccionRepo.update(l);

                    System.out.println("Leccion actualizada.");

                }, () -> System.out.println("Leccion no encontrada."));
            }

            case 4 -> {

                System.out.print("ID de la leccion a eliminar: ");
                long id = scanner.nextLong();

                leccionRepo.findById(id).ifPresentOrElse(l -> {

                    leccionRepo.delete(l);

                    System.out.println("Leccion eliminada.");

                }, () -> System.out.println("Leccion no encontrada."));
            }
        }
    }

    private static void menuModulo() {

        System.out.println("\n--- Modulos ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");

        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {

            case 1 -> {

                System.out.print("Titulo del modulo: ");
                String titulo = scanner.nextLine();

                System.out.print("Descripcion: ");
                String descripcion = scanner.nextLine();

                System.out.println("\n--- Cursos ---");
                cursoRepo.findAll().forEach(System.out::println);

                System.out.print("ID Curso: ");
                long idCurso = scanner.nextLong();

                Optional<Curso> curso = cursoRepo.findById(idCurso);

                if (curso.isPresent()) {

                    Modulo modulo = new Modulo(
                            titulo,
                            descripcion,
                            curso.get());

                    curso.get().agregarModulo(modulo); // relacion en memoria entre curso y modulo

                    moduloRepo.save(modulo);

                    System.out.println("Modulo guardado.");

                } else {

                    System.out.println("Curso no encontrado.");
                }
            }

            case 2 -> moduloRepo.findAll().forEach(System.out::println);

            case 3 -> {

                System.out.print("ID del modulo a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();

                Optional<Modulo> modulo = moduloRepo.findById(id);

                modulo.ifPresentOrElse(m -> {

                    System.out.print("Nuevo titulo: ");
                    m.setTitulo(scanner.nextLine());

                    moduloRepo.update(m);

                    System.out.println("Modulo actualizado.");

                }, () -> System.out.println("Modulo no encontrado."));
            }

            case 4 -> {

                System.out.print("ID del modulo a eliminar: ");
                long id = scanner.nextLong();

                moduloRepo.findById(id).ifPresentOrElse(m -> {

                    moduloRepo.delete(m);

                    System.out.println("Modulo eliminado.");

                }, () -> System.out.println("Modulo no encontrado."));
            }
        }
    }

    private static void menuPago() {

        System.out.println("\n--- Pagos ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar estado");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");

        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {

            case 1 -> {

                System.out.println("\n--- Usuarios ---");
                usuarioRepo.findAll().forEach(System.out::println);

                System.out.print("ID Usuario: ");
                long idUsuario = scanner.nextLong();

                System.out.println("\n--- Cursos ---");
                cursoRepo.findAll().forEach(System.out::println);

                System.out.print("ID Curso: ");
                long idCurso = scanner.nextLong();
                scanner.nextLine();

                Optional<Usuario> usuario = usuarioRepo.findById(idUsuario);

                Optional<Curso> curso = cursoRepo.findById(idCurso);

                if (usuario.isPresent() && curso.isPresent()) {

                    System.out.print("Metodo Pago (TARJETA, PAYPAL, EFECTIVO): ");

                    Pago.MetodoPago metodo = Pago.MetodoPago.valueOf(
                            scanner.nextLine().toUpperCase());

                    System.out.print("ID Transaccion: ");
                    String idTransaccion = scanner.nextLine();

                    Pago pago = new Pago(
                            usuario.get(),
                            curso.get(),
                            curso.get().getValor(),
                            metodo,
                            Pago.EstadoPago.APROBADO,
                            idTransaccion);

                    pagoRepo.save(pago);

                    System.out.println("Pago guardado.");

                } else {

                    System.out.println("Usuario o Curso no encontrado.");
                }
            }

            case 2 -> pagoRepo.findAll().forEach(System.out::println);

            case 3 -> {

                System.out.print("ID Pago: ");
                long id = scanner.nextLong();
                scanner.nextLine();

                Optional<Pago> pago = pagoRepo.findById(id);

                pago.ifPresentOrElse(p -> {

                    System.out.print("Nuevo estado (APROBADO o RECHAZADO): ");

                    Pago.EstadoPago estado = Pago.EstadoPago.valueOf(
                            scanner.nextLine().toUpperCase());

                    p.setEstado(estado);

                    pagoRepo.update(p);

                    System.out.println("Estado actualizado.");

                }, () -> System.out.println("Pago no encontrado."));
            }

            case 4 -> {

                System.out.print("ID Pago a eliminar: ");
                long id = scanner.nextLong();

                pagoRepo.findById(id).ifPresentOrElse(p -> {

                    pagoRepo.delete(p);

                    System.out.println("Pago eliminado.");

                }, () -> System.out.println("Pago no encontrado."));
            }
        }
    }

    private static void menuUsuarioRol() {

        System.out.println("\n--- Usuario Rol ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Eliminar");
        System.out.print("Opción: ");

        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {

            case 1 -> {

                System.out.println("\n--- Usuarios ---");
                usuarioRepo.findAll().forEach(System.out::println);

                System.out.print("ID Usuario: ");
                long idUsuario = scanner.nextLong();

                System.out.println("\n--- Roles ---");
                rolRepo.findAll().forEach(System.out::println);

                System.out.print("ID Rol: ");
                long idRol = scanner.nextLong();

                Optional<Usuario> usuario = usuarioRepo.findById(idUsuario);
                Optional<Rol> rol = rolRepo.findById(idRol);

                if (usuario.isPresent() && rol.isPresent()) {

                    UsuarioRol usuarioRol = new UsuarioRol(usuario.get(), rol.get());

                    usuarioRolRepo.save(usuarioRol);

                    System.out.println("Rol asignado al usuario.");

                } else {

                    System.out.println("Usuario o Rol no encontrado.");
                }
            }

            case 2 -> usuarioRolRepo.findAll().forEach(System.out::println);

            case 3 -> {

                System.out.print("ID UsuarioRol a eliminar: ");
                long id = scanner.nextLong();

                usuarioRolRepo.findById(id).ifPresentOrElse(ur -> {

                    usuarioRolRepo.delete(ur);

                    System.out.println("Registro eliminado.");

                }, () -> System.out.println("Registro no encontrado."));
            }
        }
    }

    private static void menuProgresoLeccion() {

        System.out.println("\n--- Progreso Leccion ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");

        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {

            case 1 -> {

                System.out.println("\n--- Inscripciones ---");
                inscripcionRepo.findAll().forEach(System.out::println);

                System.out.print("ID Inscripcion: ");
                long idInscripcion = scanner.nextLong();

                System.out.println("\n--- Lecciones ---");
                leccionRepo.findAll().forEach(System.out::println);

                System.out.print("ID Leccion: ");
                long idLeccion = scanner.nextLong();

                Optional<Inscripcion> inscripcion = inscripcionRepo.findById(idInscripcion);

                Optional<Leccion> leccion = leccionRepo.findById(idLeccion);

                if (inscripcion.isPresent() && leccion.isPresent()) {

                    ProgresoLeccion progreso = new ProgresoLeccion(
                            inscripcion.get(),
                            leccion.get(),
                            false,
                            null);

                    progresoLeccionRepo.save(progreso);

                    System.out.println("Progreso creado.");

                } else {

                    System.out.println("Inscripcion o Leccion no encontrada.");
                }
            }

            case 2 ->
                progresoLeccionRepo.findAll().forEach(System.out::println);

            case 3 -> {

                System.out.print("ID Progreso: ");
                long id = scanner.nextLong();

                Optional<ProgresoLeccion> progreso = progresoLeccionRepo.findById(id);

                progreso.ifPresentOrElse(p -> {

                    System.out.print("¿Completado? (true/false): ");
                    boolean completado = scanner.nextBoolean();

                    p.setCompletado(completado);

                    progresoLeccionRepo.update(p);

                    System.out.println("Progreso actualizado.");

                }, () -> System.out.println("Progreso no encontrado."));
            }

            case 4 -> {

                System.out.print("ID Progreso a eliminar: ");
                long id = scanner.nextLong();

                progresoLeccionRepo.findById(id).ifPresentOrElse(p -> {

                    progresoLeccionRepo.delete(p);

                    System.out.println("Progreso eliminado.");

                }, () -> System.out.println("Progreso no encontrado."));
            }
        }
    }

    private static void menuRol() {
        System.out.println("\n--- Roles ---");
        System.out.println("1. Crear");
        System.out.println("2. Listar");
        System.out.println("3. Actualizar");
        System.out.println("4. Eliminar");
        System.out.print("Opción: ");

        int opt = scanner.nextInt();
        scanner.nextLine();

        switch (opt) {

            case 1 -> {
                System.out.print("Nombre del rol: ");
                String nombre = scanner.nextLine();

                rolRepo.save(new Rol(nombre));
                System.out.println("Rol guardado.");
            }

            case 2 -> rolRepo.findAll().forEach(System.out::println);

            case 3 -> {
                System.out.print("ID del rol a actualizar: ");
                long id = scanner.nextLong();
                scanner.nextLine();

                Optional<Rol> rol = rolRepo.findById(id);

                rol.ifPresentOrElse(r -> {

                    System.out.print("Nuevo nombre: ");
                    r.setNombre(scanner.nextLine());

                    rolRepo.update(r);

                    System.out.println("Rol actualizado.");

                }, () -> System.out.println("Rol no encontrado."));
            }

            case 4 -> {
                System.out.print("ID del rol a eliminar: ");
                long id = scanner.nextLong();

                rolRepo.findById(id).ifPresentOrElse(r -> {

                    rolRepo.delete(r);

                    System.out.println("Rol eliminado.");

                }, () -> System.out.println("Rol no encontrado."));
            }
        }
    }

}