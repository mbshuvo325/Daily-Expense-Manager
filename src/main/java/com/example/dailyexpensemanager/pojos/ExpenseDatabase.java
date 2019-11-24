package com.example.dailyexpensemanager.pojos;

import android.content.Context;
import android.view.View;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.dailyexpensemanager.daos.expenseDao;
import com.example.dailyexpensemanager.daos.incomeDao;

@Database(entities = {ExpensePojo.class,CategoryPojo.class,IncomePojo.class},version = 1)
public abstract class ExpenseDatabase extends RoomDatabase {
    public static ExpenseDatabase db;
    public abstract expenseDao getExpense();
    public abstract incomeDao getIncome();
    public static ExpenseDatabase getInstance(Context context){
        if (db==null){
            db = Room.databaseBuilder(context,ExpenseDatabase.class,"expense_db").allowMainThreadQueries().build();
        }
        return db;
    }
}
