package edu.wat.tim.lab1.controller;

import edu.wat.tim.lab1.model.CartEntity;
import edu.wat.tim.lab1.model.CartProductsEntity;
import edu.wat.tim.lab1.model.ClientEntity;
import edu.wat.tim.lab1.model.ProductEntity;
import edu.wat.tim.lab1.repository.ProductEntityRepository;
import edu.wat.tim.lab1.service.SampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleRestController {

    private final SampleService service;

    @GetMapping("/echo")
    public String echo(String value) {
        return value;
    }

    @GetMapping("/echo/{value}")
    public String echoPath(@PathVariable String value) {
        return value;
    }

    @PostMapping("/client")
    public ResponseEntity<ClientEntity> createClientEntity(@RequestBody ClientEntity entity) {
        ClientEntity savedEntity = service.createClientEntity(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @GetMapping("/client")
    public ResponseEntity<List<ClientEntity>> getAllEntities() {
        List<ClientEntity> entities = service.getAllEntities();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @PostMapping("/client/{id}/cart")
    public ResponseEntity<CartEntity> addCartEntity(@RequestBody CartEntity entity, @PathVariable(value = "id") Long id) {
        CartEntity savedEntity = service.addCartEntity(entity, id);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

    @DeleteMapping("/client/cart/{id}")
    public ResponseEntity<HttpStatus> deleteCartEntity(@PathVariable(value = "id") Long id) {
        service.deleteCartEntity(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<ClientEntity> updateEntity(@RequestBody ClientEntity entity, @PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(service.updateEntity(entity, id), HttpStatus.OK);
    }

    // wyszukiwanie po nazwie
    @GetMapping("/products/{name}")
    public ResponseEntity<List<ProductEntity>> searchProductsByName(@PathVariable(value = "name") String name) {
        List<ProductEntity> products = service.searchProductsByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //dodawanie do koszyka
    @PostMapping("/cart/{cartId}/product/{productId}/{quantity}")
    public ResponseEntity<ProductEntity> addProductToCart(@PathVariable(value = "cartId") Long cartId, @PathVariable(value = "productId") Long productId, @PathVariable(value = "quantity") Integer quantity) {
        ProductEntity newProduct = service.addProductToCart(cartId, productId, quantity);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    //usuwanie z koszyka
    @DeleteMapping("/client/cart/{cartId}/product/{productId}")
    public ResponseEntity<HttpStatus> removeProductFromCart(@PathVariable(value = "cartId") Long cartId,
                                                            @PathVariable(value = "productId") Long productId) {
        service.deleteProductFromCart(cartId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //aktualizacja
    @PutMapping("/cart/{cartId}/product/{productId}/{newQuantity}")
    public ResponseEntity<CartProductsEntity> updateCart(@PathVariable(value = "cartId") Long cartId,
                                                         @PathVariable(value = "productId") Long productId,
                                                         @PathVariable(value = "newQuantity") Integer newQuantity){
        return new ResponseEntity<>(service.updateCartProductsEntity(cartId, productId, newQuantity), HttpStatus.OK);
    }


}
