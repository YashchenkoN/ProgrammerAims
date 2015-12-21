/**
 * Created by kolyan on 21.12.15.
 */
$(document).ready(function() {
    $('#delete_aim').click(function() {
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
                if(result.isDeleted) {
                    alert('#aim'.concat(result.deletedId));
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