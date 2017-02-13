package com.example.android.justjava;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.duration;
import static android.R.attr.name;
import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int price_santigi = 5;
    int price_afrogalo = 3;
    boolean has_santigi = false;
    boolean has_afrogalo = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increaseQuant(View view){
        if(quantity == 10){
            Toast toast = Toast.makeText(this, "Μήπως είσαι λίγο εθισμένος??", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        quantity = quantity + 1 ;
        display(quantity);
    }

    public void decreaseQuant(View view){
        if(quantity == 0){
            Toast toast = Toast.makeText(this, "Δεν πάει πιο κάτω ρεε!", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        quantity = quantity - 1 ;
        display(quantity);
    }

    public void submitOrder (View view) {
        double price = 0;

        EditText editText = (EditText) findViewById(R.id.name_given);
        String name = editText.getText().toString();
        CheckBox santigi = (CheckBox) findViewById(R.id.santigi_check_box);
        CheckBox afrogalo = (CheckBox) findViewById(R.id.afrogalo_check_box);
        if (santigi.isChecked()) {
            has_santigi = true;
        }
        else{
            has_santigi = false;
        }

        if (afrogalo.isChecked()) {
            has_afrogalo = true;
        }
        else{
            has_afrogalo = false;
        }

        price = quantity * 5;
        String msg = "Όνομα: " + name;
        msg += "\nΑφρόγαλο: " + has_afrogalo;
        msg += "\nΣαντιγί: " + has_santigi;
        msg += "\nΤιμή: " + price + "$";
        displayPrice(msg);
        sendOrder(msg);
    }

    protected  void display(int number ) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    protected void displayPrice(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("" + message);
    }

    private void sendOrder(String msg) {
        // Create the text message with a string
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Make an order from my app.");


        // Verify that the intent will resolve to an activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

}
