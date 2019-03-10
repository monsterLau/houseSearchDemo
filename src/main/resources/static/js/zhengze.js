function isNum(str) {
    var reg = /^[0-9]$/;
    /*定义验证表达式*/
    return reg.test(str);
    /*进行验证*/
}

function is(str) {
    //中文、英文、数字但不包括下划线等符号
    var reg = /^[\u4E00-\u9FA5A-Za-z0-9]+$ 或 ^[\u4E00-\u9FA5A-Za-z0-9]{2,20}$;
    return reg.test(str);
}