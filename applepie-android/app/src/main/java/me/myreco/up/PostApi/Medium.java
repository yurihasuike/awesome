
package me.myreco.up.PostApi;

import java.util.HashMap;
import java.util.Map;

public class Medium {

    private Integer id;
    private String createAt;
    private String updateAt;
    private String originalFile;
    private Integer originalWidth;
    private Integer originalHeight;
    private String transcodedFile;
    private Integer transcodedWidth;
    private Integer transcodedHeight;
    private String thumbnail;
    private Integer thumbnailWidth;
    private Integer thumbnailHeight;
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
     *     The originalFile
     */
    public String getOriginalFile() {
        return originalFile;
    }

    /**
     * 
     * @param originalFile
     *     The original_file
     */
    public void setOriginalFile(String originalFile) {
        this.originalFile = originalFile;
    }

    /**
     * 
     * @return
     *     The originalWidth
     */
    public Integer getOriginalWidth() {
        return originalWidth;
    }

    /**
     * 
     * @param originalWidth
     *     The original_width
     */
    public void setOriginalWidth(Integer originalWidth) {
        this.originalWidth = originalWidth;
    }

    /**
     * 
     * @return
     *     The originalHeight
     */
    public Integer getOriginalHeight() {
        return originalHeight;
    }

    /**
     * 
     * @param originalHeight
     *     The original_height
     */
    public void setOriginalHeight(Integer originalHeight) {
        this.originalHeight = originalHeight;
    }

    /**
     * 
     * @return
     *     The transcodedFile
     */
    public String getTranscodedFile() {
        return transcodedFile;
    }

    /**
     * 
     * @param transcodedFile
     *     The transcoded_file
     */
    public void setTranscodedFile(String transcodedFile) {
        this.transcodedFile = transcodedFile;
    }

    /**
     * 
     * @return
     *     The transcodedWidth
     */
    public Integer getTranscodedWidth() {
        return transcodedWidth;
    }

    /**
     * 
     * @param transcodedWidth
     *     The transcoded_width
     */
    public void setTranscodedWidth(Integer transcodedWidth) {
        this.transcodedWidth = transcodedWidth;
    }

    /**
     * 
     * @return
     *     The transcodedHeight
     */
    public Integer getTranscodedHeight() {
        return transcodedHeight;
    }

    /**
     * 
     * @param transcodedHeight
     *     The transcoded_height
     */
    public void setTranscodedHeight(Integer transcodedHeight) {
        this.transcodedHeight = transcodedHeight;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The thumbnailWidth
     */
    public Integer getThumbnailWidth() {
        return thumbnailWidth;
    }

    /**
     * 
     * @param thumbnailWidth
     *     The thumbnail_width
     */
    public void setThumbnailWidth(Integer thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    /**
     * 
     * @return
     *     The thumbnailHeight
     */
    public Integer getThumbnailHeight() {
        return thumbnailHeight;
    }

    /**
     * 
     * @param thumbnailHeight
     *     The thumbnail_height
     */
    public void setThumbnailHeight(Integer thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
