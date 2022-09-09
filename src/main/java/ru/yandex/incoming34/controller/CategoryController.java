package ru.yandex.incoming34.controller;

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
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all_categories_with__goods")
    public List<CategoryFull> getAllCategories() {
        List<CategoryFull> categoryFullList = new ArrayList<>();
        categoryService.getAllCategories().forEach(categoryFullList::add);
        return categoryFullList;
    }

    @GetMapping("/all-brief-categories")
    public List<CategoryBrief> getAllBriefCategories() {
        List<CategoryBrief> categoryBriefList = new ArrayList<>();
        categoryService.getAllBriefCategories().forEach(categoryBriefList::add);
        return categoryBriefList;
    }

    @PostMapping("category")
    public void createCategory(CategoryBriefDto categoryBriefDto){
        categoryService.createCategory(categoryBriefDto);
    }

    @DeleteMapping("category")
    public void removeCategory(Long id){
        categoryService.removeCategory(id);
    }

    @PutMapping("category")
    public void refreshCategory(Long categoryId, CategoryBriefDto categoryBriefDto){
        categoryService.refreshCategory(categoryId, categoryBriefDto);
    }

}
