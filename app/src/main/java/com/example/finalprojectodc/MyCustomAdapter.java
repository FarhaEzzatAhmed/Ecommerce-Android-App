package com.example.finalprojectodc;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCustomAdapter  extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {
    Context ctx;
    ArrayList<ProductModel> data;

    public MyCustomAdapter(Context ctx, ArrayList<ProductModel> data) {
        this.ctx = ctx;
        this.data = data;
    }


    @NonNull
    @Override
    public MyCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View recyclerView = inflater.inflate(R.layout.list_cell,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(recyclerView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomAdapter.MyViewHolder holder, int position) {
        holder.productTitle.setText(data.get(position).getProductTitle());
        holder.productimage.setImageResource(data.get(position).getProductImg());
        //holder.productimagplay.setImageResource(data.get(position).getProductImgplay());
        //holder.productimagstop.setImageResource(data.get(position).getProductImgstop());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                final Intent intent;
                switch (holder.getAdapterPosition()){
                    case 0:
                        intent =  new Intent(ctx,Screen8.class);
                        break;

                    case 1:
                        intent =  new Intent(ctx, Screen8.class);
                        break;
                    case 2:
                        intent =  new Intent(ctx, Screen8.class);
                        break;
                    case 3:
                        intent =  new Intent(ctx, Screen8.class);
                        break;
                    case 4:
                        intent =  new Intent(ctx, ElectrictyScreen.class);
                        break;
                    case 5:
                        intent =  new Intent(ctx, JewlwryScreen.class);
                        break;

                    default:
                        intent =  new Intent(ctx, Screen8.class);
                        break;
                }
                ctx.startActivity(intent);

                Toast.makeText(ctx, "You selected "+data.get(holder.getAdapterPosition()).getProductTitle(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

///////


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView productTitle;
        public ImageView productimage;
        //public ImageView productimagplay;
        //public ImageView productimagstop;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ctx = itemView.getContext();

            this.productTitle = itemView.findViewById(R.id.productTitle);
            this.productimage = itemView.findViewById(R.id.img);
            //this.productimagplay = itemView.findViewById(R.id.ivPlayer);
            //this.productimagstop = itemView.findViewById(R.id.ivStop);


        }


    }
}


