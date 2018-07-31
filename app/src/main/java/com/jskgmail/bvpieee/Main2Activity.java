package com.jskgmail.bvpieee;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener  {
    private SliderLayout mDemoSlider;
    static int chapterno;
  static   int verified_ieee=0;
    private StorageReference mStorageRef;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nextevent();

        SharedPreferences prefs = getSharedPreferences("ieee",MODE_PRIVATE);
        String ver = prefs.getString("verified", null);

        if (ver != null && ver.equals("1")) {
            verified_ieee = 1;
        }

        if (verified_ieee==0)
        checkemails();


        SharedPreferences prefs1 = getSharedPreferences("nxtevent",MODE_PRIVATE);
        String by = prefs1.getString("by", "N.A.");
        String date = prefs1.getString("date", "11/08/2018");
        String time = prefs1.getString("time", "6:30 PM");
        String topic = prefs1.getString("topic", "Deep Learning");

      //  TextView byy=findViewById(R.id.by);
        TextView datee=findViewById(R.id.date);
        TextView timee=findViewById(R.id.time);
        TextView topicc=findViewById(R.id.topic);

        datee.setText(date);
        timee.setText(time);
        topicc.setText(topic);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.history);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);








        mDemoSlider = (SliderLayout) findViewById(R.id.slider);


        HashMap<String, Integer> file_maps = new HashMap<>();

        file_maps.put("Latest ws", R.drawable.codex);
        file_maps.put("Latest ws", R.drawable.codex);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image((file_maps.get(name)))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        // mDemoSlider.addOnPageChangeListener(this);


        LinearLayout ras=findViewById(R.id.ras);
        ras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno=1;
                Intent intent=new Intent(Main2Activity.this,RasActivity.class);
                startActivity(intent);
            }
        });


        LinearLayout cs=findViewById(R.id.cs);
        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno=2;
                Intent intent=new Intent(Main2Activity.this,RasActivity.class);
                startActivity(intent);
            }
        });


        LinearLayout wie=findViewById(R.id.wie);
        wie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno=3;
                Intent intent=new Intent(Main2Activity.this,RasActivity.class);
                startActivity(intent);
            }
        });


        LinearLayout ias=findViewById(R.id.ias);
        ias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno=4;
                Intent intent=new Intent(Main2Activity.this,RasActivity.class);
                startActivity(intent);
            }
        });


        LinearLayout hkn=findViewById(R.id.hkn);
        hkn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno=5;
                Intent intent=new Intent(Main2Activity.this,RasActivity.class);
                startActivity(intent);
            }
        });








    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {



            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.myacc, null);

            TextView emailid=alertLayout.findViewById(R.id.mail);
            TextView ieeeacess=alertLayout.findViewById(R.id.ieeemem);

            if (verified_ieee==1)
            {
                ieeeacess.setText("You can access everything in the app for free !");
            }
            SharedPreferences prefs = getSharedPreferences("ieee",MODE_PRIVATE);
            String apnamail = prefs.getString("mail", null);

            emailid.setText(apnamail);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            // this is set the view from XML inside AlertDialog
            alert.setView(alertLayout);



            alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });

            AlertDialog dialog = alert.create();
            dialog.show();








            } else if (id == R.id.ress) {


            if (verified_ieee==0)
            {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.notieeemem, null);

                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                alert.setTitle("Content locked ");
                alert.setIcon(R.drawable.ic_lock_black_24dp);




                AlertDialog dialog = alert.create();
                dialog.show();

            }
            else


            startActivity(new Intent(Main2Activity.this,ResourcesActivity.class));








        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {




        }
        else if (id == R.id.spon) {

            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.spon, null);

            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            // this is set the view from XML inside AlertDialog
            alert.setView(alertLayout);
            alert.setTitle("Sponsors ");
            alert.setIcon(R.drawable.ic_whatshot_black_24dp);



            alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {


                @Override
                public void onClick(DialogInterface dialog, int which) {


                }
            });

            AlertDialog dialog = alert.create();
            dialog.show();



        } else if (id == R.id.nav_share) {

            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.about, null);
            final FloatingTextButton history = (FloatingTextButton) alertLayout.findViewById(R.id.history);
            final FloatingTextButton join = (FloatingTextButton) alertLayout.findViewById(R.id.join);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            // this is set the view from XML inside AlertDialog
            alert.setView(alertLayout);
            history.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String url = "https://www.ieee.org/about/ieee-history.html";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
            join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String url = "https://www.ieee.org/membership/join/index.html";
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
            });
            // disallow cancel of AlertDialog on click of back button and outside touch
            alert.setTitle("About ");
            alert.setIcon(R.drawable.ic_help_outline_black_24dp);



            AlertDialog dialog = alert.create();
            dialog.show();


        } else if (id == R.id.nav_send) {

            LayoutInflater inflater = getLayoutInflater();
            final View alertLayout = inflater.inflate(R.layout.contact, null);
            final FloatingTextButton fb = (FloatingTextButton) alertLayout.findViewById(R.id.history);
            final FloatingTextButton mail = (FloatingTextButton) alertLayout.findViewById(R.id.mail);
            final FloatingTextButton insta = (FloatingTextButton) alertLayout.findViewById(R.id.insta);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            // this is set the view from XML inside AlertDialog
            alert.setView(alertLayout);
            // disallow cancel of AlertDialog on click of back button and outside touch

            mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "sb.bvpieee@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Reg. BVPIEEE");
                    alertLayout.getContext().startActivity(Intent.createChooser(emailIntent, null));
                }
            });

            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(Main2Activity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });

            insta.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Uri uri = Uri.parse("http://instagram.com/_u/bvpieee");
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

                    likeIng.setPackage("com.instagram.android");

                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://instagram.com/bvpieee")));
                    }


                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {

        String FACEBOOK_URL="https://www.facebook.com/bvpieee/",FACEBOOK_PAGE_ID="bvpieee";
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }







    void checkemails()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ieee_emails");

     //   myRef.child("1").setValue("jsk1961998@gmail.com");
     //   myRef.child("2").setValue("82gurcharansingh@gmail.com");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                {
                String value = dataSnapshot1.getValue(String.class);
                    SharedPreferences prefs = getSharedPreferences("ieee",MODE_PRIVATE);
                    String apnamail = prefs.getString("mail", null);
