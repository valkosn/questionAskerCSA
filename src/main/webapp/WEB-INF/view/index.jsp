<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>QuestionAsker</title>
    <link href="/qa/resources/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/qa/resources/css/main.css" rel="stylesheet" type="text/css">
    <link href="/qa/resources/lib/chosen_v1.6.2/chosen.min.css" rel="stylesheet" type="text/css">
    <script src="/qa/resources/lib/jquery-3.1.0.min.js" type="text/javascript"></script>
    <script src="/qa/resources/lib/chosen_v1.6.2/chosen.jquery.min.js" type="text/javascript"></script>
    <script src="/qa/resources/js/chosenConfig.js" type="text/javascript"></script>
    <script src="/qa/resources/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <script>
        data = ${data};
        questionsAmount = ${questionsAmount};
        timePerQuestion = ${timePerQuestion};
    </script>
    <script src="/qa/resources/js/common.js" type="text/javascript"></script>
    <%
        Object additionalJS = request.getAttribute("additionalJS");
        if (!(additionalJS == null) && !additionalJS.toString().isEmpty()) {
    %>
    <script src="/qa/resources/js/${additionalJS}.js" type="text/javascript"></script>
    <%
        }
    %>
</head>
<header>
    <ul class="nav nav-pills">
        <li role="presentation" class="active"><a href="/qa/start">Home</a></li>
        <li role="presentation"><a href="/qa/config/questionManager/">Question Manager</a></li>
        <li role="presentation"><a href="/qa/config/categoryManager/">Category Manager</a></li>
    </ul>
</header>
<body>

<form id="form" name="start" method="get" action="${pageContext.request.contextPath}/test">

    <div id="start_screen_holder" class="container" rel="main">

        <div class="row">
            <h1><strong>Welcome to Question Asker!!!</strong></h1>
        </div>

        <div id="prompt" class="row">
            <span class="col-lg-12">
                If you are ready, press START. Good luck!
            </span>
        </div>

        <div class="row form-inline">

            <input id="start" class="col-lg-2 btn btn-default" type="submit" value="Start">

            <select id="test_type" name="testType" class="col-lg-2 form-control" title="Test type">
                <option value="normal" selected="selected">Normal</option>
                <option value="offline">Offline</option>
            </select>

            <select id="categories" name="categories" class="col-lg-2 form-control" title="Categories"
                    data-placeholder="Choose a category..." multiple>
                <c:forEach items="${categories}" var="cat">
                    <option value="${cat.key}">${cat.value}</option>
                </c:forEach>
            </select>

            <select id="questions_amount" name="questionsAmount" class="col-lg-2 form-control"
                    title="Amount of questions">
                <option value="5" selected="selected">5 questions</option>
                <option value="10">10 questions</option>
                <option value="25">25 questions</option>
                <option value="50">50 questions</option>
                <option value="100">100 questions</option>
                <option value="${maxQaAmount}">${maxQaAmount} questions</option>
            </select>

            <select id="time_per_question" name="timePerQuestion" class="form-control"
                    title="Time per question">
                <option value="1">1 sec/question</option>
                <option value="30">30 sec/question</option>
                <option value="60">1 min/question</option>
                <option value="120" selected="selected">2 min/question</option>
                <option value="300">5 min/question</option>
                <option value="600">10 min/question</option>
                <option value="-1">unlimited time</option>
            </select>

        </div>
        <span>${alert}</span>
    </div>


    <script type="text/javascript">

    </script>
</form>
<div id="test_answers"></div>

<div id="question_holder_" class="row" style="display: none;">
    <label id="title_" class="title">Question: {{qn/qa}}
        <span id="question_" class="question"></span>
    </label>

    <label class="title">Choice the answer:</label>
    <div id="answer_container_"></div>
    <div class="control_container">
        <input name="previous_question" type="button" value="Previous question" onclick="getPreviousQuestion()"
               disabled/>
        <input name="next_question" type="button" value="Next question" onclick="getNextQuestion()"/>
        <input name="finish" type="button" value="Result" onclick="renderResults()" style="display: none;"/>
    </div>
</div>

<div id="time_holder"></div>

<label id="answer_holder_" class="answers" style="display: none;">
    <input type="radio" name="answer_"/>
    <span id="answer_text_"></span>
</label>

<div id="result_screen" style="display: none;">

    <div id="results_holder">
        <ul id="results"></ul>
    </div>

    <div class="control_container">
        <input id="evaluate" class="control_container_element" type="button" value="Evaluate"
               onclick="evaluateResults()"/>
        <input id="new_attempt" class="control_container_element" type="button" value="New attempt"
               onclick="newAttempt()"/>
        <div id="result_message"></div>
    </div>
</div>

<script>${scriptToRun}</script>

</body>
</html>
