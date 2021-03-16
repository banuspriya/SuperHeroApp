package com.superhero.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.superhero.myapplication.R;
import com.squareup.picasso.Picasso;

public class SuperHero_Details extends AppCompatActivity {
//fileds in the details page
    TextView textView_Name_Detail,textView_Appearance, textview_Name , textView_Combat ,textView_Power,textView_Race , textView_Gender,textView_Strength,textView_publisher,textView_Relatives;
    ImageView imageview;

//Detail activity to display details of super hero clicked
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_details);
        textView_Name_Detail=(TextView)findViewById(R.id.full_name);
        textView_Combat=(TextView)findViewById(R.id.textViewCombat);
        textView_Power=(TextView)findViewById(R.id.textViewPower);
        textView_Strength=(TextView)findViewById(R.id.textViewStrength);
        textView_Race=(TextView)findViewById(R.id.textViewRace);
        textView_Gender=(TextView)findViewById(R.id.textViewGender);
        textView_publisher=(TextView)findViewById(R.id.textViewPublisher);
        textView_Relatives=(TextView)findViewById(R.id.textViewRelatives);
        textView_Appearance=(TextView)findViewById(R.id.textViewFirstAppearance);
        imageview=(ImageView)findViewById(R.id.detail_image);


        //receive intent from main activity
        Intent intent = getIntent();
        String Name_Detail = intent.getExtras().getString("Name_detail");
        String Name = intent.getExtras().getString("Name");
        String Power = intent.getExtras().getString("Power");
        String Combat= intent.getExtras().getString("Combat");
        String Strength = intent.getExtras().getString("Strength");
        String Race = intent.getExtras().getString("Gender");
        String Gender = intent.getExtras().getString("Name_detail");
        String Publisher = intent.getExtras().getString("Publisher");
        String Appearance = intent.getExtras().getString("Apperance");
        String Relatives=  intent.getExtras().getString("Relatives");
        String Image = intent.getExtras().getString("image");


        //Set values for the fileds after retriveing the intent
        Picasso.with(this)
                .load(Image)
                .into(imageview);
        textView_Name_Detail.setText(Name_Detail);
        textView_Combat.setText(Combat);
        textView_Power.setText(Power);
        textView_Strength.setText(Strength);
        textView_Appearance.setText(Appearance);
        textView_publisher.setText(Publisher);
        textView_Gender.setText(Gender);
        textView_Race.setText(Race);
        textView_Relatives.setText(Relatives);


    }
}
