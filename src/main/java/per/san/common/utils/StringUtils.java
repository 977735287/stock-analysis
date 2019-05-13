package per.san.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * description: String工具类
 *
 * @author shencai.huang@hand-china.com
 * @date 12/18/2018 19:47
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/18/2018
 */
public class StringUtils extends org.springframework.util.StringUtils {

    public static String replaceUnderLine(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while(count!=0){
            int num = sb.indexOf("_",count);
            count = num + 1;
            if(num != -1){
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count , count + 1,ia + "");
            }
        }
        return sb.toString().replaceAll("_","");
    }

    public static String replaceUnderLineAndUpperCase(String str){
        return capitalize(replaceUnderLine(str));
    }

    /**
     * 驼峰法转横线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Crossline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase()
                .concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toLowerCase());
            sb.append(matcher.end() == line.length() ? "" : "-");
        }
        return sb.toString();
    }
}
