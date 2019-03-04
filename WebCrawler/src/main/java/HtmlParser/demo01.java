package HtmlParser;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.lexer.Lexer;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class demo01 {

    public static void main(String[] args) throws IOException, ParserException {
        Document doc = Jsoup.connect("http://www.w3school.com.cn/b.asp").timeout(5000).get();
        String html = doc.html();
        //使用Lexer构造
        Lexer lexer = new Lexer(html);
        Parser parser = new Parser(lexer);
        //过滤页面中的链接标签
        NodeFilter filter = new NodeClassFilter(LinkTag.class);
        //获取匹配到的节点
        NodeList list = parser.extractAllNodesThatMatch(filter);
        for (int i = 0; i < list.size(); i++) {
            Node node = list.elementAt(i);
            System.out.println("链接为:" + ((LinkTag) node).getLink() + "\t标题为:" + node.toPlainTextString());
        }
    }
}
