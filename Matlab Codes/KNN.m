%KNN
function []=KNN(n)
n=10;   %k-fold cross validation
acc_arr=[];
%loading data
training_data=load('unigram_train');
training_label=load('unigram_label');
training_size=size(training_data);
part=training_size(1)/n;
x=[1:100];
%perform k fold CV
for k_fold=1:100
    accuracy_final=0;
    for k=1:n
        %initialize arrays for making train and test set
        train=[];
        train_labels=[];
        test_data=[];
        test_labels=[];
        for t_size=1:training_size(1)
            if (t_size>((k-1)*part))&&(t_size<=(k*part))
                %assign the test data
                test_data=[test_data;training_data(t_size,:)];
                test_labels=[test_labels;training_label(t_size)];
            else
                % assign the leftover part as the training data
                train=[train;training_data(t_size,:)];
                train_labels=[train_labels;training_label(t_size,:)];
            end
        end
        no_correct_classification=0;
        size_test=size(test_labels);
        size_training=size(train_labels);
        knn_point=zeros(size_test(1),k_fold);
        new_labels=zeros(size_test(1),k_fold);
        for t_size=1:size_test(1)
            index=zeros(1,k_fold);
            var=0;
            for j=1:size_training(1)
                if var<k_fold          
                    dist=cosineDistance(train(j,:),test_data(t_size,:));
                    var=var+1;
                    index(1,var)=dist;
                    knn_point(t_size,var)=j;
                    new_labels(t_size,var)=train_labels(j);
                else
                    dist=cosineDistance(train(j,:),test_data(t_size,:));
                    new_val=find(index(1,:)==max(index));
                    if dist<index(1,new_val(1))
                        index(1,new_val(1))=dist;
                        knn_point(t_size,new_val(1))=j;
                        new_labels(t_size,new_val(1))=train_labels(j);
                    end
                end
            end
            if mode(new_labels(t_size,:))==test_labels(t_size)
                no_correct_classification=no_correct_classification+1;
            end
        end
        
        accuracy=double((no_correct_classification*100)/size_test(1)*n);
        accuracy_final=accuracy_final + accuracy;
    end
    fprintf('final accuracy= %d\n',accuracy_final/n);
    acc_arr=[acc_arr,accuracy_final];
end
end