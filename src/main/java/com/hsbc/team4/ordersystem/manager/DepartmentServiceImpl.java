package com.hsbc.team4.ordersystem.manager;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * @author: Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-13
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {

    private final IDepartmentRepository departmentRepository;

    public DepartmentServiceImpl(IDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Page<Department> findByStatus(int current, int pageSize, int status) {
        return null;
    }

    @Override
    public Department addEntity(Department department) {
        return null;
    }

    @Override
    public int updateStatusById(String id, int status) {
        return 0;
    }

    @Override
    public Department updateEntity(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department findById(String id) {
        return departmentRepository.findByEntityId(id);
    }

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }
}
