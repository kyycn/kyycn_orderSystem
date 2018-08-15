package com.hsbc.team4.ordersystem.dictionary;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.dictionary
 * @Description:
 * @Date date: 2018-08-15
 */
@RestController
public class DictionaryController {
    private final IDictionaryService dictionaryService;
    private final ResponseResults responseResults;
    private final BeanValidator beanValidator;

    @Autowired
    public DictionaryController(IDictionaryService dictionaryService, ResponseResults responseResults, BeanValidator beanValidator) {
        this.dictionaryService = dictionaryService;
        this.responseResults = responseResults;
        this.beanValidator = beanValidator;
    }

    @GetMapping("/get/{page}")
    public ResponseResults getAllDictionary(@PathVariable Integer page){
        if (page<0){
            return responseResults.responseByErrorMessage("information error");
        }
        Page<Dictionary> dictionaryPage = dictionaryService.findByStatus(page, 10, 1);
        if (dictionaryPage==null){
            return responseResults.responseByErrorMessage("fail to get");
        }
        return responseResults.responseBySuccess("success", dictionaryPage.getContent());
    }

    @GetMapping("/getType/{type}")
    public ResponseResults getDictionary(@PathVariable String type){
        if (StringUtils.isBlank(type)){
            return responseResults.responseByErrorMessage("information error");
        }
        List<Dictionary> list = dictionaryService.findByType(type);
        if (list==null){
            return responseResults.responseByErrorMessage("fail to get");
        }
        return responseResults.responseBySuccess("success", list);
    }

    @PostMapping("/add")
    public ResponseResults getDictionary(@RequestBody Dictionary dictionary){
        if (!beanValidator.validateObject(dictionary).isEmpty()){
            return responseResults.responseByErrorMessage("information error");
        }
        Dictionary reDictionary = dictionaryService.addEntity(dictionary);
        if (reDictionary==null){
            return responseResults.responseByErrorMessage("fail to add");
        }
        return responseResults.responseBySuccess("success", reDictionary);
    }
}
