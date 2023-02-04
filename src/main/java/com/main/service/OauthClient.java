package com.main.service;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class OauthClient {

    public static final String PASSWORD = "A1234gyh";
    public static final String USERNAME = "TEST1010";
    public static final String RESOURCE = "JPMC:URI:RS-103146-10145-PreastoSF-UAT";
    private static final String CLIENT_ID = PASSWORD;
    private static final Logger LOGGER= LoggerFactory.getLogger(OauthClient.class);

    @Value("${oauth.url}")
    private String oauthUrl;

    public String getOauthToken() throws OAuthProblemException, OAuthSystemException {

        OAuthClient client = new OAuthClient(new URLConnectionClient());
        OAuthClientRequest request =
                OAuthClientRequest.tokenLocation(oauthUrl)
                        .setGrantType(GrantType.PASSWORD)
                        .setClientId(CLIENT_ID)
                        .setUsername(USERNAME)
                        .setPassword(PASSWORD)
                        .setParameter("resource", RESOURCE)
                        .buildBodyMessage();


        return client.accessToken(request,
                OAuth.HttpMethod.POST,
                OAuthJSONAccessTokenResponse.class).getAccessToken();
    }
}
