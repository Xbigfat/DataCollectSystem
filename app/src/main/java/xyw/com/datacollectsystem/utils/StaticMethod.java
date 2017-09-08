package xyw.com.datacollectsystem.utils;

import java.util.regex.Pattern;

/**
 * Created by 31429 on 2017/9/8.
 */

public class StaticMethod {

    /**
     * isIPv4Address() 方法验证传入的字符串是否为合法的 IPv4 地址
     */
    private static final Pattern IPV4_PATTERN = Pattern.compile("^(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}$");

    public static boolean isIPv4Address(final String input) {
        return IPV4_PATTERN.matcher(input).matches();
    }
}
