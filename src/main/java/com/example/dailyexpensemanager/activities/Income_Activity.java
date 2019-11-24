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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dailyexpensemanager.R;
import com.example.dailyexpensemanager.adapter.ExpenseAdapter;
import com.example.dailyexpensemanager.adapter.IncomeAdapter;
import com.example.dailyexpensemanager.pojos.ExpenseDatabase;
import com.example.dailyexpensemanager.pojos.ExpensePojo;
import com.example.dailyexpensemanager.pojos.IncomePojo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Income_Activity extends AppCompatActivity {
    private Context context;
    public static List<IncomePojo> incomePojoList;
    private IncomeAdapter rvAdapter;
    private RecyclerView incomeRV;
    Double showTotalIncome = 0.0;
    private TextView totalIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_);
        incomeRV = findViewById(R.id.incomerecyclerID);
        incomePojoList = ExpenseDatabase.getInstance(this).getIncome().getAllIncome();
        rvAdapter = new IncomeAdapter(this,incomePojoList);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        incomeRV.setLayoutManager(llm);
        incomeRV.setAdapter(rvAdapter);
        totalIncome = findViewById(R.id.setAmount);
        TotalAmount();
    }

    public void AddIncome(View view) {
        showIncomeDialog();
    }
    ///For Current date
    private String getCurrentDate(){
        return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
    }
    ///For OPtion_Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.incomeoption_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.gotoExpense:
                Intent intent = new Intent(Income_Activity.this,MainActivity.class);
                startActivity(intent);
                finish();;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void TotalAmount(){
        showTotalIncome = ExpenseDatabase.getInstance(context).getIncome().getAllIncomeAmount();
        totalIncome.setText(String.valueOf(showTotalIncome));

    }
    ///end option manu

    private void showIncomeDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.add_income_dialog,null);
        builder.setTitle("Add Your Income");
        builder.setIcon(R.drawable.ic_attach_money_black_24dp);
        final EditText InameET = view.findViewById(R.id.incomeSource);
        final EditText Iamount = view.findViewById(R.id.incomeAmount);
        final Button Cancelbtn = view.findViewById(R.id.incomeDialogCancel);
        final Button savebtn = view.findViewById(R.id.incomeDialogSave);

        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        Cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String IncomeSource = InameET.getText().toString();
                String IncomeAmount =  Iamount.getText().toString();
                IncomePojo incomePojo = new IncomePojo(IncomeSource,Double.valueOf(IncomeAmount));
               ExpenseDatabase.getInstance(context).getIncome().insertNewIncome(incomePojo);
                rvAdapter.updateIncomeList(incomePojo);
                TotalAmount();
                dialog.dismiss();

            }
        });
    }
}

