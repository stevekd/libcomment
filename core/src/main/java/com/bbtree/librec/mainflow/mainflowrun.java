package com.bbtree.librec.mainflow;
import com.bbtree.librec.conf.Configuration;
import com.bbtree.librec.data.model.TextDataModel;
import com.bbtree.librec.eval.RecommenderEvaluator;
import com.bbtree.librec.eval.rating.RMSEEvaluator;
import com.bbtree.librec.filter.GenericRecommendedFilter;
import com.bbtree.librec.recommender.Recommender;
import com.bbtree.librec.recommender.RecommenderContext;
import com.bbtree.librec.recommender.cf.ItemKNNRecommender;
import com.bbtree.librec.recommender.item.RecommendedItem;
import com.bbtree.librec.similarity.PCCSimilarity;
import com.bbtree.librec.similarity.RecommenderSimilarity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 何广 on 2017/12/14.
 */
public class mainflowrun {
    public static void main(String[] args) throws Exception {

        // build data model
        Configuration conf = new Configuration();
        conf.set("dfs.data.dir", "G:/LibRec/data");
        TextDataModel dataModel = new TextDataModel(conf);
        dataModel.buildDataModel();

        // build recommender context
        RecommenderContext context = new RecommenderContext(conf, dataModel);

        // build similarity
        conf.set("rec.recommender.similarity.key" ,"item");
        RecommenderSimilarity similarity = new PCCSimilarity();
        similarity.buildSimilarityMatrix(dataModel);
        context.setSimilarity(similarity);

        // build recommender
        conf.set("rec.neighbors.knn.number", "5");
        Recommender recommender = new ItemKNNRecommender();
        recommender.setContext(context);

        // run recommender algorithm
        recommender.recommend(context);

        // evaluate the recommended result
        RecommenderEvaluator evaluator = new RMSEEvaluator();
        System.out.println("RMSE:" + recommender.evaluate(evaluator));

        // set id list of filter
        List<String> userIdList = new ArrayList<>();
        List<String> itemIdList = new ArrayList<>();
        userIdList.add("1");
        itemIdList.add("70");

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
