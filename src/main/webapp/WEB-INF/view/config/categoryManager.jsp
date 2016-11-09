<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Question Asker</title>
        <script src="/qa/resources/lib/jquery-3.1.0.min.js" type="text/javascript"></script>
    </head>

    <body>
        <div id="delete_container">
            <c:forEach items="${categories}" var="cat">
                <span>${cat.value}</span>
                <input value="X" type="button" onclick="rmCat(${cat.key})"/> <br>
            </c:forEach>
        </div>

        <div id="add_container">
            <input id="new_category" name="newCategory" value="Category" type="text">
            <input id="add_button" name="addButton"  value="Add" type="button" onclick="addNewCat()">
        </div>

        <script>
            var addNewCat = function () {
                $.ajax({
                    url: "/qa/config/categoryManager",
                    type: "POST",
                    data: {'data': $("#new_category").val()},
                    dataType: "text",
                    success: function () {
                        location.reload(true);
                    },
                    error: function (xhr, resp, text) {
                        console.log(xhr, resp, text);
                        debugger;
                    }
                });
            };

            var rmCat = function (catID) {
                $.ajax({
                    url: "/qa/config/categoryManager/" + catID,
                    type: "DELETE",
                    success: function () {
                        location.reload(true);
                    },
                    error: function (xhr, resp, text) {
                        console.log(xhr, resp, text);
                        debugger;
                    }
                });
            };
        </script>
    </body>
</html>
