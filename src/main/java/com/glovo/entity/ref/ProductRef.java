package com.glovo.entity.ref;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Table("order_product")
@Data
@AllArgsConstructor
public class ProductRef {

    private Integer productId;

}
