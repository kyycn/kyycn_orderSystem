package com.hsbc.team4.ordersystem.dictionary;

import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import com.hsbc.team4.ordersystem.common.utils.SortTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:Cady
 * @version:
 * @Project: ordersystem
 * @Package: com.hsbc.team4.ordersystem.dictionary
 * @Description:
 * @Date date: 2018-08-09
 */
@Service
public class DictionaryServiceImpl implements IDictionaryService {

    private IDictionaryRepository dictionaryRepository;

    @Autowired
    public DictionaryServiceImpl(IDictionaryRepository dictionaryRepository) {
        this.dictionaryRepository = dictionaryRepository;
    }

    @Override
    public List<Dictionary> findByType(String type) {
        List<Dictionary> list = dictionaryRepository.findByType(type);
        for(Dictionary dic: list){
            if(dic.getStatus()==0){
                list.remove(dic);
            }
        }
        return list;
    }

    @Override
    public Page<Dictionary> findByStatus(int current, int pageSize, int status) {
        return dictionaryRepository.findByStatus(status, PageableTools.basicPage(current,pageSize));
    }

    @Override
    public Dictionary addEntity(Dictionary dictionary) {
        dictionary.setId(new UUIDFactory().getUUID());
        dictionary.setStatus(1);
        dictionary.setCreateTime(System.currentTimeMillis());
        if (new BeanValidator().validateObject(dictionary).isEmpty()){
            return dictionaryRepository.save(dictionary);
        }
        return null;
    }

    @Override
    public int updateStatusById(String id, int status) {
        if (status==0 ||status==1){
            return dictionaryRepository.updateStatusById(id, status);
        }
        return 0;
    }

    @Override
    public Dictionary updateEntity(Dictionary dictionary) {
        dictionary.setUpdateTime(System.currentTimeMillis());
        return dictionaryRepository.save(dictionary);
    }

    @Override
    public Dictionary findById(String id) {
        return dictionaryRepository.findByEntityId(id);
    }
}
