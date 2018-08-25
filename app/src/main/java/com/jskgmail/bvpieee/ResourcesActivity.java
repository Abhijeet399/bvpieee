package com.jskgmail.bvpieee;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResourcesActivity extends AppCompatActivity {
String TAG="Resourcesaa";
static String forum;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);
     //   Intent intent = getIntent();

      //  forum = intent.getStringExtra("forum");




        recyclerView=findViewById(R.id.list);

//       final ListView listView= (ListView) findViewById(R.id.list);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ieee_videos");
       // DatabaseReference myRef1 = myRef.child("cs");

        final String[] valuename = new String[1];
        final String[] valuedat = new String[1];
        final String[] valuepdf = new String[1];
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                final ArrayList<String> arrayList1=new ArrayList<>();
                final ArrayList<String> arrayList2=new ArrayList<>();
                final ArrayList<String> arrayList3=new ArrayList<>();
                final ArrayList<String> arrayList4=new ArrayList<>();
                final ArrayList<String> arrayList5=new ArrayList<>();



                //  arrayList3.add("cs");
                //    arrayList4.add("link");
                for(DataSnapshot dataSnapshotchap:dataSnapshot.getChildren()) {
                    String value = dataSnapshotchap.getKey();


                    for (DataSnapshot dataSnapshot1 : dataSnapshotchap.getChildren()) {



                        String value1 = dataSnapshot1.getKey();
                        DataSnapshot valuech=dataSnapshot1.child("name");


                        valuename[0] = (String)(valuech.getValue());

                        DataSnapshot valuedaeet=dataSnapshot1.child("date");


                        valuedat[0] = (String)(valuedaeet.getValue());
                        DataSnapshot valuepdff=dataSnapshot1.child("link");


                        valuepdf[0] = (String)(valuepdff.getValue());






                            if (value.equals(forum))
                            {
                            arrayList3.add(value);
                                Log.e(TAG, "Valuechap is: " + value);

                            Log.e(TAG, "Value1 link: " + value1);
                            arrayList4.add(value1);
                            Log.e(TAG, "Value topic: " + valuename[0]);
                            arrayList1.add(valuename[0]);
                           Log.e(TAG, "Value1 date " + valuedat[0]);
                           arrayList2.add(valuedat[0]);
                                Log.e(TAG, "Value1 pdf " + valuepdf[0]);

                           arrayList5.add(valuepdf[0]);

                        }
                        else  if (forum.equals("all"))
                            {


                                arrayList3.add(value);

                                Log.e(TAG, "Value1 link: " + value1);
                                arrayList4.add(value1);
                                Log.e(TAG, "Value topic: " + valuename[0]);
                                arrayList1.add(valuename[0]);
                                Log.e(TAG, "Value1 date " + valuedat[0]);
                                arrayList2.add(valuedat[0]);

                                arrayList5.add(valuepdf[0]);

                            }





                    }
                }





            CustomAdapterRes    adapter= new CustomAdapterRes(ResourcesActivity.this,arrayList1,arrayList2,arrayList3,arrayList4,arrayList5);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                recyclerView.setLayoutManager(new LinearLayoutManager(ResourcesActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.addItemDecoration(new DividerItemDecoration(ResourcesActivity.this, DividerItemDecoration.VERTICAL));
                recyclerView.setItemAnimator(new DefaultItemAnimator());

        //        ListViewAdapter adapter=new ListViewAdapter(ResourcesActivity.this,arrayList1,arrayList2,arrayList3,arrayList4);
          //      listView.setAdapter(adapter);

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
