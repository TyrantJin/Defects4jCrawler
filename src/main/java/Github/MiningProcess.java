package Github;

import org.kohsuke.github.GHRepository;
import org.kohsuke.github.PagedSearchIterable;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class MiningProcess {
    public static void main(String[] args){
        String[] companies={"google","alibaba","tencent","amazon","microsoft","facebook","intel","ibm","oracle","baidu"};
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\defects4j\\step1\\GithubProjects.txt"));
            int cnt=0;
            for (String company : companies) {
                GithubRepoMining ghrm = new GithubRepoMining(company);
                if (!ghrm.prepared()) {
                    System.out.println(company + "search failed.");
                    continue;
                }
                List<GHRepository> repos = ghrm.getRepos().toList();
                System.out.println(company + " has " + repos.size() + " repos.");
                int index=0;
                for (GHRepository repo : repos) {
                    GithubProjectInfo info = new GithubProjectInfo(repo);
                    bw.write(info.getProjectID()+"\t"
                            +info.getProjectName()+"\t"
                            +info.getRepoURL()+"\t"
                            +info.getIssueTrackerName()+"\t"
                            +info.getIssueTrackerID()+"\t"
                            +info.getBugFixRegex());
                    bw.newLine();
                    cnt++;
                    index++;
                    if(index%50==0) {
                        bw.flush();
                        System.out.println(company+"\t"+(index*1.0/repos.size()));
                        Thread.sleep(10000);
                    }
                }
                bw.flush();
                System.out.println(company+" done.");
            }
            bw.close();
            System.out.println("Total Projects: "+cnt);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
