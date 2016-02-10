package br.com.fearaujo.marvel.controller;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;

import br.com.fearaujo.marvel.model.Response;
import br.com.fearaujo.marvel.service.ICommunicationService;
import br.com.fearaujo.marvel.util.Constants;
import br.com.fearaujo.marvel.view.activity.MainActivity;

@EBean
public class ComicsController {

    @RestService
    ICommunicationService communicationService;

    @RootContext
    MainActivity context;

    private final int RESULT_OK = 200;

    @Background
    public void searchComics(){
        try {
            Response response = communicationService.getComics(Constants.API_KEY, Constants.HASH_KEY);
            searchComicsUi(true, response);
        } catch (Exception e){
            searchComicsUi(false, null);
        }
    }

    @UiThread
    void searchComicsUi(boolean success, Response response){
        if(success && response != null && response.getCode() == RESULT_OK){
            context.successSearch(response);
        } else {
            context.failSearch();
        }
    }


}
