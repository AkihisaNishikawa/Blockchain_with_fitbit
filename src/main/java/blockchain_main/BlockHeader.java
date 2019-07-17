package blockchain_main;

public class BlockHeader {
	// To do: change variable sizes
	private String version; // version to track protocol/software upgrade
	public long timestamp;
	public String merkleroot;// not necessary (depends on data)
	public String previoushash;
	private int difficultytarget;
	public int nonce;

	public BlockHeader(String version, int difficultytarget, String previoushash) {
		this.version = version;
		this.previoushash = previoushash;
		this.difficultytarget = difficultytarget;
		this.timestamp = System.currentTimeMillis();
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

	public void setPreviousHash(String previoushash) {
		this.previoushash = previoushash;
	}

	public String getPreviousHash() {
		return previoushash;
	}

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
