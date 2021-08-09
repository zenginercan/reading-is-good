package com.getir.readingisgood.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyStatistic {

    private Integer totalOrderCount = 0;

    private Integer totalBookCount = 0;

    private Long totalPurchasedAmount = 0L;
}
