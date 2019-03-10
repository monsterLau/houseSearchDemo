$(".confirmBtn").click(
    function () {
        var tittle = $("#tittle").val().trim();
        var price = $("#price").val().trim();
        var area = $("#area").val().trim();
        var housePhone = $("#housePhone").val().trim();
        var name = $("#name").val().trim();
        // alert(tittle);
        // return false;
        if (tittle == "") {
            alert("标题不能为空！");
            return false;
        }
        if (price == "" ) {
            alert("价格不能为空！");
            return false;
        }
        if (area =="" ) {
            alert("面积不能为空！");
            return false;
        }
        if (housePhone == "" ) {
            alert("联系号码不能为空！");
            return false;
        }
        if (name == "" ) {
            alert("联系人不能为空！");
            return false;
        }

        var regPhone = /^1[34578]\d{9}$/;
        if (!regPhone.test(housePhone)) {
            alert("请输入正确的电话号码！")
            return false;
        }

        var regPrice = /^(?!0+(?:\\.0+)?$)(?:[1-9]\\d*|0)(?:\\.\\d{1,2})?$/;
        if (!regPrice.test(price)) {
            alert("请输入正确的金额！")
            return false;
        }

        var regName=/^[\u4E00-\u9FA5]{2,10}$/;   /*定义验证表达式*/
        if (!regName.test(name)) {   /*进行验证*/
            alert("请输入正确的联系人！")
            return false;
        }
    }
)
