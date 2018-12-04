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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import static com.inti.student.assignment.R.string.email;

public class register extends AppCompatActivity implements View.OnClickListener{
    private EditText email,password,contact,name;
    private TextView signin;
    private Button btn_register;
    private ProgressDialog progressdialog;
    DatabaseReference ref;

    user User;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        name=(EditText)findViewById(R.id.name);
        email= (EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        contact=(EditText)findViewById(R.id.contact);
        btn_register =(Button)findViewById(R.id.btn_register);
        signin=(TextView)findViewById(R.id.signin);
        progressdialog=new ProgressDialog(this);
        btn_register.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("user");
        signin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent (register.this,userlogin.class);
                startActivity(i);
            }
        });


    }

    private void adduser(String id){

        String mail1=email.getText().toString().trim();
        String pas1=password.getText().toString().trim();
        String phone=contact.getText().toString().trim();
        String fname=name.getText().toString().trim();

        if(!TextUtils.isEmpty(mail1)&&!TextUtils.isEmpty(pas1)&&!TextUtils.isEmpty(phone)&&!TextUtils.isEmpty(fname)){
            user User = new user(id,mail1,pas1,phone,fname);
            ref.child(id).setValue(User);


        }

    }
    private void registerUser(){
            String mail=email.getText().toString().trim();
            String pas=password.getText().toString().trim();
            String phone1=contact.getText().toString().trim();
            String fname1=name.getText().toString().trim();
            String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if(fname1.length()<5) {
                name.setError("Please enter your name at least 5 characters");
                return;
            }
            if (android.util.Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                email.setError("Valid Email address");
            }
            else{
                email.setError("Please enter your Email address in format: yourname@example.com");
            }
            if(pas.length()<8){
                password.setError("must have 8 characters");
                return;

        }

        if(phone1.length()<10){
            contact.setError("Provide correct format of contact");
            return;
        }
        else {
            progressdialog.setMessage("Registering User....");
            progressdialog.show();



        firebaseAuth.createUserWithEmailAndPassword(mail,pas)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            String uid = firebaseAuth.getCurrentUser().getUid();
                            adduser(uid);
                            userlogin();
                        }else {
                            Toast.makeText(register.this,"Could not register.Please try again",Toast.LENGTH_LONG ).show();
                        }
                        progressdialog.dismiss();
                    }
                });}

    }
    public void userlogin() {
        String mail = email.getText().toString().trim();
        String pas = password.getText().toString().trim();


        firebaseAuth.signInWithEmailAndPassword(mail, pas)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), Mainpage.class));

                        }


                    }

                });
    }

    @Override
    public void onClick(View view) {
        if(view==btn_register){
            registerUser();


        }
    }

}

