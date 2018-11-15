function retrieveUserWishLists(userId, htmlElementId) {

	var mockWishListJSON = 
  	"[" +
    "		{\"id\": \"1\", \"userId\": \"1\", \"title\": \"Kyle\'s Wishlist 1\", \"description\": \"My nice wishlist\"}," +
    "   {\"id\": \"1\", \"userId\": \"1\", \"title\": \"Kyle\'s Wishlist 2\", \"description\": \"My naughty wishlist\"}" +
    "]";

	$.ajax({
  	url: "/echo/json",
    //url: "wish_list/by_user_id",
    type: "POST",
    //type: "GET",
    data: {json: mockWishListJSON},
    //data: {userId: 3},
    datatype: "json",
    success: function(json) {
      var table_string = "<table class='table'><tr></tr><th>Title:</th><th>Description:</th></tr>\n";
        //var poolArray = JSON.parse(json);
        for (var i = 0; i < json.length; i++) {
          table_string += 
          "  <tr><td colspan='2'>" + json[i].title + 
          "    </td><td colspan='2'>" + json[i].description + 
          "    </td>" + 
          "  </tr>\n";
          table_string += 
          "  <tr>\n" +
          "    <td id='items_purchased_list" + json[i].id + "'></td>\n" +
          "    <td id='item_questions_list" + json[i].id + "'></td>\n" +
          "    <td id='items_visibile_in" + json[i].id + "'>\n" +
          "    <td><a href='\\list_view?listId=" + json[i].id + "'>VIEW</a></td>\n" +
          "  </tr>";
        }
        table_string = table_string + "\n</table>";
        $("#" + htmlElementId).html(table_string);
        $("#dump").html(JSON.stringify(json));
    },
    error: {
    
    }
  })
}
