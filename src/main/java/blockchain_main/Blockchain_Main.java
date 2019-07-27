package blockchain_main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import additional_functions.SHA256;

public class Blockchain_Main {
	public String hash;

	private static Map<String, Block> blockchain = new HashMap<String, Block>();// This string is Hash of the Block
	private static ArrayList<Fitbit_data> fitbit;
	private static final int difficulty = 7;

	public static void main(String[] args) {
		Block genesisBlock = Block.createGenesisBlock();

		genesisBlock.computeHash();
		// make sure hash value is not changing with same input

		System.out.println("Finding a key...");

		long starttime = System.currentTimeMillis();
		final String ghash = proofOfWork(Block.genesisBlock);
		long endtime = System.currentTimeMillis();

		Gson parser = new GsonBuilder().setPrettyPrinting().create();
		blockchain.put(ghash, genesisBlock);
		System.out.println(parser.toJson(blockchain));
		System.out.println("Time spent on mining in milli second: " + (endtime - starttime));

		try {
			fitbit = Fitbit_data.getInstancesAsList();
		} catch (FileNotFoundException e) {
			System.out.println("Fitbit dataset file is not found");
			e.printStackTrace();
			System.exit(-1);
		}

		List<Fitbit_data> fb1, fb2, fb3;
		fb1 = fitbit.subList(1, 122);
		fb2 = fitbit.subList(123, 245);
		fb3 = fitbit.subList(246, 368);

		// First Block--------------------------------------------------------
		System.out.println("Finding a key...");

		BlockHeader bh1 = new BlockHeader("1", difficulty, genesisBlock.computeHash());
		Block block1 = new Block(fb1, bh1);
		bh1.setMekelRoot(block1.merkleTree());

		starttime = System.currentTimeMillis();
		blockchain.put(proofOfWork(block1), block1);
		endtime = System.currentTimeMillis();

		System.out.println(block1.computeHash());
		System.out.println(parser.toJson(block1));
		System.out.println("Time spent on mining in milli second: " + (endtime - starttime));

		// Second Block--------------------------------------------------------
		System.out.println("Finding a key...");

		BlockHeader bh2 = new BlockHeader("1", difficulty, block1.computeHash());
		Block block2 = new Block(fb2, bh2);
		bh2.setMekelRoot(block2.merkleTree());

		starttime = System.currentTimeMillis();
		blockchain.put(proofOfWork(block2), block2);
		endtime = System.currentTimeMillis();

		System.out.println(block2.computeHash());
		System.out.println(parser.toJson(block2));
		System.out.println("Time spent on mining in milli second: " + (endtime - starttime));

		// Third Block--------------------------------------------------------
		System.out.println("Finding a key...");

		BlockHeader bh3 = new BlockHeader("1", difficulty, block2.computeHash());
		Block block3 = new Block(fb3, bh3);
		bh3.setMekelRoot(block3.merkleTree());

		starttime = System.currentTimeMillis();
		blockchain.put(proofOfWork(block3), block3);
		endtime = System.currentTimeMillis();

		System.out.println(block3.computeHash());
		System.out.println(parser.toJson(block3));
		System.out.println("Time spent on mining in milli second: " + (endtime - starttime));

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
			hash = SHA256.generateHash(parser.toJson(block.blockheader));
			x = hash.substring(0, difficulty).equals(target);
			nonce++;
		}
		block.setHash(hash);// This could be done outside the function
		return hash;
	}
}
