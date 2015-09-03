"use strict";
function TaskAtHandApp() {
    var version = "v2.3";
    var appStorage = new AppStorage("taskAtHand");

    function setStatus(message) {
        $("#app>footer").text(message);
    }

    function addTask() {
        var taskName = $("#new-task-name").val();
        if (taskName) {
            addTaskElement(taskName);
            // Reset the text field
            $("#new-task-name").val("").focus();
            saveTaskList();
        }
    }

    function removeTask($task) {
        $task.remove();
        saveTaskList();
    }

    function moveTask($task, moveUp) {
        if (moveUp) {
            $task.insertBefore($task.prev());
        } else {
            $task.insertAfter($task.next());
        }
        saveTaskList();
    }

    function addTaskElement(taskName) {
        var $task = $("#task-template .task").clone();

        $task.click(function () {
            onSelectTask($task);
        });

        $("span.task-name", $task).text(taskName);

        $("#task-list").append($task);

        // Button evetns
        $("button.delete", $task).click(function () {
            removeTask($task);
        });
        $("button.moveUp", $task).click(function () {
            moveTask($task, true);
        });
        $("button.moveDown", $task).click(function () {
            moveTask($task, false);
        });

        // Task name events
        $("span.task-name", $task).click(function () {
            onEditTaskName($(this));
        });
        $("input.task-name", $task).change(function () {
            onChangeTaskName($(this));
        })
            .blur(function () {
                $(this).hide().siblings("span.task-name").show();
            });
    }

    function onSelectTask($task) {
        if ($task) {
            // Unselect other tasks
            $task.siblings(".selected").removeClass("selected");
            // Select this task
            $task.addClass("selected");
        }
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
            saveTaskList()
        }
        $span.show();
    }

    function saveTaskList() {
        var tasks = [];
        $("#task-list .task span.task-name").each(function () {
            tasks.push($(this).text())
        });
        appStorage.setValue("taskList", tasks);
    }

    function loadTaskList() {
        var tasks = appStorage.getValue("taskList");
        if (tasks) {
            for (var i in tasks) {
                addTaskElement(tasks[i]);
            }
        }
    }

    function onChangeTheme() {
        var theme = $("#theme>option").filter(":selected").val();
        setTheme(theme);
        appStorage.setValue("theme", theme);
    }

    function setTheme(theme) {
        $("#theme-style").attr("href", "themes/" + theme + ".css");
    }

    function loadTheme() {
        var theme = appStorage.getValue("theme");
        if (theme) {
            setTheme(theme);
            $("#theme>option[value=" + theme + "]")
                .attr("selected", "selected");
        }
    }

    this.start = function () {
        $("#new-task-name").keypress(function (e) {
            if (e.which == 13) { // Enter key
                addTask();
                return false;
            }
        }).focus();
        $("#theme").change(onChangeTheme);

        $("#app>header").append(version);
        setStatus("ready");

        loadTaskList();
        loadTheme();
    };
}

$(function () {
    window.app = new TaskAtHandApp();
    window.app.start();
});
