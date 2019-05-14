package com.maruani.games.mylittleheroesgi.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.bumptech.glide.Glide;
import com.maruani.games.mylittleheroesgi.R;
import com.maruani.games.mylittleheroesgi.data.model.Weapon;
import com.maruani.games.mylittleheroesgi.data.service.NetworkProvider;
import java.util.List;

public class ChooseWeaponActivity extends AppCompatActivity {

  @BindView(R.id.activity_choose_weapon_rcv) RecyclerView weaponChoiceRcv;
  @BindView(R.id.activity_choose_weapon)
  ImageView activityChooseWeapon;
  private WeaponAdapter weaponAdapter;

  String pseudo = "";
  String birthdate = "";
  String gendre = "";
  String choosenWeapon = "";
  String choosenWeaponName = "";
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_weapon);

    Intent intent = getIntent();
    if (intent != null){
      Bundle bundle = getIntent().getExtras();
      pseudo = bundle.getString("pseudo");
      birthdate = bundle.getString("birthdate");
      gendre = bundle.getString("gendre");
    }


    ButterKnife.bind(this);

    initRecyclerView();
    loadData();
  }

  private void initRecyclerView() {
    weaponChoiceRcv.setLayoutManager(new LinearLayoutManager(this));
    weaponAdapter = new WeaponAdapter();
    weaponChoiceRcv.setAdapter(weaponAdapter);

    weaponAdapter.setItemClickListener(weapon -> {
      choosenWeapon = weapon.getPictureUrl();
      choosenWeaponName = weapon.getName();
      Glide.with(this).load(weapon.getPictureUrl()).into(activityChooseWeapon);
    });
  }

  private void loadData() {
    NetworkProvider.getInstance().getWeapons(new NetworkProvider.Listener<List<Weapon>>() {
      @Override public void onSuccess(List<Weapon> data) {
        weaponAdapter.setWeaponList(data);
      }

      @Override public void onError(Throwable t) {

      }
    });
  }

  @OnClick(R.id.activity_choose_weapon_next_button) void onNextButtonClick() {
    //validate data
    //if OK navigate
    //if KO show error
    Intent intent = new Intent(this, ShowInformation.class);
    Bundle bundle = new Bundle();
    bundle.putString("pseudo", pseudo);
    bundle.putString("birthdate", birthdate);
    bundle.putString("gendre", gendre);
    bundle.putString("choosenWeapon", choosenWeapon);
    bundle.putString("choosenWeaponName", choosenWeaponName);
    intent.putExtras(bundle);
    startActivity(intent);
  }
}
