import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import AddrList from '../pages/shop-address/list'
import AddrAdd from '../pages/shop-address/addOrUpdate'
import Order from '../pages/shop-order/list'
import OrderConfirm from '../pages/shop-order/confirm'
import Cart from '../pages/shop-cart/list'
import News from '../pages/news/news-list'
import NewsDetail from '../pages/news/news-detail'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import maomixinxiList from '../pages/maomixinxi/list'
import maomixinxiDetail from '../pages/maomixinxi/detail'
import maomixinxiAdd from '../pages/maomixinxi/add'
import maomizhongleiList from '../pages/maomizhonglei/list'
import maomizhongleiDetail from '../pages/maomizhonglei/detail'
import maomizhongleiAdd from '../pages/maomizhonglei/add'
import lingyangxinxiList from '../pages/lingyangxinxi/list'
import lingyangxinxiDetail from '../pages/lingyangxinxi/detail'
import lingyangxinxiAdd from '../pages/lingyangxinxi/add'
import chongwushangpinList from '../pages/chongwushangpin/list'
import chongwushangpinDetail from '../pages/chongwushangpin/detail'
import chongwushangpinAdd from '../pages/chongwushangpin/add'
import shangpinfenleiList from '../pages/shangpinfenlei/list'
import shangpinfenleiDetail from '../pages/shangpinfenlei/detail'
import shangpinfenleiAdd from '../pages/shangpinfenlei/add'
import chongwufuwuList from '../pages/chongwufuwu/list'
import chongwufuwuDetail from '../pages/chongwufuwu/detail'
import chongwufuwuAdd from '../pages/chongwufuwu/add'
import fuwuyuyueList from '../pages/fuwuyuyue/list'
import fuwuyuyueDetail from '../pages/fuwuyuyue/detail'
import fuwuyuyueAdd from '../pages/fuwuyuyue/add'
import quxiaoyuyueList from '../pages/quxiaoyuyue/list'
import quxiaoyuyueDetail from '../pages/quxiaoyuyue/detail'
import quxiaoyuyueAdd from '../pages/quxiaoyuyue/add'
import fuwuleixingList from '../pages/fuwuleixing/list'
import fuwuleixingDetail from '../pages/fuwuleixing/detail'
import fuwuleixingAdd from '../pages/fuwuleixing/add'
import syslogList from '../pages/syslog/list'
import syslogDetail from '../pages/syslog/detail'
import syslogAdd from '../pages/syslog/add'
import couponList from '../pages/coupon/list'
import couponDetail from '../pages/coupon/detail'
import couponAdd from '../pages/coupon/add'
import mycouponList from '../pages/mycoupon/list'
import mycouponDetail from '../pages/mycoupon/detail'
import mycouponAdd from '../pages/mycoupon/add'
import chargerecordList from '../pages/chargerecord/list'
import chargerecordDetail from '../pages/chargerecord/detail'
import chargerecordAdd from '../pages/chargerecord/add'
import newstypeList from '../pages/newstype/list'
import newstypeDetail from '../pages/newstype/detail'
import newstypeAdd from '../pages/newstype/add'
import aboutusList from '../pages/aboutus/list'
import aboutusDetail from '../pages/aboutus/detail'
import aboutusAdd from '../pages/aboutus/add'
import systemintroList from '../pages/systemintro/list'
import systemintroDetail from '../pages/systemintro/detail'
import systemintroAdd from '../pages/systemintro/add'
import systemnoticeList from '../pages/systemnotice/list'
import systemnoticeDetail from '../pages/systemnotice/detail'
import systemnoticeAdd from '../pages/systemnotice/add'
import discussmaomixinxiList from '../pages/discussmaomixinxi/list'
import discussmaomixinxiDetail from '../pages/discussmaomixinxi/detail'
import discussmaomixinxiAdd from '../pages/discussmaomixinxi/add'
import discusschongwushangpinList from '../pages/discusschongwushangpin/list'
import discusschongwushangpinDetail from '../pages/discusschongwushangpin/detail'
import discusschongwushangpinAdd from '../pages/discusschongwushangpin/add'
import discusschongwufuwuList from '../pages/discusschongwufuwu/list'
import discusschongwufuwuDetail from '../pages/discusschongwufuwu/detail'
import discusschongwufuwuAdd from '../pages/discusschongwufuwu/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
                {
                    path: 'shop-address/list',
                    component: AddrList
                },
                {
                    path: 'shop-address/addOrUpdate',
                    component: AddrAdd
                },
				{
					path: 'shop-order/order',
					component: Order
				},
				{
					path: 'cart',
					component: Cart
				},
				{
					path: 'shop-order/orderConfirm',
					component: OrderConfirm
				},
				{
					path: 'news',
					component: News
				},
				{
					path: 'newsDetail',
					component: NewsDetail
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'maomixinxi',
					component: maomixinxiList
				},
				{
					path: 'maomixinxiDetail',
					component: maomixinxiDetail
				},
				{
					path: 'maomixinxiAdd',
					component: maomixinxiAdd
				},
				{
					path: 'maomizhonglei',
					component: maomizhongleiList
				},
				{
					path: 'maomizhongleiDetail',
					component: maomizhongleiDetail
				},
				{
					path: 'maomizhongleiAdd',
					component: maomizhongleiAdd
				},
				{
					path: 'lingyangxinxi',
					component: lingyangxinxiList
				},
				{
					path: 'lingyangxinxiDetail',
					component: lingyangxinxiDetail
				},
				{
					path: 'lingyangxinxiAdd',
					component: lingyangxinxiAdd
				},
				{
					path: 'chongwushangpin',
					component: chongwushangpinList
				},
				{
					path: 'chongwushangpinDetail',
					component: chongwushangpinDetail
				},
				{
					path: 'chongwushangpinAdd',
					component: chongwushangpinAdd
				},
				{
					path: 'shangpinfenlei',
					component: shangpinfenleiList
				},
				{
					path: 'shangpinfenleiDetail',
					component: shangpinfenleiDetail
				},
				{
					path: 'shangpinfenleiAdd',
					component: shangpinfenleiAdd
				},
				{
					path: 'chongwufuwu',
					component: chongwufuwuList
				},
				{
					path: 'chongwufuwuDetail',
					component: chongwufuwuDetail
				},
				{
					path: 'chongwufuwuAdd',
					component: chongwufuwuAdd
				},
				{
					path: 'fuwuyuyue',
					component: fuwuyuyueList
				},
				{
					path: 'fuwuyuyueDetail',
					component: fuwuyuyueDetail
				},
				{
					path: 'fuwuyuyueAdd',
					component: fuwuyuyueAdd
				},
				{
					path: 'quxiaoyuyue',
					component: quxiaoyuyueList
				},
				{
					path: 'quxiaoyuyueDetail',
					component: quxiaoyuyueDetail
				},
				{
					path: 'quxiaoyuyueAdd',
					component: quxiaoyuyueAdd
				},
				{
					path: 'fuwuleixing',
					component: fuwuleixingList
				},
				{
					path: 'fuwuleixingDetail',
					component: fuwuleixingDetail
				},
				{
					path: 'fuwuleixingAdd',
					component: fuwuleixingAdd
				},
				{
					path: 'syslog',
					component: syslogList
				},
				{
					path: 'syslogDetail',
					component: syslogDetail
				},
				{
					path: 'syslogAdd',
					component: syslogAdd
				},
				{
					path: 'coupon',
					component: couponList
				},
				{
					path: 'couponDetail',
					component: couponDetail
				},
				{
					path: 'couponAdd',
					component: couponAdd
				},
				{
					path: 'mycoupon',
					component: mycouponList
				},
				{
					path: 'mycouponDetail',
					component: mycouponDetail
				},
				{
					path: 'mycouponAdd',
					component: mycouponAdd
				},
				{
					path: 'chargerecord',
					component: chargerecordList
				},
				{
					path: 'chargerecordDetail',
					component: chargerecordDetail
				},
				{
					path: 'chargerecordAdd',
					component: chargerecordAdd
				},
				{
					path: 'newstype',
					component: newstypeList
				},
				{
					path: 'newstypeDetail',
					component: newstypeDetail
				},
				{
					path: 'newstypeAdd',
					component: newstypeAdd
				},
				{
					path: 'aboutus',
					component: aboutusList
				},
				{
					path: 'aboutusDetail',
					component: aboutusDetail
				},
				{
					path: 'aboutusAdd',
					component: aboutusAdd
				},
				{
					path: 'systemintro',
					component: systemintroList
				},
				{
					path: 'systemintroDetail',
					component: systemintroDetail
				},
				{
					path: 'systemintroAdd',
					component: systemintroAdd
				},
				{
					path: 'systemnotice',
					component: systemnoticeList
				},
				{
					path: 'systemnoticeDetail',
					component: systemnoticeDetail
				},
				{
					path: 'systemnoticeAdd',
					component: systemnoticeAdd
				},
				{
					path: 'discussmaomixinxi',
					component: discussmaomixinxiList
				},
				{
					path: 'discussmaomixinxiDetail',
					component: discussmaomixinxiDetail
				},
				{
					path: 'discussmaomixinxiAdd',
					component: discussmaomixinxiAdd
				},
				{
					path: 'discusschongwushangpin',
					component: discusschongwushangpinList
				},
				{
					path: 'discusschongwushangpinDetail',
					component: discusschongwushangpinDetail
				},
				{
					path: 'discusschongwushangpinAdd',
					component: discusschongwushangpinAdd
				},
				{
					path: 'discusschongwufuwu',
					component: discusschongwufuwuList
				},
				{
					path: 'discusschongwufuwuDetail',
					component: discusschongwufuwuDetail
				},
				{
					path: 'discusschongwufuwuAdd',
					component: discusschongwufuwuAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
