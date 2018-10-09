package com.contractoback.entity;

import java.util.List;

public class DocumentFragment {
    private String title;
    private List<DocumentParagraph> paragraphs;

    public DocumentFragment() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DocumentParagraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<DocumentParagraph> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
