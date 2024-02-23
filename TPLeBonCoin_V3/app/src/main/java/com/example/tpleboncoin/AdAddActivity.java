package com.example.tpleboncoin;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class AdAddActivity extends AppCompatActivity {

    private Uri selectedImageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ad);

        Intent i = getIntent();

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);

        Button AddAdValidateButton = findViewById(R.id.AddAdValidateButton);
        TextView AddAdTitle = findViewById(R.id.AddAdTitle);
        TextView AddAdAddress = findViewById(R.id.AddAdAdress);
        TextView AddAdTel = findViewById(R.id.AddAdTel);
        DBManager dbManager = DBManager.getDBManager(this);
        AddAdValidateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Rajout de l'AD à la base de donnée
                AdModel ad = new AdModel(AddAdTitle.getText().toString(),AddAdAddress.getText().toString(),selectedImageUri.toString(),AddAdTel.getText().toString());
                dbManager.open();
                dbManager.insert(ad);

                Intent intentToListView = new Intent(AdAddActivity.this,AdListViewActivity.class);
                startActivity(intentToListView);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            ImageView AddAdImage = findViewById(R.id.AddAdImage);
            AddAdImage.setImageURI(selectedImageUri);
        }
    }
}