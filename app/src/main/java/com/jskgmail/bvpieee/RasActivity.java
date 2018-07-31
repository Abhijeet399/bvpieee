package com.jskgmail.bvpieee;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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


     //   TextView chaptertext=findViewById(R.id.chaptertext);
    //    FloatingTextButton events=findViewById(R.id.history);
     //   FloatingTextButton fb=findViewById(R.id.fab1);

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





    void ss()
    {
        /*

        if (Main2Activity.chapterno==1){
            chaptertext.setText("The Robotics and Automation Society (BVPIEEE RAS) is a professional society that supports the development and the exchange of scientific knowledge in the fields of robotics and automation, including applied and theoretical issues. The RAS chapter was founded in 2014 by Shivam Bhardwaj . Over the years, it has organized several competitions like Robo Soccor, Robotic Guardians of the Galaxy, Robotic Mazes, Arcades, Robo Rugby etc. which saw an overwhelming participation. It has fostered students for inspiring them to pursue further studies in robotics and related fields from top universities like New York University, and carved students who were winning faces of several national and international competitions like eYantra and Celestini. \n\nTo know more about the events and activities conducted by BVPIEEE-RAS, refer to our past events page.");
            file_maps.put("Latest ws", R.drawable.codex);
            file_maps.put("Latest ws", R.drawable.codex);
            events.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

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

        }
        else
        if (Main2Activity.chapterno==2){
            chaptertext.setText("BVPIEEE Computer Society (sometimes abbreviated Computer Society or CS) is a professional society of IEEE. Its purpose and scope is “to advance the theory, practice, and application of computer and information processing science and technology” and the “professional standing of its members. As they say, computer and technology go hand in hand with development, this chapter aims to provide opportunities in the fields of research for new technologies like cloud storage, big data and artificial intelligence. The true focus is to harness the curiosity of the budding engineers and steer their way towards a more research and experiment based environment. Various hackathons and coding competitions are also organised by this chapter.\n\nTo know more about the events and activities conducted by BVPIEEE-CS, refer to our past events page.");
            file_maps.put("Latest ws", R.drawable.codex);
            file_maps.put("Latest ws", R.drawable.codex);
            events.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

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

        }
        else
        if (Main2Activity.chapterno==4){
            chaptertext.setText("IAS enriches both its individual members and the industry as a whole through the sharing of specific industry-related solutions. The scope of the Industry Applications Society is the advancement of the theory and practice of electrical and electronic engineering in the development, design, manufacture & application of electrical systems, apparatus, devices & controls to the processes and equipment of industry & commerce; the promotion of safe, reliable and economic installations; industry leadership in energy conservation and environmental, health, and safety issues; the creation of voluntary engineering standards and recommended practices, and the professional development of its membership.\n\nTo know more about the events and activities conducted by BVPIEEE-IAS, refer to our past events page.");
            file_maps.put("Latest ws", R.drawable.codex);
            file_maps.put("Latest ws", R.drawable.codex);
            events.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

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

        }
        else
        if (Main2Activity.chapterno==5){
            chaptertext.setText("BVPIEEE-Eta Kappa Nu (IEEE-HKN) is the student honor society of BVPIEEE and is dedicated to encouraging and recognizing excellence in the IEEE-designated fields of interest. These include: Engineering, Computer Science and Information Technology, Physical Sciences, Biological and Medical Sciences, Mathematics, Technical Communications, Education, Management, Law and Policy. The society, through a variety of service programs and leadership training, ensures that the student members develop lifelong skills that earmark them for prominent positions in industry and academia. It publishes two yearly magazines, Pratibinbh and Cogzniance. Also, it gives awards to the students who score well on academic front.\n" + "\n\n" + " To know more about the events and activities conducted by BVPIEEE-HKN Lambda Eta Chapter, refer to our past events page.");
            file_maps.put("Latest ws", R.drawable.codex);
            file_maps.put("Latest ws", R.drawable.codex);
            events.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    FACEBOOK_URL = "https://www.facebook.com/IEEE-HKN-Bvcoe-275757819137835/";
                    FACEBOOK_PAGE_ID = "IEEE-HKN-Bvcoe-275757819137835";
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(RasActivity.this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                }
            });

        }

        else
        if (Main2Activity.chapterno==3){
            chaptertext.setText("The IEEE Student Branch of Bharati Vidyapeeth’s College of Engineering got the approval for starting the women in engineering student branch affinity group on 21st June 2007.\n" +
                    "\n\n" +
                    "BVPIEEE Women in Engineering (WIE) is a professional organization dedicated to promoting women engineers and scientists, and inspiring girls around the world to follow their academic interests in a career in engineering.");
            file_maps.put("Latest ws", R.drawable.codex);
            file_maps.put("Latest ws", R.drawable.codex);
            events.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

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

        }
    */}
}
