package blockchain_main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import additional_functions.SHA256;

public class Blockchain_Main {

	private static List<Block> blockchain = new ArrayList<Block>();
	private static List<Fitbit_data> fitbit;

	public static void main(String[] args) {
		Block genesisBlock = Block.createGenesisBlock();

		genesisBlock.computeHash();
		// make sure hash value is not changing with same input

		System.out.println("Finding a key...");
		
		long starttime = System.currentTimeMillis();
		final String ghash = proofOfWork(Block.genesisBlock);
		long endtime = System.currentTimeMillis();
		System.out.println("Time spent on mining in milli second: "+ (endtime - starttime));
		
		genesisBlock.setHash(ghash);

		Gson parser = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(parser.toJson(genesisBlock));
		
		blockchain.add(genesisBlock);
		
		try {
			fitbit = Fitbit_data.getInstancesAsList();
		} catch (FileNotFoundException e) {
			System.out.println("Fitbit dataset file is not found");
			e.printStackTrace();
			System.exit(-1);
		}
		
		
		System.out.println("Finding a key...");

		BlockHeader bh1 = new BlockHeader("1", 4, genesisBlock.getHash());
		Block block1 = new Block(fitbit, bh1);
		
		starttime = System.currentTimeMillis();
		block1.setHash(proofOfWork(block1));
		endtime = System.currentTimeMillis();
		System.out.println("Time spent on mining in milli second: "+  (endtime - starttime));

		System.out.println(parser.toJson(block1));


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
