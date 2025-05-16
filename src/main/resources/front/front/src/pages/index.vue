<template>
	<div class="main-containers">
		<div class="body-containers">
			<div class="top-container">
				<!-- info -->
				<div class="top_title">
					<span @click="goMenu('/index/home')">爱猫小当家</span>
				</div>
				<!-- notice -->
				<div id="div1">
					<div>公告</div>
					<ul>
						<li v-html="noticeDetail.content"></li>
					</ul>
				</div>
				<div class="top_tel"></div>
			

				<el-dropdown class="dropdown-box" @command="handleCommand" trigger="click">
					<div class="el-dropdown-link" v-if="Token">
						<img class="top_avatar2" v-if="headportrait&&Token" :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')">
						<span class="top_label2"></span>
						<span class="top_nickname2">{{username}}</span>
						<span class="icon iconfont icon-xiala"></span>
					</div>
					<div class="el-dropdown-link" v-if="!Token">
						<div class="login-item" @click="toLogin">
							<span class="icon iconfont icon-tijiao16"></span>
							登录
						</div>
					</div>
					<el-dropdown-menu class="top-el-dropdown-menu" slot="dropdown" v-if="Token">
						<el-dropdown-item class="shop-item" :command="'shop'">
							<span class="icon iconfont icon-wuliu8"></span>
							购物车
						</el-dropdown-item>
						<el-dropdown-item v-if="notAdmin" class="user-item" :command="'user'">
							<span class="icon iconfont icon-geren11"></span>
							个人中心
						</el-dropdown-item>
						<el-dropdown-item class="register-item" :command="'register'">
							<span class="icon iconfont icon-fanhui14"></span>
							退出
						</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</div>


			<div class="menu-preview">
				<div class="menu-list">
					<div class="menu-home" :class="activeMenu=='/index/home'?'menu-active':''" @click="goMenu('/index/home')">
						<div class="title">
							<span class="icon iconfont icon-home19"></span>
							<div class="text">首页</div>
						</div>
					</div>
					<div class="menu-item" v-for="(item,index) in menuList" :key="index" @mouseenter="menuShowClick4(index)" @mouseleave="menuShowClick4(-1)" :class="activeMenu==item.url?'menu-active':''" @click.stop="goMenu(item.url)">
						<div class="title">
							<span :class="iconArr[index]"></span>
							<div class="text">{{item.name}}</div>
						</div>
						<transition name="el-zoom-in-top">
							<div v-if="showType4==index&&item.hasCate" class="menu-child-list">
								<div class="child-item" v-for="(items,indexs) in item.cateList" :key="indexs" @click.stop="cateClick(item.url,items)">{{items}}</div>
							</div>
						</transition>
					</div>
					<div class="menu-shop" :class="activeMenu=='/index/cart'?'menu-active':''" @click="goMenu('/index/cart')" v-if="Token">
						<div class="title">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<div class="text">购物车</div>
						</div>
					</div>
					<div class="menu-user" :class="activeMenu=='/index/center'?'menu-active':''" @click="goMenu('/index/center')" v-if="Token && notAdmin">
						<div class="title">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<div class="text">个人中心</div>
						</div>
					</div>
				</div>
			</div>

			<div class="banner-preview" v-if="carouselChange()">
				<div class="swiper-container mySwiper3">
					<div class="swiper-wrapper">
						<div class="swiper-slide" v-for="item in carouselList" :key="item.id">
							<div class="swiper-item">
								<el-image v-if="preHttp(item.value)" @click="carouselClick(item.url)" :src="item.value" fit="cover"></el-image>
								<el-image v-else @click="carouselClick(item.url)" :src="baseUrl + item.value" fit="cover"></el-image>
							</div>
						</div>
					</div>
					<div class="banner-hidden">
					</div>
					<!-- Add Pagination -->
					<div class="swiper-pagination"></div>
					<!-- Add Arrows -->
					<div class="swiper-button-next">
						<span class="icon iconfont icon-jiantou18"></span>
					</div>
					<div class="swiper-button-prev">
						<span class="icon iconfont icon-jiantou39"></span>
					</div>
				</div>
			</div>
			<router-view id="scrollView"></router-view>
			
			<div class="bottom-preview">
				<div class="footer"><div v-html="bottomContent"></div></div>
			</div>
		</div>
		
	</div>
</template>

<script>
	import Vue from 'vue'
	import Swiper from "swiper";
	import axios from 'axios'
