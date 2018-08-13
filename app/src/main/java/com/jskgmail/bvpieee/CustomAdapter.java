package com.jskgmail.bvpieee;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    //int height,width;
    Context context;
    ArrayList<Event_info> infos;

    public interface photoflipping{
        void flipimages(int[] image, ViewFlipper viewFlipper);
    }
    CustomAdapter.photoflipping flipper;

    public CustomAdapter(Context context, ArrayList<Event_info> infos, CustomAdapter.photoflipping flipper) {
        this.context = context;
        this.infos = infos;
        this.flipper=flipper;
    }

//    public void getScreenSize(){
//        DisplayMetrics displayMetrics=new DisplayMetrics();
//        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
//        height=displayMetrics.heightPixels;
//        width=displayMetrics.widthPixels;
//    }

    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView= inflater.inflate(R.layout.row,parent,false);
        CustomAdapter.ViewHolder holder= new CustomAdapter.ViewHolder(itemView);
        // Toast.makeText(context, "blablabla", Toast.LENGTH_SHORT).show();

      //  getScreenSize();
        return holder;

    }

    @Override
    public void onBindViewHolder(final CustomAdapter.ViewHolder holder, int position) {

        Event_info info= infos.get(position);

        //  Toast.makeText(context, feed.getUsername(), Toast.LENGTH_SHORT).show();


       holder.title.setText(info.getTopic());
       holder.desc.setText(info.getInfo());

       // for(int i=0;i<info.getPhoto().length;i++){
            flipper.flipimages(info.getPhoto(),holder.viewFlipper);
    //flipper.photoflipper(holder.getAdapterPosition());

       // Glide.with(context).load(info.getPhoto()).into(holder.photo);



    }




    @Override
    public int getItemCount() {
        return (infos==null)?0:infos.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        TextView desc;
        ViewFlipper viewFlipper;

        TextView title;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView= itemView;
            title= itemView.findViewById(R.id.title);
            desc= itemView.findViewById(R.id.desc);
            viewFlipper= itemView.findViewById(R.id.pics);


        }
    }
}
