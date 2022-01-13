package com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.dao;

import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.dao.impl.DepartmentDaoJDBC;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.dao.impl.SellerDaoJDBC;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.db.DB;

public class DaoFactory {

    public static SellerDao createSellerDao() {
        return new SellerDaoJDBC(DB.getConnection());
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC(DB.getConnection());
    }
}
