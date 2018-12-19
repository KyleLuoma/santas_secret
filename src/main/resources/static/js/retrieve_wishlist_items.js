retrieveWishListItems(1, "item_list_target");

function retrieveWishListItems(wishListId, htmlElementId) {
	/*
	var mockWishListItemListJSON = 
  	"[" +
    "   {\"id\": \"1\", \"wishListID\": \"1\", \"title\": \"Lego Saturn V\", \"description\": \"A big Lego rocket set\", \"URL\": \"www.lego.com\", \"purchased\": \"false\", \"purchasedByUserId\": \"0\", \"priority\":\"medium\"}," +
    "   {\"id\": \"2\", \"wishListID\": \"1\", \"title\": \"Beer mug\", \"description\": \"Stainless steel beer mug\", \"URL\": \"www.beermugs.com\", \"purchased\": \"true\", \"purchasedByUserId\": \"4\", \"priority\":\"medium\"}," +
    "   {\"id\": \"3\", \"wishListID\": \"1\", \"title\": \"Book V\", \"description\": \"The Art of War by Sun Tzu\", \"URL\": \"www.books.com\", \"purchased\": \"false\", \"purchasedByUserId\": \"0\", \"priority\":\"medium\"}"  +
    "]";
  */
  $.ajax({
  	//url:"/echo/json",
    url:"/desired_item/wish_list_items",
    //type: "POST",
    type: "GET",
    //data: {json: mockWishListItemListJSON},
    data: {wishListId: wishListId},
    datatype: "json",
    success: function(json) {
    	var table_string = "<table class='table'><tr></tr><th>Item:</th><th>Description:</th><th>Link:</th><th>Questions:</th></tr>";
      for (var i = 0; i < json.length; i++) {
      	table_string +=
        	"  <tr>"                                                      + "\n" + 
          "    <td>" + json[i].title + "</td>"                          + "\n" +
          "    <td>" + json[i].description + "</td>"                    + "\n" +
          "    <td>" + json[i].URL + "</td>"                            + "\n" +
          "    <td id='question_item_" + json[i].id + "'> </td>"        + "\n" +
          "  </tr>"                                                     + "\n"; 
      }
      table_string += "</table>"                                        + "\n";
      
    	$("#" + htmlElementId).html(table_string);
      $("#dump").html("Success");
    },
    error: function(err) {
    	$("#dump").html("Error");
    }
  })
}



