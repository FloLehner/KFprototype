package com.example.flo.kf;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.interpolator.linear;

/**
 * Created by flo on 26.04.17.
 */

public class FirebaseQueries {

    Long subQuestion;
    public FirebaseDatabase database = FirebaseDatabase.getInstance("https://kfprototype.firebaseio.com/");
    public DatabaseReference userRef = database.getReference("users");
    public DatabaseReference questionRef = database.getReference("topics");


    public void register(String username, String password, String mail, Context c){
        if (username.equals(null) || password.equals(null) || mail.equals(null) || username.equals("") || password.equals("") || mail.equals("") ) {
            Alerts notSuccessful = new Alerts();
            notSuccessful.registrationNotSuccessful(c);
        }
        else {
            User newUser = new User();
            newUser.username = username;
            newUser.password = password;
            newUser.mail = mail;
            userRef.child(newUser.username).setValue(newUser);
            Alerts finishAlert = new Alerts();
            finishAlert.registrationSuccessful(c);
        }
    }





    public void checkLogin(final String username, final String password, final Context c) {
        if (username.equals(null) || password.equals(null) || username.equals("") || password.equals("")) {
            Alerts notAvailable = new Alerts();
            notAvailable.userNotAvailable(c);
        } else {
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    boolean inDB;
                    inDB = dataSnapshot.hasChild(username);
                    if (inDB == true) {
                        userRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.child(username).child("password").getValue().equals(password)) {
                                    Alerts available = new Alerts();
                                    available.userAvailable(c);
                                } else {
                                    Alerts notAvailable = new Alerts();
                                    notAvailable.userNotAvailable(c);
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        /*Alerts available = new Alerts();
                        available.userAvailable(c);*/
                    } else {
                        Alerts notAvailable = new Alerts();
                        notAvailable.userNotAvailable(c);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }


    public void checkQuestions(final Context c, final LinearLayout l){
        questionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long totalchildren = dataSnapshot.getChildrenCount();
                for(int i = 1; i<=totalchildren; i++){
                    String buttonName;
                    buttonName = dataSnapshot.child(String.valueOf(i)).getKey();
                    final Button myButton = new Button(c);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                           LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                    );
                    myButton.setId(i);
                    myButton.setBackgroundColor(Color.rgb(63, 81, 181));
                    myButton.setTextColor(Color.rgb(244, 245, 247));
                    myButton.setText("Thema "+String.valueOf(i));
                    params.setMargins(16,16,16,16);
                    myButton.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            String id = String.valueOf(myButton.getId());
                            Intent showQuestions = new Intent(c, QuestionActivity.class);
                            showQuestions.putExtra("id", id);
                            c.startActivity(showQuestions);
                            //getQuestions(id);
                        }
                    });
                    l.addView(myButton, params);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




    public void getQuestions(final String id, final Context c, final LinearLayout ll){
        questionRef.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                subQuestion = dataSnapshot.getChildrenCount();
                for(int i=1; subQuestion>i; subQuestion--);
                String Antwort1=dataSnapshot.child(subQuestion.toString()).child("1").getValue().toString();
                String Antwort2=dataSnapshot.child(subQuestion.toString()).child("2").getValue().toString();
                String Antwort3=dataSnapshot.child(subQuestion.toString()).child("3").getValue().toString();
                String Antwort4=dataSnapshot.child(subQuestion.toString()).child("4").getValue().toString();
                String Lösung=dataSnapshot.child(subQuestion.toString()).child("5").getValue().toString();
                String Frage = dataSnapshot.child(subQuestion.toString()).child("Frage").getValue().toString();
                System.out.println("Das ist die Subnummer"+ subQuestion);
                System.out.println(Antwort1+" "+Antwort2+" "+Antwort3+" "+Antwort4+" "+Lösung+" "+Frage);

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                );

                params.setMargins(16,16,16,16);

                TextView questionText = new TextView(c);
                questionText.setText(Frage);
                String buttonContext1 = Antwort1;
                String buttonContext2 = Antwort2;
                String buttonContext3 = Antwort3;
                String buttonContext4 = Antwort4;
                String continueText = "Weiter";

