package com.jskgmail.bvpieee;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class chatListAdapter extends BaseAdapter {

    private Activity mActivity;
    private DatabaseReference mDatabaseReference;
    private String mDisplayName;
    private ArrayList<DataSnapshot> mSnapshotList;

    private ChildEventListener mListener = new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            mSnapshotList.add(dataSnapshot);
            notifyDataSetChanged();
          //  String username= String.valueOf(dataSnapshot.child("author").getValue());
            //String messageId=dataSnapshot.getKey();

           // if (s!=null) {
             //   Toast.makeText(mActivity, "yy" + s, Toast.LENGTH_SHORT).show();
         /*   String messageId=dataSnapshot.getKey();

            String msg= String.valueOf(dataSnapshot.child("message").getValue());
            boolean isMe = username.equals(mDisplayName);

            SharedPreferences prefs = mActivity.getSharedPreferences("msg",MODE_PRIVATE);
            String lastid = prefs.getString("lastmsgid", "na");

            if (messageId.equals(lastid)&&(!isMe))
                  sendNotification(username,msg);


*/

// Write a message to the database
            //    FirebaseDatabase database = FirebaseDatabase.getInstance();
              //  DatabaseReference myRef = database.getReference("notify");

                //myRef.setValue(messageId);
                //notifyDataSetChanged();

                //SharedPreferences.Editor editor = mActivity.getSharedPreferences("msg", MODE_PRIVATE).edit();
                //editor.putString("lastmsgid", messageId);
                //editor.apply();

        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public chatListAdapter(Activity activity, DatabaseReference ref, String name,String society) {

        mActivity = activity;
        mDisplayName = name;
        // common error: typo in the db location. Needs to match what's in MainChatActivity.


        mDatabaseReference = ref.child(society);



        mDatabaseReference.addChildEventListener(mListener);

        mSnapshotList = new ArrayList<>();
    }

    private static class ViewHolder{
        TextView authorName;
        TextView body;
        LinearLayout.LayoutParams params;
    }

    @Override
    public int getCount() {
        return mSnapshotList.size();
    }

    @Override
    public InstantMessage getItem(int position) {

        DataSnapshot snapshot = mSnapshotList.get(position);
        return snapshot.getValue(InstantMessage.class);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.chat_msg_row, parent, false);

            final ViewHolder holder = new ViewHolder();
            holder.authorName = (TextView) convertView.findViewById(R.id.author);
            holder.body = (TextView) convertView.findViewById(R.id.message);
            holder.params = (LinearLayout.LayoutParams) holder.authorName.getLayoutParams();
            convertView.setTag(holder);

        }

        final InstantMessage message = getItem(position);
        final ViewHolder holder = (ViewHolder) convertView.getTag();

        boolean isMe = message.getAuthor().equals(mDisplayName);
        setChatRowAppearance(isMe, holder);

        String author = message.getAuthor();
        holder.authorName.setText(author);

        String msg = message.getMessage();
        holder.body.setText(msg);
/*
if (!isMe)
{
    SharedPreferences.Editor editor= mActivity.getSharedPreferences("msg",MODE_PRIVATE).edit();
    editor.putString("lastmsg",msg);
    editor.apply();

}
*/
        return convertView;

    }

    private void setChatRowAppearance(boolean isItMe, ViewHolder holder) {

        if (isItMe) {
            holder.params.gravity = Gravity.END;
            holder.authorName.setTextColor(Color.GREEN);
            holder.body.setBackgroundResource(R.drawable.bubble2);
        } else {
            holder.params.gravity = Gravity.START;

            holder.authorName.setTextColor(Color.parseColor("#00CCFF"));
            holder.body.setBackgroundResource(R.drawable.bubble1);
        }

        holder.authorName.setLayoutParams(holder.params);
        holder.body.setLayoutParams(holder.params);

    }


    void cleanup() {

        mDatabaseReference.removeEventListener(mListener);
    }













}