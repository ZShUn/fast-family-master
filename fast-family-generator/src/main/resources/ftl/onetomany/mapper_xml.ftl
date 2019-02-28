<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${className}Mapper">

    <resultMap id="${className?uncap_first}MapperMap" type="${packageName}.entity.${className}">
    <#list masterTableInfo.columnInfoList as column>
        <result column="${column.columnName}" property="${column.columnJavaName}"/>
    </#list>
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="${className?uncap_first}Column">
    <#list masterTableInfo.columnInfoList as column>
        ${column.columnName} AS ${column.columnJavaName}<#if column_has_next>,</#if>
    </#list>
    </sql>

    <select id="custom" resultType="${packageName}.dto.${className}DTO">
        SELECT
        *
        FROM
    ${masterTableInfo.tableName},
    ${slaveTableInfo.tableName}
        WHERE
        1=1
            <#list masterTableInfo.columnInfoList as masterColumn>
                <#list slaveTableInfo.columnInfoList as slaveColumn>
                    <#if masterColumn.columnName == slaveColumn.columnName>
        AND ${masterTableInfo.tableName}.${masterColumn.columnName} = ${slaveTableInfo.tableName}
                        .${slaveColumn.columnName}
                    </#if>
                </#list>
            </#list>
    </select>

</mapper>
