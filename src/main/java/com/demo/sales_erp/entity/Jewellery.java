package com.demo.sales_erp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

//@Document(collection = "jewellery")
@Entity
public class Jewellery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String customerName;
    private String type;
    private String ornament;
    private String salesPerson;
    private double weight;
    private double purity;
    private double price;
    private int quantity;
    private Date purchaseDate;

    // Constructors, getters, and setters
    // ...
}
