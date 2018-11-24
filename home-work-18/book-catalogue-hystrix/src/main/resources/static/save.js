$(document).ready(function () {
    $('#add-form').submit(function (event) {
        event.preventDefault();
        var bookdto = {};
        bookdto["name"] = $("#name").val();
        bookdto["genre"] = $("#genre").val();
        bookdto["authors"] = $("#authors").val();
        bookdto["adult"] = $("#adult").is(':checked');
        $.ajax({
            url: "/save",
            type: "post",
            contentType: "application/json; charset=utf-8",
            dataType: 'json',
            data: JSON.stringify(bookdto),
            success: function (data) {
                var option = '<option value="' + data.id + '">' + data.name + ", " + data.genre + ", " + data.authors + '</option>';
                $("#book-list").append(option);
            },
            error: function (e) {

            }
        });
    });
});



        