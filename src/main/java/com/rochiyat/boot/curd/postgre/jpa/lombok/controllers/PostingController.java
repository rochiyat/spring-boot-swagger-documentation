package com.rochiyat.boot.curd.postgre.jpa.lombok.controllers;

import com.rochiyat.boot.curd.postgre.jpa.lombok.models.Posting;
import com.rochiyat.boot.curd.postgre.jpa.lombok.repositories.PostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class PostingController {

    @Autowired
    PostingRepository postingRepository;

    @GetMapping("/postings")
    public ResponseEntity<List<Posting>> getAllPosting(@RequestParam(required = false) String tittle ) {
        try {
            List<Posting> postings = new ArrayList<Posting>();

            if(tittle == null) {
                postingRepository.findAll().forEach(postings::add);
            } else {
                postingRepository.findByTittleContaining(tittle).forEach(postings::add);
            }

            if(postings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(postings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/postings/{id}")
    public ResponseEntity<Posting> getPostingById(@PathVariable("id") long id) {
        Optional<Posting> postingData = postingRepository.findById(id);

        if(postingData.isPresent()) {
            return new ResponseEntity<>(postingData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/postings")
    public ResponseEntity<Posting> createPosting(@RequestBody Posting posting) {
        try {
            Posting createPosting = postingRepository.save(new Posting(posting.getTittle(), posting.getDescription(),false));
            return new ResponseEntity<>(createPosting, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/postings/{id}")
    public ResponseEntity<Posting> updatePosting(@PathVariable("id") long id, @RequestBody Posting posting) {
        Optional<Posting> postingData = postingRepository.findById(id);

        try {
            if (postingData.isPresent()) {
                Posting getPostingData = postingData.get();
                getPostingData.setTittle(posting.getTittle());
                getPostingData.setDescription(posting.getDescription());
                getPostingData.setPublished(posting.isPublished());
                return new ResponseEntity<>(postingRepository.save(getPostingData), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/postings/{id}")
    public ResponseEntity<HttpStatus> detelePosting (@PathVariable("id") long id) {
        try {
            postingRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/postings")
    public ResponseEntity<HttpStatus> deleteAllPosting () {
        try {
            postingRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("postings/published")
    public ResponseEntity<List<Posting>> findByPublished() {
        try {
            List<Posting> postings = postingRepository.findByPublished(true);

            if(postings.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(postings, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
