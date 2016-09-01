<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>QuestionAsker</title>
    <link rel="stylesheet" type="text/css" href="../../resources/css/main.css">
</head>
<header>
    <div class="header_message">
        !!!Don't reload page!!! If you do it all your results will be wipe!!!
    </div>
</header>
<body>
<h1><strong>Welcome to Question Asker!!!</strong></h1>

<script src="../../resources/lib/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
    var prefix = '/devcolibri-rest/myservice';

    var RestGet = function () {
        $.ajax({
            type: 'GET',
            url: prefix + '/' + Date.now(),
            dataType: 'json',
            async: true,
            success: function (result) {
                alert('Время: ' + result.time
                        + ', сообщение: ' + result.message);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    }
</script>

<form id="form" name="test" method="post">

    <div id="start_screen_holder" rel="main">

        <div id="prompt">If you are ready, press START. Good luck!</div>
        <H1>${message}</H1>

        <div class="control_container">
            <input id="start" type="button" onclick="RestGet()" value="Start">
            <select id="text_type" title="Test type">
                <option value="1" selected="selected">Normal</option>
                <option value="0">Offline</option>
            </select>
            <select id="questions_amount" title="Amount of questions" onclick="addMaxValueToQuestionAmount()">
                <option value="5">5 questions</option>
                <option value="10">10 questions</option>
                <option value="25">25 questions</option>
                <option value="50" selected="selected">50 questions</option>
                <option value="100">100 questions</option>
            </select>
            <select id="time_per_question" title="Time per question">
                <option value="1">1 sec/question</option>
                <option value="30">30 sec/question</option>
                <option value="60">1 min/question</option>
                <option value="120" selected="selected">2 min/question</option>
                <option value="300">5 min/question</option>
                <option value="600">10 min/question</option>
                <option value="-1">unlimited time</option>
            </select>
        </div>
    </div>
</form>
</body>
</html>
