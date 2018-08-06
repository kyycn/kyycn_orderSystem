package com.hsbc.team4.ordersystem.roles;

import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.roles
 * @Description :
 * @Date : 2018/8/2
 */
@Service
public class RoleServiceImpl implements IRoleService{
    private final IRoleRepository iRoleRepository;

    @Autowired
    public RoleServiceImpl(IRoleRepository iRoleRepository) {
        this.iRoleRepository = iRoleRepository;
    }

    @Override
    public Page<Role> findByStatus(int current, int pageSize, int status) {
        Pageable pageable= PageableTools.addTimeSortForDescAndPage(current,pageSize);
        return iRoleRepository.findByStatus(status,pageable);
    }

    @Override
    public Role addEntity(Role role) {
        return iRoleRepository.save(role);
    }

    @Override
    public int updateStatusById(String id, int status) {
        return iRoleRepository.updateStatusById(id,status);
    }

    @Override
    public Role updateEntity(Role role) {
        return iRoleRepository.saveAndFlush(role);
    }

    @Override
    public Role findById(String id) {
        return iRoleRepository.findByEntityId(id);
    }
}
