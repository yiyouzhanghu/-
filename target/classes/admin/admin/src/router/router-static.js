import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import news from '@/views/modules/news/list'
	import maomixinxi from '@/views/modules/maomixinxi/list'
	import aboutus from '@/views/modules/aboutus/list'
	import coupon from '@/views/modules/coupon/list'
	import chongwufuwu from '@/views/modules/chongwufuwu/list'
	import chongwushangpin from '@/views/modules/chongwushangpin/list'
	import lingyangxinxi from '@/views/modules/lingyangxinxi/list'
	import syslog from '@/views/modules/syslog/list'
	import systemnotice from '@/views/modules/systemnotice/list'
	import fuwuleixing from '@/views/modules/fuwuleixing/list'
	import discussmaomixinxi from '@/views/modules/discussmaomixinxi/list'
	import quxiaoyuyue from '@/views/modules/quxiaoyuyue/list'
	import maomizhonglei from '@/views/modules/maomizhonglei/list'
	import systemintro from '@/views/modules/systemintro/list'
	import yonghu from '@/views/modules/yonghu/list'
	import shangpinfenlei from '@/views/modules/shangpinfenlei/list'
	import chargerecord from '@/views/modules/chargerecord/list'
	import discusschongwufuwu from '@/views/modules/discusschongwufuwu/list'
	import fuwuyuyue from '@/views/modules/fuwuyuyue/list'
	import orders from '@/views/modules/orders/list'
	import discusschongwushangpin from '@/views/modules/discusschongwushangpin/list'
	import config from '@/views/modules/config/list'
	import newstype from '@/views/modules/newstype/list'


//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/news',
		name: '公告信息',
		component: news
	}
	,{
		path: '/maomixinxi',
		name: '猫咪信息',
		component: maomixinxi
	}
	,{
		path: '/aboutus',
		name: '关于我们',
		component: aboutus
	}
	,{
		path: '/coupon',
		name: '优惠券',
		component: coupon
	}
	,{
		path: '/chongwufuwu',
		name: '宠物服务',
		component: chongwufuwu
	}
	,{
		path: '/chongwushangpin',
		name: '宠物商品',
		component: chongwushangpin
	}
	,{
		path: '/lingyangxinxi',
		name: '领养信息',
		component: lingyangxinxi
	}
	,{
		path: '/syslog',
		name: '系统日志',
		component: syslog
	}
	,{
		path: '/systemnotice',
		name: '系统公告',
		component: systemnotice
	}
	,{
		path: '/fuwuleixing',
		name: '服务类型',
		component: fuwuleixing
	}
	,{
		path: '/discussmaomixinxi',
		name: '猫咪信息评论',
		component: discussmaomixinxi
	}
	,{
		path: '/quxiaoyuyue',
		name: '取消预约',
		component: quxiaoyuyue
	}
	,{
		path: '/maomizhonglei',
		name: '猫咪种类',
		component: maomizhonglei
	}
	,{
		path: '/systemintro',
		name: '系统简介',
		component: systemintro
	}
	,{
		path: '/yonghu',
		name: '用户',
		component: yonghu
	}
	,{
		path: '/shangpinfenlei',
		name: '商品分类',
		component: shangpinfenlei
	}
	,{
		path: '/chargerecord',
		name: '充值记录',
		component: chargerecord
	}
	,{
		path: '/discusschongwufuwu',
		name: '宠物服务评论',
		component: discusschongwufuwu
	}
	,{
		path: '/fuwuyuyue',
		name: '服务预约',
		component: fuwuyuyue
	}
	,{
		path: '/orders/:status',
		name: '订单管理',
		component: orders
	}
	,{
		path: '/discusschongwushangpin',
		name: '宠物商品评论',
		component: discusschongwushangpin
	}
	,{
		path: '/config',
		name: '轮播图管理',
		component: config
	}
	,{
		path: '/newstype',
		name: '公告信息分类',
		component: newstype
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
