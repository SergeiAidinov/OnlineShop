package ru.yandex.incoming34.service;

import org.springframework.stereotype.Service;
import ru.yandex.incoming34.components.Cart;
import ru.yandex.incoming34.dto.CartDto;
import ru.yandex.incoming34.entities.product.ProductBrief;
import ru.yandex.incoming34.repo.ProductBriefRepo;

import java.util.Optional;

@Service
public class CartService {

    private final Cart cart;
    private final Convertor convertor;
    private final ProductBriefRepo productBriefRepo;

    public CartService(Cart cart, Convertor convertor, ProductBriefRepo productBriefRepo) {
        this.cart = cart;
        this.convertor = convertor;
        this.productBriefRepo = productBriefRepo;
    }

    public void addProduct(Long id) {
        Optional<ProductBrief> productBriefOptional = productBriefRepo.findById(id);
        if (productBriefOptional.isPresent()) {
            cart.addProduct(productBriefOptional.get());
        }
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
