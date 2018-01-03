package com.bbtree.librec.recommender.cf.ranking;

import com.bbtree.librec.BaseTestCase;
import com.bbtree.librec.common.LibrecException;
import com.bbtree.librec.conf.Configuration;
import com.bbtree.librec.job.RecommenderJob;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * ListRankMF Test Case corresponds to ListRankMFRecommender
 * {@link com.bbtree.librec.recommender.cf.ranking.ListRankMFRecommender}
 *
 * @author Jinyuanyuan
 */
public class ListRankMFTestCase extends BaseTestCase{

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * test the whole process of ListwiseFM recommendation
     *
     * @throws ClassNotFoundException
     * @throws LibrecException
     * @throws IOException
     */
    @Test
    public void testRecommender() throws ClassNotFoundException, LibrecException, IOException {
        Configuration.Resource resource = new Configuration.Resource("rec/cf/ranking/listrankmf-test.properties");
        conf.addResource(resource);
        RecommenderJob job = new RecommenderJob(conf);
        job.runJob();
    }
}
