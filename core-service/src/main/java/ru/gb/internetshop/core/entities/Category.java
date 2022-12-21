package ru.gb.internetshop.core.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @Column(name="id")
    private Long id;


    @Column(name="title")
    private String title;

    @OneToMany(mappedBy = "category")
    private List<Product> products;
}
