package com.example.dailyexpensemanager.pojos;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_income")
public class IncomePojo {

    @PrimaryKey(autoGenerate = true)
    private int incomeid;
    private String incomeSource;
    private Double incomeAmount;

    @Ignore
    public IncomePojo(int incomeid, String incomeSource, Double incomeAmount) {
        this.incomeid = incomeid;
        this.incomeSource = incomeSource;
        this.incomeAmount = incomeAmount;
    }

    public IncomePojo(String incomeSource, Double incomeAmount) {
        this.incomeSource = incomeSource;
        this.incomeAmount = incomeAmount;
    }

    public int getIncomeid() {
        return incomeid;
    }

    public void setIncomeid(int incomeid) {
        this.incomeid = incomeid;
    }

    public String getIncomeSource() {
        return incomeSource;
    }

    public void setIncomeSource(String incomeSource) {
        this.incomeSource = incomeSource;
    }

    public Double getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(Double incomeAmount) {
        this.incomeAmount = incomeAmount;
    }
}
