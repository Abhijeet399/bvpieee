package com.jskgmail.bvpieee;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

import com.google.firebase.messaging.FirebaseMessaging;

public class MainSettingActivity extends AppCompatActivity {

    Switch ras,cs,ias,hkn,wie,codex,drishti,rau,ecell,bqc,gamma;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_setting);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        actionBar.setDisplayShowHomeEnabled(true);


ras=findViewById(R.id.ras);
cs=findViewById(R.id.cs);
ias=findViewById(R.id.ias);
hkn=findViewById(R.id.hkn);
wie=findViewById(R.id.wie);
codex=findViewById(R.id.codex);
drishti=findViewById(R.id.drishti);
rau=findViewById(R.id.rau);
ecell=findViewById(R.id.ecell);
bqc=findViewById(R.id.bqc);
gamma=findViewById(R.id.gamma);

        SharedPreferences prefs1 = getSharedPreferences("subscribe",MODE_PRIVATE);
        String firsttime = prefs1.getString("first", "1");
if (firsttime.equals("1"))
        subscribeall();


        SharedPreferences prefs = getSharedPreferences("subscribe",MODE_PRIVATE);
        String rass = prefs.getString("ras", "1");
        if (rass.equals("1"))
            ras.setChecked(true);
        else ras.setChecked(false);

        String css = prefs.getString("cs", "1");
        if (css.equals("1"))
            cs.setChecked(true);
        else cs.setChecked(false);


        String iass = prefs.getString("ias", "1");
        if (iass.equals("1"))
            ias.setChecked(true);
        else ias.setChecked(false);


        String hknn = prefs.getString("hkn", "1");
        if (hknn.equals("1"))
            hkn.setChecked(true);
        else hkn.setChecked(false);


        String wiee = prefs.getString("wie", "1");
        if (wiee.equals("1"))
            wie.setChecked(true);
        else wie.setChecked(false);


        String codexx = prefs.getString("codex", "1");
        if (codexx.equals("1"))
            codex.setChecked(true);
        else codex.setChecked(false);


        String drishtii = prefs.getString("drishti", "1");
        if (drishtii.equals("1"))
            drishti.setChecked(true);
        else drishti.setChecked(false);


        String rauu = prefs.getString("rau", "1");
        if (rauu.equals("1"))
            rau.setChecked(true);
        else rau.setChecked(false);


        String ecelll = prefs.getString("ecell", "1");
        if (ecelll.equals("1"))
            ecell.setChecked(true);
        else ecell.setChecked(false);


        String gammaa = prefs.getString("gamma", "1");
        if (gammaa.equals("1"))
            gamma.setChecked(true);
        else gamma.setChecked(false);


        String bqcc = prefs.getString("bqc", "1");
        if (bqcc.equals("1"))
            bqc.setChecked(true);
        else bqc.setChecked(false);


        ras.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        ras.setChecked(ras.isChecked());
        if (ras.isChecked()) {
            FirebaseMessaging.getInstance().subscribeToTopic("ras");
            SharedPreferences.Editor editor = getSharedPreferences("subscribe", MODE_PRIVATE).edit();
            editor.putString("ras", "1");
            editor.apply();
        }
        else {
            FirebaseMessaging.getInstance().unsubscribeFromTopic("ras");
            SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
            editor.putString("ras","0");
            editor.apply();

        }
    }
});

        cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cs.setChecked(cs.isChecked());
                if (cs.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("cs");
                    SharedPreferences.Editor editor = getSharedPreferences("subscribe", MODE_PRIVATE).edit();
                    editor.putString("cs", "1");
                    editor.apply();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("cs");
                    SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
                    editor.putString("cs","0");
                    editor.apply();

                }

            }
        });


        ias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ias.setChecked(ias.isChecked());
                if (ias.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("ias");
                    SharedPreferences.Editor editor = getSharedPreferences("subscribe", MODE_PRIVATE).edit();
                    editor.putString("ias", "1");
                    editor.apply();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("ias");
                    SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
                    editor.putString("ias","0");
                    editor.apply();

                }

            }
        });


        hkn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hkn.setChecked(hkn.isChecked());
                if (hkn.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("hkn");
                    SharedPreferences.Editor editor = getSharedPreferences("subscribe", MODE_PRIVATE).edit();
                    editor.putString("hkn", "1");
                    editor.apply();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("hkn");
                    SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
                    editor.putString("hkn","0");
                    editor.apply();

                }

            }
        });


        wie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wie.setChecked(wie.isChecked());
                if (wie.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("wie");
                    SharedPreferences.Editor editor = getSharedPreferences("subscribe", MODE_PRIVATE).edit();
                    editor.putString("wie", "1");
                    editor.apply();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("wie");
                    SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
                    editor.putString("wie","0");
                    editor.apply();

                }
            }
        });



        codex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codex.setChecked(codex.isChecked());
                if (codex.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("codex");
                    SharedPreferences.Editor editor = getSharedPreferences("subscribe", MODE_PRIVATE).edit();
                    editor.putString("codex", "1");
                    editor.apply();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("codex");
                    SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
                    editor.putString("codex","0");
                    editor.apply();

                }

            }
        });



        drishti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drishti.setChecked(drishti.isChecked());
                if (drishti.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("drishti");
                    SharedPreferences.Editor editor = getSharedPreferences("subscribe", MODE_PRIVATE).edit();
                    editor.putString("drishti", "1");
                    editor.apply();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("drishti");
                    SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
                    editor.putString("drishti","0");
                    editor.apply();

                }

            }
        });











        rau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rau.setChecked(rau.isChecked());
                if (rau.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("rau");
                    SharedPreferences.Editor editor = getSharedPreferences("subscribe", MODE_PRIVATE).edit();
                    editor.putString("rau", "1");
                    editor.apply();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("rau");
                    SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
                    editor.putString("rau","0");
                    editor.apply();

                }
            }
        });

        ecell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ecell.setChecked(ecell.isChecked());
                if (ecell.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("ecell");
                    SharedPreferences.Editor editor = getSharedPreferences("subscribe", MODE_PRIVATE).edit();
                    editor.putString("ecell", "1");
                    editor.apply();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("ecell");
                    SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
                    editor.putString("ecell","0");
                    editor.apply();

                }

            }
        });


        bqc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bqc.setChecked(bqc.isChecked());
                if (bqc.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("bqc");
                    SharedPreferences.Editor editor = getSharedPreferences("subscribe", MODE_PRIVATE).edit();
                    editor.putString("bqc", "1");
                    editor.apply();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("bqc");
                    SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
                    editor.putString("bqc","0");
                    editor.apply();

                }
            }
        });


        gamma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gamma.setChecked(gamma.isChecked());
                if (gamma.isChecked()) {
                    FirebaseMessaging.getInstance().subscribeToTopic("gamma");
                    SharedPreferences.Editor editor = getSharedPreferences("subscribe", MODE_PRIVATE).edit();
                    editor.putString("gamma", "1");
                    editor.apply();
                }
                else {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic("gamma");
                    SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
                    editor.putString("gamma","0");
                    editor.apply();

                }
            }
        });



















    }

    private void subscribeall() {
        SharedPreferences.Editor editor= getSharedPreferences("subscribe",MODE_PRIVATE).edit();
        editor.putString("first","99");
        editor.apply();

        FirebaseMessaging.getInstance().subscribeToTopic("ras");
        FirebaseMessaging.getInstance().subscribeToTopic("cs");
        FirebaseMessaging.getInstance().subscribeToTopic("ias");
        FirebaseMessaging.getInstance().subscribeToTopic("hkn");
        FirebaseMessaging.getInstance().subscribeToTopic("wie");
        FirebaseMessaging.getInstance().subscribeToTopic("codex");
        FirebaseMessaging.getInstance().subscribeToTopic("bqc");
        FirebaseMessaging.getInstance().subscribeToTopic("gamma");
        FirebaseMessaging.getInstance().subscribeToTopic("rau");
        FirebaseMessaging.getInstance().subscribeToTopic("drishti");
        FirebaseMessaging.getInstance().subscribeToTopic("ecell");

    }
}
