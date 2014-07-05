SentimentAnalysis
==================

To detect sentiment in the news articles
The project aims as classifying news article based on their sentiments. We have taken multiple categories into account. For example, sad, scary, stupid, wierd etc.
We take data from fark.com and train our model to predict the sentiments which are invoked in the corresponding article.
It will be beneficial to read the paper attached with the codes. It will help you understand the model.
The repositories can be explained as:
Dataset: It contains a sample dataset which we took for training our model
Matlab: This folder includes the matlab codes used. It has codes for KNearest Neighbor, SVM with cross validation
Lexical Chain: It has the code for lexical chain analysis
Neural Network: We tried employing neural network with unsuccesful attempt. Will update if any progress made in future
Baseline Accuracy calculator: It is used to calculate baseline. The description of our baseline is present in the paper
Feature Vector Generator: This code takes the text and converts it into a feature vector. We have used bag or words i.e. unigram, bigram, trigram, we also used TF-IDF and a few more
Perceptron: It contains the perceptron code which we used to analyze two emotion categories at a time
Web Crawler: This is the code used to extract data from Fark.com:
