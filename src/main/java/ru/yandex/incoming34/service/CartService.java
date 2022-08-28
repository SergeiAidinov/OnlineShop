package ru.yandex.incoming34.service;

import org.springframework.stereotype.Service;
import ru.yandex.incoming34.components.Cart;
import ru.yandex.incoming34.dto.CartDto;

@Service
public class CartService {

    private final Cart cart;
    private final Convertor convertor;

    public CartService(Cart cart, Convertor convertor) {
        this.cart = cart;
        this.convertor = convertor;
    }

    public void addProduct(Long id) {
        cart.addProduct(id);
    }

    public CartDto getContent() {
        CartDto cartDto = convertor.convertCartToCartDto(cart);
        return cartDto;
    }


    public void removeProduct(Long id) {
        cart.removeProduct(id);
    }

    public void clearCart() {
        cart.clearCart();
    }
}
