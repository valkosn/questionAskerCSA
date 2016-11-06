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

        <div id="question_form_holder"></div>

        <form id="question_form_template" class="question_form" style="display: none">
            <select id="category_template" class="single-select" title="Category">
                <c:forEach items="${categories}" var="cat">
                    <option  id="category_${cat.key}" value="${cat.key}">${cat.value}</option>
                </c:forEach>
            </select> <br/>
            <input id="question_template" class="control_container_element" type="text" value= "Question" title="Question"/> <br/>

            <input class="control_container_element" type=button value="+" onclick="addAnswerField()"><br/>
            <div id="answer_container_template">
                <input id="answer_0" class="control_container_element" name="answer" type="text" value="Answer"/>
            </div>
            <input class="control_container_element" type="button" value="-" onclick="delAnswerField()"><br/>
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
            function newQ () {

                $("#question_form").css("display", "block");
            }

            function save () {
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
            }

            function edit (id) {
                $.ajax({
                    url: "/qa/config/questionManager/" + id,
                    type: "GET",
                    success: function (data) {
                        clearHolder();
                        render(data);
                    },
                    error: function () {
                        debugger;
                    }
                });
            }

            function deleteQ (id) {
                $.ajax({
                    url: "/qa/config/questionManager/" + id,
                    type: "DELETE",
                    success: function () {
                        location.reload(true);
                    },
                    error: function () {
                        debugger;
                    }
                });
            }

            function addAnswerField () {
                var answerContainer = $("#answer_container");

                var answerClone = answerContainer.children().last().clone();
                var idParts = answerClone.attr('id').split('_');
                answerClone.attr('id', idParts[0] + "_" + ++idParts[1]);
                answerContainer.append(answerClone);
            }

            function delAnswerField (id) {
                $("#" + id).remove();
            }


            function cancel (){
                $("#question_form").css("display", "none")
            }

            function render(qa) {
                var questionForm = $("#question_form_template").clone();
                questionForm.prop("id", "question_form");
                questionForm.children("#category_template").prop("id", "category");
                questionForm.children("#question_template").prop("id", "question");
                questionForm.children("#answer_container_template").prop("id", "answer_container");
                $("#question_form_holder").append(questionForm);

                $("#category").find("#category_" + qa.categoryUID).attr("selected", true);
                $("#category").trigger("chosen:update");

                $("#question").prop("value", qa.question);
                for(var i = 0; i < qa.answers.length - 1; i++) {
                    $("#answer_container").find("#answer_" + i).prop("value", qa.answers[i].answer);
                    addAnswerField();
                }
                $("#question_form").css("display", "block");

            }

            function fillHolder() {

            }

            function clearHolder() {
                $("#question_form_holder").children().remove();
            }

        </script>
    </body>
</html>
