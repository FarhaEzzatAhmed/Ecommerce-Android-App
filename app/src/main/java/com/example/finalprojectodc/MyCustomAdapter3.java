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

public class MyCustomAdapter3 extends RecyclerView.Adapter<MyCustomAdapter3.MyViewHolder> {

    Context ctx;
    List<Root> products ;

    public MyCustomAdapter3(Context ctx, List<Root> products) {
        this.ctx = ctx;
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.clothes_cell,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(recyclerView);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(products.get(position).getCategory().getName());
        Glide.with(ctx).load(products.get(position).getCategory().getImage()).into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent;
                switch (holder.getAdapterPosition()){
                    case 0:
                        intent =  new Intent(ctx,JewlwryScreen.class);
                        break;

                    case 1:
                        intent =  new Intent(ctx, JewlwryScreen.class);
                        break;

                    default:
                        intent =  new Intent(ctx, JewlwryScreen.class);
                        break;
                }
                ctx.startActivity(intent);

                //Toast.makeText(ctx, "You selected "+products.get(holder.getAdapterPosition()).getProductTitle(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            this.title= itemView.findViewById(R.id.title);
            this.img = itemView.findViewById(R.id.img);
        }
    }
}

