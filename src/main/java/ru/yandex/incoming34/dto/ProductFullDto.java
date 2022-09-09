package ru.yandex.incoming34.dto;

import ru.yandex.incoming34.entities.category.CategoryBrief;

import java.util.List;

public class ProductFullDto extends ProductBriefDto{

    private List<CategoryBriefDto> categoryBriefDtoList;

    public ProductFullDto() {
    }

    public ProductFullDto(List<CategoryBriefDto> categoryBriefList) {
        this.categoryBriefDtoList = categoryBriefList;
    }

    public List<CategoryBriefDto> getCategoryBriefList() {
        return categoryBriefDtoList;
    }

    public void setCategoryBriefList(List<CategoryBriefDto> categoryBriefDtoList) {
        this.categoryBriefDtoList = categoryBriefDtoList;
    }
}
