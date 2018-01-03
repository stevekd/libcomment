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
package com.bbtree.librec;

import com.bbtree.librec.conf.ConfigurationTestCase;
import com.bbtree.librec.data.convertor.ArffDataConvertorTestCase;
import com.bbtree.librec.data.convertor.TextDataConvertorTestCase;
import com.bbtree.librec.data.model.ArffDataModelTestCase;
import com.bbtree.librec.data.model.TextDataModelTestCase;
import com.bbtree.librec.data.splitter.*;
import com.bbtree.librec.filter.GenericRecommendedFilterTestCase;
import com.bbtree.librec.job.RecommenderJobTestCase;
import com.bbtree.librec.recommender.baseline.*;
import com.bbtree.librec.recommender.cf.BHFreeTestCase;
import com.bbtree.librec.recommender.cf.BUCMTestCase;
import com.bbtree.librec.recommender.cf.ItemKNNTestCase;
import com.bbtree.librec.recommender.cf.UserKNNTestCase;
import com.bbtree.librec.recommender.cf.ranking.*;
import com.bbtree.librec.recommender.cf.rating.*;
import com.bbtree.librec.recommender.content.EFMTestCase;
import com.bbtree.librec.recommender.content.HFTTestCase;
import com.bbtree.librec.recommender.context.ranking.SBPRTestCase;
import com.bbtree.librec.recommender.context.rating.*;
import com.bbtree.librec.recommender.ext.*;
import com.bbtree.librec.recommender.hybrid.HybridTestCase;
import com.bbtree.librec.recommender.item.RecommendedItemListTestCase;
import com.bbtree.librec.similarity.BinaryCosineSimilarityTestCase;
import com.bbtree.librec.tool.driver.DataDriverTestCase;
import com.bbtree.librec.tool.driver.RecDriverTestCase;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test Suite
 * @author WangYuFeng
 */
@RunWith(Suite.class)
@SuiteClasses({
	//conf
	ConfigurationTestCase.class,
	//data.convertor
	ArffDataConvertorTestCase.class,
	TextDataConvertorTestCase.class,
	//data.model
	ArffDataModelTestCase.class,
	TextDataModelTestCase.class,
	//data.splitter
	GivenNDataSplitterTestCase.class,
	GivenTestSetDataSplitterTestCase.class,
	KCVDataSplitterTestCase.class,
	LOOCVDataSplitterTestCase.class,
	RatioDataSplitterTestCase.class,
	//filter
	GenericRecommendedFilterTestCase.class,
	//io
//	ArrayWritableTestCase.class,
	//job
//	JobStatusTestCase.class,
	RecommenderJobTestCase.class,
	//recommender.item
	RecommendedItemListTestCase.class,
	//recommender.rec.baseline
	ConstantGuessTestCase.class,
	GlobalAverageTestCase.class,
	ItemAverageTestCase.class,
	ItemClusterTestCase.class,
	MostPolularTestCase.class,
	RandomGuessTestCase.class,
	UserAverageTestCase.class,
	UserClusterTestCase.class,
	//recommender.cf.rating
	AspectModelRatingTestCase.class,
	ASVDPlusPlusTestCase.class,
	BiasedMFTestCase.class,
	BPMFTestCase.class,
	BPoissMFTestCase.class,
	FMALSTestCase.class,
	FMSGDTestCase.class,
	GPLSATestCase.class,
	LDCCTestCase.class,
	LLORMATestCase.class,
	MFALSTestCase.class,
	NMFTestCase.class,
	PMFTestCase.class,
	RBMTestCase.class,
	RfRecTestCase.class,
	SVDPlusPlusTestCase.class,
	URPTestCase.class,
	BHFreeTestCase.class,
	BUCMTestCase.class,
	ItemKNNTestCase.class,
	UserKNNTestCase.class,
	//recommender.cf.ranking
	AOBPRTestCase.class,
	AspectModelRankingTestCase.class,
	BPRTestCase.class,
	CLIMFTestCase.class,
	EALSTestCase.class,
	FISMAUCTestCase.class,
	FISMRMSETestCase.class,
	GBPRTestCase.class,
	ItemBigramTestCase.class,
	LDATestCase.class,
	ListRankMFTestCase.class,
	PLSATestCase.class,
	RankALSTestCase.class,
	RankSGDTestCase.class,
	SLIMTestCase.class,
	WBPRTestCase.class,
	WRMFTestCase.class,
	//recommender.content
	EFMTestCase.class,
	HFTTestCase.class,
	//recommender.context.ranking
	SBPRTestCase.class,
	//recommender.context.rating
	RSTETestCase.class,
	SocialMFTestCase.class,
	SoRecTestCase.class,
	SoRegTestCase.class,
	TimeSVDTestCase.class,
	TrustMFTestCase.class,
	TrustSVDTestCase.class,
	//recommender.ext
	AssociationRuleTestCase.class,
	ExternalTestCase.class,
	PersonalityDiagnosisTestCase.class,
	PRankDTestCase.class,
	SlopeOneTestCase.class,
	//recommender.hybrid
	HybridTestCase.class,
	//similarity
	BinaryCosineSimilarityTestCase.class,
	//tool.driver
	DataDriverTestCase.class,
	RecDriverTestCase.class
})
public class TestCaseSuite {

}
