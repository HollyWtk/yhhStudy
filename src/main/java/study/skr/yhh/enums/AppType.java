package study.skr.yhh.enums;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 应用类型枚举类
 * 
 */
public enum AppType{
    /** 自研web应用 */
    JAVA_WEB(0, "SpringBoot应用", "jenkins/springboot-jar/pipelineScript-springboot-jar", "/etc/java", "application.yml"),
    /** 自研sdk应用 */
    JAVA_SDK(1, "自研sdk应用", "jenkins/javasdk-jar/pipelineScript-javasdk-jar"),

    /** Nodejs前端应用 */
    NODEJS(2, "Nodejs前端应用", "jenkins/nginx-nodejs/pipelineScript-nginx-nodejs", "/etc/nginx", "nginx.conf"),

    /** js前端应用 */
    JS(3, "js前端应用", "jenkins/nginx-js/pipelineScript-nginx-js", "/etc/nginx", "nginx.conf"),

    /** js前端应用 */
    JAVA_WAR(4, "SpringMVC war包应用", "jenkins/tomcat-war/pipelineScript-tomcat-war", "/opt/tomcat/webapps",
            "applicationContext.xml"),
    /** odm应用 */
    ODM(99, "odm应用");

    private static Map<Integer, AppType> cache;

    static {
        cache = Stream.of(AppType.values()).collect(Collectors.toMap(AppType::getType, Function.identity()));
    }

    private int type;

    private String description;

    private String scriptFile;

    private String configFilePath;

    private String configFileName;

    private AppType(int type, String description) {
        this.type = type;
        this.description = description;
    }

    private AppType(int type, String description, String scriptFile) {
        this.type = type;
        this.description = description;
        this.scriptFile = scriptFile;
    }

    private AppType(int type, String description, String scriptFile, String configFilePath, String configFileName) {
        this.type = type;
        this.description = description;
        this.scriptFile = scriptFile;
        this.configFileName = configFileName;
        this.configFilePath = configFilePath;
    }

    public int getType() {
        return this.type;
    }

    public static AppType enumOf(int type) {
        return cache.get(type);
    }

    public String getScriptFile() {
        return scriptFile;
    }

    public String getConfigFileName() {
        return configFileName;
    }

    public String getConfigFilePath() {
        return configFilePath;
    }
}
