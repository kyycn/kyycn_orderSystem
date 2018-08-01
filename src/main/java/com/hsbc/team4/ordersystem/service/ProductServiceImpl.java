package com.hsbc.team4.ordersystem.service;

import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import com.hsbc.team4.ordersystem.dao.IProductRepository;
import com.hsbc.team4.ordersystem.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.service
 * @Description :
 * @Date : 2018/8/2
 */
@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> findByStatus(int current, int pageSize, int status) {
        Pageable pageable= PageableTools.addTimeSortForDescAndPage(current,pageSize);
        return productRepository.findByStatus(status,pageable);
    }

    @Override
    public Product addEntity(Product product) {
        return productRepository.save(product);
    }

    @Override
    public int updateStatusById(String id, int status) {
        return productRepository.updateStatusById(id,1);
    }

    @Override
    public Product updateEntity(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findByEntityId(id);
    }
}
