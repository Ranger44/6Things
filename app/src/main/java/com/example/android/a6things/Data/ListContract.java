package com.example.android.a6things.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by holly on 8/13/2017.
 */

public final class ListContract {

    private ListContract() {}
    public static final String CONTENT_AUTHORITY = "com.example.android.a6things";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_LIST = "list";

    public static class ListEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_LIST);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LIST;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LIST;

        public static final String TABLE_NAME = "list";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TASK = "task";
        public static final String COLUMN_COMPLETE = "completed";
    }
}
