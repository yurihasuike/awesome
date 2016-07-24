
package me.myreco.up.LoginApi;

import java.util.HashMap;
import java.util.Map;

public class Post {

    private EmailOrUsername emailOrUsername;
    private Password password;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     *     The emailOrUsername
     */
    public EmailOrUsername getEmailOrUsername() {
        return emailOrUsername;
    }

    /**
     *
     * @param emailOrUsername
     *     The email_or_username
     */
    public void setEmailOrUsername(EmailOrUsername emailOrUsername) {
        this.emailOrUsername = emailOrUsername;
    }

    /**
     *
     * @return
     *     The password
     */
    public Password getPassword() {
        return password;
    }

    /**
     *
     * @param password
     *     The password
     */
    public void setPassword(Password password) {
        this.password = password;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
