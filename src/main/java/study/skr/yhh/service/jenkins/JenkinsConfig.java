package study.skr.yhh.service.jenkins;

import java.io.IOException;
import java.net.URI;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.offbytwo.jenkins.JenkinsServer;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
@ConfigurationProperties(prefix = "jenkins")
@Configuration
public class JenkinsConfig {

    @Value("url")
    private String url;

    @Value("wfapi")
    private String wfapi;

    @Value("username")
    private String username;

    @Value("password-or-token")
    private String passwordOrToken;

    @Value("jobxml-template-file")
    private String jobxmlTemplateFile;

    @Value("back-url-home")
    private String backUrlHome;

    private String backUrlRoot = "/mng/account";

    @Value("credentials-id")
    private String credentialsId;

    /*@Value("gitlab-ssh-port")
    private int gitlabSshPort;*/
    
    private JenkinsServer jenkinsServer;
    
    @PostConstruct
    public void init() {
        jenkinsServer = new JenkinsServer(URI.create(url), username, passwordOrToken);
        log.info("jenkins服务连接成功");
    }
}
