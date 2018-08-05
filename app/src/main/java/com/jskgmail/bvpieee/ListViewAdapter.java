package com.jskgmail.bvpieee;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ListViewAdapter extends BaseAdapter {
    Activity context;
    ArrayList<String> name;
    ArrayList<String> date;
    ArrayList<String> chapt;
    ArrayList<String> link;


    public ListViewAdapter(ResourcesActivity context, ArrayList<String> name, ArrayList<String> date,ArrayList<String> chapt,ArrayList<String> link)
    {
        super();
        this.context=context;
        this.name=name;
        this.date=date;
        this.chapt=chapt;
        this.link=link;

    }


    @Override
    public int getCount() {

        return name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView topic;
        TextView date;
        ImageView chap;
        RelativeLayout rl;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        LayoutInflater inflater=context.getLayoutInflater();
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.donor,null);
            holder=new ViewHolder();

            holder.topic=(TextView)convertView.findViewById(R.id.top);


            holder.date=(TextView)convertView.findViewById(R.id.date);
            holder.chap=convertView.findViewById(R.id.chap);
holder.rl=convertView.findViewById(R.id.rl);

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
        context.startActivity(new Intent(context,VideosActivity.class));



    }
});
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }


       // notifyDataSetChanged();
        return convertView;
    }


}
