package com.jskgmail.bvpieee;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.jetradar.desertplaceholder.DesertPlaceholder;
import com.victor.loading.rotate.RotateLoading;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AllEventsActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 234;
    ;
    String TAG="ALLEVENTS";
DesertPlaceholder placeholder;
RotateLoading rotateLoading;
Bitmap fileuploadedbitmap;
    String downloadUrl="na" ;
    Uri uriii;
static String chname="all";
    private Uri filePath;
     StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_events);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_home_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);
        mStorageRef = FirebaseStorage.getInstance().getReference();

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
        FloatingActionButton addevent=findViewById(R.id.addevent);

//        FloatingActionButton deleteevent=findViewById(R.id.deleteevent);
        if (Main2Activity.verified_admin==1)
{addevent.setVisibility(View.VISIBLE);
//deleteevent.setVisibility(View.VISIBLE);
}
/*
deleteevent.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.deleteevent, null);

        final AlertDialog.Builder alert = new AlertDialog.Builder(AllEventsActivity.this);

        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        // disallow cancel of AlertDialog on click of back button and outside touch
        alert.setTitle("DELETE EVENT ");
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("All events");
final ArrayList<String> arrayListall=new ArrayList();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                String value = appleSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
                arrayListall.add(value);}
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        final ListView list = alertLayout.findViewById(R.id.list);

        // Create an ArrayAdapter from List
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (AllEventsActivity.this, android.R.layout.simple_list_item_1, arrayListall);

        // DataBind ListView with items from ArrayAdapter
        list.setAdapter(arrayAdapter);

list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        arrayListall.get(i);
        DatabaseReference aa=myRef.child(arrayListall.get(i));
aa.removeValue();




    }
});

        AlertDialog dialog = alert.create();
        dialog.show();
    }


















});
*/

        final int[] stop = {0};
        addevent.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            stop[0] =1;


                                            LayoutInflater inflater = getLayoutInflater();
                                            View alertLayout = inflater.inflate(R.layout.addevents, null);

                                            final AlertDialog.Builder alert = new AlertDialog.Builder(AllEventsActivity.this);

                                            // this is set the view from XML inside AlertDialog
                                            alert.setView(alertLayout);
                                            // disallow cancel of AlertDialog on click of back button and outside touch
                                            alert.setTitle("ADD EVENT ");
                                            alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                }
                                            });


                                            final String[] ch = new String[1];
                                            final int[] ch_pos = {6};
                                            Spinner currsemin = (Spinner) alertLayout.findViewById(R.id.spinner);

                                            List<String> category1 = new ArrayList<String>();
                                            category1.add("CS");
                                            category1.add("RAS");
                                            category1.add("IAS");
                                            category1.add("WIE");
                                            category1.add("HKN");
                                            category1.add("CODE-X");
                                            category1.add("DRISHTI");
                                            category1.add("RAU");
                                            category1.add("BQC");
                                            category1.add("E-CELL");
                                            category1.add("GAMMA");
                                            category1.add("MAKERS");
                                            category1.add("Other");

                                            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(AllEventsActivity.this, android.R.layout.simple_spinner_item, category1);
                                            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                            currsemin.setAdapter(dataAdapter1);
                                            currsemin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                                @Override
                                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                    ch[0] = (parent.getItemAtPosition(position).toString());

                                                    ch_pos[0] = position;
                                                }

                                                @Override
                                                public void onNothingSelected(AdapterView<?> parent) {

                                                }
                                            });

                                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                                            final DatabaseReference myRef = database.getReference("All events");


                                            final EditText date = alertLayout.findViewById(R.id.editText);
                                            final EditText time = alertLayout.findViewById(R.id.editText2);
                                            final EditText venue = alertLayout.findViewById(R.id.editText3);
                                            final EditText topic = alertLayout.findViewById(R.id.editText4);
                                            final EditText by = alertLayout.findViewById(R.id.editText5);
                                            final EditText reglink = alertLayout.findViewById(R.id.editText6);
                                            final Button posterpic = alertLayout.findViewById(R.id.poster);


                                            alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {


                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

int chh=0;                                                    String relink;

                                                    String byy=by.getText().toString();
                                                    String datee=date.getText().toString();
                                                    String timee=time.getText().toString();
                                                    String reglinkk=reglink.getText().toString();
                                                    String topicc=topic.getText().toString();
                                                    String venuee=venue.getText().toString();

Toast.makeText(getApplicationContext(),"Event added successfully! Come back to check!!",Toast.LENGTH_LONG).show();
                                              //      finish();

                                                    if (ch_pos[0] == 0) {
                                                        chname = "cs";chh=0;
                                                    } else if (ch_pos[0] == 1) {
                                                        chname = "ras";chh=0;
                                                    } else if (ch_pos[0] == 2) {
                                                        chname = "ias";chh=0;
                                                    } else if (ch_pos[0] == 3) {
                                                        chname = "wie";chh=0;
                                                    } else if (ch_pos[0] == 4) {
                                                        chname = "hkn";chh=0;
                                                    } else if (ch_pos[0] == 5) {
                                                        chname = "codex";chh=0;

                                                    }
                                                    else if (ch_pos[0] == 6) {
                                                        chname = "drishti";chh=0;
                                                    }else if (ch_pos[0] == 7) {
                                                        chname = "rau";chh=0;
                                                    }else if (ch_pos[0] == 8) {
                                                        chname = "bqc";chh=0;
                                                    }else if (ch_pos[0] == 9) {
                                                        chname = "ecell";chh=0;
                                                    }else if (ch_pos[0] == 10) {
                                                        chname = "gamma";chh=0;
                                                    }else if (ch_pos[0] == 11) {
                                                        chname = "makers";chh=0;
                                                    }
                                                    else if (ch_pos[0] == 12) {
                                                        chh=1;
                                                    }
                                                    DatabaseReference aa;
                                                    if (chh==1)
                                                        aa = myRef.push();
                                                    else
                                                        aa = myRef.child(chname);

                                                    {
                                                        if (reglinkk.equals(""))
                                                            relink = "na";
                                                        else
                                                            relink = reglinkk;

                                                        aa.child("by").setValue(byy);
                                                        aa.child("date").setValue(datee);
                                                        aa.child("time").setValue(timee);
                                                        aa.child("link").setValue(relink);
                                                        aa.child("pic").setValue(downloadUrl);
                                                        aa.child("topic").setValue(topicc);
                                                        aa.child("venue").setValue(venuee);
                                                    }


                                                }
                                            });


                                            posterpic.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {


                                                    Intent intent = new Intent();
                                                    intent.setType("image/*");
                                                    intent.setAction(Intent.ACTION_GET_CONTENT);
                                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

                                                    //handling the image chooser activity result


                                                    //TODO

                                                }
                                            });

                                            AlertDialog dialog = alert.create();
                                            dialog.show();
                                        }

                                    }


);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("All events");
        // DatabaseReference myRef1 = myRef.child("cs");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                if (stop[0]!=1)

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                 uriii = data.getData();
              uploadImage();

                fileuploadedbitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    private void uploadImage() {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            final StorageReference ref = mStorageRef.child("images/"+ UUID.randomUUID().toString());

            ref.putFile(filePath).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){
                        throw task.getException();

                    }
                    return ref.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_LONG).show();
                        Uri downUri = task.getResult();
                        Log.e("uuuuuuuuuuuuuuuut",String.valueOf(downUri));
                        downloadUrl=String.valueOf(downUri);

                    }
                }
            })



                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AllEventsActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }



}
