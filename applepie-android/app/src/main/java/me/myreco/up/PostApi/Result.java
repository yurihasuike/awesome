
package me.myreco.up.PostApi;

import java.util.HashMap;
import java.util.Map;

public class Result {

    private Integer id;
    private Category category;
    private Author author;
    private String body;
    private Medium medium;
    private Boolean isLiked;
    private Integer cntComments;
    private Integer cntLikes;
    private String createAt;
    private String updateAt;
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
     *     The category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * 
     * @param author
     *     The author
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * 
     * @return
     *     The body
     */
    public String getBody() {
        return body;
    }

    /**
     * 
     * @param body
     *     The body
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * 
     * @return
     *     The medium
     */
    public Medium getMedium() {
        return medium;
    }

    /**
     * 
     * @param medium
     *     The medium
     */
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    /**
     * 
     * @return
     *     The isLiked
     */
    public Boolean getIsLiked() {
        return isLiked;
    }

    /**
     * 
     * @param isLiked
     *     The is_liked
     */
    public void setIsLiked(Boolean isLiked) {
        this.isLiked = isLiked;
    }

    /**
     * 
     * @return
     *     The cntComments
     */
    public Integer getCntComments() {
        return cntComments;
    }

    /**
     * 
     * @param cntComments
     *     The cnt_comments
     */
    public void setCntComments(Integer cntComments) {
        this.cntComments = cntComments;
    }

    /**
     * 
     * @return
     *     The cntLikes
     */
    public Integer getCntLikes() {
        return cntLikes;
    }

    /**
     * 
     * @param cntLikes
     *     The cnt_likes
     */
    public void setCntLikes(Integer cntLikes) {
        this.cntLikes = cntLikes;
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

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
