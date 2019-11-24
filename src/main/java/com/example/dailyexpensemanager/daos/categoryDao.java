package com.example.dailyexpensemanager.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dailyexpensemanager.pojos.CategoryPojo;

import java.util.List;

@Dao
public interface categoryDao {

    @Insert
    long insertNewCategory(CategoryPojo categoryPojo);

    @Update
    long updateCategory(CategoryPojo categoryPojo);

    @Delete
    long deleteCategory(CategoryPojo categoryPojo);


    @Query("select * from tbl_income")
    List<CategoryPojo> getAllCatagory();

    @Query("select category from tbl_category")
    List<CategoryPojo>getCategoryName();

}
