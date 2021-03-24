package com.pentax.ecommerce.services;


import com.pentax.ecommerce.dtos.ProductDTO;
import com.pentax.ecommerce.exceptions.ProductException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    public void addProduct(ProductDTO productDTO);
    public void removeProduct(String productId) throws ProductException;
    public ProductDTO findProductById(String productId);
    public List<ProductDTO> getAllProducts();
    public ProductDTO updateProduct(String productId ,ProductDTO updatedProductDetails) throws ProductException;
//    public List<ProductDTO> getAllProductsInACategory() TODO

}
