package ru.yandex.incoming34.dto;

import java.util.List;

public class NewProductDto {

    private String name;
    private Integer price;
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
