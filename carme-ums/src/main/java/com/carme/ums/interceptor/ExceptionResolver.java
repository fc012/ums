package com.carme.ums.interceptor;

import com.carme.common.exception.CommonException;
import com.carme.common.util.RequestUtil;
import com.carme.ums.constants.UmsConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author kelei.huang
 * @Type ExceptionResolver
 * @Desc 异常拦截器
 * @date 2012-7-3
 * @Version V1.0
 */
public class ExceptionResolver implements HandlerExceptionResolver {

    protected static final Logger log = LogManager.getLogger(ExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
                                         Object handler, Exception ex) {
        String msg = "其他异常:" + ex.getMessage();
        CommonException tradeEx = null;
        if (ex instanceof CommonException) {
            tradeEx = (CommonException) ex;
            if (tradeEx.getCode() == UmsConstants.UMS_EXCEPTION.PARAM) {
                msg = "参数异常:" + tradeEx.getMessage();
            } else if (tradeEx.getCode() == UmsConstants.UMS_EXCEPTION.BUSINESS) {
                msg = "业务异常:" + tradeEx.getMessage();
            } else if (tradeEx.getCode() == UmsConstants.UMS_EXCEPTION.SYSTEM) {
                msg = "系统异常:" + tradeEx.getMessage();
            } else if (tradeEx.getCode() == UmsConstants.UMS_EXCEPTION.AUTH) {
                msg = "权限异常:" + tradeEx.getMessage();
            }
        }
        String reqInfo = RequestUtil.getRequestMsg(request, msg);
        ModelAndView view = new ModelAndView("system/msg");
        view.addObject("msg", msg);
        log.error(reqInfo, ex);
        return view;
    }

}
