package ru.yandex.incoming34.test;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import ru.yandex.incoming34.components.Cart;
import ru.yandex.incoming34.entities.product.ProductBrief;
import ru.yandex.incoming34.repo.ProductBriefRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Component
class CartTest {

    @Test
    void getCartTotalPrice() {
    }

    @Test
    void addProductTest() {
        ProductBriefRepo mockRepo = Mockito.mock(ProductBriefRepo.class);
        Mockito.when(mockRepo.findById(1L)).thenReturn(justPencil());
        Mockito.when(mockRepo.findById(2L)).thenReturn(pen());
        Mockito.when(mockRepo.findById(3L)).thenReturn(colourPencil());
        Optional<ProductBrief> pencil = mockRepo.findById(1L);
        Optional<ProductBrief> pen = mockRepo.findById(2L);
        Optional<ProductBrief> colPen = mockRepo.findById(3L);
        
        System.out.println(pencil);
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