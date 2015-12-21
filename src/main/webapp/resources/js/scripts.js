/**
 * Created by kolyan on 20.12.15.
 */
$(document).ready(function() {
    $(document).on('click','.signup-tab',function(e) {
        e.preventDefault();
        $('#signup-taba').tab('show');
    });

    $(document).on('click','.signin-tab',function(e) {
        e.preventDefault();
        $('#signin-taba').tab('show');
    });

    $(document).on('click','.forgetpass-tab',function(e) {
        e.preventDefault();
        $('#forgetpass-taba').tab('show');
    });

    $('#loginModal').on('click', function() {
        $('#registration_fail').hide();
        $('#login_fail').hide();
    });

    $('#register_btn').click(function() {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            url:  '/api/register',
            dataType: 'json',
            data: JSON.stringify({
                email: $('#remail').val(),
                name: $('#username').val()
            }),
            success: function(result) {
                if(result.result) {
                    window.location = result.redirectUrl;
                } else {
                    $('#registration_fail').text(result.message);
                    $('#registration_fail').show();
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    });

    $('#login_btn').click(function() {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            url:  '/api/auth',
            dataType: 'json',
            data: JSON.stringify({
                email: $('#login_username').val(),
                password: $('#password').val()
            }),
            success: function(result) {
                if(result.result) {
                    window.location = result.redirectUrl;
                } else {
                    $('#login_fail').text(result.message);
                    $('#login_fail').show();
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    });


    $('#save_setting_btn').click(function() {
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            url:  '/api/user/settings',
            dataType: 'json',
            data: JSON.stringify({
                email: $('#setting-email').val(),
                name: $('#setting-name').val(),
                password: $('#setting-password').val()
            }),
            success: function(result) {
                $('#settings_saved').show();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert(jqXHR.status + ' ' + jqXHR.responseText);
            }
        });
    });
});