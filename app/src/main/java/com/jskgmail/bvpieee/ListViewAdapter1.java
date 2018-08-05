package com.jskgmail.bvpieee;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class ListViewAdapter1 extends BaseAdapter {
    Activity context;
    ArrayList<String> name;
    ArrayList<String> date;
    ArrayList<String> by;
    ArrayList<String> time;
    ArrayList<String> venue;


    public ListViewAdapter1(AllEventsActivity context, ArrayList<String> name, ArrayList<String> date, ArrayList<String> by, ArrayList<String> time,ArrayList<String> venue)
    {
        super();
        this.context=context;
        this.name=name;
        this.date=date;
        this.by=by;
        this.time=time;
        this.venue=venue;

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
        TextView name;
        TextView date;
    TextView by,time,venue;
        RelativeLayout rl;


    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        LayoutInflater inflater=context.getLayoutInflater();
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.allevents,null);
            holder=new ViewHolder();

            holder.name=(TextView)convertView.findViewById(R.id.name);


            holder.date=(TextView)convertView.findViewById(R.id.datee);
            holder.by=convertView.findViewById(R.id.byy);
            holder.time=convertView.findViewById(R.id.timee);
            holder.venue=convertView.findViewById(R.id.venue);

            holder.rl=convertView.findViewById(R.id.rl);

            holder.name.setText(name.get(position));
            holder.date.setText(date.get(position));
            holder.by.setText(by.get(position));
            holder.time.setText(time.get(position));
            holder.venue.setText(venue.get(position));




        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }


       // notifyDataSetChanged();
        return convertView;
    }


}
