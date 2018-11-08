package com.contractoback.persistence.mongo;

import com.contractoback.entity.DictionaryTerm;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.assertFalse;

public class MongoDictionaryDaoTest {

    private MongoDictionaryDao dao;

    @Before
    public void setUp() throws Exception {
        dao = new MongoDictionaryDao();
    }

    @Test
    public void testGetAll() throws IOException {
        Collection<DictionaryTerm> r = dao.getAllTerms();
        assertFalse(r.isEmpty());
    }
}