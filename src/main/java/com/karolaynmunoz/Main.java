package com.karolaynmunoz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;

import com.karolaynmunoz.DAO.RolDAO;
import com.karolaynmunoz.Model.Personatge;
import com.karolaynmunoz.Model.Rol;

public class Main {
    public static boolean sortirapp = false;
    public static String message = "==================";
    public static void main(String[] args)  throws IOException, SQLException, InterruptedException, Exception {
    SessionFactory sesion = HibernateUtil.getSessionFactory();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("=============================");
            System.out.println("Gestio de Peticions i Usuaris");
            System.out.println("=============================");
            int opcio = MenuOptions(br);

            switch(opcio) {
                case 1 -> {
                    System.out.println(message);
                    dadesRol(br, sesion);
                }
                case 2 -> {
                    System.out.println(message);
                    readRol(br, sesion);
                }
                case 3 -> {
                    System.out.println(message);
                    updateRol(br, sesion);
                }
                case 4 -> {
                    System.out.println(message);
                    deleteRol(br, sesion);
                }
                case 5 -> {
                    System.out.println(message);
                    findAllRol(br, sesion);
                }
                case 6 -> {
                    System.out.println(message);
                    agregacionsRol(br, sesion);
                }
                case 0 -> {
                    sortirapp = true;
                    break;
                }
                default -> {
                    System.out.println("Opcio no vàlida");
                    opcio = MenuOptions(br);
                }
            }

        } catch (IOException ioe) {
            System.err.println("Error d'entrada");
        }
    }

    public static int MenuOptions(BufferedReader br) throws InterruptedException, IOException {
        message = "==================";
        System.out.println(message);
        message = "OPCIONS";
        System.out.println(message);
        message = "1. CREAR (INSERIR) LES DADES";
        System.out.println(message);
        message = "2. READ LES DADES";
        System.out.println(message);
        message = "3. UPDATE LES DADES";
        System.out.println(message);
        message = "4. DELETE LES DADES";
        System.out.println(message);
        message = "5. FINDALL ";
        System.out.println(message);
        message = "6. AGREGACIONS (GROUP BY)";
        System.out.println(message);
        message = "0. SORTIR";
        System.out.println(message);
        message = "==================";
        System.out.println(message);
        message = "Opció: ";
        for (char c : message.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            Thread.sleep(10);   
        }

        int opcio = Integer.parseInt(br.readLine());
        return opcio;
    }

    public static void dadesRol(BufferedReader br, SessionFactory sesion) throws IOException, Exception {
        RolDAO rDAO = new RolDAO(sesion);
    
        System.out.println("Introdueix el nom del rol: ");
        String nomRol = br.readLine();
    
        System.out.println("Introdueix el nom del personatge: ");
        String[] nomsPersonatges = br.readLine().split(",");
        Set<Personatge> nomPersonatge = new HashSet<>();
        for (String nom : nomsPersonatges) {
            Personatge personatge = new Personatge(nom.trim());
            nomPersonatge.add(personatge);
        }
    
        Rol rol1 = new Rol(nomRol, nomPersonatge);
        for (Personatge personatge : nomPersonatge) {
            personatge.setRol(rol1); // Assignar el rol als personatges
        }
        rDAO.save(rol1); // Crear el rol juntament amb els personatges
    }

    public static int demanarId() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introdueix l'ID: ");
        int id = Integer.parseInt(br.readLine());
        return id;
    }

    public static void readRol(BufferedReader br, SessionFactory sesion) throws IOException, Exception {
        int id = demanarId();
        System.out.println("ID introduït: " + id);
        RolDAO rolDAO = new RolDAO(sesion);
        rolDAO.get(id);
    }

    public static void updateRol(BufferedReader br, SessionFactory sesion) throws IOException, Exception {
        int id = demanarId();
        System.out.println("ID introduït: " + id);
    
        RolDAO rDAO = new RolDAO(sesion);
        Rol rol = rDAO.get(id);
        if (rol == null) {
            System.out.println("No s'ha trobat cap rol amb l'ID " + id);
        } else {
            System.out.println("Introdueix el nou nom del rol: ");
            String nouValor = br.readLine();
            rol.setNom_rol(nouValor);
            rDAO.update(rol);
        }
    }

    public static void deleteRol(BufferedReader br, SessionFactory sesion) throws IOException, Exception {
        int id = demanarId();
        System.out.println("ID introduït: " + id);
    
        RolDAO rDAO = new RolDAO(sesion);
        Rol rol = rDAO.get(id);
        if (rol != null) {
            rDAO.delete(rol);
        } else {
            System.out.println("No s'ha trobat cap rol amb l'ID " + id);
        }
    }

    public static void findAllRol(BufferedReader br, SessionFactory sesion) throws  Exception {
        RolDAO rDAO = new RolDAO(sesion);
        rDAO.getAll();
    }

    public static void agregacionsRol(BufferedReader br, SessionFactory sesion) throws IOException {
        System.out.println("Agrupació per nom_rol");
        RolDAO rDAO = new RolDAO(sesion);
        rDAO.agregacions();
    }
}