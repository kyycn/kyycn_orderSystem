package com.hsbc.team4.ordersystem.products;

import com.hsbc.team4.ordersystem.common.adapt.BeanAdapter;
import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
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
    private final UUIDFactory uuidFactory;

    @Autowired
    public ProductController(IProductService productService, ResponseResults responseResults, BeanValidator beanValidator, BeanAdapter beanAdapter, UUIDFactory uuidFactory) {
        this.productService = productService;
        this.responseResults=responseResults;
        this.beanValidator=beanValidator;
        this.beanAdapter=beanAdapter;
        this.uuidFactory = uuidFactory;
    }

    /**
     * saveProduct
     * @param productDto
     * @return product
     */
    @ApiOperation(value = "save product", httpMethod = "POST", notes = "save product", response = ResponseResults.class)
    @PostMapping("/save")
    public ResponseResults saveProduct(@ApiParam(required = true,name = "productDto",value = "productDto project") @RequestBody ProductDto productDto){
        productDto.setId(uuidFactory.getUUID());
        beanValidator.validateObject(productDto);
        log.info("beanValidatorTest",beanValidator);
        Product product= (Product) beanAdapter.dtoAdapter(productDto,new Product());
        Product product1=productService.addEntity(product);
        if(product1!=null){
            return responseResults.responseBySuccess("ok",productDto);
        }
        return responseResults.responseByErrorMessage("overtime ,please refresh again");
    }

    /**
     * updateProduct
     * @param productDto
     */
    @ApiOperation(value = "update product", httpMethod = "POST",notes= "update product", response = ResponseResults.class)
    @PutMapping("/update")
    public ResponseResults updateProduct(@ApiParam(required = true,name = "productDto",value = "productDto project")@RequestBody  ProductDto productDto){
        beanValidator.validateObject(productDto);
        log.info("beanValidator test",beanValidator);
        Product product=(Product) beanAdapter.dtoAdapter(productDto,new Product());
        Product product1=productService.updateEntity(product);
        if(product1!=null){
            return responseResults.responseBySuccess("ok",productDto);
        }
        return responseResults.responseByErrorMessage("it's overtime!please refresh again");
    }

    /**
     * deleteProductById
     * @param id
     * @return product
     */
    @ApiOperation(value = "delete by Id", httpMethod = "DELETE", notes = "delete product by Id", response = ResponseResults.class)
    @DeleteMapping("/{id}")
    public ResponseResults deleteProductById(@ApiParam(required = true,name = "id",value = "the product id")@PathVariable String  id){
        return responseResults.responseBySuccess("ok",productService.updateStatusById(id,1));
    }

    /**
     * queryProductById
     * @param id
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
     * @return product
     */
    @ApiOperation(value = "status", httpMethod = "GET", notes = "get productList", response = ResponseResults.class)
    @GetMapping("/{status}")
    public ResponseResults getProductList(@RequestParam(value = "current",defaultValue = "0") int current,
                                       @RequestParam(value = "current",defaultValue = "10") int pageSize,
                                       @PathVariable int status){
        return responseResults.responseBySuccess("ok",productService.findByStatus(current,pageSize,status));
    }

    /**
     * queryByProductType
     * @param current
     * @param pageSize
     * @param status
     * @param productType
     * @return product
     */
    @ApiOperation(value = "get by productType",httpMethod = "GET",notes = "get productList",response = ResponseResults.class)
    @GetMapping("/queryByProductType/{status}/{productType}")
    public ResponseResults queryByProductType(@RequestParam(value = "current",defaultValue = "0") int current,
                                                @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                                @PathVariable int status,@PathVariable String productType){
        return responseResults.responseBySuccess("ok",productService.findByProductType(current,pageSize,status,productType));
    }

    /**
     * queryByProductTypeContains(productType)
     * @param current
     * @param pageSize
     * @param status
     * @param productType
     * @return product
     */
    @ApiOperation(value = "queryByProductTypeContains",httpMethod ="GET",notes = "get productList",response = ResponseResults.class)
    @GetMapping("/queryByProductTypeContains/{status}/{productType}")
    public ResponseResults queryByProductTypeContains(@RequestParam(value = "current",defaultValue = "0") int current,
                                                     @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                                     @PathVariable int status,@PathVariable String productType){
        return responseResults.responseBySuccess("ok",productService.findByProductTypeContains(current,pageSize,status,productType));
    }

    /**
     * queryByProductNameContains(productName)
     * @param current
     * @param pageSize
     * @param status
     * @param productName
     * @return product
     */
    @ApiOperation(value = "queryByProductNameContains",httpMethod ="GET",notes = "get productList",response = ResponseResults.class)
    @GetMapping("/queryByProductNameContains/{status}/{productName}")
    public ResponseResults queryByProductNameContains(@RequestParam(value = "current",defaultValue = "0") int current,
                                                     @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                                     @PathVariable int status,@PathVariable String productName){
        return responseResults.responseBySuccess("ok",productService.findByProductNameContains(current,pageSize,status,productName));
    }

    /**
     * orderByProdcutPrice desc
     * @param current
     * @param pageSize
     * @param status
     * @return product
     */
    @ApiOperation(value = "orderByProductPrice",httpMethod = "GET",notes = "get productList",response = ResponseResults.class)
    @GetMapping("/orderByProductPrice/{status}")
    public ResponseResults orderByProductPrice(@RequestParam(value = "current",defaultValue = "0") int current,
                                               @RequestParam(value = "pageSize",defaultValue = "5") int pageSize,
                                               @PathVariable int status){
        return responseResults.responseBySuccess("ok",productService.orderByProductPrice(current,pageSize,status));
    }


}
