
import java.util.List;

public class Run {


	public static void main(String[] args) {
		TrieTree tree = new TrieTree();
		tree.insert("������");
		List<Result> list = tree.find("�ǲ�������˿��");
		for (int i = 0; i < list.size(); i++) {
			Result rs = list.get(i);
			System.out.println(rs.getOff() + "," + rs.getLen() + "," + rs.getWord());
		}
	}
}
