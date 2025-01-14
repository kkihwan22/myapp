package com.myapp.shipment.service;

import com.myapp.shipment.model.CheckoutRequest;
import com.myapp.shipment.repository.ShipmentCheckoutRepository;
import com.myapp.shipment.service.entity.ShipmentCheckoutEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ShipmentService {

    private final ShipmentCheckoutRepository shipmentCheckoutRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    public Long saveCheckOutData(CheckoutRequest request) {
        ShipmentCheckoutEntity entity = shipmentCheckoutRepository.save(modelMapper.map(request, ShipmentCheckoutEntity.class));
        return entity.getShipmentId();
    }
}
