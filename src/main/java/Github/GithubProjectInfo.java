package Github;

import org.kohsuke.github.GHRepository;

public class GithubProjectInfo {
    private String projectName;
    private String projectID;
    private String repoURL;
    private String issueTrackerName;
    private String issueTrackerID;
    private String bugFixRegex;

    public GithubProjectInfo(GHRepository repo){
        projectID=repo.getName().toUpperCase();
        projectName=repo.getName();
        repoURL=repo.getHttpTransportUrl();
        issueTrackerName="github";
        issueTrackerID=repo.getFullName();
        bugFixRegex="/Fix(?:es)?\\s*#(\\d+)/mi";
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
