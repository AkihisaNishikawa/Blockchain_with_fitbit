# Blockchain Structure with Fitbit Data

This is a project of simple blockchain structure to test peformance of blockchain in local machine.  Please read these instructions carefully.

This project is part of my Research Project to test the performance of blockchain with proof of work.


## Instructions

1 - Clone this repo as Maven Project into IDE

2 - Maven `mvn 3.0+` and 'java 8.0+'required

3- For the execution, there is no need of maven. Go to `Getting Started` step 4


## Getting Started 

1 - Change Directory to where POM.xml resides

2 - Support IDE with maven If you are only running code from executable jar file, skip to step 4

e.g.)
```
 $ mvn eclipse:eclipse  
```
3 - Package all dependencies
```
 $ mvn package
```
4 - Execute the project
After Step 3 your IDE should be able to execute the code and main method is in Blockchain_Main
Here is an example to execute from command line 
e.g.)  
```
 $ java -cp target/2019-mcm-master.jar blockchain_main.Blockchain_Main
```
There is the executable jar with the dependencies in target foulder and the 2019-mcm-master.jar's difficulty is set to 7.  
Other difficulties are stored as 019-mcm-master-0X.jar  
  
Execution has to be done from where the `One_Year_of_FitBitChargeHR_Data.csv` resides  
Depends on the hardware, execution takes quite long time. Please be aware of hardware enviroment.  

In this project maven is used to collect all dependencies and to create executable files in target foulder. For more reference to create executable, see [Maven Documentation](https://maven.apache.org/plugins/maven-shade-plugin/examples/executable-jar.html) 

- The order of creating valuee and/or incetances is important  
-- Initially, genesis block should be created. Only Creation time is set initially and detail variable can be set as needed  
-- Blockheader must be created before the block to be passed onto Block instance
 
5 - Final Paper is in `docs/documentation` 

## JUnit Tests

1 - `test.Block_test.java` - Genesis Block Creation and Mining Function 

2 - `test.Dataset_test.java` - Dataset handling functions

 
## Additional Resources

1 - `additional_functions.SHA256.java` - SHA256 hash function and implementation of output handling

2 - `additional_functions.ObjectSizeCalculator.java` - Aimed for calculating block size however not used in main

#Code Reference

1 - [bitcoinj/bitcoinj](https://github.com/bitcoinj/bitcoinj)
	- Full blockchain structure from Bitcoinj

2 - [Simple Blockchain Implementation](https://github.com/in-the-keyhole/khs-blockchain-java-example)
	- 2018 Keyhole Software White Paper, Blockchain for the Enterprise

3 - [A simple implementation of blockchain](https://github.com/Will1229/Blockchain)
	- Blockchai Implementation with web interface
 
