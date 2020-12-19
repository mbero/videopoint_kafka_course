package com.videopoint.kafka.analyzer;

import java.util.Properties;

import com.videopoint.kafka.model.SentimentStatus;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class SentimentAnalyzer {

	private StanfordCoreNLP pipeline;

	public SentimentAnalyzer() {
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
		pipeline = new StanfordCoreNLP(props);
	}

	public int findSentiment(String line) {
		int mainSentiment = 0;
		if (line != null && line.length() > 0) {
			int longest = 0;
			Annotation annotation = pipeline.process(line);
			for (CoreMap sentence : annotation.get(SentencesAnnotation.class)) {
				Tree tree = sentence.get(SentimentAnnotatedTree.class);
				int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
				String partText = sentence.toString();
				if (partText.length() > longest) {
					mainSentiment = sentiment;
					longest = partText.length();
				}
			}
		}
		return mainSentiment;
	}

	public SentimentStatus getSentimentStatusByNumber(int sentimentNumber) {
		SentimentStatus sentimentStatus = null;
		switch (sentimentNumber) {
		case 1:
			sentimentStatus = SentimentStatus.VERY_NEGATIVE;
		case 2:
			sentimentStatus = SentimentStatus.NEGATIVE;
		case 3:
			sentimentStatus = SentimentStatus.NEUTRAL;
		case 4:
			sentimentStatus = SentimentStatus.POSITIVE;
		case 5:
			sentimentStatus = SentimentStatus.VERY_POSITIVE;
		default:
			sentimentStatus = SentimentStatus.NEUTRAL;

		}

		return sentimentStatus;
	}

}
