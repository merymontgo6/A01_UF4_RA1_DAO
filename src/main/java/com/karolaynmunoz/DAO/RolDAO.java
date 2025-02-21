package com.karolaynmunoz.DAO;
import org.hibernate.SessionFactory;

import com.karolaynmunoz.Model.Rol;

public class RolDAO extends GenDAOImpl<Rol> {
    public RolDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Rol.class);
    }
}
