package com.hssurmelihindi.qrbarcode;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;


public class Qrgenerator extends AppCompatActivity {

    //Initialize variable
    EditText etInput;
    Button btGEnerate;
    ImageView ivOutput;
    Button sharebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrgenerator);




        //Assign variable

        etInput = findViewById(R.id.et_input);
        btGEnerate = findViewById(R.id.bt_generate);
        ivOutput = findViewById(R.id.iv_output);
        sharebtn = findViewById(R.id.share_code);

        sharebtn.setVisibility(View.GONE);


        btGEnerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //get input value from edit text
                String sText = etInput.getText().toString().trim();

                //initialize multi format writer
                MultiFormatWriter writer = new MultiFormatWriter();


                try {
                    //initialize bit matrix
                    BitMatrix matrix = writer.encode(sText, BarcodeFormat.QR_CODE
                                ,350,350);

                    //initialize barcode encoder
                    BarcodeEncoder encoder = new BarcodeEncoder();

                    //initialize bitmap
                    Bitmap bitmap = encoder.createBitmap(matrix);

                    //set bitmap on image view
                    ivOutput.setImageBitmap(bitmap);

                    //initialize input manager
                    InputMethodManager manager = (InputMethodManager) getSystemService(
                            Context.INPUT_METHOD_SERVICE
                    );

                    //Hide soft keyboard
                    manager.hideSoftInputFromWindow(etInput.getApplicationWindowToken(),0);



                } catch (Exception e) {

                    Toast.makeText(getApplicationContext()
                            ,"OOPS... You did not write text", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }





            }
        });

    }



    public void backbtn(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}