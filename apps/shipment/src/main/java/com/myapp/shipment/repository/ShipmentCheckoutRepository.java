package com.myapp.shipment.repository;

import com.myapp.shipment.service.entity.ShipmentCheckoutEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentCheckoutRepository extends JpaRepository<ShipmentCheckoutEntity, Long> {
}
