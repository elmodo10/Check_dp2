<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.configuration.form.label.weakSpamWords" path="weakSpamWords" />
	<acme:input-textbox code="administrator.configuration.form.label.weakSpamThreshold" path="weakSpamThreshold" />
	<acme:input-textbox code="administrator.configuration.form.label.strongSpamWords" path="strongSpamWords" />
	<acme:input-textbox code="administrator.configuration.form.label.strongSpamThreshold" path="strongSpamThreshold" />
	<acme:input-textbox code="administrator.configuration.form.label.acceptedCurr" path="acceptedCurr" />
	<acme:input-textbox code="administrator.configuration.form.label.defaultCurr" path="defaultCurr" />
	
	<acme:submit code="administrator.configuration.form.button.update" action="/administrator/configuration/update" />
</acme:form>
