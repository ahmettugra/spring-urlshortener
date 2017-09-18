package web.app.controller;

import data.sql.entities.Url;
import data.sql.repositories.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import web.app.model.UrlModel;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;


@Controller
public class HomeController extends WebMvcConfigurerAdapter {

    @Autowired
    private UrlRepository urlRepository;

    public HomeController() {

    }

    @RequestMapping(value="/form", method=RequestMethod.GET)
    public String urlForm( Model model ,Url url) {

        if(!model.containsAttribute("url")){
            model.addAttribute("url", new Url());
        }

        return "form";
    }


    @RequestMapping(value="/form", method=RequestMethod.POST)
    public String urlSubmit(@Valid@ModelAttribute UrlModel urlModel, Model model) {

        Url url = urlRepository.findByLongUrl(urlModel.getLongUrl());
        if(url == null){

            url = new Url();
            url.setLongUrl(urlModel.getLongUrl());
            url.setCreated(new Date());
            url.setShortCode(urlModel.getShortCode());

            Url urlExist = null;
            int random = 10;
            do {
                urlExist = urlRepository.findByShortCode(url.getShortCode());
                if(urlExist != null) {
                    random = random + 1;
                    url.setShortCode(String.valueOf(urlModel.getShortCode() + random));
                }
            }while(urlExist != null);

            urlRepository.save(url);
        }

        model.addAttribute("url", url);
        model.addAttribute("shortUrl",url.getShortCode());
        model.addAttribute("LongUrl",url.getLongUrl());

        return "result";
    }

    @RequestMapping(value = "/{shortCode}", method = RequestMethod.GET)
    public ResponseEntity checkShortenURL(@PathVariable(value = "shortCode") String shortCode,
                                          HttpServletRequest request) {
        Url url = urlRepository.findByShortCode(shortCode);
        if (url == null) return new ResponseEntity(HttpStatus.NOT_FOUND);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", url.getLongUrl());
        return new ResponseEntity<String>(headers, HttpStatus.FOUND);
    }

}