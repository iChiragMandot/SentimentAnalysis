% SVM with cross validation. The input will decide the number of n(chunks)
function []=SVMwithCV(n)
C=4;
%loading the data
train=load('SVM_Headline_new_Train.csv');
label=load('SVM_Headline_new_Labels.csv');
train_size=size(train);
%dividing the data into chunks
chunk=floor(train_size(1)/n);
average_error=0;
for t=1:n
    fprintf('************N Fold number %d**************\n',t);
    train_data=[];
    test_data=[];
    train_labels=[];
    test_labels=[];
    %assign the train and test data and run it for n times
    for i=1:train_size(1)
        if (i<=(t*chunk))&&(i>((t-1)*chunk))
            test_data=[test_data;train(i,:)];
            test_labels=[test_labels;label(i)];
        else
            train_data=[train_data;train(i,:)];
            train_labels=[train_labels;label(i,:)];
        end
    end
    % train svm
    svmModel = trainSVM(train_data,train_labels, C);
    %predict label
    predictedLabels = classifySVM(svmModel, test_data);
    fprintf('*******fold number = %d******\n',t);
    %calculate error
    testError = sum(abs(ceil(abs((predictedLabels-test_labels')/10))))/length(test_labels)
    %display final error
    average_error=average_error+testError;
end
fprintf('\n*****Total average error = %d*****\n',average_error/n);
end