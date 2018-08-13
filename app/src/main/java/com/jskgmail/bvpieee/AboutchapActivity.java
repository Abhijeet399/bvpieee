package com.jskgmail.bvpieee;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutchapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutchap);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);
        ImageView img=findViewById(R.id.image);
        TextView text=findViewById(R.id.text);
        TextView title=findViewById(R.id.title);

        if(Main2Activity.chapterno==1)
        {
            img.setImageResource(R.drawable.rasa);
            title.setText("Robotics and Automation Society");
            text.setText("The Robotics and Automation Society (BVPIEEE RAS) is a professional society that supports the development and the exchange of scientific knowledge in the fields of robotics and automation, including applied and theoretical issues. The RAS chapter was founded in 2014 by Shivam Bhardwaj . Over the years, it has organized several competitions like Robo Soccor, Robotic Guardians of the Galaxy, Robotic Mazes, Arcades, Robo Rugby etc. which saw an overwhelming participation. It has fostered students for inspiring them to pursue further studies in robotics and related fields from top universities like New York University, and carved students who were winning faces of several national and international competitions like eYantra and Celestini.\"\n");
        }
        else  if(Main2Activity.chapterno==2)
        {            title.setText("Computer Society");
            img.setImageResource(R.drawable.csa);
            text.setText("BVPIEEE Computer Society (sometimes abbreviated Computer Society or CS) is a professional society of IEEE. Its purpose and scope is “to advance the theory, practice, and application of computer and information processing science and technology” and the “professional standing of its members. As they say, computer and technology go hand in hand with development, this chapter aims to provide opportunities in the fields of research for new technologies like cloud storage, big data and artificial intelligence. The true focus is to harness the curiosity of the budding engineers and steer their way towards a more research and experiment based environment. Various hackathons and coding competitions are also organised by this chapter.");
        }
        else  if(Main2Activity.chapterno==3)
        {            title.setText("Women In Engineering");
            img.setImageResource(R.drawable.wiea);
            text.setText("I Change The World. I Am An Engineer.\n" +
                    "IEEE WIE is one of the world’s leaders in changing the face of engineering. Our global network connects over 20,000 members in over 100 countries to advance women in technology at all points in their life and career. IEEE WIE members make lifelong friendships, acquire influential mentors, and make a difference for the benefit of humanity.");
        }
        else  if(Main2Activity.chapterno==4)
        { title.setText("Industry Applications Society");
            img.setImageResource(R.drawable.iasa);
            text.setText("IAS enriches both its individual members and the industry as a whole through the sharing of specific industry-related solutions. The scope of the Industry Applications Society is the advancement of the theory and practice of electrical and electronic engineering in the development, design, manufacture and application of electrical systems, apparatus, devices and controls to the processes and equipment of industry and commerce; the promotion of safe, reliable and economic installations; industry leadership in energy conservation and environmental, health, and safety issues; the creation of voluntary engineering standards and recommended practices, and the professional development of its membership.");
        }

        else  if(Main2Activity.chapterno==5)
        { title.setText("IEEE-HKN");
            img.setImageResource(R.drawable.hkna);
            text.setText("BVPIEEE-Eta Kappa Nu (IEEE-HKN) is the student honor society of BVPIEEE and is dedicated to encouraging and recognizing excellence in the IEEE-designated fields of interest. These include: Engineering, Computer Science and Information Technology, Physical Sciences, Biological and Medical Sciences, Mathematics, Technical Communications, Education, Management, Law and Policy. The society, through a variety of service programs and leadership training, ensures that the student members develop lifelong skills that earmark them for prominent positions in industry and academia. It publishes two yearly magazines, Pratibinbh and Cogzniance. Also, it gives awards to the students who score well on academic front.");
        }
        else  if(Main2Activity.chapterno==6)
        { title.setText("Code-X");
            img.setImageResource(R.drawable.codexa);
            text.setText("Code-X is a Special Interest Group for programming, under BVPIEEE. It aims at improving programming standards through lessening the gap between the learned ones and the beginners. It also hold workshops, weekly quiz/challenges, tutorial sessions, and offline programming competitions on a regular basis keeping the level as comfortable as possible for both, tyros and pro-coders.");
        }
        else  if(Main2Activity.chapterno==7)
        {title.setText("Drishti");
            img.setImageResource(R.drawable.dhristia);
            text.setText("Drishti is the digital arts association of BVPIEEE that works to promote and encourage Digital Arts amongst the students. Drishti conducts various events from photography-oriented sessions to photoshop-based software workshops and tutorials, giving interested students a view of the latest technology and updates in the domain of digital arts.");
        }
        else  if(Main2Activity.chapterno==8)
        { title.setText("Robotics and Automation");
            img.setImageResource(R.drawable.raua);
            text.setText("RAU, a Student Interest Group under BVPIEEE is an entity which aims to provide students with the field knowledge about Robotics and Automation. The SIG focuses more on project-based learning in addition to preparing the students to face the hurdles which usually come while implementing the algorithms. Being one of the most active parts of BVPIEEE, the SIG continously work in projects along with students across all domains. The projects include ROS,  quadcopter dynamics, balance bot and projects on Raspberry Pi and Arduino, which are the most popular prototyping boards among the budding engineers.");
        }
        else  if(Main2Activity.chapterno==9)
        { title.setText("Entrepreneurship Cell");
            img.setImageResource(R.drawable.ecella);
            text.setText("E-Cell (Entrepreneurship Cell) aims at providing a platform for students to interact with professionals and industrialists of different fields. E-Cell organizes Group Discussions, Conferences and many more Interactive sessions and helps in developing a Professional environment for students. It’s aim is to help the budding engineers of the institution to realize their dreams of establishing their own start-ups, aiding them in conceptualizing and executing their plans by conducting one to one interaction with entrepreneurs and field experts.");
        }
        else  if(Main2Activity.chapterno==10)
        { img.setImageResource(R.drawable.gammaa);
            text.setText("BVPIEEE added a vertex to its graph in the form of GAMMA which stands for Gaming and MultiMedia Animation by organizing its orientation meet on 5th March, 2014. This SIG of BVPIEEE is a new dimension which encapsulates not only gaming but all the hardwork that it takes to build a game. It not only wants the students to have fun while playing games but also learn the game.");
            title.setText("Gaming and MultiMedia Animation");
        }
        else  if(Main2Activity.chapterno==11)
        { title.setText("BVCOE Quiz Club");
            img.setImageResource(R.drawable.bqca);
            text.setText("The BVCOE Quiz Club(BQC) aims to keep alive the long- standing tradition of quizzing at Bharati Vidyapeeth’s College of Engineering. A BVPIEEE initiative and one of its six SIGs (Special Interest Groups), it holds meetings throughout the academic session where quiz enthusiasts meet and compete with each other. Seeing hat one of the premier events of BVPIEEE is a quiz, namely Dr Vikram Sarabhai Quiz, BQC has always been an integral part of not just BVPIEEE but also of the college as it has established a culture of quizzing and knowledge sharing.\n");
        }


    }
}
