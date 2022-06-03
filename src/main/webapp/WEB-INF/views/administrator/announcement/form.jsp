<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	
	<acme:input-textbox code="administrator.announcement.form.label.title" path="title"/>
	<acme:input-textbox code="administrator.announcement.form.label.body" path="body"/>
	<acme:input-textbox code="administrator.announcement.form.label.critic" path="critic"/>
	<acme:input-textbox code="administrator.announcement.form.label.link" path="link"/>
	
	<jstl:if test="${command == 'create'}">
	
		<acme:input-checkbox code="administrator.announcement.form.label.confirm" path="confirm"/>
		
		<acme:submit code="administrator.announcement.form.button.create" action="/administrator/announcement/create"/>
	</jstl:if>
	
</acme:form>