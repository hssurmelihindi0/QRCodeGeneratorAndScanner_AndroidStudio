package com.hssurmelihindi.qrbarcode;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void tarayicibuton(View view){
        Intent intent = new Intent(this, Scanner.class);
        startActivity(intent);

    }

    public void generatorbtn(View view){
        Intent intent = new Intent(this, Qrgenerator.class);
        startActivity(intent);

    }

}