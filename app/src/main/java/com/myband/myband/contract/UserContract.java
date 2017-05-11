package com.myband.myband.contract;

import android.provider.BaseColumns;

/**
 * Created by Ranieri Aguiar on 11 de mai.
 */

public interface UserContract extends BaseColumns {
    String TABLE_NAME = "user";

    String NAME = "nome";
    String PASSWORD = "password";
    String LOCATION = "location";
}
