<%@page import="java.util.ArrayList"%>
<%@page import="com.vasiliskavrn.shop.web.beans.Goods"%>
<%@page import="com.vasiliskavrn.shop.web.servlets.ShowImage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="com.vasiliskavrn.shop.web.enums.SearchType"%>--%>

<!DOCTYPE html>

<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="bookList" class="com.vasiliskavrn.shop.web.beans.GoodsList" scope="page"/>

<%@include file="../WEB-INF/jspf/letters.jspf" %>

<%request.setCharacterEncoding("UTF-8");

    long clothId = 0L;
    
    try {
        clothId= Long.valueOf(request.getParameter("cloth_id"));
    } catch (Exception ex) {
        ex.printStackTrace();
    }

%>



<div class="book_list">


    <h3>${param.name}</h3>
    

        <%
            ArrayList<Goods> list = bookList.getGoodsByCloth(clothId);
            session.setAttribute("currentGoodsList", list);
            for (Goods goods : list) {
                
        %>
        
            <div class="book_info">
                <div class="book_title">
<!--                <p> <%=goods.getclothG()%></p>-->
                </div>
                
                <div class="book_image">
                <!--<img src="../images/cat.png" height="250" width="190" alt="Обложка"/>-->
                <img src="<%=request.getContextPath()%>/ShowImage?index=<%=list.indexOf(goods) %>" height="250" width="190" alt="Обложка"/>
                </div>
                
                    <div class="book_details">
                     <br><strong>Бренд:</strong> <%=goods.getFirme()%>
                     <br><strong>Наименование:</strong> <%=goods.getclothG()%>
                     <br><strong>Страна пр-ва:</strong> <%=goods.getCountryMade()%>
                     <br><strong>Цена:</strong> <%=goods.getPrice()%>
                     <p style="margin:10px;"> <a href="#">Подробнее</a></p>
                    </div>
       </div>
            
       
        <%}%>
</div>
    
    





            
      