package com.jskgmail.bvpieee;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jetradar.desertplaceholder.DesertPlaceholder;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.Objects;

public class AllEventsActivity extends AppCompatActivity {
String TAG="ALLEVENTS";
DesertPlaceholder placeholder;
RotateLoading rotateLoading;
static String chname="all";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);

        rotateLoading=findViewById(R.id.rotateloading);

        rotateLoading.start();
        placeholder=findViewById(R.id.placeholder);
        final ListView listView= (ListView) findViewById(R.id.lv);
        final ArrayList<String> arrayList1=new ArrayList<>();
        final ArrayList<String> arrayList2=new ArrayList<>();
        final ArrayList<String> arrayList3=new ArrayList<>();
        final ArrayList<String> arrayList4=new ArrayList<>();
        final ArrayList<String> arrayList5=new ArrayList<>();
        final ArrayList<String> arrayList6=new ArrayList<>();
        final ArrayList<String> arrayList7=new ArrayList<>();










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
                  //  String value = dataSnapshotchap.getKey();

                 //   Log.e(TAG, "Valuechap is: " + value);
                    if(dataSnapshotchap.getKey().equals(chname))
                    {
                      //  Toast.makeText(getApplicationContext(),dataSnapshotchap.getKey()+"::"+chname,Toast.LENGTH_SHORT).show();

                        for (DataSnapshot dataSnapshot1 : dataSnapshotchap.getChildren()) {

                            {


                                switch (Objects.requireNonNull(dataSnapshot1.getKey())) {
                                    case "by":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList3.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "date":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList2.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "time":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList4.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "topic":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                            arrayList1.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "venue":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList5.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "pic":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList6.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "link":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList7.add(dataSnapshot1.getValue(String.class));
                                        break;
                                }




                            }
                        }}
                            else if(chname.equals("all")){


                        for (DataSnapshot dataSnapshot1 : dataSnapshotchap.getChildren()) {

                            {
                                switch (Objects.requireNonNull(dataSnapshot1.getKey())) {
                                    case "by":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList3.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "date":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList2.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "time":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList4.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "topic":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList1.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "venue":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList5.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "pic":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList6.add(dataSnapshot1.getValue(String.class));
                                        break;
                                    case "link":
                                        if (!Objects.equals(dataSnapshot1.getValue(String.class), ""))
                                        arrayList7.add(dataSnapshot1.getValue(String.class));
                                        break;
                                }





                            }
                        }

                    }
                }

                rotateLoading.stop();


                if (arrayList2.size()>0)
                    for (int i = 0; i < arrayList2.size(); ++i)
                    {

                        for (int j = i + 1; j < arrayList2.size(); ++j)
                        {

                            if(Integer.parseInt(arrayList2.get(j).replace("/",""))<Integer.parseInt(arrayList2.get(i).replace("/","")))
                            {
                                Log.e("dddddd",Integer.parseInt(arrayList2.get(i).replace("/",""))+"");

                                String l=arrayList2.get(i);
                                String l1=arrayList1.get(i);
                                String l7=arrayList7.get(i);
                                String l3=arrayList3.get(i);
                                String l4=arrayList4.get(i);
                                String l5=arrayList5.get(i);
                                String l6=arrayList6.get(i);

                                arrayList2.set(i,arrayList2.get(j));
                                arrayList2.set((j),l);

                                arrayList1.set(i,arrayList1.get(j));
                                arrayList1.set((j),l1);

                                arrayList3.set(i,arrayList3.get(j));
                                arrayList3.set((j),l3);

                                arrayList4.set(i,arrayList4.get(j));
                                arrayList4.set((j),l4);

                                arrayList5.set(i,arrayList5.get(j));
                                arrayList5.set((j),l5);

                                arrayList6.set(i,arrayList6.get(j));
                                arrayList6.set((j),l6);

                                arrayList7.set(i,arrayList7.get(j));
                                arrayList7.set((j),l7);


                            }

                         }

                        }











                ListViewAdapter1 adapter=new ListViewAdapter1(AllEventsActivity.this,arrayList1,arrayList2,
                        arrayList3,arrayList4,arrayList5,arrayList6,arrayList7);
                listView.setAdapter(adapter);

                if (arrayList1.isEmpty())
                {
                    placeholder.setVisibility(View.VISIBLE);
                }
                else
                    placeholder.setVisibility(View.GONE);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




















    }
}
