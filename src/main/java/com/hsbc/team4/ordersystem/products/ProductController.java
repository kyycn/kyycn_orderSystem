package com.hsbc.team4.ordersystem.products;

import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "product")
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
    @ApiOperation(value = "save product", httpMethod = "POST", notes = "save product", response = ResponseResults.class)
    @PostMapping("/")
    public ResponseResults saveProduct(@ApiParam(required = true,name = "productDto",value = "productDto project") @RequestBody ProductDto productDto){
        BeanValidator.validateObject(productDto);
        Product product=Product.adaptProduct(productDto);
        return ResponseResults.responseBySuccess("ok",productService.addEntity(product));
    }

    /**
     * updateProduct
     * @param productDto
     * @return User
     */
    @ApiOperation(value = "update product", httpMethod = "POST", notes = "update product", response = ResponseResults.class)
    @PutMapping("/")
    public ResponseResults updateProduct(@ApiParam(required = true,name = "productDto",value = "productDto project")@RequestBody  ProductDto productDto){
        BeanValidator.validateObject(productDto);
        Product product=Product.adaptProduct(productDto);
        return ResponseResults.responseBySuccess("ok",productService.updateEntity(product));
    }

    /**
     * deleteProductById
     * @param id
     * @return String
     */
    @ApiOperation(value = "delete by Id", httpMethod = "DELETE", notes = "delete product by Id", response = ResponseResults.class)
    @DeleteMapping("/{id}")
    public ResponseResults deleteProductById(@ApiParam(required = true,name = "id",value = "the product id")@PathVariable String  id){
        return ResponseResults.responseBySuccess("ok",productService.updateStatusById(id,1));
    }

    /**
     * queryProductById
     * @param id
     * @return User
     */
    @ApiOperation(value = "get by Id", httpMethod = "GET", notes = "get product by id", response = ResponseResults.class)
    @GetMapping("/{id}")
    public ResponseResults queryProductById(@ApiParam(required = true,name = "id",value = "the product id")@PathVariable String id){
        return ResponseResults.responseBySuccess("ok",productService.findById(id));
    }

    /**
     * getProductList
     * @param current
     * @param pageSize
     * @param status
     * @return
     */
    @ApiOperation(value = "status", httpMethod = "GET", notes = "get productList", response = ResponseResults.class)
    @GetMapping("/{status}")
    public ResponseResults getProductList(@RequestParam(value = "current",defaultValue = "0") int current,
                                       @RequestParam(value = "current",defaultValue = "10") int pageSize,
                                       @PathVariable int status){
        return ResponseResults.responseBySuccess("ok",productService.findByStatus(current,pageSize,status));
    }




}
