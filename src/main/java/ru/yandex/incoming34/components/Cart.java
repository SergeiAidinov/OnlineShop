package ru.yandex.incoming34.components;

import org.springframework.stereotype.Component;
import ru.yandex.incoming34.entities.product.ProductBrief;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class Cart {

    private Integer cartTotalPrice = 0;
    private final Map<ProductBrief, Integer> productBriefQuantityMap = new HashMap<>();

    public Integer getCartTotalPrice() {
        return cartTotalPrice;
    }

    public Map<ProductBrief, Integer> getProductBriefQuantityMap() {
        return productBriefQuantityMap;
    }

    public void addProduct(ProductBrief productBrief) {

        if (productBriefQuantityMap.containsKey(productBrief)) {
            Integer newQuantity = productBriefQuantityMap.get(productBrief) + 1;
            productBriefQuantityMap.put(productBrief, newQuantity);
        } else {
            productBriefQuantityMap.put(productBrief, 1);
        }

            cartTotalPrice = calculateCartTotalCartPrice();


    }

    public void removeProduct(ProductBrief productBriefToBeDeleted) {

        if (Objects.nonNull(productBriefQuantityMap.get(productBriefToBeDeleted))) {


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
