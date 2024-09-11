package ai.remi.comm.util.auth;


import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc RequestHolder
 */
@Slf4j
public class RequestHolder {

    private static final ThreadLocal<AuthInfo> authInfoHolder = new ThreadLocal();

    private static final ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal();


    /**
     * 使用实例：
     * CompanyAuthInfo companyAuthInfo = new CompanyAuthInfo();
     * companyAuthInfo.setStoreId(pageRenderRealTimeDTO.getStoreId());
     * companyAuthInfo.setCompanyId(pageRenderRealTimeDTO.getCompanyId());
     * companyAuthInfo.setPermissionScope(PermissionScopeEnum.COMPANY);
     * RequestHolder.add(companyAuthInfo);
     * @param authInfo
     */
    public static void add(AuthInfo authInfo) {
        authInfoHolder.set(authInfo);
    }

    public static void add(HttpServletRequest request) {
        requestHolder.set(request);
    }

    public static AuthInfo getAuthInfo() {
        AuthInfo authInfo = authInfoHolder.get();
        if (authInfo == null) {
            log.warn("当前线程AuthInfo为空,请检查");
        }
        return authInfo;
    }

    /*public static RemiAuthInfo getRemiAuthInfo() {
        AuthInfo authInfo = getAuthInfo();
        if (authInfo instanceof RemiAuthInfo){
            return (RemiAuthInfo) authInfo;
        }
        throw new RuntimeException("类型转换错误");
    }*/

    public static CompanyAuthInfo getCompanyAuthInfo() {
        AuthInfo authInfo = getAuthInfo();
        if (authInfo instanceof CompanyAuthInfo) {
            return (CompanyAuthInfo) authInfo;
        }
        throw new RuntimeException("类型转换错误");
    }

    /*public static VisitorAuthInfo getVisitorAuthInfo() {
        AuthInfo authInfo = getAuthInfo();
        if (authInfo instanceof VisitorAuthInfo){
            return (VisitorAuthInfo) authInfo;
        }
        throw new RuntimeException("类型转换错误");
    }*/

    public static HttpServletRequest getCurrentRequest() {
        return requestHolder.get();
    }

    public static void remove() {
        authInfoHolder.remove();
        requestHolder.remove();
    }

}