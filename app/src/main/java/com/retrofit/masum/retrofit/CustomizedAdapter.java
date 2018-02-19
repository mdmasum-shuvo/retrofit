package com.retrofit.masum.retrofit;

/**
 * Created by Masum on 2/18/2018.
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;




public class CustomizedAdapter  extends RecyclerView.Adapter<CustomizedAdapter.CustomViewHolder> {
    private Context context;
    List<Product> productList;


    public CustomizedAdapter(Context context,ArrayList<Product> productList){

        this.context=context;
        this.productList=productList;
    }


    class CustomViewHolder extends RecyclerView.ViewHolder{

        Context context;
        ArrayList<Product> productList;
        TextView txtName;

        public CustomViewHolder(View itemView, Context context, ArrayList<Product> productList) {
            super(itemView);
            this.context=context;
            this.productList=productList;
            txtName=(TextView)itemView.findViewById(R.id.textName);


        }


    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.card_layout,null);
        CustomViewHolder viewHolder=new CustomViewHolder(view,context, (ArrayList<Product>) productList);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        final Product e = productList.get(position);
        holder.txtName.setText(e.getName());

        holder.txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dataIntent=new Intent(context,ViewData.class);
                dataIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                dataIntent.putExtra("data", productList.get(position));
                context.startActivity(dataIntent);
            }
        });



    }


    @Override
    public int getItemCount() {
        return productList.size();
    }



}
