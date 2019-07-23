package com.example.firebasedatabasetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainGrid)
    GridLayout gridLayout;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSingleEvent(gridLayout);
    }

    private void moveToLetActivity(String type){
        Intent intent=new Intent(this,ToLetActivity.class);
        intent.putExtra("type",type);
        startActivity(intent);
    }

    // we are setting onClickListener for each element
    private void setSingleEvent(GridLayout gridLayout) {
        for(int i = 0; i<gridLayout.getChildCount();i++){
            CardView cardView=(CardView)gridLayout.getChildAt(i);
            final int finalI= i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(finalI==0){
                        Toast.makeText(MainActivity.this, "Clicked at Mess", Toast.LENGTH_SHORT).show();
                        type="mess";
                        moveToLetActivity(type);
                    }
                    else if(finalI==1){
                        Toast.makeText(MainActivity.this, "Clicked at Hostel", Toast.LENGTH_SHORT).show();
                        type="hostel";
                        moveToLetActivity(type);
                    }
                    else if(finalI==2){
                        Toast.makeText(MainActivity.this, "Clicked at Flat", Toast.LENGTH_SHORT).show();
                        type="flat";
                        moveToLetActivity(type);
                    }
                    else if(finalI==3){
                        Toast.makeText(MainActivity.this, "Clicked at Sublet", Toast.LENGTH_SHORT).show();
                        type="sublet";
                        moveToLetActivity(type);
                    }
                    else if(finalI==4){
                        Toast.makeText(MainActivity.this, "Clicked at Garage", Toast.LENGTH_SHORT).show();
                        type="garage";
                        moveToLetActivity(type);
                    }
                    else if(finalI==5){
                        Toast.makeText(MainActivity.this, "Clicked at Office", Toast.LENGTH_SHORT).show();
                        type="office";
                        moveToLetActivity(type);
                    }
                }
            });
        }
    }

    /**
     * move to post activity
     */
    private void movePostActivity(){
        Intent intent=new Intent(this,PostActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.fab_btn)
    void onClickFab(){
        movePostActivity();
    }
}
