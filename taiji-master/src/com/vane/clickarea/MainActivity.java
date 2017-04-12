package com.vane.clickarea;

import android.os.Bundle;
import android.app.Activity;
import android.view.*;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
    
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState);
        setContentView( R.layout.activity_main);
        findViewById( R.id.menu_1).setOnClickListener( this);
        findViewById( R.id.menu_2).setOnClickListener( this);
        findViewById( R.id.menu_3).setOnClickListener( this);
        findViewById( R.id.menu_4).setOnClickListener( this);

        WindowManager wm = getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();

        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) findViewById(R.id.menu_1).getLayoutParams();
        params.width=width;
        params.height=width;
        params.gravity= Gravity.BOTTOM;

        findViewById(R.id.menu_1).setLayoutParams(params);
        findViewById( R.id.menu_2).setLayoutParams(params);
        findViewById( R.id.menu_3).setLayoutParams(params);
        findViewById( R.id.menu_4).setLayoutParams(params);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate( R.menu.main, menu);
        return false;
    }

    @Override
    public void onClick(View v) {
        if(null != mToast) {
            mToast.cancel();
        }
        switch(v.getId()) {
            case R.id.menu_1:
                mToast = Toast.makeText( this, "1", Toast.LENGTH_SHORT);
                break;
            case R.id.menu_2:
                mToast = Toast.makeText( this, "2", Toast.LENGTH_SHORT);
                break;
            case R.id.menu_3:
                mToast = Toast.makeText( this, "3", Toast.LENGTH_SHORT);
                break;
            case R.id.menu_4:
                mToast = Toast.makeText( this, "4", Toast.LENGTH_SHORT);
                break;
        }
        mToast.show();
    }
}
