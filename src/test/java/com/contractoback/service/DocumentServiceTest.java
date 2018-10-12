package com.contractoback.service;

import com.contractoback.entity.DocumentFragment;
import com.contractoback.entity.DocumentTemplate;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class DocumentServiceTest {

    private DocumentService service;

    @Before
    public void setUp() throws Exception {
        service = new DocumentService();
        DictionaryService dictionaryService = new DictionaryService();
        service.setDictionaryService(dictionaryService);
    }

    @Test(expected = IOException.class)
    public void testGetFragmentDoesNotExist() throws IOException {
        service.getFragment("does not exists");
    }

    @Test
    public void testGetFragment() throws IOException {
        DocumentFragment fragment = service.getFragment("settlement_paragraph");
        assertEquals("Settlement", fragment.getTitle());
        assertEquals(1, fragment.getParagraphs().get(0).getTerms().size());
    }

    @Test
    public void testRegex() {
        assertEquals(asList("bbb"), service.getTermNames("aaa [bbb] ccc"));
        assertEquals(asList("bbb", "ddd"), service.getTermNames("aaa [bbb] ccc [ddd]"));
    }

    @Test
    public void testLoadTemplate() throws IOException {
        DocumentTemplate result = service.getTemplate("contract");
        assertEquals("contract", result.getName());
        assertEquals(3, result.getFragmentNames().size());
    }
}