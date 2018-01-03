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
import net.librec.recommender.cf.rating.ASVDPlusPlusRecommender;
import net.librec.recommender.cf.rating.SVDPlusPlusRecommender;
import net.librec.recommender.item.RecommendedItem;
import net.librec.similarity.PCCSimilarity;
import net.librec.similarity.RecommenderSimilarity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
public class test_cSVD_txt {

    public static void matrix2txt(double[][]x,int m,int n,String filepath) throws IOException {

        File file=new File(filepath);//存txt的路径

        FileWriter out=new FileWriter(file);
        for(int i=0;i<m;i++)
        {
            for(int j=0;j<n;j++)
            {
                out.write(x[i][j]+"\t");
            }
            out.write("\r\n");
        }
        out.close();
    }

    public static void main(String[] args) throws Exception {
        // build data model(SVD CF算法 是全量的计算 切勿弄混了)
        Configuration conf = new Configuration();
        conf.set("dfs.data.dir", "/home/fei/桌面/test");
        conf.set("data.input.path", "/UIR20180102.txt");

        conf.set("data.splitter.trainset.ratio","0.7");
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
        //System.out.println("RMSE:" + recommender.evaluate(evaluator));

        // set id list of filter
        List<String> userIdList = new ArrayList<>();
        List<String> itemIdList = new ArrayList<>();
        userIdList.add("100086470");
        //userIdList = {"1", "2"}
        // for (int i=1; i<=2; i++) {
        //  userIdList.add(Integer.toString(i));
        //  itemIdList.add(Integer.toString(4-i));
        // }
        //itemIdList.add("70");

        // filter the recommended result
        List<RecommendedItem> recommendedItemList = recommender.getRecommendedList();
        GenericRecommendedFilter filter = new GenericRecommendedFilter();
        filter.setUserIdList(userIdList);
        filter.setItemIdList(itemIdList);
        //recommendedItemList = filter.filter(recommendedItemList);

        // print filter result
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(
                    "user:" + recommendedItem.getUserId() + " " +
                            "item:" + recommendedItem.getItemId() + " " +
                            "value:" + recommendedItem.getValue()
            );
        }
        //输出 保存文件
        int l=recommendedItemList.size();
        double x[][]=new double[l][3];
        int temp=0;
        for (RecommendedItem recommendedItem : recommendedItemList) {
            x[temp][0]=Integer.valueOf(recommendedItem.getUserId());
            x[temp][1]=Integer.valueOf(recommendedItem.getItemId());
            x[temp][2]=recommendedItem.getValue();
            temp++;
        }
        System.out.println("X");
        System.out.println(recommendedItemList.size());
        System.out.println("user:" + x[0][0] + " " + "item:" + x[0][1] + " " + "value:" + x[0][2]);
        String path="/home/fei/桌面/textv2/data/result"+l+".txt";
        matrix2txt(x,l,3,path); // 结果保存文件
    }
}
