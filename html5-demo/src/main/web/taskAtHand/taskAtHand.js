"use strict";
function TaskAtHandApp() {
    var version = "v1.3";

    function setStatus(message) {
        $("#app>footer").text(message);
    }

    function addTask() {
        var taskName = $("#new-task-name").val();
        if (taskName) {
            addTaskElement(taskName);
            // Reset the text field
            $("#new-task-name").val("").focus();
        }
    }

    function addTaskElement(taskName) {
        var $task = $("<li></li>");
        var $delete = $("<button class='delete'>remove</button>");
        var $moveUp = $("<button class='move-up'>up</button>");
        var $moveDown = $("<button class='move-up'>down</button>");

        $task.append($delete)
            .append($moveUp)
            .append($moveDown)
            .append("<span class='task-name'>" + taskName + "</span>");

        $delete.click(function () {
            $task.remove();
        });
        $moveUp.click(function() {
            $task.insertBefore($task.prev());
        });
        $moveDown.click(function() {
            $task.insertAfter($task.next());
        });

        $("#task-list").append($task);
    }

    this.start = function () {
        $("#new-task-name").keypress(function (e) {
            if (e.which == 13) { // Enter key
                addTask();
                return false;
            }
        }).focus();

        $("#app>header").append(version);
        setStatus("ready");
    };
}

$(function () {
    window.app = new TaskAtHandApp();
    window.app.start();
});
