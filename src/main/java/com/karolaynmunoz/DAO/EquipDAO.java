package com.karolaynmunoz.DAO;
import org.hibernate.SessionFactory;

import com.karolaynmunoz.Model.Equip;

public class EquipDAO extends GenDAOImpl<Equip>  {
    public EquipDAO(SessionFactory sessionFactory) {
        super(sessionFactory,Equip.class);
    }
}
