package com.example.covidtracker;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class HelpPermission extends AppCompatActivity {

    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_permission);
        setTitle("Why do I need to enable my device location?");

        textViewResult = findViewById(R.id.textViewPermission);

        SpannableString string = new SpannableString(
                "We need to use your location to accurately show you where you are in relation to affected areas on the map. We do not use this information for anything else."
        );

        string.setSpan(new BulletSpan(40, 0), 17, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textViewResult.setText(string);


    }

}