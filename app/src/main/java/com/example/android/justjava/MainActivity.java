package com.example.android.justjava;

/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 * <p>
 * package com.example.android.justjava;
 */


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText name = (EditText) findViewById(R.id.name);
        Editable enter = name.getText();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate);
        boolean hasChocolate = chocolate.isChecked();
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = "Total: $" + price;
        priceMessage = priceMessage + "\n" + getString(R.string.thank_you);
        displayMessage(priceMessage);

        String displaySummary = createOrderSummary(price, hasWhippedCream, hasChocolate, enter);
        displayMessage(displaySummary);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, " " + getString(R.string.subjectJ) + enter);
        intent.putExtra(Intent.EXTRA_TEXT, displaySummary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }


    }

    private String createOrderSummary(int price, boolean cream, boolean choco, Editable Ahmed) {

        //String summary = "Name: Ahmed Zaki" + "\nAdd whipped cream?" + maze +"\nQuantity: " + quantity + "\nTotal: $" + price + "\nThank you.";
        String summary = " " + getString(R.string.nameJ) + Ahmed;
        summary += "\n" + getString(R.string.addwhJ) + cream;
        summary += "\n" + getString(R.string.addchJ) + choco;
        summary += "\n" + getString(R.string.quantityJ) + quantity;
        summary += "\n" + getString(R.string.totalJ) + price + "\n" + getString(R.string.thank_you);
        return summary;


    }

    /**
     * Calculates the price of the order.
     *
     * @return price
     */
    private int calculatePrice(boolean hasWhippedCream1, boolean hasChocolate2) {
        int basePrice = 5;

        if (hasWhippedCream1) {
            basePrice += 1;
        }

        if (hasChocolate2) {
            basePrice += 2;
        }

        return basePrice * quantity;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /**
     * This method is called when the plus button is clicked.
     */

    public void increment(View view) {

        if (quantity == 100) {
            Toast.makeText(this, "You cannot order more than 100 cups.", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);

    }


    /**
     * This method is called when the minus button is clicked.
     */

    public void decrement(View view) {

        if (quantity == 1) {

            Toast.makeText(this, "You cannot order less than 1 cup.", Toast.LENGTH_SHORT).show();
            return;
        }

        quantity = quantity - 1;
        displayQuantity(quantity);

    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}
