public class ApacheMiningTest {
    public static void main(String[] args){
        ApacheMining am=new ApacheMining("https://issues.apache.org/jira/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=software");
        am.mining();
    }
}
