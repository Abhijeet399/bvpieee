package com.jskgmail.bvpieee;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;


public class ListViewAdapter1 extends BaseAdapter {
    Activity context;
    ArrayList<String> name;
    ArrayList<String> date;
    ArrayList<String> by;
    ArrayList<String> time;
    ArrayList<String> venue;
    ArrayList<String> img;


    public ListViewAdapter1(AllEventsActivity context, ArrayList<String> name, ArrayList<String> date, ArrayList<String> by, ArrayList<String> time,ArrayList<String> venue,ArrayList<String> img)
    {
        super();
        this.context=context;
        this.name=name;
        this.date=date;
        this.by=by;
        this.time=time;
        this.venue=venue;
        this.img=img;

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
LinearLayout poster;
ImageView posterimg;

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

            holder.posterimg=convertView.findViewById(R.id.imageView);
            holder.poster=convertView.findViewById(R.id.poster);

            holder.rl=convertView.findViewById(R.id.rl);

            holder.name.setText(name.get(position));
            holder.date.setText(date.get(position));
            holder.by.setText(by.get(position));
            holder.time.setText(time.get(position));
            holder.venue.setText(venue.get(position));




            if (img.get(position).equals(""))
{
    holder.poster.setVisibility(View.GONE);
}
else {
    //glide
                Glide.with(context).load(img.get(position))
                        .thumbnail(0.5f)
                        //  .thumbnail(0.5f)
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.posterimg);

                holder.posterimg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        LayoutInflater inflater = context.getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.posterimg, null);
       AlertDialog.Builder alert = new AlertDialog.Builder(context);

        ImageView imageView=alertLayout.findViewById(R.id.imageView6);

        Glide.with(context).load(img.get(position))
                .thumbnail(0.5f)
              //  .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(imageView);
        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);



        final AlertDialog dialog = alert.create();
        dialog.show();









    }
});
}


        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }


       // notifyDataSetChanged();
        return convertView;
    }


}
