package org.socialmeli.be_java_hisp_w24_g04.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer productId;
    private Integer name;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
