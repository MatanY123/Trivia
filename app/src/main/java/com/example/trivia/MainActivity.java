package com.example.trivia;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> resultLauncher;
    private FBModule fbModule;

    private ConstraintLayout ll;
    private String backgroundColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = findViewById(R.id.main);

        fbModule =  new FBModule(this);

        resultLauncher = registerForActivityResult(new
                ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if (o.getResultCode()==RESULT_OK)
                {
                    Intent data = o.getData();
                    String str = data.getStringExtra("color");
                    fbModule.writeBackgroundColorToFb(str);


                }


            }
        });
    }
    public void onClickStart(View view) {

        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("background_color", backgroundColor);

        startActivity(intent);
    }
    public void onClickSetting(View view) {

        Intent i = new Intent(this, SettingActivity.class);
        resultLauncher.launch(i);
    }

    public void onClickInstruction(View view) {
        Intent i = new Intent(this, InstructionActivity.class);
        resultLauncher.launch(i);
    }

    public void setNewColorFromFb(String str) {
        //הפיירבייס קורא לפעולה בפעם הראשונה ואחרי כל פעם שהמשתמש משנה את הצבע
        backgroundColor = str;
        Toast.makeText(MainActivity.this,str,Toast.LENGTH_SHORT).show();
        setBackgroundColor(str);

    }
    public void setBackgroundColor(String color)
    {
      switch (color)
      {
          case "Red":
          {
              ll.setBackgroundColor(Color.RED);
              break;
          }
          case "Blue":
          {
              ll.setBackgroundColor(Color.BLUE);
              break;
          }
          case "Yellow":
          {
              ll.setBackgroundColor(Color.YELLOW);
              break;
          }
          case "Pink":

          {
              ll.setBackgroundColor(Color.argb(100,100,100,100));
              break;
          }
          default:
          {
              ll.setBackgroundColor(Color.WHITE);
          }
      }

    }
}