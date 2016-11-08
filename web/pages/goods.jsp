<%@page import="com.vasiliskavrn.shop.web.enums.SearchType"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.vasiliskavrn.shop.web.beans.Goods"%>
<%@page import="com.vasiliskavrn.shop.web.servlets.ShowImage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="com.vasiliskavrn.shop.web.enums.SearchType"%>--%>

<!DOCTYPE html>

<%@include file="../WEB-INF/jspf/left_menu.jspf" %>

<jsp:useBean id="goodsList" class="com.vasiliskavrn.shop.web.beans.GoodsList" scope="page"/>

<%@include file="../WEB-INF/jspf/letters.jspf" %>


<div class="book_list">




    <%
        ArrayList<Goods> list = null;

        if (request.getParameter("cloth_id") != null) {

            long clothId = Long.valueOf(request.getParameter("cloth_id"));
            list = goodsList.getGoodsByCloth(clothId);

        } else if (request.getParameter("letter") != null) {
            
           
            String letter = request.getParameter("letter");
            session.setAttribute("letter", letter);
            list = goodsList.getGoodsByLetter(letter);

        } else if (request.getParameter("search_string") != null) {
                                    
            
//            list = goodsList.getGoodsByLetter("Ш");
              

            String searchStr = request.getParameter("search_string");
            SearchType type = SearchType.FOR_ALL;

            if (request.getParameter("search_option").equals("Мальчикам")) {
                type = SearchType.FOR_BOY;
            }
            if (request.getParameter("search_option").equals("Мужчинам")) {
                type = SearchType.FOR_MAN;
            }
            if (request.getParameter("search_option").equals("Женщинам")) {
                type = SearchType.FOR_WOMEN;
            }
            if (request.getParameter("search_option").equals("Девочкам")) {
                type = SearchType.FOR_GIRL;
            }

            if (searchStr != null && !searchStr.trim().equals("")) {
                list = goodsList.getGoodsBySearch(searchStr, type);
            }
        }
    %>


    <h5 style="text-align: left; margin-top:20px;">Найдено товаров: <%=list.size()%> </h5>
    <%  session.setAttribute("currentGoodsList", list);
        for (Goods goods : list) {

    %>

    <div class="book_info">
        <div class="book_title">
            <p> <%=goods.getName()%></p>
        </div>
        <div class="book_image">
            <a href="#"><img src="<%=request.getContextPath()%>/ShowImage?index=<%=list.indexOf(goods)%>" height="250" width="190" alt="Картинка"/></a>

        </div>
        <div class="book_details">
            <br><strong>Бренд:</strong> <%=goods.getFirme()%>
            <br><strong>Наименование:</strong> <%=goods.getName()%>
            <br><strong>Страна пр-ва:</strong> <%=goods.getCountryMade()%>
            <br><strong>Цена:</strong> <%=goods.getPrice()%>
            <p style="margin:10px;"> <a href="#">Подробнее</a></p>
        </div>
    </div>


    <%}%>



</div>








