package com.maruani.games.mylittleheroesgi.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.maruani.games.mylittleheroesgi.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowInformation extends AppCompatActivity {

    String pseudo = "";
    String birthdate = "";
    String gendre ="";
    String choosenWeapon = "";
    String choosenWeaponName = "";

    @BindView(R.id.activity_show_information_pseudo) TextView getPseudo;
    @BindView(R.id.activity_show_information_gendre) TextView getGendre;
    @BindView(R.id.activity_show_information_birthdate) TextView getBirthdate;
    @BindView(R.id.activity_show_information_weapon_name) TextView getChoosenWeaponName;
    @BindView(R.id.activity_show_information_choosen_weapon) ImageView getChoosenWeapon;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_information);
        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = getIntent().getExtras();
            pseudo = bundle.getString("pseudo");
            birthdate = bundle.getString("birthdate");
            gendre = bundle.getString("gendre");
            choosenWeapon = bundle.getString("choosenWeapon");
            choosenWeaponName = bundle.getString("choosenWeaponName");
        }
        ButterKnife.bind(this);
        getPseudo.setText(pseudo);
        getGendre.setText(gendre);
        getBirthdate.setText(birthdate);
        getChoosenWeaponName.setText(choosenWeaponName);
        Glide.with(this).load(choosenWeapon).into(getChoosenWeapon);

    }



}
