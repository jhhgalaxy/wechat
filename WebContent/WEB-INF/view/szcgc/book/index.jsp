<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;" name="viewport"/>
    <title>深圳担保服务平台</title>


    <%@ include file="../common/base_recommend.jsp" %>


    <!--phone750-->
    <%@ include file="../common/jspage/jspage_phone750.jsp" %>

    <!--选择框-->
    <!--
    <link href="/mobile/plugin/westvalley_wev8/szcgc/common/css/mobileSelect.css" rel="stylesheet" type="text/css">
    <script src="/mobile/plugin/westvalley_wev8/szcgc/common/js/mobileSelect.js" type="text/javascript"></script>
     -->
    <%@ include file="../common/jspage/jspage_mobileSelect.jsp" %>
    <!--  -->
    <!--
    <link  href="/mobile/plugin/westvalley_wev8/szcgc/common/css/Popup_donate.css" rel="stylesheet">
    <script src="/mobile/plugin/westvalley_wev8/szcgc/common/js/Popup.js"></script>
    -->
    <%@ include file="../common/jspage/jspage_popup.jsp" %>

    <%@ include file="../common/jspage/jspage_larea.jsp" %>

</head>
<body>
<div class="main_container">


    <!--表单-->
    <div>
        <form id="addBusinessForm">
            <div class="business">
                <%--<div>--%>
                <%--    <h1>《零距离话担保》书籍征订</h1>--%>
                <%--</div>--%>

                <li class="b-line">
                    <h3>书籍名称</h3>
                    <input type="text" id="bookname" name="bookname" value="《零距离话担保》" readonly=""
                           class="business_input"/>
                </li>
                <li class="b-line">
                    <h3>单价</h3>
                    <input type="text" id="price" name="price" value="每本定价75元,折扣价60元" readonly=""
                           class="business_input"/>
                </li>

                <li class="b-line">
                    <h3>购买数量(本)</h3>
                    <input type="text" id="quantity" name="quantity" placeholder="请输入购买数量" oninput="OnInput (event)" onpropertychange="OnPropChanged (event)" class="business_input"/>
                </li>
                <li class="b-line">
                    <h3>总价(元)</h3>
                    <input type="text" id="totalprice" name="totalprice" readonly="" class="business_input"/>
                </li>
                <li class="b-line">
                    <h3>单位名称</h3>
                    <input type="text" id="customcorp" name="customcorp" placeholder="请输入单位名称" class="business_input"/>
                </li>
                <li class="b-line">
                    <h3>收件人名称</h3>
                    <input type="text" id="customname" name="customname" placeholder="请输入收件人姓名" class="business_input"/>
                </li>
                <li class="b-line">
                    <h3>所在地区</h3>
                    <input class="business_input" id="diqLabel" name="diqLabel" type="text" readonly=""
                           placeholder="请选择所在地区"/>
                    <input type="hidden" id="diqValue" name="diqValue">
                    <input type="hidden" id="area" name="area">
                    <%--                    <div class="aui-cells-arrow"><i class="icon-arrow"></i></div>--%>
                </li>
                <li class="b-line">
                    <h3>详细地址</h3>
                    <input type="text" id="address" name="address" placeholder="请输入详细地址" class="business_input"/>
                </li>
                <li class="b-line">
                    <h3>电子邮箱</h3>
                    <input type="text" id="email" name="email" placeholder="请输入电子邮箱" class="business_input"/>
                </li>
                <li class="b-line">
                    <h3>手机号码</h3>
                    <input type="tel" id="cellphone" name="cellphone" placeholder="请输入手机号码"
                           class="business_input mobile"/>
                </li>
                <li class="b-line">
                    <h3>验证码</h3>
                    <input type="number" id="yanzm" name="yanzm" placeholder="请输入验证码" class="business_input yanzheng"/>
                    <button type="button" class="yanzhengma" onclick="sendSmsCode()">发送验证码</button>
                </li>


                <div class="reminder">
                    如单位征订，请填写单位名称</br>
<%--                    请将订阅款转至以下账户：</br>--%>
<%--                    收款银行： 中国平安银行深圳科技园支行</br>--%>
<%--                    收款帐户名：陈丽珍</br>--%>
<%--                    收款账号： 6230 5800 0008 1688 967</br>--%>
<%--                    备注信息： 请填写收件人名称。</br>--%>
<%--                    转账后请注意保存回单截图，我司将及时为您寄出书籍（邮费到付）。</br>--%>
                    （如有疑问，可致电：陈丽珍、0755-86971928、13751181720）</br>
                </div>
                <div class="button_submit" onclick="submit4()"><a href="#"><img
                        src="${pageContext.servletContext.contextPath}/resources/images/recommend/button_submit4.png"
                        border="0"/></a></div>
            </div>

        </form>
    </div>


</div>

</body>


