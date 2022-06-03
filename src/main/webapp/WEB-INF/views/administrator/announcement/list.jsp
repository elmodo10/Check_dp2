<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">

	<acme:list-column code="administrator.announcement.list.label.creation" path="creation" width="20%" />
	<acme:list-column code="administrator.announcement.list.label.title" path="title" width="20%"/>
	<acme:list-column code="administrator.announcement.list.label.body" path="body" width="20%"/>
	<acme:list-column code="administrator.announcement.list.label.critic" path="critic" width="20%"/>
	<acme:list-column code="administrator.announcement.list.label.link" path="link" width="20%"/>
	
	
</acme:list>

<acme:button code="administrator.announcement.form.button.create" action="/administrator/announcement/create"/>