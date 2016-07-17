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
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    private ArrayList<PurchaseItem> purchases = new ArrayList<PurchaseItem>();
    private final int REQUEST_CODE = 0;
    private RelativeLayout emptyLayout, mainLayout;
    private RecyclerView purchaseList;
    private TextView allCost;
    private static final String SERIALIZE_FILE_NAME = "/savedData.ser";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        initViews();
        File file = new File(getFilesDir() + SERIALIZE_FILE_NAME);
        if (file.exists()) {
            purchases = readFromFile();
            fillPurchaseList();
            setAllCost();
            emptyLayout.setVisibility(View.INVISIBLE);
            mainLayout.setVisibility(View.VISIBLE);
        }
    }

    private void initViews() {
        emptyLayout = (RelativeLayout) findViewById(R.id.emptyView);
        mainLayout = (RelativeLayout) findViewById(R.id.mainView);
        purchaseList = (RecyclerView) findViewById(R.id.listOfPurchases);
        allCost = (TextView) findViewById(R.id.allPurchasesCost);
    }

    private void fillPurchaseList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        purchaseList.setLayoutManager(layoutManager);
        purchaseList.setAdapter(new PurchaseListAdapter(purchases, this));
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
            emptyLayout.setVisibility(View.INVISIBLE);
            mainLayout.setVisibility(View.VISIBLE);
            writeToFile(purchases);
            fillPurchaseList();
            setAllCost();
        }
    }

    public boolean writeToFile(ArrayList<PurchaseItem> purchases) {
        File file;
        try {
            file = new File(getFilesDir() + SERIALIZE_FILE_NAME);
            FileOutputStream fileOut;
            ObjectOutputStream out;
            if (!file.exists()) {
                file.createNewFile();
            }

            fileOut = new FileOutputStream(file, false);
            out = new ObjectOutputStream(fileOut);
            out.writeObject(purchases);
            out.close();
            fileOut.close();

            return true;
        } catch (Exception exc) {
            Toast.makeText(this, exc.toString(), Toast.LENGTH_LONG).show();
            return false;
        }
    }

    public ArrayList<PurchaseItem> readFromFile() {
        FileInputStream fileIn;
        ObjectInputStream in;
        ArrayList<PurchaseItem> purchases;
        try {
            fileIn = new FileInputStream(getFilesDir() + SERIALIZE_FILE_NAME);
            in = new ObjectInputStream(fileIn);
            purchases = (ArrayList<PurchaseItem>) in.readObject();
            in.close();
            fileIn.close();
            return purchases;
        } catch (Exception exc) {
            Toast.makeText(this, exc.toString(), Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void setAllCost() {
        int all = 0;
        for (int i = 0; i < purchases.size(); i++) {
            all += purchases.get(i).getCost();
        }
        String allString = String.valueOf(all)+" "+getString(R.string.rub);
        allCost.setText(allString);
    }
}
