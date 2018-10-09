package com.contractoback.entity;

import java.util.List;

public class DocumentParagraph {
    private String paragraph;
    private List<String> sentences;
    private List<DictionaryTerm> terms;

    public DocumentParagraph() {
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    public List<DictionaryTerm> getTerms() {
        return terms;
    }

    public void setTerms(List<DictionaryTerm> terms) {
        this.terms = terms;
    }
}
