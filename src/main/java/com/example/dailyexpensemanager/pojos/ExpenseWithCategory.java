package com.example.dailyexpensemanager.pojos;

import androidx.room.Embedded;

public class ExpenseWithCategory {

    @Embedded
   public ExpensePojo expensePojo;

    @Embedded
   public CategoryPojo categoryPojo;
}
