package per.san;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import per.san.common.HolidayCopy;
import per.san.common.utils.HttpRequest;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author Sanchar
 * @date 3/31/2019 19:37
 * lastUpdateBy: Sanchar
 * lastUpdateDate: 3/31/2019 19:37
 */
public class Test {
    public static void main(String[] args) {
        String url = "http://vip.stock.finance.sina.com.cn/quotes_service/api/json_v2.php/Market_Center.getHQNodeData";
        String param = "page=1&num=20&sort=changepercent&asc=1&node=hs_a&symbol=&_s_r_a=setlen";
        String s = HttpRequest.get(param, url);
        Gson gson = new Gson();
        List<Map<String, Object>> maps = gson.fromJson(s, new TypeToken<List<Map<String, Object>>>(){}.getType());
        System.out.println(maps);

//        HolidayCopy holidayCopy = new HolidayCopy();
//        //判断今天是否是工作日 周末 还是节假日
//        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
//        String httpArg = "20190404";
//        int jsonResult = holidayCopy.request(httpArg);
//        System.out.println(httpArg + " = " + jsonResult);
    }
}
