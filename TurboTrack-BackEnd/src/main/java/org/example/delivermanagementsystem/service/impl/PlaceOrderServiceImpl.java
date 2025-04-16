package org.example.delivermanagementsystem.service.impl;

import org.example.delivermanagementsystem.dto.PlaceOrderDTO;
import org.example.delivermanagementsystem.entity.PlaceOrder;
import org.example.delivermanagementsystem.repo.PlaceOrderRepository;
import org.example.delivermanagementsystem.service.PlaceOrderService;
import org.example.delivermanagementsystem.utill.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {
    @Autowired
    private PlaceOrderRepository placeOrderRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public int placeOrder(PlaceOrderDTO placeOrderDTO) {
        placeOrderRepository.save(modelMapper.map(placeOrderDTO, PlaceOrder.class));
        return VarList.Created;
    }

    @Override
    public List<PlaceOrderDTO> getPendingOrders() {
        List<PlaceOrder> placeOrders = placeOrderRepository.findAllByStatus("PENDING");
        return modelMapper.map(placeOrders, new TypeToken<List<PlaceOrderDTO>>() {}.getType());
    }

    @Override
    public void confirmOrder(UUID id) {
        PlaceOrder placeOrder = placeOrderRepository.findById(id).orElse(null);
        placeOrder.setStatus("CONFIRMED");
        placeOrderRepository.save(placeOrder);
    }

    @Override
    public List<PlaceOrderDTO> getAllOrders() {
        return modelMapper.map(placeOrderRepository.findAll(), new TypeToken<List<PlaceOrderDTO>>() {}.getType());
    }


    @Override
    public List<PlaceOrderDTO> getOrdersByCustomerId(UUID cid) {
        List<PlaceOrder> orders = placeOrderRepository.findByCustomer_cid(cid);
        return orders.stream()
                .map(order -> modelMapper.map(order, PlaceOrderDTO.class))
                .collect(Collectors.toList());
    }



}
