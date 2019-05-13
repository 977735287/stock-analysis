package per.san.common.utils.page;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * description:
 *
 * @author shencai.huang@hand-china.com
 * @date 12/18/2018 19:47
 * lastUpdateBy: shencai.huang@hand-china.com
 * lastUpdateDate: 12/18/2018
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomPageRequest {
    ApiImplicitParams apiImplicitParams() default @ApiImplicitParams({@ApiImplicitParam(
            name = "page",
            value = "page",
            dataType = "string",
            paramType = "query",
            defaultValue = "0"
    ), @ApiImplicitParam(
            name = "size",
            value = "size",
            dataType = "string",
            paramType = "query",
            defaultValue = "20"
    ), @ApiImplicitParam(
            name = "sort",
            value = "sort",
            allowMultiple = true,
            dataType = "string",
            paramType = "query"
    )});
}
