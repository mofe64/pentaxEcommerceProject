package com.pentax.ecommerce.controllers;


import com.pentax.ecommerce.dtos.ApiResponse;
import com.pentax.ecommerce.dtos.ProductDTO;
import com.pentax.ecommerce.exceptions.ProductException;
import com.pentax.ecommerce.models.Product;
import com.pentax.ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = ".", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping("/new")
    public ResponseEntity<?> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        System.out.println(productDTO);
        productService.addProduct(productDTO);
        return new ResponseEntity<>(new ApiResponse(true, "Product created successfully"),
                HttpStatus.CREATED);
    }

    @DeleteMapping("{productId}")
    public ResponseEntity<?> removeProduct(@Valid @PathVariable String productId) {
        System.out.println(productId);
        try {
            productService.removeProduct(productId);
            return new ResponseEntity<>(new ApiResponse(true, "product removed successfully"),
                    HttpStatus.NO_CONTENT);

        } catch (ProductException productException) {
            return new ResponseEntity<>(new ApiResponse(false, productException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("{productId}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO updatedProductDetails, @PathVariable String productId) {
        System.out.println(updatedProductDetails);
        try {
            ProductDTO updatedProduct = productService.updateProduct(productId, updatedProductDetails);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);

        } catch (ProductException productException) {
            return new ResponseEntity<>(new ApiResponse(false, productException.getMessage()), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("{productId}")
    public ResponseEntity<?> findProductById(@RequestBody @PathVariable String productId) {
        System.out.println(productId);
        try {
            ProductDTO productDTO = productService.findProductById(productId);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } catch (ProductException productException) {
            return new ResponseEntity<>(new ApiResponse(false,productException.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }

    }

}