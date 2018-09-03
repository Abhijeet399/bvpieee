package com.jskgmail.bvpieee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.storage.StorageReference;
import com.victor.loading.rotate.RotateLoading;

public class SplashActivity extends AppCompatActivity {
    private StorageReference mStorageRef;
    private RotateLoading rotateLoading;

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private FirebaseAuth mAuth;
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 1;

    private boolean isUserClickedBackButton = false;

    FirebaseAuth.AuthStateListener mAuthListner;


















    @Override
    protected void onStart() {
        super.onStart();

     //   new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

       //     @Override
       //     public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

        rotateLoading.start();
                mAuth.addAuthStateListener(mAuthListner);
            //    finish();
                // close this activity
            }
     //   }, 1700);





   // }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        rotateLoading = findViewById(R.id.rotateloading);
        rotateLoading.start();

        if (!isNetworkAvailable()) {
            new Handler().postDelayed(new Runnable() {

                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity

                    rotateLoading.stop();

                    Toast.makeText(getApplicationContext(),"You are viewing offline mode",Toast.LENGTH_LONG).show();
                     startActivity(new Intent(SplashActivity.this, Main2Activity.class));


                     finish();

                    // close this activity
                }
            }, 2000);        } else {
            new Handler().postDelayed(new Runnable() {

                /*
                 * Showing splash screen with a timer. This will be useful when you
                 * want to show case your app logo / company
                 */

                @Override
                public void run() {
                    // This method will be executed once the timer is over
                    // Start your app main activity

                    signIn();
                    rotateLoading.stop();

                    //  startActivity(new Intent(SplashActivity.this, Main2Activity.class));


                    // finish();

                    // close this activity
                }
            }, 2000);


        }


































        mAuth = FirebaseAuth.getInstance();

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                   // rotateLoading.stop();


                    FirebaseUser user = mAuth.getCurrentUser();
                    SharedPreferences.Editor editor= getSharedPreferences("ieee",MODE_PRIVATE).edit();
                    assert user != null;

                    String mail=user.getEmail();
                    String nam=user.getDisplayName();

                    editor.putString("mail",mail);
                    editor.putString("name",nam);

                    editor.apply();

                    Log.e("NAMELOGIN", user.getDisplayName());



                }
            }
        };

        //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Toast.makeText(SplashActivity.this, "Error!", Toast.LENGTH_LONG).show();
                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~Sign In method

    private void signIn() {

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onActivityResult

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                rotateLoading.stop();


                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                assert account != null;
                Log.e("aaaaaaacccccccc", String.valueOf(account));
                firebaseAuthWithGoogle(account);

                startActivity(new Intent(SplashActivity.this, Main2Activity.class));

                finish();

            } else {
                Toast.makeText(SplashActivity.this, "Sign In Error", Toast.LENGTH_LONG).show();
            }
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~firebaseAuthWithGoogle


    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {


        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            rotateLoading.stop();
                            FirebaseUser user = mAuth.getCurrentUser();
                            SharedPreferences.Editor editor= getSharedPreferences("ieee",MODE_PRIVATE).edit();
                            assert user != null;

                            String mail=user.getEmail();
                            String nam=user.getDisplayName();

                            editor.putString("mail",mail);
                            editor.putString("name",nam);

                            editor.apply();

                            Log.e("NAMELOGIN", user.getDisplayName());

                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");

                         //   FirebaseUser user = mAuth.getCurrentUser();
                           //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(SplashActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                    }
                });
    }
}





