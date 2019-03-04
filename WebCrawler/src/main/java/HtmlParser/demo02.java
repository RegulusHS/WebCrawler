package HtmlParser;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class demo02 {

    public static void main(String[] args) throws ParserException {
        Parser parser = new Parser("http://www.w3school.com.cn/b.asp");
        parser.setEncoding("gbk");
        NodeFilter filtertag = new TagNameFilter("ul");
        NodeFilter filterParent = new HasParentFilter(filtertag);   //父节点包含ul
        NodeFilter filtername = new TagNameFilter("li");    //选择的节点为每个li
        NodeFilter filterId = new HasAttributeFilter("id"); //并且li节点中包含id属性
        NodeFilter filter = new AndFilter(filterParent, filtername);    //并操作
        NodeFilter filterfinal = new AndFilter(filter, filterId);   //并操作
        NodeList list = parser.extractAllNodesThatMatch(filterfinal);   //选择匹配到的内容
        //循环遍历
        for (int i = 0; i < list.size(); i++) {
            //获取li的第一个子节点
            Node node = list.elementAt(i).getFirstChild();
            System.out.println("链接为:" + ((LinkTag) node).getLink() + "\t标题为:" + node.toPlainTextString());
        }
    }
}
