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
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dailyexpensemanager.pojos.ExpensePojo;
import com.example.dailyexpensemanager.R;
import com.example.dailyexpensemanager.pojos.ExpenseWithCategory;
import java.util.List;
public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    private Context context;
    private List<ExpensePojo> expenseList;
    private List<ExpenseWithCategory> expenseWithCategories;
    ///for Show details
    private View view;
    public ExpenseAdapter(List<ExpenseWithCategory> expenseWithCategories) {
      this.expenseWithCategories = expenseWithCategories;
   }
    public ExpenseAdapter(Context context, List<ExpensePojo> expenseList) {
        this.context = context;
        this.expenseList = expenseList;
    }
    @NonNull
    @Override
    public ExpenseAdapter.ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.layout_row,parent,false);
        return new ExpenseViewHolder(itemView) ;
    }
    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.ExpenseViewHolder holder, final int position) {
        holder.expenseNameTV.setText(expenseList.get(position).getExpenseName());
        holder.expenseDateTV.setText(expenseList.get(position).getExpenseDate());
        holder.expenseAmountTV.setText(String.valueOf(expenseList.get(position).getExpenseAmount()));
        holder.expenseStatusTV.setText(expenseList.get(position).getExpenseStatus());
        holder.edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater()
                        .inflate(R.menu.main_menu, popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        //MyTodo myTodo = todoList.get(position);
                        switch (menuItem.getItemId()){
                            case R.id.edit_id:
                               // int id = myTodo.getTodoId();
                                //Intent intent = new Intent(context, MainActivity.class);
                                //intent.putExtra("id", id);
                               // context.startActivity(intent);
                                Toast.makeText(context, "Edit enable", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.delete_id:
                                Toast.makeText(context, "Delete Enable", Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                });
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///show details
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = LayoutInflater.from(context);
                View view1 = inflater.inflate(R.layout.showdetails,null);
                builder.setTitle("Expense Details");
                builder.setIcon(R.drawable.ic_details_black_24dp);

                final TextView showname = view1.findViewById(R.id.showname);
                final TextView showdate = view1.findViewById(R.id.showdate);
                final TextView showamount = view1.findViewById(R.id.showamount);
                final TextView showstatus = view1.findViewById(R.id.showstatus);
                final TextView showcatagory = view1.findViewById(R.id.showcatagory);

                showname.setText(expenseList.get(position).getExpenseName());
                showdate.setText(expenseList.get(position).getExpenseDate());
                showamount.setText(String.valueOf(expenseList.get(position).getExpenseAmount()));
                showstatus.setText(expenseList.get(position).getExpenseStatus());
                //showcatagory.setText(expenseList.get(position).getCategory());
                //String numberAsString = String.valueOf(number);
                builder.setPositiveButton("Ok",null);
                builder.setView(view1);
                final AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return expenseList.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView expenseNameTV,expenseDateTV,expenseAmountTV,expenseStatusTV,edit_btn;
        public ExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            expenseNameTV = itemView.findViewById(R.id.row_name);
            expenseDateTV = itemView.findViewById(R.id.row_date);
            expenseAmountTV = itemView.findViewById(R.id.row_amount);
            expenseStatusTV = itemView.findViewById(R.id.row_status);
            edit_btn = itemView.findViewById(R.id.menu_btn);
        }
    }
    ///For update Database
    public void updateExpenseList(ExpensePojo expensePojo){
        expenseList.add(expensePojo);
        notifyDataSetChanged();
    }
}



