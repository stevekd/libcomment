package com.bbtree.librec.functional.testing;


import net.librec.conf.Configuration;
import net.librec.data.model.ArffDataModel;
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
import net.librec.recommender.content.EFMRecommender;
import net.librec.recommender.content.HFTRecommender;
import net.librec.recommender.item.RecommendedItem;
import net.librec.similarity.PCCSimilarity;
import net.librec.similarity.RecommenderSimilarity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class test_contextEFM {

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


        Configuration conf = new Configuration();

        //1 load data
        conf.set("dfs.data.dir","/home/fei/桌面/textv2/data");
        conf.set("data.input.path","test/efmtest/dc_dense.arff");
        conf.set("data.convertor.format","arff");
        conf.set("dfs.result.dir","../result1");
        conf.set("data.model.format","arff");

        //2 train set 和test set合划分比例
        conf.set("data.splitter.trainset.ratio","0.9");
        conf.set("rec.recommender.class","efm");

        //3 setting parameters
        conf.set("rec.iterator.maximum","20");
        conf.set("rec.factor.number","10");
        conf.set("rec.regularization.lambdax","0.0001");
        conf.set("rec.regularization.lambday","0.0001");
        conf.set("rec.regularization.lambdau","0.0001");
        conf.set("rec.regularization.lambdah","0.0001");
        conf.set("rec.regularization.lambdav","0.0001");

        conf.set("data.output.path","/home/fei/桌面/textv2/data");
        //conf.set("rec.recommender.similarity.key" ,"user");

        //4 build model
        RecommenderJob job = new RecommenderJob(conf);
        ArffDataModel dataModel = new ArffDataModel(conf);
        dataModel.buildDataModel();

        //5 build recommend
        RecommenderContext context = new RecommenderContext(conf, dataModel);
        Recommender recommender = new EFMRecommender();
        recommender.setContext(context);
        /////////// run recommender algorithm
        recommender.recommend(context);

        //6 get result
        List<RecommendedItem> recommendedList = recommender.getRecommendedList();

        ////////////////////////////////////////////////////////////////////
        /*executeRecommenderJob 这个函数是最主要的执行函数*/
        job.saveResult(recommendedList);
        // List<RecommendedItem> recommendedItemList = recommender.getRecommendedList();



//
//        //7 set id list of filter
//        List<String> userIdList = new ArrayList<>();
//        List<String> itemIdList = new ArrayList<>();
//        userIdList.add("100086470");
//        //userIdList = {"1", "2"}
//        // for (int i=1; i<=2; i++) {
//        //  userIdList.add(Integer.toString(i));
//        //  itemIdList.add(Integer.toString(4-i));
//        // }
//        //itemIdList.add("70");
//
//        // filter the recommended result
//        List<RecommendedItem> recommendedItemList = recommender.getRecommendedList();
//        GenericRecommendedFilter filter = new GenericRecommendedFilter();
//        filter.setUserIdList(userIdList);
//        filter.setItemIdList(itemIdList);
//        //recommendedItemList = filter.filter(recommendedItemList);
//
        //8 print filter result
        for (RecommendedItem recommendedItem : recommendedList) {
            System.out.println(
                    "user:" + recommendedItem.getUserId() + " " +
                            "item:" + recommendedItem.getItemId() + " " +
                            "value:" + recommendedItem.getValue()
            );
        }
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
