package com.hsbc.team4.ordersystem.dictionary;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

/**
* DictionaryService Tester.
*
* @author <Authors name>
* @since <pre>???? 9, 2018</pre>
* @version 1.0
*/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DictionaryServiceImplTest {

    @Autowired
    private DictionaryServiceImpl dictionaryService;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
    *
    * Method: findByType(String type)
    *
    */
    @Test
    public void testFindByType() throws Exception {
        log.info(dictionaryService.findByType(0,"sex").toString());

    }

    /**
    *
    * Method: findByStatus(int current, int pageSize, int status)
    *
    */
    @Test
    public void testFindByStatus() throws Exception {
        Page<Dictionary> page = dictionaryService.findByStatus(0,2,1);
        for(Dictionary dic: page){
            log.info(dic.toString());
        }
    }

    /**
    *
    * Method: addEntity(Dictionary dictionary)
    *
    */
    @Test
    public void testAddEntity() throws Exception {
        Dictionary dictionary = new Dictionary();
        dictionary.setType("aaa");
        dictionary.setContent("aaa");
        dictionary.setDescription("aaa");
        dictionary.setType("orderStatus");
        dictionary.setContent("payed");
        dictionary.setDescription("��֧��");
        dictionaryService.addEntity(dictionary);
    }

    /**
    *
    * Method: updateStatusById(String id, int status)
    *
    */
    @Test
    public void testUpdateStatusById() throws Exception {
        dictionaryService.updateStatusById("1",1);
    }

    /**
    *
    * Method: updateEntity(Dictionary dictionary)
    *
    */
    @Test
    public void testUpdateEntity() throws Exception {
        Dictionary dictionary = dictionaryService.findById("08ada37650c8441889ed44c771311e7b1533799457226");
        dictionary.setStatus(0);
        dictionaryService.updateEntity(dictionary);
    }

    /**
    *
    * Method: findById(String id)
    *
    */
    @Test
    public void testFindById() throws Exception {
        log.info(dictionaryService.findById("08ada37650c8441889ed44c771311e7b1533799457226").toString());
    }


}
