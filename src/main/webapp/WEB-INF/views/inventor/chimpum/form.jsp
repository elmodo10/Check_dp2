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

	<h2> <acme:message code="inventor.chimpum.list.label.item.details"/> </h2>
	
	<acme:input-textbox readonly="true" code="inventor.chimpum.form.label.code" path="code"/>
	<acme:input-moment readonly="true" code="inventor.chimpum.form.label.creation" path="creation"/>	
	<acme:input-textbox  readonly="true" code="inventor.chimpum.form.label.title" path="title"/>
	<acme:input-textbox  readonly="true" code="inventor.chimpum.form.label.description" path="description"/>
	<acme:input-moment readonly="true" code="inventor.chimpum.form.label.startsAt" path="startsAt"/>
	<acme:input-moment readonly="true" code="inventor.chimpum.form.label.finishesAt" path="finishesAt"/>
	<acme:input-money readonly="true" code="inventor.chimpum.form.label.budget" path="budget"/>
	<acme:input-url readonly="true" code="inventor.chimpum.form.label.link" path="link"/>
	<br>
	<h2> <acme:message code="inventor.chimpum.list.label.item"/> </h2>
	<br>
	<acme:input-textbox readonly="true" code="inventor.item.list.label.item.identify.name" path="artefact.name"/>
	<acme:input-textbox readonly="true" code="inventor.item.list.label.item.identify.code" path="artefact.code"/>
	<acme:input-textbox  readonly="true" code="inventor.item.list.label.item.identify.technology" path="artefact.technology"/>
	<acme:input-textbox readonly="true" code="inventor.item.list.label.item.identify.description" path="artefact.description"/>
	<acme:input-money readonly="true" code="inventor.item.list.label.item.identify.retailPrice" path="artefact.retailPrice"/>
	<acme:input-textbox readonly="true" code="inventor.item.list.label.item.identify.link" path="artefact.link"/>
	<acme:input-textbox readonly="true" code="inventor.item.list.label.item.identify.status" path="artefact.status"/>
	<acme:input-textbox readonly="true" code="inventor.item.list.label.item.identify.type" path="artefact.type"/>
	<acme:button  code="inventor.chimpum.form.label.update" action="/inventor/chimpum/update?id=${id}"/>
	<acme:submit  code="inventor.chimpum.form.label.delete" action="/inventor/chimpum/delete?id=${id}"/>
	</jstl:when>
		<jstl:when test="${command == 'create'}">
	<acme:input-textbox  code="inventor.chimpum.form.label.code" path="code"/>	
	<acme:input-textarea  code="inventor.chimpum.form.label.title" path="title"/>
	<acme:input-textarea  code="inventor.chimpum.form.label.description" path="description"/>
	<acme:input-moment  code="inventor.chimpum.form.label.startsAt" path="startsAt"/>
	<acme:input-moment  code="inventor.chimpum.form.label.finishesAt" path="finishesAt"/>
	<acme:input-money  code="inventor.chimpum.form.label.budget" path="budget"/>
	<acme:input-url code="inventor.patronage-report.form.label.link" path="link"/>

	
	
	<acme:submit code="inventor.chimpum.form.button.create" action="/inventor/chimpum/create"/>
	</jstl:when>	
	
	<jstl:when test="${command == 'update'}">
	<acme:input-textbox  code="inventor.chimpum.form.label.code" path="code"/>
	<acme:input-moment  readonly="true" code="inventor.chimpum.form.label.creation" path="creation"/>	
	<acme:input-textarea  code="inventor.chimpum.form.label.title" path="title"/>
	<acme:input-textarea  code="inventor.chimpum.form.label.description" path="description"/>
	<acme:input-moment  code="inventor.chimpum.form.label.startsAt" path="startsAt"/>
	<acme:input-moment  code="inventor.chimpum.form.label.finishesAt" path="finishesAt"/>
	<acme:input-money  code="inventor.chimpum.form.label.budget" path="budget"/>
	<acme:input-url code="inventor.patronage-report.form.label.link" path="link"/>

	
	
	<acme:submit code="inventor.chimpum.form.button.update" action="/inventor/chimpum/update"/>
	</jstl:when>	

</jstl:choose>	

</acme:form>