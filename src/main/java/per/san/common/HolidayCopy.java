package per.san.common;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.collections4.MapUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


/**
 * description:
 *
 * @author Sanchar
 * @date 3/31/2019 19:33
 * lastUpdateBy: Sanchar
 * lastUpdateDate: 3/31/2019 19:33
 */
public class HolidayCopy {
    /**
     * @param
     * @return result(0 工作日 1周末 2节假日)
     * @throws
     * @Description: 入参格式为“yyyyMMdd”如“20181001”
     */
    public static Integer request(String httpArg) {
        //工作日对应结果为 0, 休息日对应结果为 1, 节假日对应的结果为 2
        String httpUrl = "http://api.goseek.cn/Tools/holiday";
        String result = "";
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?date=" + httpArg;
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = MapUtils.getString(jsonStringToMap(String.valueOf(sbf)), "data");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null) {
            return Integer.valueOf(result);
        }
        return -1;
    }

    /**
     * @param
     * @return
     * @throws
     * @Description: JSONString转Map
     */
    private static Map<String, Object> jsonStringToMap(String json) {
        //String转成JSONObject形式
        JSONObject jsonArray = JSONObject.parseObject(json);
        Map<String, Object> param = JSONObject.parseObject(jsonArray.toJSONString(), new TypeReference<Map<String, Object>>() {
        });
        return param;
    }

}
