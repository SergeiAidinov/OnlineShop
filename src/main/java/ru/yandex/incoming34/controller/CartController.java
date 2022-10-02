package ru.yandex.incoming34.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.yandex.incoming34.dto.CartDto;
import ru.yandex.incoming34.service.CartService;

@RestController
@RequestMapping("api/v1/cart")
@Tag(name = "Контроллер корзины", description = "Позволяет управлять корзиной")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping()
    @Operation(summary = "Добавляет товар в корзину", description = "Товар добавляется по его идентификатору")
    public void addProduct(@RequestParam(name = "Идентификатор товара", defaultValue = "1") Long id) {

        cartService.addProduct(id);
    }

    @GetMapping()
    @Operation(summary = "Отображает содержимое корзины",
            description = "Отображаются индентификатор, наименование и цена кажого товара, количесвто товаров каждого наименования" +
                    "и общая стоимость товаров в корзине")
    public CartDto getContent() {
        return cartService.getContent();
    }

    @DeleteMapping()
    @Operation(summary = "Удаляет товар из корзины", description = "Товар удаляется по его идентификатору")
    public void removeProduct(@RequestParam(name = "Идентификатор товара") Long id) {
        cartService.removeProduct(id);
    }

    @DeleteMapping("/clear")
    @Operation(summary = "Полностью очищает корзину", description = "Удаляет все товары из корзины")
    public void clearCart() {
        cartService.clearCart();
    }


}
