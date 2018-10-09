package com.contractoback.service;

import com.contractoback.entity.DictionaryTerm;
import com.fasterxml.jackson.core.type.TypeReference;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TemplatingTest {

    private Configuration cfg;

    @Before
    public void setUp() throws Exception {
        cfg = new Configuration(Configuration.VERSION_2_3_27);
        cfg.setDirectoryForTemplateLoading(new File("src/main/resources/"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
    }

    @Test
    public void testXMLTemplate() throws IOException, TemplateException {
        DictionaryService service = new DictionaryService();
        service.readDictionaryFile();
        Optional<DictionaryTerm> t = service.getTerm("settlement period");
        Map<String, Object> root = new HashMap<>();

        root.put("settle", t.get());
        Template temp = cfg.getTemplate("settlement.ftl");
        Writer out = new OutputStreamWriter(System.out);
        temp.process(root,out);
       String s = out.toString();
    }
}
