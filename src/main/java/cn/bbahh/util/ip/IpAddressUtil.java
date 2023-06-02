package cn.bbahh.util.ip;

import cn.bbahh.entity.ip.IpEntity;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IpAddressUtil {
    public static List<IpEntity> getIpAddress() throws SocketException {
        Enumeration<NetworkInterface> address = NetworkInterface.getNetworkInterfaces();
        List<IpEntity> result = new ArrayList();

        while(address.hasMoreElements()) {
            NetworkInterface networkInterface = (NetworkInterface)address.nextElement();
            if (networkInterface.isUp() && !networkInterface.isLoopback() && !networkInterface.isVirtual()) {
                List<InterfaceAddress> list = networkInterface.getInterfaceAddresses();
                list.forEach((item) -> {
                    if (item.getAddress() instanceof Inet4Address) {
                        IpEntity entity = new IpEntity();
                        entity.setName(getName(networkInterface.getDisplayName()));
                        entity.setIpaddress(item.getAddress().getHostAddress());
                        entity.setMask(getMask(item.getNetworkPrefixLength()));
                        entity.setGateway(getGateway(networkInterface.getDisplayName()));
                        entity.setDns(getDns(networkInterface.getDisplayName()));
                        result.add(entity);
                    }

                });
            }
        }

        return result;
    }

    private static String getGateway(String name) {
        try {
            String cmd = "nmcli device show " + name + " | grep IP4.GATEWAY";
            String result = CommandUtil.runCommand(cmd);
            String[] results = result.split("\\s+");
            System.out.println(cmd);
            System.out.println(result);
            return results.length > 0 ? results[1] : "";
        } catch (InterruptedException | IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    private static String getName(String name) {
        try {
            String cmd = "nmcli device show " + name + " | grep GENERAL.CONNECTION";
            String result = CommandUtil.runCommand(cmd);
            String[] results = result.split("\\s+");
            System.out.println(cmd);
            System.out.println(result);
            return results.length > 0 ? results[1] : "";
        } catch (InterruptedException | IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    private static String getDns(String name) {
        try {
            String cmd = "nmcli device show " + name + " | grep IP4.DNS";
            String result = CommandUtil.runCommand(cmd);
            String[] results = result.split("\\s+");
            System.out.println(cmd);
            System.out.println(result);
            return results.length > 0 ? results[1] : "";
        } catch (InterruptedException | IOException var4) {
            throw new RuntimeException(var4);
        }
    }

    private static String getMask(short code) {
        long bits = -(1L << 32 - code);
        return String.format("%d.%d.%d.%d", (bits & 4278190080L) >> 24, (bits & 16711680L) >> 16, (bits & 65280L) / 256L, bits & 255L);
    }

    private static int calcPrefixLengthByMack(String strip) {
        int count = 0;
        String[] ipList = strip.split("\\.");
        StringBuffer sbf = new StringBuffer();
        String[] var5 = ipList;
        int var6 = ipList.length;

        int var7;
        for(var7 = 0; var7 < var6; ++var7) {
            String s = var5[var7];
            String binData = toBin(Integer.parseInt(s));
            sbf.append(binData);
        }

        String str = sbf.toString();
        boolean hasZero = false;
        char[] var11 = str.toCharArray();
        var7 = var11.length;

        for(int var12 = 0; var12 < var7; ++var12) {
            char c = var11[var12];
            if (c == '1') {
                if (hasZero) {
                    throw new RuntimeException("子网掩码不合法");
                }

                ++count;
            } else if (c == '0') {
                hasZero = true;
            }
        }

        return count;
    }

    private static String toBin(int x) {
        return Integer.toBinaryString(x);
    }

    public static void setIp(IpEntity entity) throws IOException, InterruptedException {
        String name = entity.getName();
        String ipaddress = entity.getIpaddress();
        String gateway = entity.getGateway();
        String dns = entity.getDns();
        String mask = entity.getMask();
        StringBuffer buffer = new StringBuffer();
        buffer.append("sudo nmcli connection modify ");
        buffer.append(name);
        buffer.append(" ipv4.method manual ");
        if (isIP(ipaddress)) {
            throw new RuntimeException("IP地址不合法");
        } else {
            buffer.append("ipv4.addresses ").append(ipaddress).append("/").append(calcPrefixLengthByMack(mask)).append(" ");
            if (isIP(gateway)) {
                throw new RuntimeException("网关不合法");
            } else {
                buffer.append("ipv4.gateway ").append(gateway).append(" ");
                buffer.append("connection.autoconnect yes ");
                if (isIP(dns)) {
                    throw new RuntimeException("网关不合法");
                } else {
                    buffer.append("ipv4.dns ").append(dns);
                    System.out.println(buffer);
                    CommandUtil.runCommand(buffer.toString());
                    String secondCmd = "sudo nmcli connection up " + entity.getName();
                    CommandUtil.runCommand(secondCmd);
                }
            }
        }
    }

    private static boolean isIP(String ip) {
        String maskReg = "^(((1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9]).){1}((1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d).){2})((1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)/(?:[1-9]|[12][0-9]|3[012])){1}$";
        Pattern ipPattern = Pattern.compile(maskReg);
        Matcher matcher = ipPattern.matcher(ip);
        return !matcher.matches();
    }

    private static String to8LengthBinString(int ip) {
        StringBuilder data = new StringBuilder(Integer.toBinaryString(ip));

        while(data.length() < 8) {
            data.insert(0, "0");
        }

        return data.toString();
    }

    private static String getIpBin(String ip) {
        String data = (String) Arrays.stream(ip.split("\\.")).map((item) -> {
            return to8LengthBinString(Integer.parseInt(item));
        }).collect(Collectors.joining());
        return data;
    }
}
