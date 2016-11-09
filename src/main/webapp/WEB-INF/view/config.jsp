<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link href="/qa/resources/css/main.css" rel="stylesheet" type="text/css">
        <title>QuestionAskerConfig</title>
    </head>
    <body>
        <div>
            <input class="control_container_element" value="Question Manager" type="button" onclick="location.href='${pageContext.request.contextPath}/config/questionManager/'">
            <input class="control_container_element" value="Category Manager" type="button" onclick="location.href='${pageContext.request.contextPath}/config/categoryManager/'">
        </div>
    </body>
</html>
