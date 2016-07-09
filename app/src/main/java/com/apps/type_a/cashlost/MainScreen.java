package com.apps.type_a.cashlost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    ArrayList<PurchaseItem> purchases = new ArrayList<PurchaseItem>();
    private final int REQUEST_CODE = 0;
    RelativeLayout empty, main;
    RecyclerView purchaseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        initViews();
    }

    private void initViews() {
        empty = (RelativeLayout) findViewById(R.id.emptyView);
        main = (RelativeLayout) findViewById(R.id.mainView);
        purchaseList = (RecyclerView) findViewById(R.id.listOfPurchases);
    }

    private void fillPurchaseList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        purchaseList.setLayoutManager(layoutManager);
        purchaseList.setAdapter(new PurchaseListAdapter(purchases,this));
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
            empty.setVisibility(View.INVISIBLE);
            main.setVisibility(View.VISIBLE);
            fillPurchaseList();
        }
    }
}
