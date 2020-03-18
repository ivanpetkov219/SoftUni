package springdata.automapping.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import springdata.automapping.repositories.OrderRepository;
import springdata.automapping.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }
}
