//$("#test").html();

//retrieveUserPools(1);

/**
 * Populates element with gift_pool_table id with table generating HTML containing
 * gift pools that userId is a member of.
 * @param userId user Id number associated with gift pools to appear in table
 * @returns {string}
 */
function retrieveUserPools(userId) {
  var jsonString = 
    "[" + 
    "  { \"id\": \"1\", \"poolTitle\": \"Pool title\", \"poolDescription\": \"Pool Description\" }," +
    "  { \"id\": \"3\", \"poolTitle\": \"Third Pool title\", \"poolDescription\": \"Third Pool Description\" }," +
    "  { \"id\": \"2\", \"poolTitle\": \"Second Pool title\", \"poolDescription\": \"Second Pool Description\" }" +
    "]";
  
  var result = "";
  
  $.ajax({
    //url: "/echo/json/",
      url: 'gift_pool/for_user',
    type: 'GET',
    data: {
      userId: userId
      //json: jsonString
    },
    dataType: 'json',
    success: function(json) {
      var table_string = "<table class='table'><tr></tr><th colspan=2>Title:</th><th colspan=2>Description:</th></tr>\n";
      //var poolArray = JSON.parse(json);
      var poolArray = [];

      for (var i = 0; i < json.length; i++) {
        poolArray.push(json[i].id);
        table_string += "<tr><td colspan=2>" + json[i].poolTitle + "</td><td colspan=2>" + json[i].poolDescription + "</td></tr>\n";
        table_string += 
          "  <tr>\n" +
          "    <td id='users_in_pool" + json[i].id + "'></td>\n" +
          "    <td id='items_in_pool" + json[i].id + "'></td>\n" +
          "    <td id='gifts_purchased_in_pool" + json[i].id + "'>\n" +
          "    <td><a href='\\pool_view?poolId=" + json[i].id + "'>VIEW</a></td>\n" +
          "  </tr>";
      }
      table_string = table_string + "\n</table>";
      $("#dump").html(JSON.stringify(json));
      $("#gift_pool_table").html(table_string);
      populatePoolMetaData(poolArray);
    },
    error: function() {
      console.log("Oops, something went wrong when trying to load gift pool data.");
      document.getElementById("gift_pool_table").innerHTML = "Unable to load gift pool data.";
    }
  });
  return result;
}

function populatePoolMetaData(poolIds) {
	var metaString = 
  	"[" +
    "  { \"poolId\": \"1\", \"userId\": \"1\", \"numUsers\": \"12\", \"numItems\": \"23\", \"numGiftsPurchased\": \"4\" }," +
    "  { \"poolId\": \"2\", \"userId\": \"1\", \"numUsers\": \"21\", \"numItems\": \"45\", \"numGiftsPurchased\": \"19\" }," +
    "  { \"poolId\": \"3\", \"userId\": \"1\", \"numUsers\": \"3\", \"numItems\": \"9\", \"numGiftsPurchased\": \"2\" }" +
    "]";
    
  $("#test").html(JSON.stringify(JSON.parse(metaString)) + " length: " + JSON.parse(metaString).length);
	/*for(var i = 0; i < poolIds.length; i++) {
    $("#users_in_pool" + poolIds[i]).html("12 users in pool");
    $("#lists_in_pool" + poolIds[i]).html("23 of your items in pool");
    $("#gifts_purchased_in_pool" + poolIds[i]).html("You have purchased " + 3 + " gifts");
  }*/
	  
  $.ajax({
    //url: "/echo/json/",
    url: 'gift_pool/user_pool_meta_data',
    type: 'GET',
    data: {
      userId: userId
      //json: metaString
    },
    dataType: 'json',
    success: function(json) {
      for(var i = 0; i < json.length; i++) {
          $("#users_in_pool" + json[i].poolId).html(json[i].numUsers + " users in pool");
          $("#items_in_pool" + json[i].poolId).html(json[i].numItems + " of your items in pool");
          $("#gifts_purchased_in_pool" + json[i].poolId).html(json[i].numGiftsPurchased + " gifts purchased by you");
        }
    },
    error: function() {
      console.log("Oops, something went wrong when trying to load pool meata data.");
      $("#errormsg").html("Error in populatePoolMetaData ");
    }
  });
}
