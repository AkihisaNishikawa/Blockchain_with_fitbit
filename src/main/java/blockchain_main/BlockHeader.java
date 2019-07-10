package blockchain_main;

import java.util.Date;

public class BlockHeader {
	private String version; // version to track protocol/software upgrade
	private long timestamp;
	public String merkleroot;// not necessary (depends on data)
	public String previoushash;
	private int difficultytarget;
	public int nonce = 0;

	public BlockHeader(String version, int difficultytarget, String previoushash) {
		this.version = version;
		this.previoushash = previoushash;

		this.timestamp = new Date().getTime();
	}

	public String getVersion() {
		return version;
	}

	public long getTimeStamp() {
		return timestamp;
	}

//	public void setMekelRoot(int difficulty) {
//		
//	}
//	
//	public String getMekelRoot() {
//		return merkleroot;
//	}

	public int getDifficulty() {
		return difficultytarget;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	public int getNonce() {
		return nonce;
	}
}
