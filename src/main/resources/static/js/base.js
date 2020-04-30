/**
 * Created by Administrator on 2017/6/7 0007.
 */

function fileUpload (o) {  //onchange在你选择完文件时触发
   var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
    var msg;
    var formData = new FormData(); //通过FormData对象可以组装一组用 [XMLHttpRequest]发送请求的键/值对,它可以更灵活方便的发送表单数据。
    var fileList = o.files; //通过input的files属性获得fileList
    for (var i = 0; i < fileList.length; i++) {
        var file = fileList[i];
        formData.append('file', file);
    }
    $.ajax({
        //请求方式
       /* headers:{header:token},*/
        type: "POST",
        //请求的媒体类型
        contentType: false,
        async:false,
        processData: false, // 告诉jQuery不要去处理发送的数据
        //请求地址
        url: "/pm/pm_file/saveFile",
        //数据，json字符串
        data: formData,
        cache: false,
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        //请求成功
        success: function (result) {
            msg = result;
        },
        //请求失败，包含具体的错误信息
        error: function (e) {
            msg = e;
        }
    });
   return msg;
}

//字符串转换时间戳
function getTimestamp(strDate){
    if(!strDate){
        return null;
    }
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
            function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
    var timestamp=Math.round(date.getTime());

    return timestamp;
}
//获取查询参数
function getCondition (jqObj){
    var data = new Object();
    jqObj.find("input").each(function(){
        var name = $(this).attr("name");
        if (name && $(this).val()){
            data[name] =  $(this).val();
        }
    })
    jqObj.find("select").each(function(){
        var name = $(this).attr("name");
        if (name && $(this).val()){
            data[name] =  $(this).val();
        }
    })
    return data;
}
//获取跳转参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}
//赋值
function setData (obj,jqObj) {
    jqObj.find("input").each(function(){
        var name = $(this).attr("name");
        if(name && obj[name]){
            $(this).val(obj[name]);
        }
    });
    jqObj.find("select").each(function(){
        var name = $(this).attr("name");
        if (name && $(this).val()){
            data[name] =  $(this).val();
        }
    })
}
//取值
function getData (jqObj) {
    //var value  = $('input[name="radioName"]:checked').val(); //获取被选中Radio的Value值
    var  data = new Object();
    jqObj.find("input:text").each(function(){
        var name = $(this).attr("name");
        if(name  && $(this).val()){
            data[name] = $(this).val()
        }
    });
    jqObj.find("input:radio:checked").each(function(){
        var name = $(this).attr("name");
        if(name  && $(this).val()){
            data[name] = $(this).val()
        }
    });
    jqObj.find("textarea").each(function(){
        var name = $(this).attr("name");
        if(name && $(this) && $(this).val()){
            data[name] = $(this).val()
        }
    });
    jqObj.find("select").each(function(){
        var name = $(this).attr("name");
        if (name && $(this).val()){
            data[name] =  $(this).val();
        }
    });
    jqObj.find("input:checkbox").each(function(){
        var name = $(this).attr("name");
        if (name && $(this).val()) {
            if (this.checked) {
                if(data[name]){
                    data[name] += ","+ $(this).val();
                }else {
                    data[name] = $(this).val();
                }
            }
        }
    });
    return data;
}
//清空
function empty(jqObj){
    jqObj.find("input").each(function(){
        if($(this).attr("type") == "button"){
            return;
        }
        $(this).val("");
    });
    jqObj.find("select").each(function(){
        $(this).val("");
    });
    jqObj.find("img").each(function(){
        $(this).attr("src","");
    })
}
//封装ajax
$.requestAjax = function(options) {
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
    var settings = $.extend({
        type : "post",
        dataType : "json",
        contentType :'application/x-www-form-urlencoded;charset=utf-8',
        data : {},
        httpcode : {
            success : 200,
            sessionOut : 100
        }
    }, options || {});
    // 调用ajax
    return $.ajax({
        options : settings.options,
        async : settings.async,
        beforeSend : settings.beforeSend,
        cache : settings.cache,
        complete : settings.complete,
        contentType : settings.contentType,
        context : settings.context,
        data : settings.data,
        dataFilter : settings.dataFilter,
        dataType : settings.dataType,
        error : settings.error,
        global : settings.global,
        ifModified : settings.ifModified,
        jsonp : settings.jsonp,
        jsonpCallback : settings.jsonpCallback,
        password : settings.password,
        processData : settings.processData,
        scriptCharset : settings.scriptCharset,
        success : settings.success,
        traditional : settings.traditional,
        timeout : settings.timeout,
        type : settings.type,
        url : settings.url,
        username : settings.username,
        xhr : settings.xhr,
        headers : settings.headers,
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
    });
}
//封装ajax
$.requestJsonAjax = function(options) {
    var header = $("meta[name='_csrf_header']").attr("content");
    var token =$("meta[name='_csrf']").attr("content");
    var settings = $.extend({
        type : "post",
        dataType : "json",
        contentType : 'application/json;charset=utf-8', //设置请求头信息
        data : {},
        httpcode : {
            success : 200,
            sessionOut : 100
        }
    }, options || {});
    //
    // 调用ajax
    return $.ajax({
        options : settings.options,
        async : settings.async,
        beforeSend : settings.beforeSend,
        cache : settings.cache,
        complete : settings.complete,
        contentType : settings.contentType,
        context : settings.context,
        data : JSON.stringify(settings.data),
        dataFilter : settings.dataFilter,
        dataType : settings.dataType,
        error : settings.error,
        global : settings.global,
        ifModified : settings.ifModified,
        jsonp : settings.jsonp,
        jsonpCallback : settings.jsonpCallback,
        password : settings.password,
        processData : settings.processData,
        scriptCharset : settings.scriptCharset,
        success : settings.success,
        traditional : settings.traditional,
        timeout : settings.timeout,
        type : settings.type,
        url : settings.url,
        username : settings.username,
        xhr : settings.xhr,
        headers : settings.headers,
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
    });
}
//加小数判断,千位符格式化
function thousandBitSeparator(num) {
    return num && (num.toString().indexOf('.') != -1 ? num.toString().replace(/(\d)(?=(\d{3})+\.)/g, function($0, $1) {
            return $1 + ",";
        }) : num.toString().replace(/(\d)(?=(\d{3}))/g, function($0, $1) {
            return $1 + ",";
        }));
}

