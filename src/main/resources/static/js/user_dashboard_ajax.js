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
            var table_string = "<table class='table'><tr></tr><th>Title:</th><th>Description:</th></tr>";
            //var poolArray = JSON.parse(json);

            for(var i = 0; i < json.length; i++) {

                table_string = table_string + "<tr><td>" + json[i].poolTitle + "</td><td>" + json[i].poolDescription + "</td></tr>";
            }

            table_string = table_string + "</table>";
            document.getElementById("dump").innerHTML = JSON.stringify(json);
            document.getElementById("gift_pool_table").innerHTML = table_string;
        },
        error: function() {
            console.log("Oops, something went wrong")
        }
    });
    return result;
}