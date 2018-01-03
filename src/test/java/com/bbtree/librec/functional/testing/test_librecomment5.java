package com.bbtree.librec.functional.testing;




import java.io.IOException;
import java.util.ArrayList;

import static org.apache.avro.hadoop.io.AvroSerialization.getDataModelClass;
import static org.junit.Assert.assertEquals;

import net.librec.common.LibrecException;
import net.librec.conf.Configuration;
import net.librec.conf.Configured;
import net.librec.data.DataModel;
import net.librec.data.convertor.ArffDataConvertor;
import net.librec.data.model.ArffDataModel;
import net.librec.data.model.ArffInstance;
import net.librec.data.model.TextDataModel;
import net.librec.eval.RecommenderEvaluator;
import net.librec.eval.rating.RMSEEvaluator;
import net.librec.filter.GenericRecommendedFilter;
import net.librec.job.RecommenderJob;
import net.librec.math.structure.SparseTensor;
import net.librec.recommender.Recommender;
import net.librec.recommender.RecommenderContext;
import net.librec.recommender.cf.ItemKNNRecommender;
import net.librec.recommender.cf.UserKNNRecommender;
import net.librec.recommender.cf.rating.ASVDPlusPlusRecommender;
import net.librec.recommender.cf.rating.SVDPlusPlusRecommender;
import net.librec.recommender.content.EFMRecommender;
import net.librec.recommender.content.HFTRecommender;
import net.librec.recommender.context.rating.SocialMFRecommender;
import net.librec.recommender.item.RecommendedItem;
import net.librec.similarity.PCCSimilarity;
import net.librec.similarity.RecommenderSimilarity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import net.librec.common.LibrecException;
import net.librec.conf.Configuration.Resource;
import net.librec.job.RecommenderJob;
import net.librec.util.DriverClassUtil;
import net.librec.util.ReflectionUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;





public class test_librecomment5 {

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

    public void testReadDir() throws ClassNotFoundException, LibrecException, IOException {
        Configuration conf = new Configuration();
        conf.set("inputDataPath", conf.get("dfs.data.dir") + "/test/arfftest");

        ArffDataConvertor arffLoder = new ArffDataConvertor(conf.get("inputDataPath"));
        try {
            arffLoder.readData();
        } catch (IOException e) {
            assert "read data failed".equals("");
        }
        // test case for tensor
        SparseTensor sparseTensor = arffLoder.getSparseTensor();
        ArrayList<ArffInstance> instances = arffLoder.getInstances();
        String s1 = arffLoder.getRelationName();
        System.out.println(s1);
        String s2 = arffLoder.getAttributes().get(0).getName();
        System.out.println(s2);
        System.out.println(sparseTensor.toString());

        assertEquals(18, arffLoder.getInstances().size());
        assertEquals(5, arffLoder.getAttributes().size());
        assertEquals(18, sparseTensor.size());
        assertEquals(4, sparseTensor.numDimensions());
    }
    public void testReadFile() throws ClassNotFoundException, LibrecException {
        Configuration conf = new Configuration();
        conf.set("dfs.data.dir","/home/fei/桌面/textv2/data");
        conf.set("inputDataPath", conf.get("dfs.data.dir") + "/test/arfftest/ret.arff");
        ArffDataConvertor arffLoder = new ArffDataConvertor(conf.get("inputDataPath"));
        try {
            arffLoder.readData();
        } catch (IOException e) {
            assert "read data failed".equals("");
        }
        // test case for tensor
        SparseTensor sparseTensor = arffLoder.getSparseTensor();
        ArrayList<ArffInstance> instances = arffLoder.getInstances();
        String s1 = arffLoder.getRelationName();
        System.out.println(s1);
        String s2 = arffLoder.getAttributes().get(0).getName();
        System.out.println(s2);
        String s3 = arffLoder.getAttributes().get(1).getName();
        System.out.println(s3);
        String s4 = arffLoder.getAttributes().get(2).getName();
        System.out.println(s4);
        String s5 = arffLoder.getAttributes().get(3).getName();
        System.out.println(s5);
//        String s6 = arffLoder.getAttributes().get(4).getName();
//        System.out.println(s6);
//        String s7 = arffLoder.getAttributes().get(5).getName();
//        System.out.println(s7);
        System.out.println(sparseTensor.toString());

      assertEquals(15, arffLoder.getInstances().size());
        assertEquals(4, arffLoder.getAttributes().size());
        assertEquals(15, sparseTensor.size());
//        assertEquals(4, sparseTensor.numDimensions());
    }

