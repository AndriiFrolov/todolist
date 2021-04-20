$(document).ready(function() {

    $.getJSON("/api/task/all", function(data, status) {
        $.each(data, function(i, item) {
            addRow(item, i);

        });
    });

    $("#add-btn").click(function() {
        var body = {
            summary: $("#summary-text").val(),
            description: $("#description-text").val(),

        };

        var type = 'POST';


        $.ajax({
            type: type,
            url: '/api/task/',
            data: JSON.stringify(body),
            success: function(data) {
                addRow(data, getRandomInt(100000) + 1)
                $("#validation-success").fadeIn(3000).fadeOut(3000);

            },
            error: function(jqXHR, textStatus, errorThrown) {
                showError(jqXHR.responseJSON.message, jqXHR.responseText);
            },
            contentType: "application/json",
            dataType: 'json'
        });

    });
});

function addRow(item, i) {
    var $tr = $('<tr class="task-row task-row-' + i + '">').append(
        $('<td id="summary">').text(item.summary),
        $('<td id="description">').text(item.description),
        $('<td id="status">').text(item.status),
        $('<td>').append($('<button id="complete-task">').text('Complete')
            .click(function() {
                var body = {
                    summary: item.summary,
                    description: item.description
                };
                $.ajax({
                    url: '/api/task',
                    type: 'patch',
                    data: JSON.stringify(body),
                    contentType: "application/json",
                    dataType: 'json',
                    success: function(response) {
                        $("#validation-success").fadeIn(3000).fadeOut(3000);
                        var rowToUpdate = $(".task-row-" + i);
                        rowToUpdate.children('td').eq(0).text(response.summary);
                        rowToUpdate.children('td').eq(1).text(response.description);
                        rowToUpdate.children('td').eq(2).text(response.status);
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        showError(jqXHR.responseJSON.message, jqXHR.responseText);
                    },
                });

            })),
        $('<td>').append($('<button id="delete-task">').text('Delete')
            .click(function() {
                var body = {
                    summary: item.summary
                };
                $.ajax({
                    url: '/api/task',
                    type: 'delete',
                    data: JSON.stringify(body),
                    contentType: "application/json",
                    dataType: 'json',
                    success: function(response) {
                        var rowToDelete = $(".task-row-" + i);
                        rowToDelete.remove();
                        $("#validation-success").fadeIn(3000).fadeOut(3000);

                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        showError(jqXHR.responseJSON.message, jqXHR.responseText);
                    },
                });

            }))
    ).appendTo('#tasks');
}

function showError(shortMessage, detailedMessage) {
    $("#error-message").text(shortMessage)
    $("<p>").text(detailedMessage).appendTo($('#show-error-details'))
    $("#validation-error").show();
}

function expandCollapse(id, type) {
    var hiddenPart = document.getElementById(id);
    var btn = document.getElementById(id + '-btn');
    if (hiddenPart.className.indexOf("w3-show") == -1) {
        hiddenPart.className += " w3-show";
        btn.innerText = 'Hide ' + type + ' details'
    } else {
        hiddenPart.className = hiddenPart.className.replace(" w3-show", "");
        btn.innerText = 'Show ' + type + ' details'
    }
}

function getRandomInt(max) {
    return Math.floor(Math.random() * max);
}