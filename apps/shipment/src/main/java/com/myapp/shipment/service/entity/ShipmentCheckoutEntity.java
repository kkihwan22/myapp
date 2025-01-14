package com.myapp.shipment.service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Table(name = "SHIPMENT_CHECKOUT_TABLE")
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ShipmentCheckoutEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long shipmentId;

    @Column(name = "checkOutId")
    private Long checkoutId;

    @Column(name = "memberId")
    private Long memberId;

    @Column(name = "productId")
    private Long productId;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "shippingAddress")
    private String shippingAddress;

    @CreationTimestamp
    @Column(name = "createdAt")
    private Timestamp createdAt;
}
