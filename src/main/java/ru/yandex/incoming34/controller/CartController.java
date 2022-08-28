package ru.yandex.incoming34.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.incoming34.dto.CartDto;
import ru.yandex.incoming34.service.CartService;

@RestController
@RequestMapping("api/v1/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping()
    public void addProduct(Long id){

        cartService.addProduct(id);
    }

    @GetMapping()
    public CartDto getContent(){
        return cartService.getContent();
    }

    @DeleteMapping()
    public void removeProduct(Long id){
        cartService.removeProduct(id);
    }

    @DeleteMapping("/clear")
    public void clearCart(){
        cartService.clearCart();
    }


}
