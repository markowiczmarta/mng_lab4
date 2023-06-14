package edu.wat.tim.lab1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "measure")
    private String unitOfMeasure;


    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL)
    private List<CartProductsEntity> positionEntities = new ArrayList<>();
}