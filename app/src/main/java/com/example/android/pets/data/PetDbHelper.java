package com.example.android.pets.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database helper for Pets app. Manages database creation and version management.
 * Evcil hayvan uygulaması için veritabanı yardımcısı. Veritabanı oluşturma ve sürüm yönetimini yönetir.
 */
public class PetDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = PetDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     * Veritabanı dosyasının adı
     **/
    public static final String DATABASE_NAME = "shelter.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     * Veritabanı sürümü. Veritabanı şemasını değiştirirseniz, veritabanı sürümünü artırmanız gerekir.
     */
    public static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link PetDbHelper}.
     * Yeni bir {@link PetDbHelper} örneği oluşturur.
     */
    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     * Bu veritabanı ilk kez oluşturulduğunda çağrılır.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Evcil hayvan tablosunu oluşturmak için SQL ifadesini içeren bir Dize oluştur
        String SQL_CREATE_PETS_TABLE = "CREATE TABLE " + PetContract.PetEntry.TABLE_NAME + " ("
                + PetContract.PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetContract.PetEntry.COLUMN_PET_NAME + " TEXT NOT NULL, "
                + PetContract.PetEntry.COLUMN_PET_BREED + " TEXT, "
                + PetContract.PetEntry.COLUMN_PET_GENDER + " INTEGER NOT NULL, "
                + PetContract.PetEntry.COLUMN_PET_WEIGHT + " INTEGER NOT NULL DEFAULT 0);";
        // SQL Sözdizimi Yürütülür - Çalıştırılır.
        db.execSQL(SQL_CREATE_PETS_TABLE);

    }

    // Veritabanı Yükseltmesi - Geliştirilmesi durumunda bu metot kullanılır.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Veritabanı hala sürüm 1 de bu yüzden burada yapılacak bir şey yok.
    }
}
