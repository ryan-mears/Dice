package com.example.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button b_rollD100, b_rollD20, b_rollD12, b_rollD10, b_rollD8, b_rollD6, b_rollD4, b_rollDX, b_resetTotal;
    TextView tv_rollNumber;
    Switch switch1;
    EditText et_rollMod, et_customDiceSize;

    int totalRoll = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b_rollD100      = (Button) findViewById(R.id.b_rollD100);
        b_rollD20       = (Button) findViewById(R.id.b_rollD20);
        b_rollD12       = (Button) findViewById(R.id.b_rollD12);
        b_rollD10       = (Button) findViewById(R.id.b_rollD10);
        b_rollD8        = (Button) findViewById(R.id.b_rollD8);
        b_rollD6        = (Button) findViewById(R.id.b_rollD6);
        b_rollD4        = (Button) findViewById(R.id.b_rollD4);
        b_rollDX        = (Button) findViewById(R.id.b_rollDX);
        b_resetTotal    = (Button) findViewById(R.id.b_resetTotal);

        b_rollD100.setOnClickListener(this);
        b_rollD20.setOnClickListener(this);
        b_rollD12.setOnClickListener(this);
        b_rollD10.setOnClickListener(this);
        b_rollD8.setOnClickListener(this);
        b_rollD6.setOnClickListener(this);
        b_rollD4.setOnClickListener(this);
        b_rollDX.setOnClickListener(this);

        tv_rollNumber   = (TextView) findViewById(R.id.tv_rollNumber);

        switch1         = (Switch) findViewById(R.id.switch1);

        et_rollMod             = (EditText) findViewById(R.id.et_rollMod);
        et_customDiceSize   = (EditText) findViewById(R.id.et_customDiceSize);


        b_resetTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalRoll = 0;
                tv_rollNumber.setText("Your running total is " + totalRoll);
            }
        });
    }

    @Override
    public void onClick(View view) {
        Random r = new Random();
        int theRoll, highRoll, selectDie, inMod, dieSize, total;
        boolean switch_multiDice = switch1.isChecked();
        String rollModifier = et_rollMod.getText().toString();
        String customDx = et_customDiceSize.getText().toString();
        inMod = 0;
        selectDie = 0;
        dieSize = 1;

        if (!customDx.isEmpty()) dieSize = Integer.parseInt(customDx);
        if (!rollModifier.isEmpty()) inMod = Integer.parseInt(rollModifier);
        if (dieSize <= 0) dieSize = 1;

        switch (view.getId()){
            case R.id.b_rollD100:
                selectDie = 100;
                break;
            case R.id.b_rollD20:
                selectDie = 20;
                break;
            case R.id.b_rollD12:
                selectDie = 12;
                break;
            case R.id.b_rollD10:
                selectDie = 10;
                break;
            case R.id.b_rollD8:
                selectDie = 8;
                break;
            case R.id.b_rollD6:
                selectDie = 6;
                break;
            case R.id.b_rollD4:
                selectDie = 4;
                break;
            case R.id.b_rollDX:
                selectDie = dieSize;
                break;
        }

        highRoll = selectDie + inMod;
        theRoll = r.nextInt(selectDie) + 1 ;
        total = theRoll + inMod;

        if (switch_multiDice){
            totalRoll += total;
            if(totalRoll >= 999999999) totalRoll = 999999999;
            if(totalRoll <= -999999999) totalRoll = -999999999;
            tv_rollNumber.setText("You rolled a " + total + " out of a possible " + highRoll
                    + "\n( " + theRoll + " + " + inMod + " )"
                    + "\nYour running total is " + totalRoll);
        }
        else if(!switch_multiDice) tv_rollNumber.setText("You rolled a " + total + " out of a possible " + highRoll
                + "\n( " + theRoll + " + " + inMod + " )"
                + "\nYour running total is " + totalRoll);
    }
}
