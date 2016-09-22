<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Question Asker</title>
        <script src="/qa/resources/lib/jquery-3.1.0.min.js" type="text/javascript"></script>

    </head>
    <body>
        <div>
            <c:forEach items="${questions}" var="question">
                <span>${question.question}</span>
                <input type="button" value="Edit" onclick="action(${question.id}, 'edit')"/>
                <input type="button" value="Delete" onclick="action(${question.id}, 'rm')"/><br/>
            </c:forEach>
        </div>

        <script>
            var action = function (id, type) {
                $.ajax({
                    url: "/qa/config/questionManager",
                    type: "POST",
                    data: {'id': id, 'type': type},
                    dataType: "text",
                    success: function () {
                    },
                    error: function () {
                        debugger;
                    }
                });
            };

        </script>
    </body>
</html>
