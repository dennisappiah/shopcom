package com.shopping_mart.shoppy.services.impl;

import com.shopping_mart.shoppy.dtos.GetOrderResponse;
import com.shopping_mart.shoppy.entities.Order;
import com.shopping_mart.shoppy.entities.User;
import com.shopping_mart.shoppy.repository.OrderRepository;
import com.shopping_mart.shoppy.repository.UserRepository;
import com.shopping_mart.shoppy.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;

    private final ModelMapper orderMapper;

    @Override
    public GetOrderResponse submitOrder(String username) {
        User user = userRepository.findByUsername(username);

       Order order =  Order.createOrderFromCart(user.getCart());

       return orderMapper.map(order, GetOrderResponse.class);
    }

    @Override
    public List<GetOrderResponse> findAllOrdersByUser(String username) {
        User user = userRepository.findByUsername(username);

        return orderRepository.findAllByUser(user)
                .stream().map(order -> orderMapper.map(order, GetOrderResponse.class))
                .collect(Collectors.toList());
    }

}
