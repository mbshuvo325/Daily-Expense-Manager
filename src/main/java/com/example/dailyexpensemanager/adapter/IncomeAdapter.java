package com.example.dailyexpensemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyexpensemanager.R;
import com.example.dailyexpensemanager.pojos.IncomePojo;
import java.util.List;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.incomeViewHolder> {

    private Context context;
    private List<IncomePojo> incomePojoList;

    public IncomeAdapter(Context context, List<IncomePojo> incomePojoList) {
        this.context = context;
        this.incomePojoList = incomePojoList;
    }

    @NonNull
    @Override
    public incomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.income_row,parent,false);
        return new incomeViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(@NonNull incomeViewHolder holder, int position) {
        holder.incomeSource.setText(incomePojoList.get(position).getIncomeSource());
        holder.incomeAmount.setText(String.valueOf(incomePojoList.get(position).getIncomeAmount()));
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater()
                        .inflate(R.menu.income_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //MyTodo myTodo = todoList.get(position);
                        switch (menuItem.getItemId()){
                            case R.id.incomeEdit:
                                // int id = myTodo.getTodoId();
                                //Intent intent = new Intent(context, MainActivity.class);
                                //intent.putExtra("id", id);
                                // context.startActivity(intent);
                                Toast.makeText(context, "Edit enable", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.incomeDelete:
                                Toast.makeText(context, "Delete Enable", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return incomePojoList.size();
    }


    public class incomeViewHolder extends RecyclerView.ViewHolder{
        private TextView incomeSource, inceomeDate, incomeAmount, editBtn;

        public incomeViewHolder(@NonNull View itemView) {
            super(itemView);

            incomeSource = itemView.findViewById(R.id.income_row_name);
            inceomeDate = itemView.findViewById(R.id.income_row_date);
            incomeAmount = itemView.findViewById(R.id.income_row_amount);
            editBtn = itemView.findViewById(R.id.income_menu_btn);


        }
    }
    public void updateIncomeList(IncomePojo incomePojo){
        incomePojoList.add(incomePojo);
        notifyDataSetChanged();
    }
}

