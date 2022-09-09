package ru.yandex.incoming34.repo;

import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.incoming34.entities.category.CategoryBrief;

@PropertySource("classpath:SpringHomeWork3.properties")
@Repository
public interface CategoryBriefRepo extends CrudRepository<CategoryBrief, Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE category SET category_name = :name WHERE category_id = :id")
    void updateCategory(@Param("name") String name, @Param("id") Long id);

}
