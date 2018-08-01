package com.hsbc.team4.ordersystem.controller;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import com.hsbc.team4.ordersystem.domain.Product;
import com.hsbc.team4.ordersystem.dto.ProductDto;
import com.hsbc.team4.ordersystem.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.controller
 * @Description :
 * @Date : 2018/8/2
 */
@RestController
@RequestMapping("product")
public class ProductController {
    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    /**
     * saveProduct
     * @param productDto
     * @return User
     */
    @PostMapping("/")
    public ResponseResults saveProduct(@RequestBody ProductDto productDto){
        BeanValidator.validateObject(productDto);
        Product product=Product.adaptProduct(productDto);
        return ResponseResults.responseBySuccess("ok",productService.addEntity(product));
    }

    /**
     * updateProduct
     * @param productDto
     * @return User
     */
    @PutMapping("/")
    public ResponseResults updateProduct(@RequestBody  ProductDto productDto){
        BeanValidator.validateObject(productDto);
        Product product=Product.adaptProduct(productDto);
        return ResponseResults.responseBySuccess("ok",productService.updateEntity(product));
    }

    /**
     * deleteProductById
     * @param id
     * @return String
     */
    @DeleteMapping("/{id}")
    public ResponseResults deleteProductById(@PathVariable String  id){
        return ResponseResults.responseBySuccess("ok",productService.updateStatusById(id,1));
    }

    /**
     * queryProductById
     * @param id
     * @return User
     */
    @GetMapping("/{id}")
    public ResponseResults queryProductById(@PathVariable String id){
        return ResponseResults.responseBySuccess("ok",productService.findById(id));
    }

    /**
     * getProductList
     * @param current
     * @param pageSize
     * @param status
     * @return
     */
    @GetMapping("/{status}")
    public ResponseResults getProductList(@RequestParam(value = "current",defaultValue = "0") int current,
                                       @RequestParam(value = "current",defaultValue = "10") int pageSize,
                                       @PathVariable int status){
        return ResponseResults.responseBySuccess("ok",productService.findByStatus(current,pageSize,status));
    }




}
