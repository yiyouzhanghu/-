<template>
	<div>
		<div class="login-container">
			<el-form ref="loginForm" :model="loginForm" :rules="rules" class="login_form animate__animated animate__">
				<div class="login_form2">
					<div class="login-title">爱猫小当家</div>
					<div v-if="loginType==1" class="list-item" prop="username">
						<div class="lable">
							账号：
						</div>
						<input v-model="loginForm.username" placeholder="请输入账号：">
					</div>
					<div v-if="loginType==1" class="list-item" prop="password">
						<div class="lable">
							密码：
						</div>
						<div class="password-box">
							<input v-model="loginForm.password" placeholder="请输入密码：" :type="showPassword?'text':'password'">
							<span class="icon iconfont" :class="showPassword?'icon-liulan13':'icon-liulan17'" @click="showPassword=!showPassword"></span>
						</div>
					</div>

					<div class="list-item" v-if="roles.length>1">
						<div class="lable">
							角色：
						</div>
						<div class="list-type" prop="role">
							<el-radio v-model="loginForm.tableName" :label="item.tableName" v-for="(item, index) in roles" :key="index" @change.native="getCurrentRow(item)">{{item.roleName}}</el-radio>
						</div>
					</div>

			
					<div class="list-btn">
						<el-button class="login_btn" v-if="loginType==1" @click="submitForm('loginForm')">登录</el-button>

						<div class="list-btn2">
							<router-link class="register_btn" :to="{path: '/register', query: {role: item.tableName,pageFlag:'register'}}" v-if="item.hasFrontRegister=='是'" v-for="(item, index) in roles" :key="index">注册{{item.roleName.replace('注册','')}}</router-link>
							<router-link class="resetpwd_btn" :to="{path: '/register', query: {pageFlag:'security1'}}">忘记密码？</router-link>
						</div>
					</div>
				</div>
				<div class="idea1"></div>
				<div class="idea2"></div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css';
