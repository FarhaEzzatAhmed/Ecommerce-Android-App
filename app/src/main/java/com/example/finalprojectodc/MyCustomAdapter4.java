package com.example.finalprojectodc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyCustomAdapter4  extends RecyclerView.Adapter<MyCustomAdapter4.MyViewHolder> {

    Context ctx;
    List<Root2> products ;

    public MyCustomAdapter4(Context ctx, List<Root2> products) {
        this.ctx = ctx;
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.spesific_item_cell,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(recyclerView);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(products.get(position).getTitle());
        holder.description.setText(products.get(position).getDescription());
        holder.price.setText(products.get(position).getPrice()+"");
        Glide.with(ctx).load(products.get(position).getImage()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx,DetailsScreen.class);
                intent.putExtra("price",products.get(holder.getAdapterPosition()).getPrice()+"");
                intent.putExtra("description",products.get(holder.getAdapterPosition()).getDescription());
                intent.putExtra("title",products.get(holder.getAdapterPosition()).getTitle());
                intent.putExtra("img",products.get(holder.getAdapterPosition()).getImage());
                ctx.startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title,description,price;
        public ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.description= itemView.findViewById(R.id.description);
            this.price= itemView.findViewById(R.id.price);
            this.title= itemView.findViewById(R.id.title);
            this.img = itemView.findViewById(R.id.img);
        }
    }
}
