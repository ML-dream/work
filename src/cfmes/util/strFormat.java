package cfmes.util;


public class strFormat {

	/**
	 * �ַ����滻���� source �е� oldString ȫ������ newString
	 *
	 * @param source Դ�ַ���
	 * @param oldString �ϵ��ַ���
	 * @param newString �µ��ַ���
	 * @return �滻����ַ���
	 * ��������ı��ַ���ת����HTML��ʽ���ı�
	 */
	public static void main(String[] args) {
	}

	public static String Replace(
		String source,
		String oldString,
		String newString) {
		StringBuffer output = new StringBuffer();

		int lengthOfSource = source.length(); // Դ�ַ�������
		int lengthOfOld = oldString.length(); // ���ַ�������

		int posStart = 0; // ��ʼ����λ��
		int pos; // ���������ַ�����λ��

		while ((pos = source.indexOf(oldString, posStart)) >= 0) {
			output.append(source.substring(posStart, pos));

			output.append(newString);
			posStart = pos + lengthOfOld;
		}

		if (posStart < lengthOfSource) {
			output.append(source.substring(posStart));
		}

		return output.toString();
	}

	/*
	public static String ReplaceIgnoreCase(String source, String oldString, String newString) {
	}
	*/

	/**
	 * ���ַ�����ʽ���� HTML �������
	 * ֻת�������ַ����ʺ��� HTML �еı�����
	 *
	 * @param str Ҫ��ʽ�����ַ���
	 * @return ��ʽ������ַ���
	 */
	public static String toHtmlInput(String str) {
		if (str == null)
			return null;

		String html = new String(str);

		html = Replace(html, "&", "&amp;");
		html = Replace(html, "<", "&lt;");
		html = Replace(html, ">", "&gt;");

		return html;
	}

	/**
	 * ���ַ�����ʽ���� HTML �������
	 * ����ͨ�����ַ��⣬���Կո��Ʊ���ͻ��н���ת����
	 * �Խ����ݸ�ʽ�������
	 * �ʺ��� HTML �е���ʾ���
	 *
	 * @param str Ҫ��ʽ�����ַ���
	 * @return ��ʽ������ַ���
	 */
	public static String toHtml(String str) {
		if (str == null)
			return null;

		String html = new String(str);

		html = toHtmlInput(html);
		html = Replace(html, "\r\n", "\n");
		html = Replace(html, "\n", "<br>\n");
		html = Replace(html, "\t", "    ");
		html = Replace(html, "  ", " &nbsp;");

		return html;
	}

	/**
	 * ����ͨ�ַ�����ʽ�������ݿ��Ͽɵ��ַ�����ʽ
	 *
	 * @param str Ҫ��ʽ�����ַ���
	 * @return �Ϸ������ݿ��ַ���
	 */
	public static String toSql(String str) {
		String sql = new String(str);
		return Replace(sql, "'", "''");
	}

	/*
	public static void main(String[] args) {
	    String s = "<html>    ddd";
	    Format f = new Format();
	    System.out.println(f.toHtmlInput(s));
	    System.out.println(f.toHtml(s));
	}
	*/
}