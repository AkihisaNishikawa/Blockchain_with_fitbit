package test;

import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import additional_functions.SHA256;
import blockchain_main.Block;

public class Block_test {

	@Test
	public void testGenesisBlock() {
		Block genesisBlock = Block.createGenesisBlock();
		Gson parser = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(parser.toJson(genesisBlock));
		genesisBlock.computeHash();// make sure hash value is not changing

	}
	
	@Test
	public void checkPoW() {
		System.out.println("Finding a key...");
		long starttime = System.nanoTime();
		System.out.println(proofOfWork(Block.genesisBlock));
		long endtime = System.nanoTime();
		System.out.println(endtime-starttime);
	
	}
	
	private String proofOfWork(Block block) {
		boolean x = false;
		String hash="";
		int nonce = 0;
		final int difficulty = block.blockheader.getDifficulty();
		String target = new String(new char[difficulty]).replace('\0', '0'); // Create a string with difficulty * "0"

		
		Gson parser = new Gson();
		
		while(!x) {
			block.blockheader.setNonce(nonce);
			hash = SHA256.generateHash(parser.toJson(block));
			x = hash.substring(0,difficulty).equals(target);
			nonce++;
		}
		
		
		return hash;
	}
	


}
