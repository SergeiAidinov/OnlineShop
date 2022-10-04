package ru.yandex.incoming34.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.incoming34.dto.NewProductDto;
import ru.yandex.incoming34.dto.ProductBriefDto;
import ru.yandex.incoming34.dto.ProductFullDto;
import ru.yandex.incoming34.service.ProductService;

import java.util.List;

@Tag(name = "Контроллер товаров",
		description = "Позволяет создавать, редактировать и удалять товары, а также привязывать их к категориям")
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/all_brief_products")
	@ApiOperation(value = "Показывает все товары без категорий")
	public List<ProductBriefDto> showProducts() {
				return productService.showAllBriefProducts();
	}

	@GetMapping("/all_products_with_categories")
	@ApiOperation(value = "Показывает все товары с категориями")
	public List<ProductFullDto> showProductsWithCategories() {
		return productService.showAllProductsWithCategories();
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Удаляет продукт по его идентификатору")
	public void deleteProduct(@PathVariable Long id) {

		productService.removeProductById(id);
	}

	@PostMapping()
	@ApiOperation(value = "Форма для создания нового продукта")
	public HttpStatus putProduct(@RequestBody NewProductDto newProductDto) {
		productService.putProduct(newProductDto);
		return HttpStatus.OK;
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Получение продукта по его идентификатору")
	public ResponseEntity<ProductFullDto> getProduct(@PathVariable Long id) {
		return productService.getProductFullById(id)
				.map(productFullDto -> new ResponseEntity<>(productFullDto, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Изменение продукта")
	public void modifyProduct(@PathVariable Long id, @RequestBody NewProductDto newProductDto){
		productService.modifyProduct(id, newProductDto);
	}

}
