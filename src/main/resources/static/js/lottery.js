$(function () {
    /**
     * 红球选择逻辑
     */
    $(".ball-red").click(function () {
        var isSelected = $(this).is(".ball-red-selected");
        if (isSelected) {
            $(".ball-red-selected").removeClass("ball-red-selected");
        } else {
            var count = $(".ball-red-selected").length;
            if (count >= 6) {
                alert("最多只能选择六个球！");
            } else {
                $(this).addClass("ball-red-selected");
            }
        }
    });
    /**
     * 蓝球选择逻辑
     */
    $(".ball-blue").click(function () {
        //移除所有被选择的蓝球的样式
        $(".ball-blue-selected").removeClass("ball-blue-selected");
        //给当前的蓝球加上被选择的样式
        $(this).addClass("ball-blue-selected");

    });
})

/**
 * 获取被选择的球的号码信息，封装成一个json字符串
 */
function getResult() {
    //找到所有被选择的红球
    var redBalls = "";
    $(".ball-red-selected").each(function (i,ball) {
        //获取内个球的号码
        redBalls += "," + $(ball).text();
    })
    redBalls = redBalls.substr(1);

    //获取被选择的蓝球的号码
    var blueBall = $($(".ball-blue-selected").get(0)).text();

    return {
        red : redBalls,
        blue: blueBall
    }
}

/**
 * 随机获取一注双色球
 */
function randomOne() {
    console.log("进入随机选球方法");
    //清空所有球的选择样式
    $(".ball-red-selected").removeClass("ball-red-selected");
    //限制红球的数量最多为6个
    var redLen = $(".ball-red-selected").length;
    while (redLen < 6) {
        //随机从33个红色球中选取一个
        var i = Math.floor(Math.random()*33);
        $($(".ball-red").get(i)).addClass("ball-red-selected");
    }
    //清除蓝色球的样式
    $(".ball-blue-selected").removeClass("ball-blue-selected");
    var j = Math.floor(Math.random()*16);
    $($(".ball-blue").get(j)).addClass("ball-blue-selected");
}

/**
 * 通过ajax发送数据到后台
 */
function sendToBackend(arr) {
    var url = "/cart/addToCart";
    //将数据转化为json字符串
    var data = {
        "balls": JSON.stringify(arr)
    };
    $.post(url,data,function (result) {
        //获取购买商品的数量,并赋值给购物车图标
        $("#cartSizeId").text(result.cartSize);
    },"json");
}


/**
 * 添加一注彩票进入购物车
 */
function addOne() {
    //获取所有被选择红球的数量
    var redLen = $(".ball-red-selected").length;
    console.log("红球:"+redLen);
    //获取被选择的篮球的数量
    var blueLen = $(".ball-blue-selected").length;
    console.log("蓝球:"+blueLen);
    if (redLen == 0 && blueLen == 0) {
        randomOne();
    }else if (redLen != 6 || blueLen != 1) {
        alert("请选择6个红球,1个蓝球");
    }else {
        var data = getResult();
        var arr = Array();
        arr.push(data);

        sendToBackend(arr);
    }
}

/**
 * 机选之后加入购物车
 */
$("#btn_random").click(function () {
    //获取随机选择几注
    var count = $("#selectId").val();
    //创建一个数组存储球的信息
    var arr = Array();
    for (var i = 0;i < count;i++){
        //随机产生一组双色球
        randomOne();
        //获取彩票随机的结果
        var ball = getResult();
        //添加进数组
        arr.push(ball);
    }
    //发送到后台
    sendToBackend(arr);
});