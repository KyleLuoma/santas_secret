/**
 * Populates element with gift_pool_table id with table generating HTML containing
 * gift pools that userId is a member of.
 * @param userId user Id number associated with gift pools to appear in table
 * @returns {string}
 */
function retrieveUserPools(userId) {
    var result = "";
    $.ajax({
        url: '/gift_pool/for_user',
        type: 'GET',
        data: {userId: userId},
        dataType: 'json',
        success: function(json) {
            var table_string = "<table class='table'><tr></tr><th>Title:</th><th>Description:</th></tr>\n";
            //var poolArray = JSON.parse(json);

            for(var i = 0; i < json.length; i++) {
                table_string += "<tr><td>" + json[i].poolTitle + "</td><td>" + json[i].poolDescription + "</td></tr>\n";
                table_string += "  <tr>\n" + 
                                "    <td id='users_in_pool" + json[i].id + "'></td>\n" + 
                                "    <td id='lists_in_pool" + json[i].id + "'></td>\n" + 
                                "    <td id='gifts_purchased_in_pool" + json[i].id + "'>\n" + 
                                "    <td><a href='\\pool_view?poolId=" + json[i].id + "'>VIEW</a></td>\n" + 
                                "  </tr>";
            }
            table_string = table_string + "\n</table>";
            $("#dump").html(JSON.stringify(json));
            $("#gift_pool_table").html(table_string);
        },
        error: function() {
            console.log("Oops, something went wrong when trying to load gift pool data.");
            document.getElementById("gift_pool_table").innerHTML = "Unable to load gift pool data.";
        }
    });
    return result;
}
