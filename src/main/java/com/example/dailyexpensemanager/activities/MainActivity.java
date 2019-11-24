package com.example.dailyexpensemanager.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailyexpensemanager.adapter.ExpenseAdapter;
import com.example.dailyexpensemanager.pojos.CategoryPojo;
import com.example.dailyexpensemanager.pojos.ExpenseDatabase;
import com.example.dailyexpensemanager.pojos.ExpensePojo;
import com.example.dailyexpensemanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    public static List<ExpensePojo> expenseList;
    private RecyclerView expenseRV;
    private ExpenseAdapter rvAdapter;
    Context context = this;
    private String category;
    private Spinner setCategory;
    ArrayAdapter<String> adapter;
    int categorPosition;
    Double showTotalExpense = 0.0;
    private TextView showAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        expenseRV = findViewById(R.id.recyclerID);
        showAmount = findViewById(R.id.setTotalExpenseAmount);
        expenseList =  ExpenseDatabase.getInstance(this).getExpense().getAllExpense();
        rvAdapter = new ExpenseAdapter(this, expenseList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        expenseRV.setLayoutManager(llm);
        expenseRV.setAdapter(rvAdapter);
        TotalAmount();
    }
    public void AddExpense(View view) {
        ShowLoginDailogue();
    }
   private void ShowLoginDailogue(){
       final AlertDialog.Builder builder = new AlertDialog.Builder(this);
       LayoutInflater inflater = LayoutInflater.from(this);
       View view = inflater.inflate(R.layout.add_expense_dialogue,null);
       builder.setTitle("Add Your Expense");
       builder.setIcon(R.drawable.ic_add_shopping_cart_black_24dp);
       final EditText EnameET = view.findViewById(R.id.expensename);
       final EditText Eamount = view.findViewById(R.id.expenseamount);
       final EditText Edetalis = view.findViewById(R.id.expensedetails);
       final TextView categoryTV = view.findViewById(R.id.expensecategory);
       final Spinner categorysp = view.findViewById(R.id.category_sp);
       Button saveBT = view.findViewById(R.id.expensesaveid);
       Button cancelBT = view.findViewById(R.id.expensecancelid);
       builder.setView(view);
       final AlertDialog dialog = builder.create();
       ///for database spinner

       // String[] categorys = categoryData();
       String[] categorys = getResources().getStringArray(R.array.select_category);
       adapter = new ArrayAdapter<>(
               this,android.R.layout.simple_spinner_dropdown_item, categorys
       );
       categorysp.setAdapter(adapter);
       categorysp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               category = adapterView.getItemAtPosition(i).toString();
           categorPosition = adapter.getPosition(category);
              // Toast.makeText(MainActivity.this, city, Toast.LENGTH_SHORT).show();
           }
           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
           }
       });
       dialog.show();
       saveBT.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String expenseName = EnameET.getText().toString();
               String  expenseAmount = Eamount.getText().toString();
               String expenseDetails = Edetalis.getText().toString();
              // String expenseCategory = categoryTV.getText().toString();
               ExpensePojo expensePojo1 = new ExpensePojo(categorPosition,expenseName,getCurrentDate(),Double.valueOf(expenseAmount),expenseDetails);
               ExpenseDatabase.getInstance(context).getExpense().insertNewExpense(expensePojo1);
               rvAdapter.updateExpenseList(expensePojo1);
               TotalAmount();
               dialog.dismiss();
           }
       });
       cancelBT.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dialog.dismiss();
           }
       });
    }
    ///For Current date
    private String getCurrentDate(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }
    ///For OPtion_Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.incomemenu:
                Intent intent = new Intent(MainActivity.this,Income_Activity.class);
                startActivity(intent);
                finish();
                Toast.makeText(context, "Income Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.addcategory:
                AddcategoryDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    ///end option manu

    private void TotalAmount(){
        showTotalExpense = ExpenseDatabase.getInstance(context).getExpense().getTotalExpenseAmount();
        showAmount.setText(String.valueOf(showTotalExpense));
    }
    private void AddcategoryDialog()
    {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.add_category_dialog,null);
        builder.setTitle("Add New Category");
        builder.setIcon(R.drawable.ic_add_circle_black_24dp);
        final EditText EnameET = view.findViewById(R.id.addcategory);
        Button saveBT = view.findViewById(R.id.categoryAdd);
        Button cancelBT = view.findViewById(R.id.cancel);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

    }
}
