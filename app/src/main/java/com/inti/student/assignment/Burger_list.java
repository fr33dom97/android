package com.inti.student.assignment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Mister on 29/11/2018.
 */

public class Burger_list extends ArrayAdapter<String> {

    private String[] foodname;
    private Integer[] imgid;
    private Activity context;
    TextView txclose;
    EditText ed_qt;
    TextView ed_p;
    TextView tx_tp;
    Button tx_cancel;
    Button tx_confirm;
    int w;
    ArrayList<Integer> item=new ArrayList<>();
    ArrayList<Integer> listitem=new ArrayList<>();
    Button totalcart;
    String qt;
    RelativeLayout r;
    String total,bp;
    int p,t1,qt1,totalprice,tp2,tp;







    public Burger_list(Activity context, String[] foodname,Integer[] imgid){
        super(context, R.layout.activity_burger_list,foodname);
        this.foodname=foodname;
        this.imgid=imgid;
        this.context=context;


    }

    public void setTotalprice(int totalprice)
    {
        this.tp2=totalprice;
    }

    public int getTotalprice()
    {
        return tp2;
    }

    public int getP(){
        return w;
    }

    public void setP(int ss){
        this.w=ss;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View r=convertView;
        ViewHolder viewHolder=null;




        if(r==null)
        {
            LayoutInflater inflater=context.getLayoutInflater();
            r=inflater.inflate(R.layout.activity_burger_list,null,true);
            ImageView pop=(ImageView)r.findViewById(R.id.sc);
            viewHolder=new ViewHolder(r);
            r.setTag(viewHolder);
            listitem.add(position);

            pop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    fff(item.get(position));
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.burgerdialog);
                    txclose = (TextView) dialog.findViewById(R.id.close);
                    ed_p = (TextView) dialog.findViewById(R.id.price);
                    ed_qt = (EditText) dialog.findViewById(R.id.edit_qt);
                    tx_tp = (TextView) dialog.findViewById(R.id.tv_tp);
                    tx_cancel=(Button) dialog.findViewById(R.id.cancel);
                    tx_confirm=(Button) dialog.findViewById(R.id.confirm);
                    ed_p.setText(item.get(position).toString());







                    ed_qt.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {

                            qt=ed_qt.getText().toString();
                            bp=ed_p.getText().toString();
                            if(qt.length() < 1)
                            {

                            }
                            else
                            {
                                qt1 = Integer.parseInt(qt);
                                p = Integer.parseInt(bp);
                                t1 = qt1 * p;
                                qt = String.valueOf(t1);
                                tx_tp.setText(qt);
                            }


                        }
                    });


                    txclose.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//
                                    dialog.dismiss();
//
//
//
//
                            }
                        });
                    tx_cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//
                            dialog.dismiss();
//
//
//
//
                        }
                    });

                    tx_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            totalprice+=t1;
                            setTotalprice(totalprice);
                            dialog.dismiss();
//
                        }
                    });




                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });
        }
        else
        {
            viewHolder=(ViewHolder)r.getTag();

        }
        viewHolder.im1.setImageResource(imgid[position]);
        viewHolder.tv1.setText(foodname[position]);



        return r;
    }

//    public void fff(int fa)
//    {
//        System.out.println(""+fa);
//
//    }
    class ViewHolder
    {
        TextView tv1;
        TextView tv2;
        ImageView im1;
        EditText et1;


       ViewHolder(View v) {


            tv1 = (TextView) v.findViewById(R.id.foodname);
            im1 = (ImageView) v.findViewById(R.id.fp);


            r=(RelativeLayout)v.findViewById(R.id.rl);
            item.add(14);
            item.add(12);
            item.add(15);
            item.add(13);

//
//
//
//            r.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View view) {
//
//                    System.out.println("aaaa"+item.get(0));
//                    System.out.println("ss"+listitem);
////                    System.out.println("RO"+getP());
//                    if (getP() == 0 || getP() == 1 || getP() == 2 || getP() == 3) {
//                        final Dialog dialog = new Dialog(context);
//                        dialog.setContentView(R.layout.burgerdialog);
//                        TextView txclose;
//                        final EditText ed_qt;
//                        final TextView ed_p;
//                        TextView tx_tp;
//                        Button tx_cancel;
//                        Button tx_confirm;
//
//                        txclose = (TextView) dialog.findViewById(R.id.close);
//                        ed_p = (TextView) dialog.findViewById(R.id.price);
//                        ed_qt = (EditText) dialog.findViewById(R.id.edit_qt);
//                        tx_tp = (TextView) dialog.findViewById(R.id.tv_tp);
//                        tx_cancel=(Button) dialog.findViewById(R.id.cancel);
//                        tx_confirm=(Button) dialog.findViewById(R.id.confirm);
//
//                        if (getP() == 0) {
//                            ed_p.setText("" + item.get(0).toString());
//                        } else if (getP() == 1) {
//                            ed_p.setText("" + item.get(1).toString());
//                        } else if (getP() == 2) {
//                            ed_p.setText("" + item.get(2).toString());
//                        } else if (getP() == 3) {
//                            ed_p.setText("" + item.get(3).toString());
//                        }
//                        String bla = tv2.getText().toString();
//                        ed_p.setText(bla);
//                        qt=ed_qt.getText().toString();
//                        System.out.println(bla);
//                        txclose.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
////                                if(qt.equals(""))
////                                {
////                                    System.out.print("jh");
//                                    dialog.dismiss();
////
////
////                                }
////                                else
////                                {
////                                    qt1=Integer.parseInt(qt);
////                                    p=Integer.parseInt(ed_p.getText().toString());
////                                    t1= p*qt1;
////                                    System.out.print(t1);
////
////                                }
////
//                            }
//                        });
//                        tx_cancel.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                dialog.dismiss();
//                            }
//                        });
//                        tx_confirm.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                if(qt.equals(""))
//                                {
//                                    System.out.print("tt");
//                                }
//                                else
//                                {
//                                    qt1=Integer.parseInt(qt);
//                                    p=Integer.parseInt(ed_p.getText().toString());
//                                    t1= p*qt1;
//                                    System.out.print(t1);
//                                }
//
//                            }
//                        });
//
//
//
//
//                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                        dialog.show();
//                    }
//
//                }
//
//            });
        }
    }
}
