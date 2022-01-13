package com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.service;


import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.dao.DaoFactory;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.dao.SellerDao;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.entities.Department;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.entities.Seller;

import java.util.List;

public class SellerService {

    private SellerDao sellerDao = DaoFactory.createSellerDao();

    public List<Seller> findAll() {
        return sellerDao.findAll();
    }

    public void saveOrUpdate(Seller seller) {
        if (seller.getId() == null) {
            sellerDao.insert(seller);
        } else {
            sellerDao.update(seller);
        }
    }

    public void remove(Department department) {
        sellerDao.deleteById(department.getId());
    }
}
