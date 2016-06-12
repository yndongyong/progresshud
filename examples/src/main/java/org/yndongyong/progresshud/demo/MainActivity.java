package org.yndongyong.progresshud.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.yndongyong.progresshud.DProgressHUD;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.id_btn1);
        
        
        btn1.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn1:
                showSpin();
                break;
            
        }
    }

    private void showSpin() {
        new DProgressHUD.Builder(this)
                .setStyle(DProgressHUD.Style.SPIN_INDETERMINATE).build().show();
    }
}
