import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;

public class ApacheMining {
    private Document document;
    private boolean flag;
    private ArrayList<ApacheProjectInfo> apacheProjectInfo;

    public ApacheMining(String url){
        HTMLParser hp=new HTMLParser(url);
        flag=false;
        if(hp.isParsed()) {
            this.document=hp.getDocument();
            flag=true;
            apacheProjectInfo=new ArrayList<ApacheProjectInfo>();
        }
    }

    public boolean mining(){
        if(!flag) return false;
        String data = findData();
        if(data.equals("")) return false;
        parseData(data);
        for(ApacheProjectInfo info : apacheProjectInfo){

        }
        return true;
    }

    private String findData (){
        for(Element e : document.getElementsByTag("script")){
            String code=e.data().toString();
            int pos=code.indexOf("WRM._unparsedData[\"com.atlassian.jira.project.browse:projects\"]");
            if(pos!=-1){
                return code.substring(pos,code.indexOf("RM._unparsedData[\"com.atlassian.jira.project.browse:projectTypes\"]")-1);
            }
        }
        return "";
    }

    private void parseData(String data){
        int l=data.indexOf("{"),r=data.indexOf("}");
        while(l!=-1){
            String item = data.substring(l+1,r);
            String[] elements=item.split(",");
            String key="",name="";
            for(String e : elements){
                String field=e.split(":")[0];
                String value=e.split(":")[1];
                if(field.equals("\\\"key\\\"")){
                    key=value.replaceAll("\\\\\"","");
                }
                if(field.equals("\\\"name\\\"")){
                    name=value.replaceAll("\\\\\"","");
                }
            }

            apacheProjectInfo.add(new ApacheProjectInfo(key,name));
            data=data.substring(r+1,data.length());
            l=data.indexOf("{");
            r=data.indexOf("}");
        }
    }

    public ArrayList<ApacheProjectInfo> getResult(){
        return apacheProjectInfo;
    }
}
