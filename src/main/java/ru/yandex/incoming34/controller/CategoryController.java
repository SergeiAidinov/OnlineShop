package ru.yandex.incoming34.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.incoming34.dto.CategoryBriefDto;
import ru.yandex.incoming34.entities.category.CategoryBrief;
import ru.yandex.incoming34.entities.category.CategoryFull;
import ru.yandex.incoming34.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@Tag(name = "Контроллер категорий товаров", description = "Позволяет управлять категориями товаров")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all_categories_with__goods")
    @Operation(
            summary = "Список категорий с товарами",
            description = "Позволяет получить все категории и товары, относящиеся к каждой категории"
    )
    public List<CategoryFull> getAllCategories() {
        List<CategoryFull> categoryFullList = new ArrayList<>();
        categoryService.getAllCategories().forEach(categoryFullList::add);
        return categoryFullList;
    }

    @GetMapping("/all-brief-categories")
    @Operation(
            summary = "Список категорий без товаров",
            description = "Позволяет получить все категории"
    )
    public List<CategoryBrief> getAllBriefCategories() {
        List<CategoryBrief> categoryBriefList = new ArrayList<>();
        categoryService.getAllBriefCategories().forEach(categoryBriefList::add);
        return categoryBriefList;
    }

    @PostMapping("category")
    @Operation(
            summary = "Создание категории",
            description = "Позволяет создать новую категорию"
    )
    public void createCategory(@RequestBody CategoryBriefDto categoryBriefDto) {

        categoryService.createCategory(categoryBriefDto);
    }

    @DeleteMapping("category")
    @Operation(
            summary = "Удаление категории",
            description = "Позволяет удалить категорию"
    )
    public void removeCategory(@RequestParam(name = "ID удаляемой категории", defaultValue = "3") Long id) {
        categoryService.removeCategory(id);
    }

    @PutMapping("category")
    @Operation(
            summary = "Переименование категории",
            description = "Позволяет переименовать категорию"
    )
    public void refreshCategory(
            @RequestParam(name = "ID категории", defaultValue = "1") Long categoryId,
            @RequestParam(name = "Новое наименование категории", defaultValue = "Принадлежности для письма") String newCategoryName
    ) {
        categoryService.refreshCategory(categoryId, newCategoryName);
    }


}
