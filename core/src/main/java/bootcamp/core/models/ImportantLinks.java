package bootcamp.core.models;

import com.adobe.cq.sightly.WCMUsePojo;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ImportantLinks extends WCMUsePojo {
    List<HashMap<String,Object>> linkDetails = new ArrayList<>();
    @Override
    public void activate() throws Exception {
        Resource currentResource = getResource();

        Resource childResource = currentResource.getChild("linkdetails");
        for (Resource child : childResource.getChildren()) {
            ValueMap childValueMap = child.getValueMap();
            HashMap<String,Object> hashmap = new HashMap<>(childValueMap);
            String linkStr = (String) hashmap.get("link");
            ResourceResolver resourceResolver = getResourceResolver();
            if(resourceResolver.getResource(linkStr)!=null){
                linkStr += ".html";
                hashmap.put("link",linkStr);
            }
            linkDetails.add(hashmap);
        }
    }

    public List<HashMap<String, Object>> getLinkDetails() {
        return linkDetails;
    }
}
