<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<jstl:choose>
	<jstl:when test="${command == 'show'}">	

	<h2> <acme:message code="inventor.patronage-report.list.label.patronage.details"/> </h2>
	
	<acme:input-textbox code="inventor.patronage-report.form.label.seqNumber" path="seqNumber"/>
	<acme:input-moment code="inventor.patronage-report.form.label.createdAt" path="createdAt"/>	
	<acme:input-textarea  code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronage-report.form.label.link" path="link"/>
	<br>
	<h2> <acme:message code="inventor.patronage-report.list.label.patronage"/> </h2>
	<br>
	<acme:input-textbox code="inventor.patronage.list.label.patronage.identify.status" path="patronage.status"/>
	<acme:input-textbox code="inventor.patronage.list.label.patronage.identify.code" path="patronage.code"/>
	<acme:input-textbox code="inventor.patronage.list.label.patronage.identify.legalStuff" path="patronage.legalStuff"/>
	<acme:input-money code="inventor.patronage.list.label.patronage.identify.budget" path="patronage.budget"/>
	<acme:input-textbox code="inventor.patronage.list.label.patronage.identify.startsAt" path="patronage.startsAt"/>
	<acme:input-textbox code="inventor.patronage.list.label.patronage.identify.finishesAt" path="patronage.finishesAt"/>
	<acme:input-textbox code="inventor.patronage.list.label.patronage.identify.link" path="patronage.link"/>
	</jstl:when>	
	<jstl:when test="${command == 'create'}">
	<acme:input-textbox readonly="true" code="inventor.patronage-report.form.label.seqNumber" path="seqNumber"/>
	<acme:input-moment readonly="true" code="inventor.patronage-report.form.label.createdAt" path="createdAt"/>	
	<acme:input-textarea  code="inventor.patronage-report.form.label.memorandum" path="memorandum"/>
	<acme:input-url code="inventor.patronage-report.form.label.link" path="link"/>
	<acme:input-checkbox code="inventor.patronage-report.form.label.confirm" path="confirm"/>
	<acme:submit code="inventor.patronage-report.form.button.create" action="/inventor/patronage-report/create"/>
	</jstl:when>
</jstl:choose>	

</acme:form>