export default {
	data() {
		return {
			// 系统公告
			noticeDetail: {},
			timer2: null,
			activeIndex: '0',
			baseUrl: '',
			carouselList: [],
			carouselForm: {
				inHome: true,
				inOther: true,
			},
			menuList: [],
			headportrait: localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'',
			Token: localStorage.getItem('frontToken'),
			username: localStorage.getItem('username'),
			notAdmin: localStorage.getItem('frontSessionTable')!='"users"',
			iconArr: [
				'el-icon-star-off',
				'el-icon-goods',
				'el-icon-warning',
				'el-icon-question',
				'el-icon-info',
				'el-icon-help',
				'el-icon-picture-outline-round',
				'el-icon-camera-solid',
				'el-icon-video-camera-solid',
				'el-icon-video-camera',
				'el-icon-bell',
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-operation',
				'el-icon-s-promotion',
				'el-icon-s-release',
				'el-icon-s-ticket',
				'el-icon-s-management',
				'el-icon-s-open',
				'el-icon-s-shop',
				'el-icon-s-marketing',
				'el-icon-s-flag',
				'el-icon-s-comment',
				'el-icon-s-finance',
				'el-icon-s-claim',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check'
			],
			bottomContent: '',
			showType4: -1,
		}
	},
	async created() {
		this.baseUrl = this.$config.baseUrl;
		this.menuList = this.$config.indexNav;
		this.getCarousel();
		if(localStorage.getItem('frontToken') && localStorage.getItem('frontToken')!=null) {
			this.getSession()
		}
		this.cateList = this.$config.cateList
		if(this.cateList.length){
			for(let x in this.menuList){
				for(let i in this.cateList){
					if(this.menuList[x].name==this.cateList[i].name){
						await this.$http.get(`option/${this.cateList[i].refTable}/${this.cateList[i].refColumn}`).then(rs=>{
							this.menuList[x].cateList = rs.data.data
							this.menuList[x].hasCate = true
						})
					}
				}
			}
		}
	},
	mounted() {
		this.activeIndex = localStorage.getItem('keyPath') || '0';


		// banner
		setTimeout(()=>{
			new Swiper(".mySwiper3", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"pagination":{"el":".swiper-pagination","clickable":true},"autoplay":{"delay":2500,"disableOnInteraction":false},"effect":"fade"})
		}, 500)

		// 系统公告
		this.getNotice()
	},
	computed: {
		activeMenu() {
			const route = this.$route
			const {
				meta,
				path
			} = route
			// if st path, the sidebar will highlight the path you sete
			if (meta.activeMenu) {
				return meta.activeMenu
			}
			return path
		},
	},
	watch: {
		$route(newValue) {
			let that = this
			let url = window.location.href
			let arr = url.split('#')
			for (let x in this.menuList) {
				if (newValue.path == this.menuList[x].url) {
					this.activeIndex = x
				}
			}
			this.Token = localStorage.getItem('frontToken')
			if(arr[1]!='/index/home'){
				var element = document.getElementById('scrollView');
				var distance = element.offsetTop;
				window.scrollTo( 0, distance )
			}else{
				window.scrollTo( 0, 0 )
			}
		},
		headportrait(){
			this.$forceUpdate()
		},
	},
	methods: {
		cateClick(url,fenlei){
			this.$router.push(url + '?homeFenlei=' + fenlei);
		},
		preHttp(str) {
			return str && str.substr(0,4)=='http';
		},

		// 获取系统公告
		getNotice() {
			this.$http.get('systemnotice/detail/1').then(res=>{
				if (res.data&&res.data.code==0) {
					this.noticeDetail = res.data.data
					this.$nextTick(() => {
						this.notice()
					})
				}
			})
		},
		notice() {
			var oDiv = document.getElementById('div1');
			var oUl = oDiv.getElementsByTagName('ul')[0];
			var aLi = oUl.getElementsByTagName('li');
			var speed = 5;
			oUl.innerHTML = oUl.innerHTML + oUl.innerHTML;
			oUl.style.width = aLi[0].offsetWidth * aLi.length + 'px';
		
			function move() {
				if (oUl.offsetLeft < -oUl.offsetWidth / 2) { //因为第一个offsetLeft可能是负数，所以要加一个负号
					oUl.style.left = '0';
				}
				if (oUl.offsetLeft > 0) {
					oUl.style.left = -oUl.offsetWidth / 2 + 'px';
				}
				oUl.style.left = oUl.offsetLeft - speed + 'px';
			}
			this.timer2 = setInterval(move, 100);
			
			oDiv.addEventListener('mouseenter', e => {
				e.stopPropagation()
				clearInterval(this.timer2);
			})
			oDiv.addEventListener('mouseleave', e => {
				e.stopPropagation()
				this.timer2 = setInterval(move, 100);
			})
		},
		async getSession() {
			await this.$http.get(`${localStorage.getItem('UserTableName')}/session`, {emulateJSON: true}).then(async res => {
				if (res.data.code == 0) {
					localStorage.setItem('sessionForm',JSON.stringify(res.data.data))
					localStorage.setItem('frontUserid', res.data.data.id);
					if(res.data.data.vip) {
						localStorage.setItem('vip', res.data.data.vip);
					}
					if(res.data.data.touxiang) {
						this.headportrait = res.data.data.touxiang
						localStorage.setItem('frontHeadportrait', res.data.data.touxiang);
					} else if(res.data.data.headportrait) {
						this.headportrait = res.data.data.headportrait
						localStorage.setItem('frontHeadportrait', res.data.data.headportrait);
					}
				}
			});
		},
		handleSelect(keyPath) {
			if (keyPath) {
				localStorage.setItem('keyPath', keyPath)
			}
		},
		toLogin() {
		  this.$router.push('/login');
		},
		logout() {
			localStorage.clear();
			Vue.http.headers.common['Token'] = "";
			this.$router.push('/index/home');
			this.activeIndex = '0'
			localStorage.setItem('keyPath', this.activeIndex)
			this.Token = ''
			this.$forceUpdate()
			this.$message({
				message: '登出成功',
				type: 'success',
				duration: 1000,
			});
		},
		getCarousel() {
			this.$http.get('config/list', {params: { page: 1, limit: 3 }}).then(res => {
				if (res.data.code == 0) {
					this.carouselList = res.data.data.list;
				}
			});
		},
		// 轮播图跳转
		carouselClick(url) {
			if (url) {
				if (url.indexOf('https') != -1) {
					window.open(url)
				} else {
					this.$router.push(url)
				}
			}
		},
		carouselChange(){
			let url = window.location.href
			let arr = url.split('#')
			return (this.carouselForm.inHome&&arr[1]=='/index/home')||(this.carouselForm.inOther&&arr[1]!='/index/home')
		},
		goBackend() {
			localStorage.setItem('Token', localStorage.getItem('frontToken'));
			localStorage.setItem('role', localStorage.getItem('frontRole'));
			localStorage.setItem('sessionTable', localStorage.getItem('frontSessionTable'));
			localStorage.setItem('headportrait', localStorage.getItem('frontHeadportrait'));
			localStorage.setItem('userid', localStorage.getItem('frontUserid'));
			window.location.href = `${this.$config.baseUrl}admin/dist/index.html`
			
		},
		menuShowClick4(index){
			this.showType4 = index
		},
		goMenu(path) {
			this.$router.push(path);
		},
		handleCommand(name){
			if(name == 'register') {
				this.logout()
			}
			else if (name == 'shop') {
				this.goMenu('/index/cart')
			}
			else if (name == 'user'){
				this.goMenu('/index/center')
			}
			else if (name == 'login'){
				this.toLogin()
			}
		},
	}
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.top-el-dropdown-menu {
		border: 1px solid #EBEEF5;
		border-radius: 4px;
		padding: 10px 0;
		box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
		margin: 18px 0;
		background: #fff;
		.shop-item {
			border: 0;
			padding: 0 8px;
			margin: 0 0px;
			color: inherit;
			background: #fff;
			width: auto;
			font-size: inherit;
			line-height: 32px;
			height: 32px;
			.icon {
				color: inherit;
				font-size: inherit;
			}
		}
		.shop-item:hover {
			color: #333;
			background: #475a8330;
		}
		.user-item {
			border: 0;
			padding: 0 8px;
			margin: 0 0px;
			color: inherit;
			background: #fff;
			width: auto;
			font-size: inherit;
			line-height: 32px;
			height: 32px;
			.icon {
				color: inherit;
				font-size: inherit;
			}
		}
		.user-item:hover {
			color: #333;
			background: #475a8330;
		}
		.register-item {
			border: 0;
			padding: 0 8px;
			margin: 0 0px;
			color: inherit;
			background: #fff;
			width: auto;
			font-size: inherit;
			line-height: 32px;
			height: 32px;
			.icon {
				color: inherit;
				font-size: inherit;
			}
		}
		.register-item:hover {
			color: #333;
			background: #475a8330;
		}
	}
	.main-containers {
		.body-containers {
			padding: 0px 0 0;
			margin: 0;
			background: #f3f6ff;
			min-height: 100vh;
			position: relative;
			.top-container {
				padding: 0 20px;
				z-index: 1000;
				color: #fff;
				display: flex;
				font-size: 16px;
				border-radius: 8px;
				box-shadow: 0 0px 0px rgba(64, 158, 255, .3);
				top: 30px;
				left: 5%;
				background: rgba(27,73,85,.9);
				width: 90%;
				justify-content: flex-end;
				align-items: center;
				position: absolute;
				height: 80px;
				.top_title {
					flex: 1;
					display: block;
					width: auto;
					span {
						padding: 0;
						color: #fff;
						font-size: 22px;
						line-height: 44px;
						float: left;
					}
				}
				#div1 {
					z-index: 888;
					overflow: hidden;
					top: 630px;
					background: #1b4955;
					width: 300px;
					position: absolute;
					right: 0;
					height: 38px;
					div {
						padding: 0 10px;
						z-index: 1;
						color: #fff;
						background: #1b4955;
						display: inline-block;
						font-size: 14px;
						line-height: 40px;
						position: relative;
					}
					ul {
						padding: 0;
						margin: 0;
						top: 0;
						left: 0;
						position: absolute;
						li {
							color: #fff;
							width: 400px;
							font-size: 14px;
							line-height: 40px;
							float: left;
							list-style: none;
						}
					}
				}
				.top_tel {
					margin: 0 10px;
					color: #000;
					font-size: 16px;
				}
				.dropdown-box {
					color: inherit;
					display: flex;
					font-size: inherit;
					position: absolute;
					right: 20px;
					.el-dropdown-link {
						color: inherit;
						display: flex;
						font-size: inherit;
						align-items: center;
						.top_avatar2 {
							border-radius: 100%;
							margin: 0 10px;
							object-fit: cover;
							display: inline-block;
							width: 40px;
							height: 40px;
						}
						.top_label2 {
							color: inherit;
							font-size: inherit;
							line-height: 32px;
						}
						.top_nickname2 {
							color: inherit;
							font-size: inherit;
							line-height: 32px;
						}
						.icon {
							margin: 0 0 0 5px;
							color: #fff;
							font-size: 14px;
						}
						.login-item {
							border: 0;
							cursor: pointer;
							padding: 0 8px;
							margin: 0 0px;
							color: inherit;
							background: none;
							width: auto;
							font-size: inherit;
							line-height: 32px;
							height: 32px;
							.icon {
								color: inherit;
								font-size: inherit;
							}
						}
						.login-item:hover {
						}
					}
				}
			}
			.menu-preview {
				.el-scrollbar {
					height: 100%;
			  
					& /deep/ .scrollbar-wrapper-vertical {
						overflow-x: hidden;
					}
			  
					& /deep/ .scrollbar-wrapper-horizontal {
						overflow-y: hidden;
			  
						.el-scrollbar__view {
							white-space: nowrap;
						}
					}
				}
				margin: 0 0 10px;
				z-index: 1001;
				top: 50px;
				left: 18%;
				background: none;
				width: 64%;
				position: absolute;
				height: auto;
				.menu-list {
					padding: 0 10px;
					margin: 0 auto;
					background: none;
					display: flex;
					width: 100%;
					justify-content: center;
					position: relative;
					// 首页
					.menu-home {
						cursor: pointer;
						.title {
							cursor: pointer;
							padding: 0 10px;
							color: #bcd1e1;
							display: flex;
							.icon {
								padding: 0 10px;
								margin: 0;
								color: inherit;
								display: none;
								width: 14px;
								font-size: 14px;
								line-height: 43px;
								height: 43px;
							}
							.text {
								padding: 0 10px;
								color: inherit;
								font-size: 16px;
								line-height: 43px;
								height: 43px;
							}
						}
					}
					.menu-home:hover {
						.title {
							color: #fff;
						}
					}
					.menu-home.menu-active {
						.title {
							color: #fff;
						}
					}
					// 其他盒子
					.menu-item {
						color: #000;
						background: none;
						.title {
							cursor: pointer;
							padding: 0 10px;
							color: #bcd1e1;
							display: flex;
							span {
								padding: 0 10px;
								margin: 0;
								color: inherit;
								display: none;
								width: 14px;
								font-size: 14px;
								line-height: 42px;
								height: 42px;
							}
							.text {
								padding: 0 10px;
								color: inherit;
								font-size: 16px;
								line-height: 43px;
								height: 43px;
							}
						}
						.menu-child-list {
							padding: 5px;
							z-index: 11;
							flex-direction: column;
							background: rgba(86,138,150,1);
							display: flex;
							width: auto;
							justify-content: flex-start;
							position: absolute;
							flex-wrap: wrap;
							.child-item {
								cursor: pointer;
								padding: 0 20px;
								color: #000;
								width: 100% !important;
								font-size: 15px;
								line-height: 40px;
							}
							.child-item:hover {
								color: #1b4955;
								background: #fff;
							}
						}
					}
					.menu-item:hover {
						.title {
							color: #fff;
						}
					}
					.menu-item.menu-active {
						.title {
							color: #fff;
						}
					}
					// 购物车
					.menu-shop {
						cursor: pointer;
						color: #fff;
						display: none;
						line-height: 50px;
						height: 50px;
						.title {
							padding: 0 20px;
							display: flex;
							height: 50px;
							.icon {
								padding: 0 10px;
								margin: 0;
								color: inherit;
								width: 14px;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
							.text {
								padding: 0 10px;
								color: inherit;
								font-size: 14px;
								line-height: 50px;
								height: 50px;
							}
						}
					}
					.menu-shop:hover {
						.title {
							background: #36ca61;
						}
					}
					.menu-shop.menu-active {
						.title {
							background: #55ff00;
						}
					}
					// 个人中心
					.menu-user {
						color: #000;
						background: none;
						display: none;
						.title {
							cursor: pointer;
							padding: 0 10px;
							color: #fff;
							display: flex;
							.icon {
								padding: 0 10px;
								margin: 0;
								color: inherit;
								display: none;
								width: 14px;
								font-size: 14px;
								line-height: 60px;
								height: 60px;
							}
							.text {
								padding: 0 10px;
								color: inherit;
								font-size: 15px;
								line-height: 43px;
								height: 43px;
							}
						}
					}
					.menu-user:hover {
						.title {
							color: #333;
							background: #e6cca6;
						}
					}
					.menu-user.menu-active {
						.title {
							color: #333;
							background: #e6cca6;
						}
					}
				}
			}
			.banner-preview {
				padding: 0 0 38px;
				margin: 0 auto;
				background: #1b4955;
				width: 100%;
				height: 100%;
				.swiper-button-prev:after {
					display:none;
				}
				.swiper-button-next:after {
					display:none;
				}
				.swiper-slide {
					.swiper-item {
						width: 100%;
						height: 100%;
						.el-image {
							object-fit: cover;
							width: 100%;
							height: 660px;
						}
					}
				}
				@keyframes wave1 {from { left: -236px } to { left: -1233px }}
				@keyframes wave2 {from { left: 0 } to { left: -1009px }}
				.swiper-pagination {
					left: 0;
					bottom: 10px;
					width: 100%;
					/deep/ span.swiper-pagination-bullet {
						border-radius: 100%;
						margin: 0 4px;
						background: #000;
						display: inline-block;
						width: 8px;
						opacity: .2;
						height: 8px;
					}
					/deep/ span.swiper-pagination-bullet:hover {
						background: #fff;
						opacity: 1;
					}
					/deep/ span.swiper-pagination-bullet.swiper-pagination-bullet-active {
						background: #fff;
						opacity: 1;
					}
				}
				.swiper-button-next {
					margin: -12px 5% 0 0;
					top: 50%;
					display: none;
					width: 24px;
					height: 24px;
					.icon {
						color: #fff;
						width: 24px;
						font-size: 24px;
						height: 24px;
					}
				}
				.swiper-button-prev {
					margin: -12px 0 0 5%;
					top: 50%;
					display: none;
					width: 24px;
					height: 24px;
					.icon {
						color: #fff;
						width: 24px;
						font-size: 24px;
						height: 24px;
					}
				}
			}
			.bottom-preview {
				width: 100%;
				height: auto;
				.footer {
					padding: 20px calc((100% - 1200px)/2);
					margin: 0 auto;
					overflow: hidden;
					color: #fff;
					background: #333;
					width: 100%;
					min-height: 120px;
					text-align: center;
					height: auto;
				}
			}
		}
	}
</style>
