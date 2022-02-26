package com.hssurmelihindi.qrbarcode;




import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;




public class Scanner extends AppCompatActivity {

    //Initialize variable
    Button btScan;
    String scanned;
    TextView taranan;




    String gecmis1, gecmis2, gecmis3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);






        //Assign variable

        btScan = findViewById(R.id.bt_scan);
        taranan = findViewById(R.id.taranan);


        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //Initialize intent integrator
                IntentIntegrator intentIntegrator = new IntentIntegrator(
                        Scanner.this
                );

                //set prompt text
                intentIntegrator.setPrompt("For flash use volume up key");
                //set beep
                intentIntegrator.setOrientationLocked(true);
                //set capture activity
                intentIntegrator.setCaptureActivity(Capture.class);
                //initiate scan
                intentIntegrator.initiateScan();





            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Initialize intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(
                requestCode, resultCode, data
        );

        //check condition
        if (intentResult.getContents() != null) {
            //When result content is not null
            //Initialize alert dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(
                    Scanner.this
            );

            //set title
            builder.setTitle("Result");
            // set message
            builder.setMessage(intentResult.getContents());
            scanned = intentResult.getContents();
            taranan.setText(scanned);

            //set positive button
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //Dismiss dialog
                    dialogInterface.dismiss();


                }
            });

            //show alert dialog
            builder.show();

        } else {
            //when result content is null
            //display toast
            Toast.makeText(getApplicationContext()
                    , "OOPS... You did not scan anything", Toast.LENGTH_SHORT).show();

        }


    }

    public void sharedbtn(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, scanned);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);



    }

    public void backbtn(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

