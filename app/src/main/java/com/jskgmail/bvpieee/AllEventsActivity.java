package com.jskgmail.bvpieee;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllEventsActivity extends AppCompatActivity {
String TAG="ALLEVENTS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);

        final ListView listView= (ListView) findViewById(R.id.lv);
        final ArrayList<String> arrayList1=new ArrayList<>();
        final ArrayList<String> arrayList2=new ArrayList<>();
        final ArrayList<String> arrayList3=new ArrayList<>();
        final ArrayList<String> arrayList4=new ArrayList<>();
        final ArrayList<String> arrayList5=new ArrayList<>();










        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("All events");
        // DatabaseReference myRef1 = myRef.child("cs");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                //  arrayList3.add("cs");
                //    arrayList4.add("link");
                for(DataSnapshot dataSnapshotchap:dataSnapshot.getChildren()) {
                   // String value = dataSnapshotchap.getKey();

                 //   Log.e(TAG, "Valuechap is: " + value);

                    for (DataSnapshot dataSnapshot1 : dataSnapshotchap.getChildren()) {

                        if (dataSnapshot1.getKey().equals("by"))
                            arrayList3.add(dataSnapshot1.getValue(String.class));
else   if (dataSnapshot1.getKey().equals("date"))
                            arrayList2.add(dataSnapshot1.getValue(String.class));
else
                        if (dataSnapshot1.getKey().equals("time"))
                            arrayList4.add(dataSnapshot1.getValue(String.class));
else   if (dataSnapshot1.getKey().equals("topic"))
                            arrayList1.add(dataSnapshot1.getValue(String.class));
else   if (dataSnapshot1.getKey().equals("venue"))
                            arrayList5.add(dataSnapshot1.getValue(String.class));




                    }
                }

                ListViewAdapter1 adapter=new ListViewAdapter1(AllEventsActivity.this,arrayList1,arrayList2,arrayList3,arrayList4,arrayList5);
                listView.setAdapter(adapter);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




















    }
}
