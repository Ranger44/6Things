package com.example.android.a6things.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by holly on 8/20/2017.
 */

public class ListProvider extends ContentProvider {

    public static final String LOG_TAG = ListProvider.class.getSimpleName();

    private static final int LIST = 1000;
    private static final int LIST_ID = 1001;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(ListContract.CONTENT_AUTHORITY, ListContract.PATH_LIST, LIST);
        sUriMatcher.addURI(ListContract.CONTENT_AUTHORITY, ListContract.PATH_LIST + "/#", LIST_ID);
    }

    private DbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case LIST:
                cursor = database.query(ListContract.ListEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case LIST_ID:
                selection = ListContract.ListEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(ListContract.ListEntry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch(match) {
            case LIST:
                return ListContract.ListEntry.CONTENT_LIST_TYPE;
            case LIST_ID:
                return ListContract.ListEntry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI " + uri + " with match " + match);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        //SQLiteDatabase database = mDbHelper.getWritableDatabase();

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case LIST:
                return insertItem(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertItem(Uri uri, ContentValues values) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        long id = database.insert(ListContract.ListEntry.TABLE_NAME, null, values);
        if(id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int numRowsDeleted = 0;

        final int match = sUriMatcher.match(uri);
        switch(match) {
            case LIST:
                numRowsDeleted = database.delete(ListContract.ListEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case LIST_ID:
                selection = ListContract.ListEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
                numRowsDeleted = database.delete(ListContract.ListEntry.TABLE_NAME, selection, selectionArgs);
                break;
        }
        if(numRowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numRowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {

        final int match = sUriMatcher.match(uri);
        switch(match) {
            case LIST:
                return updateList(uri, values, selection, selectionArgs);
            case LIST_ID:
                selection = ListContract.ListEntry._ID + "=?";
                selectionArgs = new String[] {String.valueOf(ContentUris.parseId(uri))};
            default:
                throw new IllegalArgumentException("Update not supported for " + uri);
        }
    }

    private int updateList(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if(values.size() == 0) {
            return 0; //don't update if no values
        }
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        int numRowsUpdated = database.update(ListContract.ListEntry.TABLE_NAME, values, selection, selectionArgs);
        if (numRowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return numRowsUpdated;
    }
}
