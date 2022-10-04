package ru.yandex.incoming34.dto;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.media.Schema;

@Api
public class CategoryBriefDto {

    @Schema(description = "Наименование категории", example = "Принадлежности для рисования", required = true)
    private String catergoryName;

    public String getCatergoryName() {
        return catergoryName;
    }

    public void setCatergoryName(String catergoryName) {
        this.catergoryName = catergoryName;
    }
}
