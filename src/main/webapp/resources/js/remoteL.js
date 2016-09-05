/**
 * Created by valko on 30-Aug-16.
 */

function getCorrectAnswer(questionNumber) {
    return $.ajax("./evaluate", {
        type: "POST",
        data: {'data' : $("#question_" + questionNumber).text()},
        dataType: "JSON"
    });
}