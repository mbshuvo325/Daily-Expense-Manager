package com.example.dailyexpensemanager.pojos;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_category")
public class CategoryPojo {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String category;

    @Ignore
    public CategoryPojo(int id, String category) {
        this.id = id;
        this.category = category;
    }

    public CategoryPojo(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static CategoryPojo[] populateData() {
        return new CategoryPojo[] {
                new CategoryPojo(1,"Food"),
                new CategoryPojo(2, "Transport"),
                new CategoryPojo(3, "Bill"),
                new CategoryPojo(4, "Cloth"),
                new CategoryPojo(5, "Education"),
                new CategoryPojo(6, "Medical"),
                new CategoryPojo(7, "Accessories"),
                new CategoryPojo(8, "Donate"),
                new CategoryPojo(9, "Lend")
        };
    }
}
