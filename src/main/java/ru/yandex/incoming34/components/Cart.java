package ru.yandex.incoming34.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.incoming34.entities.product.ProductBrief;
import ru.yandex.incoming34.repo.ProductBriefRepo;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class Cart {

    private Integer cartTotalPrice = 0;
    private final Map<ProductBrief, Integer> productBriefQuantityMap = new HashMap<>();
    private final ProductBriefRepo productBriefRepo;

    @Autowired
    public Cart(ProductBriefRepo productBriefRepo) {
        this.productBriefRepo = productBriefRepo;
    }

    public Integer getCartTotalPrice() {
        return cartTotalPrice;
    }

    public Map<ProductBrief, Integer> getProductBriefQuantityMap() {
        return productBriefQuantityMap;
    }

    public void addProduct(Long id) {
        Optional<ProductBrief> productBriefOptional = productBriefRepo.findById(id);
        if (productBriefOptional.isPresent()) {
            if (productBriefQuantityMap.containsKey(productBriefOptional.get())) {
                Integer newQuantity = productBriefQuantityMap.get(productBriefOptional.get()) + 1;
                productBriefQuantityMap.put(productBriefOptional.get(), newQuantity);
            } else {
                productBriefQuantityMap.put(productBriefOptional.get(), 1);
            }
            cartTotalPrice = calculateCartTotalCartPrice();
        }

    }

    public void removeProduct(Long id) {

        AtomicReference<ProductBrief> productBriefToBeDeletedAtomicReference = new AtomicReference<>();

        productBriefQuantityMap
                .forEach((productBrief, quality) -> {
                    if (productBrief.getId().equals(id)) {
                        productBriefToBeDeletedAtomicReference.set(productBrief);
                    }
                });

        if (Objects.nonNull(productBriefToBeDeletedAtomicReference.get())) {
            ProductBrief productBriefToBeDeleted = productBriefToBeDeletedAtomicReference.get();

            if (productBriefQuantityMap.get(productBriefToBeDeleted) == 1) {
                productBriefQuantityMap.remove(productBriefToBeDeleted);
            } else {
                Integer newQuantity = productBriefQuantityMap.get(productBriefToBeDeleted) - 1;
                productBriefQuantityMap.put(productBriefToBeDeleted, newQuantity);
            }

            cartTotalPrice = calculateCartTotalCartPrice();
        }

    }

    private Integer calculateCartTotalCartPrice() {
        AtomicInteger newTotalPrice = new AtomicInteger();
        productBriefQuantityMap.forEach((productBrief, quantity) -> {
            int positionPrice = productBrief.getPrice() * quantity;
            newTotalPrice.addAndGet(positionPrice);
        });

        return newTotalPrice.get();
    }

    public void clearCart() {
        cartTotalPrice = 0;
        productBriefQuantityMap.clear();
    }
}
