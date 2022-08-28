package ru.yandex.incoming34.repo;

import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.incoming34.dto.NewProductDto;
import ru.yandex.incoming34.entities.category.CategoryBrief;
import ru.yandex.incoming34.entities.product.ProductBrief;
import ru.yandex.incoming34.entities.product.ProductFull;

import java.util.List;
import java.util.Optional;

@PropertySource("classpath:SpringHomeWork3.properties")
@Repository
public interface ProductFullRepo extends CrudRepository<ProductFull, Long>{
String queryForProductById = "SELECT product_id, product_name, price FROM product WHERE product_id = ?1";
String queryForAllProduct = "SELECT product_id, product_name, price FROM product";

	@Query(nativeQuery = true, value = "SELECT COUNT(*) FROM product WHERE product_id = ?1")
	Long countById(Long id);
	
	@Query(nativeQuery = true, value = queryForAllProduct)
	List<ProductFull> findAllFullProducts();

	@Query(nativeQuery = true, value = queryForAllProduct)
	List<ProductBrief> findAllBriefProducts();

	@Query(nativeQuery = true, value = queryForProductById)
	Optional<ProductFull> findProductFullById(Long id);

	@Query(nativeQuery = true, value = queryForProductById)
	Optional<ProductBrief> findProductBriefById(Long id);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "INSERT INTO product (product_name, price) VALUES (:name, :price)")
	void saveProductBrief(@Param("name") String name, @Param("price") Integer price);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE product p SET p.price = :price, p.product_name = :name WHERE p.product_id = :id")
    void updateProductFull(@Param("id") Long id, @Param("name") String name, @Param("price") Integer price);


}
