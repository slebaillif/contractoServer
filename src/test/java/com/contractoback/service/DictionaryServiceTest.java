package com.contractoback.service;

import com.contractoback.entity.DictionaryTerm;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.*;

public class DictionaryServiceTest {
    private DictionaryService service;

    @Before
    public void setUp() throws Exception {
        service = new DictionaryService();
    }

    @Test
    public void testReadFile() throws IOException {
        service.readDictionaryFile();

        Optional<DictionaryTerm> t = service.getTerm("settlement period");
        assertTrue(t.isPresent());
        assertEquals(t.get().getName(), "settlement period");
        assertEquals(t.get().getDescription(), "The number of days allowed for settlement from the event. T marks the day of the even.");
    }
}