package com.example.product_app.mapper;

import com.example.product_app.shared.dto.ProductDto;
import com.example.product_app.entity.Product;

public class ProductMapper {
    public static ProductDto mapToProductDto(Product product){
        return new ProductDto(
                product.getId(),
                product.getProductName(),
                product.getProductType(),
                product.getProductPrice(),
                product.getProductDescription(),
                product.isLimited(),
                product.getProductRating()
        );
    }

    public static Product mapToProduct(ProductDto productDto){
        return new Product(
                productDto.getId(),
                productDto.getProductName(),
                productDto.getProductType(),
                productDto.getProductPrice(),
                productDto.getProductDescription(),
                productDto.isLimited(),
                productDto.getProductRating()
        );
    }
}
