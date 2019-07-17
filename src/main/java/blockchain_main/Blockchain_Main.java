package blockchain_main;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import additional_functions.SHA256;

public class Blockchain_Main {

	private static List<Block> blockchain = new ArrayList<Block>();

	public static void main(String[] args) {
		Block genesisBlock = Block.createGenesisBlock();

		genesisBlock.computeHash();// make sure hash value is not changing with same input

		System.out.println("Finding a key...");
		long starttime = System.nanoTime();
		final String ghash = proofOfWork(Block.genesisBlock);
		System.out.println(ghash);
		long endtime = System.nanoTime();
		System.out.println(endtime - starttime);
		genesisBlock.setHash(ghash);

		Gson parser = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(parser.toJson(genesisBlock));

	}

	private static String proofOfWork(Block block) {
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
			nonce++;
		}

		return hash;
	}
}
