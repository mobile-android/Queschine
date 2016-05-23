package com.iweavesolutions.queschine.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iweavesolutions.queschine.R;
import com.iweavesolutions.queschine.customviews.CustomButton;
import com.iweavesolutions.queschine.customviews.CustomTextView;
import com.iweavesolutions.queschine.utilities.PreferenceManager;

/**
 * Created by bharath.simha on 24/05/16.
 */
public class ContributorActivity extends AppCompatActivity {

    private CustomButton contributor;
    private CustomTextView skip, heading;
    private ImageView profilePic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_contributor);
        onInit();
    }

    private void onInit() {
        contributor = (CustomButton) findViewById(R.id.contributor);
        skip = (CustomTextView) findViewById(R.id.contributorSkip);
        profilePic = (ImageView) findViewById(R.id.contributorPic);
        heading = (CustomTextView) findViewById(R.id.heading);

        if (PreferenceManager.getManagerInstance().getUserName() != null)
            heading.setText("Welcome " + PreferenceManager.getManagerInstance().getUserName());

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });

        contributor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Coming Soon!!", Toast.LENGTH_SHORT).show();
            }
        });

        if (PreferenceManager.getManagerInstance().getProfilePic() != null) {
            Glide.with(getApplicationContext()).load(PreferenceManager.getManagerInstance().getProfilePic())
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(profilePic);
        }
    }
}
