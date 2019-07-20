package com.example.applicationorder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvErrorName, tvErrorPass;
    EditText etUserName, etPassWord;
    Button btLogIn;
    String name, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Log In");

        etUserName= findViewById(R.id.etUserName);
        etPassWord= findViewById(R.id.etPassWord);
        tvErrorName= findViewById(R.id.tvMessErrorName);
        tvErrorPass= findViewById(R.id.tvMessErrorPass);
        btLogIn= findViewById(R.id.btLogIn);

        btLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getBaseContext(), Home.class);
                name= etUserName.getText().toString();
                pass= etPassWord.getText().toString();
                intent.putExtra("name", name );
                if(name.equals("") && pass.length()<6){
                    tvErrorName.setText(R.string.MessErrorName);
                    tvErrorPass.setText(R.string.MessErrorPass);
                }
                else if (pass.length()<6)
                    tvErrorPass.setText(R.string.MessErrorPass);
                else if(name.equals(""))
                    tvErrorName.setText(R.string.MessErrorName);
                else
                    startActivity(intent);
            }
        });
    }
}
