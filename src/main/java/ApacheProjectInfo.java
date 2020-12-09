import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.net.URL;

public class ApacheProjectInfo {
    private String projectName;
    private String projectKey;

    private String url;

    private String projectID;
    private String repoURL;
    private String issueTrackerName;
    private String issueTrackerID;
    private String bugFixRegex;


    public ApacheProjectInfo(String projectKey,String projectName){
        this.projectName=projectName;
        this.projectKey=projectKey;
    }

    private boolean validProject(){
        String searchURL="https://github.com/search?q="+projectName.replace(" ","+");
        HTMLParser hp=new HTMLParser(searchURL);
        if(!hp.isParsed()) return false;
        Document document=hp.getDocument();
        Elements elements = document.getElementsByClass("repo-list-item hx_hit-repo d-flex flex-justify-start py-4 public source");
        if(elements.isEmpty()) return false;
        Element element=elements.first();
        Elements linkNodes=element.getElementsByClass("v-align-middle");
        if(linkNodes.isEmpty()) return false;
        Element linkNode = linkNodes.first();
        String searchedProjectName=processName(linkNode.text());
        String solvedProjectName=processName(projectName);
        //判断项目名是否一致
        if(searchedProjectName.length()-searchedProjectName.lastIndexOf(solvedProjectName)!=solvedProjectName.length()) return false;
        url="https://github.com"+linkNode.attributes().get("href");
        projectName=linkNode.attributes().get("href").replaceAll("/","-");
        projectName=projectName.substring(1,projectName.length());

        Elements labelNodes=element.getElementsByAttributeValue("itemprop","programmingLanguage");
        if(labelNodes.isEmpty()) return false;
        if(!labelNodes.get(0).text().equals("Java")) return false;
        return true;
    }

    private String processName(String name){
        name = name.replaceAll("_","");
        name = name.replaceAll("-","");
        name = name.replaceAll(" ","");
        return name.toLowerCase().replace("apache","");
    }

    public boolean parseInfo(){
        if(!validProject()) return false;
        projectID=projectKey;
        repoURL=url+".git";
        issueTrackerName="jira";
        issueTrackerID=projectKey;
        bugFixRegex="/("+projectKey+"-\\d+)/mi";
//        System.out.println(projectID);
//        System.out.println(projectName);
//        System.out.println(repoURL);
//        System.out.println(issueTrackerName);
//        System.out.println(issueTrackerID);
//        System.out.println(bugFixRegex);
        return true;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getProjectID() {
        return projectID;
    }

    public String getRepoURL() {
        return repoURL;
    }

    public String getIssueTrackerName() {
        return issueTrackerName;
    }

    public String getIssueTrackerID() {
        return issueTrackerID;
    }

    public String getBugFixRegex() {
        return bugFixRegex;
    }
}
