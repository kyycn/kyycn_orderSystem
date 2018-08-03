package com.hsbc.team4.ordersystem.products;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Kevin
 * @version :
 * @Project : ordersystem
 * @Package : com.hsbc.team4.ordersystem.products.controller
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
     * @Author:yang
     * @Description:saveProduct
     * @Param:
     * @return:
     * @Date: 2018/8/3
     */
    @PostMapping("/")
    public ResponseResults saveProduct(@RequestBody ProductDto productDto) {
        BeanValidator.validateObject(productDto);
        Product product = Product.adaptProduct(productDto);
        return ResponseResults.responseBySuccess("ok", productService.addEntity(product));
    }

    /**
     * @Author:yang
     * @Description:updateProduct
     * @Param:
     * @return:
     * @Date: 2018/8/3
     */
    @PutMapping("/")
    public ResponseResults updateProduct(@RequestBody ProductDto productDto) {
        BeanValidator.validateObject(productDto);
        Product product = Product.adaptProduct(productDto);
        return ResponseResults.responseBySuccess("ok", productService.updateEntity(product));
    }

    /**
     * @Author:yang
     * @Description:deleteProductById
     * @Param: id
     * @return:
     * @Date: 2018/8/3
     */
    @DeleteMapping("/{id}")
    public ResponseResults deleteProductById(@PathVariable String id) {
        return ResponseResults.responseBySuccess("ok", productService.updateStatusById(id, 1));
    }

    /**
     * @Author:yang
     * @Description:query product by id
     * @Param: id
     * @return:
     * @Date: 2018/8/3
     */
    @GetMapping("/{id}")
    public ResponseResults queryProductById(@PathVariable String id) {
        return ResponseResults.responseBySuccess("ok", productService.findById(id));
    }

    /**
     * @Author:yang
     * @Description:query product detail info
     * @Param: the id of product
     * @return:ResponseResults
     * @Date: 2018/8/3
     */
    @GetMapping("/query")
    public ResponseResults query(@RequestParam String id) {
        return ResponseResults.responseBySuccess("ok", productService.query(id));
    }

    /**
     * @Author:yang
     * @Description:
     * @Param:
     * @return:
     * @Date: 2018/8/3
     */
    @GetMapping("/{status}")
    public ResponseResults getProductList(@RequestParam(value = "current", defaultValue = "0") int current,
                                          @RequestParam(value = "current", defaultValue = "10") int pageSize,
                                          @PathVariable int status) {
        return ResponseResults.responseBySuccess("ok", productService.findByStatus(current, pageSize, status));
    }

}
