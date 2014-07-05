%SVM without cross validation
function []=SVMwithoutCV()
C=4;
%loading the data
training_data=load('unigram_frequency_train_data.csv');
training_labels=load('unigram_frequency_train_data_labels.csv');
test_data=load('stupid_chunk.csv');
test_Labels=load('stupid_chunk_labels.csv');
%training SVM
svm_result = trainSVM(training_data,training_labels, C);
% saving the predicted labels
predicted_Labels = classifySVM(svm_result, test_data)
%calculating error based on the predicted labels
error = sum(abs(((predicted_Labels-test_Labels')/10)))/length(test_Labels);
error
end