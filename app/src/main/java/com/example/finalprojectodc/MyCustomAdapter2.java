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

public class MyCustomAdapter2 extends RecyclerView.Adapter<MyCustomAdapter2.MyViewHolder> {

    Context ctx;
    List<Product> products ;

    public MyCustomAdapter2(Context ctx, List<Product> products) {
        this.ctx = ctx;
        this.products = products;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.product_cell,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(recyclerView);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.title.setText(products.get(position).getName());
        Glide.with(ctx).load(products.get(position).getImage()).into(holder.img);
        //holder.title2.setText(products.get(position).getName());
        //Glide.with(ctx).load(products.get(position).getImage()).into(holder.img2);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent;
                switch (holder.getAdapterPosition()){
                    case 0:
                        intent =  new Intent(ctx, Screen9.class);
                        break;

                    case 1:
                        intent =  new Intent(ctx, ElectrictyScreen.class);
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
        public TextView title,title2;
        public ImageView img,img2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            this.title= itemView.findViewById(R.id.title);
            this.img = itemView.findViewById(R.id.img);
            //this.title2= itemView.findViewById(R.id.title2);
            //this.img2 = itemView.findViewById(R.id.img2);
        }
    }
}
