package com.inti.student.assignment;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;
import static com.inti.student.assignment.R.id.fp;
import static com.inti.student.assignment.R.id.image;


//
public class listviewburger extends ListActivity {
    private Activity c;
    ListView lv;
    DatabaseReference ref;
    String[] foodname={"Spicy tenderscrip","Long Chicken","Tendergrill","French Chicken"};
    Integer[] imgid={R.drawable.st,R.drawable.lc,R.drawable.tg,R.drawable.fc};
    Button tc;
    int tp;
    FirebaseAuth firebaseAuth;

    Context context;
    Dialog dialog;
    Burger_list burger_list;







    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_listviewburger);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.BLACK);
        }

        ref = FirebaseDatabase.getInstance().getReference("user");
        firebaseAuth = FirebaseAuth.getInstance();
        lv=(ListView)findViewById(android.R.id.list);
        lv=getListView();
        burger_list=new Burger_list(this,foodname,imgid);
        lv.setAdapter(burger_list);
        tc=(Button)findViewById(R.id.tc);
        tc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(listviewburger.this);
                tp=burger_list.getTotalprice();
                builder.setMessage("The total price is " +tp + " MYR ")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                                if(tp==0)
                                {
                                   //intent back mainpage

                                    Toast.makeText(listviewburger.this,"You did not complete the purchase",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    //intent to next page
                                    String uid = firebaseAuth.getCurrentUser().getUid();
                                    addprice(uid);
                                    Intent ww = new Intent(listviewburger.this, address_info.class);
                                    startActivity(ww);
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alert=builder.create();
                alert.setTitle("Confirmation");
                alert.show();



            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                int l= position;
                System.out.println("blabla"+l);
                 burger_list.setP(l);
//                System.out.println(""+price.get(position));


            }
        });


    }
    private void addprice(String id)
    {

        price Price=new price(tp);
        ref.child(id+"/Price").setValue(Price);

    }
    public void onBackPressed()
    {
        tp=burger_list.getTotalprice();
        if(tp!=0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(listviewburger.this);
            builder.setMessage("Do you want to cancel the purchase?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            Intent ww = new Intent(listviewburger.this, Mainpage.class);
                            startActivity(ww);

                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.setTitle("Confirmation");
            alert.show();
        }
//

        else{
        super.onBackPressed();
        }

    }




}




