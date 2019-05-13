package per.san.common.utils.page;

import java.util.List;

/**
 * description: Select
 *
 * @author shencai.huang@hand-china.com
 * @date 12/13/2018 12:46
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/13/2018
 */
@FunctionalInterface
public interface Select<E> {
    List<E> doSelect();
}
