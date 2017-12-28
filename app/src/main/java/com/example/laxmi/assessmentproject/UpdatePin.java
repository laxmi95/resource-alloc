package com.example.laxmi.assessmentproject;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdatePin extends AppCompatActivity {

    EditText pin;
    Button check;
    Context context;
    SharedPreferences sharedPreferences;
    public static final String MyPreferences = "My_Preps";
    public static final String MyPin = "hello";


    //int pin1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pin);

        pin = (EditText)findViewById(R.id.updatePin);
        check = (Button)findViewById(R.id.checker);

        check.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int pin1 = Integer.parseInt(pin.getText().toString());
                sharedPreferences = getSharedPreferences(MyPreferences,Context.MODE_PRIVATE);
                String pin2 = String.valueOf(pin1);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // editor.putString(MyPin,pin2);
                //editor.commit();
                        if(pin1 == 5555){
                          // Toast.makeText(UpdatePin.this,"Already exists",Toast.LENGTH_SHORT).show();
                            final DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which){
                                        case DialogInterface.BUTTON_POSITIVE:
                                            Intent i = new Intent(UpdatePin.this,LoginActivity.class);
                                            // i.putExtra("key",fID);
                                            context.startActivity(i);
                                            break;
                                        case DialogInterface.BUTTON_NEGATIVE:
                                            dialog.cancel();
                                            break;
                                    }
                                }
                            };
                            AlertDialog.Builder dialog = new AlertDialog.Builder(UpdatePin.this);
                            dialog.setMessage("PIN already exists!!Do you want to continue?").setPositiveButton("Yes",dialogListener).setNegativeButton("No",dialogListener).show();

                        }else{

                            editor.putString(MyPin,pin2);
                            editor.commit();
                            Toast.makeText(UpdatePin.this,"Pin stored in Shared Preferences",Toast.LENGTH_SHORT).show();
                            Intent inA = new Intent(UpdatePin.this,LoginActivity.class);
                            startActivity(inA);
                        }

                }
        });
    }
}
