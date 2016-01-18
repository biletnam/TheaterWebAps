<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-render name="/WEB-INF/jsp/common/layout_main.jsp"
  title="Theater Reserveringen">

  <s:layout-component name="body">
    <s:messages/>
    <s:form beanclass="theater.action.VoorstellingReserveerActionBean">
      <div><s:hidden name="voorstellingId"/></div>
      <s:errors/>
  <table class="form">
    <tr>
      <td>Naam:</td>
      <td><s:text name="naam"/></td>
    </tr>
    <tr>
      <td>Telefoon</td>
      <td><s:text name="telefoon"/></td>
    </tr>
    <tr>
      <td>Aantal Kaarten voor voorstelling "${actionBean.voorstelling.naam}"</td>
      <td><s:text name="aantal"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td>
        <s:submit name="bestel" value="Bestel"/>
        <s:submit name="cancel" value="Cancel"/>
      </td>
    </tr>
  </table>
</s:form>
   </s:layout-component>
</s:layout-render>