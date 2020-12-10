package Github;

import org.kohsuke.github.*;

public class GithubRepoMining {
    private boolean flag;
    private PagedSearchIterable<GHRepository> repos;

    public GithubRepoMining(String key){
        flag=false;
        try {
            GitHub github = new GitHubBuilder().withOAuthToken("1517286ea9c711ae25ee43db5ad20b4712989f83").build();
            GHRepositorySearchBuilder ghrsb = github.searchRepositories();
            ghrsb = ghrsb.q(key);
            ghrsb = ghrsb.language("Java");
            ghrsb = ghrsb.order(GHDirection.DESC);
            ghrsb = ghrsb.sort(GHRepositorySearchBuilder.Sort.STARS);
            repos=ghrsb.list();
            flag=true;
        }catch(Exception e){
            //System.out.println("Github Repo Seaech Error!");
            e.printStackTrace();
        }
    }

    public boolean prepared() { return flag; }

    public PagedSearchIterable<GHRepository> getRepos() { return repos; }
}
