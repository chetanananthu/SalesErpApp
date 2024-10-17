package com.demo.sales_erp.service;

import com.demo.sales_erp.dto.JewelleryDto;
import com.demo.sales_erp.entity.Jewellery;
import com.demo.sales_erp.repository.JewelleryRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.speedment.jpastreamer.application.JPAStreamer;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JewelleryService {
    @Autowired
    private JewelleryRepo jewelleryRepo;

    private final JPAStreamer jpaStreamer;

    @Autowired
    public JewelleryService(final JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }

    public Jewellery saveJewellery(JewelleryDto jewelleryDto) {
        // Implement logic to save the jewellery object to the database
        Jewellery jewellery = new Jewellery();
        BeanUtils.copyProperties(jewelleryDto, jewellery);
        jewellery.setPurchaseDate(new Date());
        return jewelleryRepo.save(jewellery);
    }

    public List<Jewellery> getJewelleryBySalesPerson(String salesPerson) {
        // Implement logic to retrieve a jewellery object by its ID
        return jewelleryRepo.findBySalesPerson(salesPerson);
    }

    public List<Jewellery> getAllJewellery() {
        // Implement logic to retrieve all jewellery objects
        return jewelleryRepo.findAll();
    }

    public Map<String, List<Jewellery>> groupByType() {
        // Use JPA Streamer to fetch and group by type
        return jpaStreamer.stream(Jewellery.class)
                .collect(Collectors.groupingBy(Jewellery::getType));
    }

    public List<Jewellery> getJewelleryByWeightRange(double minWeight, double maxWeight) {
        return jpaStreamer.stream(Jewellery.class)
                .filter(j -> j.getWeight() >= minWeight && j.getWeight() <= maxWeight)
                .sorted((n1,n2)->Double.compare(n1.getWeight(),n2.getWeight()))
                .collect(Collectors.toList());
    }

    public List<Jewellery> getJewelleryByPurity() {
        return jpaStreamer.stream(Jewellery.class)
                .sorted((n1, n2)->Double.compare(n1.getPurity(), n2.getPurity()))
                .collect(Collectors.toList());
    }

    public Map<String,Double> getJewelleryByPrice() {
        return jpaStreamer.stream(Jewellery.class)
                .collect(Collectors.groupingBy(Jewellery::getCustomerName, Collectors.summingDouble(Jewellery::getPrice)));
    }

    public ResponseEntity<List<Jewellery>> getSalesByDate(Date purchaseDate) {
        LocalDate localDate = purchaseDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();

        List<Jewellery> sales = jpaStreamer.stream(Jewellery.class)
                .filter(j -> j.getPurchaseDate().toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate().isEqual(localDate))
                .collect(Collectors.toList());

        return ResponseEntity.ok(sales);
    }
}
