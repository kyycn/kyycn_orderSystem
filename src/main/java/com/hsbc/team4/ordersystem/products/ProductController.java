package com.hsbc.team4.ordersystem.products;

import com.hsbc.team4.ordersystem.aop.annotations.SysLog;
import com.hsbc.team4.ordersystem.common.adapt.BeanAdapter;
import com.hsbc.team4.ordersystem.common.factory.UUIDFactory;
import com.hsbc.team4.ordersystem.common.utils.BeanValidator;
import com.hsbc.team4.ordersystem.common.utils.ResponseResults;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
        this.responseResults = responseResults;
        this.beanValidator = beanValidator;
        this.beanAdapter = beanAdapter;
        this.uuidFactory = uuidFactory;
    }

    /**
     * getProductList
     *
     * @param page
     * @return product
     */
    @ApiOperation(value = "status", httpMethod = "GET", notes = "get productList", response = ResponseResults.class)
    @GetMapping("get/{page}")
    public ResponseResults getProductList(@PathVariable Integer page) {
        if (page<0) {
            return responseResults.responseByErrorMessage("");
        }
        Page<Product> products = productService.findByStatus(page, 10, 0);
        if (products != null) {
            return responseResults.responseBySuccess("query success!", products);
        }
        return responseResults.responseByErrorMessage("get productList is failed!");
    }
    /**
     * saveProduct
     *
     * @param productDto
     * @return product
     */
    @SysLog("保存产品")
    @ApiOperation(value = "save product", httpMethod = "POST", notes = "save product", response = ResponseResults.class)
    @PostMapping("/save")
    public ResponseResults saveProduct(@ApiParam(required = true, name = "productDto", value = "productDto project") @RequestBody ProductDto productDto) {
        productDto.setId(uuidFactory.getUUID());
        Map<String, String> map = beanValidator.validateObject(productDto);
        if (!CollectionUtils.isEmpty(map)) {
            return responseResults.responseByErrors(map);
        }
        log.info("beanValidatorTest", beanValidator);
        Product product = (Product) beanAdapter.dtoAdapter(productDto, new Product());
        Product product1 = productService.addEntity(product);
        if (product1 != null) {
            return responseResults.responseBySuccess("save success!", productDto);
        }
        return responseResults.responseByErrorMessage("overtime ,please refresh again");
    }

    /**
     * updateProduct
     *
     * @param productDto
     */
    @ApiOperation(value = "update product", httpMethod = "POST", notes = "update product", response = ResponseResults.class)
    @PutMapping("/update")
    public ResponseResults updateProduct(@ApiParam(required = true, name = "productDto", value = "productDto project") @RequestBody ProductDto productDto) {

        Map<String, String> map = beanValidator.validateObject(productDto);
        if (!CollectionUtils.isEmpty(map)) {
            return responseResults.responseByErrors(map);
        }
        log.info("beanValidator test", beanValidator);
        Product product = (Product) beanAdapter.dtoAdapter(productDto, new Product());
        Product product1 = productService.updateEntity(product);
        if (product1 != null) {
            return responseResults.responseBySuccess("update success", productDto);
        }
        return responseResults.responseByErrorMessage("it's overtime!please refresh again");
    }

    /**
     * deleteProductById
     *
     * @param id
     * @return product
     */
    @ApiOperation(value = "delete by Id", httpMethod = "DELETE", notes = "delete product by Id", response = ResponseResults.class)
    @DeleteMapping("delete/{id}")
    public ResponseResults deleteProductById(@ApiParam(required = true, name = "id", value = "the product id") @PathVariable String id) {

        if(StringUtils.isBlank(id)){
            return responseResults.responseByErrorMessage("error!");
        }
        int row = productService.updateStatusById(id, 1);
        if (row > 0) {
            return responseResults.responseBySuccess("delete success!");
        }
        return responseResults.responseByErrorMessage("failure delete,please try again!");
    }

    /**
     * queryProductById
     *
     * @param id
     */
    @ApiOperation(value = "get by Id", httpMethod = "GET", notes = "get product by id", response = ResponseResults.class)
    @GetMapping("/productId/{id}")
    public ResponseResults queryProductById(@ApiParam(required = true, name = "id", value = "the product id") @PathVariable String id) {
        if (StringUtils.isBlank(id)) {
            return responseResults.responseByErrorMessage("id is empty");
        }
        Product product = productService.findById(id);
        if (product != null) {
            return responseResults.responseBySuccess("ok", product);
        }
        return responseResults.responseByErrorMessage("failure query,please checkout your id!");
    }


    /**
     * queryByProductType
     *
     * @param page
     * @param productType
     * @return product
     */
    @ApiOperation(value = "get by productType", httpMethod = "GET", notes = "get productList", response = ResponseResults.class)
    @GetMapping("/queryByProductType/{productType}/{page}")
    public ResponseResults queryByProductType(@PathVariable Integer page, @PathVariable String productType) {
        if (StringUtils.isBlank(productType) || page<0) {
            return responseResults.responseByErrorMessage("error");
        }
        Page<Product> products = productService.findByProductType(page,0, productType);
        if (products != null) {
            return responseResults.responseBySuccess("query success", products);
        }
        return responseResults.responseByErrorMessage("query failed,please try it again!");
    }

    /**
     * queryByProductTypeContains(productType)
     *
     * @param current
     * @param productType
     * @return product
     */
    @ApiOperation(value = "queryByProductTypeContains", httpMethod = "GET", notes = "get productList", response = ResponseResults.class)
    @GetMapping("/queryByProductTypeContains/{productType}")
    public ResponseResults queryByProductTypeContains(@RequestParam(value = "current", defaultValue = "0") int current,
                                                      @PathVariable String productType) {
        if ( StringUtils.isEmpty(productType)) {
            return responseResults.responseByErrorMessage("error");
        }
        Page<Product> products = productService.findByProductTypeContains(current, 10, 0, productType);
        if (products != null) {
            return responseResults.responseBySuccess("ok", products);
        }
        return responseResults.responseByErrorMessage("query failed,please try it again!");
    }

    /**
     * queryByProductNameContains(productName)
     *
     * @param current
     * @param productName
     * @return product
     */
    @ApiOperation(value = "queryByProductNameContains", httpMethod = "GET", notes = "get productList", response = ResponseResults.class)
    @GetMapping("/queryByProductNameContains/{productName}")
    public ResponseResults queryByProductNameContains(@RequestParam(value = "current", defaultValue = "0") int current,
                                                      @PathVariable String productName) {
        if (StringUtils.isEmpty(productName)) {
            return responseResults.responseByErrorMessage("the status is empty!");
        }
        Page<Product> products = productService.findByProductNameContains(current, 10, 0, productName);
        if (products != null) {
            return responseResults.responseBySuccess("ok", products);
        }
        return responseResults.responseByErrorMessage("query failed,please try it again!");
    }

    /**
     * orderByProdcutPrice desc
     *
     * @param current
     * @return product
     */
    @ApiOperation(value = "orderByProductPrice", httpMethod = "GET", notes = "get productList", response = ResponseResults.class)
    @GetMapping("/orderByProductPrice")
    public ResponseResults orderByProductPrice(@PathVariable int current) {
        Page<Product> products = productService.orderByProductPrice(current, 10, 0);
        if (products != null) {
            return responseResults.responseBySuccess("ok", products);
        }
        return responseResults.responseByErrorMessage("show list failed,please try it again!");


    }
}
