package com.example.demo.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class LineItem {
    @Id
    int id;
    int quantity, orderId;
    double lineItemTotal;


}
