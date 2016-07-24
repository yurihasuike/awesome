
package me.myreco.up.CategoryApi;

import java.util.HashMap;
import java.util.Map;

public class Parent {

    private Integer id;
    private String key;
    private String label;
    private Integer nice;
    private Boolean allowPost;
    private Object url;
    private Object readmoreUrl;
    private String createAt;
    private String updateAt;
    private Object parent;
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

    /**
     * 
     * @return
     *     The createAt
     */
    public String getCreateAt() {
        return createAt;
    }

    /**
     * 
     * @param createAt
     *     The create_at
     */
    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    /**
     * 
     * @return
     *     The updateAt
     */
    public String getUpdateAt() {
        return updateAt;
    }

    /**
     * 
     * @param updateAt
     *     The update_at
     */
    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
