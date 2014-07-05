rand('seed', 491218382)

net = network;
        net=init(net);
        net.numInputs=1;
        net.numLayers=3;
        net.inputConnect(1,1) = 1;
%net.inputConnect(2,1) = 1;
%net.inputConnect(2,2) = 1;
         %net.inputConnect: [0 0 0; 1 0 0; 0 1 0]
        net.biasConnect(1)=1;
        net.biasConnect(2)=1;
        net.biasConnect(3)=1;
        net.layerConnect = [0 0 0; 1 0 0; 0 1 0];
        net.outputConnect = [0 0 1];
        net.inputs{1}.size=16382;
         net.layers{1}.size=20;
             net.layers{2}.size=7;
  %               net.layers{3}.size=10;//
        net.layers{1}.transferFcn='tansig';
        net.layers{1}.initFcn='initnw';
            net.layers{2}.transferFcn='tansig';
%                  net.inputs{2}.size=10;//16..
        net.layers{2}.initFcn='initnw';
 %            net.inputs{3}.size=10;//
        net.layers{3}.transferFcn='tansig';
        net.layers{3}.initFcn='initnw';
        
        net.initFcn='initlay';
        net.performFcn = 'mse';
        net.trainFcn = 'trainlm';
        
%net = patternnet(10);
%net = feedforwardnet(25);
%net = cascadeforwardnet(10);
net = train(net,p,target);



 
%net = feedforwardnet(10);
        
%net = patternnet(10);
%net = feedforwardnet(25);
%net = cascadeforwardnet(10);
%net = train(net,x,t);


[net,tr] = train(net,p,target);
view(net)

nntraintool
ntstool


plotperform(tr)