

function retrieveUserPools(userId) {
    var result = "";
    $.ajax({
        url: '/gift_pool/for_user',
        type: 'GET',
        data: {userId: userId},
        dataType: 'json',
        success: function(json) {
            document.getElementById("dump").innerHTML = JSON.stringify(json);
        },
        error: function() {
            console.log("Oops, something went wrong")
        }
    });
    return result;
}