//获取当前时间，格式YYYY-MM-DD
function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = year + seperator1 + month + seperator1 + strDate;
    return currentdate;
}

//计算时间差
function timeDifference(startDate, endDate){
    var date1= startDate;  //开始时间
    var date2 = endDate;    //结束时间
    //时间差的毫秒数
    var date3 = date2.getTime() - new Date(date1).getTime();
    //计算出相差天数
    var days=Math.floor(date3/(24*3600*1000));
    //计算出小时数
    var leave1=date3%(24*3600*1000);
    //计算天数后剩余的毫秒数
    var hours=Math.floor(leave1/(3600*1000));
    //计算相差分钟数
    var leave2=leave1%(3600*1000) ;
    //计算小时数后剩余的毫秒数
    var minutes=Math.floor(leave2/(60*1000));
    //计算相差秒数
    var leave3=leave2%(60*1000);
    //计算分钟数后剩余的毫秒数
    var seconds=Math.round(leave3/1000);
    //alert(" 相差 "+days+"天 "+hours+"小时 "+minutes+" 分钟"+seconds+" 秒")
    var result='';
    if(days){
        result += days+'天';
    }
    if(hours){
        result += hours+"小时";
    }
    if(minutes){
        result += minutes+'分钟';
    }
    return result;
}

//验证手机号
function checkPhone(phone){
    if(!(/^1(3|4|5|7|8)\d{9}$/.test(phone))){
        return false;
    }else{
        return true;
    }
}

// validation 验证手机号 用于 admin-webApp
function validationPhone(e){
    var phone = e.value,
        parent = $(e).parent();
    if((/^1(3|4|5|7|8)\d{9}$/.test(phone))){
        parent.find('.wrong').remove()
    }else{
        if($('.wrong').length==0){
            parent.append('<p class="wrong">手机号码输入有误</p>')
        }
    }
}

