
package me.myreco.up.UserInfoApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfo {

    private String email;
    private Integer id;
    private String username;
    private String nickname;
    private Object area;
    private String attribute;
    private String sex;
    private String bio;
    private Object birthday;
    private Object icon;
    private List<Object> posts = new ArrayList<Object>();
    private Boolean isFollowedByMe;
    private Integer cntFollowing;
    private Integer cntFollowers;
    private Integer cntPosts;
    private String lastLogin;
    private String dateJoined;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The username
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     * The username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     * The nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @param nickname
     * The nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     *
     * @return
     * The area
     */
    public Object getArea() {
        return area;
    }

    /**
     *
     * @param area
     * The area
     */
    public void setArea(Object area) {
        this.area = area;
    }

    /**
     *
     * @return
     * The attribute
     */
    public String getAttribute() {
        return attribute;
    }

    /**
     *
     * @param attribute
     * The attribute
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    /**
     *
     * @return
     * The sex
     */
    public String getSex() {
        return sex;
    }

    /**
     *
     * @param sex
     * The sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     *
     * @return
     * The bio
     */
    public String getBio() {
        return bio;
    }

    /**
     *
     * @param bio
     * The bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     *
     * @return
     * The birthday
     */
    public Object getBirthday() {
        return birthday;
    }

    /**
     *
     * @param birthday
     * The birthday
     */
    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    /**
     *
     * @return
     * The icon
     */
    public Object getIcon() {
        return icon;
    }

    /**
     *
     * @param icon
     * The icon
     */
    public void setIcon(Object icon) {
        this.icon = icon;
    }

    /**
     *
     * @return
     * The posts
     */
    public List<Object> getPosts() {
        return posts;
    }

    /**
     *
     * @param posts
     * The posts
     */
    public void setPosts(List<Object> posts) {
        this.posts = posts;
    }

    /**
     *
     * @return
     * The isFollowedByMe
     */
    public Boolean getIsFollowedByMe() {
        return isFollowedByMe;
    }

    /**
     *
     * @param isFollowedByMe
     * The is_followed_by_me
     */
    public void setIsFollowedByMe(Boolean isFollowedByMe) {
        this.isFollowedByMe = isFollowedByMe;
    }

    /**
     *
     * @return
     * The cntFollowing
     */
    public Integer getCntFollowing() {
        return cntFollowing;
    }

    /**
     *
     * @param cntFollowing
     * The cnt_following
     */
    public void setCntFollowing(Integer cntFollowing) {
        this.cntFollowing = cntFollowing;
    }

    /**
     *
     * @return
     * The cntFollowers
     */
    public Integer getCntFollowers() {
        return cntFollowers;
    }

    /**
     *
     * @param cntFollowers
     * The cnt_followers
     */
    public void setCntFollowers(Integer cntFollowers) {
        this.cntFollowers = cntFollowers;
    }

    /**
     *
     * @return
     * The cntPosts
     */
    public Integer getCntPosts() {
        return cntPosts;
    }

    /**
     *
     * @param cntPosts
     * The cnt_posts
     */
    public void setCntPosts(Integer cntPosts) {
        this.cntPosts = cntPosts;
    }

    /**
     *
     * @return
     * The lastLogin
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     *
     * @param lastLogin
     * The last_login
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     *
     * @return
     * The dateJoined
     */
    public String getDateJoined() {
        return dateJoined;
    }

    /**
     *
     * @param dateJoined
     * The date_joined
     */
    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
