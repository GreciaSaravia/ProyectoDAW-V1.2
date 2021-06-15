<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<c:if test="${sesion == null}">
		<c:redirect url="Sesion.jsp"/>
	</c:if>

    <jsp:include page="PrincipalInicio.jsp"/>
    <jsp:include page="PrincipalFinal.jsp"/>
    