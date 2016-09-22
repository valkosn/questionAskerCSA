<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>QuestionAskerConfig</title>
    </head>
    <body>
        <div>
            <input value="Question Manager" type="button" onclick="location.href='${pageContext.request.contextPath}/config/questionManager'">
            <input value="Category Manager" type="button" onclick="location.href='${pageContext.request.contextPath}/config/categoryManager'">
        </div>
    </body>
</html>
