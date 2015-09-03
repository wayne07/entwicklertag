"use strict";
function TaskAtHandApp() {
    var version = "v1.4";

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
        var $task = $("#task-template .task").clone();
        $("span.task-name", $task).text(taskName);

        $("#task-list").append($task);

        // Button evetns
        $("button.delete", $task).click(function () { $task.remove(); });
        $("button.moveUp", $task).click(function () { $task.insertBefore($task.prev()); });
        $("button.moveDown", $task).click(function () { $task.insertAfter($task.next()); });

        // Task name events
        $("span.task-name", $task).click(function () { onEditTaskName($(this)); });
        $("input.task-name", $task).change(function () { onChangeTaskName($(this)); })
            .blur(function () { $(this).hide().siblings("span.task-name").show(); });
    }

    function onEditTaskName($span) {
        $span.hide()
            .siblings("input.task-name")
            .val($span.text())
            .show()
            .focus();
    }

    function onChangeTaskName($input) {
        $input.hide();
        var $span = $input.siblings("span.task-name");
        if ($input.val()) {
            $span.text($input.val());
        }
        $span.show();
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
