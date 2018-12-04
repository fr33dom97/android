package com.inti.student.assignment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class address_info extends AppCompatActivity {
    DatabaseReference ref;
    FirebaseAuth firebaseAuth;
    EditText ed;
    String address;
    Button btn_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_info);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        ref = FirebaseDatabase.getInstance().getReference("user");
        firebaseAuth = FirebaseAuth.getInstance();
        ed = (EditText) findViewById(R.id.editText);
        btn_address = (Button) findViewById(R.id.button2);

        btn_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                address = ed.getText().toString();
                if (address.length() < 15) {
                    Toast.makeText(address_info.this, "Fellow customer, please enter correct address format", Toast.LENGTH_SHORT).show();


                } else {

                    String uid = firebaseAuth.getCurrentUser().getUid();
                    addaddress(uid);
                    Intent ww = new Intent(address_info.this, Mainpage.class);
                    startActivity(ww);
                    Toast.makeText(address_info.this, "Thank you for the purchasing.We will deliver it within 30-40 minutes", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void addaddress(String id)
    {
        address Address = new address(address);
        ref.child(id + "/Address").setValue(Address);

    }

    public void onBackPressed() {
        address = ed.getText().toString();
        if (address.length() < 15) {
            AlertDialog.Builder builder = new AlertDialog.Builder(address_info.this);
            builder.setMessage("Please enter your address before leave")
                    .setCancelable(false)
                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            Intent ww = new Intent(address_info.this, Mainpage.class);
                            startActivity(ww);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("Confirmation");
            alert.show();
        }
//

        else {
            super.onBackPressed();
        }

    }
}
