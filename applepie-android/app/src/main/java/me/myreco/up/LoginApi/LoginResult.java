package me.myreco.up.LoginApi;

import java.util.HashMap;
import java.util.Map;

public class LoginResult {

    private String twAccessTokenSecret;
    private String fbAccessToken;
    private String uid;
    private String key;
    private String twAccessToken;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The twAccessTokenSecret
     */
    public String getTwAccessTokenSecret() {
        return twAccessTokenSecret;
    }

    /**
     *
     * @param twAccessTokenSecret
     * The tw_access_token_secret
     */
    public void setTwAccessTokenSecret(String twAccessTokenSecret) {
        this.twAccessTokenSecret = twAccessTokenSecret;
    }

    /**
     *
     * @return
     * The fbAccessToken
     */
    public String getFbAccessToken() {
        return fbAccessToken;
    }

    /**
     *
     * @param fbAccessToken
     * The fb_access_token
     */
    public void setFbAccessToken(String fbAccessToken) {
        this.fbAccessToken = fbAccessToken;
    }

    /**
     *
     * @return
     * The uid
     */
    public String getUid() {
        return uid;
    }

    /**
     *
     * @param uid
     * The uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    /**
     *
     * @return
     * The key
     */
    public String getKey() {
        return key;
    }

    /**
     *
     * @param key
     * The key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     *
     * @return
     * The twAccessToken
     */
    public String getTwAccessToken() {
        return twAccessToken;
    }

    /**
     *
     * @param twAccessToken
     * The tw_access_token
     */
    public void setTwAccessToken(String twAccessToken) {
        this.twAccessToken = twAccessToken;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}