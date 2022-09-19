package ru.yandex.incoming34.test;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import liquibase.repackaged.org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ru.yandex.incoming34.components.Cart;
import ru.yandex.incoming34.entities.product.ProductBrief;
import ru.yandex.incoming34.repo.ProductBriefRepo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Component
class CartTest {


    Cart testCart = new Cart();

    private final Map<ProductBrief, Integer> sampleMap = new HashMap<>();

    @Test
    void getCartTotalPrice() {
    }

    @Test
    void addProductTest() {
        ProductBriefRepo mockRepo = Mockito.mock(ProductBriefRepo.class);
        Mockito.when(mockRepo.findById(1L)).thenReturn(justPencil());
        Mockito.when(mockRepo.findById(2L)).thenReturn(pen());
        Mockito.when(mockRepo.findById(3L)).thenReturn(colourPencil());
        Optional<ProductBrief> pencilOptional = mockRepo.findById(1L);
        Optional<ProductBrief> penOptional = mockRepo.findById(2L);
        Optional<ProductBrief> colourPenOptional = mockRepo.findById(3L);
        testCart.addProduct(pencilOptional.get());
        testCart.addProduct(pencilOptional.get());
        testCart.addProduct(colourPenOptional.get());
        testCart.addProduct(penOptional.get());

        sampleMap.put(pencilOptional.get(), 2);
        sampleMap.put(colourPenOptional.get(), 1);
        sampleMap.put(penOptional.get(),1);

        MapDifference<ProductBrief, Integer> mapDifference = Maps.difference(sampleMap, testCart.getProductBriefQuantityMap());

        Assertions.assertTrue(mapDifference.areEqual());
    }





    @Test
    void removeProduct() {
    }

    @Test
    void clearCart() {
    }

    private Optional<ProductBrief> justPencil() {
        ProductBrief pencil =  new ProductBrief();
        pencil.setId(1L);
        pencil.setName("Простой карандаш");
        pencil.setPrice(2);
        return Optional.of(pencil);
    }

    private Optional<ProductBrief> colourPencil() {
        ProductBrief colourPencil =  new ProductBrief();
        colourPencil.setId(3L);
        colourPencil.setName("Цветной карандаш");
        colourPencil.setPrice(4);
        return Optional.of(colourPencil);
    }

    private Optional<ProductBrief> pen() {
        ProductBrief pen =  new ProductBrief();
        pen.setId(2L);
        pen.setName("Ручка");
        pen.setPrice(5);
        return Optional.of(pen);
    }




}