package br.com.fearaujo.marvel.view.activity;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import br.com.fearaujo.marvel.R;
import br.com.fearaujo.marvel.model.Result;
import br.com.fearaujo.marvel.service.IImageLoader;
import br.com.fearaujo.marvel.service.impl.ImageLoaderImpl;
import br.com.fearaujo.marvel.util.StatusBarHelper;

@EActivity(R.layout.activity_details)
public class DetailsActivity extends AppCompatActivity {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.ivComic)
    ImageView ivComic;
    @ViewById(R.id.tvTitle)
    TextView tvTitle;
    @ViewById(R.id.tvDescription)
    TextView tvDescription;
    @ViewById(R.id.tvPublished)
    TextView tvPublished;
    @ViewById(R.id.tvPrice)
    TextView tvPrice;
    @ViewById(R.id.tvPages)
    TextView tvPages;

    @Extra("result")
    Result result;

    @Bean(ImageLoaderImpl.class)
    @NonConfigurationInstance
    IImageLoader imageLoader;

    @AfterViews
    void afterViews() {
        StatusBarHelper.setStatusBarTranslucent(true, getWindow());
        toolbar.bringToFront();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        String uri = result.getThumbnail().getPath() + "/portrait_incredible." + result.getThumbnail().getExtension();
        imageLoader.loadImage(uri, ivComic);

        tvTitle.setText(result.getTitle());
        tvDescription.setText(Html.fromHtml(result.getDescription() != null ? result.getDescription() : ""));
        tvPages.setText(result.getPageCount().toString());

        for (Result.Dates date : result.getDates()){
            if (date.getType().equals("onsaleDate")){
                tvPublished.setText(createDateString(date.getDate()));
            }
        }

        for (Result.Prices price : result.getPrices()){
            if (price.getType().equals("printPrice")){
                tvPrice.setText("$ " + price.getPrice());
            }
        }

        ViewCompat.setTransitionName(ivComic, "linearComicRoot");
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

    @Click(R.id.ivComic)
    void loadImageFullScreen(View v){
        Intent intent = new Intent(DetailsActivity.this, ImageFullScreenActivity_.class);
        intent.putExtra("imageUri",
                result.getThumbnail().getPath() + "/portrait_uncanny." + result.getThumbnail().getExtension()
        );
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                Pair.create(v.findViewById(R.id.ivComic), "ivComic"));
        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

    private String createDateString(String dateString){
        StringBuilder dateFormated = new StringBuilder();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            return "";
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateFormated.append(calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));

        dateFormated.append(" ");
        dateFormated.append(calendar.get(Calendar.DAY_OF_MONTH));
        dateFormated.append(", ");
        dateFormated.append(calendar.get(Calendar.YEAR));

        return dateFormated.toString();
    }
}
