package com.jskgmail.bvpieee;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Main3Activity extends AppCompatActivity implements CustomAdapter.photoflipping {
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ViewFlipper flipper;
    ArrayList<Event_info> infos;
    Event_info vsq,bvest2016,foc2016,orien2016,foc2017,orien2017,bvest2017,fervour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);

        recyclerView=findViewById(R.id.recyclerview);
        vsq= new Event_info("VSQ- 2016","VSQ stands for Vikram Sarabhai Quiz. It is a quizzing competition that includes questions of various fields. The 9th edition of VSQ held on 24th January.\n" +
                "\n" +
                "Knowledge is power and this power can make a small man cast a large shadow. So large that he just might set in motion the greatest wars that mankind can witness : The War of Minds !\n" +
                "\n" +
                "Do you think you have it in you to be crowned as the ultimate yoda? Do you feel that you can win a war without shedding a single blood drop?\n" +
                "\n" +
                "For many came to conquer the fort, but a selected few made it till the end. Nine years, and the challenge is still the same.",new int[]{R.drawable.vsq1,R.drawable.vsq2});


        bvest2016= new Event_info("BVEST- 2016","BVEST is the annual technical fest of Bharati Vidhyapeeth’s College of Engineering. Every society organizes various technical events in BVEST. BVPIEEE organised RoboSoccer." +
                " It was a three- round competition. Teams from various colleges came to participate in this competition. " +
                "After all the small arguments, tiresome repairing of bots and nerve wracking competition, and the amazing final between BVCoE and MAIT eventually a home team took away the title of the RoboSoccer legend.",new int[]{R.drawable.bvest1});

        foc2016= new Event_info("FOC- 2016","Fresh-On Campus is organised every year to welcome freshers. It is generally organized in the month of August. The Fresh on Campus 4.0 was organized on 10th August. The enthralling ice breaker event was enjoyed by the participants to the fullest." +
                "\n" +
                "Team of: Pahul, Harsh, Dhruv, Shivam and Sakaar bagged the first position in the treasure hunt.\n" +
                "\n" +
                "The enthusiasm of freshers filled the college environment with unparalleled zest and energy. To the younger lot who brought a smile on every team member’s face.We hope to see the same enthusiasm and spontaneity throughout the year.",new int[]{R.drawable.foc1,R.drawable.foc2});

        orien2016= new Event_info("Orientation- 2016","BVPIEEE officially welcomed the batch of 2016-20 at their FRESHER’S ORIENTATION held on 8th August 2016.\n" +
                "\n" +
                "The candid interaction was centred around how to make the best of college life and how BVPIEEE provides the ideal platform for the same. Following it, the Fresh on Campus 4.0 was organized on 10th August. The enthralling ice breaker event was enjoyed by the participants to the fullest.",new int[]{R.drawable.orientation1,R.drawable.orientation2});

        foc2017= new Event_info("FOC- 2017","A toast to those never ending days; The carefree laughs;  With the beyblades and pokemons; Morphing into Power Rangers and howling “Scooby Dooby Doo!” endlessly;\n" +
                "Cherishing the evergreen Tom n Jerry fights!\n" +
                "\n" +
                "To that ‘us’ within us, the one who’s still a child. We’re all set to bring the good ol’ time alive!\n" +
                "\n" +
                "Presenting-\n" +
                "\n" +
                "BVCOE Fresh on Campus 5.0! Explore the campus like never before! Solve intricate yet fun riddles themed on our all-time favourite cartoons! Participate in groups and experience the best ice breaker in the college!",new int[]{R.drawable.foc171,R.drawable.foc172});
        orien2017= new Event_info("Orientation- 2017","Midst the busy college schedules we’re here building ourselves for something new. Be it programming, robotics, gaming or photography or literature, we at BVPIEEE are always striving to bring out our passion for perfection.\n" +
                "\n" +
                "BVPIEEE organised its orientation to let you know more about IEEE. There was also a QnA session for all to interact with the Alumni and know more about the college.\n" +
                "\n" +
                "It was organised on 25th August, 2017 in the Auditorium at 2:40 pm.",new int[]{R.drawable.orientation171,R.drawable.orientation172});
        bvest2017= new Event_info("BVEST- 2017","The Robotics and Automation Society of BVPIEEE brought, ROBO RUGBY -the ultimate test of tactical maneuvering skills @BVEST’17.\n" +
                "\n" +
                "IS THAT A ROBOT PLAYING RUGBY?! An arena where robots will play rugby – built and steered by you. Task – Dodge obstacles and opponents, balance the ball and reach the finish line. It was one hell of an event which turned out to be the biggest event of BVEST’17 among all others.\n" +
                "\n" +
                "Another event that caught the eyes of the crowd was Extempore: OFF-THE-CUFF organised by BVP IEEE-HKN.\n" +
                "\n" +
                "” It is true that speaking is better than silence. However, all speak and no listen makes Jack an idiot box. ” This event was about letting your heart out by the means of words and was judged by the best faculty from the college itself, so you can guess the BARS were set HIGH.",new int[]{R.drawable.bvest20171,R.drawable.bvest20172});
        fervour= new Event_info("FERVOUR X: AI AM BACK- 2018","The tenth edition of Fervour,Fervour X : AI AM BACK was a grand success and came out to\n" +
                "\n" +
                "be the best among the rest in the academic year 2017-18. There were two flagship events, Hacktivate and Technovation.\n" +
                "\n" +
                "Other events which were the center of attraction for the visitors were Robostacle, The Line Following Challenge, BPlan, Deathstar and Joey doesn’t share food. Well there should be less explaining and more showing…",new int[]{R.drawable.fervour1,R.drawable.fervour2});
        infos= new ArrayList<>();
        infos.add(fervour);
        infos.add(bvest2017);
        infos.add(orien2017);
        infos.add(foc2017);
        infos.add(orien2016);
        infos.add(foc2016);
        infos.add(bvest2016);
        infos.add(vsq);

        adapter= new CustomAdapter(this,infos,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());





    }



//    @Override
//    public void photoflipper(ViewFlipper viewFlipper) {
//        flipper= viewFlipper;
//
//    }


    public  void flipimages(int image){
        ImageView imageView= new ImageView(this);
        Glide.with(this).load(image).into(imageView);
        // imageView.setBackgroundResource(image);
        flipper.addView(imageView);
        flipper.setFlipInterval(2000);

        flipper.setAutoStart(true);
        flipper.setInAnimation(this,android.R.anim.slide_in_left);
        flipper.setOutAnimation(this,android.R.anim.slide_out_right);
    }

    @Override
    public void flipimages(int[] image, ViewFlipper viewFlipper) {
        for(int i=0;i<image.length;i++) {
            ImageView imageView = new ImageView(this);
            Glide.with(this).load(image[i]).into(imageView);
            viewFlipper.addView(imageView);
            viewFlipper.setFlipInterval(2000);

            viewFlipper.setAutoStart(true);
            viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
            viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
