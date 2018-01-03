package com.bbtree.librec.functional.testing;


import net.librec.conf.Configuration;
import net.librec.data.model.TextDataModel;
import net.librec.eval.RecommenderEvaluator;
import net.librec.eval.rating.RMSEEvaluator;
import net.librec.filter.GenericRecommendedFilter;
import net.librec.job.RecommenderJob;
import net.librec.recommender.Recommender;
import net.librec.recommender.RecommenderContext;
import net.librec.recommender.cf.ItemKNNRecommender;
import net.librec.recommender.cf.UserKNNRecommender;
import net.librec.recommender.cf.ranking.RankALSRecommender;
import net.librec.recommender.cf.rating.ASVDPlusPlusRecommender;
import net.librec.recommender.cf.rating.SVDPlusPlusRecommender;
import net.librec.recommender.content.EFMRecommender;
import net.librec.recommender.item.RecommendedItem;
import net.librec.similarity.CosineSimilarity;
import net.librec.similarity.JaccardSimilarity;
import net.librec.similarity.PCCSimilarity;
import net.librec.similarity.RecommenderSimilarity;

import java.util.ArrayList;
import java.util.List;
public class test_librecomment3 {
    public static void main(String[] args) throws Exception {

        // build data model
        Configuration conf = new Configuration();
        conf.set("dfs.data.dir", "/home/fei/桌面/textv2/data");
        TextDataModel dataModel = new TextDataModel(conf);
        dataModel.buildDataModel();

        // build recommender context
        RecommenderContext context = new RecommenderContext(conf, dataModel);

        // build similarity  皮尔逊相似度系数
        conf.set("rec.recommender.similarity.key" ,"item");
        RecommenderSimilarity similarity = new PCCSimilarity();
        similarity.buildSimilarityMatrix(dataModel);
        context.setSimilarity(similarity);

        // build recommender
        conf.set("rec.iterator.maximum", "100");
        conf.set("rec.iterator.learningrate","0.01");
        conf.set("rec.iterator.learningrate.maximum","1000");
        conf.set("rec.user.regularization","0.01");
        conf.set("rec.item.regularization","0.01");
        conf.set("rec.factor.number","10");
        conf.set("rec.learningrate.bolddriver","false");
        conf.set("rec.learningrate.decay","1.0");
        conf.set("rec.recommender.isranking","true");
        conf.set("rec.recommender.ranking.topn","10");
        Recommender recommender = new SVDPlusPlusRecommender();
        recommender.setContext(context);

        // run recommender algorithm
        recommender.recommend(context);

        // evaluate the recommended result
        RecommenderEvaluator evaluator = new RMSEEvaluator();
       // System.out.println("RMSE:" + recommender.evaluate(evaluator));

        // set id list of filter
        List<String> userIdList = new ArrayList<>();
        List<String> itemIdList = new ArrayList<>();
        userIdList.add("6"); userIdList.add("7");userIdList.add("8");userIdList.add("9");
        //itemIdList.add("205");

        // filter the recommended result
        List<RecommendedItem> recommendedItemList = recommender.getRecommendedList();
        GenericRecommendedFilter filter = new GenericRecommendedFilter();
        filter.setUserIdList(userIdList);
        filter.setItemIdList(itemIdList);
        recommendedItemList = filter.filter(recommendedItemList);

        // print filter result
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(
                    "user:" + recommendedItem.getUserId() + " " +
                            "item:" + recommendedItem.getItemId() + " " +
                            "value:" + recommendedItem.getValue()
            );
        }
    }
}
