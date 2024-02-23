package com.example.tpleboncoin;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DbAdAdapter extends CursorAdapter {
    private final int item_layout;

    public DbAdAdapter(Context context, Cursor c, int layout) {
        super(context, c);
        item_layout = layout;
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(item_layout, viewGroup, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView idTextView = (TextView) view.findViewById(R.id.IdTV);
        TextView titleTextView = (TextView) view.findViewById(R.id.titleTV);
        TextView addressTextView = (TextView) view.findViewById(R.id.adressTV);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageIV);

        String id = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper._id));
        String title = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.TITLE));
        String address = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.ADDRESS));
        String image = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.IMAGE));

        idTextView.setText(id);
        titleTextView.setText(title);
        addressTextView.setText(address);
        Glide.with(view).load(image).into(imageView); //Glide is a library to insert an image into an imageview with a url.
    }
}
