package ru.yandex.incoming34.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.springframework.stereotype.Component;
import ru.yandex.incoming34.components.Cart;
import ru.yandex.incoming34.dto.*;
import ru.yandex.incoming34.entities.category.CategoryBrief;
import ru.yandex.incoming34.entities.product.ProductBrief;
import ru.yandex.incoming34.entities.product.ProductFull;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class Convertor {

    private final ModelMapper productBriefMapper;
    private final ModelMapper productFullMapper;
    private final ModelMapper newCategoryMapper;

    public Convertor() {
        productBriefMapper = new ModelMapper();
        productFullMapper = new ModelMapper();
        newCategoryMapper = new ModelMapper();
    }

    public ProductBriefDto convertProductBriefToDto(ProductBrief productBrief) {

        return productBriefMapper.map(productBrief, ProductBriefDto.class);
    }

    public ProductFullDto convertProductFullToDto(ProductFull productFull) {

        return productFullMapper.map(productFull, ProductFullDto.class);
    }

    public ProductFull convertNewProductToProductFull(NewProductDto newProductDto) {

        return productFullMapper.map(newProductDto, ProductFull.class);
    }

    public CartDto convertCartToCartDto(Cart cart) {
        Map<ProductBriefDto, Integer> productBriefDtoQuantityHashMap = new HashMap<>();
        cart.getProductBriefQuantityMap().forEach((productBrief, quantity) -> {
            ProductBriefDto productBriefDto = convertProductBriefToDto(productBrief);
            productBriefDtoQuantityHashMap.put(productBriefDto, quantity);
        });
        CartDto cartDto = new CartDto();
        cartDto.setMapProductBriefAndQuantity(productBriefDtoQuantityHashMap);
        cartDto.setTotalPrice(cart.getCartTotalPrice());
        return cartDto;
    }

    public CategoryBrief convertCategoryBriefDtoToCategoryBrief(CategoryBriefDto categoryBriefDto) {
        return newCategoryMapper.map(categoryBriefDto, CategoryBrief.class);

    }
}
