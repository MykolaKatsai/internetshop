package mate.academy.internetshop.web.filters;

import static mate.academy.internetshop.models.Role.RoleName.ADMIN;
import static mate.academy.internetshop.models.Role.RoleName.USER;

import java.io.IOException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.models.Role;
import mate.academy.internetshop.models.User;
import mate.academy.internetshop.services.UserService;

public class AuthorizationFilter implements Filter {
    @Inject
    private static UserService userService;
    private final Map<String, Role.RoleName> protectedUrls = new HashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        protectedUrls.put("/servlet/getAllUsers", ADMIN);
        protectedUrls.put("/servlet/addItem", ADMIN);
        protectedUrls.put("/servlet/getUserInfo", USER);
        protectedUrls.put("/servlet/addItemToBucket", USER);
        protectedUrls.put("/servlet/deleteItemFromBucket", USER);
        protectedUrls.put("/servlet/addNewOrder", USER);
        protectedUrls.put("/servlet/deleteOrder", USER);
        protectedUrls.put("/servlet/getUserOrders", USER);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Role.RoleName roleName = protectedUrls.get(req.getRequestURI()
                .replace(req.getContextPath(), ""));
        if (roleName == null) {
            processAuthenticated(req, resp, filterChain);
            return;
        }

        if (req.getCookies() == null) {
            processUnAuthenticated(req, resp);
            return;
        }

        String token = null;
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("MATE")) {
                token = cookie.getValue();
                break;
            }
        }
        if (token == null) {
            processUnAuthenticated(req, resp);
            return;
        } else {
            Optional<User> user = userService.getByToken(token);
            if (user.isPresent()) {
                if (verifyRole(user.get(), roleName)) {
                    processAuthenticated(req, resp, filterChain);
                } else {
                    processDenied(req, resp);
                }
            } else {
                processUnAuthenticated(req, resp);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private void processUnAuthenticated(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + "/login");
    }

    private void processAuthenticated(HttpServletRequest req, HttpServletResponse resp,
                                      FilterChain filterChain) throws IOException,
            ServletException {
        filterChain.doFilter(req, resp);
    }

    private void processDenied(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/accessDenied.jsp").forward(req, resp);
    }

    private boolean verifyRole(User user, Role.RoleName roleName) {
        return user.getRoles().stream().anyMatch(role -> role.getRoleName().equals(roleName));
    }

}
