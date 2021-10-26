package demo.test.security.filter;

import demo.test.common.constant.AuthenticationEnum;
import demo.test.common.exception.APIException;
import demo.test.common.response.BaseResponse;
import demo.test.common.utils.JsonUtils;
import demo.test.common.validate.Validator;
import demo.test.security.wrapper.MultiReadHttpServletRequest;
import demo.test.service.implementation.IUserService;
import demo.test.service.provider.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        MultiReadHttpServletRequest multiReadRequest = new MultiReadHttpServletRequest((HttpServletRequest) request);
        try {
            String token_otp = getParamFromRequest(request, "token_otp");
            String jwt = getJwtFromRequest(request);
            String email = JsonUtils.GetAttrBodyFromRequest(multiReadRequest, "email");
            if (!Validator.IsEmail(email)) {
                resolver.resolveException(request, response, null, new APIException(
                        BaseResponse.Builder().addErrorStatus(HttpStatus.BAD_REQUEST)
                                .addMessage(AuthenticationEnum.INVALID_EMAIL)
                ));
                return;
            }
            if (token_otp != null && jwt != null) {
                resolver.resolveException(request, response, null, new APIException(
                        BaseResponse.Builder().addErrorStatus(HttpStatus.BAD_REQUEST)
                                .addMessage(AuthenticationEnum.DUPLICATE_TOKEN)
                ));
                return;
            }
            // Handle OTP Token Valid
            if (token_otp != null && jwtService.validateJwtToken(token_otp)) {
                String username = jwtService.getUserNameFromJwtToken(jwt);
                if (!username.equals(email)) {
                    resolver.resolveException(request, response, null, new APIException(
                            BaseResponse.Builder().addErrorStatus(HttpStatus.BAD_REQUEST)
                                    .addMessage(AuthenticationEnum.REQUEST_NOT_MATCH_TOKEN)
                    ));
                    return;
                }
            }

            if (jwt != null && jwtService.validateJwtToken(jwt)) {
                String username = jwtService.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            resolver.resolveException(request, response, null, e);
        }

        filterChain.doFilter(multiReadRequest, response);
    }

    // Helper Function
    private String getJwtFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }

    private String getParamFromRequest(HttpServletRequest request, String headerParam) {
        String authHeader = request.getHeader(headerParam);
        if (!authHeader.isEmpty()) {
            return authHeader;
        }
        return null;
    }
}