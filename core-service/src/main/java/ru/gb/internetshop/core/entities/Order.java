package ru.gb.internetshop.core.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="orders")
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;


    @Column(name="username")
    private String username;

    @Column(name="total_price")
    private int totalPrice;

    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
    private List<OrderItem> items;

}
