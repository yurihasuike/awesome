
package me.myreco.up.CategoryApi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result {

    private Integer id;
    private String key;
    private String label;
    private Object parent;
    private List<Child> children = new ArrayList<Child>();
    @SerializedName("allow_post")
    private Boolean allowPost;
    private Integer nice;
    private Object url;
    private Object readmoreUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The key
     */
    public String getKey() {
        return key;
    }

    /**
     * 
     * @param key
     *     The key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 
     * @return
     *     The label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label
     *     The label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 
     * @return
     *     The parent
     */
    public Object getParent() {
        return parent;
    }

    /**
     * 
     * @param parent
     *     The parent
     */
    public void setParent(Object parent) {
        this.parent = parent;
    }

    /**
     * 
     * @return
     *     The children
     */
    public List<Child> getChildren() {
        return children;
    }

    /**
     * 
     * @param children
     *     The children
     */
    public void setChildren(List<Child> children) {
        this.children = children;
    }

    /**
     * 
     * @return
     *     The allowPost
     */
    public Boolean getAllowPost() {
        return allowPost;
    }

    /**
     * 
     * @param allowPost
     *     The allow_post
     */
    public void setAllowPost(Boolean allowPost) {
        this.allowPost = allowPost;
    }

    /**
     * 
     * @return
     *     The nice
     */
    public Integer getNice() {
        return nice;
    }

    /**
     * 
     * @param nice
     *     The nice
     */
    public void setNice(Integer nice) {
        this.nice = nice;
    }

    /**
     * 
     * @return
     *     The url
     */
    public Object getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(Object url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The readmoreUrl
     */
    public Object getReadmoreUrl() {
        return readmoreUrl;
    }

    /**
     * 
     * @param readmoreUrl
     *     The readmore_url
     */
    public void setReadmoreUrl(Object readmoreUrl) {
        this.readmoreUrl = readmoreUrl;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
