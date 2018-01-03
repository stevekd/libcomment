package com.bbtree.librec.functional.testing;


import net.librec.conf.Configuration;
import net.librec.conf.Configured;
import net.librec.data.DataConvertor;
import net.librec.data.convertor.ArffDataConvertor;
import net.librec.data.convertor.TextDataConvertor;
import net.librec.data.model.ArffInstance;
import net.librec.data.model.TextDataModel;
import net.librec.data.splitter.KCVDataSplitter;
import net.librec.data.splitter.LOOCVDataSplitter;
import net.librec.data.splitter.RatioDataSplitter;
import net.librec.eval.RecommenderEvaluator;
import net.librec.eval.ranking.AUCEvaluator;
import net.librec.eval.rating.RMSEEvaluator;
import net.librec.filter.GenericRecommendedFilter;
import net.librec.job.RecommenderJob;
import net.librec.math.structure.SparseMatrix;
import net.librec.math.structure.SparseTensor;
import net.librec.recommender.Recommender;
import net.librec.recommender.RecommenderContext;
import net.librec.recommender.cf.ItemKNNRecommender;
import net.librec.recommender.item.RecommendedItem;
import net.librec.similarity.PCCSimilarity;
import net.librec.similarity.RecommenderSimilarity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class test_librecomment_set2 {
    /*use conf.set() for set dataset model evaluate and so on*/

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

        // build data model
        Configuration conf = new Configuration();
        conf.set("dfs.data.dir", "/home/fei/桌面/textv2");
        conf.set("data.model.format","arff");
        conf.set("inputDataPath", conf.get("dfs.data.dir") + "/data/test/efmtest/dc_dense.arff");

        //比例划分
        conf.set("data.splitter.ratio", "rating");
        conf.set("data.splitter.trainset.ratio", "0.8");


        ArffDataConvertor arffLoder = new ArffDataConvertor(conf.get("inputDataPath"));
        arffLoder.readData();
        SparseTensor sparseTensor = arffLoder.getSparseTensor();
        ArrayList<ArffInstance> instances = arffLoder.getInstances();
        String s1 = arffLoder.getRelationName();
        String s2 = arffLoder.getAttributes().get(0).getName();
        System.out.println(s1);
        System.out.println(s2);

//        TextDataModel dataModel = new TextDataModel(conf);
//        dataModel.buildDataModel();
//
//
//        // build recommender context
//        RecommenderContext context = new RecommenderContext(conf, dataModel);
//
//
//        // build similarity
//        conf.set("rec.recommender.similarity.key" ,"item");
//        RecommenderSimilarity similarity = new PCCSimilarity();
//        similarity.buildSimilarityMatrix(dataModel);
//        context.setSimilarity(similarity);
//
//        // build recommender
//        conf.set("rec.neighbors.knn.number", "5");
//        //
//        conf.set("rec.eval.enable", "true");
//
//        conf.set("rec.eval.class", "rmse");
//        //
//
//
//
//        conf.set("rec.recommender.ranking.topn", "10");
//        Recommender recommender = new ItemKNNRecommender();
//
//        recommender.setContext(context);
//
//        // run recommender algorithm
//        recommender.recommend(context);
//
//
//        // evaluate the recommended result
//        RecommenderEvaluator evaluator = new RMSEEvaluator();
//        System.out.println("RMSE:" + recommender.evaluate(evaluator));
//
//        // set id list of filter
//
//        List<String> userIdList = new ArrayList<>();
//        List<String> itemIdList = new ArrayList<>();
//        userIdList.add("1");
//       itemIdList.add("70");
//
//        // filter the recommended result 过滤器返回你需要的userid 或者itemid
//        List<RecommendedItem> recommendedItemList = recommender.getRecommendedList();
//        GenericRecommendedFilter filter = new GenericRecommendedFilter();
//        filter.setUserIdList(userIdList);
//        filter.setItemIdList(itemIdList);
//        recommendedItemList = filter.filter(recommendedItemList);
//
//        // print filter result
//        for (RecommendedItem recommendedItem : recommendedItemList) {
//            System.out.println(
//                    "user:" + recommendedItem.getUserId() + " " +
//                            "item:" + recommendedItem.getItemId() + " " +
//                            "value:" + recommendedItem.getValue()
//            );
//        }
//
//        //输出 保存文件
//        int l=recommendedItemList.size();
//        double x[][]=new double[l][3];
//        int temp=0;
//        for (RecommendedItem recommendedItem : recommendedItemList) {
//            x[temp][0]=Double.valueOf(recommendedItem.getUserId());
//            x[temp][1]=Double.valueOf(recommendedItem.getItemId());
//            x[temp][2]=recommendedItem.getValue();
//            temp++;
//        }
//        System.out.println("X");
//        System.out.println(recommendedItemList.size());
//        System.out.println("user:" + x[0][0] + " " + "item:" + x[0][1] + " " + "value:" + x[0][2]);
//        String path="/home/fei/桌面/textv2/data/result"+l+".txt";
//        matrix2txt(x,l,3,path); // 结果保存文件
    }
}
