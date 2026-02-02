package dev.hisamoto.zipurl.controller;

import dev.hisamoto.zipurl.controller.dto.ShortenUrlRequest;
import dev.hisamoto.zipurl.controller.dto.ShortenUrlResponse;
import dev.hisamoto.zipurl.entitites.UrlEntity;
import dev.hisamoto.zipurl.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@RestController
public class UrlController {
    private final UrlRepository urlRepository;

    public UrlController(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest servletRequest){

        UrlValidator validator = new UrlValidator(new String[]{"http", "https"});

        if (!validator.isValid(request.url())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        String id;

        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while(urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), Instant.now().plus(2, ChronoUnit.MINUTES)));

        var redirectUrl = servletRequest.getRequestURL().toString().replace("shorten-url", id);

        return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));

    }

    @GetMapping("/{id:[a-zA-Z0-9]{5,10}}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id){

        var url = urlRepository.findById(id);

        if (url.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getFullUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

}