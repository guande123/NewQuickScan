package com.gdz.demo.gdnews.utils;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class Constant {
    public static final String ADDR = "http://v.juhe.cn/toutiao/index";
    public static  final String SCRECT_KEY ="285117f66d7f35bbd2df9fbc21015cef";//openId
    //  类型,,top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
    public static  final String[] titles = new String[]{"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
    public static final String TOP = "top";
    public static final String SHEHUI = "shehui";
    public static final String GUONEI = "guonei";
    public static final String GUOJI = "guoji";
    public static final String YULE = "yule";

    public static final String TIYU = "tiyu";
    public static final String JUNSHI = "junshi";
    public static final String KEJI = "keji";
    public static final String CAIJING = "caijing";
    public static final String SHISHANG = "shishang";
    public static String matchUri(int type){
        switch (type){
            case 0 :
                return ADDR+"?type="+TOP+"&key="+SCRECT_KEY ;
            case 1 :
                return ADDR+"?type="+SHEHUI+"&key="+SCRECT_KEY ;
            case 2 :
                return ADDR+"?type="+GUONEI+"&key="+SCRECT_KEY ;
            case 3 :
                return ADDR+"?type="+GUOJI+"&key="+SCRECT_KEY ;
            case 4 :
                return ADDR+"?type="+YULE+"&key="+SCRECT_KEY ;
            case 5 :
                return ADDR+"?type="+TIYU+"&key="+SCRECT_KEY ;
            case 6 :
                return ADDR+"?type="+JUNSHI+"&key="+SCRECT_KEY ;
            case 7 :
                return ADDR+"?type="+KEJI+"&key="+SCRECT_KEY ;
            case 8 :
                return ADDR+"?type="+CAIJING+"&key="+SCRECT_KEY ;
            case 9 :
                return ADDR+"?type="+SHISHANG+"&key="+SCRECT_KEY ;
            default:
                return "";
        }
    }
}
