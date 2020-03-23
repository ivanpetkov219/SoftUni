package com.softuni.jsonprocessingexcars.services.impls;

import com.softuni.jsonprocessingexcars.models.dtos.CarViewDto;
import com.softuni.jsonprocessingexcars.models.dtos.SaleViewDto;
import com.softuni.jsonprocessingexcars.models.entities.Part;
import com.softuni.jsonprocessingexcars.models.entities.Sale;
import com.softuni.jsonprocessingexcars.repositories.SaleRepository;
import com.softuni.jsonprocessingexcars.services.CarService;
import com.softuni.jsonprocessingexcars.services.CustomerService;
import com.softuni.jsonprocessingexcars.services.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final CarService carService;
    private final CustomerService customerService;
    private final Random random;
    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, CarService carService, CustomerService customerService, Random random, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.carService = carService;
        this.customerService = customerService;
        this.random = random;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedSales() {
        for (int i = 0; i < 20; i++) {
            Sale sale = new Sale();

            sale.setDiscount(this.setRandomDiscount());
            sale.setCar(this.carService.getRandomCar());
            sale.setCustomers(this.customerService.getRandomCustomer());

            this.saleRepository.saveAndFlush(sale);
        }

    }

    @Override
    public List<SaleViewDto> salesWithDiscount() {
        return this.saleRepository.findAll().stream().map(s -> {
            SaleViewDto saleViewDto = this.modelMapper.map(s, SaleViewDto.class);
            saleViewDto.setCar(this.modelMapper.map(s.getCar(), CarViewDto.class));
            BigDecimal price = new BigDecimal(0);
            for (Part part : s.getCar().getParts()) {
                price = price.add(part.getPrice());
            }
            saleViewDto.setPrice(price);
            saleViewDto.setCustomerName(s.getCustomers().getName());

            saleViewDto.setPriceWithDiscount(price.multiply(BigDecimal.valueOf(1 - saleViewDto.getDiscount())));
            return saleViewDto;
        }).collect(Collectors.toList());

    }

    private Double setRandomDiscount() {
        Double[] discounts = new Double[]{0D, 0.05, 0.1, 0.15, 0.2, 0.3, 0.4, 0.5};

        return discounts[this.random.nextInt(discounts.length)];
    }


}
