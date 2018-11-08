package com.contractoback.service;

import com.contractoback.entity.DictionaryTerm;
import com.contractoback.persistence.mongo.MongoDictionaryDao;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
public class DictionaryService {
    private static Logger LOG = Logger.getLogger(DictionaryService.class.getName());
    Map<String, DictionaryTerm> dictionary = new HashMap<>();
    MongoDictionaryDao dao;

    public DictionaryService() throws IOException {
        dao = new MongoDictionaryDao();
        List<DictionaryTerm> fromFile = readDictionaryFile();
        Collection<DictionaryTerm> fromMongo = dao.getAllTerms();

        fromFile.addAll(fromMongo);
        for (DictionaryTerm t : fromFile) {
            dictionary.put(t.getName(), t);
        }
    }

    public DictionaryService(Map<String, DictionaryTerm> dictionary) {
        this.dictionary = dictionary;
    }

    public List<DictionaryTerm> readDictionaryFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<DictionaryTerm> example = objectMapper.readValue(
                new File("src/main/resources/dictionary.json"),
                new TypeReference<List<DictionaryTerm>>() {
                });
        return example;
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
    public String createTerm(@RequestBody DictionaryTerm term) {
        if(term.getId() == null){
            term.setId(ObjectId.get());
        }
        LOG.info("creating:" + term.toString());
        dao.saveTerm(term);
        dictionary.put(term.getName(), term);
        return "ok";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/allterms")
    public DictionaryTerm[] getAllTerms() {
        return dictionary.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList()).toArray(new DictionaryTerm[0]);
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
