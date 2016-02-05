package br.com.fearaujo.marvel.service;

import org.androidannotations.annotations.rest.Accept;
import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.MediaType;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import br.com.fearaujo.marvel.model.Response;
import br.com.fearaujo.marvel.util.Constants;

@Rest(rootUrl = Constants.URL, converters = {GsonHttpMessageConverter.class})
public interface ICommunicationService {

    @Get("")
    @Accept(MediaType.APPLICATION_JSON)
    Response getComics();


}
