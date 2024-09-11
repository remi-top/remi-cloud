package ai.remi.comm.web.auth;

import ai.remi.comm.util.auth.AuthInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc AuthHandler
 */
public interface AuthHandler {
    AuthInfo getAuthInfo(HttpServletRequest request, HttpServletResponse response);
}
