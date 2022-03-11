package ru.netology.dao.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private java.sql.Timestamp date;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String productName;
    private int amount;
}
