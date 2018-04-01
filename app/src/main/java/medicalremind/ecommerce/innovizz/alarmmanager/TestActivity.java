package medicalremind.ecommerce.innovizz.alarmmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import medicalremind.ecommerce.innovizz.alarmmanager.service.RingtonePlayingService;

/**
 * Created by Hassan M Ashraful on 3/29/2018.
 * Jr Software Developer
 * Innovizz Technology
 */

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent stopIntent = new Intent(getApplicationContext(), RingtonePlayingService.class);
        getApplicationContext().stopService(stopIntent);

        Toast.makeText(this, getIntent().getStringExtra("alarm"), Toast.LENGTH_SHORT).show();
    }
}
