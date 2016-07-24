
package me.myreco.up.LoginApi;

import java.util.HashMap;
import java.util.Map;

public class Actions {

    private me.myreco.up.LoginApi.Post POST;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The POST
     */
    public me.myreco.up.LoginApi.Post getPOST() {
        return POST;
    }

    /**
     * 
     * @param POST
     *     The POST
     */
    public void setPOST(me.myreco.up.LoginApi.Post POST) {
        this.POST = POST;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
