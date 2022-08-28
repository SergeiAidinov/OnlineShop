package ru.yandex.incoming34.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.incoming34.dto.NewProductDto;
import ru.yandex.incoming34.dto.ProductBriefDto;
import ru.yandex.incoming34.dto.ProductFullDto;
import ru.yandex.incoming34.service.ProductService;

import java.util.List;

@Api
@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/all_brief_products")
	public List<ProductBriefDto> showProducts() {
				return productService.showAllBriefProducts();
	}

	@GetMapping("/all_products_with_categories")
	public List<ProductFullDto> showProductsWithCategories() {
		return productService.showAllProductsWithCategories();
	}

	@DeleteMapping("/{id}")
	public void deleteProduct(@RequestParam Long id) {

		productService.removeProductById(id);
	}

	@PostMapping()
	public HttpStatus putProduct(@RequestBody NewProductDto newProductDto) {
		productService.putProduct(newProductDto);
		return HttpStatus.OK;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductFullDto> getProduct(@RequestParam Long id) {
		return productService.getProductFullById(id)
				.map(productFullDto -> new ResponseEntity<>(productFullDto, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
	}

	@PutMapping("/{id}")
	public void modifyProduct(Long id, NewProductDto newProductDto){
		productService.modifyProduct(id, newProductDto);
	}

}
