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
	public String hash;
	public BlockHeader blockheader;
	public int instanceCounter;
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

	public static Block createGenesisBlock() {
		BlockHeader header = new BlockHeader("1", 3, "0");// previous hash does not exist
		header.timestamp = 1563121578155L;
		genesisBlock = new Block(header);
		return genesisBlock;
	}

	public String computeHash() {
		Gson format = new GsonBuilder().create();
		String dataInJson = format.toJson(data);
		String headerInJson = format.toJson(blockheader);
		String ts = String.valueOf(instanceCounter);

		String hash = SHA256.generateHash(headerInJson + data.size() + ts + dataInJson);
		System.out.println("Hash of this block is:" + hash);
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return blockheader.getPreviousHash();
	}

}