    public static void main(String[] args) throws Exception
    {
        Configuration conf = new Configuration();
        //load data
        conf.set("dfs.data.dir","/home/fei/桌面/textv2/data");
        conf.set("data.input.path","test/hfttest/musical_instruments.arff");
        conf.set("data.convertor.format","arff");
        conf.set("dfs.result.dir","../result1");
        conf.set("data.model.format","arff");
        //训练集和测试集合划分比例
        conf.set("data.splitter.trainset.ratio","0.9");
        conf.set("rec.recommender.class","hft");



        conf.set(" rec.iterator.learnrate","0.01");
        conf.set("rec.iterator.learnrate.maximum","0.01");
        conf.set("rec.iterator.maximum","2");
        conf.set("rec.user.regularization","0.01");
        conf.set("rec.item.regularization","0.01");
        conf.set("rec.factor.number","10");
        conf.set("rec.learnrate.bolddriver","false");
        conf.set("rec.learnrate.decay","1.0");
        conf.set("rec.recommender.lambda.user","0.05");
        conf.set("rec.recommender.lambda.item","0.05");
        conf.set("rec.bias.regularization","0.01");
        conf.set("data.output.path","/home/fei/桌面/textv2/data");

        //conf.set("rec.recommender.similarity.key" ,"user");





        RecommenderJob job = new RecommenderJob(conf);
//        Recommender recommender = new HFTRecommender();
        //////////////////  自加模块//////////////////////////////////////////////////

//        ArffDataModel dataModel = new ArffDataModel(conf);
//        dataModel.buildDataModel();
//
//        RecommenderContext context = new RecommenderContext(conf, dataModel);
        Recommender recommender = new HFTRecommender();

//        recommender.setContext(context);
//
//        // run recommender algorithm
//        recommender.recommend(context);
//        List<RecommendedItem> recommendedList = recommender.getRecommendedList();

        ////////////////////////////////////////////////////////////////////

        job.runJob();
        /*executeRecommenderJob 这个函数是最主要的执行函数*/


//        conf.set("rec.recommender.isranking","true");
//        conf.set("rec.recommender.ranking.topn","10");
//        conf.set("dfs.data.dir", "/home/fei/桌面/textv2/data");
//        conf.set("data.convertor.format","text");
//        conf.set("data.model.format","text");
//        TextDataModel da1taModel = new TextDataModel(conf);
//        dataModel.buildDataModel();
//
//        // build recommender context
//        RecommenderContext context = new RecommenderContext(conf, dataModel);
//        recommender.setContext(context);
//        recommender.recommend(context);
//
//        job.saveResult(recommendedList);
//        // List<RecommendedItem> recommendedItemList = recommender.getRecommendedList();
//        System.out.println(recommendedList);
//        // print filter result
////        for (RecommendedItem recommendedItem : recommendedItemList) {
//            System.out.println(
//                    "user:" + recommendedItem.getUserId() + " " +
//                            "item:" + recommendedItem.getItemId() + " " +
//                            "value:" + recommendedItem.getValue()
//            );
//        }






//        ArffDataConvertor arffLoder = new ArffDataConvertor(conf.get("inputDataPath"));
//        ArffDataModel dataModel = new ArffDataModel(conf);
//        dataModel.buildDataModel();
//        RecommenderContext context = new RecommenderContext(conf, dataModel);
//
//        Recommender recommender = new SVDPlusPlusRecommender();
//        recommender.setContext(context);
//
//        // run recommender algorithm
//        recommender.recommend(context);






    }
}
