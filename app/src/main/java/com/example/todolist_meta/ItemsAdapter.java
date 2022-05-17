package com.example.todolist_meta;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


//Displays data
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public interface OnClickListener{
        void onItemClickListener(int position);
    }

    public interface OnLongClickListener{
        void onItemLongClickListener(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        this.items=items;
        this.longClickListener=longClickListener;
        this.clickListener=clickListener;
    }

    @NonNull

    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        //Layout inflator to inflate a view
        View todoView=LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1,parent, false);
        //Return it
        return new ViewHolder(todoView);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        String item=items.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {

        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        TextView tvItem;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            tvItem=itemView.findViewById(android.R.id.text1);
        }

        public void bind(String item){
            tvItem.setText(item);
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClickListener(getAdapterPosition());
                }
            });
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Show what position was pressed
                    longClickListener.onItemLongClickListener(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
