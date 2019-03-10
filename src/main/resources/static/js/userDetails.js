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
            window.location.href = '/index.html';
        })
    })
})

$("#updateConfirmButton").click(function () {
    var realName = $("#realName").val();
    var email = $("#email").val();
    var phone = $("#phone").val();

    if (realName == "") {
        alert("名称不能为空！");
        return false;
    }
    if (email == "") {
        alert("邮箱不能为空！");
        return false;
    }
    if (phone == "") {
        alert("手机号码不能为空！");
        return false;
    }

    var regPhone = /^1[34578]\d{9}$/;
    if (!regPhone.test(phone)) {
        alert("请输入正确的电话号码！")
        return false;
    }

    var regName = /^[\u4E00-\u9FA5]{2,10}$/;
    /*定义验证表达式*/
    if (!regName.test(realName)) {   /*进行验证*/
        alert("请输入正确的称呼！")
        return false;
    }

    var regEmail = /[^@]+@[^@]/;
    /*定义验证表达式*/
    if (!regEmail.test(email)) {   /*进行验证*/
        alert("请输入正确的邮箱！")
        return false;
    }
})