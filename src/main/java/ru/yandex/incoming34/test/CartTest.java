package ru.yandex.incoming34.test;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import ru.yandex.incoming34.components.Cart;
import ru.yandex.incoming34.entities.product.ProductBrief;
import ru.yandex.incoming34.repo.ProductBriefRepo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
//@Configuration
//@PropertySource(value = "classpath:SpringHomeWork3.properties")
//@SpringBootTest
@ComponentScan(basePackageClasses = {Cart.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartTest {

    //@Autowired
    Cart testCart = new Cart()
            ;

    private final Map<ProductBrief, Integer> sampleMap = new HashMap<>();

    @BeforeAll
    public void populateSampleMap() {
        sampleMap.put(justPencil().get(), 2);
        sampleMap.put(colourPencil().get(), 1);
        sampleMap.put(pen().get(),1);
    }

    @Test
    void getCartTotalPrice() {
        Assertions.assertEquals(testCart.getCartTotalPrice(), 13);
    }

    @Test
    void addProductTest() {
        ProductBriefRepo mockRepo = Mockito.mock(ProductBriefRepo.class);
        Mockito.when(mockRepo.findById(1L)).thenReturn(justPencil());
        Mockito.when(mockRepo.findById(2L)).thenReturn(pen());
        Mockito.when(mockRepo.findById(3L)).thenReturn(colourPencil());
        testCart.addProduct(mockRepo.findById(1L).get());
        testCart.addProduct(mockRepo.findById(1L).get());
        testCart.addProduct(mockRepo.findById(2L).get());
        testCart.addProduct(mockRepo.findById(3L).get());

        MapDifference<ProductBrief, Integer> mapDifference = Maps.difference(sampleMap, testCart.getProductBriefQuantityMap());
        Assertions.assertTrue(mapDifference.areEqual());
    }

    @Test
    void removeProduct() {
        testCart.removeProduct(justPencil().get());
        sampleMap.put(justPencil().get(), 1);
        MapDifference<ProductBrief, Integer> mapDifference = Maps.difference(sampleMap, testCart.getProductBriefQuantityMap());
        Assertions.assertTrue(mapDifference.areEqual());

    }

    @Test
    void xlearCart() {
        testCart.clearCart();
        Assertions.assertEquals(testCart.getCartTotalPrice(), 0);
        Assertions.assertEquals(testCart.getProductBriefQuantityMap(), Collections.EMPTY_MAP);
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