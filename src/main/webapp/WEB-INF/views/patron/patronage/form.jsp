
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
 
<%@ page import="acme.enums.Status" %>
<%@ page import="acme.enums.PublishedStatus" %>

<acme:form>
	<jstl:choose>
	
		<jstl:when test="${command == 'show'}">
			<acme:input-textbox readonly="true" code="authenticated.patron.patronage.form.label.status" path="status"/>
			<acme:input-textbox readonly="true" code="authenticated.patron.patronage.form.label.code" path="code"/>	
			<acme:input-textbox readonly="true" code="authenticated.patron.patronage.form.label.legalStuff" path="legalStuff"/>	
			<acme:input-money readonly="true" code="authenticated.patron.patronage.form.label.budget" path="budget"/>

			<jstl:choose>
				<jstl:when test="${command == 'show' }">
					<acme:input-money code="patron.label.moneyExchange" path="moneyExchange" readonly="true" />
				</jstl:when>
			</jstl:choose>

			<acme:input-textbox readonly="true" code="authenticated.patron.patronage.form.label.startsAt" path="startsAt"/>
			<acme:input-textbox readonly="true" code="authenticated.patron.patronage.form.label.finishesAt" path="finishesAt"/>
			<acme:input-url readonly="true" code="authenticated.patron.patronage.form.label.link" path="link"/>	
			<acme:input-textarea readonly="true" code="authenticated.patron.patronage.form.label.inventor.company" path="inventor.company"/>
			<acme:input-textarea readonly="true" code="authenticated.patron.patronage.form.label.inventor.statement" path="inventor.statement"/>
			<acme:input-url readonly="true" code="authenticated.patron.patronage.form.label.inventor.link" path="inventor.link"/>
			
			
			<acme:button code="patron.patronage.form.buttom.patronage-reports" action="/patron/patronage-report/list-by-patronage?id=${id}"/>
			
			<jstl:if test="${publishedStatus == 'NONE_PUBLISHED' }">
			<acme:button code="authenticated.patron.patronage.form.button.update" action="/patron/patronage/update?id=${id}"/>
			<acme:submit code="authenticated.patron.patronage.form.button.delete" action="/patron/patronage/delete?id=${id}"/>
			</jstl:if>
			
		</jstl:when>
		<jstl:when test="${command == 'create'}">
		
			<acme:input-textbox code="authenticated.patron.patronage.form.label.code" path="code"/>	
			<acme:input-textbox  code="authenticated.patron.patronage.form.label.legalStuff" path="legalStuff"/>	
			<acme:input-money  code="authenticated.patron.patronage.form.label.budget" path="budget"/>	
			<acme:input-textbox  code="authenticated.patron.patronage.form.label.startsAt" path="startsAt"/>
			<acme:input-textbox  code="authenticated.patron.patronage.form.label.finishesAt" path="finishesAt"/>
			<acme:input-url  code="authenticated.patron.patronage.form.label.link" path="link"/>	
			<acme:input-select code="authenticated.patron.patronage.form.label.inventor" path="inventorUN">
			
	   			<jstl:forEach items="${inventors}" var="inventor">
					<acme:input-option code="${inventor.getUserAccount().getUsername()}" value="${inventor.getUserAccount().getUsername()}" selected="${ inventor.getUserAccount().getUsername() == inventorUN }"/>
				</jstl:forEach>
			</acme:input-select>
			
			<acme:submit code="authenticated.patron.patronage.form.button.create" action="/patron/patronage/create"/>
		</jstl:when>	
		
		<jstl:when test="${command == 'update'}">
		
		
			<acme:input-textbox code="authenticated.patron.patronage.form.label.code" path="code"/>	
			<acme:input-textbox code="authenticated.patron.patronage.form.label.legalStuff" path="legalStuff"/>	
			<acme:input-money code="authenticated.patron.patronage.form.label.budget" path="budget"/>	
			<acme:input-textbox code="authenticated.patron.patronage.form.label.startsAt" path="startsAt"/>
			<acme:input-textbox code="authenticated.patron.patronage.form.label.finishesAt" path="finishesAt"/>
			<acme:input-url code="authenticated.patron.patronage.form.label.link" path="link"/>
			<acme:input-select code="authenticated.patron.patronage.form.label.inventor" path="inventorUN">
			
	   			<jstl:forEach items="${inventors}" var="inventor">
					<acme:input-option code="${inventor.getUserAccount().getUsername()}" value="${inventor.getUserAccount().getUsername()}" selected="${ inventor.getUserAccount().getUsername() == inventorUN }"/>
				</jstl:forEach>
			</acme:input-select>
			
			<acme:submit code="authenticated.patron.patronage.form.button.update" action="/patron/patronage/update"/>
			<acme:submit code="authenticated.patron.patronage.form.button.publish" action="/patron/patronage/publish"/>
		</jstl:when>
					
	</jstl:choose>	
				
	
</acme:form>