package com.karolaynmunoz.DAO;
import org.hibernate.SessionFactory;

import com.karolaynmunoz.Model.Personatge;


public class PersonatgeDAO extends GenDAOImpl<Personatge> {
        
    public PersonatgeDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Personatge.class);
    }
}
