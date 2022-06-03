<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="authenticated.configuration.list.label.defaultCurr" path="defaultCurr"/>		
	<acme:input-textbox code="authenticated.configuration.list.label.acceptedCurr" path="acceptedCurr"/>		
	
	<div style="border: 1px solid #ccc; padding: 6px; border-radius: 6px;">
		<h3>MoneyExchange API</h3>
		<ul>
			<li><b><acme:message code="authenticated.configuration.list.label.service"/>:</b> ExchangeRate-API</li>
			<li><b><acme:message code="authenticated.configuration.list.label.apimessage"/>:</b> <jstl:out value="${moneyExchange}" /></li>
		</ul>
	</div>
	
</acme:form>