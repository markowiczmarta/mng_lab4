package edu.wat.tim.lab1.service;

import edu.wat.tim.lab1.model.CartEntity;
import edu.wat.tim.lab1.model.CartProductsEntity;
import edu.wat.tim.lab1.model.ClientEntity;
import edu.wat.tim.lab1.model.ProductEntity;
import edu.wat.tim.lab1.repository.CartEntityRepository;
import edu.wat.tim.lab1.repository.ClientEntityRepository;
import edu.wat.tim.lab1.repository.ProductEntityRepository;
import edu.wat.tim.lab1.repository.CartProductsEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SampleService {

    private final ClientEntityRepository clientEntityRepository;
    private final CartEntityRepository cartEntityRepository;
    private final ProductEntityRepository productEntityRepository;
    private final CartProductsEntityRepository cartProductsEntityRepository;

    public ClientEntity createClientEntity(ClientEntity entity) {

        return clientEntityRepository.save(entity);
    }

    public List<ClientEntity> getAllEntities() {
        return clientEntityRepository.findAll();
    }

    public CartEntity addCartEntity(CartEntity cartEntity, Long clientId) {
        ClientEntity clientEntity = clientEntityRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + clientId));
        cartEntity.setClientEntity(clientEntity);
        return cartEntityRepository.save(cartEntity);
    }

    public List<ProductEntity> searchProductsByName(String name) {
         return productEntityRepository.getByName(name);
    }

    //dodawanie do koszyka
    public ProductEntity addProductToCart( Long cartId,Long productId, Integer quantity) {
        CartEntity cartEntity = cartEntityRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono koszyka o id " + cartId));

        ProductEntity productEntity = productEntityRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono produktu o id " + productId));

        CartProductsEntity cartProducts = new CartProductsEntity();
        cartProducts.setCartEntity(cartEntity);
        cartProducts.setProductEntity(productEntity);
        cartProducts.setProductQuantity(quantity);
        cartProducts = cartProductsEntityRepository.save(cartProducts);
        return productEntity;
    }


    public void deleteCartEntity(Long cartId) {
        cartEntityRepository.deleteById(cartId);
    }

    public void deleteProductFromCart(Long cartId, Long productId) {
        cartProductsEntityRepository.deleteByCartEntityIdAndProductEntityId(cartId, productId);
    }

    public ClientEntity updateEntity(ClientEntity updatedEntity, Long id) {
        ClientEntity entity = clientEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono encji o id " + id));

        entity.setName(updatedEntity.getName());
        return clientEntityRepository.save(entity);
    }
    public CartProductsEntity updateCartProductsEntity(Long cartId, Long productId, int newQuantity){
        CartProductsEntity cartProductsEntity = cartProductsEntityRepository.findByCartEntityIdAndProductEntityId(cartId, productId);
        if(newQuantity == 0){
            cartProductsEntityRepository.deleteByCartEntityIdAndProductEntityId(cartId, productId);
            return null;
        }
        else if(newQuantity < 0){
            throw new RuntimeException("Ilość produktów nie może być ujemna");
        }
        cartProductsEntity.setProductQuantity(newQuantity);
        return cartProductsEntityRepository.save(cartProductsEntity);
    }

}
