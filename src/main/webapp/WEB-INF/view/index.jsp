<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>QuestionAsker</title>
    <link href="/qa/resources/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/qa/resources/lib/chosen_v1.6.2/chosen.min.css" rel="stylesheet" type="text/css">
    <link href="/qa/resources/lib/chosen_v1.6.2/chosen-bootstrap.css" rel="stylesheet" type="text/css">
    <link href="/qa/resources/css/main.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Bangers" rel="stylesheet">

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
<body>

<header>
    <ul class="nav nav-pills">
        <li role="presentation" class="active"><a href="/qa/start">Home</a></li>
        <li role="presentation"><a href="/qa/config/questionManager/">Question Manager</a></li>
        <li role="presentation"><a href="/qa/config/categoryManager/">Category Manager</a></li>
    </ul>
</header>

<form id="form" name="start" method="get" action="${pageContext.request.contextPath}/test">

    <div id="start_screen_holder" class="container" rel="main">

        <h1><strong>Welcome to Question Asker!!!</strong></h1>

        <h4>If you are ready, press START. Good luck!</h4>

        <div class="form-inline control_container">

            <select id="test_type" name="testType" class="form-control control_element" title="Test type">
                <option value="normal" selected="selected">Normal</option>
                <option value="offline">Offline</option>
            </select>

            <select id="categories" name="categories" class="form-control control_element" title="Categories"
                    data-placeholder="Choose a category..." multiple="multiple">
                <c:forEach items="${categories}" var="cat">
                    <option value="${cat.key}">${cat.value}</option>
                </c:forEach>
            </select>

            <select id="questions_amount" name="questionsAmount" class="form-control control_element"
                    title="Amount of questions">
                <option value="5" selected="selected">5 questions</option>
                <option value="10">10 questions</option>
                <option value="25">25 questions</option>
                <option value="50">50 questions</option>
                <option value="100">100 questions</option>
                <option value="${maxQaAmount}">${maxQaAmount} questions</option>
            </select>

            <select id="time_per_question" name="timePerQuestion" class="form-control control_element"
                    title="Time per question">
                <option value="1">1 sec/question</option>
                <option value="30">30 sec/question</option>
                <option value="60">1 min/question</option>
                <option value="120" selected="selected">2 min/question</option>
                <option value="300">5 min/question</option>
                <option value="600">10 min/question</option>
                <option value="-1">unlimited time</option>
            </select>

            <div class="row control_container">
                <button id="start" class="btn btn-default control_element" type="submit">
                    Start
                </button>
            </div>

        </div>
        <span>${alert}</span>
    </div>


    <script type="text/javascript">

    </script>
</form>

<div id="test_answers" class="container"></div>

<div id="question_holder_" class="container question_holder" style="display: none;">
    <label id="title_" class="title">Question: {{qn/qa}}
        <span id="question_" class="question"></span>
    </label>

    <div class="row">
        <label class="title">Choice the answer:</label>
    </div>

    <div class="row">
        <div id="answer_container_" class="btn-group" data-toggle="buttons">
            <label id="answer_holder_" class="answers btn btn-default" style="display: none;">
                <input type="radio" name="answer_"/>
                <span id="answer_text_"></span>
            </label>
        </div>
    </div>

    <div class="control_container">
        <button name="previous_question" class="btn btn-default control_element" type="button"
                onclick="getPreviousQuestion()"
                disabled>Previous question
        </button>
        <button name="next_question" class="btn btn-default control_element" type="button" onclick="getNextQuestion()">
            Next question
        </button>
        <button name="finish" class="btn btn-default control_element" type="button" onclick="renderResults()"
                style="display: none;">
            Result
        </button>
    </div>
</div>

<div id="time_holder" class="container time"></div>


<div id="result_screen" class="container" style="display: none;">

    <div id="results_holder" class="results {
">
        <ul id="results"></ul>
    </div>

    <div class="control_container">
        <button id="evaluate" class="btn btn-default control_element" type="button" onclick="evaluateResults()">
            Evaluate
        </button>
        <button id="new_attempt" class="btn btn-default control_element" type="button" onclick="newAttempt()">
            New attempt
        </button>
        <div id="result_message" class="result_massage"></div>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <hr>
        <p class="pull-right">Design by Rebok inc 2016</p>
    </div>
</footer>

<script>${scriptToRun}</script>

</body>

</html>
