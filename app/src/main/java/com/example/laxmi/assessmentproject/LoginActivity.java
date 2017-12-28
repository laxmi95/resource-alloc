package com.example.laxmi.assessmentproject;


        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.graphics.Color;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    EditText name;
    Button submit;
    Button clear;
    TextView stringTry;
    int even,odd;
    int sum =0,reminder;

    SharedPreferences sharedPreferences;
    public static final String MyPreferences = "My_Preps";
    public static final String MyPin = "hello";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name = (EditText)findViewById(R.id.name);
        stringTry = (TextView)findViewById(R.id.text);
        submit = (Button) findViewById(R.id.button2);
//      //submit.setOnClickListener(this);
//
//
//        //using anonymous inner class

        sharedPreferences = getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);
        submit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                //stringTry.setText("Hello," + name.getText());
                int num = Integer.parseInt(name.getText().toString());
                String num1 = String.valueOf(num);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(MyPin,num1);
                editor.commit();
                Toast.makeText(LoginActivity.this,"Pin stored",Toast.LENGTH_SHORT).show();

                if (num == 5555){
                    //stringTry.setText("Successful login!!");
                    //stringTry.setTextColor(Color.parseColor("#FF53FF40"));
                    Intent intent = new Intent(LoginActivity.this,DashBoard.class);
                    startActivity(intent);
                }
                else{
                    stringTry.setText("Login failed!!");
                    stringTry.setTextColor(Color.parseColor("#FFDF0A0A"));
                }



                // stringTry.setText(Integer.toString(num));

                //Intent intent = new Intent(LoginActivity.this,WelcomePage.class);
                //startActivity(intent);
            }
        });


        clear = (Button) findViewById(R.id.clr);
        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                stringTry.setText(" ");
            }
        });
    }


//    public void methodOne(EditText name){
//        int num = Integer.parseInt(name.getText().toString());
////        int a = 0,b=0;
////
////
////        a += num%10;
////        b = num/10;
////
////
//        stringTry.setText("Hi"+ num);
//
//    }


//implements View.OnClickListener
//    @Override
//    public void onClick(View v){
//        stringTry.setText("Hello," + name.getText());
//    }
}
