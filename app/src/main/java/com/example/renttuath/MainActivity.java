package com.example.renttuath;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.DecimalFormat;

import static java.lang.Double.parseDouble;
import static java.lang.Double.valueOf;

public class MainActivity extends AppCompatActivity {

    private EditText eTTotalIncomes;
    private TextView tVRent;
    private String total_Incomes;
    private Double total_Rent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTTotalIncomes = findViewById(R.id.etTotalIncomes);

        tVRent = findViewById(R.id.tvRent);

        eTTotalIncomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tVRent.setText("");
            }
        });

        Button buttonRent = findViewById(R.id.btnRent);
        buttonRent.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"SetTextI18n", "NewApi"})
            @Override
            public void onClick(View v) {

                total_Incomes = eTTotalIncomes.getText().toString().trim();

                if (TextUtils.isEmpty(total_Incomes)){
                    alertDialogIncomes();
                    eTTotalIncomes.requestFocus();
                }

                else {
                    double total_Incomes = parseDouble(eTTotalIncomes.getText().toString().trim());
                    double rest_Incomes = total_Incomes - 100;

                    total_Rent = 10 + (rest_Incomes * 0.2);

                    total_Rent = roundTwoDecimalsRent(total_Rent);
                    tVRent.setText("€ "+total_Rent);
                }
            }
        });

        Button buttonClearIncomes = findViewById(R.id.btnClearIncomes);
        buttonClearIncomes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTTotalIncomes.setText("");
                tVRent.setText("");
            }
        });

        Button buttonClearAll = findViewById(R.id.btnClearAll);
        buttonClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTTotalIncomes.setText("");
                tVRent.setText("");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public double roundTwoDecimalsRent(double total_Rent){
        DecimalFormat twoDFormV = new DecimalFormat("#.##");
        return  parseDouble(twoDFormV.format(total_Rent));
    }

    public void alertDialogIncomes(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Enter Value Incomes");
        alertDialogBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
