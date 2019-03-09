var houseId = $("#button").val();
var username = $(".username")[0].innerHTML;

// $.ajax({
//     url: "/allHouseByHouseUsername",
//     type: "GET",
//     data: "pn=" + pn,
//     async: false,
//     // beforeSend: function (xhr) {
//     //     xhr.setRequestHeader(header, token);
//     // },
//     success: function (result) {
//         console.log(username)
//         console.log(result.extend.success.list);
//         var list = result.extend.success.list;
//         // t=list[number]
//         list.forEach(function (t, number) {
//             $("#trline").append("<tr>\n" +
//                 "<td>" + t.houseId + "</td>\n" +
//                 "<td><img src=\"" + t.img + "\" style=\"width: 150px;height: 100px\"/></td>\n" +
//                 "<td>" + t.tittle + "</td>\n" +
//                 "<td>" + t.price + "</td>\n" +
//                 "<td>" + t.room + "</td>\n" +
//                 "<td>" + t.subway + "</td>\n" +
//                 "<td>" + t.area + "</td>\n" +
//                 // "<td>" + t.操作 + "</td>\n" +
//                 " \n" +
//                 "<td>\n" +
//                 "<a href=\"/admin/updateHouse/" + t.houseId + "\" class=\"button button-glow button-rounded button-raised button-primary\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"/>修改信息</a>\n" +
//                 "<a onclick='delHouse (" + t.houseId + ")' class=\"button button-glow button-rounded button-caution \"> <span class=\"glyphicon glyphicon-star\"\n" +
//                 "aria-hidden=\"true\"></span>下架房屋</a>\n" +
//                 "\n" +
//                 "</td>\n" +
//                 "</tr>")
//         })
//
//     },
//     error: function (result) {
//         alert("失败！");
//     }
// });
$(function () {
    pn(1);
})
function pn(pn) {
    $.ajax({
        url: "/allHouseByHouseUsername",
        type: "GET",
        data: "pn=" + pn,
        async: false,
        success: function (result) {
            buildHousePage(result);
            console.log(username)
            console.log(result.extend.success.list);
            var list = result.extend.success.list;
            // t=list[number]
            list.forEach(function (t, number) {
                $("#trline").append("<tr>\n" +
                    "<td>" + t.houseId + "</td>\n" +
                    "<td><img src=\"" + t.img + "\" style=\"width: 150px;height: 100px\"/></td>\n" +
                    "<td>" + t.tittle + "</td>\n" +
                    "<td>" + t.price + "</td>\n" +
                    "<td>" + t.room + "</td>\n" +
                    "<td>" + t.subway + "</td>\n" +
                    "<td>" + t.area + "</td>\n" +
                    // "<td>" + t.操作 + "</td>\n" +
                    " \n" +
                    "<td>\n" +
                    "<a href=\"/admin/updateHouse/" + t.houseId + "\" class=\"button button-glow button-rounded button-raised button-primary\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"/>修改信息</a>\n" +
                    "<a onclick='delHouse (" + t.houseId + ")' class=\"button button-glow button-rounded button-caution \"> <span class=\"glyphicon glyphicon-star\"\n" +
                    "aria-hidden=\"true\"></span>下架房屋</a>\n" +
                    "\n" +
                    "</td>\n" +
                    "</tr>")
            })

        },
        error: function (result) {
            alert("失败！");
        }
    });
}


function delHouse(id) {
// console.log(id)
    var houseId = id;
    $.ajax({
        url: "/admin/deleteHouse/" + houseId,
        type: "POST",
        data: { houseId: houseId},
        success: function () {
            // alert("success!");
            window.location.href = "/admin/listHouse";
        }
    })
}


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