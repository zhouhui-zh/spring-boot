package com.hui.springboot.mysql.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class Item {
    private static final String pattern = "yyyyMMdd";
    static Map<Integer, String> carNameMap = new HashMap<>();
    static Map<Integer, Integer> chuzhangriMap = new HashMap<>();
    static Map<Integer, Integer> huankuanDayMap = new HashMap<>();
    static Map<Integer, Integer> zongEduMap = new HashMap<>();

    static {
        carNameMap.put(1, "中原");
        carNameMap.put(2, "广发");
        carNameMap.put(3, "华夏");

        chuzhangriMap.put(1, 10);
        chuzhangriMap.put(2, 6);
        chuzhangriMap.put(3, 22);

        huankuanDayMap.put(1, 25);
        huankuanDayMap.put(2, 20);
        huankuanDayMap.put(3, 20);

        zongEduMap.put(1, 35000);
        zongEduMap.put(2, 80000);
        zongEduMap.put(3, 32000);
    }


    /**
     * @param type 交易卡类型 1中原，2广发，3华夏
     * @param time 交易时间  yyyyMMdd
     * @param num  交易金额
     */
    private static void zhichu(Integer type, String time, Integer num) throws Exception {
        int shengyuedu = zongEduMap.get(type) - num;
        zongEduMap.put(type, shengyuedu);
        String str = getHuankuanDay(type, time, num, shengyuedu);

    }

    /**
     * 获取还款时间
     *
     * @param type 交易卡类型  1中原，2广发，3华夏
     * @param time 交易日期 yyyyMMdd
     * @return
     */
    private static String getHuankuanDay(Integer type, String time, int num, int shengyuedu) throws Exception {
        String carname = carNameMap.get(type);
        int chuzhang = chuzhangriMap.get(type);
        int huankuanzuida = huankuanDayMap.get(type);

        int day = getDayWithMonth(time);
        int day2 = 0;
        String chuzhang2 = "";
        if (day == chuzhang) {//交易日期等于出账日期，则为最小
            day2 = huankuanzuida;
        } else if (day < chuzhang) {//交易日期等于出账日期，则为最小
            day2 = chuzhang - day + huankuanzuida;
        } else {//交易日期大于出账日期，则为下次出账单+
            int nextChuZhangRiDay = getNextChuZhangRiDays(time, type);
            day2 = nextChuZhangRiDay + huankuanzuida;
            chuzhang2 = "出账日期" + getNextChuZhangRi(time, type);

        }


        String huankuanri = gethuankuantime(time, 0, day2);
        System.out.println(carname + "每月" + chuzhang + "出账，交易日期" + time + "\t交易金额" + num + "\t剩余额度" + shengyuedu + chuzhang2 + "\t还款日:" + huankuanri);
        return null;
    }

    private static String subMonth(String date) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.DAY_OF_MONTH, 1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }


    public static int getDayWithMonth(String str) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DATE);
        return day;
    }

    public static String gethuankuantime(String str, int month, int day) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int montht = calendar.get(Calendar.MONTH);
        int dayt = calendar.get(Calendar.DATE);
        calendar.set(Calendar.MONTH, montht + month);
        calendar.set(Calendar.DAY_OF_MONTH, dayt + day);
        return sdf.format(calendar.getTime());
    }

    /**
     * 下次出账单日期
     *
     * @param str
     * @param type
     * @return
     * @throws Exception
     */
    private static String getNextChuZhangRi(String str, Integer type) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int montht = calendar.get(Calendar.MONTH);
        int dayt = calendar.get(Calendar.DATE);
        calendar.set(Calendar.MONTH, montht + 1);
        calendar.set(Calendar.DAY_OF_MONTH, chuzhangriMap.get(type));
        return sdf.format(calendar.getTime());
    }

    private static int getNextChuZhangRiDays(String str, Integer type) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(str);
        Long time1 = date.getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int montht = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, montht + 1);
        calendar.set(Calendar.DAY_OF_MONTH, chuzhangriMap.get(type));
        Long time2 = calendar.getTime().getTime();
        int day = (int) ((time2 - time1) / (3600 * 1000 * 24));
        return day;
    }

    public static int differentDayMillisecond(Date date1, Date date2) {
        int day = (int) ((date2.getTime() - date1.getTime()) / (3600 * 1000 * 24));
        return day;
    }

    /**
     * 获取最近出账单的卡
     *
     * @param cycTime yyyyMMdd
     */
    public static Map getCardType(String cycTime) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(cycTime);
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date);
        int result = 99;
        Integer resultType = null;
        String chuZhangri = null;
        for (Integer type : chuzhangriMap.keySet()) {
            Calendar calendar = Calendar.getInstance();//初始化
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, chuzhangriMap.get(type));
            if (calendar.getTime().getTime() >= date.getTime()) {
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
                //System.out.println("cycTime=" + cycTime + "\t 上月出账" + carNameMap.get(type) + "\t" + sdf.format(calendar.getTime()));

            }
            //得到相差的天数 betweenDate
            long betweenDate = (date.getTime() - calendar.getTime().getTime()) / (60 * 60 * 24 * 1000);
            if (betweenDate < result) {
                resultType = type;
                result = Long.valueOf(betweenDate).intValue();
                chuZhangri = sdf.format(calendar.getTime());
            }
            //System.out.println("cycTime=" + cycTime + "\t  出账" + carNameMap.get(type) + "\t" + sdf.format(calendar.getTime()) + "\t 相差天数 " + betweenDate + "\t " + result);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("type", resultType);
        map.put("chuZhangri", chuZhangri);
        map.put("cycTime", cycTime);
        map.put("days", result);
        map.put("cardName", carNameMap.get(resultType));
        return map;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(JSONObject.toJSONString(getCardType("20201107")));
        System.out.println(JSONObject.toJSONString(getCardType("20201101")));
        System.out.println(JSONObject.toJSONString(getCardType("20201105")));

        System.out.println(JSONObject.toJSONString(getCardType("202011010")));
        System.out.println(JSONObject.toJSONString(getCardType("202011012")));
        System.out.println(JSONObject.toJSONString(getCardType("202011020")));
        System.out.println(JSONObject.toJSONString(getCardType("202011022")));
        System.out.println(JSONObject.toJSONString(getCardType("202011006")));
        System.out.println(JSONObject.toJSONString(getCardType("202011010")));
        System.out.println(JSONObject.toJSONString(getCardType("202011020")));

        System.out.println(subMonth("20200101"));
        System.out.println(getDayWithMonth("20200111"));

        System.out.println(gethuankuantime("20201112", 1, 18));

        zhichu(1, "20201106", 10);
        zhichu(1, "20201110", 55);
        zhichu(1, "20201112", 5000);
        System.out.println();

        zhichu(2, "20201106", 600);
        zhichu(2, "20201105", 2000);
        zhichu(2, "20201112", 18000);
        System.out.println();

        zhichu(3, "20201106", 1000);
        zhichu(3, "20201122", 1000);
        zhichu(3, "20201125", 20000);
    }
}
