1 test_librecomment3.java （default data）自己调试的推荐ranking10（
notice：if use evaluate the model
	 need to set the  System.out.println("RMSE:" + recommender.evaluate(evaluator));  
	else
	// 
	and  conf.set("rec.recommender.isranking","true");
             conf.set("rec.recommender.ranking.topn","10");      ）


2  test_test_librecomment4.java    自己的data(UIR)


3 test_test_librecomment5.java     多列的测试 ARFF

4 test_contentHFT 是测试txt进行HFT算法的

5