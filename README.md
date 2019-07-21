# Blockchain Structure with Fitbit Data

This is a project of simple blockchain structure to test peformance of blockchain in local machine.  Please read these instructions carefully.

This project is part of my Research Project at Dublin City University. Akihisa Nishikawa


## Instructions

1 - Clone this repo as Maven Project into IDE

2 - Maven 'mvn 3.0+' required


## Getting Started 

1 - Change Directory to where POM.xml resides

2 - Support IDE with maven

e.g.)
 $ mvn eclipse:eclipse  

3 - Package all dependencies

 $ mvn package

In this project maven is used to collect all dependencies. In order to create executable, see [Maven Documentation](https://maven.apache.org/plugins/maven-shade-plugin/examples/executable-jar.html) 

- The order of creating valuee and/or incetances is important
--Initially, genesis block should be created. Only Creation time is set initially and detail variable can be set as needed
--Blockheader must be created before the block to be passed onto Block instance
 
4 - There is further important documentation in the `docs` directory. References of the code are included in 'docs/README.md' also in comment.

## JUnit Tests

1 - 'test.Block_test.java' - Genesis Block Creation and Mining Function 

2 - 'test.Dataset_test.java' - Dataset handling functions

 
## Additional Resources

1 - 'additional_functions.SHA256.java' - SHA256 hash function and implementation of output handling

2 - 'additional_functions.ObjectSizeCalculator.java' - Aimed for calculating block size however not used
 
