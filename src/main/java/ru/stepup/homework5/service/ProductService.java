package ru.stepup.homework5.service;

import ru.stepup.homework5.dto.ProductRequest;
import ru.stepup.homework5.dto.ProductResponse;

public interface ProductService {
    public ProductResponse addProduct(ProductRequest productRequest);
}
