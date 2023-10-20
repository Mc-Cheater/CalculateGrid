package com.example.calculategrid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    public static  String[] arr={"1","2","3","+","4","5","6","-","7","8","9","*","0",".","C","/","="};
    private StringBuilder expression=new StringBuilder();
    List<Button> btns;
    Deque<Integer> ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btns=new ArrayList<>();
        List<String> buttons=Arrays.asList(arr);

        GridLayout layout=findViewById(R.id.Container);
        ids=new ArrayDeque<>();

        for (String s:buttons
             ) {
            Button b =new Button(this);
            btns.add(b);
            b.setText(s);
            ids.push(View.generateViewId());
            b.setId(ids.getLast());
            if(!s.equals("=")) {
                layout.addView(b);
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    expression.append(b.getText().toString());
                    ((TextView)findViewById(R.id.Result)).setText(expression);
                }
            });
            }
            else{
                GridLayout.LayoutParams params=new GridLayout.LayoutParams(GridLayout.spec(4,1),GridLayout.spec(0,4));
                b.setLayoutParams(params);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView tv=findViewById(R.id.Result);
                       String[] operandes=(new String(expression)).split("[*/+-]");
                        String operators=new String();
                       for(int i=0;i<expression.length();i++){
                             if(Character.toString(expression.charAt(i)).equals("*")||
                                     Character.toString(expression.charAt(i)).equals("+")
                                 ||Character.toString(expression.charAt(i)).equals("-")
                                 ||Character.toString(expression.charAt(i)).equals("/")){

                                 operators+=Character.toString(expression.charAt(i));
                             }}
                       String[] operatorMod=operators.split("");

                       LinkedList<String> operandeDeque=new LinkedList<>(Arrays.asList(operandes));
                        Deque<String> operatorsDeque=new ArrayDeque<>(Arrays.asList(operatorMod));

                        ListIterator<String> one=operandeDeque.listIterator();
                        Iterator<String> two=operatorsDeque.iterator();
                        Log.i("begin",operandeDeque.toString());
                        Log.i("begin",operatorsDeque.toString());
                        while (two.hasNext()){
                            String leftvalue="0";
                            String rightvalue="0";
                            String op= two.next();
                           if(one.hasNext()){
                           leftvalue= one.next();}
                           if(one.hasNext()){
                           rightvalue=one.next();
                           one.previous();}
                            if(op.equals("*")){
                                double result=Double.parseDouble(leftvalue)*Double.parseDouble(rightvalue);

                                one.remove();
                                one.previous();
                                        one.set(Double.toString(result));

                                       // one.next();

                                two.remove();
                            }
                            if(op.equals("/")){
                                double result=Double.parseDouble(leftvalue)/Double.parseDouble(rightvalue);

                                one.remove();
                                one.previous();
                                one.set(Double.toString(result));

                                // one.next();

                                two.remove();
                            }

                            Log.i("after each iteration",operandeDeque.toString());
                            Log.i("after each iteration",operatorsDeque.toString());


                        }
                        one=operandeDeque.listIterator();
                        two=operatorsDeque.iterator();

                        while (two.hasNext()){
                            String leftvalue="0";
                            String rightvalue="0";
                            String op= two.next();
                            if(one.hasNext()){
                                leftvalue= one.next();}
                            if(one.hasNext()){
                                rightvalue=one.next();
                                one.previous();}
                            if(op.equals("+")){
                                double result=Double.parseDouble(leftvalue)+Double.parseDouble(rightvalue);

                                one.remove();
                                one.previous();
                                one.set(Double.toString(result));

                                // one.next();

                                two.remove();
                            }
                            if(op.equals("-")){
                                double result=Double.parseDouble(leftvalue)-Double.parseDouble(rightvalue);

                                one.remove();
                                one.previous();
                                one.set(Double.toString(result));

                                // one.next();

                                two.remove();
                            }

                            Log.i("after each iteration",operandeDeque.toString());
                            Log.i("after each iteration",operatorsDeque.toString());


                        }
                        tv.setText(operandeDeque.getLast());


                        /*while (two.hasNext()){
                            String leftvalue="0";
                            String rightvalue="0";
                            String op= two.next();
                            if(one.hasNext()){
                                leftvalue= one.next();}
                            if(one.hasNext()){
                                rightvalue=one.next();
                                one.previous();}
                            if(op.equals("*")){
                                double result=Double.parseDouble(leftvalue)*Double.parseDouble(rightvalue);

                                one.remove();
                                one.previous();
                                one.set(Double.toString(result));

                                // one.next();

                                two.remove();
                            }
                            if(op.equals("/")){
                                one.previous();
                                one.set(Double.toString(Double.parseDouble(leftvalue)/Double.parseDouble(rightvalue)));
                                one.next();
                                one.remove();
                                two.remove();
                            }
                            Log.i("after each iteration",operandeDeque.toString());
                            Log.i("after each iteration",operatorsDeque.toString());


                        }*/
                       /* while (two.hasNext()){
                            String leftvalue="0";
                            String rightvalue="0";
                            String op= two.next();
                            if(one.hasNext()){
                                leftvalue= one.next();}
                            if(one.hasNext()){
                                rightvalue=one.next();}
                            if(op.equals("+")){
                                one.previous();
                                one.set(Double.toString(Double.parseDouble(leftvalue)+Double.parseDouble(rightvalue)));
                                one.next();
                                one.remove();
                                two.remove();
                            }
                            if(op.equals("-")){
                                one.previous();
                                one.set(Double.toString(Double.parseDouble(leftvalue)-Double.parseDouble(rightvalue)));
                                one.next();
                                one.remove();
                                two.remove();
                            }
                            Log.i("after each iteration",operandeDeque.toString());
                            Log.i("after each iteration",operatorsDeque.toString());


                        }

                        Log.i("final state",operandeDeque.toString());
                        Log.i("final state",operatorsDeque.toString());

                         /* for (String s: operandeDeque
                             ) {log+=s;

                        }*
//                        tv.setText(log);
                        tv.setText(operandeDeque.getLast());



              /*          int len=operatorMod.length;
                   for(int i=0;i<len;i++){
                       if(Character.toString(expression.charAt(i)).equals("*") ||Character.toString(expression.charAt(i)).equals("/")){
                   if(Character.toString(expression.charAt(i)).equals("*")){
                       operandes[i]=Double.parseDouble(operandes[i])*Double.parseDouble(operandes[i+1]);
                       }
                   else if(Character.toString(expression.charAt(i)).equals("/")){


                   }

                       }

                   }
*/





                     /*   Map<Integer,Double> index_value=new HashMap<>();
                       int ind=0;
                       for (String s:
                             operators.split("")) {
                           if(s.equals("*"))
                           {
                               index_value.put(ind,Double.parseDouble(operandes[ind])*Double.parseDouble(operandes[ind+1]));
                           }
                           if(s.equals("/"))
                           {
                               index_value.put(ind,Double.parseDouble(operandes[ind])*Double.parseDouble(operandes[ind+1]));
                           }

                           ind++;
                        }
                       */




                        // int l=expression.length();
                        /*for(int i=0;i<l;i++){
                            if(Character.toString(expression.charAt(i)).equals("*")){
                                double result=Double.parseDouble(Character.toString(expression.charAt(i-1)))*Double.parseDouble(Character.toString(expression.charAt(i+1)));
                                expression.replace(i-1,i+2,Double.toString(result));
                                i--;
                                l=l-2;
                            }
                            else
                            if(Character.toString(expression.charAt(i)).equals("/")){
                                double result=Double.parseDouble(Character.toString(expression.charAt(i-1)))*Double.parseDouble(Character.toString(expression.charAt(i+1)));
                                expression.replace(i-1,i+2,Double.toString(result));
                                i--;
                                l=l-2;
                            }

                        }*/
                    }
                });
                layout.addView(b);

            }


        }

       // layout.






    }



}