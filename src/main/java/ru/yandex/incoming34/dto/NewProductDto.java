package ru.yandex.incoming34.dto;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Api
public class NewProductDto {

    @Schema(description = "Наименование продукта", example = "Карандаш", defaultValue = "Новый продукт")
    private String name;

    @Schema(description = "Цена продукта", example = "3", defaultValue = "0")
    private Integer price;

    @Schema(description = "Список категорий", example = "[1, 2]")
    private List<Long> categoriesNumberList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<Long> getCategoriesNumberList() {
        return categoriesNumberList;
    }

    public void setCategoriesNumberList(List<Long> categoriesNumberList) {
        this.categoriesNumberList = categoriesNumberList;
    }
}
