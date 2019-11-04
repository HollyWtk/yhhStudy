package study.skr.yhh.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.FolderJob;
import com.offbytwo.jenkins.model.JobWithDetails;

import lombok.extern.slf4j.Slf4j;
import study.skr.yhh.enums.AppType;
import study.skr.yhh.service.jenkins.JenkinsConfig;
import study.skr.yhh.util.JobXmlUtil;

/**  
 * <p>Description: </p>  
 * @author yhh  
 * @date 2019年11月4日  
 */
@RestController
@RequestMapping("/jenkins")
@Slf4j
public class JenkinsBackController {
    
    @Autowired
    private JenkinsConfig jenkinsConfig;

    @GetMapping("/backUrl")
    public void jenkinsBack() {
        System.out.println(jenkinsConfig.getBackUrlHome());
    }
    
    @GetMapping("createJob")
    public void createJenkinsJob() {
        try {
            String projectName = "yhh";
            String appName = "yhhApp";
            JenkinsServer jenkinsServer = jenkinsConfig.getJenkinsServer();
            JobWithDetails folderJob = jenkinsServer.getJob(projectName);
            if(folderJob == null) {
                jenkinsServer.createFolder("yhh");
                folderJob = jenkinsServer.getJob("yhh");
            }
            FolderJob fj = new FolderJob(projectName, folderJob.getUrl());
            
            JobWithDetails jobDetails = jenkinsServer.getJob(fj, appName);
            if (jobDetails == null) {
                String jobxml = JobXmlUtil.setSetScriptAndGetString(jenkinsConfig.getJobxmlTemplateFile(),AppType.JAVA_WEB.getScriptFile());
                jenkinsServer.createJob(fj, appName, jobxml);
            } else {
                log.warn("Jenkins任务“" + appName + "”在目录“" + projectName + "”中已存在。");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
}
