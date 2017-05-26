package com.cjt2325.colorbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.cjt2325.colorbarlibrary.JColorBar;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout layout;
    private JColorBar colorBar_1;

    private Button btn_default;
    private Button btn_red;
    private Button btn_green;
    private Button btn_blue;
    private Button btn_black;

    private int[] defaultColors = {
            0xFFFFFFFF,
            0XFFF03411,
            0XFFFEBB46,
            0XFFFFE900,
            0XFFC3FE30,
            0XFF00B109,
            0XFF25D1FF,
            0XFF157AFC,
            0XFF0037C6,
            0XFF925AF9,
            0XFFFE99FF,
            0XFFFC6A8F,
            0XFFF725BA
    };

    private int[] grayColors = {
            0XFF333333,
            0XFF444444,
            0XFF555555,
            0XFF666666,
            0XFF777777,
            0XFF888888,
            0XFF999999,
            0XFFAAAAAA,
            0XFFBBBBBB,
            0XFFCCCCCC,
            0XFFDDDDDD,
            0XFFEEEEEE,
            0XFFFFFFFF
    };
    private int[] redColors = {
            0XFFFFBBBB,
            0XFFFFAAAA,
            0XFFFF9999,
            0XFFFF8888,
            0XFFFF7777,
            0XFFFF6666,
            0XFFFF5555,
            0XFFFF4444,
            0XFFFF3333,
            0XFFFF2222,
            0XFFFF1111,
            0XFFFF0000
    };
    private int[] greenColors = {
            0XFFBBFFBB,
            0XFFAAFFAA,
            0XFF99FF99,
            0XFF88FF88,
            0XFF77FF77,
            0XFF66FF66,
            0XFF55FF55,
            0XFF44FF44,
            0XFF33FF33,
            0XFF22FF22,
            0XFF11FF11,
            0XFF00FF00
    };
    private int[] blueColors = {
            0XFFBBBBFF,
            0XFFAAAAFF,
            0XFF9999FF,
            0XFF8888FF,
            0XFF7777FF,
            0XFF6666FF,
            0XFF5555FF,
            0XFF4444FF,
            0XFF3333FF,
            0XFF2222FF,
            0XFF1111FF,
            0XFF0000FF
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        layout = (RelativeLayout) findViewById(R.id.activity_main);
        //默认自带颜色(与微信一样)
        colorBar_1 = (JColorBar) findViewById(R.id.colorbar_1);
        colorBar_1.setColorsUdateListener(new JColorBar.ColorsUdateListener() {
            @Override
            public void update(int color) {
                layout.setBackgroundColor(color);
            }
        });

        btn_default = (Button) findViewById(R.id.btn_default);
        btn_red = (Button) findViewById(R.id.btn_red);
        btn_green = (Button) findViewById(R.id.btn_green);
        btn_blue = (Button) findViewById(R.id.btn_blue);
        btn_black = (Button) findViewById(R.id.btn_black);

        btn_default.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorBar_1.setColors(defaultColors);
            }
        });
        btn_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorBar_1.setColors(redColors);
            }
        });
        btn_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorBar_1.setColors(greenColors);
            }
        });
        btn_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorBar_1.setColors(blueColors);
            }
        });
        btn_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorBar_1.setColors(grayColors);
            }
        });
    }
}
