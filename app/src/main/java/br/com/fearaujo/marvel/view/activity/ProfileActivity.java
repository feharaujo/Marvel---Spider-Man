package br.com.fearaujo.marvel.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.com.fearaujo.marvel.R;

@EActivity(R.layout.activity_profile)
public class ProfileActivity extends AppCompatActivity {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;

    @AfterViews
    void afterViews(){
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the toolbar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Click(R.id.ivFacebook)
    void facebook(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/felipetrova"));
        startActivity(browserIntent);
    }

    @Click(R.id.ivGithub)
    void github(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/feharaujo"));
        startActivity(browserIntent);
    }

    @Click(R.id.ivLinkedin)
    void linkedin(){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://br.linkedin.com/in/felipe-trova-de-araujo-85803732\n"));
        startActivity(browserIntent);
    }

}
