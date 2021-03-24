package com.pentax.ecommerce.services;

import com.pentax.ecommerce.dtos.ProductDTO;
import com.pentax.ecommerce.exceptions.ProductException;
import com.pentax.ecommerce.models.Product;
import com.pentax.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServicesImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public void addProduct(ProductDTO productDTO) {
        Product productToSave = ProductDTO.unpackDTO(productDTO);
        addNewProduct(productToSave);

    }

    private void addNewProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void removeProduct(String productId) throws ProductException {
        Product productToRemove = findAProductById(productId);
        removeProduct(productToRemove);

    }

    private void removeProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public ProductDTO findProductById(String productId) {
        return null;
    }

    private Product findAProductById(String productId) throws ProductException {
        Optional<Product> productOptional = productRepository.findProductById(productId);
        if (productOptional.isPresent()) {
            return productOptional.get();
        } else {
            throw new ProductException("No product found with that id");
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return null;
    }

    @Override
    public ProductDTO updateProduct(String productId, ProductDTO updatedProductDetails) throws ProductException {
        Product productToUpdate = findAProductById(productId);
        if (!productToUpdate.getDescription().equals(updatedProductDetails.getDescription())) {
            productToUpdate.setDescription(updatedProductDetails.getDescription());
        }
        if (!productToUpdate.getPrice().equals(updatedProductDetails.getPrice())) {
            productToUpdate.setPrice(updatedProductDetails.getPrice());
        }
        if (!productToUpdate.getImage().equals(updatedProductDetails.getImage())) {
            productToUpdate.setImage(updatedProductDetails.getImage());
        }
        Product updatedProduct = saveProduct(productToUpdate);
        return ProductDTO.packDTO(updatedProduct);
    }

    private Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}