                final Button button1=new Button(c);
                final Button button2=new Button(c);
                final Button button3=new Button(c);
                final Button button4=new Button(c);
                final Button continueButton = new Button(c);

                button1.setText(buttonContext1);
                button2.setText(buttonContext2);
                button3.setText(buttonContext3);
                button4.setText(buttonContext4);

                questionText.setTextColor(Color.rgb(0,0,0));
                button1.setBackgroundColor(Color.rgb(63, 81, 181));
                button1.setTextColor(Color.rgb(244, 245, 247));

                button2.setBackgroundColor(Color.rgb(63, 81, 181));
                button2.setTextColor(Color.rgb(244, 245, 247));

                button3.setBackgroundColor(Color.rgb(63, 81, 181));
                button3.setTextColor(Color.rgb(244, 245, 247));

                button4.setBackgroundColor(Color.rgb(63, 81, 181));
                button4.setTextColor(Color.rgb(244, 245, 247));

                continueButton.setBackgroundColor(Color.rgb(128, 128, 128));
                continueButton.setTextColor(Color.rgb(244, 245, 247));
                continueButton.setText(continueText);

                button1.setId(1);
                button2.setId(2);
                button3.setId(3);
                button4.setId(4);

                button1.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        String buttonid = String.valueOf(button1.getId());
                        String buttonContent = dataSnapshot.child(subQuestion.toString()).child(buttonid).getValue().toString();
                        String answerContent = dataSnapshot.child(subQuestion.toString()).child("5").getValue().toString();

                        if(buttonContent.equals(answerContent)){
                            Alerts correctAnswer = new Alerts();
                            correctAnswer.correctAnswer(c);
                        }
                        else{
                            Alerts falseAnswer = new Alerts();
                            falseAnswer.falseAnswer(c);
                        }
                    }
                });

                button2.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        String buttonid = String.valueOf(button2.getId());
                        String buttonContent = dataSnapshot.child(subQuestion.toString()).child(buttonid).getValue().toString();
                        String answerContent = dataSnapshot.child(subQuestion.toString()).child("5").getValue().toString();

                        if(buttonContent.equals(answerContent)){
                            Alerts correctAnswer = new Alerts();
                            correctAnswer.correctAnswer(c);
                        }
                        else{
                            Alerts falseAnswer = new Alerts();
                            falseAnswer.falseAnswer(c);
                        }
                    }
                });

                button3.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        String buttonid = String.valueOf(button3.getId());
                        String buttonContent = dataSnapshot.child(subQuestion.toString()).child(buttonid).getValue().toString();
                        String answerContent = dataSnapshot.child(subQuestion.toString()).child("5").getValue().toString();

                        if(buttonContent.equals(answerContent)){
                            Alerts correctAnswer = new Alerts();
                            correctAnswer.correctAnswer(c);
                        }
                        else{
                            Alerts falseAnswer = new Alerts();
                            falseAnswer.falseAnswer(c);
                        }
                    }
                });

                button4.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        String buttonid = String.valueOf(button4.getId());
                        String buttonContent = dataSnapshot.child(subQuestion.toString()).child(buttonid).getValue().toString();
                        String answerContent = dataSnapshot.child(subQuestion.toString()).child("5").getValue().toString();

                        if(buttonContent.equals(answerContent)){
                            Alerts correctAnswer = new Alerts();
                            correctAnswer.correctAnswer(c);
                        }
                        else{
                            Alerts falseAnswer = new Alerts();
                            falseAnswer.falseAnswer(c);
                        }
                    }
                });

                continueButton.setOnClickListener(new TextView.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        Long actualQuestionNumber = subQuestion;
                        if(actualQuestionNumber<subQuestion){
                            subQuestion++;
                            String id = String.valueOf(subQuestion);
                            Intent showQuestions = new Intent(c, QuestionActivity.class);
                            showQuestions.putExtra("id", id);
                            c.startActivity(showQuestions);
                        }
                        else{
                            Alerts finished = new Alerts();
                            finished.allQuestionsfinished(c);
                        }

                    }
                });

                ll.addView(questionText, params);
                ll.addView(button1, params);
                ll.addView(button2, params);
                ll.addView(button3, params);
                ll.addView(button4, params);
                ll.addView(continueButton, params);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        System.out.println(id);

    }
}
