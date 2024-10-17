package com.demo.sales_erp.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class JewelleryDto {
//    private Long id;
    @NotBlank(message = "Customer name is required")
    private String customerName;
    @NotBlank(message = "Type is required")
    private String type;
    @NotBlank(message = "Ornament is required")
    private String ornament;
    @Pattern(regexp = "^SP[1-9]$",message = "Sales person is required")
    private String salesPerson;
    @Min(value = 10, message = "Weight must be a positive value")
    private double weight;
    @Min(value = 0, message = "Purity must be between 0 and 100")
    @Max(value = 100, message = "Purity must be between 0 and 100")
    private double purity;
    @Min(value = 100, message = "Price must be a positive value")
    private double price;
    @Min(value = 1, message = "Quantity must be a positive value")
    private int quantity;
}
