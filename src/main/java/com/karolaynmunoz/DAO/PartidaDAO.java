package com.karolaynmunoz.DAO;
import org.hibernate.SessionFactory;

import com.karolaynmunoz.Model.Partida;


public class PartidaDAO extends GenDAOImpl<Partida> {
    
    public PartidaDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Partida.class);
    }
}
