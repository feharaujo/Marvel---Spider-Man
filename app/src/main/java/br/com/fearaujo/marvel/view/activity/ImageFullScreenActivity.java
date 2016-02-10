package br.com.fearaujo.marvel.view.activity;

import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import br.com.fearaujo.marvel.R;
import br.com.fearaujo.marvel.service.IImageLoader;
import br.com.fearaujo.marvel.service.impl.ImageLoaderImpl;

@EActivity(R.layout.activity_image_full_screen)
public class ImageFullScreenActivity extends AppCompatActivity {

    @ViewById(R.id.ivComicFullScreen)
    ImageView ivComicFullScreen;

    @Bean(ImageLoaderImpl.class)
    @NonConfigurationInstance
    IImageLoader imageLoader;

    @Extra("imageUri")
    String imageUri;

    @AfterViews
    void afterViews(){
        imageLoader.loadImage(imageUri, ivComicFullScreen);
        ViewCompat.setTransitionName(ivComicFullScreen, "ivComic");
    }

    @Click(R.id.ivExit)
    void exit(){
        onBackPressed();
    }

}
