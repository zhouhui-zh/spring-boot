package com.hui.springboot.one;

import sun.swing.StringUIClientPropertyKey;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IpAddressUtils {

    public static void main(String[] args) {
        //System.out.println( getIpAddress());
        System.out.println(getLocalAddress());
    }

    public static String getIpAddress() {
        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();

                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            if (sb.length() == 0) {

                                sb.append(ip.getHostAddress() + "\n");
                            }
                            System.out.println(ip.getHostAddress());
                            ;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("IP地址获取失败" + e.toString());
        }
        return sb.toString();
    }

    public static String getLocalAddress() {

        StringBuilder sb = new StringBuilder();
        try {
            Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();//获取本地所有网络接口
            while (en.hasMoreElements()) {//遍历枚举中的每一个元素
                NetworkInterface ni = en.nextElement();
                Enumeration<InetAddress> enumInetAddr = ni.getInetAddresses();
                while (enumInetAddr.hasMoreElements()) {
                    InetAddress inetAddress = enumInetAddr.nextElement();
                    // 排除loopback回环类型地址和链路本地地址
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()
                            && inetAddress.isSiteLocalAddress()) {
                        sb.append("name:" + inetAddress.getHostName().toString() + "\t");
                        sb.append("ip:" + inetAddress.getHostAddress().toString() + "\n");

                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
