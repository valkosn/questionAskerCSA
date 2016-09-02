<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>QuestionAsker</title>
</head>
<body>
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
</body>
</html>