package com.example.dailyexpensemanager.pojos;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_expense")
public class ExpensePojo {
    @PrimaryKey(autoGenerate = true)
    private int expenseid;
    private int c_id;
    private  String expenseName;
    private String expenseDate;
    private Double expenseAmount;
    private String expenseStatus;

    @Ignore
    public ExpensePojo(int expenseid, int c_id, String expenseName, String expenseDate, Double expenseAmount, String expenseStatus) {
        this.expenseid = expenseid;
        this.c_id = c_id;
        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
        this.expenseAmount = expenseAmount;
        this.expenseStatus = expenseStatus;
    }

    public ExpensePojo(int c_id, String expenseName, String expenseDate, Double expenseAmount, String expenseStatus) {
        this.c_id = c_id;
        this.expenseName = expenseName;
        this.expenseDate = expenseDate;
        this.expenseAmount = expenseAmount;
        this.expenseStatus = expenseStatus;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public Double getExpenseAmount() {
        return expenseAmount;
    }

    public String getExpenseStatus() {
        return expenseStatus;
    }

    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }

    public void setExpenseDate(String expenseDate) {
        this.expenseDate = expenseDate;
    }

    public void setExpenseAmount(Double exprnseAmount) {
        this.expenseAmount = exprnseAmount;
    }

    public void setExpenseStatus(String expenseStatus) {
        this.expenseStatus = expenseStatus;
    }

    public int getExpenseid() {
        return expenseid;
    }

    public void setExpenseid(int expenseid) {
        this.expenseid = expenseid;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }
}
