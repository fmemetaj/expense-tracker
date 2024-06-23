package com.fmemetaj.expensetracker.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "expenses")
public class Expense {

    @Id
    private String id;
    private String description;
    private Double amount;
    private String category;
    private String date;

    @DBRef
    private User user;
}
