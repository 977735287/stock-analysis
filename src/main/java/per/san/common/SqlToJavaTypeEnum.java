package per.san.common;

public enum SqlToJavaTypeEnum {
    VARCHAR("String"),
    CHAR("String"),
    BLOB("byte[]"),
    TEXT("String"),
    INT("Integer"),
    INTEGER("Integer"),
    TINYINT("Integer"),
    SMALLINT("Integer"),
    MEDIUMINT("Integer"),
    BIT("Boolean"),
    BIGINT("Long"),
    FLOAT("Float"),
    DOUBLE("Double"),
    DECIMAL("Double"),
    DATE("Date"),
    DATETIME("Date"),
    TIME("Date"),
    TIMESTAMP("Date"),
    LONGTEXT("String"),
    TINYTEXT("String");

    private final String javaType;

    SqlToJavaTypeEnum(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaType(){
        return javaType;
    }
}
