package com.example.android.pets.data;

import android.provider.BaseColumns;

/**
 * Burada sabitler yer alacağı için final olarak tanımladık.
 * Bu sınıftan nesne oluşturulamaz.
 * Her tablo için ayrı bir iç sınıf oluşturulmalıdır.
 * İlk olarak veritabanı şemamızı gün yüzüne çıkarmamız ona göre sözleşme hazırlamamız gerekmektedir.
 * Burada kısaca Veritabanı içinde yer alacak tabloların sabit değişkenleri oluşturuluyor. Tip olarak ise kolon tipleri değil sabitlerin
 * tipleri belirtiliyor.
 */

public final class PetContract {

    /**
     * Birinin sözleşme sınıfını yanlışlıkla başlatmasını önlemek için,
     * boş bir kurucuyu verin.
     */
    private PetContract() {
    }

    /*
        Evcil hayvan veritabanı tablosu için sabit değerleri tanımlayan iç sınıf.
        Tablodaki her giriş tek bir evcil hayvanı temsil eder.
     */
    public static final class PetEntry implements BaseColumns {

        // Tablo Adı Sabiti
        public final static String TABLE_NAME = "pets";

        // Sabitlerin Tipidir.  Tablo kolonlarının tipi değildir.

        public final static String _ID = BaseColumns._ID; // Veri ID ' si - Benzersiz
        public final static String COLUMN_PET_NAME = "name"; // İsim Tip TEXT
        public final static String COLUMN_PET_BREED = "breed"; // Nesil Tip TEXT
        public final static String COLUMN_PET_GENDER = "gender"; // Cinsiyet Tip INTEGER
        public final static String COLUMN_PET_WEIGHT = "weight"; // Ağırlık Tip INTEGER


        // Cinsiyet Sabitleri - 3 Değer alabileceği için baştan belirledik.
        // Bu tür sınırlı sayıda değer alan içerikler sabit olarak belirtilmelidir.
        public static final int GENDER_UNKNOWN = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_FEMALE = 2;
    }

}
