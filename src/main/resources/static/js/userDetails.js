// var username = $("#username").val();

$(function () {
    var username = $('.username')[0].innerHTML;
    $.ajax({
        type: 'GET',
        async: false,
        url: '/user/getUserDetails?username=' + username,
        success: function (result) {
            console.log(result)
            $('#realName').val(result.realName);
            $('#email').val(result.email);
            $('#phone').val(result.phone);
        }
    })

});

$("#updateConfirmButton").click(function () {
    var username = $(".username")[0].innerHTML;
    var realName = $("#realName").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    // alert(realName);
    $.ajax({
        url: "/user/updateUser",
        data: {username: username, realName: realName, phone: phone, email: email},
        type: "POST",
        success: (function (result) {
            console.log(result);
            window.location.href='/index.html';
        })
    })
})