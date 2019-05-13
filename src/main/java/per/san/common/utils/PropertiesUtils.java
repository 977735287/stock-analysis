package per.san.common.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * description:
 *
 * @author shencai.huang@hand-china.com
 * @date 12/18/2018 12:58
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/18/2018
 */
public class PropertiesUtils {

    private Properties prop;

    public PropertiesUtils(String filePath) {
        prop = new Properties();
        try {
            prop.load(PropertiesUtils.class.getClassLoader().getResourceAsStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        return prop.getProperty(key);
    }
}
