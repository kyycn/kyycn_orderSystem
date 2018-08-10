package com.hsbc.team4.ordersystem.manager;

import com.hsbc.team4.ordersystem.common.utils.ValidatorTools;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
* ManagerAccountServiceImpl Tester.
*
* @author <Authors name>
* @since <pre>???? 7, 2018</pre>
* @version 1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ManagerAccountServiceImplTest {

    @Autowired
    private IManagerAccountService managerAccountService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: findByStatus(int current, int pageSize, int status)
    *
    */
    @Test
    public void testFindByStatus() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: addEntity(ManagerAccount managerAccount)
    *
    */
    @Test
    public void testAddEntity() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: updateStatusById(String id, int status)
    *
    */
    @Test
    public void testUpdateStatusById() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: updateEntity(ManagerAccount managerAccount)
    *
    */
    @Test
    public void testUpdateEntity() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: findById(String id)
    *
    */
    @Test
    public void testFindById() throws Exception {
    //TODO: Test goes here...
    }

    /**
    *
    * Method: findByName(String name)
    *
    */
    @Test
    public void testFindByName() throws Exception {
        System.out.println(managerAccountService.findByName("666"));
    }


}
