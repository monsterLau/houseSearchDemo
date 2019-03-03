$(function () {
    pn(1);
})

function pn(pn) {
    $.ajax({
        url: "/admin/adminFindUserOrder",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            console.log(result);
            buildHousePage(result);
            count = result.extend.success.pages;
            var list = result.extend.success.list;
            // t=list[number]
            list.forEach(function (t, number) {
                var houseId = t.houseId;
                console.log(number)
                // alert(houseId);
                $("#trline").append(
                    "<tr >\n" +
                    "<td>" + t.houseId + "</td>\n" +
                    "<td>" + t.tittle + "</td>\n" +
                    "<td>" + t.price + "</td>\n" +
                    "<td>" + t.users[0].username +  "</td>\n" +
                    "<td class='realName' id='rn" + t.houseId + "'>" + t.users[0].realName + "</td>\n" +
                    "<td>" + t.users[0].phone + "</td>\n" +
                    "<td>" + t.users[0].email + "</td>\n" +
                    "                    <td>\n" +
                    "                            <a href=\"/checkHouseDetailsById/" + t.houseId + " \" class=\"button  button-glow button-rounded button-raised button-primary\"><span class=\"glyphicon glyphicon-ok\" aria-hidden=\"true\"/>查看</a>\n" +
                    "                            <a id=\"" + t.houseId + "\" class=\"button button-glow button-rounded button-caution orderBtn \"> <span class=\"glyphicon glyphicon-trash\"\n" +
                    "                                                               aria-hidden=\"true\")'></span>处理</button></tr>"
                )
            })
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

$(document).on("click", ".orderBtn", function () {

    var houseId = $(this).attr("id");
    var username = $(this).parent().parent().find("td").eq(3).text();

    console.log(username)

    // alert(houseId)
    $.ajax({
        url: "/admin/delUserOrder",
        type: "POST",
        data: {
            houseId: houseId,
            username: username
        },
        success: function () {
            // alert("success!");
            window.location.href = "/admin/listOrder"
        },error:function () {
            alert("error")
        }
    })
})
