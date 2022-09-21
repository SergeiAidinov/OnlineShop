package ru.yandex.incoming34.test;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.stereotype.Component;
import ru.yandex.incoming34.components.Cart;
import ru.yandex.incoming34.entities.product.ProductBrief;
import ru.yandex.incoming34.repo.ProductBriefRepo;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartTest {

    private final Map<ProductBrief, Integer> sampleMap = new HashMap<>();
    Cart testCart = new Cart();

    @BeforeAll
    public void populateSampleMap() {
        sampleMap.put(justPencil().get(), 2);
        sampleMap.put(colourPencil().get(), 1);
        sampleMap.put(pen().get(), 1);
    }

    @Test
    @Order(2)
    void getCartTotalPriceTest() {
        Assertions.assertEquals(testCart.getCartTotalPrice(), 13);
    }

    @Test
    @Order(1)
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
    @Order(3)
    void removeProductTest() {
        testCart.removeProduct(justPencil().get());
        sampleMap.put(justPencil().get(), 1);
        MapDifference<ProductBrief, Integer> mapDifference = Maps.difference(sampleMap, testCart.getProductBriefQuantityMap());
        Assertions.assertTrue(mapDifference.areEqual());

    }

    @Test
    @Order(4)
    void clearCartTest() {
        testCart.clearCart();
        Assertions.assertEquals(testCart.getCartTotalPrice(), 0);
        Assertions.assertEquals(testCart.getProductBriefQuantityMap(), Collections.EMPTY_MAP);
    }

    private Optional<ProductBrief> justPencil() {
        ProductBrief pencil = new ProductBrief();
        pencil.setId(1L);
        pencil.setName("Простой карандаш");
        pencil.setPrice(2);
        return Optional.of(pencil);
    }

    private Optional<ProductBrief> colourPencil() {
        ProductBrief colourPencil = new ProductBrief();
        colourPencil.setId(3L);
        colourPencil.setName("Цветной карандаш");
        colourPencil.setPrice(4);
        return Optional.of(colourPencil);
    }

    private Optional<ProductBrief> pen() {
        ProductBrief pen = new ProductBrief();
        pen.setId(2L);
        pen.setName("Ручка");
        pen.setPrice(5);
        return Optional.of(pen);
    }

}