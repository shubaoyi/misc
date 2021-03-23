package com.byshu.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchImg {
    // 地址
    ArrayList<String> urls = new ArrayList<String>();
    // img标签正则，src正则
    private static final String IMGURL_REG = "<img id=\"imgpath\" .*src=(.*?)[^>]*?>";
    private static final String IMGSRC_REG = "[a-zA-z]+://[^\\s]*";

    {
        // 卫星云图，雷达图
        urls.add("http://www.nmc.cn/publish/satellite/FY4A-true-color.htm");
        urls.add("http://www.nmc.cn/publish/radar/chinaall.html");
    }

    public static void main(String[] args) {
        try {
            FetchImg fi = new FetchImg();
            for (String url : fi.urls) {
                doFetch(fi, url, url.contains("radar") ? "radar" : "satellite");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void doFetch(FetchImg fi, String url, String dir) throws Exception {
        String html = fi.getHtml(url);
        List<String> imgUrl = fi.getImageUrl(html);
        List<String> imgSrc = fi.getImageSrc(imgUrl);
        fi.download(imgSrc, dir);
    }

    // 获取html内容
    private String getHtml(String url) throws Exception {
        URL url1 = new URL(url);
        URLConnection connection = url1.openConnection();
        InputStream in = connection.getInputStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);

        String line;
        StringBuffer sb = new StringBuffer();
        while ((line = br.readLine()) != null) {
            sb.append(line, 0, line.length()).append('\n');
        }

        br.close();
        isr.close();
        in.close();
        return sb.toString();
    }

    // 获取ImageUrl地址
    private List<String> getImageUrl(String html) {
        Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

    // 获取ImageSrc地址
    private List<String> getImageSrc(List<String> listimageurl) {
        List<String> list = new ArrayList<String>();
        for (String image : listimageurl) {
            Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);
            while (matcher.find()) {
                list.add(matcher.group().substring(0, matcher.group().length() - 1));
                // 只取主图
                break;
            }
        }
        return list;
    }

    // 下载图片
    private void download(List<String> listImgSrc, String dir) {
        try {
            for (String url : listImgSrc) {
                int endIndex = url.lastIndexOf("?");
                String imageName = url.substring(url.lastIndexOf("/") + 1, endIndex == -1 ? url.length() : endIndex);

                File f = new File("F:/" + dir);
                if (!f.exists())
                    f.mkdir();

                URL uri = new URL(url);
                InputStream in = uri.openStream();
                FileOutputStream fo = new FileOutputStream(new File(f.getAbsolutePath() + "/" + imageName));
                byte[] buf = new byte[1024];
                int length = 0;

                System.out.println("开始下载:" + url);
                while ((length = in.read(buf, 0, buf.length)) != -1) {
                    fo.write(buf, 0, length);
                }
                in.close();
                fo.close();
                System.out.println(imageName + "下载完成");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
