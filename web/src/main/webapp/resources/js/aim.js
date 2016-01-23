/**
 * Created by kolyan on 21.12.15.
 */
$(document).ready(function() {
    $('#container_aims').find(':button').click(function() {
        console.log($(event.target));
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            url:  '/api/aim/delete',
            dataType: 'json',
            data: JSON.stringify({
                id: $('#aim_id').val()
            }),
            success: function(result) {
                console.log(result);
                if(result.deleted) {
                    $('#aim'.concat(result.deletedId)).remove();
                } else {

                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    });
});