package edu.wat.tim.lab1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private List<CartEntity> cartEntities = new ArrayList<>();

    public ClientEntity(String name) {
        this.name = name;
    }

}