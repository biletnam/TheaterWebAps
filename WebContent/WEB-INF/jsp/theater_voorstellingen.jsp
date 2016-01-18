<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
  title="${actionBean.context.currentTheater.naam}">
  <s:layout-component name="body">
  <h1>Voorstellingen</h1>
    <s:messages/>
    <d:table name="${actionBean.context.currentTheater.voorstellingen}" id="vst" requestURI="" defaultsort="1">
      <d:column title="Naam" property="naam" sortable="true"/>
      <d:column title="Datum"  sortable="true">
        <fmt:formatDate type="date" pattern="dd MMM yyyy" value="${vst.datum.time}"/>
      </d:column>
      <d:column title="Bestellen">

        <s:link beanclass="theater.action.VoorstellingReserveerActionBean" event="reserveer">Reserveer
          <s:param name="voorstellingId" value="${vst.id}"/>
        </s:link>
      </d:column>
    </d:table>
  </s:layout-component>
</s:layout-render>