package blockchain_main;

import com.google.gson.Gson;

import additional_functions.SHA256;

public class Blockchain_Main {
	
	public static void main(String[] args) {
		
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
