import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class MiningProcess {
    public static void main(String[] args){
        ApacheMining am=new ApacheMining("https://issues.apache.org/jira/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=software&sortColumn=name&sortOrder=ascending&s=view_projects");
        if(!am.mining()) return ;
        ArrayList<ApacheProjectInfo> infos=am.getResult();
        int cnt=0,cor=0;
        try {
            BufferedWriter bw=new BufferedWriter(new FileWriter("D:\\defects4j\\step1\\ApacheProjects.txt"));
            for (ApacheProjectInfo info : infos) {
                Thread.sleep(10000);
                System.out.println("------------------------------------------------");
                cnt++;
                System.out.println(cnt);

                if (!info.parseInfo()) {
                    System.out.println(info.getProjectName() + " failed");
                    continue;
                }
                bw.write(info.getProjectID()+"\t"
                        +info.getProjectName()+"\t"
                        +info.getRepoURL()+"\t"
                        +info.getIssueTrackerName()+"\t"
                        +info.getIssueTrackerID()+"\t"
                        +info.getBugFixRegex());
                bw.newLine();
                bw.flush();
                cor++;
                System.out.println(info.getProjectName() + " success");
                info = null;
            }
            bw.close();
            System.out.println("Total Projects: " + cnt + "   Success Projects: " + cor);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
