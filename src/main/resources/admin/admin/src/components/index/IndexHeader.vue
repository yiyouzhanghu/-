<template>
	<div class="navbar">
		<div class="title">
			<span class="title-name">{{this.$project.projectName}}</span>
		</div>
		<!-- 系统通知-2 -->
		<div id="div1">
			<div>公告</div>
			<ul>
				<li v-html="noticeDetail.content"></li>
			</ul>
		</div>
		<div class="dropdown-box">
			<div class="el-dropdown-link">
				<el-image v-if="user" :src="avatar?this.$base.url + avatar : require('@/assets/img/avator.png')" fit="cover"></el-image>
				<span class="label">欢迎您，</span>
				<span class="nickname">{{this.$storage.get('adminName')}}</span>
			</div>
			<div class="top-el-dropdown-menu-2">
				<div class="item1" @click="handleCommand('')">首页</div>
				<div class="item2" @click="handleCommand('center')">个人中心</div>
				<div class="item3" v-if="this.$storage.get('role')!='管理员'" @click="handleCommand('front')">退出到前台</div>
				<div class="item3" v-if="this.$storage.get('role')=='管理员'" @click="handleCommand('backUp')">数据备份</div>
				<div class="item4" @click="handleCommand('logout')">退出登录</div>
			</div>
		</div>
	</div>
</template>

<script>
	import {
		Loading
	} from 'element-ui';
	import axios from 'axios';
	export default {
		data() {
			return {
				dialogVisible: false,
				ruleForm: {},
				user: null,
				// 系统公告
				noticeDetail: {},
			};
		},
		created() {
			// 系统公告
			this.getNotice()
		},
		computed: {
			avatar(){
				return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
			},
		},
		mounted() {
			let sessionTable = this.$storage.get("sessionTable")
			this.$http({
				url: sessionTable + '/session',
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code === 0) {
					if(sessionTable == 'yonghu') {
						this.$storage.set('headportrait',data.data.touxiang)
					}
					if(sessionTable == 'users') {
						this.$storage.set('headportrait',data.data.image)
					}
					this.$storage.set('userForm',JSON.stringify(data.data))
					this.user = data.data;
					this.$storage.set('userid',data.data.id);
				} else {
					let message = this.$message
					message.error(data.msg);
				}
			});
		},
		methods: {
			// 获取系统公告
			getNotice() {
				this.$http({
					url: '/systemnotice/detail/1',
					method: "get"
				}).then(({data})=>{
					if ( data && data.code==0 ) {
						this.noticeDetail = data.data
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
				this.timer = setInterval(move, 100);
			
				oDiv.addEventListener('mouseenter', e => {
					e.stopPropagation()
					clearInterval(this.timer);
				})
				oDiv.addEventListener('mouseleave', e => {
					e.stopPropagation()
					this.timer = setInterval(move, 100);
				})
			},
			handleCommand(name) {
				if (name == 'logout') {
					this.onLogout()
				} 
				else if (name == 'front') {
					this.onIndexTap()
				}
				else if (name == 'backUp'){
					this.backUp()
				}
				else {
					let router = this.$router
					name = '/'+name
					router.push(name)
				}
			},
			onLogout() {
				let storage = this.$storage
				let router = this.$router
				storage.clear()
				this.$store.dispatch('tagsView/delAllViews')
				router.replace({
					name: "login"
				});
			},
			onIndexTap(){
				window.location.href = `${this.$base.indexUrl}`
			},
			backUp() {
				this.$confirm('是否备份数据库?', '数据备份提示', {
					confirmButtonText: '是',
					cancelButtonText: '否',
					type: 'warning'
				}).then(() => {
					this.$http({
						url: '/mysqldump',
						method: "get"
					}).then(({
						data
					}) => {
						if (data) {
							const binaryData = [];
							binaryData.push(data);
							const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
								type: 'application/pdf;chartset=UTF-8'
							}))
							const a = document.createElement('a')
							a.href = objectUrl
							a.download = 'mysql.dmp'
							// a.click()
							// 下面这个写法兼容火狐
							a.dispatchEvent(new MouseEvent('click', {
								bubbles: true,
								cancelable: true,
								view: window
							}))
							window.URL.revokeObjectURL(data)
							message.message("数据备份成功")
						} else {
							let message = this.$message
							message.error(data.msg);
						}
					});
				});
			},
		}
	};
</script>


<style lang="scss" scoped>
	.navbar {
		.title {
			top: 15px;
			left: 40px;
			display: block;
			position: absolute;
			.title-name {
				padding: 0;
				color: #fff;
				font-weight: 600;
				font-size: 20px;
				line-height: 44px;
				float: left;
			}
		}
		#div1 {
			margin: 0 20px 0 0;
			overflow: hidden;
			background: none;
			width: 300px;
			position: relative;
			height: 40px;
			div {
				padding: 0 10px 0 34px;
				z-index: 1;
				color: #fff;
				background: url(http://codegen.caihongy.cn/20241006/e3e7a09ac5b2470da9b4a2de1efc24c6.png) no-repeat left center / 26px,#4dc2de;
				display: inline-block;
				font-size: 16px;
				line-height: 40px;
				position: relative;
			}
			ul {
				top: 0;
				left: 0;
				position: absolute;
				
				li {
					color: #fff;
					width: 400px;
					font-size: 16px;
					line-height: 40px;
					float: left;
					list-style: none;
				}
			}
		}
		.dropdown-box {
			color: inherit;
			display: flex;
			font-size: inherit;
			right: 20px;
			.el-dropdown-link {
				display: flex;
				align-items: center;
				.el-image {
					border-radius: 100%;
					margin: 0 10px;
					object-fit: cover;
					display: none;
					width: 42px;
					height: 42px;
				}
				.label {
					color: inherit;
					display: none;
					font-size: inherit;
					line-height: 32px;
				}
				.nickname {
					color: inherit;
					display: none;
					font-size: inherit;
					line-height: 32px;
				}
			}
			.top-el-dropdown-menu-2 {
				display: flex;
				justify-content: center;
				align-items: center;
				div.item1 {
					cursor: pointer;
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
					line-height: 32px;
				}
				div.item1:hover {
				}
				div.item2 {
					cursor: pointer;
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
					line-height: 32px;
				}
				div.item2:hover {
				}
				div.item3 {
					cursor: pointer;
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
					line-height: 32px;
				}
				div.item3:hover {
				}
				div.item4 {
					cursor: pointer;
					padding: 0 5px;
					color: inherit;
					font-size: inherit;
					line-height: 32px;
				}
				div.item4:hover {
				}
			}
		}
	}
</style>
