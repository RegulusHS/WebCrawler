package FileProcessInCrawler;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;

/**
 * 网络爬虫下载图片实战案例
 */
public class Demo05 {

    private static HttpClient httpClient = HttpClients.custom().build();

    static void saveImage(String url, String savePath) throws IOException {
        //图片下载保存地址
        File file = new File(savePath);
        //如果文件存在则删除
        if(file.exists()) {
            file.delete();
        }
        //缓冲流
        BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(savePath));
        //请求图片数据
        try {
            HttpEntity entity = getEntityByHttpGetMethod(url);
            //以字节的方式写入
            byte[] byt = EntityUtils.toByteArray(entity);
            bw.write(byt);
            System.out.println("图片下载成功!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        bw.close();
    }

    //另外一种操作方式
    static void saveImage1(String url, String savePath) throws IOException {
        //获取图片信息,作为输入流
        InputStream in = getEntityByHttpGetMethod(url).getContent();
        byte[] buffer = new byte[1024];
        BufferedInputStream bufferedIn = new BufferedInputStream(in);
        int len = 0;
        //创建缓冲流
        FileOutputStream fileOutputStream = new FileOutputStream(new File(savePath));
        BufferedOutputStream bufferedOut = new BufferedOutputStream(fileOutputStream);
        //图片导入
        while ((len = bufferedIn.read(buffer, 0, 1024)) != -1) {
            bufferedOut.write(buffer, 0, len);
        }
        //缓冲流释放与关闭
        bufferedOut.flush();
        bufferedOut.close();
    }

    //请求某一个URL,获得请求到的内容
    private static HttpEntity getEntityByHttpGetMethod(String url) {
        HttpGet httpGet = new HttpGet(url);
        //获取结果
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = httpResponse.getEntity();
        return entity;
    }

    public static void main(String[] args) throws IOException {
        String url = "http://pic.yxdown.com/list/0_0_1.html";
        HttpEntity entity = getEntityByHttpGetMethod(url);
        //获取所有图片链接
        String html = EntityUtils.toString(entity);
        Elements elements = Jsoup.parse(html).select("div.cbmiddle > a.proimg > img");
        for (Element ele : elements) {
            String pictureUrl = ele.attr("src");
            //bpic.588ku.com/back_pic/05/82/06/005c3e8d0dcfee7.jpg!/fh/300/quality/90/unsharp/true/compress/true
            saveImage1(pictureUrl, "F:\\learn\\WebCrawler\\src\\main\\java\\FileProcessInCrawler\\picture/" + pictureUrl.split("/")[7]);
        }
//        saveImage("http://588ku.com/beijing/0-0-pxnum-0-8-0-0-0-1/?hd=85//bpic.588ku.com/back_pic/05/82/06/005c3e8d0dcfee7.jpg!/fh/300/quality/90/unsharp/true/compress/true", "F:\\learn\\WebCrawler\\src\\main\\java\\FileProcessInCrawler\\picture/005c3e8d0dcfee7.jpg");
    }
}
