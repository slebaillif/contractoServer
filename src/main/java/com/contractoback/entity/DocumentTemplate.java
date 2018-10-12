package com.contractoback.entity;

import java.util.List;

public class DocumentTemplate {
    private String name;
    private List<String> fragmentNames;

    public DocumentTemplate() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFragmentNames() {
        return fragmentNames;
    }

    public void setFragmentNames(List<String> fragmentNames) {
        this.fragmentNames = fragmentNames;
    }
}
