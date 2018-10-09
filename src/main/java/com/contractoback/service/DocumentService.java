package com.contractoback.service;

import com.contractoback.entity.DictionaryTerm;
import com.contractoback.entity.DocumentFragment;
import com.contractoback.entity.DocumentParagraph;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class DocumentService {
    public static final String REGEX_FIND_TERMS = "(\\[.*?\\])";
    private static Logger LOG = Logger.getLogger(DocumentService.class.getName());

    @Autowired
    private DictionaryService dictionaryService;

    public DocumentService() {
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping("/fragment")
    public DocumentFragment getFragment(@RequestParam String name) throws IOException {
        LOG.info("Requesting fragment:" + name);
        ObjectMapper objectMapper = new ObjectMapper();
        DocumentFragment fragment = objectMapper.readValue(
                new File("src/main/resources/" + name + ".json"),
                DocumentFragment.class);

        for (DocumentParagraph p : fragment.getParagraphs()) {
            List<DictionaryTerm> terms = new ArrayList<>();
            for (String sentence : p.getSentences()) {
                terms.addAll(dictionaryService.getTerms(getTermNames(sentence)));
            }
            p.setTerms(terms);
        }
        return fragment;
    }

    public List<String> getTermNames(String text) {
        Pattern pattern = Pattern.compile(REGEX_FIND_TERMS);
        Matcher matcher = pattern.matcher(text);
        List<String> result = new ArrayList<>();
        while (matcher.find()) {
            result.add(matcher.group().replace("[", "").replace("]", ""));
        }
        return result;
    }

    public void setDictionaryService(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }
}
