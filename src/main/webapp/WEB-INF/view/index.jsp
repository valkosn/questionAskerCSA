<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>QuestionAsker</title>
    <link href="/qa/resources/css/main.css" rel="stylesheet" type="text/css">
    <link href="/qa/resources/lib/chosen_v1.6.2/chosen.css" rel="stylesheet" type="text/css">
    <script src="/qa/resources/lib/jquery-3.1.0.min.js" type="text/javascript"></script>
    <script src="/qa/resources/lib/chosen_v1.6.2/chosen.jquery.js" type="text/javascript"></script>
    <script src="/qa/resources/js/chosenConfig.js" type="text/javascript"></script>
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
    <div class="header_message">
        !!!Don't reload page!!! If you do it all your results will be wipe!!!
    </div>
</header>
<body>
<h1><strong>Welcome to Question Asker!!!</strong></h1>

<form id="form" name="start" method="post" action="${pageContext.request.contextPath}/test">

    <div id="start_screen_holder" rel="main">

        <div id="prompt">If you are ready, press START. Good luck!</div>

        <div class="control_container">
            <input id="start" class="control_container_element" type="submit" value="Start">
            <input id="config" class="control_container_element" type="button" value="Config"
                   onclick="location.href='${pageContext.request.contextPath}/config'">
            <select id="test_type" name="testType" class="single-select" title="Test type">
                <option value="normal" selected="selected">Normal</option>
                <option value="offline">Offline</option>
            </select>
            <select id="categories" name="categories" class="category-select" title="Categories"
                    data-placeholder="Choose a category..." multiple>
                <c:forEach items="${categories}" var="cat">
                    <option value="${cat.key}">${cat.value}</option>
                </c:forEach>
            </select>
            <select id="questions_amount" name="questionsAmount" class="single-select" title="Amount of questions">
                <option value="5" selected="selected">5 questions</option>
                <option value="10">10 questions</option>
                <option value="25">25 questions</option>
                <option value="50">50 questions</option>
                <option value="100">100 questions</option>
                <option value="${maxQaAmount}">${maxQaAmount} questions</option>
            </select>
            <select id="time_per_question" name="timePerQuestion" class="single-select" title="Time per question">
                <option value="1">1 sec/question</option>
                <option value="30">30 sec/question</option>
                <option value="60">1 min/question</option>
                <option value="120" selected="selected">2 min/question</option>
                <option value="300">5 min/question</option>
                <option value="600">10 min/question</option>
                <option value="-1">unlimited time</option>
            </select>
            <span>${alert}</span>
        </div>
    </div>

    <script type="text/javascript">

    </script>
</form>
<div id="test_answers"></div>

<div id="question_holder_" class="questions" style="display: none;">
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
        <input id="evaluate" class="control_container_element" type="button" value="Evaluate" onclick="evaluateResults()"/>
        <input id="new_attempt" class="control_container_element" type="button" value="New attempt" onclick="newAttempt()"/>
        <div id="result_message"></div>
    </div>
</div>

<script>${scriptToRun}</script>

</body>
</html>
