<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Question Asker</title>
    <link href="/qa/resources/css/main.css" rel="stylesheet" type="text/css">
    <link href="/qa/resources/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/qa/resources/lib/DataTables-1.10.12/media/css/jquery.dataTables.min.css" rel="stylesheet"
          type="text/css">
    <link href="/qa/resources/lib/DataTables-1.10.12/media/css/dataTables.bootstrap.min.css" rel="stylesheet"
          type="text/css">

    <script src="/qa/resources/lib/jquery-3.1.0.min.js" type="text/javascript"></script>
    <script src="/qa/resources/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="/qa/resources/lib/DataTables-1.10.12/media/js/jquery.dataTables.min.js"
            type="text/javascript"></script>
    <script src="/qa/resources/lib/DataTables-1.10.12/media/js/dataTables.bootstrap.min.js"
            type="text/javascript"></script>

</head>
<header>
    <ul class="nav nav-pills">
        <li role="presentation"><a href="/qa/start">Home</a></li>
        <li role="presentation" class="active"><a href="/qa/config/questionManager/">Question Manager</a></li>
        <li role="presentation"><a href="/qa/config/categoryManager/">Category Manager</a></li>
        <li role="presentation"><a id="addNewQuestionButton" type="button" class="btn btn-primary" data-toggle="modal"
                                   data-target="#questionModal"
                                   onclick="newQ()">Add new question</a></li>
    </ul>
</header>
<body>

<div class="modal fade" id="questionModal" tabindex="-1" role="dialog" aria-labelledby="questionModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="questionModalLabel">Add new question</h4>
            </div>

            <div class="modal-body" id="questionModalHolder">
                <!-- Question holder -->
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    Close
                </button>

                <button type="button" class="btn btn-success" onclick="save()">
                    Save changes
                </button>
            </div>
        </div>
    </div>
</div>

<div id="question_form_holder" class="container center-block"></div>

<form id="question_form_template" class="question_form form-horizontal" style="display: none">
    <div>
        <input id="question_id" name="questionId" type="hidden">
    </div>

    <div class="form-group">
        <label for="category_template" id="category_label_template" class="col-lg-2 control-label">Category</label>
        <div class="col-lg-10">
            <select id="category_template" class="form-control" name="category.categoryId" title="Category">
                <c:forEach items="${categories}" var="cat">
                    <option id="category_${cat.key}" value="${cat.key}">${cat.value}</option>
                </c:forEach>
            </select>
        </div>
    </div>

    <div class="form-group">
        <label for="question_template" id="question_label_template" class="col-lg-2 control-label">Question</label>
        <div class="col-lg-10">
            <input id="question_template" class="form-control" name="question" type="text" placeholder="Enter question"
                   title="Question"/>
        </div>
    </div>

    <div class="form-group">
        <button type="button" class="btn btn-primary" id="addAnswer" onclick="addAnswerField()">
            Add answer field
        </button>
    </div>

    <div id="answer_container_template">

        <div class="form-group" id="answerRow_0">
            <label for="answer_0" id="label_0" class="col-lg-2 control-label">Answer</label>
            <div class="col-lg-9 input-group">
                <input class="form-control" id="answer_0" name="answer" type="text" placeholder="Enter answer"/>
                <span class="input-group-btn">
                    <button class="btn btn-danger" id="delButton_0" type="button" onclick="delAnswerField(0)">
                        -
                    </button>
                </span>
            </div>
        </div>
    </div>
</form>

<div class="container">
    <div class="row">
        <table id="questionTable" class="table table-striped" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>ID</th>
                <th>Category</th>
                <th>Question</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${questions}" var="question">
                <tr>
                    <td><span class="">${question.questionId}</span></td>
                    <td><c:out value="${categories[question.category.categoryId]}"/></td>
                    <td><span class="">${question.question}</span></td>
                    <td>
                        <button class="btn btn-primary" type="button" data-toggle="modal" data-target="#questionModal"
                                onclick="edit(${question.questionId})">
                            Edit
                        </button>
                    </td>
                    <td>
                        <button class="btn btn-danger" type="button" onclick="deleteQ(${question.questionId})">
                            Delete
                        </button>

                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
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
        questionForm.find("input[name='answer']").each(function () {
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
        var answerClone = answerContainer.children().last().clone();
        var idParts = answerClone.attr('id').split('_');
        var currantId = idParts[1];
        var nextId = parseInt(currantId) + 1;
        answerClone.attr('id', idParts[0] + "_" + nextId);
        answerClone.find("#label_" + currantId).attr("id", "label_" + nextId).attr("for", "answer_" + nextId);
        answerClone.find("#answer_" + currantId).prop("id", "answer_" + nextId).val("");
        answerClone.find("#delButton_" + currantId).prop("id", "delButton_" + nextId).attr("onclick", "delAnswerField(" + nextId + ")");
        answerContainer.append(answerClone);
    }

    function delAnswerField(id) {
        if (id == 0) {
            alert("Can't remove true answer")
        } else {
            $("#answerRow_" + id).remove();
        }
    }

    function cancel() {
        clearHolder();
    }

    function render(qa) {
        clearHolder();
        fillHolder();
        if (qa != null) {
            $("#questionModalLabel").text("Edit question");
            $("#question_id").val(qa.questionId);
            $("#category").find("#category_" + qa.category.categoryId).attr("selected", true);
            $("#question").prop("value", qa.question);
            for (var i = 0; i < qa.answers.length; i++) {
                $("#answer_container").find("#answer_" + i).prop("value", qa.answers[i].answer);
                if (i < qa.answers.length - 1) {
                    addAnswerField();
                }
            }
        } else {
            $("#questionModalLabel").text("Add new question");
        }
    }

    function fillHolder() {
        var questionForm = $("#question_form_template").clone();
        questionForm.prop("id", "question_form");
        questionForm.find("#category_template").prop("id", "category");
        questionForm.find("#category_label_template").prop("for", "category");
        questionForm.find("#question_template").prop("id", "question");
        questionForm.find("#question_label_template").prop("for", "question");
        questionForm.find("#answer_container_template").prop("id", "answer_container");
        $("#questionModalHolder").append(questionForm);
        $("#question_form").css("display", "block");

    }

    function clearHolder() {
        $("#questionModalHolder").children().remove();
    }

    $(document).ready(function () {
        $('#questionTable').dataTable({

        });
    });

</script>
</body>
</html>
