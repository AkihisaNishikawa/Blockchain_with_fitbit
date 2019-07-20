package blockchain_main;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import additional_functions.SHA256;;

/**
 * @author aki Block instance
 */
public class Block {
	// To do: change variable sizes
	private List<Fitbit_data> data;// this can be changed to any preferred data
	public BlockHeader blockheader;
	public int instanceCounter;
	public String hash;
	public static Block genesisBlock;

	public Block(BlockHeader blockheader) {
		this.data = new ArrayList<Fitbit_data>();
		this.blockheader = blockheader;
		this.instanceCounter = data.size();

	}

	public Block(List<Fitbit_data> data, BlockHeader blockheader) {
		this.data = data;
		this.blockheader = blockheader;
		this.instanceCounter = data.size();

	}

	/*
	 * This method is adapted from
	 * https://github.com/in-the-keyhole/khs-blockchain-java-example/blob/master/src/main/java/simple/chain/Block.java
	 * According to the reference it is
	 * originated from the https://github.com/bitcoinj/bitcoinj project Copyright
	 * 2011 Google Inc. Copyright 2014 Andreas Schildbach
	 * 
	 * Creating Merkle Tree (Binary Tree) inside of ArrayList and Return Merkle Root
	 */
	
	public String merkleTree() {
		ArrayList<String> tree = new ArrayList<>();
		Gson parser = new Gson();
		String hash;
		// add all transactions as leaves of the tree.
		for (Fitbit_data f : data) {
			hash = SHA256.generateHash(parser.toJson(f));
			tree.add(hash);
		}
		int levelOffset = 0; // first level

		// Iterate through each level, stopping when we reach the root (levelSize == 1).
		for (int levelSize = data.size(); levelSize > 1; levelSize = (levelSize + 1) / 2) {
			// For each pair of nodes on that level:
			for (int left = 0; left < levelSize; left += 2) {
				// The right hand node can be the same as the left hand, in the
				// case where we don't have enough data.
				int right = Math.min(left + 1, levelSize - 1);
				String tleft = tree.get(levelOffset + left);
				String tright = tree.get(levelOffset + right);
				tree.add(SHA256.generateHash(tleft + tright));
			}
			// Move to the next level.
			levelOffset += levelSize;
		}
		return tree.get(tree.size()-1);
	}

	public static Block createGenesisBlock() {
		BlockHeader header = new BlockHeader("1", 5, "0");// previous hash does not exist
		header.timestamp = 1563121578155L;
		genesisBlock = new Block(header);
		return genesisBlock;
	}

	public String computeHash() {
		Gson format = new GsonBuilder().create();
		String headerInJson = format.toJson(blockheader);
		String hash = SHA256.generateHash(headerInJson);
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
