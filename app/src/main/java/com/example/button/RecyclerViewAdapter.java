package com.example.button;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    private Context mContext;
    private List<City> mData;
    Dialog myDialog;


    public RecyclerViewAdapter(Context mContext, List<City> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_items_city,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.country_name.setText(mData.get(position).getTitle());
        holder.img_city_thumbnail.setImageResource(mData.get(position).getThumbnail());
        myDialog = new Dialog(mContext);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowPopup(position);
            }
        });

        //set on click here
        // set the descr here;
        // do this using intent.putExtra("Description",mData.get(position).getDescription());
        //mContext.startActivity(intent)



        //hint-- recieve and set data by creating new Class which will be used in intent

    }
    public void ShowPopup( final int p)
    {
        TextView textclose, city_name,city_desc;
        String title,description;
        ImageView imageView;
        Button button_fav;
        myDialog.setContentView(R.layout.custompopup);
        textclose = (TextView) myDialog.findViewById(R.id.text_close_popup);
        button_fav = (Button) myDialog.findViewById(R.id.fav_popup);
        city_name = (TextView) myDialog.findViewById(R.id.text_city_popup);
        city_desc = (TextView) myDialog.findViewById(R.id.text_desc_popup);
        imageView = (ImageView) myDialog.findViewById(R.id.img_popup);

        title = mData.get(p).getTitle();
        city_name.setText(title);
        city_desc.setText(mData.get(p).getDescription());
        imageView.setImageResource(mData.get(p).getThumbnail());

        textclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView country_name;
        ImageView img_city_thumbnail;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            country_name =(TextView) itemView.findViewById(R.id.city_title_id);
            img_city_thumbnail = (ImageView) itemView.findViewById(R.id.city_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_city);


        }
    }

}
