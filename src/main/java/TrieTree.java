
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class TrieTree {
	private TrieNode root = new TrieNode();
	private static String CHARSET = "UnicodeBigUnmarked";

	public void insert(String word) {
		if (word == null || word.length() == 0) {
			return;
		}

		byte[] b = null;
		try {
			b = word.getBytes(CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		TrieNode node = root;
		for (int i = 0; i < b.length; i+=2) {
			int ai1 = b[i] & 0xff;
			int ai2 = b[i+1] & 0xff;
			if (node.nodes[ai1][ai2] == null) {
				node.nodes[ai1][ai2] = new TrieNode();
			}
			node = node.nodes[ai1][ai2];
		}
		node.isWord = true;
	}

	public List<Result> find(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		List<Result> list = new ArrayList<Result>();
		byte[] b = null;
		try {
			b = str.getBytes(CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		for (int start = 0; start < b.length; start += 2) {
			int off = start;
			int length = 0;
			TrieNode node = this.root;
			for (int i = start; i < b.length ; i += 2) {
				int ai1 = b[i] & 0xff;
				int ai2 = b[i+1] & 0xff;
				node = node.nodes[ai1][ai2];
				if (node == null){
					break;
				}
				if (node.isWord) {
					off = start;
					length = i+2-start;
					break;
				}
			}

			if (length > 0) {
				Result sensitiveWord = new Result();
				try {
					sensitiveWord.setWord(new String(b, off, length, CHARSET));
					sensitiveWord.setOff(off / 2 );
					sensitiveWord.setLen(length / 2);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				list.add(sensitiveWord);
				start = off + length;
			}
		}
		return list;
	}

	private class TrieNode {
		TrieNode[][] nodes = new TrieNode[256][256];
		boolean isWord = false;
	}
}
