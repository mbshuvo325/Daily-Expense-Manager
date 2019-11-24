package com.example.dailyexpensemanager.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dailyexpensemanager.pojos.ExpensePojo;
import com.example.dailyexpensemanager.pojos.ExpenseWithCategory;
import com.example.dailyexpensemanager.pojos.IncomePojo;

import java.util.List;

@Dao
public interface expenseDao {
    @Insert
    long insertNewExpense(ExpensePojo expensePojo);

    @Update
    int updateExpense(ExpensePojo expensePojo);

    @Delete
    int deleteExpense(ExpensePojo expensePojo);


    @Query("select * from tbl_expense")
    List<ExpensePojo> getAllExpense();


    @Query("select * from tbl_expense" + " inner join tbl_category"+" on tbl_expense.c_id = tbl_category.id")
    List<ExpenseWithCategory> getAllInfo();

    @Query("select SUM(expenseAmount) from tbl_expense")
    Double getTotalExpenseAmount();

}
