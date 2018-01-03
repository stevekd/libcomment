package com.bbtree.librec.functional.testing;


import net.librec.conf.Configuration;
import net.librec.conf.Configured;
import net.librec.data.DataConvertor;
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
public class test_librecomment_set {
    /*use conf.set() for set dataset model evaluate and so on*/

    public static void main2(String[] args) throws Exception {

        Configuration conf = new Configuration();

        conf.set(Configured.CONF_DATA_COLUMN_FORMAT, "UIR");
        conf.set("dfs.data.dir", "/home/fei/桌面/textv2");
        conf.set("inputDataPath", conf.get("dfs.data.dir") + "/data/test/datamodeltest/matrix4by4.txt");
//        conf.set("data.splitter.ratio", "rating");
//        conf.set("data.splitter.trainset.ratio", "0.8");
        TextDataConvertor textDataConvertor = new TextDataConvertor(conf.get("inputDataPath"));
        textDataConvertor.processData();

        SparseMatrix preference = textDataConvertor.getPreferenceMatrix();
        SparseMatrix datetimeMatrix = textDataConvertor.getDatetimeMatrix();


        System.out.println(preference);


        int[][] array ={{1,2,3,4,5},{1,2,3,4,5},{1,2,3,4,5}} ;
        double[][] ma2=new double[3][3];
        ma2[1][1]= preference.colData[1];
        System.out.println(array);
        System.out.println(ma2[1][1]);
        conf.set("data.splitter.ratio", "rating");
        conf.set("data.splitter.trainset.ratio", "0.8");
    }


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
        conf.set("dfs.data.dir", "/home/fei/桌面/22233");
        conf.set("dfs.result.dir", "/home/fei/桌面/22233");
        conf.set("data.input.path", "UIR.txt");

        //比例划分
//        conf.set("data.splitter.ratio", "rating");
//        conf.set("data.splitter.trainset.ratio", "0.8");

        //留一法
        conf.set("data.splitter.givenn", "user");
        conf.set("data.splitter.givenn.n", "100");
//
        //交叉验证
//        conf.set("data.model.splitter", "kcv");
//        conf.set("data.splitter.cv.number", "5");
//        conf.set("data.splitter.cv.index","1");

        //具体的数据读取过程与分割过程需要在调用buildDataModel之前通过setConf的方式传入对象中.
        /*如果要用LibRec中的算法,那么就直接通过conf来设定参数，然后生成相应的DataModel。
        （网站上面的convertor（）是用于其他的算法，拿出去切割数据用的，可以不考虑）*/

        TextDataModel dataModel = new TextDataModel(conf);
        dataModel.buildDataModel();


        // build recommender context
        RecommenderContext context = new RecommenderContext(conf, dataModel);


        // build similarity
        conf.set("rec.recommender.similarity.key" ,"user");
        RecommenderSimilarity similarity = new PCCSimilarity();
        similarity.buildSimilarityMatrix(dataModel);
        context.setSimilarity(similarity);

        // build recommender
        conf.set("rec.neighbors.knn.number", "5");
        //
        conf.set("rec.eval.enable", "true");

        conf.set("rec.eval.class", "rmse");
        //



        conf.set("rec.recommender.ranking.topn", "10");
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
        userIdList.add("100124045");
        //itemIdList.add("271");

        // filter the recommended result 过滤器返回你需要的userid 或者itemid
        List<RecommendedItem> recommendedItemList = recommender.getRecommendedList();
        GenericRecommendedFilter filter = new GenericRecommendedFilter();
        filter.setUserIdList(userIdList);
        //filter.setItemIdList(itemIdList);
        recommendedItemList = filter.filter(recommendedItemList);

        // print filter result
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(
                    "user:" + recommendedItem.getUserId() + " " +
                            "item:" + recommendedItem.getItemId() + " " +
                            "value:" + recommendedItem.getValue()
            );
        }
        System.out.println("推荐完成！");
        //输出 保存文件
        int l=recommendedItemList.size();
        double x[][]=new double[l][3];
        int temp=0;
        for (RecommendedItem recommendedItem : recommendedItemList) {
            x[temp][0]=Double.valueOf(recommendedItem.getUserId());
            x[temp][1]=Double.valueOf(recommendedItem.getItemId());
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
