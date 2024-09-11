package ai.remi.comm.util.auth;

/**
 * @author dianjiu【公众号 点九开源】
 * @email dianjiuxyz@gmail.com
 * @desc TenantHolder
 */
public class TenantHolder {

    /**
     * 是否是管理员
     */
    private static final ThreadLocal<Boolean> isAdminThreadLocal = new ThreadLocal<>();

    /**
     * 是否是不拦截的请求
     */
    private static final ThreadLocal<Boolean> urlIgnoreThreadLocal = new ThreadLocal<>();


    public static Boolean isAdmin() {
        return isAdminThreadLocal.get();
    }

    public static void admin(boolean isAdmin) {
        isAdminThreadLocal.set(isAdmin);
    }

    public static Boolean getIgnoredRequest() {
        return urlIgnoreThreadLocal.get();
    }

    public static void setIgnoredRequest(boolean ignoredRequest) {
        urlIgnoreThreadLocal.set(ignoredRequest);
    }


    public static void remove() {
        isAdminThreadLocal.remove();
        urlIgnoreThreadLocal.remove();
    }
}