<script type="text/javascript">
    var times = 120;

    function roof() {
        $(".yanzhengma").prop('disabled', true);// 验证码按钮不能再点击
        if (times == 0) {
            $('.yanzhengma').text('发送验证码(' + times + 's)');
            $('.yanzhengma').prop('disabled', false);
            $('.yanzhengma').text('发送验证码');
            times = 120;
            return
        }
        $('.yanzhengma').text('发送验证码(' + times + 's)');
        times--;
        setTimeout(roof, 1000);
    }

    // Firefox, Google Chrome, Opera, Safari, Internet Explorer from version 9
    function OnInput (event) {
        $("#totalprice").val(60*$("#quantity").val());
    }
    // Internet Explorer
    function OnPropChanged (event) {
        $("#totalprice").val(60*$("#quantity").val());
    }


    //提交申报
    function submit4() {
        if (checkMust()) {// 检查数据填写情况
            return;
        } else {// 数据校验通过
            checkSmsCode();
        }
    }

    function back() {
        window.history.go(-1);
    }

    //校验表单数据
    function checkMust() {
        if ($("#quantity").val() == "") {
            msg("请填写征订数量！");
            return true;
        }
        if ($("#customname").val() == "") {
            msg("请填写收件人姓名！");
            return true;
        }
        if ($("#diqLabel").val() == "") {
            msg("请填写所在区域！");
            return true;
        }
        if ($("#address").val() == "") {
            msg("请填写详细地址！");
            return true;
        }
        if ($("#cellphone").val() == "") {
            msg("请填写手机号码！");
            return true;
        }
        if ($("#email").val() == "") {
            msg("请填写电子邮箱！");
            return true;
        }
        if ($("#yanzm").val() == "") {
            msg("请填写验证码！");
            return true;
        }

    }


    // 发送验证码
    function sendSmsCode() {
        var checkPhone = $("#cellphone").val();
        if (!checkPhone) {
            $('.mobile').focus();
            document.querySelector('.mobile').placeholder = '手机号获取错误';
            return
        }
        //用户id
        var layerIndex = loading("正在发送请求...");// 显示加载中
        jQuery.ajax({
            type: 'POST',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {phone: checkPhone},
            url: "../message/sendSMSByPhone",
            dataType: "json",
            success: function (obj) {//提交成功
                endLoading(layerIndex);
                if (obj == null || typeof (obj) == 'undefined') {
                    msg("操作失败");
                    return;
                }
                if (obj.code == "0") { //执行成功
                    msg("验证码发送成功,2分钟内有效");
                    //短信发送的倒计时（剩余时间）
                    roof();
                } else {
                    if (obj.msg != "") {
                        msg(obj.msg);
                    } else {
                        msg("验证码发送失败");
                    }
                }
            },
            error: function (e) {//提交失败
                endLoading(layerIndex);
                msg("操作异常！");
            }
        });
    }

    // 校验验证码
    function checkSmsCode() {
        var checkPhone = $("#cellphone").val();
        var checkCode = $("#yanzm").val();
        if (!checkCode) {
            $('.yanzheng').focus();
            document.querySelector('.yanzheng').placeholder = '请填写验证码';
            return
        }

        var layerIndex = loading("正在校验...");// 显示加载中
        jQuery.ajax({
            type: 'POST',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: {code: checkCode, phone: checkPhone, checkType: "checkCode"},
            url: "../message/checkSmsCodeByPhone",
            dataType: "json",
            success: function (obj) {//提交成功
                endLoading(layerIndex);
                if (obj == null || typeof (obj) == 'undefined') {
                    msg("操作失败");
                    return;
                }
                if (obj.code == "0") { //执行成功

                    submitData();
                } else {
                    if (obj.msg != "") {
                        msg(obj.msg);
                    } else {
                        msg("验证码错误！");
                    }
                }
            },
            error: function (e) {//提交失败
                endLoading(layerIndex);
                msg("操作异常！");
            }
        });
    }

    // 提交
    //TODO: 修改URL
    function submitData() {
        $("#area").val(getDiq());// 地区
        var layerIndex = loading("校验成功，正在提交...");
        jQuery.ajax({
            type: 'POST',
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: $("#addBusinessForm").serialize(),
            url: "/book/purchase",
            dataType: "json",
            success: function (obj) {//提交成功
                endLoading(layerIndex);
                if (obj == null || typeof (obj) == 'undefined') {
                    msg("操作失败");
                    return;
                }
                if (obj.code == "0") { //执行成功
                    window.location.href = "/book/success";
                } else {
                    if (obj.msg != "") {
                        msg(obj.msg);
                    } else {
                        msg("提交失败");
                    }
                }
            },
            error: function (e) {//提交失败
                endLoading(layerIndex);
                msg("操作异常！");
            }
        });
    }

    // 获取地区
    function getDiq() {
        var diqLabel = $("#diqLabel").val();
        var reg = new RegExp(",", "g");//g,表示全部替换。
        var area = diqLabel.replace(reg, " ");
        return area;
    }

</script>

<script>
    var area1 = new LArea();
    area1.init({
        'trigger': '#diqLabel', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
        'valueTo': '#diqValue', //选择完毕后id属性输出到该位置
        'keys': {
            id: 'id',
            name: 'name'
        }, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
        'type': 1, //数据源类型
        'data': LAreaData //数据源
    });
</script>


</html>


