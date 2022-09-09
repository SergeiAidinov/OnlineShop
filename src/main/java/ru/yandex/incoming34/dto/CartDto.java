package ru.yandex.incoming34.dto;

import java.util.Map;

public class CartDto {
    private Integer totalPrice;
    private Map<ProductBriefDto, Integer> mapProductBriefDtoAndQuantity;

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Map<ProductBriefDto, Integer> getMapProductBriefDtoAndQuantity() {
        return mapProductBriefDtoAndQuantity;
    }

    public void setMapProductBriefAndQuantity(Map<ProductBriefDto, Integer> mapProductBriefAndQuantity) {
        this.mapProductBriefDtoAndQuantity = mapProductBriefAndQuantity;
    }
}
