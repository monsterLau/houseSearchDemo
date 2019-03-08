
var options = {

    onFail: function () {
        alert($myform.getInvalid().length + ' invalid fields.')
    },

    inputs: {
        'password': {
            filters: 'required pass',
        },
        'username': {
            filters: 'required username',
        },
        'email': {
            filters: 'required email',
        },
        'phone': {
            filters: 'required phone',
        },'realName': {
            filters: 'required',
        }
    }

};

//检查输入是否为空
function isNull(name) {
    var Name=this.name;
    if(name.size=0||name == ""||name.replace(/(^s*)|(s*$)/g, "").length==0){
        alert(Name+"为空！")
        return false;
    }
}


$("#user_register_btn").click(function () {

    var username = $("#username").val();
    var password = $("#password").val();
    var phone=$("#phone").val();
    var email=$("#email").val();
    var realName=$("#realName").val();
    var header = $("meta[name='_csrf_header']").attr("content");
    var token = $("meta[name='_csrf']").attr("content");

    //判断用户名密码是否为空
    if (username.size = 0 || username == "" || username.replace(/(^s*)|(s*$)/g, "").length == 0) {
        alert("用户名为空");
        return false;

        if (password.size = 0 || password == "" || password.replace(/(^s*)|(s*$)/g, "").length == 0) {
            alert("密码为空");
            return false;
        }
    }

    $.ajax({
        url: "/registerAdmin",
        type: "post",
        data: {"username": username, "password": password,"phone":phone,"email":email,"realName":realName},
        async: false,
        // beforeSend: function (xhr) {
        //     xhr.setRequestHeader(header, token);
        // },
        success: function (result) {
            if (result.extend.sameName == "1") {
                alert("用户名重复！");
//                        window.location.href = "register";
            } else {
                alert("注册成功！")
                window.location.href="login";
            }
        },
        error: function (result) {
            alert("注册失败！");
        }
    })
});

var $registerForm = $('#registerForm').idealforms(options).data('idealforms');

$('#reset').click(function () {
    $registerForm.reset().fresh().focusFirst()
});

$registerForm.focusFirst();

