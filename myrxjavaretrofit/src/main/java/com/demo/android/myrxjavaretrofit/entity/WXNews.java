package com.demo.android.myrxjavaretrofit.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by XiongRun on 2017/5/9.
 */

public class WXNews implements Serializable {


	/**
	 * code : 200
	 * msg : success
	 * newslist : [{"ctime":"2017-05-09","title":"看不见的伤痕更深更疼？| 什么是\u201c微笑抑郁症\u201d（Smiling Depression）","description":"CPPA幸福中国","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-22999408.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5MzAxMjMwOQ==&idx=2&mid=2654084295&sn=6bce487bdd0dcbdb569b00bca345363d"},{"ctime":"2017-05-09","title":"你太精，我太傻，不适合一起玩耍","description":"晒石会","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-21877538.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MzAxMjA3MjA1Mw==&idx=6&mid=2652626560&sn=41ad2f2bec1923b11b942d6d91854783"},{"ctime":"2017-05-09","title":"茗星私享丨彭志军：陶瓷艺术的践行者，不妥协方能成就自我","description":"茶语网","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-23002115.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5MDE2NzQ4MA==&idx=3&mid=2657112918&sn=b61365d5cebee043dcc3bba260021c89"},{"ctime":"2017-05-09","title":"他从以色列空运一百多斤金属板来中国，还引起了众人围观","description":"南都周刊","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-23001811.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjE2MDI0OTk2MQ==&idx=1&mid=2650848820&sn=8ddddec7d2698193142bb438b8ce7beb"},{"ctime":"2017-05-09","title":"纪念《资本论》第1卷出版150周年：资本论还有意义吗","description":"经济学家圈","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-12762888.static/640","url":"http://mp.weixin.qq.com/s?__biz=MzA5NTU4NTUzMg==&idx=2&mid=2657270514&sn=1236afd6d0e4a9f0aaee7f984165b6f0"},{"ctime":"2017-05-09","title":"恭贺吕祖圣诞千秋：历代显应，慈心度人","description":"腾讯道学","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-12938832.static/640","url":"http://mp.weixin.qq.com/s?__biz=MzA5MzE2MzMzMg==&idx=1&mid=2651890919&sn=cb7d51ec8601a12c0d78a216f25c496c"},{"ctime":"2017-05-09","title":"未来人物 | 2016设计系毕业作品展回顾（二）","description":"广院设计","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-23001557.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MzAxMTc3OTg2Mg==&idx=1&mid=2651120473&sn=32103e24a43da6ef743383986f914d25"},{"ctime":"2017-05-09","title":"世界顶级名画赏析（上）","description":"李银河","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-23000522.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MzAxNDU3MzI5OA==&idx=2&mid=2651963352&sn=b975bc7ba51a6b638a164f7d21362ae1"},{"ctime":"2017-05-09","title":"\u201c剑上的蛇影\u201d：欧洲中世纪早期拼接锻打花纹钢剑鉴赏","description":"冷兵器研究所","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-23000111.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MzI4NTAyNzMzNA==&idx=3&mid=2652387412&sn=7debcfc164bdabe6de23d7c903a2bf37"},{"ctime":"2017-05-09","title":"小学阶段是学习书法的黄金时期！","description":"篆刻微刻","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-22915379.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MzA3Mzk1NDUxMA==&idx=5&mid=2655632333&sn=c54613c85715c7d820ef89df5b8583dd"}]
	 */

	private int code;
	private String msg;
	private List<NewslistBean> newslist;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<NewslistBean> getNewslist() {
		return newslist;
	}

	public void setNewslist(List<NewslistBean> newslist) {
		this.newslist = newslist;
	}

	public static class NewslistBean {
		/**
		 * ctime : 2017-05-09
		 * title : 看不见的伤痕更深更疼？| 什么是“微笑抑郁症”（Smiling Depression）
		 * description : CPPA幸福中国
		 * picUrl : http://zxpic.gtimg.com/infonew/0/wechat_pics_-22999408.jpg/640
		 * url : http://mp.weixin.qq.com/s?__biz=MjM5MzAxMjMwOQ==&idx=2&mid=2654084295&sn=6bce487bdd0dcbdb569b00bca345363d
		 */

		private String ctime;
		private String title;
		private String description;
		private String picUrl;
		private String url;

		public String getCtime() {
			return ctime;
		}

		public void setCtime(String ctime) {
			this.ctime = ctime;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}
}
