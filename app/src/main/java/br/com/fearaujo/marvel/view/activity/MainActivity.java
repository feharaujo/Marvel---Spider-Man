package br.com.fearaujo.marvel.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import br.com.fearaujo.marvel.R;
import br.com.fearaujo.marvel.controller.ComicsController;
import br.com.fearaujo.marvel.model.Response;
import br.com.fearaujo.marvel.service.IImageLoader;
import br.com.fearaujo.marvel.service.impl.ImageLoaderImpl;
import br.com.fearaujo.marvel.util.OrientationHelper;
import br.com.fearaujo.marvel.view.adapter.ComicsAdapter;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {

    @ViewById(R.id.toolbar)
    Toolbar toolbar;
    @ViewById(R.id.rvComics)
    RecyclerView rvComics;
    @ViewById(R.id.progressBar)
    ProgressBar progressBar;
    @ViewById(R.id.llDisconnected)
    LinearLayout llDisconnected;

    @Bean
    @NonConfigurationInstance
    ComicsController comicsController;

    @Bean(ImageLoaderImpl.class)
    @NonConfigurationInstance
    IImageLoader imageLoader;

    @NonConfigurationInstance
    Response response;

    @AfterViews
    void afterViews(){
        setSupportActionBar(toolbar);

        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(this);
        builder.writeDebugLogs();
        ImageLoader.getInstance().init(builder.build());

        // Evita rechamar o web service quando troca a orientacao
        if(response == null) {
            searchComics();
            Log.v("LOG", "WEB SERVICE");
        } else {
            createAdapter(response);
            Log.v("LOG", "LOCAL");
        }
    }

    @Click(R.id.llDisconnected)
    void retrySearch(){
        searchComics();
    }

    private void searchComics(){
        rvComics.setVisibility(View.GONE);
        llDisconnected.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);

        comicsController.searchComics();
    }

    public void failSearch() {
        llDisconnected.setVisibility(View.VISIBLE);
        rvComics.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
    }

    public void successSearch(Response response){
        this.response = response;

        createAdapter(response);
    }

    private void createAdapter(Response response){
        int columns = 0;
        if(OrientationHelper.getOrientation(this) == OrientationHelper.PORT){
            columns = 3;
        } else {
            columns = 4;
        }

        rvComics.setHasFixedSize(true);
        rvComics.setLayoutManager(new GridLayoutManager(this, columns));
        rvComics.setAdapter(new ComicsAdapter(response.getData().getResults(), this, imageLoader));

        llDisconnected.setVisibility(View.GONE);
        rvComics.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }


}
