package com.example.tpleboncoin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class AdViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_view);
        ImageView ImageAdView = (ImageView) this.findViewById(R.id.imageAdView);
        TextView TitleAdView = findViewById(R.id.textTitleAdView);
        TextView AddressAdView = findViewById(R.id.textAddressAdView);

        Intent i = getIntent();

        TitleAdView.setText("Title : " + i.getStringExtra("title"));
        AddressAdView.setText("Address : " + i.getStringExtra("address"));

        long id = i.getLongExtra("AdId",0);

        DBManager dbManager = DBManager.getDBManager(this);
        dbManager.open();
        AdModel selectedAd = (AdModel) dbManager.getById((int) id);
        String image = selectedAd.getImage();

        Glide.with(this).load(image).into(ImageAdView);

        Button callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = selectedAd.getTelephone();
                String phoneNumber = "tel:"+tel;
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(phoneNumber));
                startActivity(intent);
            }
        });


    }
}

