package com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.dao;

import java.util.List;

import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.entities.Department;
import model.entities.Department;

public interface DepartmentDao {

	void insert(Department obj);
	void update(Department obj);
	void deleteById(Integer id);
	Department findById(Integer id);
	List<Department> findAll();
}
