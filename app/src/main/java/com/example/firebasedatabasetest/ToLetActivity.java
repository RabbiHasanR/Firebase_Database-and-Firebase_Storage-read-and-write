package com.example.firebasedatabasetest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToLetActivity extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private List<Post> posts = new ArrayList<>();
    private List<Photo> photos=new ArrayList<>();
    private String type;
    private DatabaseReference myRef2;

    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view)
    TextView emptyTextView;
//    @BindView(R.id.imageSlider)
    private SliderView sliderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_let);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Posts");
        myRef2= database.getReference().child("Photos");
        sliderView=(SliderView)findViewById(R.id.imageSlider);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        if(intent.hasExtra("type")){
            type=intent.getExtras().getString("type");
            setPostOnRecyclerview();
        }
    }
//    private void retrivePhoto(String key){
//        ref.child(key).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Log.d("Uri:",String.valueOf(uri));
//                Toast.makeText(ToLetActivity.this, String.valueOf(uri), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    /**
     * set post on recyclerview
     */
    private void setPostOnRecyclerview(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // StringBuffer stringbuffer = new StringBuffer();
                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Post post = dataSnapshot1.getValue(Post.class);
                    /**
                     * add post based on type
                     */
                    if(post.getType().equalsIgnoreCase(type)){
                        posts.add(post);
                        myRef2.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for(DataSnapshot dataSnapshot2:dataSnapshot.child(post.getKey()).getChildren()){
                                    Photo photo=dataSnapshot2.getValue(Photo.class);
                                    photos.add(photo);
                                }
//                                for(Photo photo1:photos){
//                                    Log.d("Uri:",photo1.getUri());
//                                }
//                                Log.d("photos:",String.valueOf(photos.isEmpty()));
                                if(!photos.isEmpty()){
                                    SliderAdapter adapter = new SliderAdapter(photos);
                                    sliderView.setSliderAdapter(adapter);

                                    sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
                                    sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
                                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                                    sliderView.setIndicatorSelectedColor(Color.WHITE);
                                    sliderView.setIndicatorUnselectedColor(Color.GRAY);
                                    sliderView.startAutoCycle();

                                    sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
                                        @Override
                                        public void onIndicatorClicked(int position) {
                                            sliderView.setCurrentPagePosition(position);
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
                Log.d("Posts:",String.valueOf(posts.isEmpty()));
                if(posts.isEmpty()){
                    recyclerView.setVisibility(View.INVISIBLE);
                    emptyTextView.setVisibility(View.VISIBLE);
                }
                else {
                    recyclerView.setVisibility(View.VISIBLE);
                    emptyTextView.setVisibility(View.INVISIBLE);
                    PostAdapter recycler = new PostAdapter(posts);
                    RecyclerView.LayoutManager layoutmanager = new LinearLayoutManager(ToLetActivity.this);
                    recyclerView.setLayoutManager(layoutmanager);
                    recyclerView.setItemAnimator( new DefaultItemAnimator());
                    recyclerView.setAdapter(recycler);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(ToLetActivity.this, "Failed to retrive data", Toast.LENGTH_SHORT).show();
            }
        });
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
