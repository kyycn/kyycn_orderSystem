package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.nimbus.State;

/**
 * @author: Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.manager
 * @Description:
 * @Date date: 2018-08-14
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final IDepartmentService departmentService;
    private final ResponseResults responseResults;
    private final BeanValidator beanValidator;

    @Autowired
    public DepartmentController(IDepartmentService departmentService, ResponseResults responseResults, BeanValidator beanValidator) {
        this.departmentService = departmentService;
        this.responseResults = responseResults;
        this.beanValidator = beanValidator;
    }

    @GetMapping("/get/{page}")
    public ResponseResults getAllDepartments(@PathVariable Integer page){
        if (page>=0){
            Page<Department> departmentPage = departmentService.findByStatus(page,20,1);
            if (departmentPage!=null){
                return responseResults.responseBySuccess("success", departmentPage.getContent());
            }
        }
        return responseResults.responseByErrorMessage("fail to get");
    }

    @PostMapping("/update")
    public ResponseResults updateDepartment(@RequestBody Department department){
        if (!beanValidator.validateObject(department).isEmpty()){
            return responseResults.responseByErrorMessage("information error");
        }
        department = departmentService.updateEntity(department);
        if (department!=null){
            return responseResults.responseBySuccess("success", department);
        }
        return responseResults.responseByErrorMessage("fail to update");
    }

    @PostMapping("/add")
    public ResponseResults addDepartment(@RequestBody Department department){
        if (beanValidator.validateObject(department).isEmpty()){
            Department department1 = departmentService.addEntity(department);
            if (department1!=null){
                return responseResults.responseBySuccess("add successful", department1);
            }
        }
        return responseResults.responseByErrorMessage("fail to add");
    }

}
