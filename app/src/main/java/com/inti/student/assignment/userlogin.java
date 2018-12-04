package com.inti.student.assignment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class userlogin extends AppCompatActivity {
    private Button btn_login;
    private EditText email, password;
    private TextView signup;
    private ProgressDialog progressdialog;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btn_login = (Button) findViewById(R.id.btn_login);
        signup = (TextView) findViewById(R.id.signup);
        firebaseAuth = FirebaseAuth.getInstance();

        progressdialog = new ProgressDialog(this);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(userlogin.this, register.class);
                startActivity(i);
            }
        });


    }

    public void onStart() {
    super.onStart();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    if(user!=null)
    {
        String userId = user.getUid();
        String userEmail = user.getEmail();

        Intent i = new Intent (userlogin.this,Mainpage.class);
        startActivity(i);

    }

}
    private void userLogin(){
        String mail =email.getText().toString().trim();
        String pas = password.getText().toString().trim();


        if (android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
            email.setError("valid Email address");
        }
        else{
            email.setError("invalid Email address");
        }
        if(pas.length()<8){
            password.setError("must have 8 characters");
            return;

        }

        progressdialog.setMessage("Login In. Please be patient");
        progressdialog.show();

        firebaseAuth.signInWithEmailAndPassword(mail,pas)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       progressdialog.dismiss();
                        if (task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),Mainpage.class));
                            finish();

                        }
                        else
                        {
                            FirebaseAuthException e = (FirebaseAuthException)task.getException();
                            Toast.makeText(userlogin.this, "Failed Login: "+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }

//    @Override
//    public void onClick(View view) {
//        if(view == btn_login){
//
//        }
//
//    }

}
