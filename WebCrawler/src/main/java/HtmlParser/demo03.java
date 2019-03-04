package HtmlParser;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class demo03 {

    public static void main(String[] args) throws IOException, ParserException {
        URL url = new URL("http://www.w3school.com.cn/b.asp");
        URLConnection conn = url.openConnection();
        Parser parser = new Parser(conn);
        //通过css选择器解析内容
        CssSelectorNodeFilter filter = new CssSelectorNodeFilter("#course > ul > li");
        NodeList list = parser.extractAllNodesThatMatch(filter);    //选择匹配到的内容
        for (int i = 0; i < list.size(); i++) {
            Node node = list.elementAt(i).getFirstChild();
            System.out.println("链接为:" + ((LinkTag) node).getLink() + "\t标题为:" + node.toPlainTextString());
        }
    }
}
