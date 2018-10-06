package com.jskgmail.bvpieee;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.victor.loading.rotate.RotateLoading;
import com.wajahatkarim3.easyflipview.EasyFlipView;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import ru.dimorinny.floatingtextbutton.FloatingTextButton;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, BaseSliderView.OnSliderClickListener {
    private SliderLayout mDemoSlider;
    static int chapterno = 99;
    static int verified_ieee = 0;
    private StorageReference mStorageRef;
    ScrollView scroll;
    TextView chapters1;
    TextView chapters2,
            chapters3,
            chapters4,
            chapters5;
    Boolean upd = false;
    TextView sigs1,
            sigs2,
            sigs3,
            sigs4,
            sigs5,
            sigs6,
            sigs7;
    Boolean upd2 = false;
   static int verified_admin=0;

    private static final int PICK_IMAGE_REQUEST = 234;
    ;
    String TAG="ALLEVENTS";
    RotateLoading rotateLoading;
    Bitmap fileuploadedbitmap;
    String downloadlinkads="na" ;
    Uri uriii;
    static String chname="all";
    private Uri filePath;

    RelativeLayout info_announce;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //  info_announce=findViewById(R.id.rrr);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //   startService(new Intent(this, MyFirebaseMessagingService.class));
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        //FirebaseMessaging.getInstance().unsubscribeFromTopic("news");
        //   FirebaseMessaging.getInstance().subscribeToTopic("news1");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create channel to show notifications.
            String channelId = getString(R.string.default_notification_channel_id);
            String channelName = getString(R.string.default_notification_channel_name);
            NotificationManager notificationManager =
                    getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                        channelName, NotificationManager.IMPORTANCE_LOW));
            }
        }

        mStorageRef = FirebaseStorage.getInstance().getReference();

        scroll = findViewById(R.id.scroll);
        chapters1 = (TextView) findViewById(R.id.chapters1);
        chapters2 = (TextView) findViewById(R.id.chapters2);
        chapters3 = findViewById(R.id.chapters3);
        chapters4 = findViewById(R.id.chapters4);
        chapters5 = findViewById(R.id.chapters5);
        sigs1 = findViewById(R.id.sigs1);
        sigs2 = findViewById(R.id.sigs2);
        sigs3 = findViewById(R.id.sigs3);
        sigs4 = findViewById(R.id.sigs4);
        sigs5 = findViewById(R.id.sigs5);
        sigs6 = findViewById(R.id.sigs6);
        // sigs7 =(ImageView ) findViewById(R.id.sigs7);

        TextView other = findViewById(R.id.other);
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllEventsActivity.chname = "all";
                startActivity(new Intent(Main2Activity.this, AllEventsActivity.class));
            }
        });


        nextevent();

        SharedPreferences prefs = getSharedPreferences("ieee", MODE_PRIVATE);
        String ver = prefs.getString("verified", null);

        if (ver != null && ver.equals("1")) {
            verified_ieee = 1;
            startService(new Intent(this, MyServicemsg.class));


        }

        String ver1 = prefs.getString("admin", null);

        if (ver1 != null && ver1.equals("1")) {
            verified_admin = 1;
        }

        TextView addnextevent = findViewById(R.id.setevent);
        TextView addieee = findViewById(R.id.addieee);
        TextView adads = findViewById(R.id.addads);


        if (verified_ieee == 0&&verified_admin==0)
            checkemails();

        if (verified_admin==1) {
            addieee.setVisibility(View.VISIBLE);
            addnextevent.setVisibility(View.VISIBLE);
adads.setVisibility(View.VISIBLE);
admins();
        }

