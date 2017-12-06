package com.unipd.ingsw.gruppo3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class BillViewerGruppo1 extends AppCompatActivity {

    //Dal Maso
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        setContentView(R.layout.activity_bill_viewer);
        Intent intent = getIntent();
        String imgPath = intent.getExtras().getString("imagePath");
        String imgName = intent.getExtras().getString("imageName");
        setTitle(imgName);
        TextView billName = (TextView)findViewById(R.id.billName);
        billName.setText(imgName);
        ImageView imgView = (ImageView)findViewById(R.id.billImage);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(imgPath,bmOptions);
        imgView.setImageBitmap(bitmap);

    }
    //Dal Maso
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
