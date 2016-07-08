package com.apps.type_a.cashlost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    ArrayList<PurchaseItem> purchases = new ArrayList<PurchaseItem>();

    private final int REQUEST_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addMenuItem: {
                Intent intent = new Intent(this, AddScreen.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
            }
            default: {
                return false;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            purchases.add((PurchaseItem) data.getSerializableExtra(AddScreen.PURCHASE_ITEM_KEY));
            int i;
        }
    }
}
