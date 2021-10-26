package demo.test.security.configuration;

import demo.test.common.constant.AuthenticationEnum;
import demo.test.common.exception.APIException;
import demo.test.common.response.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(RestAuthenticationEntryPoint.class);
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e)
            throws IOException {
        logger.error("Unauthorized error. Message - {}", e.getMessage());
        resolver.resolveException(request, response, null, new APIException(
                BaseResponse.Builder().addErrorStatus(HttpStatus.UNAUTHORIZED).addMessage(AuthenticationEnum.LOGIN_FAIL)
        ));
    }
}