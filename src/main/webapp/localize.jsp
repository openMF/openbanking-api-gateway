<%@ page import="java.util.ResourceBundle" %>
<%@ page import="org.wso2.carbon.identity.application.authentication.endpoint.util.AuthenticationEndpointUtil" %>
<%@ page import="org.wso2.carbon.identity.application.authentication.endpoint.util.EncodedControl" %>
<%@ page import="java.nio.charset.StandardCharsets" %>
<%@page contentType="text/html; charset=UTF-8" %>
<%
    final String BUNDLE = "org.wso2.carbon.identity.application.authentication.endpoint.i18n.Resources";
    final ResourceBundle resourceBundle = ResourceBundle.getBundle(BUNDLE, request.getLocale(), new
            EncodedControl(StandardCharsets.UTF_8.toString()));

    final String DPC_BUNDLE = "hu.dpc.openbanking.wso2.carbon.identity.application.authentication.endpoint.i18n.Resources";
    final ResourceBundle dpcResourceBundle = ResourceBundle.getBundle(DPC_BUNDLE, request.getLocale(), new
            EncodedControl(StandardCharsets.UTF_8.toString()));
%>
