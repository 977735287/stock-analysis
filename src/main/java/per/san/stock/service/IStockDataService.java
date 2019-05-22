package per.san.stock.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author Sanchar
 * @date 5/21/2019 19:49
 * lastUpdateBy: Sanchar
 * lastUpdateDate: 5/21/2019 19:49
 */
public interface IStockDataService {

    /**
     * description:
     * @param response
     * @param start
     * @param end
     * @return 数据对象
     */
    void downloadHistoryInfo(HttpServletResponse response, String start, String end) throws IOException;

    /**
     * description:
     * @return 数据
     */
    Map<String, List<String>> getMarketIndex();

    /**
     * description:
     * @return 数据
     */
    Map<String, Object> getMinuteData(String code, String type);
}