import menu from '@/config/menu'
export default {
	//数据集合
	data() {
		return {
            baseUrl: this.$config.baseUrl,
            loginType: 1,
			roleMenus: [],
			loginForm: {
				username: '',
				password: '',
				tableName: '',
				code: '',
			},
			role: '',
            roles: [],
			rules: {
				username: [
					{ required: true, message: '请输入账号', trigger: 'blur' }
				],
				password: [
					{ required: true, message: '请输入密码', trigger: 'blur' }
				]
			},
			codes: [{
				num: 1,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}, {
				num: 2,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}, {
				num: 3,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}, {
				num: 4,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}],
			flag: false,
			verifyCheck2: false,
			showPassword: false,
		}
	},
	components: {
	},
	created() {
		this.roleMenus = menu.list()
		for(let item in this.roleMenus) {
			if(this.roleMenus[item].hasFrontLogin=='是') {
				this.roles.push(this.roleMenus[item]);
			}
		}
		
	},
	mounted() {
	},
	//方法集合
	methods: {
		randomString() {
			var len = 4;
			var chars = [
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2',
				'3', '4', '5', '6', '7', '8', '9'
			]
			var colors = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f']
			var sizes = ['14', '15', '16', '17', '18']
			
			var output = []
			for (var i = 0; i < len; i++) {
				// 随机验证码
				var key = Math.floor(Math.random() * chars.length)
				this.codes[i].num = chars[key]
				// 随机验证码颜色
				var code = '#'
				for (var j = 0; j < 6; j++) {
					var key = Math.floor(Math.random() * colors.length)
					code += colors[key]
				}
				this.codes[i].color = code
				// 随机验证码方向
				var rotate = Math.floor(Math.random() * 45)
				var plus = Math.floor(Math.random() * 2)
				if (plus == 1) rotate = '-' + rotate
				this.codes[i].rotate = 'rotate(' + rotate + 'deg)'
				// 随机验证码字体大小
				var size = Math.floor(Math.random() * sizes.length)
				this.codes[i].size = sizes[size] + 'px'
			}
		},
		getCurrentRow(row) {
			this.role = row.roleName;
		},
		submitForm(formName) {
			if (this.roles.length!=1) {
				if (!this.role) {
					this.$message.error("请选择登录用户类型");
					return false;
				}
			} else {
				this.role = this.roles[0].roleName;
				this.loginForm.tableName = this.roles[0].tableName;
			}
			if (!this.loginForm.username) {
				this.$message.error("请输入用户名");
				return;
			}
			if (!this.loginForm.password) {
				this.$message.error("请输入密码");
				return;
			}

			this.loginPost(formName)
		},
		loginPost(formName) {
			this.$refs[formName].validate((valid) => {
				if (valid) {
					this.$http.get(`${this.loginForm.tableName}/login`, {params: this.loginForm}).then(res => {
						if (res.data.code === 0) {
							localStorage.setItem('frontToken', res.data.token);
							localStorage.setItem('UserTableName', this.loginForm.tableName);
							localStorage.setItem('username', this.loginForm.username);
							// localStorage.setItem('adminName', this.loginForm.username);
							localStorage.setItem('frontSessionTable', this.loginForm.tableName);
							localStorage.setItem('frontRole', this.role);
							localStorage.setItem('keyPath', 0);
							this.$router.push('/');
							this.$message({
								message: '登录成功',
								type: 'success',
								duration: 1500,
							});
						} else {
							this.$message.error(res.data.msg);
						}
					});
				} else {
					return false;
				}
			});
		},
    }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.login-container {
		background-repeat: no-repeat;
		background-size: 100% 100% !important;
		background: url(http://codegen.caihongy.cn/20241202/e6375f2dc4bd402a8ff0d5bac37717f2.jpg);
		display: flex;
		width: 100%;
		min-height: 100vh;
		justify-content: center;
		align-items: center;
		background-position: center center;
		position: relative;
		background: url(http://codegen.caihongy.cn/20241202/e6375f2dc4bd402a8ff0d5bac37717f2.jpg);
		.login_form {
			border-radius: 10px;
			padding: 30px 40px 20px 40px;
			margin: 0px auto 150px;
			z-index: 10;
			background: #fff;
			width: 600px;
			position: relative;
			height: auto;
			.login_form2 {
				width: 100%;
				.login-title {
					margin: 0 auto 20px;
					color: #333;
					font-weight: 600;
					width: 100%;
					font-size: 16px;
					line-height: 44px;
					text-align: center;
				}
				.list-item {
					margin: 0 auto 26px;
					background: none;
					display: flex;
					width: 100%;
					align-items: center;
					.lable {
						border: 1px solid #dce2e5;
						border-radius: 4px 0 0 4px;
						color: #093b48;
						background: #fcfbfc;
						width: 120px;
						font-size: 15px;
						line-height: 48px;
						text-align: right;
					}
					input {
						border: 1px solid #dce2e5;
						border-radius: 0 4px 4px 0;
						padding: 0 10px;
						color: #666;
						flex: 1;
						background: #dce2e5;
						width: calc(100% - 0px);
						font-size: 15px;
						height: 50px;
					}
					input:focus {
						border: 1px solid #dce2e5;
						border-radius: 0 4px 4px 0;
						padding: 0 10px;
						outline: none;
						color: #666;
						flex: 1;
						background: #dce2e5;
						width: calc(100% - 0px);
						font-size: 15px;
						height: 50px;
					}
					.password-box {
						flex: 1;
						display: flex;
						width: calc(100% - 0px);
						position: relative;
						align-items: center;
						input {
							border: 1px solid #dce2e5;
							border-radius: 0 4px 4px 0;
							padding: 0 10px;
							color: #666;
							background: #dce2e5;
							width: 100%;
							font-size: 14px;
							height: 50px;
						}
						input:focus {
							border: 1px solid #dce2e5;
							border-radius: 0 4px 4px 0;
							padding: 0 10px;
							color: #666;
							background: #dce2e5;
							width: 100%;
							font-size: 14px;
							height: 50px;
						}
						.iconfont {
							cursor: pointer;
							z-index: 1;
							color: #000;
							top: 0;
							font-size: 16px;
							line-height: 44px;
							position: absolute;
							right: 16px;
						}
					}
					input::placeholder {
						color: #666;
						font-size: 15px;
					}
				}
				.list-type {
					border-radius: 0 4px 4px 0;
					padding: 0 0 0 10px;
					margin: 0;
					background: #dce2e5;
					display: flex;
					width: calc(100% - 120px);
					line-height: 50px;
					align-items: center;
					height: 50px;
					/deep/ .el-radio__input .el-radio__inner {
						background: rgba(53, 53, 53, 0);
						border-color: #666666;
					}
					/deep/ .el-radio__input.is-checked .el-radio__inner {
						background: #1b4955;
						border-color: #1b4955;
					}
					/deep/ .el-radio__label {
						color: #666666;
						font-size: 16px;
					}
					/deep/ .el-radio__input.is-checked+.el-radio__label {
						color: #1b4955;
						font-size: 16px;
					}
				}
				.list-btn {
					padding: 0 0 0 0px;
					margin: 0 auto 20px;
					display: flex;
					width: 100%;
					flex-wrap: wrap;
					.login_btn {
						border: 0;
						cursor: pointer;
						border-radius: 4px;
						padding: 0 30px;
						margin: 0 0px;
						color: #fff;
						background: #1b4955;
						letter-spacing: 4px;
						width: 100%;
						font-size: 18px;
						height: 52px;
					}
					.login_btn:hover {
					}
					.list-btn2 {
						margin: 0px auto 0;
						display: flex;
						width: 100%;
						justify-content: space-between;
						flex-wrap: wrap;
						order: -1;
						.register_btn {
							cursor: pointer;
							padding: 5px;
							margin: 0 10px 10px 0;
							color: #333;
							background: #fff;
							text-decoration: none;
							font-size: 16px;
						}
						.register_btn:hover {
							opacity: 0.8;
						}
						.resetpwd_btn {
							cursor: pointer;
							padding: 5px;
							margin: 0 0px 10px 0;
							color: #333;
							background: #fff;
							text-decoration: none;
							font-size: 15px;
						}
						.resetpwd_btn:hover {
							opacity: 0.8;
						}
					}
				}
			}
			.idea1 {
				background: red;
				display: none;
				width: 100%;
				height: 40px;
			}
			.idea2 {
				background: blue;
				display: none;
				width: 100%;
				height: 40px;
			}
		}
	}
</style>
