var count = 0;
// $.ajax({
//     url: "/allHouse",
//     type: "Get",
//     data: "pn="pn,
//     async: false,
//     // beforeSend: function (xhr) {
//     //     xhr.setRequestHeader(header, token);
//     // },
//     success: function (result) {
//         console.log(result.extend.success.pages);
//         count = result.extend.success.pages;
//         var list = result.extend.success.list;
//         // t=list[number]
//         list.forEach(function (t, number) {
//             var houseId = t.houseId;
//             // alert(houseId);
//             $("#trline").append(
//                 // alert(houseId);
//                 "<tr>\n" +
//                 "<td>" + t.houseId + "</td>\n" +
//                 " <td>\n" +
//                 "<div class=\"col-xs-6 col-md-3\">\n" +
//                 " <img src=\"" + t.img + "\" style=\"width: 180px;height: 110px\"/>\n" +
//                 " </div>\n" +
//                 " </td>" +
//                 "<td>" + t.tittle + "</td>\n" +
//                 "<td>" + t.price + "</td>\n" +
//                 "<td>" + t.room + "</td>\n" +
//                 "<td>" + t.subway + "</td>\n" +
//                 "<td>" + t.area + "</td>\n" +
//                 "                    <td>\n" +
//                 "                            <a href=\"/checkHouseDetailsById/" + t.houseId + " \" class=\"button  button-glow button-rounded button-raised button-primary\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"/>查看</a>\n" +
//                 "                            <a class=\"button button-glow button-rounded button-caution\"> <span class=\"glyphicon glyphicon-star\"\n" +
//                 "                                                               aria-hidden=\"true\"></span>收藏</a></tr>"
//             )
//         })
//
//     },
//     error: function (result) {
//         alert("失败！");
//     }
// })
$(function () {
    pn(1);
})

function pn(pn) {
    $.ajax({
        url: "/allHouse",
        type: "Get",
        data: "pn=" + pn,
        async: false,
        // beforeSend: function (xhr) {
        //     xhr.setRequestHeader(header, token);
        // },
        success: function (result) {
            buildHousePage(result);
            console.log(result.extend.success.pages);
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
                    "                            <a id=\"" + t.houseId + "\" class=\"button button-glow button-rounded button-caution orderBtn \"> <span class=\"glyphicon glyphicon-star\"\n" +
                    "                                                               aria-hidden=\"true\"></span>预约</a></tr>"
                )
            })

        },
        error: function (result) {
            alert("失败！");
        }
    })

}

$(document).on("click", ".orderBtn", function () {
    var username = $('.username')[0].innerHTML;
    var houseId = $(this).attr("id");
    // console.log(username+"------"+houseId)

    $.ajax({
        url: "/user/userOrder",
        data: {username: username, houseId: houseId},
        type: "POST",
        success: function (result) {
            console.log(username+"------"+houseId)
            alert("收藏成功！");
            window.location.href="/index";
        },
        error: function () {
            alert("您的权限错误")
        }
    })
});


//分页
function buildHousePage(result) {
    $("#trline").empty();
    $("#page").empty();

    var ul = $("<ul ></ul>").addClass("pagination  pagination-lg");

    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.extend.success.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        firstPageLi.click(function () {
            pn(1);
        });
        prePageLi.click(function () {
            pn(result.extend.success.pageNum - 1);
        });
    }
    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
    if (result.extend.success.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            pn(result.extend.success.pageNum + 1);
        });
        lastPageLi.click(function () {
            pn(result.extend.success.pages);
        });
    }
    ul.append(firstPageLi).append(prePageLi);
    $.each(result.extend.success.navigatepageNums, function (index, item) {

        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.extend.success.pageNum == item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            pn(item);
        });
        ul.append(numLi);
    });
    ul.append(nextPageLi).append(lastPageLi);

    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page");
}


$("#priceBtnHtL").click(function () {
    paixu(1,"/allHouseByPriceFromHighToLow");
    }
)


$("#priceBtnLtH").click(function () {
        paixu(1,"/allHouseByPriceFromLowToHigh");
    }
)


function paixu(pn,url) {
    $.ajax({
        url: url,
        type: "Get",
        data: "pn=" + pn,
        async: false,
        success: function (result) {
            $("#trline").empty();
            $("#page").empty();
            buildHousePage(result);
            count = result.extend.success.page;
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
                    "                            <a id=\"" + t.houseId + "\" class=\"button button-glow button-rounded button-caution orderBtn \"> <span class=\"glyphicon glyphicon-star\"\n" +
                    "                                                               aria-hidden=\"true\"></span>预约</a></tr>"
                )
            })

        },
        error: function (result) {
            alert("失败！");
        }
    })

}