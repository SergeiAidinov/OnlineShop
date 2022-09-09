package ru.yandex.incoming34.repo;

import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.incoming34.entities.category.CategoryFull;

@PropertySource("classpath:SpringHomeWork3.properties")
@Repository
public  interface CategoryFullRepo extends CrudRepository <CategoryFull, Long>{

    @Query(nativeQuery = true, value = "SELECT category_id, category_name FROM category")
    Iterable<CategoryFull> findAllCategories();
/*
    @Query(nativeQuery = true, value = "SELECT category_id, category_name FROM category")
    Iterable<CategoryFull> findAllBriefCategories();



    @Query(nativeQuery = true, value = "SELECT category_id, category_name FROM category WHERE category_id = ?1")
    CategoryFull findBriefCategoryById(Long id);

 */

}
