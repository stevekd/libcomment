/**
 * Copyright (C) 2016 LibRec
 *
 * This file is part of LibRec.
 * LibRec is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * LibRec is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with LibRec. If not, see <http://www.gnu.org/licenses/>.
 */
package com.bbtree.librec.recommender.cf.ranking;

import com.bbtree.librec.BaseTestCase;
import com.bbtree.librec.common.LibrecException;
import com.bbtree.librec.conf.Configuration;
import com.bbtree.librec.job.RecommenderJob;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * RankSGD Test Case corresponds to RankSGDRecommender
 * {@link com.bbtree.librec.recommender.cf.ranking.RankSGDRecommender}
 *
 * @author SunYatong
 */
public class RankSGDTestCase extends BaseTestCase {
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * test the whole process of RankSGD recommendation
     *
     * @throws ClassNotFoundException
     * @throws LibrecException
     * @throws IOException
     */
    @Test
    public void testRecommender() throws ClassNotFoundException, LibrecException, IOException {
        Configuration.Resource resource = new Configuration.Resource("rec/cf/ranking/ranksgd-test.properties");
        conf.addResource(resource);
        RecommenderJob job = new RecommenderJob(conf);
        job.runJob();
    }
}
