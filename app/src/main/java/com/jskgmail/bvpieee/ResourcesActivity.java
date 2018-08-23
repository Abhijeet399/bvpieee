package com.jskgmail.bvpieee;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResourcesActivity extends AppCompatActivity {
String TAG="Resourcesaa";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);



       final ListView listView= (ListView) findViewById(R.id.list);
        final ArrayList<String> arrayList1=new ArrayList<>();
        final ArrayList<String> arrayList2=new ArrayList<>();
        final ArrayList<String> arrayList3=new ArrayList<>();
        final ArrayList<String> arrayList4=new ArrayList<>();








        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ieee_videos");
       // DatabaseReference myRef1 = myRef.child("cs");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                //  arrayList3.add("cs");
                //    arrayList4.add("link");
                for(DataSnapshot dataSnapshotchap:dataSnapshot.getChildren()) {
                    String value = dataSnapshotchap.getKey();

                    Log.e(TAG, "Valuechap is: " + value);

                    for (DataSnapshot dataSnapshot1 : dataSnapshotchap.getChildren()) {
                        String value1 = dataSnapshot1.getKey();
                        for (DataSnapshot dataSnapshot11 : dataSnapshot1.getChildren()) {
                            String value2 = dataSnapshot11.getKey();
                            String value22 =  dataSnapshot11.getValue(String.class);
                            arrayList3.add(value);

                            Log.e(TAG, "Value1 link: " + value1);
                            arrayList4.add(value1);
                            Log.e(TAG, "Value topic: " + value2);
                            arrayList1.add(value2);
                           Log.e(TAG, "Value1 date " + value22);
                           arrayList2.add(value22);

                        }


                    }
                }

                ListViewAdapter adapter=new ListViewAdapter(ResourcesActivity.this,arrayList1,arrayList2,arrayList3,arrayList4);
                listView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

























        //   myRef.child("2").setValue("82gurcharansingh@gmail.com");














    }



    }
