package com.jskgmail.bvpieee;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.victor.loading.rotate.RotateLoading;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ResourcesActivity extends AppCompatActivity
{
String TAG="Resourcesaa";
static String forum="all";
    RecyclerView recyclerView;
    SearchView search;
    DatabaseReference myRef;
    RotateLoading rotateLoading;
    CustomAdapterRes adapter;
String downloadurl="na";


    private static final int PICK_IMAGE_REQUEST = 234;
    ;
    Bitmap fileuploadedbitmap;
    Uri uriii;
    private Uri filePath;
    StorageReference mStorageRef;





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
        mStorageRef = FirebaseStorage.getInstance().getReference();

rotateLoading=findViewById(R.id.rotateloading);
rotateLoading.start();

search=findViewById(R.id.search);
        recyclerView=findViewById(R.id.list);

//       final ListView listView= (ListView) findViewById(R.id.list);
        final String[] chname = new String[1];

        FloatingActionButton addresource=findViewById(R.id.addresource);

      if (Main2Activity.verified_admin==1)
        {addresource.setVisibility(View.VISIBLE);}

        addresource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.addresource, null);

                final AlertDialog.Builder alert = new AlertDialog.Builder(ResourcesActivity.this);

                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                // disallow cancel of AlertDialog on click of back button and outside touch
                alert.setTitle("ADD RESOURCE ");
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
                category1.add("Other");

                ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(ResourcesActivity.this, android.R.layout.simple_spinner_item, category1);
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
                final DatabaseReference myRef = database.getReference("ieee_videos");


                final EditText date = alertLayout.findViewById(R.id.editText);
                final EditText ylink = alertLayout.findViewById(R.id.editText2);
                final EditText name = alertLayout.findViewById(R.id.editText3);
               final Button resource= alertLayout.findViewById(R.id.res);


                alert.setPositiveButton("Set", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (ch_pos[0] == 0) {
                            chname[0] = "cs";
                        } else if (ch_pos[0] == 1) {
                            chname[0] = "ras";
                        } else if (ch_pos[0] == 2) {
                            chname[0] = "ias";
                        } else if (ch_pos[0] == 3) {
                            chname[0] = "wie";
                        } else if (ch_pos[0] == 4) {
                            chname[0] = "hkn";
                        } else if (ch_pos[0] == 5) {
                            SharedPreferences prefs = getSharedPreferences("ieee", MODE_PRIVATE);
                            chname[0] = prefs.getString("mail", null);
                        }


                        DatabaseReference aa = myRef.child(chname[0]);
                        DatabaseReference bb = aa.child(ylink.getText().toString());
                        bb.child("date").setValue(date.getText().toString());
                        bb.child("link").setValue(downloadurl);
                        bb.child("name").setValue(name.getText().toString());



                    }
                });



                resource.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent intent = new Intent();
                        intent.setType("*/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select file"), PICK_IMAGE_REQUEST);

                        //handling the image chooser activity result


                        //TODO

                    }
                });

                AlertDialog dialog = alert.create();
                dialog.show();






            }
        });








        FirebaseDatabase database = FirebaseDatabase.getInstance();
       myRef = database.getReference("ieee_videos");
        // DatabaseReference myRef1 = myRef.child("cs");


        final String[] valuename = new String[1];
        final String[] valuedat = new String[1];
        final String[] valuepdf = new String[1];
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                final ArrayList<String> arrayList1 = new ArrayList<>();
                final ArrayList<String> arrayList2 = new ArrayList<>();
                final ArrayList<String> arrayList3 = new ArrayList<>();
                final ArrayList<String> arrayList4 = new ArrayList<>();
                final ArrayList<String> arrayList5 = new ArrayList<>();


                //  arrayList3.add("cs");
                //    arrayList4.add("link");
                for (DataSnapshot dataSnapshotchap : dataSnapshot.getChildren()) {
                    String value = dataSnapshotchap.getKey();


                    for (DataSnapshot dataSnapshot1 : dataSnapshotchap.getChildren()) {


                        String value1 = dataSnapshot1.getKey();
                        DataSnapshot valuech = dataSnapshot1.child("name");


                        valuename[0] = (String) (valuech.getValue());

                        DataSnapshot valuedaeet = dataSnapshot1.child("date");


                        valuedat[0] = (String) (valuedaeet.getValue());
                        DataSnapshot valuepdff = dataSnapshot1.child("link");


                        valuepdf[0] = (String) (valuepdff.getValue());

                            if (value.equals(forum)) {
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

                            } else if (forum.equals("all")) {


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


                rotateLoading.stop();
                adapter = new CustomAdapterRes(ResourcesActivity.this, arrayList1, arrayList2, arrayList3, arrayList4, arrayList5);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                recyclerView.setLayoutManager(new LinearLayoutManager(ResourcesActivity.this, LinearLayoutManager.VERTICAL, false));
                recyclerView.addItemDecoration(new DividerItemDecoration(ResourcesActivity.this, DividerItemDecoration.VERTICAL));
                recyclerView.setItemAnimator(new DefaultItemAnimator());


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });









































        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String text = newText;
                Log.v("sosos", text);
                newText = newText.toLowerCase();


                Log.e("sssssss","kdudh");

                final String finalNewText = newText;

                Log.e("sssssss",newText);
                final String[] valuename = new String[1];
                final String[] valuedat = new String[1];
                final String[] valuepdf = new String[1];
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        final ArrayList<String> arrayList1 = new ArrayList<>();
                        final ArrayList<String> arrayList2 = new ArrayList<>();
                        final ArrayList<String> arrayList3 = new ArrayList<>();
                        final ArrayList<String> arrayList4 = new ArrayList<>();
                        final ArrayList<String> arrayList5 = new ArrayList<>();


                        //  arrayList3.add("cs");
                        //    arrayList4.add("link");
                        for (DataSnapshot dataSnapshotchap : dataSnapshot.getChildren()) {
                            String value = dataSnapshotchap.getKey();


                            for (DataSnapshot dataSnapshot1 : dataSnapshotchap.getChildren()) {


                                String value1 = dataSnapshot1.getKey();
                                DataSnapshot valuech = dataSnapshot1.child("name");


                                valuename[0] = (String) (valuech.getValue());

                                DataSnapshot valuedaeet = dataSnapshot1.child("date");


                                valuedat[0] = (String) (valuedaeet.getValue());
                                DataSnapshot valuepdff = dataSnapshot1.child("link");


                                valuepdf[0] = (String) (valuepdff.getValue());

                                if (valuename[0].toLowerCase().contains(finalNewText)) {
                                    if (value.equals(forum)) {
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

                                    } else if (forum.equals("all")) {


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
                        }


                        adapter = new CustomAdapterRes(ResourcesActivity.this, arrayList1, arrayList2, arrayList3, arrayList4, arrayList5);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        recyclerView.setLayoutManager(new LinearLayoutManager(ResourcesActivity.this, LinearLayoutManager.VERTICAL, false));
                        recyclerView.addItemDecoration(new DividerItemDecoration(ResourcesActivity.this, DividerItemDecoration.VERTICAL));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());


                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });

                return false;
            }        });





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

            final StorageReference ref = mStorageRef.child("files/"+ UUID.randomUUID().toString());

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
                        downloadurl=String.valueOf(downUri);

                    }
                }
            })



                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(ResourcesActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }


















}


