if(value.equals(apnamail))   {
    verified_ieee=1;
    SharedPreferences.Editor editor= getSharedPreferences("ieee",MODE_PRIVATE).edit();
    editor.putString("verified","1");


    editor.apply();

}
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });



    }



void nextevent()
{
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Next event");

    final String[] nxt_by = new String[1];
    final String[] nxt_date = new String[1];
    final String[] nxt_time = new String[1];
    final String[] nxt_topic = new String[1];

    //   myRef.child("1").setValue("jsk1961998@gmail.com");
    //   myRef.child("2").setValue("82gurcharansingh@gmail.com");

    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
                if (dataSnapshot1.getKey().equals("by"))
                    nxt_by[0] = dataSnapshot1.getValue(String.class);
                if (dataSnapshot1.getKey().equals("date"))
                    nxt_date[0] = dataSnapshot1.getValue(String.class);
                 if (dataSnapshot1.getKey().equals("time"))
                     nxt_time[0] = dataSnapshot1.getValue(String.class);
                 if (dataSnapshot1.getKey().equals("topic"))
                     nxt_topic[0] = dataSnapshot1.getValue(String.class);


                 SharedPreferences.Editor editor = getSharedPreferences("nxtevent", MODE_PRIVATE).edit();
                 editor.putString("by", String.valueOf(nxt_by[0]));
                editor.putString("date", String.valueOf(nxt_date[0]));
                editor.putString("time", String.valueOf(nxt_time[0]));
                editor.putString("topic", String.valueOf(nxt_topic[0]));
                editor.apply();



            }
        }
        @Override
        public void onCancelled(DatabaseError error) {
            // Failed to read value
        }
    });



}










}
