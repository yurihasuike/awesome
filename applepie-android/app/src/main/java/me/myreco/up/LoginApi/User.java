
package me.myreco.up.LoginApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private String name;
    private String description;
    private List<String> renders = new ArrayList<String>();
    private List<String> parses = new ArrayList<String>();
    private Actions actions;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The renders
     */
    public List<String> getRenders() {
        return renders;
    }

    /**
     * 
     * @param renders
     *     The renders
     */
    public void setRenders(List<String> renders) {
        this.renders = renders;
    }

    /**
     * 
     * @return
     *     The parses
     */
    public List<String> getParses() {
        return parses;
    }

    /**
     * 
     * @param parses
     *     The parses
     */
    public void setParses(List<String> parses) {
        this.parses = parses;
    }

    /**
     * 
     * @return
     *     The actions
     */
    public Actions getActions() {
        return actions;
    }

    /**
     * 
     * @param actions
     *     The actions
     */
    public void setActions(Actions actions) {
        this.actions = actions;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
