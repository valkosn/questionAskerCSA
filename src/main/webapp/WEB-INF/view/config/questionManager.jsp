<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Question Asker</title>
        <script src="/qa/resources/lib/jquery-3.1.0.min.js" type="text/javascript"></script>
        <link href="/qa/resources/css/main.css" rel="stylesheet" type="text/css">
        <link href="/qa/resources/lib/chosen_v1.6.2/chosen.css" rel="stylesheet" type="text/css">
        <script src="/qa/resources/lib/chosen_v1.6.2/chosen.jquery.js" type="text/javascript"></script>
        <script src="/qa/resources/js/chosenConfig.js" type="text/javascript"></script>

    </head>
    <body>
        <form id="question_form" class="question_form" style="display: none">
            <select id="category" class="single-select" title="Category">
                <c:forEach items="${categories}" var="cat">
                    <option  id="category_${cat.key}" value="${cat.key}">${cat.value}</option>
                </c:forEach>
            </select> <br/>
            <input id="question" class="control_container_element" type="text" value= "Question" title="Question"/> <br/>

            <input class="control_container_element" type=button value="+" onclick="addAnswerField()"><br/>
            <div id="answer_container">
                <input id="answer_1" class="control_container_element" name="answer" type="text" value="Answer"/>
            </div>
            <input class="control_container_element" type=button value="-" onclick="delAnswerField()"><br/>
            <input class="control_container_element" type="button" value="Save" onclick="save()"/>
            <input class="control_container_element" type="button" value="Cancel" onclick="cancel()"/>
        </form>

        <div>
            <input class="control_container_element" type="button" value="Add new question" onclick="newQ()"> <br/>
            <c:forEach items="${questions}" var="question">
                <input class="control_container_element" type="button" value="Edit" onclick="edit(${question.id})"/>
                <input class="control_container_element" type="button" value="Delete" onclick="deleteQ(${question.id})"/>
                <span>${question.question}</span><br/>
            </c:forEach>
        </div>

        <script>
            var newQ = function () {

                $("#question_form").css("display", "block");
            };

            var save = function () {
                $.ajax({
                    url: "/qa/config/questionManager",
                    type: "POST",
                    data: $("#question_form").serialize(),
                    dataType: "JSON",
                    success: function () {
                        location.reload(true);
                    },
                    error: function () {
                        debugger;
                    }
                });
            };

            var edit = function (id) {
                $.ajax({
                    url: "/qa/config/questionManager/" + id,
                    type: "GET",
                    success: function (data) {
//                        $("").prop("selected", true);
                        $("#question").prop("value", data.question);
                        for(var i = 0; i < data.answers.length - 1; i++) {
                            addAnswerField();
                        }
                        $("#question_form").css("display", "block");
                    },
                    error: function () {
                        debugger;
                    }
                });
            };

            var deleteQ = function (id) {
                $.ajax({
                    url: "/qa/config/questionManager/" + id,
                    type: "DELETE",
//                    data: {'id': id},
//                    dataType: "text",
                    success: function () {
                        location.reload(true);
                    },
                    error: function () {
                        debugger;
                    }
                });
            };

            var addAnswerField = function () {
                var answerContainer = $("#answer_container");
                var answerClone = answerContainer.children().last().clone();
                var idParts = answerClone.attr('id').split('_');
                answerClone.attr('id', idParts[0] + "_" + ++idParts[1]);
                answerContainer.append(answerClone);
            };

            var delAnswerField = function (id) {
                $("#" + id).remove();
            };


            var cancel = function(){
                $("#question_form").css("display", "none")
            };

        </script>
    </body>
</html>
