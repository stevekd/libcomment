package com.bbtree.librec.recommender.cf.rating;

import com.bbtree.librec.BaseTestCase;
import com.bbtree.librec.common.LibrecException;
import com.bbtree.librec.conf.Configuration;
import com.bbtree.librec.job.RecommenderJob;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * SVDPlusPlus Test Case correspond to SVDPlusPlusRecommender
 * {@link com.bbtree.librec.recommender.cf.rating.SVDPlusPlusRecommender}
 *
 * @author SunYatong
 */
public class SVDPlusPlusTestCase extends BaseTestCase {
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * test the whole process of SVDPlusPlus Recommendation
     *
     * @throws ClassNotFoundException
     * @throws LibrecException
     * @throws IOException
     */
    @Test
    public void testRecommender() throws ClassNotFoundException, LibrecException, IOException {
        Configuration.Resource resource = new Configuration.Resource("rec/cf/rating/svdpp-test.properties");
        conf.addResource(resource);
        RecommenderJob job = new RecommenderJob(conf);
        job.runJob();
    }
}
