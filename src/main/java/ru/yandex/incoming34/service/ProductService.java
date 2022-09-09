package ru.yandex.incoming34.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.incoming34.dto.NewProductDto;
import ru.yandex.incoming34.dto.ProductBriefDto;
import ru.yandex.incoming34.dto.ProductFullDto;
import ru.yandex.incoming34.entities.Link;
import ru.yandex.incoming34.entities.category.CategoryBrief;
import ru.yandex.incoming34.entities.product.ProductFull;
import ru.yandex.incoming34.repo.LinkRepo;
import ru.yandex.incoming34.repo.ProductBriefRepo;
import ru.yandex.incoming34.repo.ProductFullRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductBriefRepo productBriefRepo;
    private final ProductFullRepo productFullRepo;
    private final CategoryService categoryService;
    private final LinkRepo linkRepo;
    private final Convertor convertor;

    @Autowired
    public ProductService(ProductBriefRepo productBriefRepo, ProductFullRepo productFullRepo, CategoryService categoryService, LinkRepo linkRepo, Convertor convertor) {
        this.productBriefRepo = productBriefRepo;
        this.productFullRepo = productFullRepo;
        this.categoryService = categoryService;
        this.linkRepo = linkRepo;
        this.convertor = convertor;

    }

    public List<ProductBriefDto> showAllBriefProducts() {
        return productBriefRepo.findAllBriefProducts()
                .stream()
                .map(productBrief -> convertor.convertProductBriefToDto(productBrief))
                .collect(Collectors.toList());
    }

    public List<ProductFullDto> showAllProductsWithCategories() {
        return productFullRepo.findAllFullProducts()
                .stream().map(productFull -> convertor.convertProductFullToDto(productFull))
                .collect(Collectors.toList());
    }

    public Optional<ProductFullDto> getProductFullById(Long id) {
        return productFullRepo.findProductFullById(id).map(convertor::convertProductFullToDto);
    }

    public void putProduct(NewProductDto newProductDto) {
        ProductFull productFull = createProductFull(newProductDto);
        productFullRepo.save(productFull);
    }

    private ProductFull createProductFull(NewProductDto newProductDto) {
        ProductFull productFull = convertor.convertNewProductToProductFull(newProductDto);
        List<CategoryBrief> categoryBriefList = categoryService.getAllBriefCategoriesByIds(newProductDto.getCategoriesNumberList());
        productFull.setCategoryBriefList(categoryBriefList);
        return  productFull;
    }

    public void removeProductById(Long id) {
        productFullRepo.deleteById(id);
    }

    public void modifyProduct(Long productId, NewProductDto newProductDto) {
        ProductFull productFull = convertor.convertNewProductToProductFull(newProductDto);
        List<CategoryBrief> categoryBriefList;
        if (Objects.isNull(newProductDto.getCategoriesNumberList()) || newProductDto.getCategoriesNumberList().isEmpty()){
            categoryBriefList = Collections.EMPTY_LIST;
        } else {
            categoryBriefList = categoryService.getAllBriefCategoriesByIds(newProductDto.getCategoriesNumberList());
        }
        productFull.setCategoryBriefList(categoryBriefList);
        productFullRepo.updateProductFull(productId, productFull.getName(), productFull.getPrice());
        linkRepo.deleteAllByProductId(productId);
        if (!categoryBriefList.isEmpty()) {
            linkRepo.saveAll(compileLinkList(productId, categoryBriefList));
        }
    }

    private List<Link> compileLinkList(Long productId, List<CategoryBrief> categoryBriefList) {
        List<Link> linkList = new ArrayList<>();
        categoryBriefList.forEach(categoryBrief -> {
            Link link = new Link();
            link.setProductId(productId);
            link.setCategoryId(categoryBrief.getId());
            linkList.add(link);
        });
        return linkList;
    }
}
