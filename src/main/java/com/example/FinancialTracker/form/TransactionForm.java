package com.example.FinancialTracker.form;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TransactionForm {

    private BigDecimal amount;

    private String date;

}
