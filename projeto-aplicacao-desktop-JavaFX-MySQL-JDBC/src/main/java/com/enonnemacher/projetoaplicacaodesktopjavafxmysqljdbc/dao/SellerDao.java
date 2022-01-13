package com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.dao;

import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.entities.Department;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.entities.Seller;

import java.util.List;

public interface SellerDao {

	void insert(Seller obj);
	void update(Seller obj);
	void deleteById(Integer id);
	Seller findById(Integer id);
	List<Seller> findAll();
	List<Seller> findByDepartment(Department department);
}
