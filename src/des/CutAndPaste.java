package des;

public class CutAndPaste {
	public CutAndPaste() {}
	
	public String cutBlock(String encrypted, int begin, int end) {
		return encrypted.substring(0,begin) + encrypted.substring(end,encrypted.length());
	}
	
	public String replaceBlock(String oneBlock, String otherBlock, int blockNr) {
		String toBeReplaced = otherBlock.substring(blockNr*16, (blockNr+1)*16);
		
		String first = oneBlock.substring(0, blockNr*16);
		String last = oneBlock.substring((blockNr+1)*16, oneBlock.length());
		
		return first+toBeReplaced+last;
	}
	
	public String asciiToHex(String ascii) {
		char[] chars = ascii.toCharArray();
	    StringBuffer hex = new StringBuffer();
	    for (int i = 0; i < chars.length; i++)
	    {
	        hex.append((Integer.toHexString((int) chars[i])));
	    }
	    return hex.toString().toUpperCase();
	}
	
	public String hexToAscii(String hex) {
		StringBuilder output = new StringBuilder("");
	    for (int i = 0; i < hex.length(); i += 2)
	    {
	        String str = hex.substring(i, i + 2);
	        output.append((char) Integer.parseInt(str, 16));
	    }
	    return output.toString();
	}
}
