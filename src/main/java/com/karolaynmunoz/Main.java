package com.karolaynmunoz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.SessionFactory;

import com.karolaynmunoz.DAO.EquipDAO;
import com.karolaynmunoz.DAO.PartidaDAO;
import com.karolaynmunoz.DAO.PersonatgeDAO;
import com.karolaynmunoz.DAO.RolDAO;
import com.karolaynmunoz.Model.Equip;
import com.karolaynmunoz.Model.Partida;
import com.karolaynmunoz.Model.Personatge;
import com.karolaynmunoz.Model.Rol;

public class Main {
    public static boolean sortirapp = false;
    public static String message = "==================";
    public static void main(String[] args)  throws IOException, SQLException, InterruptedException {
    SessionFactory sesion = HibernateUtil.getSessionFactory();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("=============================");
            System.out.println("Gestio de Peticions i Usuaris");
            System.out.println("=============================");
            int opcio = MenuOptions(br);

            switch(opcio) {
                case 1 -> {
                    System.out.println(message);
                    System.out.println("Funcionen Totes les opcions");
                    System.out.println(message);
                    int taula = demanarTaula(br);
                    switch (taula) {
                        case 1 -> dadesRol(br, sesion);
                        case 2 -> dadesPersonatge(br, sesion);
                        case 3 -> dadesEquip(br, sesion);
                        case 4 -> dadesPartida(br, sesion);
                    }
                }
                case 2 -> {
                    System.out.println(message);
                    System.out.println("Funcionen Totes les opcions");
                    System.out.println(message);
                    int taula = demanarTaula(br);
                    switch (taula) {
                        case 1 -> readRol(br, sesion);
                        case 2 -> readPersonatge(br, sesion);
                        case 3 -> readEquip(br, sesion);
                        case 4 -> readPartida(br, sesion);
                    }
                }
                case 3 -> {
                    System.out.println(message);
                    System.out.println("Funciona Rol i Personatge");
                    System.out.println(message);
                    int taula = demanarTaula(br);
                    switch (taula) {
                        case 1 -> updateRol(br, sesion);
                        case 2 -> updatePersonatge(br, sesion);
                    }
                }
                case 4 -> {
                    System.out.println(message);
                    System.out.println("Funciona Rol");
                    System.out.println(message);
                    int taula = demanarTaula(br);
                    switch (taula) {
                        case 1 -> deleteRol(br, sesion);
                    }
                }
                case 5 -> {
                    System.out.println(message);
                    System.out.println("Funciona Rol");
                    System.out.println(message);
                    int taula = demanarTaula(br);
                    switch (taula) {
                        case 1 -> findAllRol(br, sesion);
                    }
                }
                case 6 -> {
                    System.out.println(message);
                    System.out.println("Funciona Rol i Personatge");
                    System.out.println(message);
                    int taula = demanarTaula(br);
                    switch (taula) {
                        case 1 -> agregacionsRol(br, sesion);
                        case 2 -> agregacionsPersonatge(br, sesion);
                    }
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
        String message = "";

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

    public static int demanarTaula (BufferedReader br) throws IOException {
        System.out.println("Quina taula vols tractar?:\n1. Rol\n2. Personatge\n3. Equipo\n4. Partida ");
        int taula = Integer.parseInt(br.readLine());

        return taula;
    }

    public static void dadesRol(BufferedReader br, SessionFactory sesion) throws IOException {
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
        rDAO.crearRol(sesion, rol1); // Crear el rol juntament amb els personatges
    }
    
    public static void dadesPersonatge(BufferedReader br, SessionFactory sesion) throws IOException {
        PersonatgeDAO pDAO = new PersonatgeDAO(sesion);

        System.out.println("Introdueix el nom del personatge: ");
        String nomPersonatge = br.readLine();

        System.out.println("Introdueix el nom del rol: ");
        String nomRol = br.readLine();

        System.out.println("Introdueix el nom de l'equip: ");
        String nomEquip = br.readLine();

        // Crear un objecte de tipus Rol
        Rol rol = new Rol(nomRol);

        // Crear un objecte de tipus Personatge i assignar el rol
        Personatge p1 = new Personatge(nomPersonatge, rol);

        // Crear l'equip i assignar-lo al personatge
        Equip equip = new Equip(nomEquip, p1);
        Set<Equip> equips = new HashSet<>();
        equips.add(equip);
        p1.setEquips(equips);

        // Persistir el personatge, el rol i l'equip en una única transacció
        pDAO.crearPersonatge(sesion, p1);
    }

    public static void dadesEquip(BufferedReader br, SessionFactory sesion) throws IOException {
        EquipDAO eDAO = new EquipDAO(sesion);
    
        System.out.println("Introdueix el nom de l'equip: ");
        String nomEquip = br.readLine();
    
        System.out.println("Introdueix l'id del personatge: ");
        int idPersonatge = Integer.parseInt(br.readLine());
    
        // Crear un objecte de tipus Personatge amb l'ID proporcionat
        Personatge personatge = new Personatge();
        personatge.setId(idPersonatge);
    
        // Crear un objecte de tipus Equip i assignar el personatge
        Equip equip = new Equip(nomEquip, personatge);
    
        // Persistir l'equip en una única transacció
        eDAO.crearEquip(sesion, equip);
    }
    
    public static void dadesPartida(BufferedReader br, SessionFactory sesion) throws IOException {
        PartidaDAO pDAO = new PartidaDAO(sesion);
    
        System.out.println("Introdueix l'id de l'equip: ");
        int idEquip = Integer.parseInt(br.readLine());
    
        // Crear un objecte de tipus Equip amb l'ID proporcionat
        Equip equip = new Equip();
        equip.setId_equip(idEquip);
    
        // Crear un objecte de tipus Partida i assignar l'equip
        List<Equip> equips = new ArrayList<>();
        equips.add(equip);
        Partida partida = new Partida(equips);
    
        // Persistir la partida en una única transacció
        pDAO.crearPartida(sesion, partida);
    }
    
    public static int demanarId() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Introdueix l'ID: ");
        int id = Integer.parseInt(br.readLine());
        return id;
    }

    public static void readRol(BufferedReader br, SessionFactory sesion) throws IOException {
        int id = demanarId();
        System.out.println("ID introduït: " + id);
    
        // Crear una instància de RolDAO i crida el mètode readRol
        RolDAO rolDAO = new RolDAO(sesion);
        rolDAO.readRol(sesion, id);
    }
    
    public static void readPersonatge(BufferedReader br, SessionFactory sesion) throws IOException {
        int id = demanarId();
        System.out.println("ID introduït: " + id);

        // Crea una instància de PersonatgeDAO i crida el mètode readPersonatge
        PersonatgeDAO pDAO = new PersonatgeDAO(sesion);
        pDAO.readPersonatge(sesion, id);
    }

    public static void readEquip(BufferedReader br, SessionFactory sesion) throws IOException {
        int id = demanarId();
        System.out.println("ID introduït: " + id);

        EquipDAO eDAO = new EquipDAO(sesion);
        eDAO.readEquip(sesion, id);
    }
    
    public static void readPartida(BufferedReader br, SessionFactory sesion) throws IOException {
        int id = demanarId();
        System.out.println("ID introduït: " + id);

        PartidaDAO pDAO = new PartidaDAO(sesion);
        pDAO.readPartida(sesion, id);
    }

    public static void updateRol(BufferedReader br, SessionFactory sesion) throws IOException {
        int id = demanarId();
        System.out.println("ID introduït: " + id);
    
        RolDAO rDAO = new RolDAO(sesion);
        if (!rDAO.existeixRolId(sesion, id)) {
            System.out.println("No s'ha trobat cap rol amb l'ID " + id);
            return;
        }
        
        System.out.println("Quin camp vols modificar?");
        System.out.println("1. Nom del rol");
        int opcio = Integer.parseInt(br.readLine());
    
        String nouValor = "";
        switch (opcio) {
            case 1 -> {
                System.out.println("Introdueix el nou nom del rol: ");
                nouValor = br.readLine();
            }
            default -> {
                System.out.println("Opció no vàlida.");
                return;
            }
        }
        rDAO.updateRol(sesion, id, nouValor);
    }
    
    public static void updatePersonatge(BufferedReader br, SessionFactory sesion) throws IOException {
        int id = demanarId();
        System.out.println("ID introduït: " + id);
    
        PersonatgeDAO pDAO = new PersonatgeDAO(sesion);
        if (!pDAO.existeixPersonatgeId(sesion, id)) {
            System.out.println("No s'ha trobat cap personatge amb l'ID " + id);
            return;
        }
    
        System.out.println("Quin camp vols modificar?");
        System.out.println("1. Nom del personatge\n2. Id del rol");
        int opcio = Integer.parseInt(br.readLine());
    
        String nouValor = "";
        int idRol = 0;

        switch (opcio) {
            case 1 -> {
                System.out.println("Introdueix el nou nom del personatge: ");
                nouValor = br.readLine();
            }
            case 2 -> {
                System.out.println("Introdueix el nou id del rol: ");
                idRol = Integer.parseInt(br.readLine());
            }
            default -> {
                System.out.println("Opció no vàlida.");
                return;
            }
        }
        pDAO.updatePersonatge(id, nouValor, opcio, idRol);
    }

    public static void deleteRol(BufferedReader br, SessionFactory sesion) throws IOException {
        int id = demanarId();
        System.out.println("ID introduït: " + id);
    
        RolDAO rDAO = new RolDAO(sesion);
        rDAO.deleteRol(sesion, id);
    }

    public static void findAllRol(BufferedReader br, SessionFactory sesion) {
        RolDAO rDAO = new RolDAO(sesion);
        rDAO.findAllRol(sesion);
    }

    public static void agregacionsRol(BufferedReader br, SessionFactory sesion) throws IOException {
        System.out.println("Agregacions del rol");
        System.out.println("Selecciona l'agrupació:");
        System.out.println("1. Agrupació per id_rol\n2. Agrupació per nom_rol");
        int opcio = Integer.parseInt(br.readLine());
        RolDAO rDAO = new RolDAO(sesion);
        rDAO.agregacionsRol(sesion, opcio);
    }
    
    public static void agregacionsPersonatge(BufferedReader br, SessionFactory sesion) throws IOException {
        System.out.println("Agregacions del Personatge");
        System.out.println("Selecciona l'agrupació:");
        System.out.println("1. Agrupació per id_personatge");
        System.out.println("2. Agrupació per id_rol");
        System.out.println("3. Agrupació per nom_personatge");
        int opcio = Integer.parseInt(br.readLine());
    
        PersonatgeDAO pDAO = new PersonatgeDAO(sesion);
        pDAO.agregacionsPersonatge(sesion, opcio);
    }
}