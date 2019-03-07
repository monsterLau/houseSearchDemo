$(function () {
    pn(1);
})

var username = $('.username')[0].innerHTML;
//
// $(function () {
//     $.ajax({
//         url: "/user/userHouseOrder",
//         data: {username: username},
//         type: "GET",
//         success: function (result) {
//             console.log(result);
//             var list = result.extend.houseOrder;
//             // t=list[number]
//             list.forEach(function (t, number) {
//                 var houseId = t.houseId;
//                 // alert(houseId);
//                 $("#trline").append(
//                     // alert(houseId);
//                     "<tr>\n" +
//                     "<td>" + t.houseId + "</td>\n" +
//                     " <td>\n" +
//                     "<div class=\"col-xs-6 col-md-3\">\n" +
//                     " <img src=\"" + t.img + "\" style=\"width: 180px;height: 110px\"/>\n" +
//                     " </div>\n" +
//                     " </td>" +
//                     "<td>" + t.tittle + "</td>\n" +
//                     "<td>" + t.price + "</td>\n" +
//                     "<td>" + t.room + "</td>\n" +
//                     "<td>" + t.subway + "</td>\n" +
//                     "<td>" + t.area + "</td>\n" +
//                     "                    <td>\n" +
//                     "                            <a href=\"/checkHouseDetailsById/" + t.houseId + " \" class=\"button  button-glow button-rounded button-raised button-primary\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"/>查看</a>\n" +
//                     "                            <a  id=\"" + t.houseId + "\" class=\"button button-glow button-rounded button-caution delOrderBtn \"> <span class=\"glyphicon glyphicon-star\"\n" +
//                     "                                                               aria-hidden=\"true\"></span>取消收藏</a></tr>"
//                 )
//             })
//         }
//     })
// })


function pn(pn) {
    $.ajax({
        url: "/user/userHouseOrder",
        data: {username: username,"pn":pn},
        type: "GET",
        // data: ,
        async: false,
        // beforeSend: function (xhr) {
        //     xhr.setRequestHeader(header, token);
        // },
        success: function (result) {
            buildHousePage(result);
            console.log(result);
            count = result.extend.success.pages;
            var list = result.extend.success.list;
            // t=list[number]
            list.forEach(function (t, number) {
                var houseId = t.houseId;
                // alert(houseId);
                $("#trline").append(

                    "<tr>\n" +
                    "<td>" + t.houseId + "</td>\n" +
                    " <td>\n" +
                    "<div class=\"col-xs-6 col-md-3\">\n" +
                    " <img src=\"" + t.img + "\" style=\"width: 180px;height: 110px\"/>\n" +
                    " </div>\n" +
                    " </td>" +
                    "<td>" + t.tittle + "</td>\n" +
                    "<td>" + t.price + "</td>\n" +
                    "<td>" + t.room + "</td>\n" +
                    "<td>" + t.subway + "</td>\n" +
                    "<td>" + t.area + "</td>\n" +
                    "                    <td>\n" +
                    "                            <a href=\"/checkHouseDetailsById/" + t.houseId + " \" class=\"button  button-glow button-rounded button-raised button-primary\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"/>查看</a>\n" +
                    "                            <a id=\"" + t.houseId + "\" class=\"button button-glow button-rounded button-caution orderBtn delOrderBtn\"> <span class=\" glyphicon glyphicon-star\"\n" +
                    "                                                               aria-hidden=\"true\"></span>取消预约</a></tr>"

                )
            })

        },
        error: function (result) {
            alert("失败！");
        }
    })

}

$(document).on("click", ".delOrderBtn", function () {
    var username = $('.username')[0].innerHTML;
    var houseId = $(this).attr("id");

    $.ajax({
        url: "/user/delUserOrder",
        type: "POST",
        data: {"username": username, "houseId": houseId},
        success: function (result) {
            alert("取消成功");
            window.location.href = "/user/userOrder";
        }
    })
})

//分页
function buildHousePage(result) {
    $("#trline").empty();
    $("#page").empty();

    var ul=$("<ul ></ul>").addClass("pagination  pagination-lg");

    var firstPageLi=$("<li></li>").append($("<a></a>").append("首页").attr("href","#"));
    var prePageLi=$("<li></li>").append($("<a></a>").append("&laquo;"));
    if(result.extend.success.hasPreviousPage==false){
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    }else {
        firstPageLi.click(function () {
            pn(1);
        });
        prePageLi.click(function () {
            pn(result.extend.success.pageNum-1);
        });
    }
    var nextPageLi=$("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi=$("<li></li>").append($("<a></a>").append("末页").attr("href","#"));
    if(result.extend.success.hasNextPage==false){
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    }else {
        nextPageLi.click(function () {
            pn(result.extend.success.pageNum+1);
        });
        lastPageLi.click(function () {
            pn(result.extend.success.pages);
        });
    }
    ul.append(firstPageLi).append(prePageLi);
    $.each(result.extend.success.navigatepageNums,function (index,item) {

        var numLi=$("<li></li>").append($("<a></a>").append(item));
        if(result.extend.success.pageNum==item){
            numLi.addClass("active");
        }
        numLi.click(function () {
            pn(item);
        });
        ul.append(numLi);
    });
    ul.append(nextPageLi).append(lastPageLi);

    var navEle=$("<nav></nav>").append(ul);
    navEle.appendTo("#page");
}
