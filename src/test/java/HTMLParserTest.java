import Utils.HTMLParser;
import org.jsoup.nodes.Document;

public class HTMLParserTest {
    public static void main(String[] args){
        HTMLParser h=new HTMLParser("https://issues.apache.org/jira/secure/BrowseProjects.jspa?selectedCategory=all&selectedProjectType=software");
        if(h.isParsed()){
            Document d=h.getDocument();
            System.out.println(d.nodeName());
        }
    }
}
