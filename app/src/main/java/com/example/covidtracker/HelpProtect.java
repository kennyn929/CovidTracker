package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HelpProtect extends AppCompatActivity {

    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_protect);
        setTitle("How do I protect myself?");

        textViewResult = findViewById(R.id.textView2);

        SpannableString string = new SpannableString(
                "What can you do?\nStay at least 6 feet away from others\nWear a mask\nWash your hands frequently\nStay at home"
        );

        string.setSpan(new BulletSpan(40, 0), 17, string.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textViewResult.setText(string);

        String link = "For more information, click here.";
        TextView hyperlink = findViewById(R.id.textViewHyperlink);
        SpannableString lnk = new SpannableString(link);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                //Toast.makeText(HelpProtect.this, "Test", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cdc.gov/coronavirus/2019-ncov/prevent-getting-sick/prevention.html"));
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.BLUE);
            }
        };

        lnk.setSpan(clickableSpan, 22, 32, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        hyperlink.setText(lnk);
        hyperlink.setMovementMethod(LinkMovementMethod.getInstance());

    }

}