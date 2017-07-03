package org.yndongyong.progresshud.demo;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.yndongyong.progresshud.DProgressHUD;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;

    private DProgressHUD progressDialog;
    private DProgressHUD pieDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.id_btn1);
        btn1.setOnClickListener(this);

        btn2 = (Button) findViewById(R.id.id_btn2);
        btn2.setOnClickListener(this);

        btn3 = (Button) findViewById(R.id.id_btn3);
        btn3.setOnClickListener(this);


        btn4 = (Button) findViewById(R.id.id_btn4);
        btn4.setOnClickListener(this);

        btn5 = (Button) findViewById(R.id.id_btn5);
        btn5.setOnClickListener(this);

        btn6 = (Button) findViewById(R.id.id_btn6);
        btn6.setOnClickListener(this);

        btn7 = (Button) findViewById(R.id.id_btn7);
        btn7.setOnClickListener(this);

        btn8 = (Button) findViewById(R.id.id_btn8);
        btn8.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_btn1:
                progressDialog = DProgressHUD.show(this, DProgressHUD.Style.SPIN_INDETERMINATE);
                break;
            case R.id.id_btn2:
                progressDialog = DProgressHUD.show(this, DProgressHUD.Style.SPIN_INDETERMINATE,
                        "正在加载中...");
                break;

            case R.id.id_btn3:
                progressDialog = DProgressHUD.show(this, DProgressHUD.Style.ALERT_ACTION_INFO,
                        "这是一条提示信息");
                break;
            case R.id.id_btn5:
                progressDialog = DProgressHUD.show(this, DProgressHUD.Style.ALERT_ACTION_DONE,
                        "操作成功！");
                break;

            case R.id.id_btn6:
                progressDialog = DProgressHUD.show(this, DProgressHUD.Style.ALERT_ACTION_WARN,
                        "警告信息！");
                break;

            case R.id.id_btn7:
                progressDialog = DProgressHUD.show(this, DProgressHUD.Style.ALERT_ACTION_ERROR,
                        "操作失败！");
                break;

            case R.id.id_btn4:
//                pieDialog = DProgressHUD.show(this, DProgressHUD.Style.PIE_DETERNIMATER,"0%",true);
                pieDialog = DProgressHUD.show(this, DProgressHUD.Style.PIE_DETERNIMATER, null, true);
                scheduleProgress();
                break;

            case R.id.id_btn8:
                startActivity(new Intent(this,NestFragmentActivity.class));
                break;
        }
    }

    private void scheduleProgress() {
        if (pieDialog != null) {
            new Handler().postDelayed(new Runnable() {
                int progress = 0;

                @Override
                public void run() {
                    int random = new Random().nextInt(10);
                    progress += random;
                    if (progress <= 100 && pieDialog != null && pieDialog.isShowing()) {
                        pieDialog.setProgress(progress);
//                        pieDialog.setLabel(progress+"%");
                        new Handler(getMainLooper()).postDelayed(this, 500);
                    } else {
//                        pieDialog.setLabel("100%");
                        pieDialog.setProgress(100);
                    }
                }
            }, 1000);
        }

    }

}
