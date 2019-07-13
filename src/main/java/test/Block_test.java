package test;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import blockchain_main.Block;
import additional_functions.ObjectSizeCalculator;

public class Block_test {

	@Test
	public void testGenesisBlock() {
		Block genesisBlock = Block.createGenesisBlock();
		Gson parser = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(parser.toJson(genesisBlock));
		genesisBlock.computeHash();
		System.out.println(parser.toJson(genesisBlock));
		long a = ObjectSizeCalculator.calculateObjectSize(genesisBlock);
		genesisBlock.setBlocksize(a);
		System.out.println(parser.toJson(genesisBlock));
		genesisBlock.computeHash();
	}

}
