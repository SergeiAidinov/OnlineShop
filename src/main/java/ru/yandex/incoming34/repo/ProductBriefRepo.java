package ru.yandex.incoming34.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.incoming34.entities.product.ProductBrief;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductBriefRepo extends CrudRepository<ProductBrief, Long> {

    @Modifying(flushAutomatically = true)
    @Query(nativeQuery = true, value = "SELECT product_id, product_name, price FROM product")
    List<ProductBrief> findAllBriefProducts();
}
