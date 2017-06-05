package com.myband.myband.contract;

/**
 * Created by Ranieri Aguiar on 11 de mai.
 */

public interface UserContract {
    String TABLE_NAME = "user";

    String COLUMN_ID = TABLE_NAME + "_id";
    String COLUMN_CATEGORY = TABLE_NAME + "_category";
    String COLUMN_NAME = TABLE_NAME + "_userName";
    String COLUMN_LOGIN = TABLE_NAME + "_login";
    String COLUMN_PASSWORD = TABLE_NAME + "_password";
    String COLUMN_LATITUDE = TABLE_NAME + "_latitude";
    String COLUMN_LONGITUDE = TABLE_NAME + "_longitude";
    String COLUMN_AUTOLOGIN = TABLE_NAME + "_autoLogin";

    String ID = TABLE_NAME + "." + COLUMN_ID;
    String CATEGORY = TABLE_NAME + "." + COLUMN_CATEGORY;
    String NAME = TABLE_NAME + "." + COLUMN_NAME;
    String LOGIN = TABLE_NAME + "." + COLUMN_LOGIN;
    String PASSWORD = TABLE_NAME + "." + COLUMN_PASSWORD;
    String LATITUDE = TABLE_NAME + "." + COLUMN_LATITUDE;
    String LONGITUDE = TABLE_NAME + "." + COLUMN_LONGITUDE;
    String AUTOLOGIN = TABLE_NAME + "." + COLUMN_AUTOLOGIN;
}
