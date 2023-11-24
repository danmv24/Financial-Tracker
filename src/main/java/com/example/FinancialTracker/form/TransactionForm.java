package com.example.FinancialTracker.form;


import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionForm {

    private String categoryName;

    private BigDecimal amount;

    private String date;

    private String comment;

}
