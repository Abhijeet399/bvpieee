package com.jskgmail.bvpieee;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapterRes extends RecyclerView.Adapter<CustomAdapterRes.ViewHolder>  {
    //int height,width;
    Activity context;
    ArrayList<String> name;
    ArrayList<String> date;
    ArrayList<String> chapt;
    ArrayList<String> link;
    ArrayList<String> pdf;



    public CustomAdapterRes(ResourcesActivity context, ArrayList<String> name, ArrayList<String> date,ArrayList<String> chapt,ArrayList<String> link,ArrayList<String> pdf)
    {    this.context = context;
        this.context=context;
        this.name=name;
        this.date=date;
        this.chapt=chapt;
        this.link=link;
        this.pdf=pdf;

    }

//    public void getScreenSize(){
//        DisplayMetrics displayMetrics=new DisplayMetrics();
//        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
//        height=displayMetrics.heightPixels;
//        width=displayMetrics.widthPixels;
//    }

    @Override
    public CustomAdapterRes.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView= inflater.inflate(R.layout.allresource,parent,false);
        CustomAdapterRes.ViewHolder holder= new CustomAdapterRes.ViewHolder(itemView);
        // Toast.makeText(context, "blablabla", Toast.LENGTH_SHORT).show();

      //  getScreenSize();
        return holder;

    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

    //    batch_info info= infos.get(position);

        //  Toast.makeText(context, feed.getUsername(), Toast.LENGTH_SHORT).show();



        holder.topic.setText(name.get(position));
        holder.date.setText(date.get(position));
        if ((chapt.get(position)).equals("ras"))
            holder.chap.setImageResource(R.drawable.ras);
        else
        if ((chapt.get(position)).equals("cs"))
            holder.chap.setImageResource(R.drawable.ieee_cs_logo);
        else
        if ((chapt.get(position)).equals("hkn"))
            holder.chap.setImageResource(R.drawable.hkn);
        else
        if ((chapt.get(position)).equals("ias"))
            holder.chap.setImageResource(R.drawable.iaslogo);
        else
        if ((chapt.get(position)).equals("wie"))
            holder.chap.setImageResource(R.drawable.wie);



        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VideosActivity.link=link.get(position);
                ResourcePDFActivity.link=pdf.get(position);
                context.startActivity(new Intent(context,VideosActivity.class));



            }
        });



    }




    @Override
    public int getItemCount() {
        return (name==null)?0:name.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder  {

        TextView topic;
        TextView date;
        ImageView chap;
        RelativeLayout rl;




        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView= itemView;
            topic=(TextView)itemView.findViewById(R.id.top);


            date=(TextView)itemView.findViewById(R.id.date);
            chap=itemView.findViewById(R.id.chap);
            rl=itemView.findViewById(R.id.rl);


        }

              //  b2.setImageBitmap(bm);
        //        notifyDataSetChanged();
            }}


































