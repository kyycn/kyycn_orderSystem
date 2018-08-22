package com.hsbc.team4.ordersystem.products;

import com.hsbc.team4.ordersystem.common.utils.PageableTools;
import com.hsbc.team4.ordersystem.products.IProductService;
import com.hsbc.team4.ordersystem.products.Product;
import com.hsbc.team4.ordersystem.products.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.service
 * @Description :
 * @Date : 2018/8/2
 */
@Service
public class ProductServiceImpl implements IProductService {

    private final IProductRepository productRepository;

    /**
     * @Author:yang
     * @Description:
     * @Param:
     * @return:
     * @Date: 2018/8/3
     */
    @Autowired
    public ProductServiceImpl(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * @Author:yang
     * @Description:
     * @Param:
     * @return:
     * @Date: 2018/8/3
     */
    @Override
    public Page<Product> findByStatus(int current, int pageSize, int status) {
        Pageable pageable = PageableTools.addTimeSortForDescAndPage(current, pageSize);
        return productRepository.findByStatus(status, pageable);
    }

    /**
     * @Author:yang
     * @Description:
     * @Param:
     * @return:
     * @Date: 2018/8/3
     */
    @Override
    public Product addEntity(Product product) {
        return productRepository.save(product);
    }

    /**
     * @Author:yang
     * @Description:
     * @Param:
     * @return:
     * @Date: 2018/8/3
     */
    @Override
    public int updateStatusById(String id, int status) {
        return productRepository.updateStatusById(id, 1);
    }

    /**
     * @Author:yang
     * @Description:
     * @Param:
     * @return:
     * @Date: 2018/8/3
     */
    @Override
    public Product updateEntity(Product product) {
        return productRepository.saveAndFlush(product);
    }

    /**
     * @Author:yang
     * @Description:
     * @Param:
     * @return:
     * @Date: 2018/8/3
     */
    @Override
    public Product findById(String id) {
        return productRepository.findByEntityId(id);
    }

    @Override
    public Page<Product> findByProductType(int current, int pageSize, int status, String productType) {
        Pageable pageable = PageableTools.basicPage(current, pageSize);
        return productRepository.findByProductType(status, productType, pageable);
    }

    @Override
    public Page<Product> findByProductTypeContains(int current, int pageSize, int status, String productType) {
        Pageable pageable = PageableTools.basicPage(current, pageSize);
        return productRepository.findByProductTypeContains(status, productType, pageable);
    }

    @Override
    public Page<Product> findByProductNameContains(int current, int pageSize, int status, String productName) {
        Pageable pageable = PageableTools.basicPage(current, pageSize);
        return productRepository.findByProductNameContains(status, productName, pageable);
    }

    @Override
    public Page<Product> orderByProductPrice(int current, int pageSize, int status) {
        Pageable pageable = PageableTools.basicPage(current, pageSize);
        return productRepository.orderByProductPrice(status, pageable);
        /**
         * @Author:yang
         * @Description: query product detail info
         * @Param:the id of product
         * @return:product
         * @Date: 2018/8/3
         */
    }
        @Override
        public Product query (String id){
//        Product product = new Product();
//        product.setId(id);
//        product.setProductName("汇丰生活信用卡");
//        product.setProductDescription("汇丰银行国内首发汇丰生活信用卡，其包含一张人民币银联白金卡和一张美元MasterCard白金卡");
//        product.setProductIcon("life-card.jpg");
//        product.setProductPrice(300);
//        product.setStandar("汇丰生活信用卡主卡年费人民币300元，内含多项专属权益。首年激活卡片后免收首年年费，刷卡满6次可免次年年费。附属卡免收年费。");
//        product.setCondidtion("主卡申请人需年满18周岁，境内居民申请需月薪达人民币4,000元或以上，境外居民申请需月薪达人民币10,000元或以上。附属卡申请人须年满16周岁。 ");
//        return product;
            return productRepository.findByEntityId(id);
        }

    @Override
    public Page<Product> queryLike(String productType,String str,int current, int pageSize) {
        Pageable pageable = PageableTools.basicPage(current, pageSize);
        return productRepository.queryLike(productType,str,pageable);
    }
    }


