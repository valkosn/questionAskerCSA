/**
 * Created by valko on 30-Aug-16.
 */

function getCorrectAnswer(questionNumber) {
    return common.incomeData[questionNumber].answers[0].hashCode();
}