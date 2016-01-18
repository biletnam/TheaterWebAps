<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
  title="Bestelling">

  <s:layout-component name="body">
    <s:messages/>
    <h2>${actionBean.context.currentTheater.naam}</h2>
   <h3>U heeft besteld voor voorstelling: "${actionBean.voorstelling.naam}"
   <br>
   Op datum : <fmt:formatDate type="date" pattern="dd MMM yyyy" value="${actionBean.voorstelling.datum.time}"/>
  </h3><br>
   <h5>
   Uw klantnummer :  ${actionBean.klant.id} <br>
   Op naam :  ${actionBean.klant.naam}<br>
   met telefoon nummer : ${actionBean.klant.telefoon}</h5>
   <h5>Heeft ${actionBean.aantal} kaarten gekocht
   en zit op de plaatsen:</h5><br>
   <c:forEach items="${actionBean.plaats}" var="p" varStatus="i">
    Rij ${p.rijnummer} : Stoel ${p.stoelnummer}<br>
   </c:forEach>
   </s:layout-component>
</s:layout-render>