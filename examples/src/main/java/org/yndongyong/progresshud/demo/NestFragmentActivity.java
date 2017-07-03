package org.yndongyong.progresshud.demo;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NestFragmentActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nest_fragment);
        getSupportFragmentManager().beginTransaction().add(R.id.container,new BlankFragment() ).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