admins();
        SharedPreferences prefs1 = getSharedPreferences("nxtevent", MODE_PRIVATE);
        String desc = prefs1.getString("desc", "N.A.");
        String date = prefs1.getString("date", "11/08/2018");
        String time = prefs1.getString("time", "6:30 PM");
        String topic = prefs1.getString("topic", "Deep Learning");
        //  TextView byy=findViewById(R.id.by);

        TextView datee = findViewById(R.id.date);
        TextView timee = findViewById(R.id.time);
        TextView topicc = findViewById(R.id.topic);
        TextView desc_txt = findViewById(R.id.desc);

        desc_txt.setText(desc);
        datee.setText(date);
        timee.setText(time);
        topicc.setText(topic);
        //    timee.setText(time);

     /*   EasyFlipView easyFlipView = (EasyFlipView) findViewById(R.id.easyflip);
     //   EasyFlipView.FlipState flipSide = easyFlipView.getCurrentFlipState();

        easyFlipView.flipTheView();
        easyFlipView.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide)
            {

                // ...
                // Your code goes here
                // ...

            }
        });

*/
        EasyFlipView easyFlipView = (EasyFlipView) findViewById(R.id.easyflip);
        //   EasyFlipView.FlipState flipSide = easyFlipView.getCurrentFlipState();

        easyFlipView.flipTheView();
        easyFlipView.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide) {

                // ...
                // Your code goes here
                // ...

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

        file_maps.put("BVPIEE-CS workshop", R.drawable.h1);
        file_maps.put("BVPIEE-RAS", R.drawable.h2);
        file_maps.put("BVPIEE-RAS ", R.drawable.h3);
        file_maps.put("FOC", R.drawable.foc171);
        file_maps.put("Fervour-X", R.drawable.fervour1);
        file_maps.put("BVPIEE-RAS  ", R.drawable.h6);
        file_maps.put(" BVPIEE-RAS ", R.drawable.h7);
        file_maps.put("BVEST", R.drawable.bvest1);
        file_maps.put("Fervour-X ", R.drawable.fervour2);


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


        //   mDemoSlider1 = (SliderLayout) findViewById(R.id.slider1);


        HashMap<String, Integer> file_maps1 = new HashMap<>();

        file_maps1.put("BVPIEE-CS workshop", R.drawable.h1);
        file_maps1.put("BVPIEE-RAS", R.drawable.h2);
        file_maps1.put("BVPIEE-RAS ", R.drawable.h3);
        file_maps1.put("FOC", R.drawable.foc171);


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


        go1();


        // mDemoSlider.addOnPageChangeListener(this);


//        LinearLayout ras=findViewById(R.id.ras);
        chapters1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno = 1;
                Intent intent = new Intent(Main2Activity.this, RasActivity.class);
                startActivity(intent);

            }
        });


        //   LinearLayout cs=findViewById(R.id.cs);
        chapters2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno = 2;
                Intent intent = new Intent(Main2Activity.this, RasActivity.class);
                startActivity(intent);
            }
        });


        //   LinearLayout wie=findViewById(R.id.wie);
        chapters3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno = 4;
                Intent intent = new Intent(Main2Activity.this, RasActivity.class);
                startActivity(intent);
            }
        });


        //    LinearLayout ias=findViewById(R.id.ias);
        chapters4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno = 5;
                Intent intent = new Intent(Main2Activity.this, RasActivity.class);
                startActivity(intent);
            }
        });


        //   LinearLayout hkn=findViewById(R.id.hkn);
        chapters5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno = 3;
                Intent intent = new Intent(Main2Activity.this, RasActivity.class);
                startActivity(intent);
            }
        });

        //      LinearLayout codex=findViewById(R.id.codex);
        sigs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno = 6;
                Intent intent = new Intent(Main2Activity.this, RasActivity.class);
                startActivity(intent);
            }
        });

        //   LinearLayout drishti=findViewById(R.id.drishti);
        sigs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno = 7;
                Intent intent = new Intent(Main2Activity.this, RasActivity.class);
                startActivity(intent);
            }
        });

        //  LinearLayout rau=findViewById(R.id.rau);
        sigs3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno = 8;
                Intent intent = new Intent(Main2Activity.this, RasActivity.class);
                startActivity(intent);
            }
        });

        //   LinearLayout ecell=findViewById(R.id.ecell);
        sigs4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno = 11;
                Intent intent = new Intent(Main2Activity.this, RasActivity.class);
                startActivity(intent);
            }
        });

        //   LinearLayout gamma=findViewById(R.id.gamma);
        sigs5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno = 9;
                Intent intent = new Intent(Main2Activity.this, RasActivity.class);
                startActivity(intent);
            }
        });


        //  LinearLayout bqc=findViewById(R.id.bqc);
        sigs6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterno = 10;
                Intent intent = new Intent(Main2Activity.this, RasActivity.class);
                startActivity(intent);
            }
        });


    }

    public void clicked(View view) {
        if (upd == false) {
            //Toast.makeText(this,"In false",Toast.LENGTH_LONG).show();
            chapters1.setVisibility(View.VISIBLE);
            chapters2.setVisibility(View.VISIBLE);
            chapters3.setVisibility(View.VISIBLE);
            chapters4.setVisibility(View.VISIBLE);
            chapters5.setVisibility(View.VISIBLE);

            scroll.post(new Runnable() {
                @Override
                public void run() {
                    //     scroll.fullScroll(View.FOCUS_DOWN);
                    scroll.smoothScrollTo(0, chapters5.getBottom());

                }
            });


            upd = true;
            upd2 = false;
            sigs1.setVisibility(View.GONE);
            sigs2.setVisibility(View.GONE);
            sigs3.setVisibility(View.GONE);
            sigs4.setVisibility(View.GONE);
            sigs5.setVisibility(View.GONE);
            sigs6.setVisibility(View.GONE);

        } else {
            // Toast.makeText(this,"In true",Toast.LENGTH_LONG).show();
            chapters1.setVisibility(View.GONE);
            chapters2.setVisibility(View.GONE);
            chapters3.setVisibility(View.GONE);
            chapters4.setVisibility(View.GONE);
            chapters5.setVisibility(View.GONE);
            upd = false;

        }
    }

    public void clicked2(final View view) {
        if (upd2 == false) {
            // Toast.makeText(this,"In false",Toast.LENGTH_LONG).show();
            sigs1.setVisibility(View.VISIBLE);
            sigs2.setVisibility(View.VISIBLE);
            sigs3.setVisibility(View.VISIBLE);
            sigs4.setVisibility(View.VISIBLE);
            sigs5.setVisibility(View.VISIBLE);
            sigs6.setVisibility(View.VISIBLE);
            //    sigs7.setVisibility(View.VISIBLE);
            upd2 = true;
            upd = false;
            chapters1.setVisibility(View.GONE);
            chapters2.setVisibility(View.GONE);
            chapters3.setVisibility(View.GONE);
            chapters4.setVisibility(View.GONE);
            chapters5.setVisibility(View.GONE);
            scroll.post(new Runnable() {
                @Override
                public void run() {
                    //   scroll.fullScroll(View.FOCUS_DOWN);
                    scroll.smoothScrollTo(0, sigs6.getBottom());
                }
            });


        } else {
            //   Toast.makeText(this,"In true",Toast.LENGTH_LONG).show();
            sigs1.setVisibility(View.GONE);
            sigs2.setVisibility(View.GONE);
            sigs3.setVisibility(View.GONE);
            sigs4.setVisibility(View.GONE);
            sigs5.setVisibility(View.GONE);
            sigs6.setVisibility(View.GONE);
            //   sigs7.setVisibility(View.GONE);
            upd2 = false;
        }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {


            LayoutInflater inflater = getLayoutInflater();
            View alertLayout = inflater.inflate(R.layout.myacc, null);

            TextView emailid = alertLayout.findViewById(R.id.mail);
            TextView ieeeacess = alertLayout.findViewById(R.id.ieeemem);
            ImageView close = alertLayout.findViewById(R.id.close);
            if (verified_ieee == 1) {
                ieeeacess.setText("You can access everything in the app for free !");
            }
            SharedPreferences prefs = getSharedPreferences("ieee", MODE_PRIVATE);
            String apnamail = prefs.getString("mail", null);

            emailid.setText(apnamail);
            AlertDialog.Builder alert = new AlertDialog.Builder(this);

            // this is set the view from XML inside AlertDialog
            alert.setView(alertLayout);


            final AlertDialog dialog = alert.create();
            dialog.show();
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.cancel();
                }
            });


        } else if (id == R.id.ress) {


            if (verified_ieee == 0) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.notieeemem, null);

                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);


                AlertDialog dialog = alert.create();
                dialog.show();

            } else

            {
                Intent i = (new Intent(Main2Activity.this, ResourcesActivity.class));
                ResourcesActivity.forum = "all";
                startActivity(i);

            }


        } else if (id == R.id.nav_slideshow) {

            if (verified_ieee == 0) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.notieeemem, null);

                AlertDialog.Builder alert = new AlertDialog.Builder(this);

                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);


                AlertDialog dialog = alert.create();
                dialog.show();

            } else


            {
                Intent i = new Intent(Main2Activity.this, MainChatActivity.class);
                i.putExtra("forum", "gen");
                startActivity(i);

            }


        } else if (id == R.id.nav_manage) {


            startActivity(new Intent(Main2Activity.this, Main3Activity.class));


        } else if (id == R.id.nav_gallery) {
            AllEventsActivity.chname = "all";

            Intent i = (new Intent(Main2Activity.this, AllEventsActivity.class));
            startActivity(i);

        }
    /*    else if (id == R.id.spon) {

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



        } */
        else if (id == R.id.nav_share) {

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
            //        alert.setTitle("About ");
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

        String FACEBOOK_URL = "https://www.facebook.com/bvpieee/", FACEBOOK_PAGE_ID = "bvpieee";
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


    void checkemails() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("ieee_emails");

        //   myRef.child("1").setValue("jsk1961998@gmail.com");
        //   myRef.child("2").setValue("82gurcharansingh@gmail.com");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String value = dataSnapshot1.getValue(String.class);
                    SharedPreferences prefs = getSharedPreferences("ieee", MODE_PRIVATE);
                    String apnamail = prefs.getString("mail", null);
                    assert value != null;
                    if (value.equals(apnamail)) {
                        verified_ieee = 1;
                        SharedPreferences.Editor editor = getSharedPreferences("ieee", MODE_PRIVATE).edit();
                        editor.putString("verified", "1");
                        editor.apply();

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


        DatabaseReference myRef1 = database.getReference("admin");

        //   myRef.child("1").setValue("jsk1961998@gmail.com");
        //   myRef.child("2").setValue("82gurcharansingh@gmail.com");

        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String value = dataSnapshot1.getValue(String.class);
                    SharedPreferences prefs = getSharedPreferences("ieee", MODE_PRIVATE);
                    String apnamail = prefs.getString("mail", null);
                    assert value != null;
                    if (value.equals(apnamail)) {
                        verified_admin = 1;
                        SharedPreferences.Editor editor = getSharedPreferences("ieee", MODE_PRIVATE).edit();
                        editor.putString("admin", "1");


                        editor.apply();

                        TextView addnextevent = findViewById(R.id.setevent);
                        TextView addieee = findViewById(R.id.addieee);
                        TextView adads = findViewById(R.id.addads
                        );

                            addieee.setVisibility(View.VISIBLE);
                            addnextevent.setVisibility(View.VISIBLE);
adads.setVisibility(View.VISIBLE);


                        }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main2_drawer1, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.setting) {

            Intent i = new Intent(Main2Activity.this, MainSettingActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    void nextevent() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        DatabaseReference myRef = database.getReference("Next event");

        final String[] nxt_date = new String[1];
        final String[] nxt_time = new String[1];
        final String[] nxt_topic = new String[1];
        final String[] nxt_desc = new String[1];

        //   myRef.child("1").setValue("jsk1961998@gmail.com");
        //   myRef.child("2").setValue("82gurcharansingh@gmail.com");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    if (dataSnapshot1.getKey().equals("date"))
                        nxt_date[0] = dataSnapshot1.getValue(String.class);
                    if (dataSnapshot1.getKey().equals("time"))
                        nxt_time[0] = dataSnapshot1.getValue(String.class);
                    if (dataSnapshot1.getKey().equals("topic"))
                        nxt_topic[0] = dataSnapshot1.getValue(String.class);
                    if (dataSnapshot1.getKey().equals("desc"))
                        nxt_desc[0] = dataSnapshot1.getValue(String.class);


                    SharedPreferences.Editor editor = getSharedPreferences("nxtevent", MODE_PRIVATE).edit();
                    editor.putString("date", String.valueOf(nxt_date[0]));
                    editor.putString("time", String.valueOf(nxt_time[0]));
                    editor.putString("topic", String.valueOf(nxt_topic[0]));
                    editor.putString("desc", String.valueOf(nxt_desc[0]));

                    editor.apply();


                    TextView datee = findViewById(R.id.date);
                    TextView timee = findViewById(R.id.time);
                    TextView topicc = findViewById(R.id.topic);
                    TextView desc_txt = findViewById(R.id.desc);

                    desc_txt.setText(nxt_desc[0]);
                    datee.setText(nxt_date[0]);
                    //      timee.setText(time);
                    topicc.setText(nxt_topic[0]);
                    timee.setText(nxt_time[0]);


                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    /*
    String channelId  = getString(R.string.default_notification_channel_id);
    String channelName = getString(R.string.default_notification_channel_name);
    NotificationManager notificationManager =
            getSystemService(NotificationManager.class);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        notificationManager.createNotificationChannel(new NotificationChannel(channelId,
                channelName, NotificationManager.IMPORTANCE_LOW));
    }
    FirebaseMessaging.getInstance().subscribeToTopic("notification")
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (!task.isSuccessful()) {
                    }
                }
            });


*/

    }


    void go1() {

        final TextView sp = findViewById(R.id.sp);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final ImageView adpic = findViewById(R.id.adpic);

        final String[] ads = {""};
        final String[] link = {""};

        DatabaseReference myRef = database.getReference("ad");


        //   myRef.child("1").setValue("jsk1961998@gmail.com");
        //   myRef.child("2").setValue("82gurcharansingh@gmail.com");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                    if (dataSnapshot1.getKey().equals("ad")) {
                        ads[0] = dataSnapshot1.getValue(String.class);
                        if (ads[0] != "") {
                            sp.setVisibility(View.VISIBLE);
                            adpic.setVisibility(View.VISIBLE);

                            Glide.with(getApplicationContext()).load(ads[0]).into(adpic);
                        } else {
                            sp.setVisibility(View.GONE);
                            adpic.setVisibility(View.GONE);
                        }


                    }
                    if (dataSnapshot1.getKey().equals("link")) {
                        link[0] = dataSnapshot1.getValue(String.class);
                        if (!link[0].equals("")) {

                            adpic.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent i = new Intent(Intent.ACTION_VIEW);
                                    i.setData(Uri.parse(link[0]));
                                    startActivity(i);

                                }
                            });
                        }
                    }


                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


    }


    void admins() {
        TextView addnextevent = findViewById(R.id.setevent);
        final TextView addieee = findViewById(R.id.addieee);
        final TextView addads = findViewById(R.id.addads);

        SharedPreferences prefs = getSharedPreferences("ieee", MODE_PRIVATE);
        final String apnamail = prefs.getString("mail", null);

        addnextevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.addnextevent, null);

                final TextView date = alertLayout.findViewById(R.id.editText);
                final TextView time = alertLayout.findViewById(R.id.editText2);
                final TextView venue = alertLayout.findViewById(R.id.editText3);
                final TextView topic = alertLayout.findViewById(R.id.editText4);
                final TextView description = alertLayout.findViewById(R.id.editText5);

                final Button done = alertLayout.findViewById(R.id.done);

                AlertDialog.Builder alert = new AlertDialog.Builder(Main2Activity.this);

                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);


                final AlertDialog dialog = alert.create();
                dialog.show();
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        if (verified_admin == 1) {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("Next event");

                            myRef.child("date").setValue(date.getText().toString());
                            myRef.child("time").setValue(time.getText().toString());
                            myRef.child("topic").setValue(topic.getText().toString());
                            myRef.child("desc").setValue(description.getText().toString());
                            myRef.child("venue").setValue(venue.getText().toString());


                            dialog.cancel();

                        }

                    }
                });

                         }


        });




        addieee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.addieeemail, null);

                final EditText emailid = alertLayout.findViewById(R.id.mail);
                //  final ImageView close = alertLayout.findViewById(R.id.close);
                Button done = alertLayout.findViewById(R.id.done);

                AlertDialog.Builder alert = new AlertDialog.Builder(Main2Activity.this);

                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);


                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        {
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("ieee_emails");
                            assert apnamail != null;
                            myRef.child(String.valueOf(myRef.hashCode())).setValue(emailid.getText().toString());

                        }

                    }
                });

                final AlertDialog dialog = alert.create();
                dialog.show();


            }
        });




        addads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LayoutInflater inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.addads, null);

                final EditText adss = alertLayout.findViewById(R.id.website);
                //  final ImageView close = alertLayout.findViewById(R.id.close);
                Button done = alertLayout.findViewById(R.id.done);

                Button adpic = alertLayout.findViewById(R.id.adpic);

                AlertDialog.Builder alert = new AlertDialog.Builder(Main2Activity.this);

                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);

                adpic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);


                    }
                });
                final AlertDialog dialog=alert.create();
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        {                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("ad");
                            myRef.child("ad").setValue(downloadlinkads);
                            myRef.child("link").setValue( adss.getText().toString());
                            dialog.cancel();
                            Toast.makeText(getApplicationContext(),"Ad added",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                dialog.show();


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

            final StorageReference ref = mStorageRef.child("ads/"+ UUID.randomUUID().toString());

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
                        downloadlinkads=String.valueOf(downUri);

                    }
                }
            })



                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(Main2Activity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }















}