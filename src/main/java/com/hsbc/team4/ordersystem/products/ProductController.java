package com.hsbc.team4.ordersystem.products;

import com.alibaba.fastjson.JSON;
import com.hsbc.team4.ordersystem.common.adapt.BeanAdapter;
import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class ProductController {
    private final IProductService productService;
    private final ResponseResults responseResults;
    private final BeanValidator beanValidator;
    private final BeanAdapter beanAdapter;

    @Autowired
    public ProductController(IProductService productService,ResponseResults responseResults,BeanValidator beanValidator,BeanAdapter beanAdapter) {
        this.productService = productService;
        this.responseResults=responseResults;
        this.beanValidator=beanValidator;
        this.beanAdapter=beanAdapter;

    }

    /**
     * saveProduct
     * @param productDto
     * @return product
     */
    @ApiOperation(value = "save product", httpMethod = "POST", notes = "save product", response = ResponseResults.class)
    @PostMapping("/save")
    public ResponseResults saveProduct(@ApiParam(required = true,name = "productDto",value = "productDto project") @RequestBody ProductDto productDto){
        beanValidator.validateObject(productDto);
        log.info("beanValidatorTest",beanValidator);
        Product product= (Product) beanAdapter.dtoAdapter(productDto,new Product());
        return responseResults.responseBySuccess("ok",productService.addEntity(product));
    }

    /**
     * updateProduct
     * @param productDto
     * @return Account
     */
    @ApiOperation(value = "update product", httpMethod = "POST",notes= "update product", response = ResponseResults.class)
    @PutMapping("/update")
    public ResponseResults updateProduct(@ApiParam(required = true,name = "productDto",value = "productDto project")@RequestBody  ProductDto productDto){
        beanValidator.validateObject(productDto);
        Product product=Product.adaptProduct(productDto);
        return responseResults.responseBySuccess("ok",productService.updateEntity(product));
    }

    /**
     * deleteProductById
     * @param id
     * @return String
     */
    @ApiOperation(value = "delete by Id", httpMethod = "DELETE", notes = "delete product by Id", response = ResponseResults.class)
    @DeleteMapping("/{id}")
    public ResponseResults deleteProductById(@ApiParam(required = true,name = "id",value = "the product id")@PathVariable String  id){
        return responseResults.responseBySuccess("ok",productService.updateStatusById(id,1));
    }

    /**
     * queryProductById
     * @param id
     * @return Account
     */
    @ApiOperation(value = "get by Id", httpMethod = "GET", notes = "get product by id", response = ResponseResults.class)
    @GetMapping("/productId/{id}")
    public ResponseResults queryProductById(@ApiParam(required = true,name = "id",value = "the product id")@PathVariable String id){
        return responseResults.responseBySuccess("ok",productService.findById(id));
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
        return responseResults.responseBySuccess("ok",productService.findByStatus(current,pageSize,status));
    }
    @ApiOperation(value = "status",httpMethod = "GET",notes = "get productListByType",response = ResponseResults.class)
    @GetMapping("/queryType/{status}/{name}")
    public ResponseResults getProductListByType(@RequestParam(value = "current",defaultValue = "0") int current,
                                                @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                                @PathVariable int status,@PathVariable String name){
        return responseResults.responseBySuccess("ok",productService.findByName(current,pageSize,status,name));
    }


}
