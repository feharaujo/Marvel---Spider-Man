package br.com.fearaujo.marvel.service.impl;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.androidannotations.annotations.EBean;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import br.com.fearaujo.marvel.R;
import br.com.fearaujo.marvel.service.IImageLoader;

@EBean
public class ImageLoaderImpl implements IImageLoader{

    private DisplayImageOptions options;
    private ImageLoadingListener animateFirstListener;

    @Override
    public void loadImage(String uri, ImageView imageView) {
        animateFirstListener = new AnimateFirstDisplayListener();

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.img_marvel_logo)
                .showImageOnFail(R.drawable.ic_error_outline_black_48dp)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoader.getInstance().displayImage(uri, imageView, options, animateFirstListener);
    }

    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {

        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());

        @Override
        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            if (loadedImage != null) {
                ImageView imageView = (ImageView) view;
                boolean firstDisplay = !displayedImages.contains(imageUri);
                if (firstDisplay) {
                    FadeInBitmapDisplayer.animate(imageView, 500);
                    displayedImages.add(imageUri);
                }
            }
        }
    }
}
