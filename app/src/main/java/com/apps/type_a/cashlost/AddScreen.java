package com.apps.type_a.cashlost;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Type_A on 06.07.2016.
 */
public class AddScreen extends AppCompatActivity {

    EditText place, cost, name, date;
    Spinner type;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_screen);
        setTitle(R.string.addScreenTitle);
        initViews();
        setDate();
        save();
    }

    private void initViews() {
        date = (EditText) findViewById(R.id.purchaseDateField);
        place = (EditText) findViewById(R.id.placeField);
        cost = (EditText) findViewById(R.id.costField);
        name = (EditText) findViewById(R.id.purchaseNameField);
        type = (Spinner) findViewById(R.id.purchaseTypeSpinner);
        saveButton = (Button) findViewById(R.id.saveButton);
    }

    public void setDate() {
        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DataPickerDialog dialog = new DataPickerDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePickerDialog");
                }
            }
        });
    }

    public void save() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFieldsEmpty()) {
                    Toast.makeText(AddScreen.this, R.string.emptyFields, Toast.LENGTH_SHORT).show();
                } else {
                    PurchaseDate currentDate = parseDateFromString(date.getText().toString());
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_screen_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backMenuItem: {
                finish();
                return true;
            }
            default: {
                return false;
            }
        }
    }


    private PurchaseDate parseDateFromString(String date) {
        String[] parsed = date.split(".");
        int day = Integer.valueOf(parsed[1]);
        int month = Integer.valueOf(parsed[2]);
        int year = Integer.valueOf(parsed[3]);
        return new PurchaseDate(day, month, year);
    }

    private Boolean isFieldsEmpty() {
        return date.getText().toString().equals("") || place.getText().toString().equals("") || cost.getText().toString().equals("") || name.getText().toString().equals("");
    }

}
