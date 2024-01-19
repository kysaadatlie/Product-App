package com.example.product_app.service.impl;

import com.example.product_app.shared.dto.ProductDto;
import com.example.product_app.entity.Product;
import com.example.product_app.exception.ResourceNotFoundException;
import com.example.product_app.mapper.ProductMapper;
import com.example.product_app.repository.ProductRepository;
import com.example.product_app.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = ProductMapper.mapToProduct(productDto);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product doesn't exist with the given id: " + productId));
        return ProductMapper.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map((product) -> ProductMapper.mapToProductDto(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto updateProduct(Long productId, ProductDto updatedProduct) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product doesn't exist with the given id: " + productId));

        product.setProductName(updatedProduct.getProductName());
        product.setProductType(updatedProduct.getProductType());
        product.setProductPrice(updatedProduct.getProductPrice());
        product.setProductDescription(updatedProduct.getProductDescription());
        product.setLimited(updatedProduct.isLimited());
        product.setProductRating(updatedProduct.getProductRating());

        Product updatedProductObj = productRepository.save(product);

        return ProductMapper.mapToProductDto(updatedProductObj);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Product doesn't exist with the given id: " + productId));

        productRepository.deleteById(productId);
    }
}
