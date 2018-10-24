package com.contractoback.service;

import com.contractoback.entity.DictionaryTerm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@RestController
public class DictionaryService {
    private static Logger LOG = Logger.getLogger(DictionaryService.class.getName());
    Map<String, DictionaryTerm> dictionary = new HashMap<>();

    public DictionaryService() throws IOException {
        readDictionaryFile();
    }

    public DictionaryService(Map<String, DictionaryTerm> dictionary) {
        this.dictionary = dictionary;
    }

    public void readDictionaryFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<DictionaryTerm> example = objectMapper.readValue(
                new File("src/main/resources/dictionary.json"),
                new TypeReference<List<DictionaryTerm>>() {
                });
        for (DictionaryTerm t : example) {
            dictionary.put(t.getName(), t);
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/term")
    public Optional<DictionaryTerm> getTerm(@RequestParam String term) {
        LOG.info("Request for term:" + term);
        DictionaryTerm t = dictionary.get(term);
        if (t == null) {
            LOG.info("Not Found - Request for term:" + term);
            return Optional.empty();
        } else {
            LOG.info("Found - Request for term:" + term);
            return Optional.of(t);
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/addterm", method = RequestMethod.POST)
    public String createTerm(@RequestBody DictionaryTerm term){
        LOG.info("creating:"+term.toString());
        return "ok";
    }

    public List<DictionaryTerm> getTerms(List<String> termNames) {
        List<DictionaryTerm> terms = new ArrayList<>();
        for (String termName : termNames) {
            Optional<DictionaryTerm> t = getTerm(termName);
            if (t.isPresent()) {
                terms.add(t.get());
            }
        }
        return terms;
    }
}
