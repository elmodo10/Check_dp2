<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="any.item.form.label.name" path="name" readonly="true"/>
	<acme:input-textbox code="any.item.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="any.item.form.label.technology" path="technology" readonly="true"/>
	<acme:input-money code="any.item.form.label.retailPrice" path="retailPrice" readonly="true"/>
	
	<jstl:choose>
		<jstl:when test="${command == 'show' }">
			<acme:input-money code="inventor.label.moneyExchange" path="moneyExchange" readonly="true"/>
		</jstl:when>
	</jstl:choose>
	
	<acme:input-textbox code="any.item.form.label.description" path="description" readonly="true"/>
	<acme:input-url code="any.item.form.label.link" path="link" readonly="true"/>
	<acme:input-textbox code="any.item.form.label.type" path="type" readonly="true"/>
	
</acme:form>