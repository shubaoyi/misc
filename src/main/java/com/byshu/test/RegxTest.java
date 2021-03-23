package com.byshu.test;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc
 * @author byshu
 */
public class RegxTest {

    public static void main(String[] args) {
        // 反向引用
        String str = "hello1hello1";
        //System.out.println(str.matches("(hello\\d?)\\1"));

        // group
        Pattern p = Pattern.compile("(hello1)\\1");
        Matcher m = p.matcher("hello1hello1");
        //System.out.println(m.find());
        //System.out.println(m.group());

        // 预查不消耗字符（只做判断 即使判断成功字符也不纳入匹配结果）
        // 正向肯定预查 (?=pattern)
//        System.out.println("(?=b) : " + "abc".matches("a(?=b)bc"));
//
//        System.out.println("(b) : " + "abc".matches("a(b)bc"));
//        // 正向否定预查 (?!pattern)
//        System.err.println("(?!b) : " + "abc".matches("a(?!b)bc"));

        // 反向肯定预查 (?<=pattern)
//        System.out.println("(?<=b) : " + "abc".matches("a(?<=a)bc"));
//        System.out.println("(a) : " + "abc".matches("a(a)bc"));
//        // 反向否定预查 (?<!pattern)
//        System.err.println("(?<!a) : " + "abc".matches("a(?<!a)bc"));

        // 非获取匹配 (?:pattern) 不会被保存，不能在反向引用中获取

        String url = "search.do?uspm=1.1.1_V2014.102.3633514.1&srcid=searchkeywords_s&keywords=3633514&receiveProvinceId=310000";
//        System.err.println(url.matches("^.+searchkeywords_\\d*&.*$"));
//        System.err.println(url.matches("^.+searchkeywords_(?!\\d*&)[a-zA-Z0-9\u4E00-\u9FA5]+&.*$"));

        String url2 = "1111&22&33&44&";
//        System.err.println(url2.matches("(\\d*&)+"));
//        System.out.println(url.replaceAll("(.+&)keywords=", "").replaceAll("&.*", ""));
//        Pattern p1 = Pattern.compile("(\\d*&)+?");
//        Matcher m1 = p1.matcher(url2);
//        System.out.println(m1.groupCount());
//        System.out.println(m1.find());
//        System.out.println(m1.group(1));

        // 匹配非纯数字和中文字母数字组合
//        System.out.println("123a".matches("(?!\\d*$)[a-zA-Z0-9\u4E00-\u9FA5]{2,12}"));
//        System.out.println("啊".getBytes("UTF-8").length);

        //System.err.println(",".matches("^(?!,)[\\d,]+(?<!,)$"));
    }
}

