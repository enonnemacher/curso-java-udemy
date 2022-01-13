package com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.service;


import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.dao.DaoFactory;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.dao.DepartmentDao;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.entities.Department;

import java.util.List;

public class DepartmentService {

    private DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

    public List<Department> findAll() {
        return departmentDao.findAll();
    }
}
