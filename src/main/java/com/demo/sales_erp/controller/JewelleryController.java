package com.demo.sales_erp.controller;

import com.demo.sales_erp.dto.JewelleryDto;
import com.demo.sales_erp.entity.Jewellery;
import com.demo.sales_erp.service.JewelleryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jewellery")
public class JewelleryController {
    @Autowired
    private JewelleryService jewelleryService;

    @GetMapping
    public List<Jewellery> getAllJewellery(){
        return jewelleryService.getAllJewellery();
    }

    @PostMapping
    public Jewellery createJewellery(@RequestBody @Valid JewelleryDto jewelleryDto){
        return jewelleryService.saveJewellery(jewelleryDto);
    }

    @GetMapping("/{salesPerson}")
    public List<Jewellery> getJewelleryById(@PathVariable String salesPerson){
        return jewelleryService.getJewelleryBySalesPerson(salesPerson);
    }

    @GetMapping("/type")
    public Map<String,List<Jewellery>> getJewelleryByType(){
        return jewelleryService.groupByType();
    }

    @GetMapping("/by-weight")
    public ResponseEntity<List<Jewellery>> getJewelleryByWeightRange(
            @RequestParam double minWeight,
            @RequestParam double maxWeight) {
        List<Jewellery> jewelleryList = jewelleryService.getJewelleryByWeightRange(minWeight, maxWeight);
        return ResponseEntity.ok(jewelleryList);
    }

    @GetMapping("/by-purity")
    public ResponseEntity<List<Jewellery>> getJewellerySortByPurity() {
        List<Jewellery> jewelleryList = jewelleryService.getJewelleryByPurity();
        return ResponseEntity.ok(jewelleryList);
    }

    @GetMapping("/by-price")
    public ResponseEntity<Map<String,Double>> getJewelleryCustomerByPrice() {
        Map<String,Double> jewelleryList = jewelleryService.getJewelleryByPrice();
        return ResponseEntity.ok(jewelleryList);
    }

    @GetMapping("/by-date/{date}")
    public ResponseEntity<List<Jewellery>> getJewelleryByDate(@PathVariable String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date purchaseDate = formatter.parse(date); // Parse String to Date
            return jewelleryService.getSalesByDate(purchaseDate); // Call the updated method
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body(null); // Handle invalid date format
        }
    }
}