// checkIdCard 验证身份证号 用于 admin-webApp
function checkIdCard(e){
    var idCard = e.value,
        parent = $(e).parent();
    //15位和18位身份证号码的正则表达式
    var regIdCard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;

    //如果通过该验证，说明身份证格式正确，但准确性还需计算
    if(regIdCard.test(idCard)){
        if(idCard.length==18){
            var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
            var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
            var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
            for(var i=0;i<17;i++){
                idCardWiSum+=idCard.substring(i,i+1)*idCardWi[i];
            }

            var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
            var idCardLast=idCard.substring(17);//得到最后一位身份证号码

            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
            if(idCardMod==2){
                if(idCardLast=="X"||idCardLast=="x"){
                    parent.find('.wrong').remove()
                }else{
                    if($('.wrong').length==0){
                        parent.append('<p class="wrong">身份证号码输入有误</p>')
                    }
                }
            }else{
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                if(idCardLast==idCardY[idCardMod]){
                    parent.find('.wrong').remove()
                }else{
                    if($('.wrong').length==0){
                        parent.append('<p class="wrong">身份证号码输入有误</p>')
                    }
                }
            }
        }
    }else{
        if($('.wrong').length==0){
            parent.append('<p class="wrong">身份证格式不正确</p>')
        }
    }
}

//身份证校验
function validateIdCard(idCard){
    //15位和18位身份证号码的正则表达式
    var regIdCard=/^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;

    //如果通过该验证，说明身份证格式正确，但准确性还需计算
    if(regIdCard.test(idCard)){
        if(idCard.length==18){
            var idCardWi=new Array( 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ); //将前17位加权因子保存在数组里
            var idCardY=new Array( 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ); //这是除以11后，可能产生的11位余数、验证码，也保存成数组
            var idCardWiSum=0; //用来保存前17位各自乖以加权因子后的总和
            for(var i=0;i<17;i++){
                idCardWiSum+=idCard.substring(i,i+1)*idCardWi[i];
            }

            var idCardMod=idCardWiSum%11;//计算出校验码所在数组的位置
            var idCardLast=idCard.substring(17);//得到最后一位身份证号码

            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X
            if(idCardMod==2){
                if(idCardLast=="X"||idCardLast=="x"){
                    //alert("恭喜通过验证啦！");
                    return false;
                }else{
                    //alert("身份证号码错误！");
                    return true;
                }
            }else{
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码
                if(idCardLast==idCardY[idCardMod]){
                    //alert("恭喜通过验证啦！");
                    return false;
                }else{
                    //alert("身份证号码错误！");
                    return true;
                }
            }
        }
    }else{
        //alert("身份证格式不正确!");
        return true;
    }
}

//银行卡校验
function CheckBankNo(t_bankno) {
    var bankno = $.trim(t_bankno.val());
    if(bankno == "") {
        //alert("请填写银行卡号");
        return false;
    }
    if(bankno.length < 16 || bankno.length > 19) {
        //alert("银行卡号长度必须在16到19之间");
        return false;
    }
    var num = /^\d*$/; //全数字
    if(!num.exec(bankno)) {
        //alert("银行卡号必须全为数字");
        return false;
    }
    //开头6位
    var strBin = "10,18,30,35,37,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,58,60,62,65,68,69,84,87,88,94,95,98,99";
    if(strBin.indexOf(bankno.substring(0, 2)) == -1) {
        //alert("银行卡号开头6位不符合规范");
        return false;
    }
    //Luhm校验（新）
    if(!luhmCheck(bankno))
        return false;
    //alert("验证通过!");
    return true;
}
//显示退出
function shoeLogout(){
    $(".login").show('slow');
}


//只能输入数字
function checkNumber(number) {
    if(/[^\d]/g.test(number)){
        return true;
    }else{
        return false;
    }
}

//金额校验(保留5位小数) true  通过  false 未通过
function checkMoney(money) {
    var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,5})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;
    if(reg.test(money)) {
        return true
    }else {
        return false;
    }
}
//只能输入数字
function nullVerification(o) {
    var flag = true;
   $(o).find("[check]").each(function(){
         if(!$(this).val()){
             $(this).css("border-color","red");
             flag =  false;
         }
   })
    return flag;

}

String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, '');
}