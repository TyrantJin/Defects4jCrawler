import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLParser {
    private String url;
    private Document document;
    private boolean parsed;
    public HTMLParser(String url){
        this.url = url;
        parsed = false;
        Connection conn = Jsoup.connect(url);
        conn.ignoreHttpErrors(true);
        conn.timeout(30000);
        Connection.Response response;
        try {
            response=conn.execute();
            if(response.statusCode()==200) {
                document = Jsoup.parse(response.body());
                parsed = true;
            }
            else{
                System.out.println("Connet failed. URL : "+url+" "+response.statusCode()+" "+response.statusMessage());
            }
        }catch(Exception e){
            System.out.println("Connet failed. URL : "+url+" Unkonw Exception!");
        }

    }

    public boolean isParsed(){
        return parsed;
    }

    public Document getDocument(){
        return document;
    }
}
