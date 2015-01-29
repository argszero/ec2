var indexPage = function(){
    $(document.body).empty();
    var refreshBook = function(){
        var newBooks = $('<div></div>').appendTo(document.body);
        return function(){
            newBooks.empty();
            $.get( "theBigBook/book", function( data ) {
                    $($(data).map(function(i,v){
                        var name = v.match(/.*\/([^\/]*)$/)[1];
                        return '<div><a href="theBigBook/book'+v+'">'+name+'</a></div>';
                    }).get().join("")).appendTo(newBooks).button()
            });
        }
    }();
    var bookFormDialog = $('<div>'
        +'  <div>title: <input size="15" name="title" class="text ui-widget-content ui-corner-all"/></div>'
        +'</div>').appendTo(document.body).dialog({
            buttons:{
                "Ok":function(){
                    var title =$('input[name="title"]',this).val();
                    var self = $(this);
                    $.ajax({
                        type: "PUT",
                        url: "theBigBook/book/"+title
                    }).done(function(data) {
                        if(!data.success){
                            alert(data.data);
                        }else{
                            $(self).dialog( "close" );
                            refreshBook();
                        }
                    });
                }
            }
        }).dialog( "close" );
    $('<button class="button">create new book</button>')
    .button()
    .appendTo(document.body)
    .click(function(){
        bookFormDialog.dialog("open");
    });
    refreshBook();
}

$( document ).ready(function() {
    indexPage();
});