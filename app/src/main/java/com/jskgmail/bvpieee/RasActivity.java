package com.jskgmail.bvpieee;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.HashMap;

public class RasActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {
    private SliderLayout mDemoSlider;
    public static String FACEBOOK_URL ;
    public static String FACEBOOK_PAGE_ID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ras);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView chaptext=findViewById(R.id.chapname);
CardView event=findViewById(R.id.event);
CardView res=findViewById(R.id.reso);
CardView dis=findViewById(R.id.discuss);
        CardView abt=findViewById(R.id.abt);
        CardView fb=findViewById(R.id.fb);

     //   TextView chaptertext=findViewById(R.id.chaptertext);
    //    FloatingTextButton events=findViewById(R.id.history);
     //   FloatingTextButton fb=findViewById(R.id.fab1);

        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RasActivity.this,AllEventsActivity.class));
            }
        });

        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RasActivity.this,ResourcesActivity.class));
            }
        });


        abt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RasActivity.this,AboutchapActivity.class));
            }
        });

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);
        HashMap<String, Integer> file_maps = new HashMap<>();








        if (Main2Activity.chapterno==1){
            //chaptertext.setText("The Robotics and Automation Society (BVPIEEE RAS) is a professional society that supports the development and the exchange of scientific knowledge in the fields of robotics and automation, including applied and theoretical issues. The RAS chapter was founded in 2014 by Shivam Bhardwaj . Over the years, it has organized several competitions like Robo Soccor, Robotic Guardians of the Galaxy, Robotic Mazes, Arcades, Robo Rugby etc. which saw an overwhelming participation. It has fostered students for inspiring them to pursue further studies in robotics and related fields from top universities like New York University, and carved students who were winning faces of several national and international competitions like eYantra and Celestini. \n\nTo know more about the events and activities conducted by BVPIEEE-RAS, refer to our past events page.");
            file_maps.put("The Robotics and Automation Society (BVPIEEE RAS)", R.drawable.ras1p);
            file_maps.put("The Robotics and Automation Society (BVPIEEE RAS) ", R.drawable.ras2p);
            file_maps.put("The Robotics and Automation Society (BVPIEEE RAS)  ", R.drawable.ras3p);

            chaptext.setText("The Robotics and Automation Society (RAS)");

            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/bvpieeeras";
                    FACEBOOK_PAGE_ID = "bvpieeeras";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });
            dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(RasActivity.this,MainChatActivity.class);
                    i.putExtra("forum", "ras");
                    startActivity(i);
                }
            });
        }
        else
        if (Main2Activity.chapterno==2){
            //  chaptertext.setText("BVPIEEE Computer Society (sometimes abbreviated Computer Society or CS) is a professional society of IEEE. Its purpose and scope is “to advance the theory, practice, and application of computer and information processing science and technology” and the “professional standing of its members. As they say, computer and technology go hand in hand with development, this chapter aims to provide opportunities in the fields of research for new technologies like cloud storage, big data and artificial intelligence. The true focus is to harness the curiosity of the budding engineers and steer their way towards a more research and experiment based environment. Various hackathons and coding competitions are also organised by this chapter.\n\nTo know more about the events and activities conducted by BVPIEEE-CS, refer to our past events page.");
            file_maps.put("BVPIEEE Computer Society", R.drawable.cs2p);
            file_maps.put("BVPIEEE Computer Society  ", R.drawable.cs3p);
            file_maps.put("BVPIEEE Computer Society ", R.drawable.csa);

            chaptext.setText("Computer Society (CS)");

            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/BVPIEEECS";
                    FACEBOOK_PAGE_ID = "BVPIEEECS";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });
            dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(RasActivity.this,MainChatActivity.class);
                    i.putExtra("forum", "cs");
                    startActivity(i);
                }
            });

        }
        else
        if (Main2Activity.chapterno==4){
            //  chaptertext.setText("IAS enriches both its individual members and the industry as a whole through the sharing of specific industry-related solutions. The scope of the Industry Applications Society is the advancement of the theory and practice of electrical and electronic engineering in the development, design, manufacture & application of electrical systems, apparatus, devices & controls to the processes and equipment of industry & commerce; the promotion of safe, reliable and economic installations; industry leadership in energy conservation and environmental, health, and safety issues; the creation of voluntary engineering standards and recommended practices, and the professional development of its membership.\n\nTo know more about the events and activities conducted by BVPIEEE-IAS, refer to our past events page.");
            file_maps.put("BVPIEEE-IAS", R.drawable.ias2p);
            file_maps.put("BVPIEEE-IAS ", R.drawable.ias4p);
            file_maps.put("BVPIEEE-IAS  ", R.drawable.ias1p);

            chaptext.setText("Industry Applications Society (IAS)");

            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/BVPIEEE-IAS-483730548444337/";
                    FACEBOOK_PAGE_ID = "BVPIEEE-IAS-483730548444337/";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });
            dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(RasActivity.this,MainChatActivity.class);
                    i.putExtra("forum", "ias");
                    startActivity(i);
                }
            });
        }
        else
        if (Main2Activity.chapterno==5){
            //  chaptertext.setText("BVPIEEE-Eta Kappa Nu (IEEE-HKN) is the student honor society of BVPIEEE and is dedicated to encouraging and recognizing excellence in the IEEE-designated fields of interest. These include: Engineering, Computer Science and Information Technology, Physical Sciences, Biological and Medical Sciences, Mathematics, Technical Communications, Education, Management, Law and Policy. The society, through a variety of service programs and leadership training, ensures that the student members develop lifelong skills that earmark them for prominent positions in industry and academia. It publishes two yearly magazines, Pratibinbh and Cogzniance. Also, it gives awards to the students who score well on academic front.\n" + "\n\n" + " To know more about the events and activities conducted by BVPIEEE-HKN Lambda Eta Chapter, refer to our past events page.");
            file_maps.put("BVPIEEE-Eta", R.drawable.hkn1p);
            file_maps.put("BVPIEEE-Eta ", R.drawable.hkn2p);
            chaptext.setText("BVPIEEE-Eta Kappa Nu (IEEE-HKN)");

            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/IEEE-HKN-Bvcoe-275757819137835/";
                    FACEBOOK_PAGE_ID = "IEEE-HKN-Bvcoe-275757819137835";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                }
            });
            dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(RasActivity.this,MainChatActivity.class);
                    i.putExtra("forum", "hkn");
                    startActivity(i);
                }
            });
        }

        else
        if (Main2Activity.chapterno==3){
            //  chaptertext.setText("The IEEE Student Branch of Bharati Vidyapeeth’s College of Engineering got the approval for starting the women in engineering student branch affinity group on 21st June 2007.\n" +
         //   "\n\n" +
                //    "BVPIEEE Women in Engineering (WIE) is a professional organization dedicated to promoting women engineers and scientists, and inspiring girls around the world to follow their academic interests in a career in engineering."
            file_maps.put("BVPIEEE Women in Engineering (WIE)", R.drawable.wie2p);
            file_maps.put("BVPIEEE Women in Engineering (WIE) ", R.drawable.wie3p);
            file_maps.put("BVPIEEE Women in Engineering (WIE)  ", R.drawable.wie4p);

            chaptext.setText("Women in Engineering (WIE)");


            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/WieMeet2k15/";
                    FACEBOOK_PAGE_ID = "WieMeet2k15";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });
            dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(RasActivity.this,MainChatActivity.class);
                    i.putExtra("forum", "wie");
                    startActivity(i);
                }
            });
        }
        else
        if (Main2Activity.chapterno==6){
            //  chaptertext.setText("The IEEE Student Branch of Bharati Vidyapeeth’s College of Engineering got the approval for starting the women in engineering student branch affinity group on 21st June 2007.\n" +
            //   "\n\n" +
            //    "BVPIEEE Women in Engineering (WIE) is a professional organization dedicated to promoting women engineers and scientists, and inspiring girls around the world to follow their academic interests in a career in engineering."
            file_maps.put("CODE-X", R.drawable.codex1p);
            file_maps.put("CODE-X ", R.drawable.codex2p);
            chaptext.setText("CODE-X");


            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/WieMeet2k15/";
                    FACEBOOK_PAGE_ID = "WieMeet2k15";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });
            dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(RasActivity.this,MainChatActivity.class);
                    i.putExtra("forum", "codex");
                    startActivity(i);
                }
            });
        }

        else
        if (Main2Activity.chapterno==7){
            //  chaptertext.setText("The IEEE Student Branch of Bharati Vidyapeeth’s College of Engineering got the approval for starting the women in engineering student branch affinity group on 21st June 2007.\n" +
            //   "\n\n" +
            //    "BVPIEEE Women in Engineering (WIE) is a professional organization dedicated to promoting women engineers and scientists, and inspiring girls around the world to follow their academic interests in a career in engineering."
            file_maps.put("DRISHTI", R.drawable.dhristi1p);
            file_maps.put("DRISHTI ", R.drawable.dhristi2p);
            chaptext.setText("DRISHTI");


            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/drishti.bvpieee.7/";
                    FACEBOOK_PAGE_ID = "drishti.bvpieee.7";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });
            dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(RasActivity.this,MainChatActivity.class);
                    i.putExtra("forum", "drishti");
                    startActivity(i);
                }
            });
        }

        else
        if (Main2Activity.chapterno==8){
            //  chaptertext.setText("The IEEE Student Branch of Bharati Vidyapeeth’s College of Engineering got the approval for starting the women in engineering student branch affinity group on 21st June 2007.\n" +
            //   "\n\n" +
            //    "BVPIEEE Women in Engineering (WIE) is a professional organization dedicated to promoting women engineers and scientists, and inspiring girls around the world to follow their academic interests in a career in engineering."
            file_maps.put("Robotics and Automation (RAU)", R.drawable.rau1p);
            file_maps.put("Robotics and Automation (RAU) ", R.drawable.rau2p);

            chaptext.setText("Robotics and Automation (RAU)");


            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/WieMeet2k15/";
                    FACEBOOK_PAGE_ID = "WieMeet2k15";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });
            dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(RasActivity.this,MainChatActivity.class);
                    i.putExtra("forum", "rau");
                    startActivity(i);
                }
            });

        }

        else
        if (Main2Activity.chapterno==9){
            //  chaptertext.setText("The IEEE Student Branch of Bharati Vidyapeeth’s College of Engineering got the approval for starting the women in engineering student branch affinity group on 21st June 2007.\n" +
            //   "\n\n" +
            //    "BVPIEEE Women in Engineering (WIE) is a professional organization dedicated to promoting women engineers and scientists, and inspiring girls around the world to follow their academic interests in a career in engineering."
            file_maps.put("Entrepreneurship Cell", R.drawable.ecell1p);
            file_maps.put("Entrepreneurship Cell ", R.drawable.ecell2p);
            chaptext.setText("Entrepreneurship Cell (E-CELL)");


            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/WieMeet2k15/";
                    FACEBOOK_PAGE_ID = "WieMeet2k15";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });
            dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(RasActivity.this,MainChatActivity.class);
                    i.putExtra("forum", "ecell");
                    startActivity(i);
                }
            });
        }

        else
        if (Main2Activity.chapterno==10){
            //  chaptertext.setText("The IEEE Student Branch of Bharati Vidyapeeth’s College of Engineering got the approval for starting the women in engineering student branch affinity group on 21st June 2007.\n" +
            //   "\n\n" +
            //    "BVPIEEE Women in Engineering (WIE) is a professional organization dedicated to promoting women engineers and scientists, and inspiring girls around the world to follow their academic interests in a career in engineering."
            file_maps.put("Gamma", R.drawable.gamma1p);
            file_maps.put("Gamma ", R.drawable.gamma2p);
            chaptext.setText("Gaming and MultiMedia Animation (GAMMA)");


            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/WieMeet2k15/";
                    FACEBOOK_PAGE_ID = "WieMeet2k15";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });
            dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(RasActivity.this,MainChatActivity.class);
                    i.putExtra("forum", "gamma");
                    startActivity(i);
                }
            });

        }

        else
        if (Main2Activity.chapterno==11){
            //  chaptertext.setText("The IEEE Student Branch of Bharati Vidyapeeth’s College of Engineering got the approval for starting the women in engineering student branch affinity group on 21st June 2007.\n" +
            //   "\n\n" +
            //    "BVPIEEE Women in Engineering (WIE) is a professional organization dedicated to promoting women engineers and scientists, and inspiring girls around the world to follow their academic interests in a career in engineering."
            file_maps.put("BVCOE QUIZ CLUB (BQC)", R.drawable.bqc1p);
            file_maps.put("BVCOE QUIZ CLUB (BQC) ", R.drawable.bcq2p);
            chaptext.setText("BVCOE QUIZ CLUB (BQC)");


            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/WieMeet2k15/";
                    FACEBOOK_PAGE_ID = "WieMeet2k15";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });
            dis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i=new Intent(RasActivity.this,MainChatActivity.class);
                    i.putExtra("forum", "bqc");
                    startActivity(i);
                }
            });

        }




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














    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }



    //method to get the right URL to use in the intent
    public String getFacebookPageURL(Context context) {
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






}
