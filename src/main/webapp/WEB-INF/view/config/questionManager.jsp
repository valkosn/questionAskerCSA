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
    <div>
        <input id="question_id" name="questionId" type="hidden">
    </div>
    <select id="category_template" name="category.categoryId" title="Category">
        <c:forEach items="${categories}" var="cat">
            <option id="category_${cat.key}" value="${cat.key}">${cat.value}</option>
        </c:forEach>
    </select> <br/>
    <input id="question_template" class="control_container_element" name="question" type="text" value=""
           title="Question"/> <br/>

    <input class="control_container_element" type=button value="+" onclick="addAnswerField()"><br/>
    <div id="answer_container_template">
        <input id="answer_0" class="control_container_element" name="answers.answer" type="text" value=""/>
    </div>
    <input class="control_container_element" type="button" value="-" onclick="delAnswerField()"><br/>
    <input class="control_container_element" type="button" value="Save" onclick="save()"/>
    <input class="control_container_element" type="button" value="Cancel" onclick="cancel()"/>
</form>

<div>
    <input class="control_container_element" type="button" value="Add new question" onclick="newQ()"> <br/>
    <c:forEach items="${questions}" var="question">
        <input class="control_container_element" type="button" value="Edit" onclick="edit(${question.questionId})"/>
        <input class="control_container_element" type="button" value="Delete"
               onclick="deleteQ(${question.questionId})"/>
        <span>${question.question}</span><br/>
    </c:forEach>
</div>

<script>
    function newQ() {
        render(null);
    }

    function save() {
        var questionForm = $("#question_form");
        var question = {
            questionId: questionForm.find("#question_id").val(),
            question: questionForm.find("#question").val(),
            answers: [],
            category: {
                categoryId: questionForm.find("#category").val()
            }
        };
        questionForm.find("#answer_container").children().each(function () {
            var answer = {answer: this.value};
            question.answers.push(answer);
        });
        if (question.question != "" && question.answers[0].answer != "") {
            let requestMethod = "";
            if (question.questionId != "") {
                requestMethod = "PUT";
            } else {
                requestMethod = "POST";
            }
            $.ajax({
                url: "/qa/config/questionManager",
                type: requestMethod,
                data: JSON.stringify(question),
                dataType: "text",
                contentType: "application/json;charset=UTF-8",
                success: function () {
                    location.reload(true);
                },
                error: function (xhr, resp, text) {
                    console.log(xhr, resp, text);
                    debugger;
                }
            });
        } else {
            alert("Fill required fields!!!")
        }
    }

    function edit(questionId) {
        $.ajax({
            url: "/qa/config/questionManager/" + questionId,
            type: "GET",
            success: function (data) {
                render(data);
            },
            error: function (xhr, resp, text) {
                console.log(xhr, resp, text);
                debugger;
            }
        });
    }

    function deleteQ(questionId) {
        $.ajax({
            url: "/qa/config/questionManager/" + questionId,
            type: "DELETE",
            success: function () {
                location.reload(true);
            },
            error: function (xhr, resp, text) {
                console.log(xhr, resp, text);
                debugger;
            }
        });
    }

    function addAnswerField() {
        var answerContainer = $("#answer_container");
        var answerClone = answerContainer.children().last().clone().val("");
        var idParts = answerClone.attr('id').split('_');
        answerClone.attr('id', idParts[0] + "_" + ++idParts[1]);
        answerContainer.append(answerClone);
    }

    function delAnswerField(id) {
        $("#answer_" + id).remove();
    }

    function cancel() {
        clearHolder();
    }

    function render(qa) {
        clearHolder();
        fillHolder();
        if (qa != null) {
            $("#question_id").val(qa.questionId);
            $("#category").find("#category_" + qa.category.categoryId).attr("selected", true);
            $("#question").prop("value", qa.question);
            for (var i = 0; i < qa.answers.length; i++) {
                $("#answer_container").find("#answer_" + i).prop("value", qa.answers[i].answer);
                if (i < qa.answers.length - 1) {
                    addAnswerField();
                }
            }
        }
        createChosen();
    }

    //TODO: fix it...
    function createChosen() {
        $(".single-select").chosen({
            width: "10%",
            disable_search: "true"
        });
    }

    function fillHolder() {
        var questionForm = $("#question_form_template").clone();
        questionForm.prop("id", "question_form");
        questionForm.children("#category_template").prop("id", "category").attr("class", "single-select");
        questionForm.children("#question_template").prop("id", "question");
        questionForm.children("#answer_container_template").prop("id", "answer_container");
        $("#question_form_holder").append(questionForm);
        $("#question_form").css("display", "block");

    }

    function clearHolder() {
        $("#question_form_holder").children().remove();
    }

    $.fn.serializeObject = function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

</script>
</body>
</html>
