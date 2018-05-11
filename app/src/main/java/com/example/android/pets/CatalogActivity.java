/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.pets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.android.pets.data.PetContract;
import com.example.android.pets.data.PetDbHelper;

/**
 * Displays list of pets that were entered and stored in the app.
 */
public class CatalogActivity extends AppCompatActivity {


    private PetDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        // Veritabanımıza erişmek için SQLiteOpenHelper'ın bir alt sınıfını başlatıyoruz.
        // ve mevcut olan etkinliğin içeriği gönderilir.
        mDbHelper = new PetDbHelper(this);
        // Eklenen Satır sayısını döndürecek metot. ( Veritabanından )
        displayDatabaseInfo();
    }

    /**
     * Evcil hayvan veritabanının durumu hakkında ekrandaki
     * TextView de bilgi görüntülemek için geçici yardımcı yöntem.
     */
    private void displayDatabaseInfo() {


        // Okuma yapmak için SQLiteDatabase referansını mDbHelper ile bütünleştiriyoruz.
        // Bir veritabanına bağlantı açar ve bir veritabanı oluşturur. Biz kendi veritabanımız için kullandık.
        // .open shelter.db ile aynı mantığa sahiptir.
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        /**
         Evcil hayvan tablosundaki tüm satırları içeren bir İmleç almak için
         bu ham SQL sorgusunu "Evcil hayvanlardan SEÇİN" işlemini gerçekleştirin.
         Cursor tüm satırları ifade etmektedir.
         */
        Cursor cursor = db.rawQuery("SELECT * FROM " + PetContract.PetEntry.TABLE_NAME, null);

        try {
            // İmlecin satır sayısını gösterdiğini Textview içerisinde göstereceğiz.
            TextView displayTextview = (TextView) findViewById(R.id.text_view_pet);
            displayTextview.setText("Number of rows in pets database table: " + cursor.getCount());
        } finally {
            // Okuma işlemi bittiğinde imleci kapatarak performans faydası sağlayabiliriz.
            cursor.close();
        }

    }

    // Activity yeniden başlatıldığında
    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:

                insertPet();
                displayDatabaseInfo();

                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void insertPet() {

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PetContract.PetEntry.COLUMN_PET_NAME,"Garfield");
        values.put(PetContract.PetEntry.COLUMN_PET_BREED, "Terrier");
        values.put(PetContract.PetEntry.COLUMN_PET_GENDER, PetContract.PetEntry.GENDER_MALE);
        values.put(PetContract.PetEntry.COLUMN_PET_WEIGHT, 7);
        // İlgili Tabloya values values değerlerini aktar. Ekle , Insert Et
        long newRowId = db.insert(PetContract.PetEntry.TABLE_NAME, null, values);
    }
}
