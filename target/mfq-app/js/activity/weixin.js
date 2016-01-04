// JavaScript Document
wx.ready(function () {
  // 1 判断当前版本是否支持指定 JS 接口，支持批量判断
  document.querySelector('#checkJsApi').onclick = function () {
    wx.checkJsApi({
      jsApiList: [
        'getNetworkType',
        'previewImage'
      ],
      success: function (res) {
        alert(JSON.stringify(res));
      }
    });
  };
  
  document.querySelector('#share_pyq').onclick = function (){
	    wx.onMenuShareTimeline({
	        title: '互联网之子 方倍工作室',
	        link: 'http://movie.douban.com/subject/25785114/',
	        imgUrl: 'http://img3.douban.com/view/movie_poster_cover/spst/public/p2166127561.jpg',
	        trigger: function (res) {
	          alert('用户点击分享到朋友圈');
	        },
	        success: function (res) {
	          alert('已分享');
	        },
	        cancel: function (res) {
	          alert('已取消');
	        },
	        fail: function (res) {
	          alert(JSON.stringify(res));
	        }
	      });
	      alert('已注册获取“分享到朋友圈”状态事件');
  };

});

wx.error(function (res) {
  alert(res.errMsg);
});