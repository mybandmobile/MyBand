package com.myband.myband.contract;


/**
 * Created by SuporteTécnico on 12/06/2017.
 */

public interface EventContract {
    String TABLE_NAME = "event";

    String COLUMN_ID = TABLE_NAME + "_id";
    String COLUMN_REQUESTER = TABLE_NAME + "_requester";
    String COLUMN_PROVIDER = TABLE_NAME + "_provider";
    String COLUMN_SUBGENRE = TABLE_NAME + "_subGenre";
    String COLUMN_INITIALDATE = TABLE_NAME + "_initialDate";
    String COLUMN_FINALDATE = TABLE_NAME + "_finalDate";


    String ID = TABLE_NAME + "." + COLUMN_ID;
    String REQUESTER = TABLE_NAME + "." + COLUMN_REQUESTER;
    String PROVIDER = TABLE_NAME + "." + COLUMN_PROVIDER;
    String SUBGENRE = TABLE_NAME + "." + COLUMN_SUBGENRE;
    String INITIALDATE = TABLE_NAME + "." + COLUMN_INITIALDATE;
    String FINALDATE = TABLE_NAME + "." + COLUMN_FINALDATE;
}
