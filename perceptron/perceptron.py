import os
from featurecreator import FeatureCreator
from itertools import tee, izip
from collections import Counter
from random import choice
print "inside perceptron"


def dot(a,b):
   
    sum1=0
  
    for i in range(0,len(b)):
        a1=int(a[i])*int(b[i])
        sum1=sum1+a1
    return sum1


def initializeweight(size):
    weight=[0] *size
   
    return weight 


def transferfunc(input1,weight):
    y= dot(input1,weight)
    if y>=0:
        return 1
    return -1


def multiply1(scalar,vector):
    newvector=[0]*len(vector)
    i=0;
    print vector
    for v in vector:
        #print "v"
        #print v
        #print scalar
        #if(v!=''):
        newvector[i]=int(v)*scalar
       
        i=i+1
  
    return newvector



def add(vector1,vector2):       
    newvector=[0]*len(vector1)
    for i in range(0,len(vector1)):
        newvector[i]=vector1[i]+vector2[i]
    
    return newvector



def adjustweight(error,weight,input1):
    
    weight = add(weight,multiply1(error, input1))

    return weight



def checkifweightchange(obtainedclass,expectedclass):
    if (obtainedclass == 1 and expectedclass == -1) or (obtainedclass == -1 and expectedclass == 1):
        return 1
    return 0




def featurevectorsize():
    openfile=open(r'C:\Users\sai preethy\Dropbox\\nlp\interesting_sad_train.txt', 'r')
   # openfile = open('C:\Users\sai preethy\Downloads\\unigrampres1.txt', 'r')
    cnt=1
    unifeaturevector = []
    unifeaturelist = [] 
    content = openfile.readline()
    words = content.split()
    return len(words)-1   
            
           
         


#exit()   
def featurevector1(globalerror,weight):
    crimefile = open(r'C:\Users\sai preethy\Dropbox\nlp\\interesting_sad_train.txt', 'r')
    yourResult = [line.split('\t') for line in crimefile.readlines()] 
    for i in range(1,len(yourResult)):
       # input1=[]
        input1=choice(yourResult)

        yourResult.remove(input1)
        del input1[-1]
        print(input1[0])
        expectedclass=int(input1[0])
        input1.pop(0)
     
        obtainedclass=transferfunc(input1,weight)
       
        error=int(expectedclass)-int(obtainedclass)
       
        if obtainedclass != expectedclass:
            weight=adjustweight(error,weight,input1)
      
        globalerror += abs(error)
         
    ret=[]
    ret=weight+[globalerror]
     
    return ret
            





def test(weight):
    correct=0
    crimefile = open(r'C:\Users\sai preethy\Dropbox\nlp\\interesting_sad_test.txt', 'r')
    yourResult = [line.split('\t') for line in crimefile.readlines()] 
    for i in range(1,len(yourResult)):
       # input1=[]
        input1=choice(yourResult)
        yourResult.remove(input1)
        #del input1[-1]
        
        expectedclass=input1[0]
        input1.pop(0)

        obtainedclass=transferfunc(input1,weight)
       
        error=int(expectedclass)-int(obtainedclass)
       # print "error obt"
        print error
        print int(expectedclass)
        print int(obtainedclass)
        print "done"
        if error==0:
            correct=correct+1
    return correct
     
     
     
     
     
size=featurevectorsize()
weight=initializeweight(size-2)
#exit()
learn=False
cnt=0
while not learn:
    globalerror=0.0   
    print "iteration"
    wglobal=featurevector1(globalerror,weight) 
    globalerror=wglobal.pop()
    del wglobal[-1]
    weight=wglobal
    print "weight"
    print weight
    print "global error"
    print globalerror
    if globalerror==0.0 or cnt==100:
    #if cnt==20:
        learn=True
        print "stopping"
        print globalerror
        print cnt
    cnt=cnt+1
acc=test(weight)
print "accuracy"
print acc
    
