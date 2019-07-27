package test;

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
		Multithread mt = new Multithread(Block.genesisBlock);
		System.out.println("Finding a key...");
		long starttime = System.nanoTime();
		mt.start();
		try {
			mt.join();
		} catch (InterruptedException e) {
			// 例外処理
			e.printStackTrace();
		}
		long endtime = System.nanoTime();
		System.out.println(endtime - starttime);

	}

}

class Multithread extends Thread {
	Block block;

	Multithread(Block block) {
		this.block = block;
	}

	@Override
	public void run() {
		boolean x = false;
		String hash = "";
		int nonce = 0;
		final int difficulty = block.blockheader.getDifficulty();
		String target = new String(new char[difficulty]).replace('\0', '0'); // Create a string with difficulty * "0"

		Gson parser = new Gson();

		while (!x) {
			block.blockheader.setNonce(nonce);
			hash = SHA256.generateHash(parser.toJson(block));
			x = hash.substring(0, difficulty).equals(target);
			System.out.println(nonce);
			nonce++;
		}

	}

}