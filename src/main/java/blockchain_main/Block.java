package blockchain_main;

import java.lang.instrument.Instrumentation;
import java.util.List;

/**
 * @author aki Block instance
 */
public class Block {
	private List<Fitbit_data> data;// for now I will keep it as String but it will be another Object
	public String hash;
	public BlockHeader blockheader;
	public long blocksize;

	public Block(List<Fitbit_data> data, BlockHeader blockheader) {
		this.data = data;
		this.blockheader = blockheader;

	}

	/**
	 * @param block Pass block onto this method to calculate and set blocksize
	 */
	public void setBlocksize(Block block) {
		this.blocksize = getObjectSize(block);
	}

	public static Block getGenesisBlock() {
		Block genesisBlock = null; // To do decide what's in genesis block
		return genesisBlock;
	}

	private static Instrumentation instrumentation;

	public static long getObjectSize(Object o) {
		return instrumentation.getObjectSize(o);
	}

}
