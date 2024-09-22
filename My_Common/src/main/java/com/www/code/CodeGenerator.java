package com.www.code;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * <p>
 * mysql 代码生成器
 * </p>
 */
public class CodeGenerator {


    public static final String OUTPUTDIR = "D://test";

    public static final String TABLES = "system_log_0";

    public static final String PARENT = "com.www";

    public static final String USERNAME = "root";
    public static final String PASSWORD = "55555wyt";
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://192.168.192.192:3306/my_project?useUnicode=true&useSSL=false&characterEncoding=utf8";


    /**
     * 运行启动
     */
    public static void main(String[] args) {
        test1();
    }


    public static void test1() {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        // 是否支持AR模式
        config.setActiveRecord(true)
                // 作者
                .setAuthor("夕颜")
                // 生成路径，最好使用绝对路径
                .setOutputDir(OUTPUTDIR + "/src/main/java/")
                // 文件覆盖
                .setFileOverride(true)
                // 主键策略
                .setIdType(IdType.AUTO)

                //实体类结尾名称
                .setEntityName("%sEntity")
                //实体命名方式  默认值：null 例如：%sEntity 生成 UserEntity
                .setEntityName("%sEntity")
                //mapper 命名方式 默认值：null 例如：%sDao 生成 UserDao
                .setMapperName("%sMapper")
                //Mapper xml 命名方式   默认值：null 例如：%sDao 生成 UserDao.xml
                .setXmlName("%sMapper")
                //service 命名方式   默认值：null 例如：%sBusiness 生成 UserBusiness
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")

                //生成基本的resultMap
                .setBaseResultMap(true)

                //不使用AR模式
                .setActiveRecord(false)

                //生成基本的SQL片段
                .setBaseColumnList(true);

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        // 设置数据库类型
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName(DRIVER_NAME)
                .setUrl(URL)
                .setUsername(USERNAME)
                .setPassword(PASSWORD);

        //3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();

        //全局大写命名
        stConfig.setCapitalMode(true)
                // 数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                //  数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
                .setColumnNaming(NamingStrategy.underline_to_camel)

                //使用lombok
                .setEntityLombokModel(true)

                //使用restcontroller注解
                .setRestControllerStyle(true)

                // 生成的表, 支持多表一起生成，以数组形式填写
                .setInclude(TABLES)
                // 排除生成的表
                //.setExclude(new String[]{"test"})

                //驼峰转连字符
                .setControllerMappingHyphenStyle(true);

        // 4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(PARENT)
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller")
                .setEntity("pojo")
                .setXml("mappers");

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig);

        //6. 执行操作
        ag.execute();
        System.out.println("=======  Done 相关代码生成完毕  ========");

    }

}