$( document ).ready(function() {
    var bookName = $('#bookName').val()
    $.get( "theBigBook/book/"+bookName, function( data ) {
        alert(data);
    });
});