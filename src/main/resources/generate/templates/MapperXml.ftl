<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${packageName}.mapper.${table.className}Mapper" >

    <delete id="deleteBatch">
        delete from ${table.tableName}
        <where>
            ${table.pk.columnName} in
            <foreach collection="ids" item="id" open="(" close=")" separator=",">
                ${r'#{id}'}
            </foreach>
        </where>
    </delete>
</mapper>
