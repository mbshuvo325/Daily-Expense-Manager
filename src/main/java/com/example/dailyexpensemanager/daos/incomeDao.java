package com.example.dailyexpensemanager.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dailyexpensemanager.pojos.IncomePojo;

import java.util.List;

@Dao
public interface incomeDao {

    @Insert
    long insertNewIncome(IncomePojo incomePojo);

    @Update
    int updateIncome(IncomePojo incomePojo);

    @Delete
    int deleteIncome(IncomePojo incomePojo);

    @Query("select * from tbl_income")
    List<IncomePojo> getAllIncome();

    @Query("select SUM(incomeAmount) from tbl_income")
    Double getAllIncomeAmount();
}
