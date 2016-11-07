<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Василиска:Вход</title>
        <link href="css/style_index.css" rel="stylesheet" type="text/css">    
     </head>
    <body>
        <%session.invalidate(); %>
        <div class="main">

            <div class="content">
                <p class="title"><span class="text"><img src="images/cat.png" width="62" height="82" hspace="10" vspace="10" align="middle"></span></p>
                <p class="title">Магазин Василиска</p>
                <p class="text">Добро пожаловать в интернет магазин одежды Василиска, где вы сможете найти много интересной информации. Доступны функции поиска, просмотра, сортировки и многие другие.</p>
                <p class="text">Проект находится в разработке, поэтому дизайн и функционал будет постоянно дорабатываться.</p>
                <p class="text">По всем вопросам обращайтесь по адресу <a href="mailto:support@testvasiliska.com">support@testvasiliska.com</a></p>
                <p>&nbsp;</p>

            </div>

            <div class="login_div">
                <p class="title">Для входа введите свои данные:</p>
                <form class="login_form" name="username" action="pages/main.jsp" method="POST">
                    Имя: <input type="text" name="username" value="" size="20" />
                    <input type="submit" value="Войти" />
                </form>

            </div>

            <div class="footer">
                Разработчик: Максим Вишневский, 2013 г
            </div>
        </div>
        
    </body>
</html>
