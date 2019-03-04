package Fastjson;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * 爬虫实战案例 http://www.haodou.com/recipe/853171/
 *
 * 1. 确定要爬取的数据内容,建立Java Bean对象类
 * 2. 抓包分析评论对应的真实地址
 * 3. 掐头去尾，在线校正 JSON 数据（网址：https://www.json.cn/）
 * 4. 使用 Httpclient 工具或其他 URL 请求工具，获取网页真实地址对应的字符串
 */
public class demo03 {
    //初始化httpclient
    private static HttpClient httpClient = HttpClients.custom().build();

    public static void main(String[] args) throws IOException {
        //需要解析的URL
        String url = "http://www.haodou.com/comment.php?do=list&callback=jQuery183016721538977115902_1531563599327&channel=recipe&item=853171&sort=desc&page=1&size=5&comment_id=0&cate=0&purify=common&_=1531563599599";
        String jsonstring = getJson(url);
        //解析JSON数据
        List<CommentModel> datalist = parseData(jsonstring);
        //输出数据
        for (CommentModel comm : datalist) {
            System.out.println(comm.getCommentId() + "\t" + comm.getItemId() + "\t" + comm.getContent());
        }
    }

    //解析Json内容
    private static List<CommentModel> parseData(String json) {
        json = decode(json);
        //使用分割以及正则取代,处理成标准化JSON数组
        String jsondata = "{" + json.split("data\":\\{")[2].split("\"avatar")[0].replaceAll("\"_\\d*[0-9]\":", "");
        String jsonStr = jsondata.substring(0, jsondata.length()-2);
        //将json数组解析成对象集合
        List<CommentModel> datalis = JSON.parseArray("[" + jsonStr.substring(1, jsonStr.length()) + "]", CommentModel.class);
        return datalis;
    }

    //将unicode码转化为中文
    private static String decode(String unicodeStr) {
        if(unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if(unicodeStr.charAt(i) == '\\') {
                if((i < maxLoop - 5)
                        && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr.charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    //获取JSON内容
    private static String getJson(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        //发出get请求
        HttpResponse response = httpClient.execute(httpGet);
        //获取网页内容流
        HttpEntity httpEntity = response.getEntity();
        //以字符串的形式(需设置编码)
        String entity = EntityUtils.toString(httpEntity, "gbk");
        //关闭内容流
        EntityUtils.consume(httpEntity);
        return entity;  //返回JSON
    }
}
