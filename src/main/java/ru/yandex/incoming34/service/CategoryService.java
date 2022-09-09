package ru.yandex.incoming34.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.incoming34.dto.CategoryBriefDto;
import ru.yandex.incoming34.entities.category.CategoryBrief;
import ru.yandex.incoming34.entities.category.CategoryFull;
import ru.yandex.incoming34.repo.CategoryBriefRepo;
import ru.yandex.incoming34.repo.CategoryFullRepo;

import java.util.List;

@Service
public class CategoryService {

    private final Convertor convertor;
    private final CategoryFullRepo categoryFullRepo;
    private final CategoryBriefRepo categoryBriefRepo;

    @Autowired
    public CategoryService(Convertor convertor, CategoryFullRepo categoryFullRepo, CategoryBriefRepo categoryBriefRepo) {
        this.convertor = convertor;
        this.categoryFullRepo = categoryFullRepo;
        this.categoryBriefRepo = categoryBriefRepo;
    }

    public List<CategoryFull> getAllCategories() {

        return (List<CategoryFull>) categoryFullRepo.findAllCategories();
    }

    public List<CategoryBrief> getAllBriefCategories() {
        return (List<CategoryBrief>) categoryBriefRepo.findAll();
    }

    public void createCategory(CategoryBriefDto categoryBriefDto) {
        CategoryBrief categoryBrief = convertor.convertCategoryBriefDtoToCategoryBrief(categoryBriefDto);
        categoryBriefRepo.save(categoryBrief);

    }

    public void removeCategory(Long id) {

        categoryBriefRepo.deleteById(id);
    }

    public List<CategoryBrief> getAllBriefCategoriesByIds(List<Long> categoriesNumberList) {
        return (List<CategoryBrief>) categoryBriefRepo.findAllById(categoriesNumberList);
    }

    public void refreshCategory(Long categoryId, CategoryBriefDto categoryBriefDto) {
        //CategoryBrief categoryBrief = convertor.convertCategoryBriefDtoToCategoryBrief(categoryBriefDto);
        categoryBriefRepo.updateCategory(categoryBriefDto.getCatergoryName(), categoryId);
    }
}

