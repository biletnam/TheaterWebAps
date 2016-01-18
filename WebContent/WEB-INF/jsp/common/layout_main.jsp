<!--
 ! Excerpted from "Stripes: and Java Web Development is Fun Again",
 ! published by The Pragmatic Bookshelf.
 ! Copyrights apply to this code. It may not be used to create training material, 
 ! courses, books, articles, and the like. Contact us if you are in doubt.
 ! We make no guarantees that this code is fit for any purpose. 
 ! Visit http://www.pragmaticprogrammer.com/titles/fdstr for more book information.
-->
<%@page contentType="text/html;charset=ISO-8859-1" language="java"%>
<%@include file="/WEB-INF/jsp/common/taglibs.jsp"%>

<s:layout-definition>
  <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd">
    <div id="kop">
     <img src="images/logo.PNG" width="200" height="100">
     <h1>Opdracht 3 Rogier de Mooij</h1>
     <h1>Theater Opdracht</h1>
    </div>
  <html>
    <head>
      <title>${title}</title>
      <link rel="stylesheet" type="text/css"
        href="${contextPath}/css/stijl.css">
    </head>
    <body>
      <div id="header">
        <h1><span class="title">${title}</span></h1>
      </div>
      <div id="body>">
        <s:layout-component name="body"/>
      </div>
      
    </body>
  </html>
</s:layout-definition>
